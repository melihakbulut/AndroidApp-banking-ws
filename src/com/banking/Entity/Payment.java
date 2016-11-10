package com.banking.Entity;

import java.util.Hashtable;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

public class Payment implements KvmSerializable {

	private int p_id,paymentType;
	private String a_id,paymentInfo,transcDate;
	private double amount;
	
	public Payment(){
		
	}
	
	public Payment(int p_id, int paymentType, String a_id, String paymentInfo,
			String transcDate, double amount) {
		super();
		this.p_id = p_id;
		this.paymentType = paymentType;
		this.a_id = a_id;
		this.paymentInfo = paymentInfo;
		this.transcDate = transcDate;
		this.amount = amount;
	}

	public int getP_id() {
		return p_id;
	}

	public void setP_id(int p_id) {
		this.p_id = p_id;
	}

	public int getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(int paymentType) {
		this.paymentType = paymentType;
	}

	public String getA_id() {
		return a_id;
	}

	public void setA_id(String a_id) {
		this.a_id = a_id;
	}

	public String getPaymentInfo() {
		return paymentInfo;
	}

	public void setPaymentInfo(String paymentInfo) {
		this.paymentInfo = paymentInfo;
	}

	public String getTranscDate() {
		return transcDate;
	}

	public void setTranscDate(String transcDate) {
		this.transcDate = transcDate;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Override
	public Object getProperty(int arg0) {
		switch (arg0) {
		case 0:
			return a_id;
		case 1:
			return amount;
		case 2:
			return p_id;
		case 3:
			return paymentInfo;
		case 4:
			return paymentType;
		case 5:
			return transcDate;
		default:
			break;
		}
		return null;
	}

	@Override
	public int getPropertyCount() {
		// TODO Auto-generated method stub
		return 6;
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
			info.type=PropertyInfo.INTEGER_CLASS;
			info.name="p_id";
			break;
		case 3:
			info.type=PropertyInfo.STRING_CLASS;
			info.name="paymentInfo";
			break;
		case 4:
			info.type=PropertyInfo.INTEGER_CLASS;
			info.name="paymentType";
			break;
		case 5:
			info.type=PropertyInfo.STRING_CLASS;
			info.name="transcDate";
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
			p_id=Integer.parseInt(arg1.toString());
			break;
		case 3:
			paymentInfo=arg1.toString();
			break;
		case 4:
			paymentType=Integer.parseInt(arg1.toString());
			break;
		case 5:
			transcDate=arg1.toString();
			break;
		default:
			break;
		}
		
	}
	
	
}
