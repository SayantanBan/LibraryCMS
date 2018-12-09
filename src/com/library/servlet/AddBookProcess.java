package com.library.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.library.bean.BookBean;
import com.library.dao.LibraryDAO;

/**
 * Servlet implementation class AddBookProcess
 */
@WebServlet("/AddBookProcess")
@MultipartConfig(maxFileSize = 16177215) 
public class AddBookProcess extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		PrintWriter out=response.getWriter();
		String author="author1";
		int i=1;
		List<String> authors=new ArrayList<String>();

		String book_name=request.getParameter("book_name");
		while (request.getParameter(author)!=null)
		{
			authors.add(request.getParameter(author));
			i++;
			author="author"+ Integer.toString(i);
		}
		String publisher = request.getParameter("publisher");
		String category = request.getParameter("category");
		String quantity= request.getParameter("quantity");
		InputStream inputStream = null;
		Part filePart = request.getPart("cover");
        if (filePart != null)
        {
            inputStream = filePart.getInputStream();
        }
         
        BookBean bean = new BookBean();
		
		LibraryDAO dao = new LibraryDAO();
		
		try
		{
			bean.setBookName(book_name);
			bean.setAuthor(authors);
			bean.setPublisher(publisher);
			bean.setCategory(category);
			bean.setCover(inputStream);
			bean.setQuantity(Integer.parseInt(quantity));
			
			String add_status=dao.add(bean);
			request.setAttribute("div", "hide");

			request.getRequestDispatcher("LibraryHome.jsp").include(request, response);  
			System.out.println(add_status);
			if (add_status.equals("error"))
			{
				out.print("<br><center><h4><font color='red'>Error: Could not fetch data from database</font></h4></center>"); 
			}
			else if (add_status.equals("Added"))
			{
				out.print("<br><center><font color='red'>Book Added!</font></center>");  
			}
			else
			{
				out.print("<br><center><font color='red'>Book Already Exists!</font></center>");  
			}
			
		}
		catch (Exception e)
		{
			System.out.println(e);
			request.getRequestDispatcher("LibraryHome.jsp").include(request, response);  
			out.print("<br><center><font color='red'>Book Could Not Be Added!</font></center>");  
		}
	}

}
