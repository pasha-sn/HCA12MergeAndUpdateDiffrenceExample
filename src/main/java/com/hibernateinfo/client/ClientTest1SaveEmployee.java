package com.hibernateinfo.client;

import java.util.Date;

import org.hibernate.Session;

import com.hibernateinfo.entities.Employee;
import com.hibernateinfo.util.HibernateUtil;

/**
 * @author Pasha
 * Remember the golden rule: readable code is often faster code. 
 * Produce readable code first and only change it if it proves to be too slow.
 */
public class ClientTest1SaveEmployee {

	public static void main(String[] args) 
	{
		
		try(Session session = HibernateUtil.getSessionFactory().openSession()) 
		{
			session.beginTransaction();
			Employee employee= new Employee();
			employee.setEmployeeName("Pasha Sadi");
			employee.setEmail("pasha.sn@gmail.com");
			employee.setSalary(75000d);
			employee.setDoj(new Date());
			
			session.save(employee);
			
			session.getTransaction().commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}			
}
/*
<property name="hibernate.hbm2ddl.auto">create</property>
Hibernate: 
    
    drop table address_table cascade constraints
Hibernate: 
    
    drop table EMPLOYEE_ADDRESS_TABLE cascade constraints
Hibernate: 
    
    drop table employee_table cascade constraints
Hibernate: 
    
    drop sequence hibernate_sequence
Hibernate: create sequence hibernate_sequence start with 1 increment by  1
Hibernate: 
    
    create table address_table (
       address_id number(10,0) not null,
        city_name varchar2(50 char),
        postal_code number(19,0),
        state_name varchar2(255 char),
        street_name varchar2(50 char),
        primary key (address_id)
    )
Hibernate: 
    
    create table EMPLOYEE_ADDRESS_TABLE (
       EMPLOYEE_ID number(10,0) not null,
        ADDRESS_ID number(10,0) not null
    )
Hibernate: 
    
    create table employee_table (
       employee_id number(10,0) not null,
        date_of_join timestamp,
        email varchar2(255 char),
        employee_name varchar2(100 char) not null,
        salary double precision,
        primary key (employee_id)
    )
Hibernate: 
    
    alter table employee_table 
       add constraint UK_2casspobvavvi9s23312f9mhm unique (email)
Hibernate: 
    
    alter table EMPLOYEE_ADDRESS_TABLE 
       add constraint FKphgr2qpjqlk6xhi7px1t2b7rg 
       foreign key (ADDRESS_ID) 
       references address_table
Hibernate: 
    
    alter table EMPLOYEE_ADDRESS_TABLE 
       add constraint FKfpb7bjnuajgo5jr0bpj6s025f 
       foreign key (EMPLOYEE_ID) 
       references employee_table
Hibernate: 
    select
        hibernate_sequence.nextval 
    from
        dual
Hibernate: 
    insert 
    into
        employee_table
        (date_of_join, email, employee_name, salary, employee_id) 
    values
        (?, ?, ?, ?, ?)
*/