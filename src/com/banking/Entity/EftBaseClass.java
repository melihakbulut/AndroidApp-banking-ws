package com.banking.Entity;

public class EftBaseClass {
	protected int e_id;
	protected String a_id,transferInfo,receiverFullname,receiver_identification_no,transacDate,desc;
	protected double amount;
	
	public EftBaseClass(){
		
	}
	
	public EftBaseClass(int e_id, String a_id, String transferInfo,
			String receiverFullname, String receiver_identification_no,
			String transacDate, String desc, double amount) {
		super();
		this.e_id = e_id;
		this.a_id = a_id;
		this.transferInfo = transferInfo;
		this.receiverFullname = receiverFullname;
		this.receiver_identification_no = receiver_identification_no;
		this.transacDate = transacDate;
		this.desc = desc;
		this.amount = amount;
	}

	public int getE_id() {
		return e_id;
	}

	public void setE_id(int e_id) {
		this.e_id = e_id;
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

	public String getReceiverFullname() {
		return receiverFullname;
	}

	public void setReceiverFullname(String receiverFullname) {
		this.receiverFullname = receiverFullname;
	}

	public String getReceiver_identification_no() {
		return receiver_identification_no;
	}

	public void setReceiver_identification_no(String receiver_identification_no) {
		this.receiver_identification_no = receiver_identification_no;
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
	
}
