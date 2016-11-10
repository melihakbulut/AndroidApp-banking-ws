package com.banking.fragments;

import java.util.List;

import org.joda.time.DateTime;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.banking.Entity.CheckingAccount;
import com.banking.Entity.Customer;
import com.banking.Entity.CustomerCard;
import com.banking.Entity.EftBasedOnName;
import com.banking.androidapp.R;
import com.banking.enums.Card_Enum;
import com.banking.enums.GRUD_Enum;
import com.banking.soap.CheckingAccSoap;
import com.banking.soap.CustomerCardSoap;
import com.banking.soap.CustomerSoap;
import com.banking.soap.DateType;
import com.banking.soap.EftSoap;
import com.banking.soap.SoapClass;

public class zHomeFragment extends Fragment {

	TextView txt1,txt2;
	Button btn;
	Customer c=null;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.home_fragment, container, false);
		new BaseAsyncTask().execute();
	
		


        return rootView;
	}
	
	private class BaseAsyncTask extends AsyncTask<Object, Void, Object> {

		@Override
		protected Object doInBackground(Object... params) {
			CustomerSoap sc=new CustomerSoap();
			c=sc.login("24581574804", "password12345678");
//			boolean a=sc.updateCustomer(new Customer(8,"melih yeni2","54644546","asdsad@s.com",
//					"asdas dsadsadsadasd","23123123123",DateType.formatDate(DateTime.now()),
//					DateType.formatDate(DateTime.now()),"123123123123","yenipass",1)
//			,GRUD_Enum.UPDATE);
			CustomerCard card=new CustomerCard();
			card.setCard_id("3458230838867103");
			card.setC_id(c.getId());
			card.setCardType(Card_Enum.CREDIT_CARD);
			card.setCardClass(Card_Enum.AMEX);
			card.setCardLimit(2800);
			card.setCardStatus(1);
			card.setPassword("cardpass1400");
			//boolean a=new CustomerCardSoap().updateCheckingAcc(card, GRUD_Enum.UPDATE);
			
			//Log.i("homeFragment",String.valueOf(a));

			return c;
		}
		
		
		
		@Override
		protected void onPostExecute(Object result) {
			Customer c2=(Customer)result;
			txt1=(TextView)getActivity().findViewById(R.id.txtLabel);
			txt1.setText("Hoþgeldiniz ID = "+c2.getFullname());
			
			btn=(Button)getActivity().findViewById(R.id.button1);
			
			btn.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					new BaseAsyncTask2().execute();
				}
			});
			
			super.onPostExecute(result);
		}

	}
	
	private class BaseAsyncTask2 extends AsyncTask<Object, Void, Object> {

		@Override
		protected Object doInBackground(Object... params) {
			CheckingAccSoap sc=new CheckingAccSoap();
			EftSoap eftSoap=new EftSoap();
			List<CheckingAccount> checkList=sc.getCheckingAccounts(c);
			
			List<EftBasedOnName> check2=eftSoap.getEftName(checkList.get(0));

			return check2;
		}
		
		
		
		@Override
		protected void onPostExecute(Object result) {
			//List<CheckingAccount> ch=(List<CheckingAccount>)result;
			List<EftBasedOnName> ch=(List<EftBasedOnName>)result;
			
			txt2=(TextView)getActivity().findViewById(R.id.textView1);
			for (EftBasedOnName c : ch) {
				txt2.setText(txt2.getText()+"\n"+c.getA_id()+" - "+c.getAmount()+" - "+c.getReceiverFullname());
			}
			
			super.onPostExecute(result);
		}

	}
	
	
	
}
