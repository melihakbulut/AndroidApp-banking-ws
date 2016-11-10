package com.banking.Entity;

import java.util.Hashtable;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

public class DepositAccount extends BaseAccount implements KvmSerializable {

	private int accountType,av_id;
	private String term;//in terms of Day
	private double interestRate;
	
	public DepositAccount(){
		
	}
	
	public DepositAccount(String a_id, int c_id, int currency, double balance,
			String creationDate, int status,int accountType,String term,double interestRate,int av_id) {
		super(a_id, c_id, currency, balance, creationDate, status);
		// TODO Auto-generated constructor stub
		this.av_id=av_id;
		this.accountType=accountType;
		this.term=term;
		this.interestRate=interestRate;
	}
	
	public int getAv_id() {
		return av_id;
	}

	public void setAv_id(int av_id) {
		this.av_id = av_id;
	}
	public int getAccountType() {
		return accountType;
	}

	public void setAccountType(int accountType) {
		this.accountType = accountType;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

	@Override
	public Object getProperty(int arg0) {
		switch (arg0) {
		case 0:
			return a_id;
		case 1:
			return balance;
		case 2:
			return c_id;
		case 3:
			return creationDate;
		case 4:
			return currency;
		case 5:
			return status;
		case 6:
			return accountType;
		case 7:
			return av_id;
		case 8:
			return interestRate;
		case 9:
			return term;
		default:
			break;
		}
		return null;
	}

	@Override
	public int getPropertyCount() {
		// TODO Auto-generated method stub
		return 10;
	}

	@Override
	public void getPropertyInfo(int arg0, Hashtable arg1, PropertyInfo info) {
		switch (arg0) {
		case 0:
			info.type=PropertyInfo.STRING_CLASS;
			info.name="a_id";
			break;
		case 1:
			info.type=Double.class;
			info.name="balance";
			break;
		case 2:
			info.type=PropertyInfo.INTEGER_CLASS;
			info.name="c_id";
			break;
		case 3:
			info.type=PropertyInfo.STRING_CLASS;
			info.name="creationDate";
			break;
		case 4:
			info.type=PropertyInfo.INTEGER_CLASS;
			info.name="currency";
			break;
		case 5:
			info.type=PropertyInfo.INTEGER_CLASS;
			info.name="status";
			break;
		case 6:
			info.type=PropertyInfo.INTEGER_CLASS;
			info.name="accountType";
			break;
		case 7:
			info.type=PropertyInfo.INTEGER_CLASS;
			info.name="av_id";
			break;
		case 8:
			info.type=Double.class;
			info.name="interestRate";
			break;
		case 9:
			info.type=PropertyInfo.STRING_CLASS;
			info.name="term";
			break;
		default:
			break;
		}
		
	}

	@Override
	public void setProperty(int arg0, Object arg1) {
		switch (arg0) {
		case 0:
			a_id=arg1.toString();
			break;
		case 1:
			balance=Double.parseDouble(arg1.toString());
			break;
		case 2:
			c_id=Integer.parseInt(arg1.toString());
			break;
		case 3:
			creationDate=arg1.toString();
			break;
		case 4:
			currency=Integer.parseInt(arg1.toString());
			break;
		case 5:
			status=Integer.parseInt(arg1.toString());
			break;
		case 6:
			accountType=Integer.parseInt(arg1.toString());
			break;
		case 7:
			av_id=Integer.parseInt(arg1.toString());
			break;
		case 8:
			interestRate=Double.parseDouble(arg1.toString());
			break;
		case 9:
			term=arg1.toString();
			break;
		default:
			break;
		}
		
	}
	
	

}
