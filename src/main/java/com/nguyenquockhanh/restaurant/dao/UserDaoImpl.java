package com.nguyenquockhanh.restaurant.dao;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nguyenquockhanh.restaurant.entity.Customer;
import com.nguyenquockhanh.restaurant.entity.User;

@Repository
@Transactional
public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao {

	@Override
	public void create(User user) {
		this.getSession().save(user);

	}

	@Override
	public List<User> findAll() {
		CriteriaQuery<User> criteria = this.getBuilder().createQuery(User.class);
		Root<User> root = criteria.from(User.class);

		List<Predicate> predicates = new ArrayList<>();

		predicates.add(this.getBuilder().equal(root.get("status"), 1));
		predicates.add(this.getBuilder().notEqual(root.get("role"), 3));
		predicates.add(this.getBuilder().notEqual(root.get("role"), 1));



		criteria.select(root).where(predicates.toArray(new Predicate[] {}))
		.orderBy(this.getBuilder().desc(root.get("id")));

		return this.getSession().createQuery(criteria).getResultList();
	}

	@Override
	public List<User> findAllUsers() {
		CriteriaQuery<User> criteria = this.getBuilder().createQuery(User.class);
		Root<User> root = criteria.from(User.class);

		List<Predicate> predicates = new ArrayList<>();

		criteria.select(root).orderBy(this.getBuilder().desc(root.get("id")));

		return this.getSession().createQuery(criteria).getResultList();
	}
	
	
	
	@Override
	public List<User> findByStatus() {
		CriteriaQuery<User> criteria = this.getBuilder().createQuery(User.class);
		Root<User> root = criteria.from(User.class);

		List<Predicate> predicates = new ArrayList<>();

		predicates.add(this.getBuilder().equal(root.get("status"), 0));

		criteria.select(root).where(predicates.toArray(new Predicate[] {}));
		return this.getSession().createQuery(criteria).getResultList();
	}

	@Override
	public User findOne(int id) {
		return this.getSession().find(User.class, id);
	}

	@Override
	public void update(User user) {
		this.getSession().update(user);
	}

	@Override
	public User findByUserName(String username) {
		CriteriaQuery<User> criteria = this.getBuilder().createQuery(User.class);
		Root<User> root = criteria.from(User.class);

		List<Predicate> predicates = new ArrayList<>();

		predicates.add(this.getBuilder().equal(root.get("status"), 1));
		predicates.add(this.getBuilder().equal(root.get("user_name"), username));

		criteria.select(root).where(predicates.toArray(new Predicate[] {}));
		return this.getSession().createQuery(criteria).uniqueResult();
	}

	@Override
	public User findByAccessToken(String accessToken) {
		byte[] decodedBytes = Base64.getMimeDecoder().decode(accessToken);
		String decodedMime = new String(decodedBytes);

		ObjectMapper mapper = new ObjectMapper();
		try {
			User map = mapper.readValue(decodedMime, User.class);

			User user = this.findOne(map.getId());

			if (user != null)
				return user;
			else
				return null;

		} catch (Exception e) {
		}
		return null;
	}

	@Override
	public User findByUserNameAndPss(String username, String pss) {
		CriteriaQuery<User> criteria = this.getBuilder().createQuery(User.class);
		Root<User> root = criteria.from(User.class);

		List<Predicate> predicates = new ArrayList<>();

		predicates.add(this.getBuilder().equal(root.get("user_name"), username));
		predicates.add(this.getBuilder().equal(root.get("password"), pss));


		criteria.select(root).where(predicates.toArray(new Predicate[] {}));
		return this.getSession().createQuery(criteria).uniqueResult();
	}

	@Override
	public List<User> findAllUser() {
		CriteriaQuery<User> criteria = this.getBuilder().createQuery(User.class);
		Root<User> root = criteria.from(User.class);

		List<Predicate> predicates = new ArrayList<>();

		predicates.add(this.getBuilder().equal(root.get("status"), 1));
		predicates.add(this.getBuilder().equal(root.get("role"), 3));


		criteria.select(root).where(predicates.toArray(new Predicate[] {}))
		.orderBy(this.getBuilder().desc(root.get("id")));

		return this.getSession().createQuery(criteria).getResultList();
	}

	@Override
	public User findByPhone(String phone) {
		CriteriaQuery<User> criteria = this.getBuilder().createQuery(User.class);
		Root<User> root = criteria.from(User.class);

		List<Predicate> predicates = new ArrayList<>();

		predicates.add(this.getBuilder().equal(root.get("_phone"), phone));


		criteria.select(root).where(predicates.toArray(new Predicate[] {}));
		return this.getSession().createQuery(criteria).uniqueResult();
	}

	@Override
	public List<User> findAllEmloyee2() {
		CriteriaQuery<User> criteria = this.getBuilder().createQuery(User.class);
		Root<User> root = criteria.from(User.class);

		List<Predicate> predicates = new ArrayList<>();

		predicates.add(this.getBuilder().equal(root.get("status"), 1));
		predicates.add(this.getBuilder().equal(root.get("role"), 2));


		criteria.select(root).where(predicates.toArray(new Predicate[] {}))
		.orderBy(this.getBuilder().desc(root.get("id")));

		return this.getSession().createQuery(criteria).getResultList();
	}

}
