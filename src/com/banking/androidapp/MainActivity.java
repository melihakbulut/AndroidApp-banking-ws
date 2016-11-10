package com.banking.androidapp;


import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.DrawerLayout.DrawerListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.TextView;

import com.banking.Entity.Customer;
import com.banking.fragments.AccountDetails;
import com.banking.fragments.Bill;
import com.banking.fragments.CardDetails;
import com.banking.fragments.CardNew;
import com.banking.fragments.CardUpdate;
import com.banking.fragments.EftBName;
import com.banking.fragments.EftBNumber;
import com.banking.fragments.EftShow;
import com.banking.fragments.GSM;
import com.banking.fragments.Gambling;
import com.banking.fragments.Institution;
import com.banking.fragments.PayCredit;
import com.banking.fragments.ProfileUpdate;
import com.banking.fragments.RepoNew;
import com.banking.fragments.RepoShow;
import com.banking.fragments.TransferBName;
import com.banking.fragments.TransferBNumber;
import com.banking.fragments.TransferShow;
import com.banking.soap.CustomerSoap;

@SuppressWarnings("deprecation")
public class MainActivity extends FragmentActivity {

	private DrawerLayout mDrawerLayout;
	private android.app.Fragment fragment = null;
	private ExpandableListView expListView;
	private HashMap<String, List<String>> listDataChild;
	private ExpandableListAdapter listAdapter;
	private List<String> listDataHeader;

	View view_Group;

	private static final int FILTER_ID = 0;

	// nav drawer title
	private CharSequence mDrawerTitle;

	// used to store app title
	private CharSequence mTitle;

	private ActionBarDrawerToggle mDrawerToggle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// enabling action bar app icon and behaving it as toggle button
		getActionBar().setBackgroundDrawable(
				new ColorDrawable(Color.parseColor("#0082C3")));
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);

		mTitle = mDrawerTitle = getTitle();

		setUpDrawer();
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, // nav menu toggle icon
				R.string.app_name, // nav drawer open - description for
									// accessibility
				R.string.app_name // nav drawer close - description for
									// accessibility
		) {
			@Override
			public void onDrawerClosed(View view) {
				getActionBar().setTitle(mTitle);
				// calling onPrepareOptionsMenu() to show action bar icons
				invalidateOptionsMenu();
			}

			@Override
			public void onDrawerOpened(View drawerView) {
				getActionBar().setTitle(mDrawerTitle);
				// calling onPrepareOptionsMenu() to hide action bar icons
				invalidateOptionsMenu();
			}
		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);
		makeActionOverflowMenuShown();
		getIntentCustomer();

	}

	// actionbar over flow icon
	private void makeActionOverflowMenuShown() {
		// devices with hardware menu button (e.g. Samsung ) don't show action
		// overflow menu
		try {
			final ViewConfiguration config = ViewConfiguration.get(this);
			final Field menuKeyField = ViewConfiguration.class
					.getDeclaredField("sHasPermanentMenuKey");
			if (menuKeyField != null) {
				menuKeyField.setAccessible(true);
				menuKeyField.setBoolean(config, false);
			}
		} catch (final Exception e) {
			Log.e("", e.getLocalizedMessage());
		}
	}

	/**
	 * 
	 * Get the names and icons references to build the drawer menu...
	 */
	private void setUpDrawer() {
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		/*
		 * mDrawerLayout.setScrimColor(getResources().getColor(
		 * android.R.color.transparent));
		 */
		mDrawerLayout.setDrawerListener(mDrawerListener);
		expListView = (ExpandableListView) findViewById(R.id.list_slidermenu);
		prepareListData();

		listAdapter = new ExpandableListAdapter(this, listDataHeader,
				listDataChild);
		// setting list adapter
		expListView.setAdapter(listAdapter);
		// expandable list view click listener

		expListView.setOnChildClickListener(new OnChildClickListener() {
			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				// setbackground color for list that is selected in child group
				v.setSelected(true);
				if (view_Group != null) {
					view_Group.setBackgroundColor(Color.parseColor("#ffffff"));
				}
				view_Group = v;
				view_Group.setBackgroundColor(Color.parseColor("#ee6a65"));

				switch (groupPosition) {

				/*
				 * Here add your fragment class name for each case menu (eg.
				 * Layout1, layout2 in screen) you can add n number of classes
				 * to the swithch case Also when you add the class name here,
				 * also add the corresponding name to the array list
				 */
				// dash board
				case 0:
					fragment = new ProfileUpdate();
					break;

				// before you file
				case 1:
					switch (childPosition) {
					case 0:
						fragment = new CardNew();
						break;
					case 1:
						fragment = new CardUpdate();
						break;
					case 2:
						fragment = new CardDetails();
						break;

					default:
						break;
					}
					break;

				// my profile
				case 2:
					switch (childPosition) {
					case 0:
						fragment = new AccountDetails();
						break;

					default:
						break;
					}
					break;

				// income slip
				case 3:
					switch (childPosition) {

					case 0:
						fragment = new TransferBNumber();
						break;
					case 1:
						fragment = new TransferBName();
						break;
					case 2:
						fragment = new EftBNumber();
						break;
					case 3:
						fragment = new EftBName();
						break;
					case 4:
						fragment = new TransferShow();
						break;
					case 5:
						fragment = new EftShow();
						break;

					default:
						break;
					}
					break;

				// federal deduction
				case 4:
					switch (childPosition) {
					case 0:
						fragment = new RepoNew();
						break;
					case 1:
						fragment = new RepoShow();
						break;


					default:
						break;
					}
					break;

				// provincial activity
				case 5:
					switch (childPosition) {
					case 0:
						fragment = new Bill();
						break;
					case 1:
						fragment = new Institution();
						break;
					case 2:
						fragment = new PayCredit();
						break;
					case 3:
						fragment = new GSM();
						break;
					case 4:
						fragment = new Gambling();
						break;
						

					default:
						break;
					}
					break;

				// expenses
				case 6:
					switch (childPosition) {
					case 0:
						fragment = new ProfileUpdate();
						break;

					default:
						fragment=new ProfileUpdate();
						break;
					}
					break;
				default:
					fragment=new ProfileUpdate();
					break;
				}
				getFragmentManager().beginTransaction()
						.replace(R.id.frame_container, fragment).commit();
				expListView.setItemChecked(childPosition, true);
				mDrawerLayout.closeDrawer(expListView);
				return false;
			}
		});
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState); // Sync the toggle state after
												// onRestoreInstanceState has
												// occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig); // Pass any configuration
													// change to the drawer
													// toggls
		mDrawerToggle.onConfigurationChanged(newConfig);
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// toggle nav drawer on selecting action bar app icon/title
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		// Handle action bar actions click
		switch (item.getItemId()) {
		case R.id.action_settings:
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}

	// Catch the events related to the drawer to arrange views according to this
	// action if necessary...
	private DrawerListener mDrawerListener = new DrawerListener() {

		@Override
		public void onDrawerStateChanged(int status) {

		}

		@Override
		public void onDrawerSlide(View view, float slideArg) {

		}

		@Override
		public void onDrawerOpened(View view) {
			getActionBar().setTitle(mDrawerTitle);
			// calling onPrepareOptionsMenu() to hide action bar icons
			invalidateOptionsMenu();
		}

		@Override
		public void onDrawerClosed(View view) {
			getActionBar().setTitle(mTitle);
			// calling onPrepareOptionsMenu() to show action bar icons
			invalidateOptionsMenu();
		}
	};

	private void prepareListData() {

		listDataHeader = new ArrayList<String>();
		listDataChild = new HashMap<String, List<String>>();

		String[] array = getResources()
				.getStringArray(R.array.nav_drawer_items);
		listDataHeader = Arrays.asList(array);


		List<String> dashboard = new ArrayList<String>();

		List<String> l1 = new ArrayList<String>();
		String[] before = getResources()
				.getStringArray(R.array.Cards);
		l1 = Arrays.asList(before);

		List<String> l2 = new ArrayList<String>();
		String[] myproe = getResources().getStringArray(R.array.Accounts);
		l2 = Arrays.asList(myproe);

		List<String> l3 = new ArrayList<String>();
		String[] inco = getResources().getStringArray(R.array.Transfers);
		l3 = Arrays.asList(inco);
		
		List<String> l4 = new ArrayList<String>();
		String[] fed = getResources().getStringArray(R.array.Repo);
		l4 = Arrays.asList(fed);

		List<String> l5 = new ArrayList<String>();
		String[] provi = getResources().getStringArray(
				R.array.Payment);
		l5 = Arrays.asList(provi);

		List<String> l6 = new ArrayList<String>();
		String[] expen = getResources().getStringArray(R.array.MyProfile);
		l6 = Arrays.asList(expen);



		// assigning values to menu and submenu
		listDataChild.put(listDataHeader.get(0), dashboard); // Header, Child
																// data
		listDataChild.put(listDataHeader.get(1), l1);
		listDataChild.put(listDataHeader.get(2), l2);
		listDataChild.put(listDataHeader.get(3), l3);
		listDataChild.put(listDataHeader.get(4), l4);
		listDataChild.put(listDataHeader.get(5), l5);
		listDataChild.put(listDataHeader.get(6), l6);

	}

	public class ExpandableListAdapter extends BaseExpandableListAdapter {

		private Context _context;
		private List<String> _listDataHeader; // header titles
		// child data in format of header title, child title
		private HashMap<String, List<String>> _listDataChild;

		public ExpandableListAdapter(Context context,
				List<String> listDataHeader,
				HashMap<String, List<String>> listChildData) {
			this._context = context;
			this._listDataHeader = listDataHeader;
			this._listDataChild = listChildData;
		}

		@Override
		public Object getChild(int groupPosition, int childPosititon) {
			return this._listDataChild.get(
					this._listDataHeader.get(groupPosition))
					.get(childPosititon);
		}

		@Override
		public long getChildId(int groupPosition, int childPosition) {
			return childPosition;
		}

		@Override
		public View getChildView(int groupPosition, final int childPosition,
				boolean isLastChild, View convertView, ViewGroup parent) {

			final String childText = (String) getChild(groupPosition,
					childPosition);

			if (convertView == null) {
				LayoutInflater infalInflater = (LayoutInflater) this._context
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = infalInflater.inflate(R.layout.list_item, null);
			}

			TextView txtListChild = (TextView) convertView
					.findViewById(R.id.lblListItem);

			txtListChild.setText(childText);
			return convertView;
		}

		@Override
		public int getChildrenCount(int groupPosition) {
			return this._listDataChild.get(
					this._listDataHeader.get(groupPosition)).size();
		}

		@Override
		public Object getGroup(int groupPosition) {
			return this._listDataHeader.get(groupPosition);
		}

		@Override
		public int getGroupCount() {
			return this._listDataHeader.size();
		}

		@Override
		public long getGroupId(int groupPosition) {
			return groupPosition;
		}

		@Override
		public View getGroupView(int groupPosition, boolean isExpanded,
				View convertView, ViewGroup parent) {
			String headerTitle = (String) getGroup(groupPosition);
			if (convertView == null) {
				LayoutInflater infalInflater = (LayoutInflater) this._context
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = infalInflater.inflate(R.layout.list_group, null);
			}
			// changing the +/- on expanded list view

			TextView lblListHeader = (TextView) convertView
					.findViewById(R.id.lblListHeader);
			// lblListHeader.setTypeface(null, Typeface.BOLD);
			lblListHeader.setText(headerTitle);

			// nav drawer icons from resources
			// navMenuIcons =
			// getResources().obtainTypedArray(R.array.nav_drawer_icons);
			// imgListGroup.setImageDrawable(navMenuIcons.getDrawable(groupPosition));

			// adding icon to expandable list view
			
			return convertView;
		}

		@Override
		public boolean hasStableIds() {
			return false;
		}

		@Override
		public boolean isChildSelectable(int groupPosition, int childPosition) {
			return true;
		}
	}
	static String  id,pass;
	public void getIntentCustomer(){
		Intent intent = getIntent();
		id = intent.getStringExtra("Id");
		pass = intent.getStringExtra("Password");
		Log.i("loginId",id);
		Log.i("loginpass",pass);
	}
	
	public static Customer getCustomer(){
		CustomerSoap cs=new CustomerSoap();
		return cs.login(id,pass);
	}
}

