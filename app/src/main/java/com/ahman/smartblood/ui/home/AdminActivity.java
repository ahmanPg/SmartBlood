package com.ahman.smartblood.ui.home;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.ahman.smartblood.R;
import com.ahman.smartblood.helper.HttpJsonParser;
import com.ahman.smartblood.ui.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AdminActivity extends AppCompatActivity {
    private static  final String  KEY_SUCCESS = "success";
    private static final String BASE_URL = "http://192.168.43.156/html/SmartBlood/android/";
    private static final String KEY_USER_ID = "user_id";
    private String donorId;
    private ProgressDialog pDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.app_name);

//          add tabs to TabLayout
//        Toast.makeText(this, username, Toast.LENGTH_LONG ).show();
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Home").setIcon(R.drawable.ic_home_black_24dp));
        tabLayout.addTab(tabLayout.newTab().setText("Donors List").setIcon(R.drawable.ic_person_outline_black_24dp));
        tabLayout.addTab(tabLayout.newTab().setText("Centers List").setIcon(R.drawable.ic_person_pin_circle_black_24dp));
        tabLayout.addTab(tabLayout.newTab().setText("Profile").setIcon(R.drawable.ic_person_outline_black_24dp));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

//          Feed ViewPager with tabs
        final ViewPager viewPager = findViewById(R.id.viewPager);
        final AdminActivity.PagerAdapter adapter = new AdminActivity.PagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.action_logout){
            logOut();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }



    // Adapter for the viewpager using FragmentPagerAdapter
    public class PagerAdapter extends FragmentStatePagerAdapter {
        int mNumOfTabs;

        public PagerAdapter(FragmentManager fm, int NumOfTabs) {
            super(fm);
            this.mNumOfTabs = NumOfTabs;
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    return new AdminHomFrag();
                case 1:
                    return new DonorListFrag();
                case 2:
                    return new  UserLocationsFragment();
                case 3:
                    return new UserProfileFragment();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return mNumOfTabs;
        }
    }
    public void logOut(){
        class LogOutAsyncTask extends AsyncTask<String, String, String> {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                //Display progress bar
                pDialog = new ProgressDialog(AdminActivity.this);
                pDialog.setMessage("Loading Donor Details. Please wait...");
                pDialog.setIndeterminate(false);
                pDialog.setCancelable(false);
                pDialog.show();
            }

            @Override
            protected String doInBackground(String... params) {
                HttpJsonParser httpJsonParser = new HttpJsonParser();
                Map<String, String> httpParams = new HashMap<>();
                JSONObject jsonObject = httpJsonParser.makeHttpRequest(
                        BASE_URL + "logout.php", "GET", httpParams);
                try {
                    int success = jsonObject.getInt(KEY_SUCCESS);
                    if (success == 1) {
                        //Parse the JSON response


                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return null;
            }

            protected void onPostExecute(String result) {
                pDialog.dismiss();
                runOnUiThread(
                        new Runnable() {
                    public void run() {
                        Intent logoutIntent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(logoutIntent);

                    }
                });
            }


        }
        new LogOutAsyncTask().execute();

    }


}
