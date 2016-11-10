package com.banking.Entity;

import java.util.Hashtable;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

public class Installment implements KvmSerializable {
	private int i_id, status;
	private String card_id, company, term, transcDate,productName;
	private Double amount;
	
	public Installment(){
		
	}

	public Installment(int i_id, int status, String card_id, String company,
			String term, String transcDate, Double amount,String productName) {
		super();
		this.i_id = i_id;
		this.status = status;
		this.card_id = card_id;
		this.company = company;
		this.term = term;
		this.transcDate = transcDate;
		this.amount = amount;
		this.productName=productName;
	}
	

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getI_id() {
		return i_id;
	}

	public void setI_id(int i_id) {
		this.i_id = i_id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getCard_id() {
		return card_id;
	}

	public void setCard_id(String card_id) {
		this.card_id = card_id;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public String getTranscDate() {
		return transcDate;
	}

	public void setTranscDate(String transcDate) {
		this.transcDate = transcDate;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	@Override
	public Object getProperty(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getPropertyCount() {
		// TODO Auto-generated method stub
		return 8;
	}

	@Override
	public void getPropertyInfo(int arg0, Hashtable arg1, PropertyInfo arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setProperty(int arg0, Object arg1) {
		switch (arg0) {
		case 0:
			amount=Double.parseDouble(arg1.toString());
			break;
		case 1:
			card_id=arg1.toString();
			break;
		case 2:
			company=arg1.toString();
			break;
		case 3:
			i_id=Integer.parseInt(arg1.toString());
			break;
		case 4:
			productName=arg1.toString();
			break;
		case 5:
			status=Integer.parseInt(arg1.toString());
			break;
		case 6:
			term=arg1.toString();
			break;
		case 7:
			transcDate=arg1.toString();
			break;
		default:
			break;
		}
		
	}

}
