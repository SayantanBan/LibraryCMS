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

import com.library.dao.LibraryDAO;

/**
 * Servlet implementation class CatalogueProcess
 */
@WebServlet("/CatalogueProcess")
public class CatalogueProcess extends HttpServlet
{
	
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		PrintWriter out=response.getWriter();
		
		int pageid=Integer.parseInt(request.getParameter("page"));
		
		int total=5;
		if (pageid>1)
		{
			pageid--;
			pageid=(pageid*total)+1;
		}
		
		LibraryDAO dao=new LibraryDAO();
	
		List<String> books = dao.bookList(pageid,total);
		int page_number=dao.cataloguePageNumber();
		HttpSession session = request.getSession();	    
		session.setAttribute("div", "hide");

		if (books==null || page_number==-1)
		{
			request.getRequestDispatcher("LibraryHome.jsp").include(request, response);  
			out.print("<br><center><h4><font color='red'>Error: Could not fetch data from database</font></h4></center>"); 
		}
		else if (books.isEmpty())
		{
			request.getRequestDispatcher("LibraryHome.jsp").include(request, response);  
			out.print("<br><center><h3><font color='red'>No Books Found!</font></h3></center>"); 
		}
		else
		{
			request.setAttribute("catalogue", books);
			request.setAttribute("pages", page_number);
					
			getServletConfig().getServletContext().getRequestDispatcher("/BookCatalogue.jsp").forward(request,response);

		}
		
		
	}	
}
