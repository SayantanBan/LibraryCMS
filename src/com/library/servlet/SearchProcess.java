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
 * Servlet implementation class SearchProcess
 */
@WebServlet("/SearchProcess")
public class SearchProcess extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		 
		response.setContentType("text/html");
		
		HttpSession session = request.getSession();	    
		
		if (Integer.parseInt(request.getParameter("call"))==1)
		{
			String book_name = request.getParameter("book_name");
			session.setAttribute("search_string", book_name);
		}
		
		
		int pageid=Integer.parseInt(request.getParameter("page"));
		
		int total=5;
	
		if (pageid>1)
		{
			pageid--;
			pageid=(pageid*total)+1;
		}
		
	   
        PrintWriter out = response.getWriter();
        
        String book_name=(String)(session.getAttribute("search_string"));
		
        LibraryDAO dao = new LibraryDAO();
		 
		List<BookBean> book_list= dao.findBook(book_name,pageid,total);
		int page_number=dao.searchPageNumber(book_name);
		session.setAttribute("div", "hide");

		request.getRequestDispatcher("LibraryHome.jsp").include(request, response);  

		 if (book_list.isEmpty() || page_number==0)
		{
			 out.print("<br><h4><center><font color='red'>No Book Found</h4></center>");  
		}
		else
		{
			request.setAttribute("search", book_list);
			request.setAttribute("page_number", page_number);
			getServletConfig().getServletContext().getRequestDispatcher("/searchResult.jsp").forward(request,response);
		}	
	}
}



