package com.library.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.library.dao.LibraryDAO;

/**
 * Servlet implementation class AddBookForm
 */
@WebServlet("/AddBookForm")
public class AddBookForm extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{

		LibraryDAO dao= new LibraryDAO();
		try
		{
			List<String> bean = dao.getCategory();
			request.setAttribute("category", bean);
			HttpSession session = request.getSession();	    
			session.setAttribute("div", "hide");

			getServletConfig().getServletContext().getRequestDispatcher("/addBook.jsp").forward(request,response);
			
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		
	}
}
