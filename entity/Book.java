package entity;

import java.lang.*;

public class Book
{
	private String bookId;
	private String name;
	private String author;
	private int quantity;
	
	public Book(){}
	public Book(String bookId, String name, String author, int quantity)
	{
		this.bookId = bookId;
		this.name = name;
		this.author = author;
		this.quantity = quantity;
	}
	
	public void setBookId(String bookId){this.bookId = bookId;}
	public void setName(String name){this.name = name;}
	public void setAuthor(String author){this.author = author;}
	public void setQuantity(int quantity){this.quantity = quantity;}
	
	public String getBookId(){return bookId;}
	public String getName(){return name;}
	public String getAuthor(){return author;}
	public int getQuantity(){return quantity;}
}