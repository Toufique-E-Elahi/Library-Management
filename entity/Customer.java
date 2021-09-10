package entity;

import java.lang.*;

public class Customer
{
	private String cstId;
	private String name;
	private String address;
	private String email;
	
	public Customer(){}
	public Customer(String cstId, String name, String address, String email)
	{
		this.cstId = cstId;
		this.name = name;
		this.address = address;
		this.email = email;
	}
	
	public void setCstId(String cstId){this.cstId = cstId;}
	public void setName(String name){this.name = name;}
	public void setAddress(String address){this.address = address;}
	public void setEmail(String email){this.email = email;}
	
	public String getCstId(){return cstId;}
	public String getName(){return name;}
	public String getAddress(){return address;}
	public String getEmail(){return email;}
}