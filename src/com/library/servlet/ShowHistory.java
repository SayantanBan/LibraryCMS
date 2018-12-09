package com.library.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.library.bean.ShowHistoryBean;
import com.library.dao.LibraryDAO;

/**
 * Servlet implementation class ShowHistory
 */
@WebServlet("/ShowHistory")
public class ShowHistory extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();	    
		String currentUserName = (String)(session.getAttribute("currentUserName"));

		
		PrintWriter out=response.getWriter();
		 
		LibraryDAO dao= new LibraryDAO();
		session.setAttribute("div", "hide");

		List<ShowHistoryBean> bean = dao.showHistory(currentUserName);
		request.getRequestDispatcher("LibraryHome.jsp").include(request, response);  

		
		if (bean==null)
		{
			out.print("<br><center><h4><font color='red'>Error: Could not fetch data from database</font></h4></center>"); 

		}
		else if (bean.isEmpty())
		{
			 out.print("<br><center><font color='red'>No Book Issued!</font></center>");  
		}
		else
		{
			request.setAttribute("history", bean);
			getServletConfig().getServletContext().getRequestDispatcher("/showHistory.jsp").forward(request,response);
		}
	}
}	
