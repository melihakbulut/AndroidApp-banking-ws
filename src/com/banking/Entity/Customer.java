package com.banking.Entity;

import java.util.Hashtable;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

public class Customer implements KvmSerializable {

	private int id,status;
	private String fullname, mobile, mail, address, id_num,password,lastLoginIP,registerDate,lastLoginDate;
	
	public Customer(){
		//default Constructor
	}
	
	
	public Customer(int id, String fullname, String mobile, String mail,
			String address, String id_num, String registerDate,
			String lastLoginDate, String lastLoginIP,String password,int status) {
		super();
		this.id = id;
		this.fullname = fullname;
		this.mobile = mobile;
		this.mail = mail;
		this.address = address;
		this.id_num = id_num;
		this.registerDate = registerDate;
		this.lastLoginDate = lastLoginDate;
		this.lastLoginIP = lastLoginIP;
		this.password=password;
		this.status=status;
	}
		
	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getId_num() {
		return id_num;
	}
	public void setId_num(String id_num) {
		this.id_num = id_num;
	}
	public String getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}
	public String getLastLoginDate() {
		return lastLoginDate;
	}
	public void setLastLoginDate(String lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}
	public String getLastLoginIP() {
		return lastLoginIP;
	}
	public void setLastLoginIP(String lastLoginIP) {
		this.lastLoginIP = lastLoginIP;
	}


	@Override
	public Object getProperty(int arg0) {
		switch (arg0) {
		case 0:
			return address;
		case 1:
			return fullname;
		case 2:
			return id;
		case 3:
			return id_num;
		case 4:
			return lastLoginDate;
		case 5:
			return lastLoginIP;
		case 6:
			return mail;
		case 7:
			return mobile;
		case 8:
			return password;
		case 9:
			return registerDate;
		case 10:
			return status;
		default:
			break;
		}
		return null;
	}


	@Override
	public int getPropertyCount() {
		// TODO Auto-generated method stub
		return 11;
	}


	@Override
	public void getPropertyInfo(int arg0, Hashtable arg1, PropertyInfo info) {
		switch (arg0) {
		case 0:
			info.type=PropertyInfo.STRING_CLASS;
			info.name="address";
			break;
		case 1:
			info.type=PropertyInfo.STRING_CLASS;
			info.name="fullname";
			break;
		case 2:
			info.type=PropertyInfo.INTEGER_CLASS;
			info.name="id";
			break;
		case 3:
			info.type=PropertyInfo.STRING_CLASS;
			info.name="id_num";
			break;
		case 4:
			info.type=PropertyInfo.STRING_CLASS;
			info.name="lastLoginDate";
			break;
		case 5:
			info.type=PropertyInfo.STRING_CLASS;
			info.name="lastLoginIP";
			break;
		case 6:
			info.type=PropertyInfo.STRING_CLASS;
			info.name="mail";
			break;
		case 7:
			info.type=PropertyInfo.STRING_CLASS;
			info.name="mobile";
			break;
		case 8:
			info.type=PropertyInfo.STRING_CLASS;
			info.name="password";
			break;
		case 9:
			info.type=PropertyInfo.STRING_CLASS;
			info.name="registerDate";
			break;
		case 10:
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
			address=arg1.toString();
			break;
		case 1:
			fullname=arg1.toString();
			break;
		case 2:
			 id=Integer.parseInt(arg1.toString());
			 break;
		case 3:
			id_num=arg1.toString();
			break;
		case 4:
			lastLoginDate=arg1.toString();
			break;
		case 5:
			lastLoginIP=arg1.toString();
			break;
		case 6:
			mail=arg1.toString();
			break;
		case 7:
			mobile=arg1.toString();
			break;
		case 8:
			password=arg1.toString();
			break;
		case 9:
			registerDate=arg1.toString();
			break;
		case 10:
			status=Integer.parseInt(arg1.toString());
			break;
		default:
			break;
		}
		
	}
	
	

}
