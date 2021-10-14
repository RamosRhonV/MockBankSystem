package com.revature;

import java.sql.SQLException;
import java.util.Scanner;

import com.revature.utils.BankLoggingUtil;

/**
 * Presents the banking aspect of the banking app
 * Allows users to withdraw or deposit "funds" from a selected bank account
 * 
 * @author Rhon Vincent Ramos
 *
 */

public class BankingPage implements PresentingPage
{
	public void presentPage()
	{
		System.out.println("Presenting Banking page...");
		userActions();
	}

	// "hub area" within the banking service
	// allows withdrawal or depositing of "funds"
	public void userActions()
	{
		new BankLoggingUtil();
		BankLoggingUtil.bankLogger.info("In banking page userActions...");	
		
		System.out.println("Are you here to Withdraw (A) or Deposit (B) (or enter nothing to return to main page)? -> ");
		
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
			try 
			{
				goToAction(pageAccess);
			} catch (SQLException e) 
			{

				e.printStackTrace();
			}
		}
		catch (StringIndexOutOfBoundsException se) 
		{
			System.out.println("No input given. Returning to main page...");
			new BankMainPage().userActions();
		}
	}
	
	// checks user's input then sends them to correct service
	public void goToAction(char code) throws SQLException
	{	
		switch(code)
		{
			case 'A':
			case 'a': withdrawMoney(); break;
			case 'B':
			case 'b': depositMoney(); break; 
			default: System.out.println("Unrecognized action. Try again"); userActions();
		}
	}
	
	// withdraws the "money" from an account selected by user
	public void withdrawMoney() throws SQLException
	{
		new BankLoggingUtil();
		BankLoggingUtil.bankLogger.info("In withdraw money...");	
		Scanner userInput = new Scanner(System.in);

		try 
		{
			System.out.print("Enter account ID: ");
			String checkIfNum = "";
			checkIfNum = userInput.nextLine();
			int accountID = Integer.parseInt(checkIfNum);
			System.out.println("You are withdrawing from account " + accountID + ".");
			
			System.out.print("How much money are you withdrawing? -> ");
			checkIfNum = userInput.nextLine();
			int toWithdraw = Integer.parseInt(checkIfNum);
			System.out.println("You withdrew " + toWithdraw + " cash.");
			new InfoSave().withdrawMoney(UserLoginPage.userID, accountID, toWithdraw);

		} 
		catch (NumberFormatException nf) 
		{
			System.out.println("Unrecognized value. Try again...");
			withdrawMoney();
		}
		
		userActions();
	}
	
	// deposits "money" into an account selected by user
	public void depositMoney() throws SQLException
	{
		new BankLoggingUtil();
		BankLoggingUtil.bankLogger.info("In deposit money...");	
		Scanner userInput = new Scanner(System.in);

		try 
		{
			System.out.print("Enter account ID: ");
			String checkIfNum = "";
			checkIfNum = userInput.nextLine();
			int accountID = Integer.parseInt(checkIfNum);
			System.out.println("You are depositing into account " + accountID + ".");
			
			System.out.print("How much money are you depositing? -> ");
			checkIfNum = userInput.nextLine();
			int toDeposit = Integer.parseInt(checkIfNum);
			System.out.println("You deposited " + toDeposit + " cash.");
			new InfoSave().depositMoney(UserLoginPage.userID, accountID, toDeposit);

		} 
		catch (NumberFormatException nf) 
		{
			System.out.println("Unrecognized value. Try again...");
			depositMoney();
		}
		
		userActions();
	}
}
