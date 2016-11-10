package com.banking.Entity;

import java.util.Hashtable;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

public class EftBasedOnNumber extends EftBaseClass implements KvmSerializable {

	private int transferType;
	
	public EftBasedOnNumber(){
		
	}
	
	public EftBasedOnNumber(int e_id, String a_id, String transferInfo,
			String receiverFullname, String receiver_identification_no,
			String transacDate, String desc, double amount,int transferType) {
		super(e_id, a_id, transferInfo, receiverFullname, receiver_identification_no,
				transacDate, desc, amount);
			
			this.transferType=transferType;
	}

	public int getTransferType() {
		return transferType;
	}

	public void setTransferType(int transferType) {
		this.transferType = transferType;
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
			return e_id;
		case 4:
			return receiverFullname;
		case 5:
			return receiver_identification_no;
		case 6:
			return transacDate;
		case 7:
			return transferInfo;
		case 8:
			return transferType;
		default:
			break;
		}

		return null;
	}

	@Override
	public int getPropertyCount() {
		// TODO Auto-generated method stub
		return 9;
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
			info.name="e_id";
			break;
		case 4:
			info.type=PropertyInfo.STRING_CLASS;
			info.name="receiverFullname";
			break;
		case 5:
			info.type=PropertyInfo.STRING_CLASS;
			info.name="receiver_identification_no";
			break;
		case 6:
			info.type=PropertyInfo.STRING_CLASS;
			info.name="transacDate";
			break;
		case 7:
			info.type=PropertyInfo.STRING_CLASS;
			info.name="transferInfo";
			break;
		case 8:
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
			e_id=Integer.parseInt(arg1.toString());
			break;
		case 4:
			receiverFullname=arg1.toString();
			break;
		case 5:
			receiver_identification_no=arg1.toString();
			break;
		case 6:
			transacDate=arg1.toString();
			break;
		case 7:
			transferInfo=arg1.toString();
			break;
		case 8:
			transferType=Integer.parseInt(arg1.toString());
			break;
		default:
			break;
		}
		
	}

}
