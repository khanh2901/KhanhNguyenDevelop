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

import com.nguyenquockhanh.restaurant.entity.Material;
import com.nguyenquockhanh.restaurant.entity.MaterialModel;
import com.nguyenquockhanh.restaurant.entity.Unit;
import com.nguyenquockhanh.restaurant.response.StoreProcedureListResult;

@Repository("MaterialDao")
@Transactional
public class MaterialDaoimpl extends AbstractDao<Integer, Material> implements MaterialDao {

	@Override
	public void create(Material material) {
		this.getSession().save(material);
		
	}

	@Override
	public void update(Material material) {
			this.getSession().update(material);
	}

	@Override
	public Material findOne(int id) {
		return this.getSession().find(Material.class, id);
	}

	@Override
	public List<Material> findAll(int id) {
		CriteriaQuery<Material> criteria = this.getBuilder().createQuery(Material.class);
		Root<Material> root = criteria.from(Material.class);

		List<Predicate> predicates = new ArrayList<>();

		predicates.add(this.getBuilder().equal(root.get("status"), 1));
		predicates.add(this.getBuilder().equal(root.get("restaurantId"), id));


		criteria.select(root).where(predicates.toArray(new Predicate[] {}));
		return this.getSession().createQuery(criteria).getResultList();
	}

	@Override
	public List<Material> findByStatus(int id) {
		CriteriaQuery<Material> criteria = this.getBuilder().createQuery(Material.class);
		Root<Material> root = criteria.from(Material.class);

		List<Predicate> predicates = new ArrayList<>();

		predicates.add(this.getBuilder().equal(root.get("status"), 0));
		predicates.add(this.getBuilder().equal(root.get("restaurantId"), id));


		criteria.select(root).where(predicates.toArray(new Predicate[] {}));
		return this.getSession().createQuery(criteria).getResultList();
	}

	@Override
	public List<MaterialModel> getLisstTotalQuanittyMaterial(int id) {

		StoredProcedureQuery query = this.getSession()
				.createStoredProcedureQuery("sp_g_total_quanitty_material",MaterialModel.class)
				.registerStoredProcedureParameter("userId", Integer.class, ParameterMode.IN);
		query.setParameter("userId", id);
		
		return query.getResultList();

	}

	
	
	
	
	
	
	
	
}
