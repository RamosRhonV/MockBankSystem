package com.revature;

import java.sql.SQLException;
import java.util.Scanner;

import com.revature.utils.BankLoggingUtil;

public class EmployeeToolsPage implements PresentingPage
{
	public void presentPage()
	{
		System.out.println("Presenting Employee Tools page...");
		new BankMainPage().userActions();
	}
	
	public void userActions()
	{
		new BankLoggingUtil();
		BankLoggingUtil.bankLogger.info("In employee tools userActions...");	
		
		System.out.println("Tools available: change user validity (A) (or input nothing to exit). -> ");
		
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
	
	public void goToAction(char code)
	{	
		try
		{
			switch(code)
			{
				case 'A':
				case 'a': changeValidity(); break;
				default: System.out.println("Unrecognized action. Try again"); userActions();
			}
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
	}
	
	public void changeValidity() throws SQLException
	{
		new BankLoggingUtil();
		BankLoggingUtil.bankLogger.info("In change validity...");	
		
		System.out.print("Select a user's ID. -> ");
		Scanner userInput = new Scanner(System.in);
		try 
		{
			String userID = userInput.nextLine();
			System.out.println("Customer selected.");
			System.out.print("Select 1 for valid, select 0 for invalid. -> ");
			int newValid = userInput.nextInt();
			new InfoSave().changeValidity(userID, newValid);
		} 
		catch (NumberFormatException nf) 
		{
			System.out.println("Unrecognized ID. Try again...");
			changeValidity();
		}
		
		userActions();
	}
}
