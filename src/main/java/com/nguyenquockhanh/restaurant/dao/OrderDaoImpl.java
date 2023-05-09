package com.nguyenquockhanh.restaurant.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.nguyenquockhanh.restaurant.entity.Order;
import com.nguyenquockhanh.restaurant.entity.OrderDetail;

@Repository("OrderDao")
@Transactional
public class OrderDaoImpl extends AbstractDao<Integer, Order> implements OrderDao {

	@Override
	public List<Order> findAll() {
		CriteriaQuery<Order> criteria = this.getBuilder().createQuery(Order.class);
		Root<Order> root = criteria.from(Order.class);

		criteria.select(root).orderBy(this.getBuilder().asc(root.get("id")));
		return this.getSession().createQuery(criteria).getResultList();
	}

	@Override
	public List<OrderDetail> findAllOrderDetailByOrderId(int orderId) {
		CriteriaQuery<OrderDetail> criteria = this.getBuilder().createQuery(OrderDetail.class);
		Root<OrderDetail> root = criteria.from(OrderDetail.class);

		List<Predicate> predicates = new ArrayList<>();

		predicates.add(this.getBuilder().equal(root.get("order_id"), orderId));

		criteria.select(root).where(predicates.toArray(new Predicate[] {}));
		return this.getSession().createQuery(criteria).getResultList();
	}

	@Override
	public Order findOne(int id) {
		// TODO Auto-generated method stub
		return this.getSession().find(Order.class, id);
	}

	@Override
	public void changStatus(Order order) {
		this.getSession().update(order);

	}

	@Override
	public List<Order> findByCustomerId(int id) {
		CriteriaQuery<Order> criteria = this.getBuilder().createQuery(Order.class);
		Root<Order> root = criteria.from(Order.class);

		List<Predicate> predicates = new ArrayList<>();

		predicates.add(this.getBuilder().equal(root.get("customerId"), id));
		
		criteria.select(root).orderBy(this.getBuilder().asc(root.get("id")));
		return this.getSession().createQuery(criteria).getResultList();
	}

}
