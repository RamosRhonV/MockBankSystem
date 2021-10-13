package com.revature;

import com.revature.utils.BankLoggingUtil;

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
		new BankLoggingUtil();
		BankLoggingUtil.bankLogger.info("In presentPage method...");
		
		System.out.println("Presenting page...");
		new UserLoginPage().presentPage();
		new BankMainPage().presentPage();
	}
}
