package interfaces;

import java.lang.*;

import entity.*;

public interface ILoanListRepo
{
	public void insertInDB(LoanList L);
	public void deleteFromDB(String loanId);
	public void updateInDB(LoanList l);
	public LoanList searchLoanList(String loanId);
	public String[][] getAllLoanList();
}