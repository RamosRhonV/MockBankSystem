package com.revature;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.revature.utils.BankDatabaseConnectionUtil;

/**
 * Controls all Java to SQL functionality, including
 * saving user account info, withdrawals, and admin control.
 * 
 * @author Rhon Vincent Ramos
 *
 */

public class InfoSave 
{
	public void saveInfo(String ID, String pass, String tier) throws SQLException
	{
		try(Connection bankConnection = BankDatabaseConnectionUtil.getConnection())
		{
			String sql = "INSERT INTO user_info (userID, encryptedpassword, usertier)"
					+    "VALUES ( " + ID + ", " + pass + ", " + tier + ");";
			Statement insertStatement = bankConnection.createStatement();
			insertStatement.execute(sql);
		}
	}
	
	public void withdrawMoney(String ID, int account, int takeOut) throws SQLException
	{
		try(Connection bankConnection = BankDatabaseConnectionUtil.getConnection())
		{
			Statement statement = bankConnection.createStatement();
			String selectSQL = "SELECT accountFunds "
					+          "FROM user_accounts"
					+          "WHERE userID = " + ID;
			ResultSet resultSelect = statement.executeQuery(selectSQL);
			
			int remainingMoney = resultSelect.getInt(0) - takeOut;
			String updateSQL = "UPDATE TABLE user_accounts "
					+          "SET accountFunds = " + remainingMoney
					+          "WHERE accountID = " + account;
			ResultSet remainingFunds = statement.executeQuery(updateSQL);
			
			System.out.println("You have " +  remainingFunds.getInt(0) + " left in your account.");
		}
	}
	
	public void depositMoney(String ID, int account, int putIn) throws SQLException
	{
		try(Connection bankConnection = BankDatabaseConnectionUtil.getConnection())
		{
			Statement statement = bankConnection.createStatement();
			String selectSQL = "SELECT accountFunds "
					+          "FROM user_accounts"
					+          "WHERE userID = " + ID;
			ResultSet resultSelect = statement.executeQuery(selectSQL);
			
			int remainingMoney = resultSelect.getInt(0) + putIn;
			String updateSQL = "UPDATE TABLE user_accounts "
					+          "SET accountFunds = " + remainingMoney
					+          "WHERE accountID = " + account;
			ResultSet remainingFunds = statement.executeQuery(updateSQL);
			
			System.out.println("You have " +  remainingFunds.getInt(0) + " left in your account.");
		}
	}
	
	public void changeUserName(String ID, int newName) throws SQLException
	{
		try(Connection bankConnection = BankDatabaseConnectionUtil.getConnection())
		{
			
			String updateSQL = "UPDATE TABLE user_info "
					+          "SET username = " + newName 
					+          "WHERE userID = " + ID;
			Statement statement = bankConnection.createStatement();
			statement.executeQuery(updateSQL);
			
			System.out.println("Your new username is " + newName + "!");
		}
	}
	
	public void changePassword(String ID, String newPass) throws SQLException
	{
		try(Connection bankConnection = BankDatabaseConnectionUtil.getConnection())
		{
			
			String updateSQL = "UPDATE TABLE user_info "
					+          "SET encryptedpassword = " + newPass 
					+          "WHERE userID = " + ID;
			Statement statement = bankConnection.createStatement();
			statement.executeQuery(updateSQL);
			
			System.out.println("Password change success!");
		}
	}
	
	public void changeValidity(String ID, int validity) throws SQLException
	{
		try(Connection bankConnection = BankDatabaseConnectionUtil.getConnection())
		{
			
			String updateSQL = "UPDATE TABLE user_accounts "
					+          "SET validUserID = " + validity
					+          "WHERE userID = " + ID;
			Statement statement = bankConnection.createStatement();
			statement.executeQuery(updateSQL);
			
			System.out.println("Validity changed!");
		}
	}
	
	public void changeEmployeeStatus(String ID, int eID) throws SQLException
	{
		try(Connection bankConnection = BankDatabaseConnectionUtil.getConnection())
		{
			
			String updateSQL = "UPDATE TABLE bank_staff_accounts "
					+          "SET bankEmployeeID = " + eID
					+          "WHERE userID = " + ID;
			Statement statement = bankConnection.createStatement();
			statement.executeQuery(updateSQL);
			
			System.out.println("Validity changed!");
		}
	}
	
	public void changeAdminStatus(String ID, int eID) throws SQLException
	{
		try(Connection bankConnection = BankDatabaseConnectionUtil.getConnection())
		{
			
			String updateSQL = "UPDATE TABLE bank_staff_accounts "
					+          "SET bankAdminID = " + eID
					+          "WHERE userID = " + ID;
			Statement statement = bankConnection.createStatement();
			statement.executeQuery(updateSQL);
			
			System.out.println("Validity changed!");
		}
	}
}
