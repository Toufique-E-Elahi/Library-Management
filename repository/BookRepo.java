package repository;

import java.lang.*;
import java.util.ArrayList;
import entity.*;
import interfaces.*;

public class BookRepo implements IBookRepo
{
	DatabaseConnection dbc;
	
	public BookRepo()
	{
		dbc = new DatabaseConnection();
	}
	
	public void insertInDB(Book b)
	{
		String query = "INSERT INTO book VALUES ('"+b.getBookId()+"','"+b.getName()+"','"+b.getAuthor()+"',"+b.getQuantity()+");";
		try
		{
			dbc.openConnection();
			dbc.st.execute(query);
			dbc.closeConnection();
		}
		catch(Exception ex){System.out.println(ex.getMessage());}
	}
	public void deleteFromDB(String bookId)
	{
		String query = "DELETE from book WHERE bookId='"+bookId+"';";
		try
		{
			dbc.openConnection();
			dbc.st.execute(query);
			dbc.closeConnection();
		}
		catch(Exception e){System.out.println(e.getMessage());}
	}
	public void updateInDB(Book b)
	{
		String query = "UPDATE book SET name='"+b.getName()+"', author = '"+b.getAuthor()+"', quantity = "+b.getQuantity()+" WHERE bookId='"+b.getBookId()+"'";
		
		try
		{
			dbc.openConnection();
			dbc.st.executeUpdate(query);
			dbc.closeConnection();
		}
		catch(Exception ex){System.out.println(ex.getMessage());}
	}
	public Book searchBook(String bookId)
	{
		Book b = null;
		String query = "SELECT `name`, `author`, `quantity` FROM `book` WHERE `bookId`='"+bookId+"';";     
		try
		{
			System.out.println(query);
			dbc.openConnection();
			dbc.result = dbc.st.executeQuery(query);
		
			while(dbc.result.next())
			{
				
				String name = dbc.result.getString("name");
				String author = dbc.result.getString("author");
				int quantity = dbc.result.getInt("quantity");
				
				b = new Book();
				b.setBookId(bookId);
				b.setName(name);
				b.setAuthor(author);
				b.setQuantity(quantity);
			}
			
		}
		catch(Exception ex){System.out.println(ex.getMessage());}
		dbc.closeConnection();
		return b;
	}
	public String[][] getAllBooks()
	{
		ArrayList<Book> ar = new ArrayList<Book>();
		String query = "SELECT * FROM book;";  
		
		try
		{
			dbc.openConnection();
			dbc.result = dbc.st.executeQuery(query);
			
		
			while(dbc.result.next())
			{
				String bookId = dbc.result.getString("bookID");
				String name = dbc.result.getString("name");
				String author = dbc.result.getString("author");
				int quantity = dbc.result.getInt("quantity");
				
				Book b = new Book(bookId,name,author,quantity);
				ar.add(b);
			}
		}
		catch(Exception e){System.out.println(e.getMessage());}
		dbc.closeConnection();
		
		
		Object obj[] = ar.toArray();
		String data[][] = new String [ar.size()][4];
		
		for(int i=0; i<obj.length; i++)
		{
			data[i][0] = ((Book)obj[i]).getBookId();
			data[i][1] = ((Book)obj[i]).getName();
			data[i][2] = ((Book)obj[i]).getAuthor();
			data[i][3] = (((Book)obj[i]).getQuantity())+"";
		}
		
		return data;
	}
}




