package com.library.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.library.bean.IssueBean;
import com.library.dao.LibraryDAO;



/**
 * Servlet implementation class ShowIssued
 */
@WebServlet("/ShowIssued")
public class ShowIssued extends HttpServlet 
{
	static Connection con = null;
	static PreparedStatement statement = null;
	static ResultSet resultSet = null;
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		 PrintWriter out=response.getWriter();
		 
		 LibraryDAO dao= new LibraryDAO();
		 
		 List<IssueBean> bean = dao.showIssuedBooks();
		 HttpSession session = request.getSession();	    
			session.setAttribute("div", "hide");

		if (bean.isEmpty())
		{
			 request.getRequestDispatcher("librarianHome.jsp").include(request, response);  
			 out.print("<br><center><font color='red'>No Book Issued!</font></center>");  

		}
		else
		{
			request.setAttribute("issued", bean);
			
			getServletConfig().getServletContext().getRequestDispatcher("/showIssued.jsp").forward(request,response);
		}
	}
}
