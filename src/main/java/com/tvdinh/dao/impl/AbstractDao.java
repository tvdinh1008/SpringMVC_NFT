package com.tvdinh.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.PersistenceUnit;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tvdinh.dao.GenericDao;

@Transactional
public class AbstractDao<ID extends Serializable, T> implements GenericDao<ID, T> {
	@PersistenceContext
	protected EntityManager entityManager;

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
}
