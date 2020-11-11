package com.tvdinh.dao.impl;

import com.tvdinh.dao.IUserDao;
import com.tvdinh.entity.UserEntity;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

@Repository
public class UserDao extends AbstractDao<Long, UserEntity> implements IUserDao {

	@Override
	public UserEntity findOneUsernameAndStatus(String username, int status) {
		UserEntity result=null;
		try {
			StringBuilder sql=new StringBuilder("");
			sql.append("Select * from [user] Where username=:username and status=:status");
			
			Query q=entityManager.createNativeQuery(sql.toString(),UserEntity.class);
			q.setParameter("username", username);
			q.setParameter("status", status);
			result=(UserEntity)q.getSingleResult();
			//cố tình lấy roles thì nó sẽ tạo ra 1 câu truy vấn tiếp(Tức sẽ có 2 câu truy vấn) khi sử dụng lazy.
			//Còn sử dụng EAGER thì 1 câu truy vấn và nó sẽ đổ luôn dữ liệu roles luôn
			result.getRole(); 
		} catch(Exception e) {   
			logger.error(e.getMessage(),e);
		}
		return result;
	}
}
