package com.revature;

/**
 * Presents a page to the user based on their status
 * in the program.
 * 
 * @author Rhon Vincent Ramos
 *
 */

public class PresentPage
{	
	// presents a specific page to the user
	public void presentPage()
	{
		System.out.println("Presenting page...");
		new UserLoginPage().presentPage();
		new BankMainPage().presentPage();
	}
}
