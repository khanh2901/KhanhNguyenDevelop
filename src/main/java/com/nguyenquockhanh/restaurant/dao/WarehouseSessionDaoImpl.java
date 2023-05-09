package com.nguyenquockhanh.restaurant.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import com.nguyenquockhanh.restaurant.entity.Material;
import com.nguyenquockhanh.restaurant.entity.WareHouseSessionDetail;
import com.nguyenquockhanh.restaurant.entity.WareHousesSession;

@Repository("WarehouseSessionDao")
@Transactional
public class WarehouseSessionDaoImpl extends AbstractDao<Integer, WareHousesSession> implements WarehouseSessionDao {

	@Override
	public void importMaterial(int userId, int discountPercent, int vat, int discountAmount, String description,
			String materials) {

		StoredProcedureQuery query = this.getSession().createStoredProcedureQuery("sp_u_create_warehouse_session")
				.registerStoredProcedureParameter("userId", Integer.class, ParameterMode.IN)
				.registerStoredProcedureParameter("discountPercent", Integer.class, ParameterMode.IN)
				.registerStoredProcedureParameter("_vat", Integer.class, ParameterMode.IN)
				.registerStoredProcedureParameter("discountAmount", Integer.class, ParameterMode.IN)
				.registerStoredProcedureParameter("_description", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("warehouseSessionDetail", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("status_code", Integer.class, ParameterMode.OUT)
				.registerStoredProcedureParameter("message_error", String.class, ParameterMode.OUT);
		query.setParameter("userId", userId);
		query.setParameter("discountPercent", discountPercent);
		query.setParameter("_vat", vat);
		query.setParameter("discountAmount", discountAmount);
		query.setParameter("_description", description);
		query.setParameter("warehouseSessionDetail", materials);

		int statusCode = (int) query.getOutputParameterValue("status_code");
		String messageError = query.getOutputParameterValue("message_error").toString();

	}

	@Override
	public List<WareHousesSession> findAll(int id) {
		CriteriaQuery<WareHousesSession> criteria = this.getBuilder().createQuery(WareHousesSession.class);
		Root<WareHousesSession> root = criteria.from(WareHousesSession.class);

		List<Predicate> predicates = new ArrayList<>();

		predicates.add(this.getBuilder().equal(root.get("status"), 1));

		criteria.select(root).where(predicates.toArray(new Predicate[] {}));
		return this.getSession().createQuery(criteria).getResultList();
	}

	@Override
	public void spCreateFood(int id, String materials, int vat, int categoryType, int discountPercent,
			int discountAmount, String foodName, String avatar) {

		StoredProcedureQuery query = this.getSession().createStoredProcedureQuery("sp_create_food")
				.registerStoredProcedureParameter("userId", Integer.class, ParameterMode.IN)
				.registerStoredProcedureParameter("materialIds", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("vat", Integer.class, ParameterMode.IN)
				.registerStoredProcedureParameter("categoryType", Integer.class, ParameterMode.IN)
				.registerStoredProcedureParameter("discountPerCent", Integer.class, ParameterMode.IN)
				.registerStoredProcedureParameter("discountAmount", Integer.class, ParameterMode.IN)
				.registerStoredProcedureParameter("foodName", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("avatar", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("status_code", Integer.class, ParameterMode.OUT)
				.registerStoredProcedureParameter("message_error", String.class, ParameterMode.OUT);
		query.setParameter("userId", id);
		query.setParameter("materialIds", materials);
		query.setParameter("vat", vat);
		query.setParameter("categoryType", categoryType);
		query.setParameter("discountPerCent", discountPercent);
		query.setParameter("discountAmount", discountAmount);
		query.setParameter("foodName", foodName);
		query.setParameter("avatar", avatar);

		int statusCode = (int) query.getOutputParameterValue("status_code");
		String messageError = query.getOutputParameterValue("message_error").toString();
	}

	@Override
	public List<WareHouseSessionDetail> findAllWareHouseDetail(int id) {
		CriteriaQuery<WareHouseSessionDetail> criteria = this.getBuilder().createQuery(WareHouseSessionDetail.class);
		Root<WareHouseSessionDetail> root = criteria.from(WareHouseSessionDetail.class);

		List<Predicate> predicates = new ArrayList<>();

		predicates.add(this.getBuilder().equal(root.get("warehouseSessionId"), id));

		criteria.select(root).where(predicates.toArray(new Predicate[] {}));
		return this.getSession().createQuery(criteria).getResultList();
	}

}
