package com.banking.soap;

import java.util.List;

import com.banking.Entity.Bank;

import android.os.AsyncTask;
import android.os.Message;
import android.util.Log;

public class BaseAsyncTask extends AsyncTask<Object, Void, Object> {

	@Override
	protected Object doInBackground(Object... params) {
		SoapClass sc=new SoapClass();
		//List<Bank> list=sc.getBanks();
		

		return null;
	}
	
	
	
	@Override
	protected void onPostExecute(Object result) {
		List<Bank> banks=(List<Bank>)result;
		Log.i("#APP","melih");
		for (Bank bank : banks) {
			Log.i("#APP",bank.getBank_id()+" - "+bank.getName());
		}
		
		super.onPostExecute(result);
	}

}
