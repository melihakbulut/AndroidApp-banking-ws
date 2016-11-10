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
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.banking.Entity.Bank;
import com.banking.Entity.CheckingAccount;
import com.banking.Entity.Customer;
import com.banking.androidapp.MainActivity;
import com.banking.androidapp.R;
import com.banking.enums.Currency_Enum;
import com.banking.soap.CheckingAccSoap;

public class AccountDetails extends Fragment {

	LinearLayout mLinearLayout;
	TextView tv;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {


		View rootView = inflater.inflate(R.layout.account_detail_fragment, container, false);

		mLinearLayout=(LinearLayout)rootView.findViewById(R.id.account_detail_fragmentId);
		tv=(TextView)rootView.findViewById(R.id.textViewAccDetail);
		new BaseAsyncTask().execute();
		
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
			List<CheckingAccount> acc=(List<CheckingAccount>)result;
					
			StringBuilder sb=new StringBuilder();
			for (CheckingAccount c : acc) {
				sb.append("AccNumber : "+c.getA_id()+"\n");
				sb.append("IBAN : \n"+c.getIBAN()+"\n");
				
				if(c.getCurrency()==Currency_Enum.DOLLAR)
				sb.append("Currency : Dollar \n");
				else if(c.getCurrency()==Currency_Enum.EURO)
				sb.append("Currency : Euro \n");
				else if(c.getCurrency()==Currency_Enum.TL)
					sb.append("Currency : TL \n");
				
				sb.append("Balance : "+c.getBalance()+"\n");
				sb.append("Daily Transfer Limit : "+c.getDailyTransferLimit()+"\n");
				sb.append("Creation Date : \n"+c.getCreationDate()+"\n");
				if(c.getStatus()==1)
				sb.append("Status : Active"+"\n\n");
				else {
					sb.append("Status : Passive"+"\n\n");
				}
				
			}
			tv.setText(sb.toString());
				
				 if (dialog.isShowing()) {
		                dialog.dismiss();
			
			
			super.onPostExecute(result);
				 	}	

			}
		}
}
