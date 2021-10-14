package com.revature;

import java.sql.SQLException;
import java.util.Scanner;

import com.revature.utils.BankLoggingUtil;

/**
 * Presents the services required for manipulating the user's own account.
 * 
 * @author Rhon Vincent Ramos
 *
 */

public class AccountInfoPage implements PresentingPage
{
	public void presentPage()
	{	
		System.out.println("Presenting Account Info page...");
		userActions();
		new BankMainPage().userActions();
	}

	// "hub area" for the account management page
	public void userActions()
	{
		new BankLoggingUtil();
		BankLoggingUtil.bankLogger.info("In account page userActions...");
		
		System.out.println("Are you here to change username (A), change password (B), or change tier (C) (or enter nothing to return to main page)? -> ");
		
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
	
	// checks user's input and provides the correct service
	public void goToAction(char code)
	{	
		try
		{
			switch(code)
			{
				case 'A':
				case 'a': changeName(); break;
				case 'B':
				case 'b': changePass(); break; 
				default: System.out.println("Unrecognized action. Try again"); userActions();
			}
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
	}
	
	// allows the user to change their name in the database
	public void changeName ()
	{
		new BankLoggingUtil();
		BankLoggingUtil.bankLogger.info("In change username...");	
	
		System.out.print("What will be your new username? -> ");
		Scanner userInput = new Scanner(System.in);
		String newName = "z";
		
		newName = userInput.nextLine();
		if(newName.isEmpty())
		{
			System.out.println("No input detected. Please try again.");
			changeName();
		}
		else
		{
			System.out.println("Your new username is: " + newName);
		}
		userActions();
	}

	// allows the user to change their password
	// the password is encrypted before being stored in the database
	public void changePass () throws SQLException
	{
		new BankLoggingUtil();
		BankLoggingUtil.bankLogger.info("In change password...");	
		
		System.out.print("What will be your new password? -> ");
		Scanner userInput = new Scanner(System.in);
		String newPass = "z";
		
		newPass = userInput.nextLine();
		if(newPass.isEmpty())
		{
			System.out.println("No input detected. Please try again.");
			changeName();
		}
		else
		{
			String toEncrypt = new UserLoginPage().encryptPassword(newPass);
			new InfoSave().changePassword(UserLoginPage.getUserID(), toEncrypt);
			System.out.println("Password successfully changed. Ensure you remember it!");
		}
		userActions();
	}
}
