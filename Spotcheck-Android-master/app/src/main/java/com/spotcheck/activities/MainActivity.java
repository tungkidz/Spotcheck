package com.spotcheck.activities;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import com.spotcheck.fragments.*;
import com.spotcheck.R;

public class MainActivity extends AppCompatActivity
		implements NavigationView.OnNavigationItemSelectedListener,
		android.support.v7.widget.SearchView.OnQueryTextListener
{

	private static final String NAV_ITEM_ID = "navItemID";
	protected DrawerLayout mDrawerLayout;
	private ActionBarDrawerToggle mDrawerToggle;
	private Toolbar toolbar;
	private int navItemID;

	private final DiscoverSpotsFragment discoverSpotsFragment = new DiscoverSpotsFragment();
	private final PeopleFragment peopleFragment = new PeopleFragment();
	private final NewSpotFragment newSpotFragment = new NewSpotFragment();
	private final NotificationsFragment notificationsFragment = new NotificationsFragment();
	private final SavedSpotsFragment savedSpotsFragment = new SavedSpotsFragment();

	private final Handler mDrawerActionHandler = new Handler();
	private static final long DRAWER_CLOSE_DELAY_MS = 350;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

		setupToolbar();

		if (savedInstanceState == null)
			navItemID = R.id.drawer_item1;
		else
			navItemID = savedInstanceState.getInt(NAV_ITEM_ID);

		NavigationView navView = (NavigationView) findViewById(R.id.nav_view);
		if (navView != null)
			navView.setNavigationItemSelectedListener(this);
		navView.getMenu().findItem(navItemID).setChecked(true);
		setTitle(navView.getMenu().findItem(navItemID).getTitle());

		setupDrawerToggle();

		navigate(navItemID);

		FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
		if (fab != null)
		{
			fab.setOnClickListener(new View.OnClickListener()
			{
				@Override
				public void onClick(View view)
				{
					Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
							.setAction("Action", null).show();
				}
			});
		}

		navView.setNavigationItemSelectedListener(this);
		View header = navView.getHeaderView(0);
		Intent intent = getIntent();
		String[] credentials = intent.getStringExtra(LoginActivity.CREDENTIALS_MESSAGE).split(":");
		TextView userEmail = (TextView) header.findViewById(R.id.user_email);
		TextView userName = (TextView) header.findViewById(R.id.user_name);
		userEmail.setText(credentials[0]);
		userName.setText(credentials[1]);

	}

	private void navigate(final int itemID)
	{
		invalidateOptionsMenu();
		switch (itemID)
		{
			case R.id.drawer_item1:
				getSupportFragmentManager()
						.beginTransaction()
						.replace(R.id.container_view, discoverSpotsFragment)
						.commit();
				break;
			case R.id.drawer_item2:
				getSupportFragmentManager()
						.beginTransaction()
						.replace(R.id.container_view, peopleFragment)
						.commit();
				break;
			case R.id.drawer_item3:
				getSupportFragmentManager()
						.beginTransaction()
						.replace(R.id.container_view, newSpotFragment)
						.commit();
				break;
			case R.id.drawer_item4:
				getSupportFragmentManager()
						.beginTransaction()
						.replace(R.id.container_view, notificationsFragment)
						.commit();
				break;
			case R.id.drawer_item5:
				getSupportFragmentManager()
						.beginTransaction()
						.replace(R.id.container_view, savedSpotsFragment)
						.commit();
				break;
			case R.id.drawer_item_settings:
				//TODO: SETTINGS
				break;
			case R.id.drawer_item_logout:
				Intent loginActivity = new Intent(this, LoginActivity.class);
				startActivity(loginActivity);
				finish();
				break;
		}
	}

	private void setupToolbar()
	{
		toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);
	}

	private void setupDrawerToggle()
	{
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
		mDrawerToggle.syncState();
		mDrawerLayout.addDrawerListener(mDrawerToggle);
	}

	public boolean onNavigationItemSelected(final MenuItem menuItem)
	{
		menuItem.setChecked(true);
		navItemID = menuItem.getItemId();
		setTitle(menuItem.getTitle());

		mDrawerLayout.closeDrawer(GravityCompat.START);
		mDrawerActionHandler.postDelayed(new Runnable()
		{
			@Override
			public void run()
			{
				navigate(menuItem.getItemId());
			}
		}, DRAWER_CLOSE_DELAY_MS);
		return true;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		if (navItemID == R.id.drawer_item1)
		{
			getMenuInflater().inflate(R.menu.search_menu, menu);
			MenuItem searchItem = menu.findItem(R.id.action_search);
			SearchManager searchManager = (SearchManager) MainActivity.this.getSystemService(Context.SEARCH_SERVICE);

			SearchView searchView = null;
			if (searchItem != null)
				searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
			if (searchView != null)
				searchView.setOnQueryTextListener(this);
		}
		else
			getMenuInflater().inflate(R.menu.menu_main, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		int id = item.getItemId();
		if (id == R.id.action_settings)
			return true;

		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onBackPressed()
	{
		if (mDrawerLayout.isDrawerOpen(GravityCompat.START))
			mDrawerLayout.closeDrawer(GravityCompat.START);
		else
			super.onBackPressed();
	}

	@Override
	protected void onSaveInstanceState(final Bundle outState)
	{
		super.onSaveInstanceState(outState);
		outState.putInt(NAV_ITEM_ID, navItemID);
	}


	@Override
	public void setTitle(CharSequence title)
	{   getSupportActionBar().setTitle(title);  }

	@Override
	public boolean onQueryTextSubmit(String query)
	{
		//TODO: SEARCH
		return false;
	}

	@Override
	public boolean onQueryTextChange(String newText)
	{
		//TODO: ??
		return false;
	}
}