package com.banking.fragments;


import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import com.banking.Entity.CheckingAccount;
import com.banking.Entity.Customer;
import com.banking.Entity.Repo;
import com.banking.Entity.TransferBasedOnName;
import com.banking.Entity.TransferBasedOnNumber;
import com.banking.androidapp.MainActivity;
import com.banking.androidapp.R;
import com.banking.soap.CheckingAccSoap;
import com.banking.soap.DateType;
import com.banking.soap.RepoSoap;
import com.banking.soap.TransferSoap;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;

public class TransferShow extends Fragment {

	LinearLayout mLinearLayout;
	TextView tv;
	Spinner sp;
	List<CheckingAccount> acc;
	List<TransferBasedOnNumber> list2;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {


		View rootView = inflater.inflate(R.layout.show_transfer_fragment, container, false);

		mLinearLayout=(LinearLayout)rootView.findViewById(R.id.showTransfer_fragmentId);
		tv=(TextView)rootView.findViewById(R.id.textViewShowTransfer);
		sp=(Spinner)rootView.findViewById(R.id.spinnerTransferShow);
		
		new BaseAsyncTask().execute();
		
		sp.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				Log.i("asdfa",acc.get(position).getA_id());
				new BaseAsyncTask2().execute(acc.get(position).getA_id());
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
		});
		
        return rootView;
	}
	
	private class BaseAsyncTask extends AsyncTask<Object, Void, Object> {
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
			acc=(List<CheckingAccount>)result;
			
			List<String> spinnerArray =  new ArrayList<String>(acc.size());
			
			for (CheckingAccount c : acc) {
				spinnerArray.add(c.getA_id()+"\n"+c.getBalance());
			}
			
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				    getActivity().getApplicationContext(), R.layout.spinner_item, spinnerArray);

				adapter.setDropDownViewResource(R.layout.spinner_item);
				sp.setAdapter(adapter);
			
				 if (dialog.isShowing()) {
		                dialog.dismiss();
			
			
			super.onPostExecute(result);
				 	}	

			}
		}
	
	private class BaseAsyncTask2 extends AsyncTask<Object, Void, Object> {
		private ProgressDialog   dialog = new ProgressDialog(getActivity());
		
		protected void onPreExecute() {
            this.dialog.setMessage("Please wait");
            this.dialog.show();
        }
		
		@Override
		protected Object doInBackground(Object... params) {
			String a_id=(String) params[0];
			TransferSoap ts=new TransferSoap();
			CheckingAccount acc=new CheckingAccount();
			acc.setA_id(a_id);
			
			List<TransferBasedOnName> list=ts.getTransferName(acc);
			list2=ts.getTransferNumber(acc);
			return list;
		}

		@Override
		protected void onPostExecute(Object result) {
			List<TransferBasedOnName> list=(List<TransferBasedOnName>)result;
					
			StringBuilder sb=new StringBuilder();
			for (TransferBasedOnName r : list) {
				sb.append("AccNumber : "+r.getA_id()+"\n");
				sb.append("Transfer Information : \n"+r.getTransferInfo()+"\n");
				sb.append("Receiver Full name : \n "+r.getReceiverFullname()+"\n");
				sb.append("Address : \n "+r.getAddress()+"\n");
				
				sb.append("TransactionDate : "+r.getTransacDate()+"\n");
				sb.append("Amount : "+r.getAmount()+" \n");
				
				sb.append("Description : \n "+r.getDesc()+"\n\n");
				
			}
			tv.setText(sb.toString());
			
			for (TransferBasedOnNumber r : list2) {
				sb.append("AccNumber : "+r.getA_id()+"\n");
				sb.append("Transfer Information : \n"+r.getTransferInfo()+"\n");				
				sb.append("TransactionDate : "+r.getTransacDate()+"\n");
				sb.append("Amount : "+r.getAmount()+" \n");
				
				sb.append("Description : \n "+r.getDesc()+"\n\n");
				
			}
			tv.setText(tv.getText()+sb.toString());
				
				 if (dialog.isShowing()) {
		                dialog.dismiss();
			
			
			super.onPostExecute(result);
				 	}	

			}
		}
}
