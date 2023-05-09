package com.nguyenquockhanh.restaurant.dao;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.nguyenquockhanh.restaurant.entity.Branch;

@Repository("BranchDao")
@Transactional
public class BranchDaoImpl extends AbstractDao<Integer, Branch> implements BranchDao {

}
