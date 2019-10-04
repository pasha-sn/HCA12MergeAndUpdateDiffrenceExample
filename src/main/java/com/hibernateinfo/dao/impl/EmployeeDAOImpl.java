package com.hibernateinfo.dao.impl;

import org.hibernate.Session;

import com.hibernateinfo.dao.EmployeeDAO;
import com.hibernateinfo.entities.Employee;
import com.hibernateinfo.util.HibernateUtil;

/**
 * @author Pasha
 * Remember the golden rule: readable code is often faster code. 
 * Produce readable code first and only change it if it proves to be too slow.
 */
public class EmployeeDAOImpl implements EmployeeDAO {

	@Override
	public void addEmployee(Employee employee) 
	{
		try(Session session = HibernateUtil.getSessionFactory().openSession()) 
		{
			session.beginTransaction();
			Integer id = (Integer) session.save(employee);
			System.out.println("Employee is created with Id:: " + id);
			session.getTransaction().commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Employee fetchEmployeeById(int employeeId) 
	{
		Employee employee = null;
		try(Session session = HibernateUtil.getSessionFactory().openSession()) 
		{
			employee = session.get(Employee.class, employeeId);
			if(employee != null)
			{
				return employee;
			}else
			{
				System.out.println("Employee Does Not Exist With Provided Id!");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void updateEmployeeById(int employeeId, Double newSal) 
	{
		try(Session session = HibernateUtil.getSessionFactory().openSession()) 
		{
			Employee employee = session.get(Employee.class, employeeId);
			if(employee != null)
			{	
				employee.setSalary(newSal);
				session.beginTransaction();					
				session.save(employee);
				session.getTransaction().commit();
			}else
			{
				System.out.println("Employee Does Not Exist With Provided Id!");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteEmployeeById(Integer employeeId) 
	{
		try(Session session = HibernateUtil.getSessionFactory().openSession()) 
		{
			Employee employee = session.get(Employee.class, employeeId);
			if(employee != null)
			{
				session.beginTransaction();
				session.delete(employee);
				session.getTransaction().commit();
			}else
			{
				System.out.println("Employee Does Not Exist With Provided Id!");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
