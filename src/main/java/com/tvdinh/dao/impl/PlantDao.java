package com.tvdinh.dao.impl;

import org.springframework.stereotype.Repository;

import com.tvdinh.dao.IPlantDao;
import com.tvdinh.entity.PlantEntity;

@Repository
public class PlantDao extends AbstractDao<Long, PlantEntity> implements IPlantDao{

}
