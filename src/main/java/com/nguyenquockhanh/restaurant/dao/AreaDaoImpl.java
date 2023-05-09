package com.nguyenquockhanh.restaurant.dao;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.nguyenquockhanh.restaurant.entity.Area;
@Repository("AreaDao")
@Transactional
public class AreaDaoImpl extends AbstractDao<Integer,Area> implements AreaDao{

}
