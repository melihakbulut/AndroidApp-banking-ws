package com.banking.soap;

import java.util.ArrayList;
import java.util.List;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.util.Log;

import com.banking.Entity.Bank;
import com.banking.Entity.Branch;
import com.banking.Entity.City;

public class InfoSoap extends SoapClass {

	  public List<Bank> getBanks () {
		  	METHOD_NAME="getBanks";
		    List<Bank> banks = new ArrayList<Bank>();
		    
		    Log.i("#APP","soap baþladý");		     
		    SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
		     
		    SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		    envelope.setOutputSoapObject(request);
		     
		    try {

			    HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
			    androidHttpTransport.debug = true;
				androidHttpTransport.call(SOAP_ACTION, envelope);
				
				Log.i("#APP",envelope.toString());
				banks=getListFromEnvelopeBanks(envelope);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		     
		    
		   return banks;
	  }
	  
		public List<Bank> getListFromEnvelopeBanks(SoapSerializationEnvelope result){
			List<Bank> list=new ArrayList<Bank>();
			SoapSerializationEnvelope env=(SoapSerializationEnvelope)result;
			SoapObject results = (SoapObject) env.bodyIn;
			
			for(int i= 0; i< results.getPropertyCount(); i++){
				SoapObject iterator=(SoapObject) results.getProperty(i);
				Bank b=new Bank();
				for (int j = 0; j < b.getPropertyCount(); j++) {
					b.setProperty(j, iterator.getProperty(j));
				}
				list.add(b);		
			}
			return list;
		}
		
		public List<City> getCities() {
		  	METHOD_NAME="getCities";
		    List<City> cities = new ArrayList<City>();
		    
		    Log.i("#APP","soap baþladý");		     
		    SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
		     
		    SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		    envelope.setOutputSoapObject(request);
		     
		    try {

			    HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
			    androidHttpTransport.debug = true;
				androidHttpTransport.call(SOAP_ACTION, envelope);
				
				Log.i("#APP",envelope.toString());
				cities=getListFromEnvelopeCities(envelope);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		     
		    
		   return cities;
	  }
	  
		public List<City> getListFromEnvelopeCities(SoapSerializationEnvelope result){
			List<City> list=new ArrayList<City>();
			SoapSerializationEnvelope env=(SoapSerializationEnvelope)result;
			SoapObject results = (SoapObject) env.bodyIn;
			
			for(int i= 0; i< results.getPropertyCount(); i++){
				SoapObject iterator=(SoapObject) results.getProperty(i);
				City b=new City();
				for (int j = 0; j < b.getPropertyCount(); j++) {
					b.setProperty(j, iterator.getProperty(j));
				}
				list.add(b);		
			}
			return list;
		}
		
		public List<Branch> getBranches(int bank_id,int city_id){
			  List<Branch> acc=new ArrayList<Branch>();
			  METHOD_NAME="getBranches";
			  SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
			  request.addProperty("arg0", bank_id);
			  request.addProperty("arg1", city_id);
			  
			  SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
			    envelope.setOutputSoapObject(request);
			    MarshalDouble md=new MarshalDouble();
			    md.register(envelope);
			    try {

			    	 HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
					    androidHttpTransport.debug = true;
						androidHttpTransport.call(SOAP_ACTION, envelope);
						
						Log.i("#APP",envelope.bodyIn.toString());
						acc=getListFromEnvelopeBranch(envelope);
						
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			  
			  return acc;
		  }
		  
		  public List<Branch> getListFromEnvelopeBranch(SoapSerializationEnvelope result){
				List<Branch> list=new ArrayList<Branch>();
				SoapSerializationEnvelope env=(SoapSerializationEnvelope)result;
				SoapObject results = (SoapObject) env.bodyIn;
				Branch ca=null;
				
				for(int i= 0; i< results.getPropertyCount(); i++){
					SoapObject iterator=(SoapObject) results.getProperty(i);
					ca=new Branch();
					for (int j = 0; j < ca.getPropertyCount(); j++) {
						ca.setProperty(j, iterator.getProperty(j));
					}
					list.add(ca);			

				}
				return list;
			}
}
