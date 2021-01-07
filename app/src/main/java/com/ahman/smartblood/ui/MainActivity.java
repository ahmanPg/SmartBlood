package com.ahman.smartblood.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;


import com.ahman.smartblood.MapsActivity;
import com.ahman.smartblood.R;
import com.ahman.smartblood.ui.about.AboutActivity;
import com.ahman.smartblood.ui.login.LoginActivity;
import com.ahman.smartblood.ui.registration.DonorForm;
import com.ahman.smartblood.ui.request.NeedBlood;


public class MainActivity extends AppCompatActivity {
    public static String donorId = "no";
    Spinner options;
    Button buttonDonor;
    String spinnerValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        options = findViewById(R.id.city);
        options.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerValue = options.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }

        });

        final Button button = findViewById(R.id.go_button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if ("-- Choose an Action --" == spinnerValue) {
                    // Tell the user to choose something
                    Toast.makeText(MainActivity.this,"Choose an Option",
                            Toast.LENGTH_LONG).show();

                    return;
                }
                startActivity(new Intent(
                        view.getContext(),
                        spinnerValue == "Find Donor" ? NeedBlood.class : MapsActivity.class
                ));
            }
        });

        String[] link_of_choice = new String[]{"-- Choose an Action --", "Find Donor", "Find Center"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, link_of_choice);
        options.setAdapter(adapter);

        //be donor button
        buttonDonor = findViewById(R.id.be_donor);
        if (donorId.equals("no")) {
            buttonDonor.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this, DonorForm.class));
                }
            });
        }

    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // User chose the "login" action, pull login  form
            case R.id.action_login:
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                return true;
            // User chose the "search" item, show the app settings UI...
            case R.id.action_search:
                return true;
            case R.id.action_settings:
//                startActivity(new Intent(getApplicationContext(), SettingActivity.class));
            case R.id.action_about:
                startActivity(new Intent(getApplicationContext(), AboutActivity.class));
                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }


    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.options_menu, menu);
        getMenuInflater().inflate(R.menu.options_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setIconifiedByDefault(true);
        return true;
    }
}