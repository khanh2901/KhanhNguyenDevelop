package com.nguyenquockhanh.restaurant.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.nguyenquockhanh.restaurant.entity.BillModel;
import com.nguyenquockhanh.restaurant.entity.Cart;
import com.nguyenquockhanh.restaurant.entity.Customer;
import com.nguyenquockhanh.restaurant.entity.Food;
import com.nguyenquockhanh.restaurant.entity.Material;

@Repository("FoodDao")
@Transactional
public class FoodDaoImpl extends AbstractDao<Integer, Food> implements FoodDao{

	@Override
	public void spCreateFood(int id, String materials, int vat, int categoryType, int discountPercent,
			int discountAmount, String foodName) {
		StoredProcedureQuery query = this.getSession()
				.createStoredProcedureQuery("sp_create_food")
				.registerStoredProcedureParameter("userId", Integer.class, ParameterMode.IN)
				.registerStoredProcedureParameter("materialIds", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("_vat", Integer.class, ParameterMode.IN)
				.registerStoredProcedureParameter("categoryType", Integer.class, ParameterMode.IN)
				.registerStoredProcedureParameter("discountPercent", Integer.class, ParameterMode.IN)
				.registerStoredProcedureParameter("discountAmount", Integer.class, ParameterMode.IN)
				.registerStoredProcedureParameter("foodName", String.class, ParameterMode.IN);
		query.setParameter("userId", id);
		query.setParameter("materialIds", materials);
		query.setParameter("_vat", vat);
		query.setParameter("categoryType", categoryType);
		query.setParameter("discountPercent", discountPercent);
		query.setParameter("discountAmount", discountAmount);
		query.setParameter("foodName", foodName);

			}

	@Override
	public List<Food> findAll(int restaurantId) {
		CriteriaQuery<Food> criteria = this.getBuilder().createQuery(Food.class);
		Root<Food> root = criteria.from(Food.class);

		List<Predicate> predicates = new ArrayList<>();

		predicates.add(this.getBuilder().equal(root.get("status"), 1));
		predicates.add(this.getBuilder().equal(root.get("restaurantId"), restaurantId));


		criteria.select(root).where(predicates.toArray(new Predicate[] {}));
		return this.getSession().createQuery(criteria).getResultList();
	}

	@Override
	public Food findOne(int id) {
		
		return this.getSession().find(Food.class, id);
	}

	@Override
	public void update(Food food) {
		this.getSession().update(food);
		
	}

	@Override
	public List<Food> findAllFoodPause(int restaurantId) {
		CriteriaQuery<Food> criteria = this.getBuilder().createQuery(Food.class);
		Root<Food> root = criteria.from(Food.class);

		List<Predicate> predicates = new ArrayList<>();

		predicates.add(this.getBuilder().equal(root.get("status"), 0));
		predicates.add(this.getBuilder().equal(root.get("restaurantId"), restaurantId));


		criteria.select(root).where(predicates.toArray(new Predicate[] {}));
		return this.getSession().createQuery(criteria).getResultList();
	}

	@Override
	public void saleFood(int restaurantId, int userId, int quantity,String foodIds, String description) {
		StoredProcedureQuery query = this.getSession()
				.createStoredProcedureQuery("sp_u_sale_food")
				.registerStoredProcedureParameter("restaurantId", Integer.class, ParameterMode.IN)
				.registerStoredProcedureParameter("userId", Integer.class, ParameterMode.IN)
				.registerStoredProcedureParameter("quantity", Integer.class, ParameterMode.IN)
				.registerStoredProcedureParameter("_description", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("foodIds", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("status_code", Integer.class, ParameterMode.OUT)
				.registerStoredProcedureParameter("message_error", String.class, ParameterMode.OUT);
		query.setParameter("restaurantId", restaurantId);
		query.setParameter("userId", userId);
		query.setParameter("quantity", quantity);
		query.setParameter("_description", description);
		query.setParameter("foodIds", foodIds);
		
		int statusCode = (int) query.getOutputParameterValue("status_code");
		String messageError = query.getOutputParameterValue("message_error").toString();
	}

	@Override
	public void create(Cart cart) {
		this.getSession().save(cart);
	}

	@Override
	public void update(Cart cart) {
		this.getSession().update(cart); 
		
	}

	@Override
	public Cart findOneCart(int id,int userId) {
		// TODO Auto-generated method stub
		CriteriaQuery<Cart> criteria = this.getBuilder().createQuery(Cart.class);
		Root<Cart> root = criteria.from(Cart.class);

		List<Predicate> predicates = new ArrayList<>();

		predicates.add(this.getBuilder().equal(root.get("foodId"), id));
		predicates.add(this.getBuilder().equal(root.get("userId"), userId));

		criteria.select(root).where(predicates.toArray(new Predicate[] {}));
		return this.getSession().createQuery(criteria).uniqueResult();
	}

	@Override
	public void delete(Cart cart) {
		this.getSession().delete(cart);
		
	}

	@Override
	public Cart findOneCart1(int id) {
		// TODO Auto-generated method stub
		return this.getSession().find(Cart.class, id);
	}

	@Override
	public List<Cart> findAllCart(int restaurantId, int id) {
		CriteriaQuery<Cart> criteria = this.getBuilder().createQuery(Cart.class);
		Root<Cart> root = criteria.from(Cart.class);

		List<Predicate> predicates = new ArrayList<>();

		predicates.add(this.getBuilder().equal(root.get("status"), 1));
		predicates.add(this.getBuilder().equal(root.get("restaurantId"), restaurantId));
		predicates.add(this.getBuilder().equal(root.get("userId"), id));



		criteria.select(root).where(predicates.toArray(new Predicate[] {}));
		return this.getSession().createQuery(criteria).getResultList();
	}

	@Override
	public BillModel spSuccessBill(int id, int restaurantId) {
		StoredProcedureQuery query = this.getSession()
				.createStoredProcedureQuery("sp_u_success_bill",BillModel.class)
				.registerStoredProcedureParameter("userId", Integer.class, ParameterMode.IN)
				.registerStoredProcedureParameter("restaurantId", Integer.class, ParameterMode.IN);
		query.setParameter("userId", id);
		query.setParameter("restaurantId", restaurantId);

		return (BillModel) query.getResultList().stream().findFirst().orElse(null);
	}
	
	
}
