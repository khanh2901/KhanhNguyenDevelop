package com.nguyenquockhanh.restaurant.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.nguyenquockhanh.restaurant.entity.Customer;
import com.nguyenquockhanh.restaurant.entity.Unit;

@Repository("UnitDao")
@Transactional
public class UnitDaoImpl extends AbstractDao<Integer, Unit> implements UnitDao {

	@Override
	public void create(Unit unit) {
		this.getSession().save(unit);
		
	}
	
	@Override
	public void update(Unit unit) {
		this.getSession().update(unit);
		
	}

	@Override
	public List<Unit> findAll(int id) {
		CriteriaQuery<Unit> criteria = this.getBuilder().createQuery(Unit.class);
		Root<Unit> root = criteria.from(Unit.class);

		List<Predicate> predicates = new ArrayList<>();

		predicates.add(this.getBuilder().equal(root.get("status"), 1));
		predicates.add(this.getBuilder().equal(root.get("restaurantId"), id));


		criteria.select(root).where(predicates.toArray(new Predicate[] {}));
		return this.getSession().createQuery(criteria).getResultList();
	}

	@Override
	public List<Unit> findByStatus(int id) {
		CriteriaQuery<Unit> criteria = this.getBuilder().createQuery(Unit.class);
		Root<Unit> root = criteria.from(Unit.class);

		List<Predicate> predicates = new ArrayList<>();

		predicates.add(this.getBuilder().equal(root.get("status"), 0));
		predicates.add(this.getBuilder().equal(root.get("restaurantId"), id));


		criteria.select(root).where(predicates.toArray(new Predicate[] {}));
		return this.getSession().createQuery(criteria).getResultList();
	}

	@Override
	public Unit findOne(int id) {
		// TODO Auto-generated method stub
		return this.getSession().find(Unit.class, id);
	}


}
