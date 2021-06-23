package com.nagarro.java.fresher.training.modals;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@SuppressWarnings("serial")
@Entity
@Table
public class User{
	
	@Id 
	int userID;
	String password;
	
	public int getUserID() {
		return userID;
	}
	@Override
	public String toString() {
		return "User [userID=" + userID + ", password=" + password + "]";
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public User() {
		
	}
	public User(int userID,  String password) {
		this.userID = userID;
		this.password = password;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
