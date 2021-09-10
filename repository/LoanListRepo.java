package repository;

import java.lang.*;
import java.util.ArrayList;
import entity.*;
import interfaces.*;

public class LoanListRepo implements ILoanListRepo
{
	DatabaseConnection dbc;
	
	public LoanListRepo()
	{
		dbc = new DatabaseConnection();
	}
	
	public void insertInDB(LoanList l)
	{
		String query = "INSERT INTO `loanlist` (`loanId`, `bookId`, `cstId`, `returnDate`) VALUES ('"+l.getLoanId()+"', '"+l.getBookId()+"', '"+l.getCstId()+"', '"+l.getReturnDate()+"');";
		try
		{
			dbc.openConnection();
			dbc.st.execute(query);
			dbc.closeConnection();
		}
		catch(Exception ex){System.out.println(ex.getMessage());}
	}
	public void deleteFromDB(String loanId)
	{
		String query = "DELETE from loanlist WHERE loanId='"+loanId+"';";
		try
		{
			dbc.openConnection();
			dbc.st.execute(query);
			dbc.closeConnection();
		}
		catch(Exception e){System.out.println(e.getMessage());}
	}
	public void updateInDB(LoanList l)
	{
		String query = "UPDATE loanlist SET bookId='"+l.getBookId()+"', cstId = '"+l.getCstId()+"', returnDate = "+l.getReturnDate()+" WHERE loanId='"+l.getLoanId()+"'";
		
		try
		{
			dbc.openConnection();
			dbc.st.executeUpdate(query);
			dbc.closeConnection();
		}
		catch(Exception ex){System.out.println(ex.getMessage());}
	}
	public LoanList searchLoanList(String loanId)
	{
		LoanList l = null;
		String query = "SELECT `bookId`, `cstId`, `returnDate` FROM `loanlist` WHERE `loanId`='"+loanId+"';";     
		try
		{
			System.out.println(query);
			dbc.openConnection();
			dbc.result = dbc.st.executeQuery(query);
		
			while(dbc.result.next())
			{
				
				String bookId = dbc.result.getString("bookId");
				String cstId = dbc.result.getString("cstId");
				String returnDate = dbc.result.getString("returnDate");
				
				l = new LoanList();
				l.setLoanId(loanId);
				l.setBookId(bookId);
				l.setCstId(cstId);
				l.setReturnDate(returnDate);
			}
			
		}
		catch(Exception ex){System.out.println(ex.getMessage());}
		dbc.closeConnection();
		return l;
	}
	public String[][] getAllLoanList()
	{
		ArrayList<LoanList> ar = new ArrayList<LoanList>();
		String query = "SELECT * FROM loanlist;";  
		
		try
		{
			dbc.openConnection();
			dbc.result = dbc.st.executeQuery(query);
			
		
			while(dbc.result.next())
			{
				String loanId = dbc.result.getString("loanId");
				String bookId = dbc.result.getString("bookId");
				String cstId = dbc.result.getString("cstId");
				String returnDate = dbc.result.getString("returnDate");
				
				LoanList l = new LoanList(loanId,bookId,cstId,returnDate);
				ar.add(l);
			}
		}
		catch(Exception e){System.out.println(e.getMessage());}
		dbc.closeConnection();
		
		
		Object obj[] = ar.toArray();
		String data[][] = new String [ar.size()][4];
		
		for(int i=0; i<obj.length; i++)
		{
			data[i][0] = ((LoanList)obj[i]).getLoanId();
			data[i][1] = ((LoanList)obj[i]).getBookId();
			data[i][2] = ((LoanList)obj[i]).getCstId();
			data[i][3] = (((LoanList)obj[i]).getReturnDate())+"";
		}
		return data;
	}
}




