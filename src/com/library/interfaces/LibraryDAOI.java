package com.library.interfaces;

import java.util.List;

import com.library.bean.BookBean;
import com.library.bean.IssueBean;
import com.library.bean.MemberBean;
import com.library.bean.ShowHistoryBean;

public interface LibraryDAOI 
{
	/**
	 * Fetches Book Names according to user input
	 * @return The list of Book Names
	 */
	public List<String> searchBooks();

	/**
	 * Fetches book details according to user input
	 * @param book_name
	 * @param start
	 * @param total
	 * @return
	 */
	 public List<BookBean> findBook(String book_name,int start,int total);
	 
	 /**
	  * Calculates the number of pages required for displaying search result
	  * @param book_name
	  * @return
	  */
	 public int searchPageNumber(String book_name);
	 
	 /**
	  * Fetches individual Book details
	  * @param book_name
	  * @return
	  */
	 public BookBean bookDetails(String book_name);

	 /**
	  * Fetches books according to page limit
	  * @param start
	  * @param total
	  * @return
	  */
	 public  List<String> bookList(int start,int total);

	 /**
	  * Calculates the number of pages required for displaying book catalogue
	  * @return
	  */
	 public int cataloguePageNumber();
	 
	 /**
	  * Fetches book details for treeview
	  * @return
	  */
	 public  List<BookBean> treeview();

	 /**
	  * checks the password according to the user name	
	  * @param bean
	  * @return
	  */
		public String validateUser (String user_name,String password);

	 /**
		 *inserts book details in the database
		 * @return
		 */
		public String add(BookBean bean);
		/**
		 *checks if the book already exists in the database
		 * @return
		 */
		public String checkBook(String book_name);

		
		/**
		 * fetches all user details
		 * @return all user details
		 */
		public List<MemberBean> showUsers();

		/**
		 * fetches all issued books details 
		 * @param user_id
		 * @return all issued books details
		 */
		public List<ShowHistoryBean> showHistory(String user_id);

		/**
		 * fetches  category names
		 * @return
		 */
		public List<String> getCategory();
		 /**
		 *fetches the image of the book 
		 * @return the book image
		 */
		 
		 public  BookBean displayImage(String book_name);
		 /**
			 * inserts book issue details in the database
			 * @param bean
			 * @return
			 */
			public String requestIssue(IssueBean bean) ;

			/**
			 * checks if request is already sent
			 * @param user_id
			 * @param book_id
			 * @return
			 */
			public String checkRequest(String user_id, String book_id);

			 /**
			 *shows all request for books
			 * @return
			 */
			public  List<IssueBean>  showRequests ();
			
			/**
			 * inserts details of issued book in database
			 * 
			 */
			public  String issueBook(IssueBean bean);
			/**
			 * checks if the book is available in the library and if the book is already issued to the user
			 *  
			 */
			 public  String checkIssue(String user_id,String book_id);

			 /**
			  * upadates request status when rejected
			  * @param bean
			  * @return
			  */
				public String rejectBook(IssueBean bean); 
				/**
				 * fetches all issued books details
				 * @return
				 */
				 public  List<IssueBean>  showIssuedBooks();

					/**
					 * updates return details in the database
					 * @param bean
					 * @return
					 */
					 public  String returnBook(IssueBean bean);

					 /**
						 *inserts user details in the database
						 * @return
						 */
						public String register (MemberBean bean);
						/**
						 *checks if the user name already exists
						 * @return
						 */
						public String checkUser(String user_name);	 





















}
