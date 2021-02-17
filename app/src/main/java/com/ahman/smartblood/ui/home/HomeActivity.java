package com.ahman.smartblood.ui.home;

import android.app.ProgressDialog;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.core.view.GravityCompat;
import androidx.viewpager.widget.ViewPager;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.ahman.smartblood.R;
import com.ahman.smartblood.data.SharedPrefManager;


public class HomeActivity extends AppCompatActivity {
    private static  final String  KEY_SUCCESS = "success";
    private static final String BASE_URL = "URLs.URL_ALL";
    private static final String KEY_USER_ID = "user_id";
    private String donorId;
    private ProgressDialog pDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.app_name);

//          add tabs to TabLayout
//        Toast.makeText(this, username, Toast.LENGTH_LONG ).show();
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Home").setIcon(R.drawable.ic_home_black_24dp));
        tabLayout.addTab(tabLayout.newTab().setText("Appointments").setIcon(R.drawable.ic_date_range_black_24dp));
        tabLayout.addTab(tabLayout.newTab().setText("Locations").setIcon(R.drawable.ic_person_pin_circle_black_24dp));
        tabLayout.addTab(tabLayout.newTab().setText("Profile").setIcon(R.drawable.ic_person_outline_black_24dp));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

//          Feed ViewPager with tabs
        final ViewPager viewPager = findViewById(R.id.viewPager);
        final PagerAdapter adapter = new PagerAdapter
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
            super.onBackPressed();
        }
        finish();
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
            SharedPrefManager.logout();
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
                    return new UserHomeFragment();
                case 1:
                    return new UserAppointmentsFragment();
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




}
