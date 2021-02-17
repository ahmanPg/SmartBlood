package com.ahman.smartblood.ui.registration;


import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;
import android.support.v4.view.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Toast;

import com.ahman.smartblood.R;

public class DonorForm extends AppCompatActivity {
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_QUESTION = "question";
    private static final String KEY_ANSWER = "answer";
    private String username;
    private String password;
    private String email;
    private String secQuestion;
    private String answer;

    private Bundle dataBetweenFragment;
    private ViewPager mViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.donor_form);
        getSupportActionBar().hide();
        SharedPreferences preferences = getSharedPreferences("Registration_info", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.commit();
        mViewPager = findViewById(R.id.view_pager);
        mViewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setTabTextColors(ColorStateList.valueOf(getResources().getColor(R.color.colorWhite)));
        tabLayout.setupWithViewPager(mViewPager);

    }
    public void selectIndex(int newIndex) {
//        SharedPreferences preferences = getSharedPreferences("Registration_info", Context.MODE_PRIVATE);
//        username = preferences.getString(KEY_USERNAME, "null?");
//        password = preferences.getString(KEY_PASSWORD, null);
//        email = preferences.getString(KEY_EMAIL, null);
//        secQuestion = preferences.getString(KEY_QUESTION, null);
//        answer = preferences.getString(KEY_ANSWER, null);
        mViewPager.setCurrentItem(newIndex);
    }

    @Override
    public void onBackPressed() {
        int currentPosition = mViewPager.getCurrentItem();
        if (currentPosition != 0) {
            mViewPager.setCurrentItem(0);
        } else {
            super.onBackPressed();
        }
    }
    public void saveData (Bundle data) {
        this.dataBetweenFragment = data;
    }

    public Bundle getSavedData () {
        return this.dataBetweenFragment;
    }


//    @Override
//    public void onFragmentInteraction(String key, String value) {
//        new DonorRegistrationFrag().onFragmentInteraction(key, value);
//    }

}