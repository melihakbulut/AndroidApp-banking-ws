package com.banking.Entity;

import java.util.Date;
import java.util.Hashtable;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import com.banking.soap.MarshalDouble;

public class CheckingAccount extends BaseAccount implements KvmSerializable {
	
	private String IBAN;
	private double dailyTransferLimit;

	public CheckingAccount(){
		
	}
	
	public CheckingAccount(String a_id, int c_id, int currency, double balance,
			String creationDate, int status,String IBAN,double dailyTransferLimit) {
		super(a_id, c_id, currency, balance, creationDate, status);
		this.IBAN=IBAN;
		this.dailyTransferLimit=dailyTransferLimit;
	}
	

	public String getIBAN() {
		return IBAN;
	}

	public void setIBAN(String iBAN) {
		IBAN = iBAN;
	}

	public double getDailyTransferLimit() {
		return dailyTransferLimit;
	}

	public void setDailyTransferLimit(double dailyTransferLimit) {
		this.dailyTransferLimit = dailyTransferLimit;
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
			return dailyTransferLimit;
		case 7:
			return IBAN;
		default:
			break;
		}
		return null;
	
	}

	@Override
	public int getPropertyCount() {
		// TODO Auto-generated method stub
		return 8;
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
			info.type=Double.class;
			info.name="dailyTransferLimit";
			break;
		case 7:
			info.type=PropertyInfo.STRING_CLASS;
			info.name="IBAN";
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
			dailyTransferLimit=Double.parseDouble(arg1.toString());
			break;
		case 7:
			IBAN=arg1.toString();
			break;
		default:
			break;
		}
		
	}
	
	

	
}
