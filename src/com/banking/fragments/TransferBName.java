package com.banking.fragments;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import com.banking.Entity.CheckingAccount;
import com.banking.Entity.Customer;
import com.banking.Entity.CustomerCard;
import com.banking.Entity.TransferBasedOnName;
import com.banking.androidapp.MainActivity;
import com.banking.androidapp.R;
import com.banking.enums.GRUD_Enum;
import com.banking.soap.CheckingAccSoap;
import com.banking.soap.CustomerCardSoap;
import com.banking.soap.DateType;
import com.banking.soap.TransferSoap;

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

public class TransferBName extends Fragment {

	Spinner spCardT;
	EditText eIdnum,eFName,amount;
	Button btn;
	TransferBasedOnName t;
	CheckingAccount selectedCheckingAccount;
	List<CheckingAccount> acc;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View rootView = inflater.inflate(R.layout.transferbasedname_fragment, container, false);
		
		spCardT=(Spinner)rootView.findViewById(R.id.spinnerAcc);
		
		eIdnum=(EditText)rootView.findViewById(R.id.editText1);
		eFName=(EditText)rootView.findViewById(R.id.editText2);
		amount=(EditText)rootView.findViewById(R.id.editText3);
		
		btn=(Button)rootView.findViewById(R.id.buttonTnameSend);
		
		t=new TransferBasedOnName();
		spCardT.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				selectedCheckingAccount=acc.get(position);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
		});

		
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				t.setA_id(selectedCheckingAccount.getA_id());
				t.setReceiverFullname(eFName.getText().toString());
				t.setAmount(Double.parseDouble(amount.getText().toString()));
				t.setAddress("");
				t.setDesc("");
				t.setTransacDate(DateType.formatDate(DateTime.now()));
				t.setTransferInfo("");
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
			
			TransferSoap cas=new TransferSoap();
			boolean message=cas.saveTransferName(t, GRUD_Enum.INSERT);

			return message;
		}

		@Override
		protected void onPostExecute(Object result) {
				boolean message=(Boolean) result;
				if(message)
					Toast.makeText(getActivity(), "Transfer completed successfully", Toast.LENGTH_LONG).show();
				else
					Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_LONG).show();
			
				 if (dialog.isShowing()) {
		                dialog.dismiss();
			
			
			super.onPostExecute(result);
				 	}	

			}
		}
}
