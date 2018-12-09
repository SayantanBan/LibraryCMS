package com.library.bean;

import java.sql.Date;
/**
 * This entity is used for issuing, returning books and displaying issued books
 * @author shreyab
 *
 */
public class IssueBean
{
	private String user_id;
	private String book_id;
	private Date issue_date;
	private Date return_date;
	private String username;
	private String issue_status;
	
    public String getUserId() 
    {
       return user_id;
	}

    public void setUserId(String newUserId)
    {
    	user_id = newUserId;
    }
    
    public String getBookId() 
    {
       return book_id;
	}

    public void setBookId(String newBookId)
    {
    	book_id = newBookId;
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
    public String getUserName() 
    {
        return username;
	}

     public void setUserName(String newUsername) 
     {
        username = newUsername;
	 }
     
     public String getIssueStatus() 
     {
         return issue_status;
 	}

      public void setIssueStatus(String newIssueStatus) 
      {
    	  issue_status = newIssueStatus;
 	 }
     
}
