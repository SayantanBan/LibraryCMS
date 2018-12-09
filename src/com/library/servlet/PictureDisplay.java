package com.library.servlet;

import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.library.bean.BookBean;
import com.library.dao.LibraryDAO;

/**
 * Servlet implementation class PictureProcess
 */
@WebServlet("/PictureDisplay")
public class PictureDisplay extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String name=request.getParameter("book_name");
			
		response.setContentType("image/gif");
		LibraryDAO dao=new LibraryDAO();
		
	
		{
		BookBean bean = dao.displayImage(name);
		
		
		try 
		{
			byte[] image=bean.getCoverPhoto();
			
			OutputStream o = response.getOutputStream();
			
			o.write(image);
			
			 o.flush();

			 o.close();
		} 
		catch (Exception e)
		{
			 System.out.println(e);
		}
	}
	}

}
