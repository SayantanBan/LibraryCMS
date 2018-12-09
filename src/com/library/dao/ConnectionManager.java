package com.library.dao;

import java.sql.DriverManager;
import java.sql.Connection;

public class ConnectionManager 
{
	private static final ConnectionManager dbConnection;
	private static Connection con = null;
	
	static
	{
		dbConnection = new ConnectionManager();
	}
	
	private ConnectionManager()
	{

		try
		{
			try 
			{
				Class.forName("com.mysql.jdbc.Driver");
			} 
			catch (ClassNotFoundException e) 
			{
				e.printStackTrace();
			}
			
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_db", "root", "root");
			
			System.out.println("Connected");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}
	public static ConnectionManager getDBConnection()
	{
		return dbConnection;
	}
	public  Connection getConnection() 
	{
		return con;
		
	}
	@Override
	protected void finalize() throws Throwable
	{
		if(con!=null) 
		{
			System.out.println("closed successfully");
		con.close();
		con = null;
		}
	}
	
}

