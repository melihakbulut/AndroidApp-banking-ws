package com.banking.fragments;

import java.util.ArrayList;
import java.util.List;

import com.banking.Entity.CheckingAccount;
import com.banking.Entity.Customer;
import com.banking.Entity.CustomerCard;
import com.banking.androidapp.MainActivity;
import com.banking.androidapp.R;
import com.banking.enums.Card_Enum;
import com.banking.enums.GRUD_Enum;
import com.banking.soap.CheckingAccSoap;
import com.banking.soap.CustomerCardSoap;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class CardUpdate extends Fragment {

	Spinner spCardT;
	EditText eCardLimit,ePassword;
	Button btn;
	CustomerCard card=null;
	List<CustomerCard> acc;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View rootView = inflater.inflate(R.layout.card_update_fragment, container, false);
		
		spCardT=(Spinner)rootView.findViewById(R.id.spinnerCustomerCard);
		
		eCardLimit=(EditText)rootView.findViewById(R.id.editCardLimit);
		ePassword=(EditText)rootView.findViewById(R.id.editTextPassword);
		
		btn=(Button)rootView.findViewById(R.id.buttonUpdateCard);
		
		card=new CustomerCard();
		spCardT.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				card=acc.get(position);
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
		

		new BaseAsyncTask2().execute();
		
        return rootView;
	}

	
	private class BaseAsyncTask2 extends AsyncTask<Object, Void, Object> {
		private ProgressDialog   dialog = new ProgressDialog(getActivity());
		
		protected void onPreExecute() {
            this.dialog.setMessage("Please wait");
            this.dialog.show();
        }
		
		@Override
		protected Object doInBackground(Object... params) {
			
			CustomerCardSoap cas=new CustomerCardSoap();
			Customer c=(Customer)MainActivity.getCustomer();
			List<CustomerCard> list=cas.getCustomerCards(c);

			return list;
		}

		@Override
		protected void onPostExecute(Object result) {
			acc=(List<CustomerCard>)result;
			
			List<String> spinnerArray =  new ArrayList<String>(acc.size());
			
			for (CustomerCard c : acc) {
				spinnerArray.add(c.getCard_id()+"\n"+c.getCardLimit());
			}
			
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				    getActivity().getApplicationContext(), R.layout.spinner_item, spinnerArray);

				adapter.setDropDownViewResource(R.layout.spinner_item);
				spCardT.setAdapter(adapter);
			
				 if (dialog.isShowing()) {
		                dialog.dismiss();
			
			
			super.onPostExecute(result);
				 	}	

			}
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
			boolean message=cas.updateCustomerCard(card, GRUD_Enum.UPDATE);

			return message;
		}

		@Override
		protected void onPostExecute(Object result) {
				boolean message=(Boolean) result;
				if(message)
					Toast.makeText(getActivity(), "Update Card is successful", Toast.LENGTH_LONG).show();
				else
					Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_LONG).show();
			
				 if (dialog.isShowing()) {
		                dialog.dismiss();
			
			
			super.onPostExecute(result);
				 	}	

			}
		}
}
