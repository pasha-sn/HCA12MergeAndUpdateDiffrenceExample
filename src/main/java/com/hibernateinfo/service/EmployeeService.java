package com.hibernateinfo.service;

import com.hibernateinfo.entities.Employee;

/**
 * @author Pasha
 * Remember the golden rule: readable code is often faster code. 
 * Produce readable code first and only change it if it proves to be too slow.
 */
public interface EmployeeService 
{
	public abstract void createEmployee(Employee employee);
	public abstract Employee getEmployeeById(int employeeId);
	public abstract void updateEmployeeById(int employeeId, Double newSal);
	public abstract void deleteEmployeeById(Integer employeeId);
}
