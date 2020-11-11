package com.tvdinh.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import com.tvdinh.dao.GenericDao;

import javassist.tools.rmi.ObjectNotFoundException;

@Transactional
public class AbstractDao<ID extends Serializable, T> implements GenericDao<ID, T> {
	@PersistenceContext
	protected EntityManager entityManager;
	protected final Logger logger = Logger.getLogger(this.getClass());// chú ý file config: resouces/log4j.properties


	private Class<T> persistenceClass; // model.classư

	@SuppressWarnings("unchecked")
	public AbstractDao() {
		this.persistenceClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[1];
	}

	// lấy tên class truyền vào
	public String getPersistenceClassName() {
		return persistenceClass.getSimpleName();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll() {
		List<T> result = new ArrayList<T>();

		try {
			String sql = "select t from " + getPersistenceClassName() + " t";
			result = entityManager.createQuery(sql).getResultList();
		} catch (Exception e) {
			return result;
		}
		return result;
	}

	@Override
	public List<T> findRange(int firstResult, int maxResults) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T update(T entity) {
		T result = null;
		try {
			Object object = entityManager.merge(entity);
			result = (T) object;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return result;
	}

	@Override
	public T save(T entity) {
		try {
			entityManager.persist(entity);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
		return entity;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T findById(ID var1) {
		T result = null;
		try {
			Object object = entityManager.find(persistenceClass, var1);
			result = (T) object;
			if (result == null) {
				throw new ObjectNotFoundException("not found " + var1, null);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} 
		return result;
	}

	@Override
	public Integer delete(ID[] ids) {
		Integer count = 0;
		try {
			for (ID id : ids) {
				T entity = (T) entityManager.find(persistenceClass, id);
				entityManager.remove(entity);
				count++;
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} 
		return count;// nếu count==list.size thì chứng tỏ xóa thành công
	}

	@Override
	public Long count() {
		Long count = (long) 0;
		try {
			String sql = "select count(t) from " + getPersistenceClassName() + " t";
			count = (long) entityManager.createQuery(sql).getSingleResult();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return count;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Object[] findByProperties(Map<String, Object> property, Integer offset, Integer limit, String sortExpression,
			String sortDirection, String JOIN_FETCH) {
		List<T> result = new ArrayList<T>();
		Long count = 0l;
		try {
			String params[] = new String[property.size()];
			Object values[] = new Object[property.size()];
			int i = 0;
			for (Map.Entry item : property.entrySet()) {
				params[i] = (String) item.getKey();
				values[i] = item.getValue();
				i++;
			}

			//StringBuilder sql1 = new StringBuilder("SELECT t FROM " + getPersistenceClassName() + " t WHERE 1=1");
			StringBuilder sql1 = new StringBuilder("SELECT t FROM " + getPersistenceClassName() + " t");
			
			if(StringUtils.isNotBlank(JOIN_FETCH)) {
				sql1.append(" JOIN FETCH " + "t."+JOIN_FETCH);
			}
			
			sql1.append(" WHERE 1=1");
			
			
			for (int j = 0; j < property.size(); j++) {
				sql1.append(" and ").append("LOWER(t."+params[j]+") LIKE '%' || :"+params[j]+" || '%'");
			}
			if(StringUtils.isNotBlank(sortExpression) && StringUtils.isNotBlank(sortDirection)) {
				sql1.append(" ORDER BY t.").append(sortExpression);
				sql1.append(" "+ (sortDirection.equals("ASC")?"ASC":"DESC"));
			}
 			Query q = entityManager.createQuery(sql1.toString()).setFirstResult(offset).setMaxResults(limit);
			for(int j=0;j<property.size();j++) {
				if(values[j] instanceof Integer) {
					q.setParameter(params[j], String.valueOf(values[j]));
				}else {
					q.setParameter(params[j], values[j]);
				}
			}
			result=q.getResultList();
			
			
			StringBuilder sql2 =new StringBuilder("SELECT count(t) FROM " + getPersistenceClassName() + " t WHERE 1=1");
			for (int j = 0; j < property.size(); j++) {
				sql2.append(" and ").append("LOWER(t."+params[j]+") LIKE '%' || :"+params[j]+" || '%'");
			}
			Query q2 =  entityManager.createQuery(sql2.toString());
			for(int j=0;j<property.size();j++) {
				if(values[j] instanceof Integer) {
					q2.setParameter(params[j], String.valueOf(values[j]));
				}else {
					q2.setParameter(params[j], values[j]);
				}
			}
			count=(Long)q2.getSingleResult();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return new Object[] { count, result };
	}

}
