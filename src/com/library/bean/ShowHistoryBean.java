package com.library.bean;

import java.sql.Date;
/**
 * This entity is used to display issued book details
 * @author shreyab
 *
 */
public class ShowHistoryBean
{
	private String book_id;
	private Date issue_date;
	private Date return_date;
	private String book_name;
	private String issueStatus;
	

	 public String getBookId() 
	    {
	       return book_id;
		}

	    public void setBookId(String newBookId)
	    {
	    	book_id = newBookId;
	    }
	    
	    public String getBookName() 
	    {
	       return book_name;
		}

	    public void setBookName(String newbookName)
	    {
	    	book_name = newbookName;
	    }


	    public Date getIssueDate() 
	    {
	       return issue_date;
		}

	    public void setIssueDate(Date newIssueDate)
	    {
	    	issue_date = newIssueDate;
	    }
	    
	    public Date getReturnDate() 
	    {
	       return return_date;
		}

	    public void setReturnDate(Date newReturnDate)
	    {
	    	return_date = newReturnDate;
	    }
	    
	    public String getIssueStatus() 
	    {
	       return issueStatus;
		}


		public void setIssueStatus(String newissueStatus)
		{
			issueStatus=newissueStatus;
		}

}
