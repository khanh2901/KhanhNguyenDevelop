package com.nguyenquockhanh.restaurant.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.nguyenquockhanh.restaurant.entity.Category;

@Repository("CategoryDao")
@Transactional
public class CategoryDaoImpl extends AbstractDao<Integer, Category> implements CategoryDao {

	@Override
	public void create(Category category) {

		this.getSession().save(category);
	}

	@Override
	public void update(Category category) {
		this.getSession().update(category);
	}

	@Override
	public Category findOne(int id) {
		return this.getSession().find(Category.class, id);
	}

	@Override
	public List<Category> findAll(int id) {
		CriteriaQuery<Category> criteria = this.getBuilder().createQuery(Category.class);
		Root<Category> root = criteria.from(Category.class);

		List<Predicate> predicates = new ArrayList<>();

		predicates.add(this.getBuilder().equal(root.get("status"), 1));
		predicates.add(this.getBuilder().equal(root.get("restaurantId"), id));

		criteria.select(root).where(predicates.toArray(new Predicate[] {}));
		return this.getSession().createQuery(criteria).getResultList();
	}

	@Override
	public List<Category> findByStatus(int id) {
		CriteriaQuery<Category> criteria = this.getBuilder().createQuery(Category.class);
		Root<Category> root = criteria.from(Category.class);

		List<Predicate> predicates = new ArrayList<>();

		predicates.add(this.getBuilder().equal(root.get("status"), 0));
		predicates.add(this.getBuilder().equal(root.get("restaurantId"), id));

		criteria.select(root).where(predicates.toArray(new Predicate[] {}));
		return this.getSession().createQuery(criteria).getResultList();
	}

}
