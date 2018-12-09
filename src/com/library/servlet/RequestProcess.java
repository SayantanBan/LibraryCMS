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

import com.library.bean.IssueBean;
import com.library.dao.LibraryDAO;

/**
 * Servlet implementation class RequestProcess
 */
@WebServlet("/RequestProcess")
public class RequestProcess extends HttpServlet 
{
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		PrintWriter out=response.getWriter();

		LibraryDAO dao =new LibraryDAO();

		List<IssueBean> bean = dao.showRequests();
		HttpSession session = request.getSession();	    
		session.setAttribute("div", "hide");

		request.getRequestDispatcher("LibraryHome.jsp").include(request, response);  

		if (bean==null)
		{
			out.print("<br><center><h4><font color='red'>Error: Could not fetch data from database</font></h4></center>"); 
		}
		if (bean.isEmpty())
		{
			 out.print("<br><center><font color='red'>No Pending Request!</font></center>");  

		}
		else
		{
			request.setAttribute("request", bean);
			getServletConfig().getServletContext().getRequestDispatcher("/issueRequest.jsp").forward(request,response);
		}
	}
}


