package com.hibernateinfo.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

/**
 * @author Pasha
 * Remember the golden rule: readable code is often faster code. 
 * Produce readable code first and only change it if it proves to be too slow.
 */
@Entity
@Table(name="address_table")
@DynamicUpdate
public class Address 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="address_id")
	private Integer addressId;
	
	@Column(name = "street_name",length=50)
    private String street;
	
    @Column(name = "city_name",length=50)
    private String city;
    
    @Column(name = "state_name")
    private String state;
    
    @Column(name = "postal_code")
    private Long postalcode;    

    //Can someone explain mappedBy in Hibernate?
  	//https://stackoverflow.com/questions/9108224/can-someone-explain-mappedby-in-hibernate  	
    @ManyToMany(mappedBy="addressList")
    private List<Employee> employeeList = new ArrayList<>();

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Long getPostalcode() {
		return postalcode;
	}

	public void setPostalcode(Long pincode) {
		this.postalcode = pincode;
	}
	
	public List<Employee> getEmployeeList() {
		return employeeList;
	}

	public void setEmployeeList(List<Employee> employeeList) {
		this.employeeList = employeeList;
	}

	@Override
	public String toString() {
		return "Address [addressId=" + addressId + ", street=" + street + ", city=" + city + ", state=" + state
				+ ", postalcode=" + postalcode + "]";
	}	    
}
