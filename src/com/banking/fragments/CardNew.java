package com.banking.fragments;

import java.util.ArrayList;
import java.util.List;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

import com.banking.Entity.CheckingAccount;
import com.banking.Entity.Customer;
import com.banking.Entity.CustomerCard;
import com.banking.androidapp.MainActivity;
import com.banking.androidapp.R;
import com.banking.enums.Card_Enum;
import com.banking.enums.GRUD_Enum;
import com.banking.soap.CheckingAccSoap;
import com.banking.soap.CustomerCardSoap;

public class CardNew extends Fragment {

	Spinner spCardType,spCardClass;
	EditText eCardLimit,ePassword;
	Button btn;
	CustomerCard card=null;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View rootView = inflater.inflate(R.layout.card_new_fragment, container, false);
		
		spCardType=(Spinner)rootView.findViewById(R.id.spinnerCardType);
		spCardClass=(Spinner)rootView.findViewById(R.id.spinnerCardClass);
		fillSpinners();
		eCardLimit=(EditText)rootView.findViewById(R.id.editCardLimit);
		ePassword=(EditText)rootView.findViewById(R.id.editTextPassword);
		
		btn=(Button)rootView.findViewById(R.id.buttonSaveCardNew);
		
		card=new CustomerCard();
		spCardType.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				card.setCardType(position+1);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
		});
		spCardClass.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				if(position==1)
					card.setCardClass(Card_Enum.AMEX);
				else if(position==2)
					card.setCardClass(Card_Enum.MASTERCARD);
				else if(position==3)
					card.setCardClass(Card_Enum.VISA);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
		});
		
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				card.setCardLimit(Double.parseDouble(eCardLimit.getText().toString()));
				card.setPassword(ePassword.getText().toString());
				card.setCardStatus(1);
				new BaseAsyncTask().execute();
				
			}
		});
		
        return rootView;
	}
	
	public void fillSpinners(){
		List<String> spCardTypeArray=  new ArrayList<String>();
		spCardTypeArray.add("Credit Card");
		spCardTypeArray.add("Virtual Card");
		spCardTypeArray.add("ATM Card");
		
		List<String> spCardClassArray=new ArrayList<String>();
		spCardClassArray.add("AMEX");
		spCardClassArray.add("Master Card");
		spCardClassArray.add("VISA");
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
			    getActivity().getApplicationContext(), R.layout.spinner_item, spCardTypeArray);

			adapter.setDropDownViewResource(R.layout.spinner_item);
			spCardType.setAdapter(adapter);
			
			ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(
				    getActivity().getApplicationContext(), R.layout.spinner_item, spCardClassArray);

				adapter2.setDropDownViewResource(R.layout.spinner_item);
				spCardClass.setAdapter(adapter2);
		
	}
	
	private class BaseAsyncTask extends AsyncTask<Object, Void, Object> {
		private ProgressDialog   dialog = new ProgressDialog(getActivity());
		
		protected void onPreExecute() {
            this.dialog.setMessage("Please wait");
            this.dialog.show();
        }
		
		@Override
		protected Object doInBackground(Object... params) {
			
			CustomerCardSoap cas=new CustomerCardSoap();
			Customer c=(Customer)MainActivity.getCustomer();
			card.setC_id(c.getId());
			boolean message=cas.updateCustomerCard(card, GRUD_Enum.INSERT);

			return message;
		}

		@Override
		protected void onPostExecute(Object result) {
				boolean message=(Boolean) result;
				if(message)
					Toast.makeText(getActivity(), "Saving New Card is successful", Toast.LENGTH_LONG).show();
				else
					Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_LONG).show();
			
				 if (dialog.isShowing()) {
		                dialog.dismiss();
			
			
			super.onPostExecute(result);
				 	}	

			}
		}
}
