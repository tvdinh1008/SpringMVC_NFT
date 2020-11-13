package com.tvdinh.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tvdinh.dao.IPlantDao;
import com.tvdinh.entity.PlantEntity;
import com.tvdinh.service.IPlantService;
@Service
public class PlantService implements IPlantService{
	
	@Autowired
	private IPlantDao plantDao;
	
	@Override
	public List<PlantEntity> findAll() {
		return plantDao.findAll(); 
	}

}
