package com.nguyenquockhanh.restaurant.dao;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.nguyenquockhanh.restaurant.entity.RestaurantBrand;

@Repository("RestaurantBrandDao")
@Transactional
public class RestaurantBrandDaoimpl extends AbstractDao<Integer, RestaurantBrand> implements RestaurantBrandDao {

}
