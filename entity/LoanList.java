package entity;

import java.lang.*;

public class LoanList
{
	private String loanId;
	private String bookId;
	private String cstId;
	private String returnDate;
	
	
	public LoanList(){}
	public LoanList(String loanId, String bookId, String cstId, String returnDate )
	{
		this.loanId = loanId;
		this.bookId = bookId;
		this.cstId = cstId;
		this.returnDate = returnDate;
		
	}
	
	public void setLoanId(String loanId){this.loanId = loanId;}
	public void setBookId(String bookId){this.bookId = bookId;}
	public void setCstId(String cstId){this.cstId = cstId;}
	public void setReturnDate(String returnDate){this.returnDate = returnDate;}
	
	
	public String getLoanId(){return loanId;}
	public String getBookId(){return bookId;}
	public String getCstId(){return cstId;}
	public String getReturnDate(){return returnDate;}
	
}