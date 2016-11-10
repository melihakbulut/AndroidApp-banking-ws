package com.banking.Entity;

import java.util.Hashtable;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

public class TransferBasedOnNumber implements KvmSerializable {
	protected int t_id,transferType;
	protected String a_id,transferInfo,transacDate,desc;
	protected double amount;
	
	public TransferBasedOnNumber(){
		
	}
	
	public TransferBasedOnNumber(int t_id, int transferType, String a_id,
			String transferInfo, String transacDate, String desc, double amount) {
		super();
		this.t_id = t_id;
		this.transferType = transferType;
		this.a_id = a_id;
		this.transferInfo = transferInfo;
		this.transacDate = transacDate;
		this.desc = desc;
		this.amount = amount;
	}

	public int getT_id() {
		return t_id;
	}

	public void setT_id(int t_id) {
		this.t_id = t_id;
	}

	public int getTransferType() {
		return transferType;
	}

	public void setTransferType(int transferType) {
		this.transferType = transferType;
	}

	public String getA_id() {
		return a_id;
	}

	public void setA_id(String a_id) {
		this.a_id = a_id;
	}

	public String getTransferInfo() {
		return transferInfo;
	}

	public void setTransferInfo(String transferInfo) {
		this.transferInfo = transferInfo;
	}

	public String getTransacDate() {
		return transacDate;
	}

	public void setTransacDate(String transacDate) {
		this.transacDate = transacDate;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
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
			return desc;
		case 3:
			return t_id;
		case 4:
			return transacDate;
		case 5:
			return transferInfo;
		case 6:
			return transferType;
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
			info.name="desc";
			break;
		case 3:
			info.type=PropertyInfo.INTEGER_CLASS;
			info.name="t_id";
			break;
		case 4:
			info.type=PropertyInfo.STRING_CLASS;
			info.name="transacDate";
			break;
		case 5:
			info.type=PropertyInfo.STRING_CLASS;
			info.name="transferInfo";
			break;
		case 6:
			info.type=PropertyInfo.INTEGER_CLASS;
			info.name="transferType";
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
			desc=arg1.toString();
			break;
		case 3:
			t_id=Integer.parseInt(arg1.toString());
			break;
		case 4:
			transacDate=arg1.toString();
			break;
		case 5:
			transferInfo=arg1.toString();
			break;
		case 6:
			transferType=Integer.parseInt(arg1.toString());
			break;
		default:
			break;
		}
		
	}
	
}
