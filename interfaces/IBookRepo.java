package interfaces;

import java.lang.*;

import entity.*;

public interface IBookRepo
{
	public void insertInDB(Book b);
	public void deleteFromDB(String bookId);
	public void updateInDB(Book b);
	public Book searchBook(String bookId);
	//public String[][] getAllBook();
}