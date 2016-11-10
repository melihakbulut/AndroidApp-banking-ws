package com.banking.Entity;

import java.util.Hashtable;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

public class CustomerCard implements KvmSerializable {

	private String card_id,password;
	private int c_id,cardType,cardStatus,cardClass;
	private double cardLimit;
	
	public CustomerCard(){
		
	}
	public CustomerCard(String card_id, int c_id, String password,
			int cardType, int cardStatus, double cardLimit,int cardClass) {
		super();
		this.card_id = card_id;
		this.c_id = c_id;
		this.password = password;
		this.cardType = cardType;
		this.cardStatus = cardStatus;
		this.cardLimit = cardLimit;
		this.cardClass=cardClass;
	}
	
	public int getCardClass() {
		return cardClass;
	}
	public void setCardClass(int cardClass) {
		this.cardClass = cardClass;
	}
	public String getCard_id() {
		return card_id;
	}
	public void setCard_id(String card_id) {
		this.card_id = card_id;
	}
	public int getC_id() {
		return c_id;
	}
	public void setC_id(int c_id) {
		this.c_id = c_id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getCardType() {
		return cardType;
	}
	public void setCardType(int cardType) {
		this.cardType = cardType;
	}
	public int getCardStatus() {
		return cardStatus;
	}
	public void setCardStatus(int cardStatus) {
		this.cardStatus = cardStatus;
	}
	public double getCardLimit() {
		return cardLimit;
	}
	public void setCardLimit(double cardLimit) {
		this.cardLimit = cardLimit;
	}
	@Override
	public Object getProperty(int arg0) {
		switch (arg0) {
		case 0:
			return c_id;
		case 1:
			return cardClass;
		case 2:
			return cardLimit;
		case 3:
			return cardStatus;
		case 4:
			return cardType;
		case 5:
			return card_id;
		case 6:
			return password;
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
			info.type=PropertyInfo.INTEGER_CLASS;
			info.name="c_id";
			break;
		case 1:
			info.type=PropertyInfo.INTEGER_CLASS;
			info.name="cardClass";
			break;
		case 2:
			info.type=Double.class;
			info.name="cardLimit";
			break;
		case 3:
			info.type=PropertyInfo.INTEGER_CLASS;
			info.name="cardStatus";
			break;
		case 4:
			info.type=PropertyInfo.INTEGER_CLASS;
			info.name="cardType";
			break;
		case 5:
			info.type=PropertyInfo.STRING_CLASS;
			info.name="card_id";
			break;
		case 6:
			info.type=PropertyInfo.STRING_CLASS;
			info.name="password";
			break;
		default:
			break;
		}
		
	}
	@Override
	public void setProperty(int arg0, Object arg1) {
		switch (arg0) {
		case 0:
			c_id=Integer.parseInt(arg1.toString());
			break;
		case 1:
			cardClass=Integer.parseInt(arg1.toString());
			break;
		case 2:
			cardLimit=Double.parseDouble(arg1.toString());
			break;
		case 3:
			cardStatus=Integer.parseInt(arg1.toString());
			break;
		case 4:
			cardType=Integer.parseInt(arg1.toString());
			break;
		case 5:
			card_id=arg1.toString();
			break;
		case 6:
			password=arg1.toString();
			break;
		default:
			break;
		}
		
	}
	
}
