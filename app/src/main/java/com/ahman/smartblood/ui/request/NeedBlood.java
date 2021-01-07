package com.ahman.smartblood.ui.request;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.ahman.smartblood.R;
import com.ahman.smartblood.helper.HttpJsonParser;
import com.ahman.smartblood.helper.URLs;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class NeedBlood extends AppCompatActivity {
    private static final String BASE_URL = URLs.URL_ALL;
    private static final String KEY_CENTER_NAME = "center_name";
    private static final String KEY_CENTER_ADDRESS = "center_address";
    private static final String KEY_SUCCESS = "success";
    private static final String KEY_DATA = "data";

    private  ArrayList<HashMap<String,String>> centerList;
    private HashMap<String, String> map;
    private Spinner groupChoice;
    private Spinner districtChoice;
    private Spinner centerSpinner;
    private Button need;

    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_need_blood);
        getSupportActionBar().hide();
        districtChoice = findViewById(R.id.districtSpinner);
        groupChoice = findViewById(R.id.needBlood);
        centerSpinner = findViewById(R.id.centers);
        need = findViewById(R.id.startSearch);

        new FetchCentersAsyncTask().execute();
        String[] group = new String[]{"O+","O-", "A+", "B+","A-", "B-", "AB+", "AB-"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, group);
        groupChoice.setAdapter(adapter1);


        need.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String group = groupChoice.getSelectedItem().toString();
                String district = districtChoice.getSelectedItem().toString();

                Intent intent = new Intent(getApplicationContext(), DonorList.class);
                intent.putExtra("group", group);
                intent.putExtra("district", district);
                startActivity(intent);
            }
        });

    }

    public class FetchCentersAsyncTask extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //Display progress bar
            pDialog = new ProgressDialog(NeedBlood.this);
            pDialog.setMessage("Loading donors. Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            HttpJsonParser httpJsonParser = new HttpJsonParser();
            JSONObject jsonObject = httpJsonParser.makeHttpRequest(
                    BASE_URL + "fetch_all_centers.php", "GET", null);
            try {
                int success = jsonObject.getInt(KEY_SUCCESS);
                JSONArray centers;
                if (success == 1) {
                    centerList = new ArrayList<HashMap<String, String>>();
                    centers = jsonObject.getJSONArray(KEY_DATA);
                    //Iterate through the response and populate donors list
                    for (int i = 0; i < centers.length(); i++) {
                        JSONObject center = centers.getJSONObject(i);
                        String centerName = center.getString(KEY_CENTER_NAME);
                        map = new HashMap<String, String>();
                        map.put(KEY_CENTER_NAME, centerName);
                        centerList.add(map);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(String result) {
            pDialog.dismiss();
            runOnUiThread(new Runnable() {
                public void run() {

                    Collection<String> values = map.values();
                    String[] array = values.toArray(new String[values.size()]);
                    Log.i("Myarray", "["+array+"]");
                    ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, array);
                   centerSpinner.setAdapter(adapter);
                }
            });
        }

    }

}