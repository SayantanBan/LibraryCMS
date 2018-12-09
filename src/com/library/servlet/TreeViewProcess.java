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

import com.library.bean.BookBean;
import com.library.dao.LibraryDAO;

/**
 * Servlet implementation class TreeViewProcess
 */
@WebServlet("/TreeViewProcess")
public class TreeViewProcess extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		PrintWriter out=response.getWriter();

		LibraryDAO dao= new LibraryDAO();
		
		HttpSession session = request.getSession();	    
		session.setAttribute("div", "hide");

		List<BookBean> bean = dao.treeview();
		if (bean==null)
		{
			request.getRequestDispatcher("LibraryHome.jsp").include(request, response);  
			out.print("<br><center><h4><font color='red'>Error: Could not fetch data from database</font></h4></center>"); 
		}
		else if (bean.isEmpty())
		{
			request.getRequestDispatcher("LibraryHome.jsp").include(request, response);  
			out.print("<br><center><h3><font color='red'>No Books Found!</font></h3></center>"); 
		}	
		else
		{
			request.setAttribute("treeview", bean);
			getServletConfig().getServletContext().getRequestDispatcher("/treeview.jsp").forward(request,response);
		}
	}
	

}
