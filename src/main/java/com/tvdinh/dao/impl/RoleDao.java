package com.tvdinh.dao.impl;

import com.tvdinh.dao.IRoleDao;
import com.tvdinh.entity.RoleEntity;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class RoleDao extends AbstractDao<Long, RoleEntity> implements IRoleDao {
}
