package com.hibernateinfo.client;

import org.hibernate.Session;

import com.hibernateinfo.entities.Employee;
import com.hibernateinfo.util.HibernateUtil;

/**
 * @author Pasha
 * Remember the golden rule: readable code is often faster code. 
 * Produce readable code first and only change it if it proves to be too slow.
 */
public class ClientTest2DiffrenceBetweenMergeAndUpdate {

	public static void main(String[] args) 
	{
		Employee employee1 = null;
		Employee employee2 = null;
		try(Session session = HibernateUtil.getSessionFactory().openSession()) 
		{			
			employee1 = session.get(Employee.class, 1);
			System.out.println(employee1);
			System.out.println("***********************************************");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//session object has been closed in try catch block with session as resource
		//employee1 is now a detached object
		employee1.setSalary(31001.00);
		
		try(Session session = HibernateUtil.getSessionFactory().openSession()) 
		{
			session.beginTransaction();
			employee2 = session.get(Employee.class, 1);
			
			//update(employee1) will throw an exception
			//org.hibernate.NonUniqueObjectException: A different object with the same 
			//identifier value was already associated with the session : [com.hibernateionfo.entities.Employee#1]
			//session.update(employee1);			
			
			//Update: if you are sure that the session does not contains an already persistent 
			//instance with the same identifier,then use update to save the data in hibernate.
			//Merge: if you want to save your modifications at any time with out knowing about 
			//the state of an session, then use merge() in hibernate.
			//update vs merge method in hibernate?
			//https://stackoverflow.com/questions/49604134/update-vs-merge-method-in-hibernate
			session.merge(employee1); 
			
			session.getTransaction().commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("***********************************************");
		System.out.println(employee2);
	}	

}
/*
<property name="hibernate.hbm2ddl.auto">update</property>
fetch=FetchType.EAGER Hibernate will hit database just once!
Hibernate: 
    select
        employee0_.employee_id as employee_id1_2_0_,
        employee0_.date_of_join as date_of_join2_2_0_,
        employee0_.email as email3_2_0_,
        employee0_.employee_name as employee_name4_2_0_,
        employee0_.salary as salary5_2_0_,
        addresslis1_.EMPLOYEE_ID as EMPLOYEE_ID1_1_1_,
        address2_.address_id as ADDRESS_ID2_1_1_,
        address2_.address_id as address_id1_0_2_,
        address2_.city_name as city_name2_0_2_,
        address2_.postal_code as postal_code3_0_2_,
        address2_.state_name as state_name4_0_2_,
        address2_.street_name as street_name5_0_2_ 
    from
        employee_table employee0_ 
    left outer join
        EMPLOYEE_ADDRESS_TABLE addresslis1_ 
            on employee0_.employee_id=addresslis1_.EMPLOYEE_ID 
    left outer join
        address_table address2_ 
            on addresslis1_.ADDRESS_ID=address2_.address_id 
    where
        employee0_.employee_id=?
Employee [employeeId=1, employeeName=Pasha Sadi, email=pasha.sn@gmail.com, doj=2011-11-11 11:11:11.111, addressList=[], salary=75000.0]
***********************************************
Hibernate: 
    select
        employee0_.employee_id as employee_id1_2_0_,
        employee0_.date_of_join as date_of_join2_2_0_,
        employee0_.email as email3_2_0_,
        employee0_.employee_name as employee_name4_2_0_,
        employee0_.salary as salary5_2_0_,
        addresslis1_.EMPLOYEE_ID as EMPLOYEE_ID1_1_1_,
        address2_.address_id as ADDRESS_ID2_1_1_,
        address2_.address_id as address_id1_0_2_,
        address2_.city_name as city_name2_0_2_,
        address2_.postal_code as postal_code3_0_2_,
        address2_.state_name as state_name4_0_2_,
        address2_.street_name as street_name5_0_2_ 
    from
        employee_table employee0_ 
    left outer join
        EMPLOYEE_ADDRESS_TABLE addresslis1_ 
            on employee0_.employee_id=addresslis1_.EMPLOYEE_ID 
    left outer join
        address_table address2_ 
            on addresslis1_.ADDRESS_ID=address2_.address_id 
    where
        employee0_.employee_id=?
***********************************************
Employee [employeeId=1, employeeName=Pasha Sadi, email=pasha.sn@gmail.com, doj=2011-11-11 11:11:11.111, addressList=[], salary=31001.0]


*/