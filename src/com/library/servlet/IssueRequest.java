package com.library.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.library.bean.IssueBean;
import com.library.dao.LibraryDAO;

/**
 * Servlet implementation class IssueRequest
 */
@WebServlet("/IssueRequest")
public class IssueRequest extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		 String book_id=request.getParameter("book_id");
		
		 PrintWriter out=response.getWriter();

		 HttpSession session = request.getSession();	    
		 String currentUserName = (String)(session.getAttribute("currentUserName"));

		 IssueBean bean= new IssueBean();
		 
		 bean.setBookId(book_id);
		 bean.setUserName(currentUserName);
		
		 LibraryDAO dao=new LibraryDAO();
		 session.setAttribute("div", "show");

		 request.getRequestDispatcher("LibraryHome.jsp").include(request, response);  

		
		 String request_status=dao.requestIssue(bean);

		 if (request_status.equals("error"))
		 {
			 out.print("<script>alert('Error: Could not fetch data from database');</script>"); 
		 }
		
		 else if (request_status.equals("Request Sent"))
		 {
			 out.print("<script>alert('Request sent!');</script>");  
		 }
		 else if (request_status.equals("Requested"))
		 {
			 
			 out.print("<script>alert('Request already sent!');</script>");  

		 }
		 else if (request_status.equals("Not Available"))
		 {
			 out.print("<script>alert('Book Not Available...Try Later!');</script>");  

		 }
	 }
	
}
