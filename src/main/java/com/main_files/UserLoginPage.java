package com.revature;

import java.sql.SQLException;
import java.util.Scanner;

import com.revature.utils.BankLoggingUtil;

/**
 * Presents the user login to the user.
 * 
 * @author Ronnie
 *
 */

public class UserLoginPage implements PresentingPage
{
	static String userID = "";
	static String userPass = "";
	static String userTier = "";

	static UserInfo userInfo = new UserInfo(userID, userPass, userTier);
	
	// presents the user login page
	public void presentPage()
	{
		new BankLoggingUtil();
		BankLoggingUtil.bankLogger.info("In login page presentPage...");
		
		System.out.println("Presenting User Login page...");
		userActions();
	}
	
	// presents login dialogue then presents username and password inputs
	public void userActions()
	{
		new BankLoggingUtil();
		BankLoggingUtil.bankLogger.info("In login page userActions...");
		
		Scanner userInput = new Scanner(System.in);
		
		System.out.println("Welcome to CMD Bank!\nEnter your credentials, below.");
		
		System.out.print("ID: ");
		userID = userInput.nextLine();
		userInfo.setUserID(userID);
		
		System.out.print("Password: ");
		userPass = userInput.nextLine();
		String encrypted = encryptPassword(userPass);
		userInfo.setUserPassword(encrypted);
		
		System.out.print("Tier: ");
		userTier = userInput.nextLine();
		userInfo.setUserTier(userTier);
		
		System.out.println("Thank you! We are now logging you in...");
		
		// saves user data to sql database
		try
		{
			new InfoSave().saveInfo(userInfo.userID, userInfo.userPassword, userInfo.userTier);
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
	}
	
	public String encryptPassword(String pass)
	{
		String encryptedPass = "";
		char selectedChar = 'z';
		
		for(int x = pass.length() - 1; x >= 0; x--)
		{
			selectedChar = (char) (pass.charAt(x) + 1);
			encryptedPass += selectedChar;
		}
		
		return encryptedPass;
	}
	
	public static String getUserID()
	{
		return userInfo.userID;
	}
	
	public static String getUserTier()
	{
		return userInfo.userTier;
	}
}
