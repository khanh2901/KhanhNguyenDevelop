package com.nguyenquockhanh.restaurant.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.stereotype.Repository;

import com.nguyenquockhanh.restaurant.entity.Customer;
import com.nguyenquockhanh.restaurant.entity.EmployeeWorking;

@Repository("CustomerDao")
@Transactional
public class CustomerDaoImpl extends AbstractDao<Integer, Customer> implements CustomerDao {

	@Override
	public void create(Customer customer) {

		this.getSession().save(customer);
	}

	@Override
	public List<Customer> findAll() {
		CriteriaQuery<Customer> criteria = this.getBuilder().createQuery(Customer.class);
		Root<Customer> root = criteria.from(Customer.class);

		List<Predicate> predicates = new ArrayList<>();

		predicates.add(this.getBuilder().equal(root.get("status"), 1));

		criteria.select(root).where(predicates.toArray(new Predicate[] {}))
				.orderBy(this.getBuilder().desc(root.get("id")));
		return this.getSession().createQuery(criteria).getResultList();
	}

	@Override
	public List<Customer> findByStatus() {
		CriteriaQuery<Customer> criteria = this.getBuilder().createQuery(Customer.class);
		Root<Customer> root = criteria.from(Customer.class);

		List<Predicate> predicates = new ArrayList<>();

		predicates.add(this.getBuilder().equal(root.get("status"), 0));

		criteria.select(root).where(predicates.toArray(new Predicate[] {}));
		return this.getSession().createQuery(criteria).getResultList();
	}

	@Override
	public Customer findOne(int i) {
		return this.getSession().find(Customer.class, i);
	}

	@Override
	public Customer findByPhone(String phone) {
		CriteriaQuery<Customer> criteria = this.getBuilder().createQuery(Customer.class);
		Root<Customer> root = criteria.from(Customer.class);

		List<Predicate> predicates = new ArrayList<>();

		predicates.add(this.getBuilder().equal(root.get("phone"), phone));

		criteria.select(root).where(predicates.toArray(new Predicate[] {}));
		return this.getSession().createQuery(criteria).uniqueResult();
	}

	@Override
	public void update(Customer customer) {
		this.getSession().update(customer);

	}

	@Override
	public Customer findByUserNameAndPassword(String username, String password) {
		CriteriaQuery<Customer> criteria = this.getBuilder().createQuery(Customer.class);
		Root<Customer> root = criteria.from(Customer.class);

		List<Predicate> predicates = new ArrayList<>();

		predicates.add(this.getBuilder().equal(root.get("userName"), username));
		predicates.add(this.getBuilder().equal(root.get("password"), password));

		criteria.select(root).where(predicates.toArray(new Predicate[] {}));
		return this.getSession().createQuery(criteria).uniqueResult();
	}

	@Override
	public Customer findByUserName(String username) {
		CriteriaQuery<Customer> criteria = this.getBuilder().createQuery(Customer.class);
		Root<Customer> root = criteria.from(Customer.class);

		List<Predicate> predicates = new ArrayList<>();

		predicates.add(this.getBuilder().equal(root.get("user_name"), username));

		criteria.select(root).where(predicates.toArray(new Predicate[] {}));
		return this.getSession().createQuery(criteria).uniqueResult();
	}

	@Override
	public void createWork(EmployeeWorking work) {
		this.getSession().save(work);

	}

	@Override
	public List<EmployeeWorking> findAllJob() {
		CriteriaQuery<EmployeeWorking> criteria = this.getBuilder().createQuery(EmployeeWorking.class);
		Root<EmployeeWorking> root = criteria.from(EmployeeWorking.class);

		List<Predicate> predicates = new ArrayList<>();
		predicates.add(this.getBuilder().notEqual(root.get("status"), 3));

		criteria.select(root).where(predicates.toArray(new Predicate[] {}))
				.orderBy(this.getBuilder().desc(root.get("id")));
		return this.getSession().createQuery(criteria).getResultList();
	}

	@Override
	public EmployeeWorking findJobById(int id) {
		// TODO Auto-generated method stub
		return this.getSession().find(EmployeeWorking.class, id);
	}

	@Override
	public void updateWork(EmployeeWorking work) {
		this.getSession().update(work);

	}

	public List<EmployeeWorking> findAllJobDone() {
		List<Object[]> resultList = this.getSession().createQuery(
				"SELECT id, restaurantId,restaurantBrandId,branchId,jobName,employeeId,employeeName,status,jobAssignor,description,taskLevel FROM EmployeeWorking ew WHERE ew.status = 3 AND date_format(ew.created_at ,'%Y-%m-%d') =  date_format(now() ,'%Y-%m-%d')")
				.getResultList();
		List<EmployeeWorking> employeeWorkingList = new ArrayList<>();
		for (Object[] row : resultList) {
			EmployeeWorking employeeWorking = new EmployeeWorking();
			employeeWorking.setId((Integer) row[0]);
			employeeWorking.setRestaurantId((Integer) row[1]);
			employeeWorking.setRestaurantBrandId((Integer) row[2]);
			employeeWorking.setBranchId((Integer) row[3]);
			employeeWorking.setJobName((String) row[4]);
			employeeWorking.setEmployeeId((Integer) row[5]);
			employeeWorking.setEmployeeName((String) row[6]);
			employeeWorking.setStatus((Integer) row[7]);
			employeeWorking.setJobAssignor((String) row[8]);
			employeeWorking.setDescription((String) row[9]);
			employeeWorking.setTaskLevel((Integer) row[10]);
			employeeWorkingList.add(employeeWorking);
		}
		return employeeWorkingList;
	}

	@Override
	public List<EmployeeWorking> findAllJobByStatus(int status, int id) {
		List<Object[]> resultList = this.getSession().createQuery(
				"SELECT ew.id, ew.restaurantId, ew.restaurantBrandId, ew.branchId, ew.jobName, ew.employeeId, ew.employeeName, ew.status, ew.jobAssignor, ew.description, ew.taskLevel "
						+ "FROM EmployeeWorking ew " + "WHERE ew.status = :status " + "AND ew.employeeId = :id "
						+ "AND date_format(ew.created_at, '%Y-%m-%d') = date_format(now(), '%Y-%m-%d')")
				.setParameter("status", status).setParameter("id", id).getResultList();

		List<EmployeeWorking> employeeWorkingList = new ArrayList<>();
		for (Object[] row : resultList) {
			EmployeeWorking employeeWorking = new EmployeeWorking();
			employeeWorking.setId((Integer) row[0]);
			employeeWorking.setRestaurantId((Integer) row[1]);
			employeeWorking.setRestaurantBrandId((Integer) row[2]);
			employeeWorking.setBranchId((Integer) row[3]);
			employeeWorking.setJobName((String) row[4]);
			employeeWorking.setEmployeeId((Integer) row[5]);
			employeeWorking.setEmployeeName((String) row[6]);
			employeeWorking.setStatus((Integer) row[7]);
			employeeWorking.setJobAssignor((String) row[8]);
			employeeWorking.setDescription((String) row[9]);
			employeeWorking.setTaskLevel((Integer) row[10]);
			employeeWorkingList.add(employeeWorking);
		}
		return employeeWorkingList;
	}

}
