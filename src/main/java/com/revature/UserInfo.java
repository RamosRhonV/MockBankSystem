package com.revature;

/**
 * Holds user metadata as they travel through the Banking program.
 * Values stored are visible across all classes.
 * 
 * @author Ronnie
 *
 */

public class UserInfo 
{
	String userID;
	String userPassword;
	String userTier;
	
	public UserInfo(String ID, String pass, String tier)
	{
		userID = ID;
		userPassword = pass;
		userTier = tier;
	}
	
	public void setUserID(String ID)
	{
		this.userID = ID;
	}

	public void setUserPassword(String pass)
	{
		this.userPassword = pass;
	}
	
	public void setUserTier(String tier)
	{
		this.userTier = tier;
	}
}
