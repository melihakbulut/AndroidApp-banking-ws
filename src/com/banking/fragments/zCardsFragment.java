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
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.banking.Entity.Bank;
import com.banking.androidapp.R;
import com.banking.soap.InfoSoap;
import com.banking.soap.SoapClass;

public class zCardsFragment extends Fragment {

	Spinner sp1;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		new BaseAsyncTask().execute();
		View rootView = inflater.inflate(R.layout.account_detail_fragment, container, false);
        return rootView;
	}
	
	private class BaseAsyncTask extends AsyncTask<Object, Void, Object> {
		private ProgressDialog   dialog = new ProgressDialog(getActivity());
		
		protected void onPreExecute() {
            this.dialog.setMessage("Progress start");
            this.dialog.show();
        }
		
		@Override
		protected Object doInBackground(Object... params) {
			InfoSoap sc=new InfoSoap();
			List<Bank> list=sc.getBanks();
			

			return list;
		}

		@Override
		protected void onPostExecute(Object result) {
			List<Bank> banks=(List<Bank>)result;
			Log.i("#APP","melih");
			
			
			List<String> spinnerArray =  new ArrayList<String>(banks.size());
			spinnerArray.add(0,"Seçiniz");
			for (Bank b : banks) {
				spinnerArray.add(b.getBank_id(), b.getName());
			}
			
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				    getActivity().getApplicationContext(), R.layout.spinner_item, spinnerArray);

				adapter.setDropDownViewResource(R.layout.spinner_item);
				sp1.setAdapter(adapter);
				
				 if (dialog.isShowing()) {
		                dialog.dismiss();
			
			
			super.onPostExecute(result);
				 	}	

			}
		}
}
