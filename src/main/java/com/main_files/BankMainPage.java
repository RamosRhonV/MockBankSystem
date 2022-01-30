package com.revature;

import java.util.Scanner;

import com.revature.utils.BankLoggingUtil;

/**
 * This is the "main hub" of the Bank program. The user
 * must return to this page to access other available
 * actions.
 * 
 * @author Ronnie
 *
 */

public class BankMainPage implements PresentingPage
{
	public void presentPage()
	{
		System.out.println("Presenting Bank Main Page...");
		System.out.println("Welcome to CMD Bank's main page!\nWhat would you like to do?");
		
		userActions();
	}
	
	// "hub area" where the user returns to after using a service
	// presents choice of services based on user tier
	public void userActions()
	{
		new BankLoggingUtil();
		BankLoggingUtil.bankLogger.info("In main page userActions...");
		
		String tierSelect = UserLoginPage.getUserTier();
		
		switch (tierSelect)
		{
			case "3": System.out.print("Admin Page (A), ");
			case "2": System.out.print("Employee Page(B), ");
			case "1": System.out.println("Banking (C)  Account Management (D) (or enter no value to exit.)"); break;
			default: System.out.println("Unknown user detected."); System.exit(0);
		}
		
		Scanner userInput = new Scanner(System.in);
		String userChoice = "";
		
		System.out.print("What is your action? -> ");
		
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
			goToPage(pageAccess);
		}
		catch (StringIndexOutOfBoundsException se) 
		{
			System.out.println("No input given. Thanks for banking with us! Exiting...");
			System.exit(0);
		}
	}
	
	// checks user's choice and presents the correct service
	public void goToPage(char code)
	{	
		switch(code)
		{
			case 'A':
			case 'a': new AdminToolsPage().presentPage(); break; 
			case 'B':
			case 'b': new EmployeeToolsPage().presentPage(); break;
			case 'C':
			case 'c': new BankingPage().presentPage(); break; 
			case 'D':
			case 'd': new AccountInfoPage().presentPage(); break;
			default: System.out.println("Unrecognized action. Try again"); userActions();
		}
	}
}
