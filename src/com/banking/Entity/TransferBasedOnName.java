package com.banking.Entity;

import java.util.Hashtable;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

public class TransferBasedOnName extends TransferBasedOnNumber implements KvmSerializable {

	private String receiverFullname,address;
	
	public TransferBasedOnName(){
		
	}
	
	public TransferBasedOnName(int t_id, int transferType, String a_id,
			String transferInfo, String transacDate, String desc, double amount,
			String receiverFullname,String address) {
		super(t_id, transferType, a_id, transferInfo, transacDate, desc, amount);
		
		this.receiverFullname=receiverFullname;
		this.address=address;
	}

	public String getReceiverFullname() {
		return receiverFullname;
	}

	public void setReceiverFullname(String receiverFullname) {
		this.receiverFullname = receiverFullname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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
		case 7:
			return address;
		case 8:
			return receiverFullname;
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
		case 7:
			info.type=PropertyInfo.STRING_CLASS;
			info.name="address";
			break;
		case 8:
			info.type=PropertyInfo.STRING_CLASS;
			info.name="receiverFullname";
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
		case 7:
			address=arg1.toString();
			break;
		case 8:
			receiverFullname=arg1.toString();
			break;
		default:
			break;
		}
		
	}

	
}
