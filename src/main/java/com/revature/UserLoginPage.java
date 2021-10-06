package com.revature;

import java.util.Scanner;

/**
 * Presents the user login to the user.
 * 
 * @author Ronnie
 *
 */

public class UserLoginPage implements PresentingPage
{
	String userName = "";
	String userPass = "";
	String userTier = "";

	UserInfo userInfo = new UserInfo(userName, userPass, userTier);
	
	// presents the user login page
	public void presentPage()
	{
		System.out.println("Presenting User Login page...");
		userActions();
	}
	
	// presents login dialogue then presents username and password inputs
	public void userActions()
	{
		Scanner userInput = new Scanner(System.in);
		
		System.out.println("Welcome to CMD Bank!\nEnter your credentials, below.");
		
		System.out.print("Username: ");
		userName = userInput.nextLine();
		
		System.out.print("Password: ");
		userPass = userInput.nextLine();
		
		System.out.print("Tier: ");
		userTier = userInput.nextLine();
		
		System.out.println("Thank you! We are now logging you in...");
	}
	
	// saves user data to sql database
	public void saveData()
	{
		// resets name and pass to initial status
		userName = "";
		userPass = "";
	}
}
