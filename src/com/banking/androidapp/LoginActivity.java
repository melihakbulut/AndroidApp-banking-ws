package com.banking.androidapp;

import java.util.ArrayList;
import java.util.List;

import com.banking.Entity.Customer;
import com.banking.Entity.CustomerCard;
import com.banking.soap.CustomerCardSoap;
import com.banking.soap.CustomerSoap;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {

	EditText eId,ePass;
	Button btn;
	Context c;
	Customer customer;
	boolean login=false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		c=this;
		init();
	}
	
	public void init(){
		eId=(EditText)findViewById(R.id.editLogin1);
		ePass=(EditText)findViewById(R.id.editLogin2);
		
		btn=(Button)findViewById(R.id.button1);
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				new BaseAsyncTask2().execute();
				if(login){

					Intent intent = new Intent(c, MainActivity.class);
					intent.putExtra("Id",eId.getText().toString());
					intent.putExtra("Password",ePass.getText().toString());
	                startActivity(intent);   
				}
				
			}
		});
	}
	
	private class BaseAsyncTask2 extends AsyncTask<Object, Void, Object> {
		private ProgressDialog   dialog = new ProgressDialog(c);
		
		protected void onPreExecute() {
            this.dialog.setMessage("Please wait");
            this.dialog.show();
        }
		
		@Override
		protected Object doInBackground(Object... params) {
			
			customer=new CustomerSoap().login(eId.getText().toString(), ePass.getText().toString());
			
			return customer;
		}

		@Override
		protected void onPostExecute(Object result) {
			Customer cs=(Customer) result;
			if(cs.getFullname()!=null){
				Toast.makeText(c, "Login successful", Toast.LENGTH_LONG).show();
				login=true;
			}
			else
				Toast.makeText(c, "Something went wrong", Toast.LENGTH_LONG).show();
		
			
				 if (dialog.isShowing()) {
		                dialog.dismiss();
			
			
			super.onPostExecute(result);
				 	}	

			}
		}
	
	
}
