package repository;

import java.lang.*;
//import java.util.ArrayList;
import entity.*;
import interfaces.*;

public class CustomerRepo implements ICustomerRepo
{
	DatabaseConnection dbc;
	
	public CustomerRepo()
	{
		dbc = new DatabaseConnection();
	}
	
	public void insertInDB(Customer c)
	{
		String query = "INSERT INTO `customer` (`cstId`, `name`, `address`, `email`) VALUES ('"+c.getCstId()+"', '"+c.getName()+"', '"+c.getAddress()+"', '"+c.getEmail()+"');";
		try
		{
			dbc.openConnection();
			dbc.st.execute(query);
			dbc.closeConnection();
		}
		catch(Exception ex){System.out.println(ex.getMessage());}
	}
	public void deleteFromDB(String cstId)
	{
		String query = "DELETE from customer WHERE cstId='"+cstId+"';";
		try
		{
			dbc.openConnection();
			dbc.st.execute(query);
			dbc.closeConnection();
		}
		catch(Exception e){System.out.println(e.getMessage());}
	}
	public void updateInDB(Customer c)
	{
		String query = "UPDATE `customer` SET `name` = '"+c.getName()+"', `address` = '"+c.getAddress()+"', `email` = '"+c.getEmail()+"' WHERE `customer`.`cstId` = '"+c.getCstId()+"'";
		
		try
		{
			dbc.openConnection();
			dbc.st.executeUpdate(query);
			dbc.closeConnection();
		}
		catch(Exception ex){System.out.println(ex.getMessage());}
	}
	public Customer searchCustomer(String cstId)
	{
		Customer c = null;
		String query = "SELECT `name`, `address`, `email` FROM `customer` WHERE `cstId`='"+cstId+"';";     
		try
		{
			System.out.println(query);
			dbc.openConnection();
			dbc.result = dbc.st.executeQuery(query);
		
			while(dbc.result.next())
			{
				
				String name = dbc.result.getString("name");
				String address = dbc.result.getString("address");
				String email = dbc.result.getString("email");
				
				c = new Customer();
				c.setCstId(cstId);
				c.setName(name);
				c.setAddress(address);
				c.setEmail(email);
			}
			
		}
		catch(Exception ex){System.out.println(ex.getMessage());}
		dbc.closeConnection();
		return c;
	}
	/*public String[][] getAllEmployee()
	{
		ArrayList<Employee> ar = new ArrayList<Employee>();
		String query = "SELECT * FROM employees;";  
		
		try
		{
			dbc.openConnection();
			dbc.result = dbc.st.executeQuery(query);
			
		
			while(dbc.result.next())
			{
				String empId = dbc.result.getString("empId");
				String name = dbc.result.getString("employeeName");
				String designation = dbc.result.getString("designation");
				double salary = dbc.result.getDouble("salary");
				
				Employee e = new Employee(empId,name,designation,salary);
				ar.add(e);
			}
		}
		catch(Exception e){System.out.println(e.getMessage());}
		dbc.closeConnection();
		
		
		Object obj[] = ar.toArray();
		String data[][] = new String [ar.size()][4];
		
		for(int i=0; i<obj.length; i++)
		{
			data[i][0] = ((Employee)obj[i]).getEmpId();
			data[i][1] = ((Employee)obj[i]).getName();
			data[i][2] = ((Employee)obj[i]).getDesignation();
			data[i][3] = (((Employee)obj[i]).getSalary())+"";
		}
		return data;
	}*/
}




