package com.hibernateinfo.service.impl;

import com.hibernateinfo.dao.impl.EmployeeDAOImpl;
import com.hibernateinfo.entities.Employee;
import com.hibernateinfo.service.EmployeeService;

/**
 * @author Pasha
 * Remember the golden rule: readable code is often faster code. 
 * Produce readable code first and only change it if it proves to be too slow.
 */
public class EmployeeServiceImpl implements EmployeeService {

	@Override
	public void createEmployee(Employee employee) {
		new EmployeeDAOImpl().addEmployee(employee);
	}

	@Override
	public Employee getEmployeeById(int employeeId) {
		return new EmployeeDAOImpl().fetchEmployeeById(employeeId);
	}

	@Override
	public void updateEmployeeById(int employeeId, Double newSal) {
		new EmployeeDAOImpl().updateEmployeeById(employeeId, newSal);
	}

	@Override
	public void deleteEmployeeById(Integer employeeId) {
		new EmployeeDAOImpl().deleteEmployeeById(employeeId);
	}
}
