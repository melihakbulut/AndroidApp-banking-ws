package com.banking.soap;

import java.util.ArrayList;
import java.util.List;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.util.Log;

import com.banking.Entity.CheckingAccount;
import com.banking.Entity.Customer;
import com.banking.Entity.TransferBasedOnName;
import com.banking.Entity.TransferBasedOnNumber;

public class TransferSoap extends SoapClass {

	public boolean saveTransferName(TransferBasedOnName c,int g){
		  METHOD_NAME="saveTransferName";
		  boolean status=false;
		  SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
		  request.addProperty("arg0", c);
		  request.addProperty("arg1", g);
		  
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
	
	public boolean saveTransferNumber(TransferBasedOnNumber c,int g){
		  METHOD_NAME="saveTransferNumber";
		  boolean status=false;
		  SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
		  request.addProperty("arg0", c);
		  request.addProperty("arg1", g);
		  
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
	
	public List<TransferBasedOnName> getTransferName(CheckingAccount c){
		  List<TransferBasedOnName> acc=new ArrayList<TransferBasedOnName>();
		  METHOD_NAME="getTransferName";
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
					
					Log.i("#APP",envelope.toString());
					acc=getListFromEnvelopeTransferName(envelope);
					
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		  
		  return acc;
	  }
	  
	  public List<TransferBasedOnName> getListFromEnvelopeTransferName(SoapSerializationEnvelope result){
			List<TransferBasedOnName> list=new ArrayList<TransferBasedOnName>();
			SoapSerializationEnvelope env=(SoapSerializationEnvelope)result;
			SoapObject results = (SoapObject) env.bodyIn;
			TransferBasedOnName ca=null;
			
			for(int i= 0; i< results.getPropertyCount(); i++){
				SoapObject iterator=(SoapObject) results.getProperty(i);
				ca=new TransferBasedOnName();
				for (int j = 0; j < ca.getPropertyCount(); j++) {
					ca.setProperty(j, iterator.getProperty(j));
				}
				list.add(ca);			

			}
			return list;
		}
	  
	  public List<TransferBasedOnNumber> getTransferNumber(CheckingAccount c){
		  List<TransferBasedOnNumber> acc=new ArrayList<TransferBasedOnNumber>();
		  METHOD_NAME="getTransferNumber";
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
					
					Log.i("#APP",envelope.toString());
					acc=getListFromEnvelopeTransferNumber(envelope);
					
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		  
		  return acc;
	  }
	  
	  public List<TransferBasedOnNumber> getListFromEnvelopeTransferNumber(SoapSerializationEnvelope result){
			List<TransferBasedOnNumber> list=new ArrayList<TransferBasedOnNumber>();
			SoapSerializationEnvelope env=(SoapSerializationEnvelope)result;
			SoapObject results = (SoapObject) env.bodyIn;
			TransferBasedOnNumber ca=null;
			
			for(int i= 0; i< results.getPropertyCount(); i++){
				SoapObject iterator=(SoapObject) results.getProperty(i);
				ca=new TransferBasedOnNumber();
				for (int j = 0; j < ca.getPropertyCount(); j++) {
					ca.setProperty(j, iterator.getProperty(j));
				}
				list.add(ca);			

			}
			return list;
		}
}
