package com.banking.soap;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.util.Log;

import com.banking.Entity.Customer;

public class CustomerSoap extends SoapClass {

	
	  public Customer login(String id_number,String password){
		  METHOD_NAME="loginCustomer";
		  Customer c=new Customer();
		  SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
		  request.addProperty("arg0", id_number);
		  request.addProperty("arg1", password);
		  
		  SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		    envelope.setOutputSoapObject(request);

		    try {

			    HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
			    androidHttpTransport.debug = true;
				androidHttpTransport.call(SOAP_ACTION, envelope);
				SoapObject r = (SoapObject) envelope.getResponse();
				Log.i("#app", envelope.bodyIn.toString());
				if(r!=null){
					for (int i = 0; i < c.getPropertyCount(); i++) {
						c.setProperty(i,r.getProperty(i));
					}					
				}
					
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		  
		  return c;
	  }
	  
	  public boolean updateCustomer(Customer c,int g){
		  boolean status=false;
		  METHOD_NAME="saveCustomer";
		  SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
		  request.addProperty("arg0", c);
		  request.addProperty("arg1", g);
		  
		  SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		    envelope.setOutputSoapObject(request);

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
