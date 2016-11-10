package com.banking.fragments;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

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

import com.banking.Entity.Bank;
import com.banking.Entity.Branch;
import com.banking.Entity.CheckingAccount;
import com.banking.Entity.City;
import com.banking.Entity.Customer;
import com.banking.Entity.EftBasedOnName;
import com.banking.androidapp.MainActivity;
import com.banking.androidapp.R;
import com.banking.enums.GRUD_Enum;
import com.banking.soap.CheckingAccSoap;
import com.banking.soap.DateType;
import com.banking.soap.EftSoap;
import com.banking.soap.InfoSoap;
import com.banking.soap.TransferSoap;

public class EftBName extends Fragment {

	Spinner spBank,spCity,spBranch,spinnerAcc;
	EditText eIdnum,eFName,eMobile,amount;
	Button btn;
	
	List<Bank> banks;
	Bank selectedBank;
	List<City> cities;
	City selectedCity;
	List<Branch> branches;
	Branch selectedBranch;
	EftBasedOnName e;
	List<CheckingAccount> acc;
	CheckingAccount selectedCheckingAccount;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View rootView = inflater.inflate(R.layout.eftbasedname_fragment, container, false);
		spBank=(Spinner)rootView.findViewById(R.id.spinnerEBank);
		spCity=(Spinner)rootView.findViewById(R.id.spinnerECity);
		spBranch=(Spinner)rootView.findViewById(R.id.spinnerEBranch);
		spinnerAcc=(Spinner)rootView.findViewById(R.id.spinnerEAcc);
		
		eIdnum=(EditText)rootView.findViewById(R.id.editText1);
		eFName=(EditText)rootView.findViewById(R.id.editText2);
		eMobile=(EditText)rootView.findViewById(R.id.editText3);
		amount=(EditText)rootView.findViewById(R.id.editText4);
		
		btn=(Button)rootView.findViewById(R.id.buttonEnameSend);
		
		new BaseAsyncTaskAcc().execute();
		new BaseAsyncTaskBank().execute();
		new BaseAsyncTaskCity().execute();
		
		e=new EftBasedOnName();
		spBank.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				selectedBank=banks.get(position);
				Log.i("selectBank", String.valueOf(selectedBank.getBank_id()));
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
		});
		
		spCity.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				selectedCity=cities.get(position);
				Log.i("selectCity", String.valueOf(selectedCity.getCity_id()));
				
				new BaseAsyncTaskBranch().execute();
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
		});
		
		spBranch.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				selectedBranch=branches.get(position);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
		});
		
		spinnerAcc.setOnItemSelectedListener(new OnItemSelectedListener() {

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
				e.setA_id(selectedCheckingAccount.getA_id());
				e.setAmount(Double.parseDouble(amount.getText().toString()));
				e.setReceiver_identification_no(eIdnum.getText().toString());
				e.setReceiverMobile(eMobile.getText().toString());
				e.setReceiverFullname(eFName.getText().toString());
				e.setTransacDate(DateType.formatDate(DateTime.now()));
				e.setTransferInfo("");
				e.setDesc("");
				
				new BaseAsyncTaskSaveEftBName().execute();
			}
		});
		
        return rootView;
	}
	private class BaseAsyncTaskSaveEftBName extends AsyncTask<Object, Void, Object> {
		private ProgressDialog   dialog = new ProgressDialog(getActivity());
		
		protected void onPreExecute() {
            this.dialog.setMessage("Please wait");
            this.dialog.show();
        }
		
		@Override
		protected Object doInBackground(Object... params) {
			
			EftSoap cas=new EftSoap();
			boolean message=cas.saveEftName(e, GRUD_Enum.INSERT);

			return message;
		}

		@Override
		protected void onPostExecute(Object result) {
				boolean message=(Boolean) result;
				if(message)
					Toast.makeText(getActivity(), "Eft completed successfully", Toast.LENGTH_LONG).show();
				else
					Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_LONG).show();
			
				 if (dialog.isShowing()) {
		                dialog.dismiss();
			
			
			super.onPostExecute(result);
				 	}	

			}
		}

	
	private class BaseAsyncTaskAcc extends AsyncTask<Object, Void, Object> {
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
				spinnerAcc.setAdapter(adapter);
			
				 if (dialog.isShowing()) {
		                dialog.dismiss();
			
			
			super.onPostExecute(result);
				 	}	

			}
		}
	
	private class BaseAsyncTaskBranch extends AsyncTask<Object, Void, Object> {
		private ProgressDialog   dialog = new ProgressDialog(getActivity());
		
		protected void onPreExecute() {
            this.dialog.setMessage("Please wait");
            this.dialog.show();
        }
		
		@Override
		protected Object doInBackground(Object... params) {
			
			InfoSoap cas=new InfoSoap();
			List<Branch> list=cas.getBranches(selectedBank.getBank_id(), selectedCity.getCity_id());

			return list;
		}

		@Override
		protected void onPostExecute(Object result) {
			branches=(List<Branch>)result;
			
			List<String> spinnerArray =  new ArrayList<String>(branches.size());
			
			for (Branch c : branches) {
				spinnerArray.add(c.getName());
			}
			
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				    getActivity().getApplicationContext(), R.layout.spinner_item, spinnerArray);

				adapter.setDropDownViewResource(R.layout.spinner_item);
				spBranch.setAdapter(adapter);
			
				 if (dialog.isShowing()) {
		                dialog.dismiss();
			
			
			super.onPostExecute(result);
				 	}	

			}
		}
	
	private class BaseAsyncTaskBank extends AsyncTask<Object, Void, Object> {
		private ProgressDialog   dialog = new ProgressDialog(getActivity());
		
		protected void onPreExecute() {
            this.dialog.setMessage("Please wait");
            this.dialog.show();
        }
		
		@Override
		protected Object doInBackground(Object... params) {
			
			InfoSoap cas=new InfoSoap();
			List<Bank> list=cas.getBanks();

			return list;
		}

		@Override
		protected void onPostExecute(Object result) {
			banks=(List<Bank>)result;
			
			List<String> spinnerArray =  new ArrayList<String>(banks.size());
			
			for (Bank c : banks) {
				spinnerArray.add(c.getName());
			}
			
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				    getActivity().getApplicationContext(), R.layout.spinner_item, spinnerArray);

				adapter.setDropDownViewResource(R.layout.spinner_item);
				spBank.setAdapter(adapter);
			
				 if (dialog.isShowing()) {
		                dialog.dismiss();
			
			
			super.onPostExecute(result);
				 	}	

			}
		}
	
	private class BaseAsyncTaskCity extends AsyncTask<Object, Void, Object> {
		private ProgressDialog   dialog = new ProgressDialog(getActivity());
		
		protected void onPreExecute() {
            this.dialog.setMessage("Please wait");
            this.dialog.show();
        }
		
		@Override
		protected Object doInBackground(Object... params) {
			
			InfoSoap cas=new InfoSoap();
			List<City> list=cas.getCities();

			return list;
		}

		@Override
		protected void onPostExecute(Object result) {
			cities=(List<City>)result;
			
			List<String> spinnerArray =  new ArrayList<String>(cities.size());
			
			for (City c : cities) {
				spinnerArray.add(c.getName());
			}
			
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				    getActivity().getApplicationContext(), R.layout.spinner_item, spinnerArray);

				adapter.setDropDownViewResource(R.layout.spinner_item);
				spCity.setAdapter(adapter);
			
				 if (dialog.isShowing()) {
		                dialog.dismiss();
			
			
			super.onPostExecute(result);
				 	}	

			}
		}
}
