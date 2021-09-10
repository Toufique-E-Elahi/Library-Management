package interfaces;

import java.lang.*;

import entity.*;

public interface ICustomerRepo
{
	public void insertInDB(Customer c);
	public void deleteFromDB(String cstId);
	public void updateInDB(Customer c);
	public Customer searchCustomer(String cstId);
	//public String[][] getAllCustomer();
}