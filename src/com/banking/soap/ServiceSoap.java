package com.banking.soap;

import java.util.ArrayList;
import java.util.List;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import com.banking.Entity.CheckingAccount;
import com.banking.Entity.DepositAccount;

import android.util.Log;


public class ServiceSoap extends SoapClass {

	public boolean checkService(CheckingAccount c){
		  METHOD_NAME="checkService";
		  boolean status=false;
		  SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
		  request.addProperty("arg0", c);
		  
		  SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		    envelope.setOutputSoapObject(request);
		    MarshalDouble md=new MarshalDouble();
		    md.register(envelope);

		    try {

			    HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
			    androidHttpTransport.debug = true;
				androidHttpTransport.call(SOAP_ACTION, envelope);
				
				SoapPrimitive message = (SoapPrimitive) envelope.getResponse();
				
				if(message!=null){
					status=Boolean.parseBoolean(message.toString());		
				}
					
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		  
		  return status;
	  }
	
	
	public boolean transferBetweenCheckingAccounts(CheckingAccount c,String transferInfo,int transferType,double amount){
		  METHOD_NAME="transferBetweenCheckingAccounts";
		  boolean status=false;
		  SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
		  request.addProperty("arg0", c);
		  request.addProperty("arg1", transferInfo);
		  request.addProperty("arg2", transferType);
		  request.addProperty("arg3", amount);
		  
		  SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		    envelope.setOutputSoapObject(request);
		    MarshalDouble md=new MarshalDouble();
		    md.register(envelope);

		    try {

			    HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
			    androidHttpTransport.debug = true;
				androidHttpTransport.call(SOAP_ACTION, envelope);
				
				SoapPrimitive message = (SoapPrimitive) envelope.getResponse();
				
				if(message!=null){
					status=Boolean.parseBoolean(message.toString());		
				}
					
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		  
		  return status;
	  }
	
	public boolean transferToCheckingToDeposit(CheckingAccount c,DepositAccount dacc,double amount,int direction){
		  METHOD_NAME="transferToCheckingToDeposit";
		  boolean status=false;
		  SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
		  request.addProperty("arg0", c);
		  request.addProperty("arg1", dacc);
		  request.addProperty("arg2", amount);
		  request.addProperty("arg3", direction);
		  
		  SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		    envelope.setOutputSoapObject(request);
		    MarshalDouble md=new MarshalDouble();
		    md.register(envelope);

		    try {

			    HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
			    androidHttpTransport.debug = true;
				androidHttpTransport.call(SOAP_ACTION, envelope);
				
				SoapPrimitive message = (SoapPrimitive) envelope.getResponse();
				
				if(message!=null){
					status=Boolean.parseBoolean(message.toString());		
				}
					
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		  
		  return status;
	  }
	
	
	
	
		
}
