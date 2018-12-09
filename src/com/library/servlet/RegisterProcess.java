package com.library.servlet;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.library.bean.MemberBean;
import com.library.dao.LibraryDAO;

/**
 * Servlet implementation class RegisterProcess
 */
@WebServlet("/RegisterProcess")
public class RegisterProcess extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		PrintWriter out=response.getWriter();
		
		String user_name = request.getParameter("user_name");
		String password = request.getParameter("password");
		String first_name=request.getParameter("first_name");
		String last_name=request.getParameter("last_name");
		String phone_no=request.getParameter("phone_no");
		String email_id=request.getParameter("remail");
		MemberBean bean = new MemberBean();
		LibraryDAO dao = new LibraryDAO();
		
		try 
		{
			bean.setUserName(user_name);
			bean.setPassword(password);
			bean.setRole("User");
			bean.setFirstName(first_name);
			bean.setLastName(last_name);
			bean.setPhoneNo(phone_no);
			bean.setEmail_id(email_id);
			String register_status=dao.register(bean);
			HttpSession session = request.getSession();	    
			session.setAttribute("div", "hide");

			session.setAttribute("change",null);

			request.getRequestDispatcher("login.jsp").include(request, response);  

			if (register_status==null)
			{
				out.print("<br><center><h4><span style='background-color:white'><font color='red'>Error: Could not fetch data from database</font><span></h4></center>"); 

			}
			else if (register_status.equals("Registered"))
			{
				out.print("<br><center><h4><span style='background-color:white'><font color='red'>User Registered!</font><span></h4></center>");  
				String email=dao.sendEmail(bean);
				if (email==null)
				{
					out.print("<br><center><h4><span style='background-color:white'><font color='red'>Error: Could not fetch data from database</font><span></h4></center>"); 
				}
			}
			else
			{
				out.print("<br><center><h4><span style='background-color:white'><font color='red'>User name Already Exists!</font><span></h4></center>");  
			}
			
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}		

}


















