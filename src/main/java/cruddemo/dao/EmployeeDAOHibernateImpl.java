package com.springboot.cruddemo.dao;

import java.util.List;


import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springboot.cruddemo.entiry.Employee;

@Repository
public class EmployeeDAOHibernateImpl implements EmployeeDAO {
	
	//define field for entityManager
	private EntityManager entityManager;
	
	//constructor injection
	@Autowired
	public EmployeeDAOHibernateImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}
	@Override
	public List<Employee> findAll() {
		//get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		//create a query
		Query<Employee> theQuery = currentSession.createQuery("from Employee", Employee.class);
		//excecute query and get result list
		List<Employee> employees=theQuery.getResultList();
		//return the list
		return employees;
	}
	@Override	
	public Employee findById(int theId) {
		//get the current hibernate session
				Session currentSession = entityManager.unwrap(Session.class);
				Employee theEmployee = currentSession.get(Employee.class, theId);
		
		return theEmployee;
	}
	@Override
	public void save(Employee theEmployee) {
		// get the currentSession hibernate
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.saveOrUpdate(theEmployee);
		
	}
	@Override
	public void deleteById(int theId) {
		// get the currentSession hibernate
				Session currentSession = entityManager.unwrap(Session.class);
		//delete the employee by id
				Query theQuery = currentSession.createQuery("delete from Employee where id=:employeeId");	
				theQuery.setParameter("employeeId", theId);
				theQuery.executeUpdate();
		
	}
	

}
