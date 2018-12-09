package com.library.bean;
/**
 * This entity id used for registering new members, displaying user details and checking login credentials 
 * @author shreyab
 *
 */
public class MemberBean 
{
	private String user_id;
    private String username;
    private String role;
    private String password;
    private String first_name;
    private String last_name;
    private String phone_no;
	private String email_id;

	
    public String getEmail_id()
    {
		return email_id;
	}

	public void setEmail_id(String email_id) 
	{
		this.email_id = email_id;
	}


    public String getUserId() 
    {
       return user_id;
	}

    public void setUserId(String newUserId)
    {
    	user_id = newUserId;
    }
    
    public String getUserName() 
    {
        return username;
	}

     public void setUserName(String newUsername) 
     {
        username = newUsername;
	 }
     
     public String getFirstName() 
     {
         return first_name;
 	}

      public void setFirstName(String newFirstName) 
      {
    	  first_name = newFirstName;
 	 }
      public String getLastName() 
     {
         return last_name;
 	}

      public void setLastName(String newLastName) 
      {
    	  last_name = newLastName;
 	 }
      public String getPhoneNo() 
     {
         return phone_no;
 	}

      public void setPhoneNo(String newPhoneNo) 
      {
    	  phone_no = newPhoneNo;
 	 }
     public String getRole() 
     {
         return role;
 	}

      public void setRole(String newRole) 
      {
         role = newRole;
 	 }
	public String getPassword()
    {
       return password;
	}

    public void setPassword(String newPassword) 
    {
       password = newPassword;
	}

}
