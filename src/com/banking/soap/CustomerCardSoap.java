package com.banking.soap;

import java.util.ArrayList;
import java.util.List;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.util.Log;

import com.banking.Entity.Customer;
import com.banking.Entity.CustomerCard;

public class CustomerCardSoap extends SoapClass {

	public boolean updateCustomerCard(CustomerCard c,int g){
		  METHOD_NAME="saveCustomerCard";
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
				
				
				Log.i("#app", envelope.bodyIn.toString());
				SoapPrimitive message = (SoapPrimitive) envelope.getResponse();
				
				if(message!=null){
					status=Boolean.parseBoolean(message.toString());		
				}
					
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		  
		  return status;
	  }
	
	public List<CustomerCard> getCustomerCards(Customer c){
		  List<CustomerCard> acc=new ArrayList<CustomerCard>();
		  METHOD_NAME="getCustomerCards";
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
					acc=getListFromEnvelope(envelope);
					
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		  
		  return acc;
	  }
	  
	  public List<CustomerCard> getListFromEnvelope(SoapSerializationEnvelope result){
			List<CustomerCard> list=new ArrayList<CustomerCard>();
			SoapSerializationEnvelope env=(SoapSerializationEnvelope)result;
			SoapObject results = (SoapObject) env.bodyIn;
			CustomerCard ca=null;
			
			for(int i= 0; i< results.getPropertyCount(); i++){
				SoapObject iterator=(SoapObject) results.getProperty(i);
				ca=new CustomerCard();
				for (int j = 0; j < ca.getPropertyCount(); j++) {
					ca.setProperty(j, iterator.getProperty(j));
				}
				list.add(ca);			

			}
			return list;
		}
	
	
}
