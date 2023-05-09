package com.nguyenquockhanh.restaurant.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.Table;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.nguyenquockhanh.restaurant.entity.Booking;
import com.nguyenquockhanh.restaurant.entity.Customer;
import com.nguyenquockhanh.restaurant.entity.Food;
import com.nguyenquockhanh.restaurant.entity.Tables;

@Repository("TableDao")
@Transactional
public class TableDaoImpl extends AbstractDao<Integer, Table> implements TableDao {

	@Override
	public void create(Booking booking) {
		// TODO Auto-generated method stub
		 this.getSession().save(booking);
	}

	@Override
	public List<Booking> spGBooking(int i, int j, int k, String dateBooking) {
		
		StoredProcedureQuery query = this.getSession()
				.createStoredProcedureQuery("sp_g_booking",Booking.class)
				.registerStoredProcedureParameter("restaurantId", Integer.class, ParameterMode.IN)
				.registerStoredProcedureParameter("restaurantBrandId", Integer.class, ParameterMode.IN)
				.registerStoredProcedureParameter("restaurantBranchId", Integer.class, ParameterMode.IN)
				.registerStoredProcedureParameter("fromDate", String.class, ParameterMode.IN);
		query.setParameter("restaurantId", i);
		query.setParameter("restaurantBrandId", j);
		query.setParameter("restaurantBranchId", k);
		query.setParameter("fromDate", dateBooking);

		return query.getResultList();
	}

	@Override
	public List<Booking> findAll() {
		CriteriaQuery<Booking> criteria = this.getBuilder().createQuery(Booking.class);
		Root<Booking> root = criteria.from(Booking.class);

		List<Predicate> predicates = new ArrayList<>();

		predicates.add(this.getBuilder().equal(root.get("status"), 1));


		criteria.select(root).where(predicates.toArray(new Predicate[] {}));
		return this.getSession().createQuery(criteria).getResultList();
	}

	@Override
	public Booking findOne(int id) {
		CriteriaQuery<Booking> criteria = this.getBuilder().createQuery(Booking.class);
		Root<Booking> root = criteria.from(Booking.class);

		List<Predicate> predicates = new ArrayList<>();

		predicates.add(this.getBuilder().equal(root.get("userId"), id));

		criteria.select(root).where(predicates.toArray(new Predicate[] {}));
		return this.getSession().createQuery(criteria).uniqueResult();
	}

	@Override
	public void update(Booking bookings) {
		this.getSession().update(bookings);
		
	}

	@Override
	public Booking findById(int id) {
		// TODO Auto-generated method stub
		return this.getSession().find(Booking.class, id);
	}

	@Override
	public List<Tables> findAllTable() {
		CriteriaQuery<Tables> criteria = this.getBuilder().createQuery(Tables.class);
		Root<Tables> root = criteria.from(Tables.class);

		criteria.select(root);
		return this.getSession().createQuery(criteria).getResultList();
	}



}
