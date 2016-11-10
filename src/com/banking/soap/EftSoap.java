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
import com.banking.Entity.EftBasedOnName;
import com.banking.Entity.EftBasedOnNumber;

public class EftSoap extends SoapClass {

	
	public boolean saveEftName(EftBasedOnName c,int g){
		  METHOD_NAME="saveEftName";
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
	
	public boolean saveEftNumber(EftBasedOnNumber c,int g){
		  METHOD_NAME="saveEftNumber";
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
	
	public List<EftBasedOnName> getEftName(CheckingAccount c){
		  List<EftBasedOnName> acc=new ArrayList<EftBasedOnName>();
		  METHOD_NAME="getEftName";
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
					acc=getListFromEnvelopeEftName(envelope);
					
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		  
		  return acc;
	  }
	  
	  public List<EftBasedOnName> getListFromEnvelopeEftName(SoapSerializationEnvelope result){
			List<EftBasedOnName> list=new ArrayList<EftBasedOnName>();
			SoapSerializationEnvelope env=(SoapSerializationEnvelope)result;
			SoapObject results = (SoapObject) env.bodyIn;
			EftBasedOnName ca=null;
			
			for(int i= 0; i< results.getPropertyCount(); i++){
				SoapObject iterator=(SoapObject) results.getProperty(i);
				ca=new EftBasedOnName();
				for (int j = 0; j < ca.getPropertyCount(); j++) {
					ca.setProperty(j, iterator.getProperty(j));
				}
				list.add(ca);			

			}
			return list;
		}
	  
	  public List<EftBasedOnNumber> getEftNumber(CheckingAccount c){
		  List<EftBasedOnNumber> acc=new ArrayList<EftBasedOnNumber>();
		  METHOD_NAME="getEftNumber";
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
					acc=getListFromEnvelopeEftNumber(envelope);
					
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		  
		  return acc;
	  }
	  
	  public List<EftBasedOnNumber> getListFromEnvelopeEftNumber(SoapSerializationEnvelope result){
			List<EftBasedOnNumber> list=new ArrayList<EftBasedOnNumber>();
			SoapSerializationEnvelope env=(SoapSerializationEnvelope)result;
			SoapObject results = (SoapObject) env.bodyIn;
			EftBasedOnNumber ca=null;
			
			for(int i= 0; i< results.getPropertyCount(); i++){
				SoapObject iterator=(SoapObject) results.getProperty(i);
				ca=new EftBasedOnNumber();
				for (int j = 0; j < ca.getPropertyCount(); j++) {
					ca.setProperty(j, iterator.getProperty(j));
				}
				list.add(ca);			

			}
			return list;
		}
}
