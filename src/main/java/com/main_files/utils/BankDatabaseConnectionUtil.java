package com.revature.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Connects the program to the Bank database
 * 
 * @author Rhon Vincent Ramos
 *
 */

public class BankDatabaseConnectionUtil 
{
	public static Connection getConnection() throws SQLException
	{
		try
		{
			Class.forName("org.postgresql.Driver");
		}
		catch(ClassNotFoundException ce)
		{
			ce.printStackTrace();
		}
		
		String url = "";
		String username = "";
		String password = ""; // password for the database. please do not use for nefarious purposes :(
		
		return DriverManager.getConnection(url, username, password);
	}
}
