package com.banking.Entity;

import java.util.Date;

public class BaseAccount {

	protected String a_id;
	protected int c_id,currency;
	protected double balance;
	protected String creationDate;
	protected int status;
	
	public BaseAccount(){
		
	}
		
	public BaseAccount(String a_id, int c_id, int currency, double balance,
			String creationDate, int status) {
		super();
		this.a_id = a_id;
		this.c_id = c_id;
		this.currency = currency;
		this.balance = balance;
		this.creationDate = creationDate;
		this.status = status;
	}
	
	public String getA_id() {
		return a_id;
	}
	public void setA_id(String a_id) {
		this.a_id = a_id;
	}
	public int getC_id() {
		return c_id;
	}
	public void setC_id(int c_id) {
		this.c_id = c_id;
	}
	public int getCurrency() {
		return currency;
	}
	public void setCurrency(int currency) {
		this.currency = currency;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public String getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
}
