package com.banking.fragments;

import java.util.List;

import com.banking.Entity.Customer;
import com.banking.Entity.CustomerCard;
import com.banking.androidapp.MainActivity;
import com.banking.androidapp.R;
import com.banking.enums.Card_Enum;
import com.banking.enums.Currency_Enum;
import com.banking.soap.CheckingAccSoap;
import com.banking.soap.CustomerCardSoap;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CardDetails extends Fragment {

	LinearLayout mLinearLayout;
	TextView tv;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {


		View rootView = inflater.inflate(R.layout.card_detail_fragment, container, false);

		mLinearLayout=(LinearLayout)rootView.findViewById(R.id.card_detail_fragmentId);
		tv=(TextView)rootView.findViewById(R.id.textViewCardDetail);
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
			CustomerCardSoap cas=new CustomerCardSoap();
			Customer c=(Customer)MainActivity.getCustomer();
			List<CustomerCard> list=cas.getCustomerCards(c);

			return list;
		}

		@Override
		protected void onPostExecute(Object result) {
			List<CustomerCard> acc=(List<CustomerCard>)result;
					
			StringBuilder sb=new StringBuilder();
			for (CustomerCard c : acc) {
				sb.append("Card ID : "+c.getCard_id()+"\n");
				sb.append("CardLimit :"+c.getCardLimit()+"\n");
				
				if(c.getCardType()==Card_Enum.CREDIT_CARD)
				sb.append("Type : Credit Card \n");
				else if(c.getCardType()==Card_Enum.VIRTUAL_CARD)
				sb.append("Type : Virtual Card \n");
				else if(c.getCardType()==Card_Enum.BANK_CARD)
					sb.append("Type : ATM Card \n");
				if(c.getCardStatus()==1)
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
