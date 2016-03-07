package mybooks.root;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.text.html.*;  
import javax.swing.text.html.parser.*; 

import javax.xml.datatype.DatatypeConfigurationException;

import mybooks.root.model.Book;

public class DBops {
	
	private Connection c;
	
	public DBops(){
		 try {
		    	c = DBconn.getConnection(); 
			    } catch(SQLException e){
			    	System.err.println("Error: "+e.getMessage());
			    	System.exit(1);
			    } catch (ClassNotFoundException e){
			    	System.err.println("Error: "+e.getMessage());
			    	System.exit(2);
			    }
		
	}
	
	public void create(String title, String author, String year, String html) {
		System.out.println("DB: " + title + " " + author + " " + year);
		Book b = new Book(0, title, author, year, html, null, null);
		b.setYear(year);
		
		String SQL = "INSERT INTO books (title, author, year, html) VALUES(?,?,?,?)";
		try {
		  PreparedStatement ps = c.prepareStatement(SQL);
		  ps.setString(1, b.getTitle());
		  ps.setString(2, b.getAuthor());
		  ps.setString(3, b.getYear());
		  ps.setString(4, b.getHtml());
		  ps.executeUpdate();
		} catch(SQLException e){
	    	System.err.println("Create error: "+e.getMessage());
	    	System.exit(1);
	    }	
	}
	
	public void update(Book book) {
		String SQL = "UPDATE books SET last_read = NOW(), page = ? WHERE id = " + book.getId();
		try {
		  PreparedStatement ps = c.prepareStatement(SQL);
		  ps.setString(1, book.getPage());
		  ps.executeUpdate();
		} catch(SQLException e){
	    	System.err.println("Update error: "+e.getMessage());
	    	System.exit(1);
	    }	
	}
	
	public Book find(int id){ 
		Book ret = null;
		  String SQL = "SELECT * FROM books WHERE id = " + id;
		  try{		    	
			    ResultSet rs = c.createStatement().executeQuery(SQL);
			    while(rs.next()){
			    	try {
						ret = new Book(rs.getInt("id"), rs.getString("title"),
			    			       rs.getString("author"),
			    			       Integer.toString(javax.xml.datatype.DatatypeFactory.newInstance().newXMLGregorianCalendar(rs.getString("year")).getYear()), rs.getString("html"),
			    			       rs.getString("last_read"), rs.getString("page"));
					} catch (DatatypeConfigurationException e) {
						e.printStackTrace();
					}

			    	
			    }
		  } catch(SQLException e){
		    	System.err.println("Find error: "+e.getMessage());
		    	System.exit(1);
		  }
		  
		  return ret;
	}
	
	public List<Book> list() { 
		List<Book> ret = new ArrayList();
		String SQL = "SELECT * FROM books";
		try{		    	
		   ResultSet rs = c.createStatement().executeQuery(SQL);
		   while(rs.next()){
			   try {
					ret.add(new Book(rs.getInt("id"), rs.getString("title"),
		    			       rs.getString("author"),
		    			       Integer.toString(javax.xml.datatype.DatatypeFactory.newInstance().newXMLGregorianCalendar(rs.getString("year")).getYear()), rs.getString("html"),
		    			       rs.getString("last_read"), rs.getString("page")));
				} catch (DatatypeConfigurationException e) {
					e.printStackTrace();
				}
		   }
		} catch(SQLException e){
		   	System.err.println("Find error: "+e.getMessage());
		   	System.exit(1);
		}
		 
		return ret;
	}
}
