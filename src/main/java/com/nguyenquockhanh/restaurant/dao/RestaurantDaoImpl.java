package com.nguyenquockhanh.restaurant.dao;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.nguyenquockhanh.restaurant.entity.Restaurant;

@Repository("RestaurantDao")
@Transactional
public class RestaurantDaoImpl extends AbstractDao<Integer, Restaurant> implements RestaurantDao {

}
