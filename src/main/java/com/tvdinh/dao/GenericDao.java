package com.tvdinh.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<ID extends Serializable,T> {
	public List<T> findAll();
}
