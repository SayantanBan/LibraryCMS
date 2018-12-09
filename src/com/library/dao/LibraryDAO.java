package com.library.dao;


import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.io.InputStream;
import java.sql.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.library.bean.BookBean;
import com.library.bean.IssueBean;
import com.library.bean.MemberBean;
import com.library.bean.ShowHistoryBean;
import com.library.interfaces.LibraryDAOI;
enum Role
{
	Librarian,User
}

public class LibraryDAO implements LibraryDAOI
{
	static PreparedStatement statement = null;
	static ResultSet resultSet = null;
	static Connection con =ConnectionManager.getDBConnection().getConnection();
	static PreparedStatement statement1 = null;
	static ResultSet resultSet1 = null;
	public String add(BookBean bean) 
	{
		 String book_name = bean.getBookName();
		 
		 List<String> author= bean.getAuthor();
		 
		 String publisher=bean.getPublisher();
		 String category=bean.getCategory();
		 InputStream  image=  bean.getCover();
		 int quantity=bean.getQuantity();
		
		 String book_id="";
		 
		 String status="";

		 String check=checkBook(book_name);
		 if (check.equals("error"))
		 {
			 status="error";
		 }
		 
		 else if (check.equals("Doesn't Exists"))
		 { 
			 try
			 {
				 con.setAutoCommit(false);

				 String category_id="";
				 String publisher_id="";
				 
				 statement =con.prepareStatement("select publisher_id from publisher where publisher_name=?");
				 statement.setString(1, publisher);
				 resultSet = statement.executeQuery();
				 
				 if (resultSet.next())
				 {
					 publisher_id=resultSet.getString(1);
				 }
				 else
				 {
					 statement =con.prepareStatement("select publisher_id from  publisher order by publisher_id desc limit 1 ");
					 resultSet = statement.executeQuery();
					 if (resultSet.next())
					 {
						 String lastPublisherId=resultSet.getString(1);
						 String temp="";
						 
						 for (int i=1;i<lastPublisherId.length();i++)
						 {
							 temp=temp+lastPublisherId.charAt(i);
						 }
		
						 int number=Integer.parseInt(temp);
						 number++;
						 
						 publisher_id="P";
						 publisher_id=publisher_id+Integer.toString(number);
					 }	 
					 else
					 {
						 publisher_id="P1";
					 }	 
					 statement =con.prepareStatement("insert into publisher values (?,?)");
					 statement.setString(1,publisher_id);
					 statement.setString(2,publisher);
					 statement.executeUpdate();
					 
				}
				 
				 
				 System.out.println(publisher_id);
				 
				statement =con.prepareStatement("select category_id from book_category where category_name=?");
				statement.setString(1, category);
				resultSet = statement.executeQuery();
				if (resultSet.next())
				{
					category_id=resultSet.getString(1);
				}
				else
				{
					statement =con.prepareStatement("select category_id from  book_category order by category_id desc limit 1 ");
					resultSet = statement.executeQuery();
					if (resultSet.next())
					{
						 String lastCategoryId=resultSet.getString(1);
						 String temp="";
					 
						 for (int i=1;i<lastCategoryId.length();i++)
						 {
							 temp=temp+lastCategoryId.charAt(i);
						 }

						 int number=Integer.parseInt(temp);
						 number++;
						 
						 category_id="C";
						 category_id=category_id+Integer.toString(number);
					}
					else
					{
						 category_id="C1";
					}	
					statement =con.prepareStatement("insert into book_category values (?,?)");
					statement.setString(1,category_id);
				 	statement.setString(2,category);
					statement.executeUpdate();
				}
				
				
				
				System.out.println(category_id);

				
				statement =con.prepareStatement("select book_id from  books order by book_id desc limit 1 ");
				resultSet = statement.executeQuery();
				if (resultSet.next())
				{
					 String lastBookId=resultSet.getString(1);
					 int bookIdNumber=Integer.parseInt(lastBookId);
					 bookIdNumber++;
					 book_id=book_id+Integer.toString(bookIdNumber);
				}
			 	else
				{
					book_id="1001";
				}
				statement =con.prepareStatement("insert into books values (?,?,?,?,?,?,?)");				
				statement.setString(1,book_id);
			 	statement.setString(2,book_name);
			 	statement.setString(3,publisher_id);
			 	statement.setBlob(4,image);
			 	statement.setInt(5, quantity);
			 	statement.setInt(6, 0);
			 	statement.setString(7, category_id);
				statement.executeUpdate();
				
				
				int len=author.size();
				int j=0;
				while (j<len)
				{
					 System.out.println(author.get(j));
					 String author_id="";
					 statement =con.prepareStatement("select author_id from author where author_name=?");
					 statement.setString(1,author.get(j));
					 resultSet = statement.executeQuery();
					 if (resultSet.next())
					 {
						 author_id=resultSet.getString(1);
					 }
					 else
					 {
						 statement =con.prepareStatement("select author_id from  author order by author_id desc limit 1 ");
						 resultSet = statement.executeQuery();
						 if (resultSet.next())
						 {
							 String lastAuthorId=resultSet.getString(1);
							 String temp="";
							 int len1=lastAuthorId.length();
							 for (int i=1;i<len1;i++)
							 {
								 temp=temp+lastAuthorId.charAt(i);
							 }
							 int authorIdNumber=Integer.parseInt(temp);
							 authorIdNumber++;
							 author_id="A";
							 author_id=author_id+Integer.toString(authorIdNumber);
						 }
						 else
						 {
							 author_id="A1";
						 }
						 
						 statement =con.prepareStatement("insert into author values (?,?)");
						 statement.setString(1,author_id);
						 statement.setString(2,author.get(j));
						 statement.executeUpdate();
					 }	 
					
						
					System.out.println(author_id); 
					
					 statement =con.prepareStatement("insert into book_author values(?,?)");
					 statement.setString(1, book_id);
					 statement.setString(2, author_id);
					 statement.executeUpdate();
			
					 j++;
				 	 

				}
				status="Added";
				con.commit();

			 }
			 catch(SQLException e)
			 {
				 System.out.println(e);
				 try
				 {
					 if(con!=null)
			            con.rollback();
			     }
				 catch(SQLException e2)
				 {
			         e2.printStackTrace();
			     }
			 }
			 finally
			 {
				 try
				 {
					 if (resultSet!=null)
					 {
						 resultSet.close();
					 }
					 if (statement!=null)
					 {
						 statement.close();
					 }
				 }
				 catch(SQLException e)
				 {
					 System.out.println(e);
					 status=null;
				 }
			 }
			 
		 
		 }
		 else 
		 {
			 status="Existing";
		 }
		 return status;
	 }			
	 
		

	
	
	public String checkBook(String book_name)
	{
		String status="Doesn't Exists" ;
		try
		{
			statement =con.prepareStatement("select * from books where book_name=?");
			statement.setString(1,book_name);
			resultSet = statement.executeQuery();
			if (resultSet.next())
			{
				status="Existing";
			}
		}
		catch(SQLException e)
		{
			System.out.println(e);
			status="error";
		}
		return status;
			
	}

	public List<String> getCategory()
	{
		 List<String> list=new ArrayList<String>();
		 try
		 {
			 statement =con.prepareStatement("select distinct category_name from book_category order by category_name asc");
			 resultSet = statement.executeQuery();
			
			 while (resultSet.next())
			 {
			   	 list.add(resultSet.getString(1));
			 }
		}
		 catch(SQLException e)
		 {
			 System.out.println(e);
		 }			
		 finally
		 {
			 try
			 {
				 if (resultSet!=null)
				 {
					 resultSet.close();
				 }
				 if (statement!=null)
				 {
					 statement.close();
				 }
			 }
			 catch(SQLException e)
			 {
				 System.out.println(e);
			 }
		 }
		return list;
	}
	
	
	public  List<String> bookList(int start,int total)
	{
		 List<String> list=new ArrayList<String>();
		 try
		 {
			 statement =con.prepareStatement("select book_name from books limit "+(start-1)+","+total+" ");
			 resultSet=statement.executeQuery();
			 
			 while(resultSet.next())
			 {
				 list.add(resultSet.getString(1));
			 }
			 
		 }
		 catch(SQLException e)
		 {
			 System.out.println(e);
			 list=null;
		 }
		 finally
		 {
			 try
			 {
				 if (resultSet!=null)
				 {
					 resultSet.close();
				 }
				 if (statement!=null)
				 {
					 statement.close();
				 }
			 }
			 catch(SQLException e)
			 {
				 System.out.println(e);
				 list=null;
			 }
		 }
		 return list;
	}
	public BookBean bookDetails(String book_name)
	{
		BookBean bean=new BookBean();
		String publisher_id="";
		String category_id="";
		String book_id="";
		
		 try
		 {
			 int available_quantity;
			 statement =con.prepareStatement("select * from books where book_name=?");
			 statement.setString(1, book_name);

			 resultSet=statement.executeQuery();
			 if(resultSet.next())
			 {

				 bean.setBookId(resultSet.getString("book_id"));
				 bean.setBookName(resultSet.getString("book_name"));
				

				 available_quantity=(resultSet.getInt("quantity"))-(resultSet.getInt("issued_quantity"));
				 bean.setQuantity(available_quantity);
				 publisher_id=resultSet.getString("publisher_id");
				 category_id=resultSet.getString("category_id");
				 book_id=resultSet.getString("book_id");
				
				 statement =con.prepareStatement("select * from publisher where publisher_id=?");
				 statement.setString(1, publisher_id);

				 resultSet=statement.executeQuery();
				 if(resultSet.next())
				 {
					 bean.setPublisher(resultSet.getString("publisher_name"));
				 }
				 
				 
				 statement =con.prepareStatement("select * from book_category where category_id=?");
				 statement.setString(1, category_id);

				 resultSet=statement.executeQuery();
				 if(resultSet.next())
				 {
					 bean.setCategory(resultSet.getString("category_name"));
				 }
				 List<String> authors=new ArrayList<String>();

				 statement =con.prepareStatement("select * from book_author where book_id=?");
				 statement.setString(1, book_id);
				 resultSet=statement.executeQuery();
				 while(resultSet.next())
				 {
					 String author_id=resultSet.getString(2);
					 statement1 =con.prepareStatement("select author_name from author where author_id=?");
					 statement1.setString(1, author_id);
					 resultSet1=statement1.executeQuery();
					 if (resultSet1.next())
					 {
						 authors.add(resultSet1.getString(1));
					 }
				 }
				 bean.setAuthor(authors); 
			 }
		 }
		 catch(Exception e)
		 {
			 System.out.println(e);
			 bean=null;
		 }
		 finally
		 {
			 try
			 {
				 if (resultSet!=null)
				 {
					 resultSet.close();
				 }
				 if (statement!=null)
				 {
					 statement.close();
				 }

				 if (resultSet1!=null)
				 {
					 resultSet1.close();
				 }
				 if (statement1!=null)
				 {
					 statement1.close();
				 }
			 }
			 catch(SQLException e)
			 {
				 System.out.println(e);
				 bean=null;
			 }
		 }
		return bean;
	}
	
	public  BookBean displayImage(String book_name)
	{
		 BookBean bean=new BookBean();
		 byte[] imageData = null;

		 try
		 {
			 
			 String sql="select * from books where book_name='"+book_name+"'";
			 statement =con.prepareStatement(sql);
			 resultSet=statement.executeQuery();
			 if(resultSet.next())
			 {

				 Blob image=resultSet.getBlob("cover");
					
				try 
				 {
					 imageData = image.getBytes(1,(int)image.length());
									
				 } 
				 catch (SQLException e)
				 {
					 System.out.println(e);
				 }
				 bean.setCoverPhoto(imageData);
			
			 }
		 }
		 catch(Exception e)
		 {
			 System.out.println(e);
		 }
		 return bean;
	}
	 
	 
	 
	 public int cataloguePageNumber()
	 {
		 int page=0;
		 try
		 {
			 statement =con.prepareStatement("select count(*) from books ");
			 resultSet=statement.executeQuery();
			 if(resultSet.next())
			 {
				 if (resultSet.getInt(1)%5==0)
					 page=(resultSet.getInt(1)/5);
				 else 
					 page=(resultSet.getInt(1)/5)+1;
			 }
		 }
		 catch(Exception e)
		 {
			 System.out.println(e);
			 page=-1;
		 }
		 finally
		 {
			 try
			 {
				 if (resultSet!=null)
				 {
					 resultSet.close();
				 }
				 if (statement!=null)
				 {
					 statement.close();
				 }
			 }
			 catch(SQLException e)
			 {
				 System.out.println(e);
				 page=-1;
			 }
		 }
		 
		 return page;
	 }	 

	public  List<BookBean> treeview( )
	{
		List<BookBean> list=new ArrayList<BookBean>();
		
		try
		{
		 	statement= (PreparedStatement)con.prepareStatement("select * from book_category ");
			resultSet=statement.executeQuery();
							
			while (resultSet.next())
		 	{
				BookBean bean=new BookBean();
				
				statement1= (PreparedStatement)con.prepareStatement("select count(*) from books where category_id=?");
				statement1.setString(1, resultSet.getString(1));
				resultSet1=statement1.executeQuery();
				
				int len=0;
				
				if (resultSet1.next())
				{
					len=resultSet1.getInt(1);
				}
				
				String [] book_names=new String[len];
									
				bean.setCategory(resultSet.getString(2));
					
				statement1= (PreparedStatement)con.prepareStatement("select book_name from books where category_id='"+resultSet.getString(1)+"'");
				resultSet1=statement1.executeQuery();
				
				int i=0;
				
				while (resultSet1.next())
				{	 
					book_names[i]=resultSet1.getString(1);
					i++;
				}
				bean.setBookNames(book_names);

		 	  	list.add(bean);
		 	}
		}
		catch(Exception e)
		{
			System.out.println(e);
	 	  	list=null;
		 }
		 finally
		 {
			 try
			 {
				 if (resultSet!=null)
				 {
					 resultSet.close();
				 }
				 if (statement!=null)
				 {
					 statement.close();
				 }
				 if (resultSet1!=null)
				 {
					 resultSet1.close();
				 }
				 if (statement1!=null)
				 {
					 statement1.close();
				 }
			 }
			 catch(SQLException e)
			 {
				 System.out.println(e);
			 	  	list=null;
			 }
		 }
		return list;
	}	
	 public List<BookBean> findBook(String book_name,int start,int total)
	 {
		 List<BookBean> list=new ArrayList<BookBean>();
		 try
		 {
			 statement =con.prepareStatement("select book_name from books  where book_name like '%"+book_name+"%' limit "+(start-1)+","+total+"");
			 resultSet = statement.executeQuery();
			 
			 while(resultSet.next())
			 {
				 BookBean bean=new BookBean();

				 bean.setBookName(resultSet.getString(1));
			 	
		 	  	 list.add(bean);
			 }
				 
		 }
		 catch(SQLException e)
		 {
			 System.out.println(e);
			 list=null;
		 }
		 finally
		 {
			 try
			 {
				 if (resultSet!=null)
				 {
					 resultSet.close();
				 }
				 if (statement!=null)
				 {
					 statement.close();
				 }
			 }
			 catch(SQLException e)
			 {
				 System.out.println(e);
			 	 list=null;
			 }
		 }
		return list;
	}
		
		 public int searchPageNumber(String book_name)
		 {
			 int page=0;
			 try
			 {
				 String sql="select count(*) from books  where book_name like '%"+book_name+"%'  ";
				 statement =con.prepareStatement(sql);
				 resultSet=statement.executeQuery();
				 if(resultSet.next())
				 {
					 if (resultSet.getInt(1)%5==0)
						 page=(resultSet.getInt(1)/5);
					 else 
						 page=(resultSet.getInt(1)/5)+1;
				 }
			
			 
			 }catch(Exception e)
			 {
				 System.out.println(e);
				 page=0;
			 }
			 finally
			 {
				 try
				 {
					 if (resultSet!=null)
					 {
						 resultSet.close();
					 }
					 if (statement!=null)
					 {
						 statement.close();
					 }
				 }
				 catch(SQLException e)
				 {
					 System.out.println(e);
					 page=0;
				 }
			 }
			 
			 return page;
		 }

		public List<String> searchBooks()
		{

			List<String> list=new ArrayList<String>();
					
			try
			{
				 statement =con.prepareStatement("select book_name from books");
				 resultSet = statement.executeQuery();
				 
				 while(resultSet.next())
				 {
			 	  	 list.add(resultSet.getString(1));
				 }	 
			 }
			 catch(SQLException e)
			 {
				 System.out.println(e);
				 list=null;
			 }
			 finally
			 {
				 try
				 {
					 if (resultSet!=null)
					 {
						 resultSet.close();
					 }
					 if (statement!=null)
					 {
						 statement.close();
					 }
				 }
				 catch(SQLException e)
				 {
					 System.out.println(e);
				 	 list=null;
				 }
			 }
			return list;
			
			
		}
		
	public String validateUser (String user_name,String password)
	{
		
		String role= "";
		
		String user="";
		 try
		 {
			 statement =con.prepareStatement("select role from members where user_name=? AND password=?");
			 statement.setString(1, user_name);
			 statement.setString(2, password);
			 
			 resultSet = statement.executeQuery();
				
			 if(resultSet.next())
			 {
				 role = resultSet.getString(1);
				 
				 if (role.equals(Role.Librarian.name()))
				 {
					 user="Librarian";
				 }
				 else if (role.equals(Role.User.name()))
				 {
					 user="User";
				 }
			 }
			 else
			 {
				 user="Invalid";
			 }
		 }
		 catch(SQLException e)
		 {
			 System.out.println(e);
			 user=null;
		 }			
		 finally
		 {
			 try
			 {
				 if (resultSet!=null)
				 {
					 resultSet.close();
				 }
				 if (statement!=null)
				 {
					 statement.close();
				 }
			 }
			 catch(SQLException e)
			 {
				 System.out.println(e);
				 user=null;
			 }
		 }
		 return user;
	}
	
	
	public List<MemberBean> showUsers()
	{
		List<MemberBean> list=new ArrayList<MemberBean>();
		
		 try
		 {
			statement= (PreparedStatement)con.prepareStatement("select * from members where role='User'");
			resultSet =statement.executeQuery();    
			
			while (resultSet.next())
		 	 {
				 MemberBean bean=new MemberBean();

		 		 bean.setUserId(resultSet.getString(1));
		 	  	 bean.setUserName(resultSet.getString(2));
		 	  	 bean.setFirstName(resultSet.getString(5));
		 	  	 bean.setLastName(resultSet.getString(6));
		 	  	 bean.setPhoneNo(resultSet.getString(7));
		 	  	 list.add(bean);
		 	 }
			
						
		 }
		 catch(SQLException e)
		 {
			 System.out.println(e);
			 list=null;
		 }
		 finally
		 {
			 try
			 {
				 if (resultSet!=null)
				 {
					 resultSet.close();
				 }
				 if (statement!=null)
				 {
					 statement.close();
				 }
			 }
			 catch(SQLException e)
			 {
				 System.out.println(e);
				 list=null;
			 }
		 }
		return list;
	 
	}
	
	
	
	public String requestIssue(IssueBean bean) 
	{
		String user_name = bean.getUserName();
		String book_id = bean.getBookId();
		String user_id="";
		String status="Already Requested";

		try
		{
			statement = (PreparedStatement)con.prepareStatement("select user_id from members where user_name=?");
		
			statement.setString(1, user_name);
			resultSet =statement.executeQuery();    
			if (resultSet.next())
			{	
				user_id=resultSet.getString(1);
			}
			String check=checkRequest(user_id,book_id);
	
			if (check==null)
			{
				status="error";
			}
			else if (check.equals("Requested"))
			{
				status="Requested";
			}
			else if (check.equals("No Request"))
			{
		 		statement= (PreparedStatement)con.prepareStatement("insert into issue_request values (?,?,?)");
		 		statement.setString(1,book_id);
			 	statement.setString(2,user_id);
			 	statement.setString(3,"Pending");

				statement.executeUpdate();
				status="Request Sent";
			}
			else if (check.equals("Not Available"))
			{
				status="Not Available";
			}
		}
		catch(SQLException e)
		{
			System.out.println(e);
			status="error";
		}
		finally
		 {
			 try
			 {
				 if (resultSet!=null)
				 {
					 resultSet.close();
				 }
				 if (statement!=null)
				 {
					 statement.close();
				 }
			 }
			 catch(SQLException e)
			 {
				 System.out.println(e);
					status="error";
			 }
		 }
		
		return status;		
	}

	public String checkRequest(String user_id, String book_id)
	{
		String status="No Request";
		try
		{
		 	 statement= (PreparedStatement)con.prepareStatement("select * from issue_request where user_id=? and book_id=? and issue_status=?");
		 	 statement.setString(1, user_id);
		 	 statement.setString(2, book_id);
		 	 statement.setString(3, "Pending");

		 	 resultSet=statement.executeQuery();
		 	 
		 	 if (resultSet.next())
		 	 {
		 		 status="Requested";
		 	 }
		 	 else
		 	 {
		 		 statement= (PreparedStatement)con.prepareStatement("select  (quantity-issued_quantity) from books where book_id=? ");
			 	 statement.setString(1, book_id);

			 	 resultSet=statement.executeQuery();
			 	 
			 	 if (resultSet.next())
			 	 {
			 		 if (resultSet.getInt(1)==1)
			 		 {
					 	 statement= (PreparedStatement)con.prepareStatement("select * from issue_request where book_id=? and issue_status=?");
					 	 statement.setString(1, book_id);
					 	 statement.setString(2, "Pending");
					 	 resultSet=statement.executeQuery();
					 	 
					 	 if (resultSet.next())
					 	 {
					 		 status="Not Available";
					 	 }
					 	
					 	 
			 		 }
			 	 }

		 	 }
		 	 
		}
		catch(SQLException e)
		{
			System.out.println(e);
			status=null;
		}

		return status;
	}
	
	
	
	 public  List<IssueBean>  showRequests ()
	 {
		 List<IssueBean> list=new ArrayList<IssueBean>();

		 try
		 {
		 	 statement= (PreparedStatement)con.prepareStatement("select * from issue_request where issue_status='Pending'");
		 	 resultSet = statement.executeQuery();			
		 	 while (resultSet.next())
		 	 {
		 		IssueBean bean=new IssueBean();

		 		 bean.setBookId(resultSet.getString(1));
		 	  	 bean.setUserId(resultSet.getString(2));
		 	  			 	  	 
		 	  	 list.add(bean);
		 	 }
		 
		 }
		 catch(Exception e)
		 {
			 System.out.println(e);
			 list=null;
		 }
		 finally
		 {
			 try
			 {
				 if (resultSet!=null)
				 {
					 resultSet.close();
				 }
				 if (statement!=null)
				 {
					 statement.close();
				 }
			 }
			 catch(SQLException e)
			 {
				 System.out.println(e);
				 list=null;
			 }
		 }
		return list;
		 
	 }
	 
	 
	 public  String issueBook(IssueBean bean)
	 {
		 String user_id = bean.getUserId();
		 String book_id = bean.getBookId();
		 
		 String status="";
		 
		 Date issue_date = new Date(System.currentTimeMillis());
	    
	     
		 String checkstatus=checkIssue(user_id,book_id);
		 
		 if (checkstatus.equals("Existing"))
		 {
			 status="Existing";
		 }
		 else if (checkstatus.equals("Available"))
		 {
		 	 int issued_quantity=0;
			 try
			 {
			 	
			 	 statement= (PreparedStatement)con.prepareStatement("insert into issued_books values (?,?,?,?)");
				
			 	 statement.setString(1,book_id);
			 	 statement.setString(2,user_id);
			 	 statement.setDate(3,issue_date);
			 	 statement.setDate(4, null);
				 statement.executeUpdate();
				
				 statement= (PreparedStatement)con.prepareStatement("update issue_request set issue_status = 'Accepted' where user_id=? and book_id=?");
				 statement.setString(1, user_id);
				 statement.setString(2, book_id);
				 statement.executeUpdate();
			 	 
				 statement= (PreparedStatement)con.prepareStatement("select issued_quantity from books where book_id=?");
			 	 statement.setString(1, book_id);
			 	 resultSet = statement.executeQuery();			
			 	 if (resultSet.next())
			 	 {
			 		issued_quantity=resultSet.getInt(1);
			 		issued_quantity++;

			 	 }
				 statement= (PreparedStatement)con.prepareStatement("update books set issued_quantity=? where book_id=?");
				 statement.setInt(1, issued_quantity);
				 statement.setString(2,book_id);
			 	 statement.executeUpdate();			
			 	 
			 }
			
			catch(SQLException e)
			{
				System.out.println(e);
				status=null;
			}
			 finally
			 {
				 try
				 {
					 if (resultSet!=null)
					 {
						 resultSet.close();
					 }
					 if (statement!=null)
					 {
						 statement.close();
					 }
				 }
				 catch(SQLException e)
				 {
					 System.out.println(e);
					 status=null;
				 }
			 }

			 status="issued";
		 } 
		 else 
			 status="not available";
		 
		 return status;
	 }
	 
	 
	 public  String checkIssue(String user_id,String book_id)
	 {
			
			String status="Not available";
			try
			{
				
				statement =con.prepareStatement("select * from issued_books where user_id=? and book_id=? and return_date is null");
				statement.setString(1, user_id);
				statement.setString(2, book_id);

				resultSet=statement.executeQuery();
				
				
				if (resultSet.next())
				{	
					status="Existing";
				}
				else
				{
					statement=con.prepareStatement("select * from books where book_id=? and quantity>issued_quantity");
					statement.setString(1, book_id);
					resultSet = statement.executeQuery();		
					if(resultSet.next())
					{
						status="Available";
					}
				}
			}	
			catch(SQLException e)
			{
				System.out.println(e);
			}
			
			return status;
		
	}





	public String rejectBook(IssueBean bean) 
	{
		 String user_id = bean.getUserId();
		 String book_id = bean.getBookId();
		 
		 String status="";
	
			 try
			 {
				 statement= (PreparedStatement)con.prepareStatement("update issue_request set issue_status = 'Rejected' where user_id=? and book_id=?");
				 statement.setString(1, user_id);
				 statement.setString(2, book_id);
				 statement.executeUpdate();
			 	 
				 status="Reject";
			 	 
			 }
			
			catch(Exception e)
			{
				System.out.println(e);
				status=null;
			}
			 finally
			 {
				 try
				 {
					 if (resultSet!=null)
					 {
						 resultSet.close();
					 }
					 if (statement!=null)
					 {
						 statement.close();
					 }
				 }
				 catch(SQLException e)
				 {
					 System.out.println(e);
					 status=null;
				 }
			 }

		 return status;
	 }
	
	public List<ShowHistoryBean> showHistory(String user_name)
	{
		 List<ShowHistoryBean> list=new ArrayList<ShowHistoryBean>();
		String user_id="";
		PreparedStatement statement1 = null;
		ResultSet resultSet1 = null;
		 try
		 {
			statement= (PreparedStatement)con.prepareStatement("select user_id from members where user_name=?");
			statement.setString(1,user_name);
			resultSet =statement.executeQuery();    
			if (resultSet.next())
			{
				user_id=resultSet.getString(1);
			}
			
			statement= (PreparedStatement)con.prepareStatement("select * from issue_request where user_id=? and issue_status=? or issue_status=? ");
			statement.setString(1,user_id);
			statement.setString(2,"Pending");
			statement.setString(3,"Rejected");

			resultSet =statement.executeQuery();    	
			while (resultSet.next())
		 	{
				 ShowHistoryBean bean=new ShowHistoryBean();

		 		 bean.setBookId(resultSet.getString(1));
		 	  	 bean.setIssueStatus(resultSet.getString(3));
		 	  	 statement1= (PreparedStatement)con.prepareStatement("select book_name from books where book_id=?");
				 statement1.setString(1, resultSet.getString(1));
				 resultSet1 =statement1.executeQuery();    
				
				 if (resultSet1.next())
			 	 {
					
			 		 bean.setBookName(resultSet1.getString(1));
			 	 }
				
		 	  	 list.add(bean);
		 	 }

			
			
			
			statement= (PreparedStatement)con.prepareStatement("select * from issued_books where user_id=?");
			statement.setString(1,user_id);
			resultSet =statement.executeQuery();    	
			while (resultSet.next())
		 	{
				 ShowHistoryBean bean=new ShowHistoryBean();

		 		 bean.setBookId(resultSet.getString(1));
		 	  	 bean.setIssueDate(resultSet.getDate(3));
		 	  	 bean.setReturnDate(resultSet.getDate(4));
		 	  	 bean.setIssueStatus("Issued");

		 	  	 statement1= (PreparedStatement)con.prepareStatement("select book_name from books where book_id=?");
				 statement1.setString(1, resultSet.getString(1));
				 resultSet1 =statement1.executeQuery();    
				
				 if (resultSet1.next())
			 	 {
					
			 		 bean.setBookName(resultSet1.getString(1));
			 	 }
				
		 	  	 list.add(bean);
		 	 }
			
						
		 }
		 catch(Exception e)
		 {
			 System.out.println(e);
			 list=null;
			}
			 finally
			 {
				 try
				 {
					 if (resultSet!=null)
					 {
						 resultSet.close();
					 }
					 if (statement!=null)
					 {
						 statement.close();
					 }
				 }
				 catch(SQLException e)
				 {
					 System.out.println(e);
					 list=null;
				 }
			 }

		return list;
	 }
	
	 public  List<IssueBean> showIssuedBooks()
	 {
		 List<IssueBean> list=new ArrayList<IssueBean>();

		 try
		 {
			statement= (PreparedStatement)con.prepareStatement("select * from issued_books where return_date is null order by issue_date");
			resultSet =statement.executeQuery();    
			
			while (resultSet.next())
		 	 {
				 IssueBean bean=new IssueBean();

		 		 bean.setBookId(resultSet.getString(1));
		 	  	 bean.setUserId(resultSet.getString(2));
		 	  	 bean.setIssueDate(resultSet.getDate(3));
		 	  	 bean.setReturnDate(resultSet.getDate(4));
		 	  		
		 	  	 list.add(bean);
		 	 }
			statement= (PreparedStatement)con.prepareStatement("select * from issued_books where return_date is not null order by return_date");
			resultSet =statement.executeQuery();    
			
			while (resultSet.next())
		 	 {
				 IssueBean bean=new IssueBean();

		 		 bean.setBookId(resultSet.getString(1));
		 	  	 bean.setUserId(resultSet.getString(2));
		 	  	 bean.setIssueDate(resultSet.getDate(3));
		 	  	 bean.setReturnDate(resultSet.getDate(4));
		 	  		
		 	  	 list.add(bean);
		 	 }
		 }
		 catch(Exception e)
		 {
			 System.out.println(e);
			 list=null;
			}
			 finally
			 {
				 try
				 {
					 if (resultSet!=null)
					 {
						 resultSet.close();
					 }
					 if (statement!=null)
					 {
						 statement.close();
					 }
				 }
				 catch(SQLException e)
				 {
					 System.out.println(e);
					 list=null;
				 }
			 }

		return list;
	 }
	 
	 
	 
	 public  String returnBook(IssueBean bean)
	 {
		 String user_id = bean.getUserId();
		 String book_id = bean.getBookId();
		 Date return_date = new Date(System.currentTimeMillis());
		 int issued_quantity=0;
		 String status="";
		 try
		 {
		 	statement= (PreparedStatement)con.prepareStatement("update issued_books set return_date=? where user_id=? and book_id=? and return_date is null ");
			statement.setDate(1, return_date);
			statement.setString(2, user_id);
			statement.setString(3, book_id);
			
			statement.executeUpdate();
			
			statement= (PreparedStatement)con.prepareStatement("select issued_quantity from books where book_id=?");
			statement.setString(1, book_id);
			resultSet = statement.executeQuery();			
		 	 if (resultSet.next())
		 	 {
		 		issued_quantity=resultSet.getInt(1);
		 		issued_quantity--;

		 	 }
		 	
			 statement= (PreparedStatement)con.prepareStatement("update books set issued_quantity=? where book_id=?");
		 	 statement.setInt(1,issued_quantity);
		 	 statement.setString(2, book_id);
			 statement.executeUpdate();	
			 status="Returned";
		}
		
		catch(Exception e)
		{
			System.out.println(e);
			 status=null;
		}
		 finally
		 {
			 try
			 {
				 if (resultSet!=null)
				 {
					 resultSet.close();
				 }
				 if (statement!=null)
				 {
					 statement.close();
				 }
			 }
			 catch(SQLException e)
			 {
				 System.out.println(e);
				 status=null;
			 }
		 }

		 return status;

	 }
	 
	 public String register (MemberBean bean)
		{
			 String user_name = bean.getUserName();
			 String password = bean.getPassword();
			 String role = bean.getRole();
			 String first_name=bean.getFirstName();
			 String last_name=bean.getLastName();
			 int phone_no=Integer.parseInt(bean.getPhoneNo());
			 String email_id=bean.getEmail_id();
			 String user_id="";
			 
			 String status="";
			 
			 String check=checkUser(user_name);
			System.out.println(email_id);
			 if (check.equals("error"))
			 {
				 status="error";
			 }
			 else if (check.equals("Doesn't Exists"))
			 { 
				 try
				 {
					 statement =con.prepareStatement("select user_id from  members where role='"+role+"' order by user_id desc limit 1 ");
					 resultSet = statement.executeQuery();
					if (resultSet.next())
					{
						String lastUserId=resultSet.getString(1);
						String temp="";
						 for (int i=1;i<lastUserId.length();i++)
						 {
							 temp=temp+lastUserId.charAt(i);
						 }

						 int number=Integer.parseInt(temp);
						 number++;
						 user_id=user_id+lastUserId.charAt(0);
						 user_id=user_id+Integer.toString(number);
						
					}	
					else
					{
						user_id="U1";
					}
					
					statement =con.prepareStatement("insert into members values (?,?,?,?,?,?,?,?)");
					statement.setString(1,user_id);
				 	statement.setString(2,user_name);
				 	statement.setString(3,password);
				 	statement.setString(4, role);
				 	statement.setString(5, first_name);
				 	statement.setString(6, last_name);
				 	statement.setInt(7, phone_no);
				 	statement.setString(8, email_id);
					statement.executeUpdate();
					
					
					status="Registered";
				 }
				 catch(SQLException e)
				 {
					 System.out.println(e);
					 status=null;
				}
				 finally
				 {
					 try
					 {
						 if (resultSet!=null)
						 {
							 resultSet.close();
						 }
						 if (statement!=null)
						 {
							 statement.close();
						 }
					 }
					 catch(SQLException e)
					 {
						 System.out.println(e);
						 status=null;
					 }
				 }	 
			 }
			 else 
			 {
				 status="Existing";
	
			 }
			 
			return status;
		}
		
		
		public String checkUser(String user_name)
		{
			String status="Doesn't Exists" ;
			try
			{
				statement =con.prepareStatement("insert into books values (?,?,?,?,?,?,?)");

				statement =con.prepareStatement("select * from members where user_name=?");
				statement.setString(1,user_name);
				resultSet = statement.executeQuery();
				if (resultSet.next())
				{
					status="Existing";
				}
				
			}
			 catch(SQLException e)
			 {
				 System.out.println(e);
				 status="error";
			 }
			 return status;
		}





		public String sendEmail(MemberBean bean) 
		{
			 //String user_name = bean.getUserName();
			 String first_name=bean.getFirstName();
			 String receiver_email_id=bean.getEmail_id();
			 
			 final String username = "librarianabc74@gmail.com";
				final String password = "librarian";

			 String sender_email_id = "librarianabc74@gmail.com";
			 String status = null;
			 

			 Properties props = new Properties();
				props.put("mail.smtp.auth", "true");
				props.put("mail.smtp.starttls.enable", "true");
				props.put("mail.smtp.host", "smtp.gmail.com");
				props.put("mail.smtp.port", "587");
/*				 try
				  {
						statement =con.prepareStatement("select email_id from members where role=?");
						statement.setString(1,"Librarian");
						resultSet = statement.executeQuery();
						if (resultSet.next())
						{
							sender_email_id=resultSet.getString(1);
						}
						
						
				  }
				 catch (SQLException e)
				 {
					 System.out.println(e);
					 status=null;
				} */
				 
				Session session = Session.getInstance(props,new javax.mail.Authenticator() 
				{
					protected PasswordAuthentication getPasswordAuthentication()
					{
						return new PasswordAuthentication(username, password);
					}
				  });
				try {

					Message message = new MimeMessage(session);
					message.setFrom(new InternetAddress(sender_email_id));

			         message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver_email_id));

			         message.setSubject("Library Subscription!");

			         message.setText("Hello "+first_name+ ", \nYou have successfully registered to Library.");


					Transport.send(message);

					System.out.println("Done");
			         status="Sent";


				} 
				
			  catch (MessagingException e) 
			  {
				  System.out.println(e);
					 status=null;
			  } 
			 			 
				
			 finally
			 {
				 try
				 {
					 if (resultSet!=null)
					 {
						 resultSet.close();
					 }
					 if (statement!=null)
					 {
						 statement.close();
					 }
				 }
				 catch(SQLException e)
				 {
					 System.out.println(e);
					 status=null;
				 }
			 }

			return status;
		}



}
