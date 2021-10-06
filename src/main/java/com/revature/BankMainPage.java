package com.revature;

import java.util.Scanner;

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
	
	public void userActions()
	{
		System.out.println("Banking (A)  Account Management (B) (or enter no value to exit.)");
		
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
			System.out.println("No input given. Exiting...");
			System.exit(0);
		}
	}
	
	public void goToPage(char code)
	{	
		switch(code)
		{
			case 'A':
			case 'a': new BankingPage().presentPage(); break;
			case 'B':
			case 'b': new AccountInfoPage().presentPage(); break; 
			case 'C':
			case 'c': new EmployeeToolsPage().presentPage(); break;
			case 'D':
			case 'd': new AdminToolsPage().presentPage(); break;
			default: System.out.println("Unrecognized action. Try again"); userActions();
		}
	}
}
