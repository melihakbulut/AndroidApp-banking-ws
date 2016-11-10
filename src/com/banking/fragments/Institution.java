package com.banking.fragments;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import com.banking.Entity.CheckingAccount;
import com.banking.Entity.Customer;
import com.banking.Entity.CustomerCard;
import com.banking.Entity.Payment;
import com.banking.androidapp.MainActivity;
import com.banking.androidapp.R;
import com.banking.enums.Card_Enum;
import com.banking.enums.GRUD_Enum;
import com.banking.soap.CheckingAccSoap;
import com.banking.soap.CustomerCardSoap;
import com.banking.soap.DateType;
import com.banking.soap.PaymentSoap;

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

public class Institution extends Fragment {

	Spinner spAcc,spUni;
	EditText eRIDnum,amount;
	Button btn;
	CheckingAccount acc=null;
	List<CheckingAccount> accounts;
	List<String> spUniArray;
	Payment p;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View rootView = inflater.inflate(R.layout.institution_fragment, container, false);
		
		spAcc=(Spinner)rootView.findViewById(R.id.spinnerAcc);
		spUni=(Spinner)rootView.findViewById(R.id.spinnerUni);
		fillSpinners();
		eRIDnum=(EditText)rootView.findViewById(R.id.editTextTc);
		amount=(EditText)rootView.findViewById(R.id.editTextAmount);
		
		btn=(Button)rootView.findViewById(R.id.buttonInst);
		
		acc=new CheckingAccount();
		p=new Payment();
		spAcc.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				acc=accounts.get(position);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
		});
		spUni.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				p.setPaymentInfo(spUniArray.get(position)+" ödemesi");
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
		});
		
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				p.setA_id(acc.getA_id());
				p.setAmount(Double.parseDouble(amount.getText().toString()));
				p.setPaymentType(1);
				p.setTranscDate(DateType.formatDate(DateTime.now()));
				new BaseAsyncTask().execute();
				
			}
		});
		new BaseAsyncTask2().execute();
		
        return rootView;
	}
	
	public void fillSpinners(){
		spUniArray=  new ArrayList<String>();
		spUniArray.add("Abant Ýzzet Baysal Üniversitesi");
		spUniArray.add("Anadolu Üniversitesi");
		spUniArray.add("Ankara Üniversitesi");
		spUniArray.add("Balýkesir Üniversitesi");
		spUniArray.add("Bahçeþehir Üniversitesi");
		spUniArray.add("Boðaziçi Üniversitesi");
		spUniArray.add("Çanakkale Onsekiz Mart Üniversitesi");
		spUniArray.add("Dokuz Eylül Üniversitesi");
		spUniArray.add("Gazi Üniversitesi");
		spUniArray.add("Hacettepe Üniversitesi");
		spUniArray.add("Karadeniz Teknik Üniversitesi");
		spUniArray.add("Mimar Sinan Güzel Sanatlar Üniversitesi");
		spUniArray.add("Trakya Üniversitesi");
		spUniArray.add("Uludað Üniversitesi");
		
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
			    getActivity().getApplicationContext(), R.layout.spinner_item, spUniArray);

			adapter.setDropDownViewResource(R.layout.spinner_item);
			spUni.setAdapter(adapter);
		
	}
	private class BaseAsyncTask2 extends AsyncTask<Object, Void, Object> {
		private ProgressDialog   dialog = new ProgressDialog(getActivity());
		
		protected void onPreExecute() {
            this.dialog.setMessage("Please wait");
            this.dialog.show();
        }
		
		@Override
		protected Object doInBackground(Object... params) {
			
			CheckingAccSoap cas=new CheckingAccSoap();
			Customer c=(Customer)MainActivity.getCustomer();
			List<CheckingAccount> list=cas.getCheckingAccounts(c);

			return list;
		}

		@Override
		protected void onPostExecute(Object result) {
			accounts=(List<CheckingAccount>)result;
			
			List<String> spinnerArray =  new ArrayList<String>(accounts.size());
			
			for (CheckingAccount c : accounts) {
				spinnerArray.add(c.getA_id()+"\n"+c.getBalance());
			}
			
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				    getActivity().getApplicationContext(), R.layout.spinner_item, spinnerArray);

				adapter.setDropDownViewResource(R.layout.spinner_item);
				spAcc.setAdapter(adapter);
			
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
			
			PaymentSoap cas=new PaymentSoap();
			boolean message=cas.savePayment(p, GRUD_Enum.INSERT);

			return message;
		}

		@Override
		protected void onPostExecute(Object result) {
				boolean message=(Boolean) result;
				if(message)
					Toast.makeText(getActivity(), "Payment successful", Toast.LENGTH_LONG).show();
				else
					Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_LONG).show();
			
				 if (dialog.isShowing()) {
		                dialog.dismiss();
			
			
			super.onPostExecute(result);
				 	}	

			}
		}
}

