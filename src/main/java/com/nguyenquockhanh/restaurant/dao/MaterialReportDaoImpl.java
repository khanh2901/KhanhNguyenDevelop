package com.nguyenquockhanh.restaurant.dao;

import java.util.List;

import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.nguyenquockhanh.restaurant.entity.Material;
import com.nguyenquockhanh.restaurant.entity.ReportMaterialModel;

@Repository
@Transactional
public class MaterialReportDaoImpl extends AbstractDao<Integer, Material> implements MaterialReportDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<ReportMaterialModel> spGMaterialReport() {
		StoredProcedureQuery query = this.getSession().createStoredProcedureQuery("sp_g_revenue_material",
				ReportMaterialModel.class);

		return query.getResultList();
	}

}
