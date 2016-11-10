package com.banking.Entity;

import java.util.Hashtable;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

public class Branch implements KvmSerializable {
	private int branch_id,bank_id,city_id;
	private String name;
	
	public Branch(){
		
	}
	
	public Branch(int branch_id, int bank_id, int city_id, String name) {
		super();
		this.branch_id = branch_id;
		this.bank_id = bank_id;
		this.city_id = city_id;
		this.name = name;
	}
	
	public int getBranch_id() {
		return branch_id;
	}
	public void setBranch_id(int branch_id) {
		this.branch_id = branch_id;
	}
	public int getBank_id() {
		return bank_id;
	}
	public void setBank_id(int bank_id) {
		this.bank_id = bank_id;
	}
	public int getCity_id() {
		return city_id;
	}
	public void setCity_id(int city_id) {
		this.city_id = city_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public Object getProperty(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getPropertyCount() {
		// TODO Auto-generated method stub
		return 4;
	}

	@Override
	public void getPropertyInfo(int arg0, Hashtable arg1, PropertyInfo arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setProperty(int arg0, Object arg1) {
		switch (arg0) {
		case 0:
			bank_id=Integer.parseInt(arg1.toString());
			break;
		case 1:
			branch_id=Integer.parseInt(arg1.toString());
			break;
		case 2:
			city_id=Integer.parseInt(arg1.toString());
			break;
		case 3:
			name=arg1.toString();
			break;

		default:
			break;
		}
		
	}
		
	
}
