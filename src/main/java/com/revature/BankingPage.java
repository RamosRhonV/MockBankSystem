package com.revature;

public class BankingPage implements PresentingPage
{
	public void presentPage()
	{
		System.out.println("Presenting Banking page...");
		new BankMainPage().userActions();
	}

	public void userActions()
	{
		
	}
}
