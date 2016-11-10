package com.banking.Entity;

import java.util.Hashtable;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

public class City implements KvmSerializable {

	private int city_id;
	private String name;
	
	public City(){
		
	}
	
	public City(int city_id, String name) {
		super();
		this.city_id = city_id;
		this.name = name;
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
		return 2;
	}

	@Override
	public void getPropertyInfo(int arg0, Hashtable arg1, PropertyInfo arg2) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void setProperty(int arg0, Object arg1) {
		switch (arg0) {
		case 0:
			city_id=Integer.parseInt(arg1.toString());
			break;
		case 1:
			name=arg1.toString();
			break;

		default:
			break;
		}
		
	}
	
	
}
