package com.nguyenquockhanh.restaurant.dao;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.nguyenquockhanh.restaurant.entity.WareHouseSessionDetail;

@Repository("WarehouseSessionDetailDao")
@Transactional
public class WarehouseSessionDetailDaoImpl extends AbstractDao<Integer, WareHouseSessionDetail>
		implements WarehouseSessionDetailDao {

}
