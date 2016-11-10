package com.banking.Entity;

import java.util.Hashtable;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

public class Repo implements KvmSerializable {

	private int r_id, status;
	private String a_id, startDate, endDate;
	private double amount, interestRate;

	public Repo() {

	}

	public Repo(int r_id, int status, String a_id, String startDate,
			String endDate, double amount, double interestRate) {
		super();
		this.r_id = r_id;
		this.status = status;
		this.a_id = a_id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.amount = amount;
		this.interestRate = interestRate;
	}

	public int getR_id() {
		return r_id;
	}

	public void setR_id(int r_id) {
		this.r_id = r_id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getA_id() {
		return a_id;
	}

	public void setA_id(String a_id) {
		this.a_id = a_id;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
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
			return amount;
		case 2:
			return endDate;
		case 3:
			return interestRate;
		case 4:
			return r_id;
		case 5:
			return startDate;
		case 6:
			return status;
		default:
			break;
		}
		return null;
	}

	@Override
	public int getPropertyCount() {
		// TODO Auto-generated method stub
		return 7;
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
			info.name="amount";
			break;
		case 2:
			info.type=PropertyInfo.STRING_CLASS;
			info.name="endDate";
			break;
		case 3:
			info.type=Double.class;
			info.name="interestRate";
			break;
		case 4:
			info.type=PropertyInfo.INTEGER_CLASS;
			info.name="r_id";
			break;
		case 5:
			info.type=PropertyInfo.STRING_CLASS;
			info.name="startDate";
			break;
		case 6:
			info.type=PropertyInfo.INTEGER_CLASS;
			info.name="status";
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
			amount=Double.parseDouble(arg1.toString());
			break;
		case 2:
			endDate=arg1.toString();
			break;
		case 3:
			interestRate=Double.parseDouble(arg1.toString());
			break;
		case 4:
			r_id=Integer.parseInt(arg1.toString());
			break;
		case 5:
			startDate=arg1.toString();
			break;
		case 6:
			status=Integer.parseInt(arg1.toString());;
			break;
		default:
			break;
		}
		
	}

}
