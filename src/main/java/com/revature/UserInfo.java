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
	String userName;
	String userPassword;
	String userTier;
	
	public UserInfo(String name, String pass, String tier)
	{
		userName = name;
		userPassword = pass;
		userTier = tier;
	}
}
