package com.revature;

import java.sql.SQLException;

/**
 * Presents the tools provided for admins of the bank application
 */

import java.util.Scanner;

import com.revature.utils.BankLoggingUtil;

public class AdminToolsPage implements PresentingPage
{
	public void presentPage()
	{
		System.out.println("Presenting AdminTools page...");
		userActions();
	}

	// "hub area" for bank administrators
	public void userActions()
	{
		new BankLoggingUtil();
		BankLoggingUtil.bankLogger.info("In admin tools userActions...");	
		
		System.out.print("Change an employee ID of a user (A), change an admin ID of a user (B), or delete a user's records (C) (or input nothing to exit). -> ");
		
		Scanner userInput = new Scanner(System.in);
		String userChoice = "";
		
		try
		{
			userChoice = userInput.nextLine();
			char pageAccess = 'z';
			if(userChoice != null)
			{
				pageAccess = userChoice.charAt(0);
			}
			else
			{
				System.exit(0);
			}
			System.out.println("You selected " + pageAccess);
			goToAction(pageAccess);
		}
		catch (StringIndexOutOfBoundsException se) 
		{
			System.out.println("No input given. Returning to main page...");
			new BankMainPage().userActions();
		}
	}
	
	// checks user's choice then provides the correct tool
	public void goToAction(char code)
	{	
		try
		{
			switch(code)
			{
				case 'A':
				case 'a': changeEmployeeID(); break;
				case 'B':
				case 'b': changeAdminID(); break;
				case 'C':
				case 'c': deleteUser(); break;
				default: System.out.println("Unrecognized action. Try again"); userActions();
			}
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
	}
	
	// allows admins to add, change, or remove an employee's ID
	public void changeEmployeeID() throws SQLException
	{		
		new BankLoggingUtil();
		BankLoggingUtil.bankLogger.info("In add employee id...");	
		
		System.out.print("Select a user's ID. -> ");
		Scanner userInput = new Scanner(System.in);
		try 
		{
			String userID = userInput.nextLine();
			System.out.println("User selected.");
			System.out.print("Change this user's employee ID (or input nothing to remove their employee status). -> ");
			int newID = userInput.nextInt();
			new InfoSave().changeEmployeeStatus(userID, newID);
		} 
		catch (NumberFormatException nf) 
		{
			System.out.println("Unrecognized ID. Try again...");
			changeEmployeeID();
		}
		
		userActions();
	}
	
	// allows admins to add, change, or remove an admin's ID
	public void changeAdminID() throws SQLException
	{		
		new BankLoggingUtil();
		BankLoggingUtil.bankLogger.info("In change admin id...");	
		
		System.out.print("Select a user's ID. -> ");
		Scanner userInput = new Scanner(System.in);
		try 
		{
			String userID = userInput.nextLine();
			System.out.println("User selected.");
			System.out.print("Change this user's admin ID (or input nothing to remove their admin status). -> ");
			int newID = userInput.nextInt();
			new InfoSave().changeAdminStatus(userID, newID);
		} 
		catch (NumberFormatException nf) 
		{
			System.out.println("Unrecognized ID. Try again...");
			changeEmployeeID();
		}
		
		userActions();
	}

	// allows the deletion of a user's records in the database
	public void deleteUser() throws SQLException
	{		
		new BankLoggingUtil();
		BankLoggingUtil.bankLogger.info("In delete user...");	
		
		System.out.print("Delete which user's ID. -> ");
		Scanner userInput = new Scanner(System.in);
		try 
		{
			String userID = userInput.nextLine();
			System.out.println("User selected. Deleting...");
			new InfoSave().deleteUser(userID);
		} 
		catch (NumberFormatException nf) 
		{
			System.out.println("Unrecognized ID. Try again...");
			changeEmployeeID();
		}
		
		userActions();
	}
}
