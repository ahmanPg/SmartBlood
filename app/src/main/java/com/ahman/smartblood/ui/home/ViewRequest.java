package com.ahman.smartblood.ui.home;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.ahman.smartblood.R;
import com.ahman.smartblood.helper.CheckNetworkStatus;
import com.ahman.smartblood.helper.HttpJsonParser;
import com.ahman.smartblood.ui.request.ViewDonor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class ViewRequest extends AppCompatActivity {
        private static final String KEY_SUCCESS = "success";
        private static final String KEY_DATA = "data";
        private static final String KEY_DONOR_ID= "donor_id";
    private static final String KEY_DONOR_NAME = "request_status";
        private static final String KEY_STATUS = "donor_name";
        private static final String KEY_DONOR_GROUP = "donor_group";
        private static final String KEY_REQ_COUNT = "request_count";
        private static final String KEY_DONOR_AGE = "donor_age";
        private static final String KEY_DONOR_SEX = "donor_sex";
        private static final String KEY_DONOR_LOCATION = "donor_location";
//    private static final String KEY_DONOR_PROFILE
//    /= "donor_profile";

        private static final String BASE_URL = "http://192.168.43.156/html/SmartBlood/android/";
        private ArrayList<HashMap<String, String>> donorList;
        private ProgressDialog pDialog;
        private ListView donorListView;
        private HashMap<String, String> map;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_view_request);
            getSupportActionBar().hide();

            donorListView = findViewById(R.id.listView);
            new ViewRequestAsyncTask().execute();
        }



        public class ViewRequestAsyncTask extends AsyncTask<String, String, String> {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                //Display progress bar
                pDialog = new ProgressDialog(ViewRequest.this);
                pDialog.setMessage("Loading requests. Please wait...");
                pDialog.setIndeterminate(false);
                pDialog.setCancelable(false);
                pDialog.show();
            }

            @Override
            protected String doInBackground(String... params) {
                HttpJsonParser httpJsonParser = new HttpJsonParser();

                JSONObject jsonObject = httpJsonParser.makeHttpRequest(
                        BASE_URL + "fetch_request.php", "GET", null);
                try {
                    int success = jsonObject.getInt(KEY_SUCCESS);
                    JSONArray donors;
                    if (success == 1) {
                        donorList = new ArrayList<HashMap<String, String>>();
                        donors = jsonObject.getJSONArray(KEY_DATA);
                        //Iterate through the response and populate donors list
                        for (int i = 0; i < donors.length(); i++) {
                            JSONObject donor = donors.getJSONObject(i);
                            int donorId = donor.getInt(KEY_DONOR_ID);
                            String donorName = donor.getString(KEY_DONOR_NAME);
                            String donorGroup = donor.getString(KEY_DONOR_GROUP);
                            String status = donor.getString(KEY_STATUS);
                            String donorDob = donor.getString(KEY_DONOR_AGE);
                            String donorSex = donor.getString(KEY_DONOR_SEX);
                            String donorLocation = donor.getString(KEY_DONOR_LOCATION);
                            int reqCount = donor.getInt(KEY_REQ_COUNT);
                            map = new HashMap<String, String>();
                            map.put(KEY_DONOR_ID, Integer.toString(donorId));
                            map.put(KEY_DONOR_NAME, donorName);
                            map.put(KEY_DONOR_GROUP, donorGroup);
                            map.put(KEY_DONOR_AGE, donorDob);
                            map.put(KEY_DONOR_SEX, donorSex);
                            map.put(KEY_STATUS, status);
                            map.put(KEY_DONOR_LOCATION, donorLocation);
                            map.put(KEY_REQ_COUNT, Integer.toString(reqCount));
//                        map.put(KEY_DONOR_PROFILE, donorProfile.toString());
                            donorList.add(map);
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
                        populateDonorList();
                    }
                });
            }

        }

        /**
         * Updating parsed JSON data into ListView
         * */
        private void populateDonorList() {
            ListAdapter adapter = new SimpleAdapter(
                    getApplicationContext(), donorList,
                    R.layout.donor_request_row, new String[]{KEY_REQ_COUNT, KEY_DONOR_ID,  KEY_STATUS
                    , KEY_DONOR_GROUP, KEY_DONOR_AGE, KEY_DONOR_SEX, KEY_DONOR_LOCATION,KEY_DONOR_NAME},
                    new int[]{R.id.reqNumber_tv, R.id.donorId, R.id.donorName, R.id.donorGroup, R.id.donorDob, R.id.donorSex, R.id.donorLocation, R.id.status});
            // updating listView
            donorListView.setAdapter(adapter);
            //Call MovieUpdateDeleteActivity when a movie is clicked
            donorListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    //Check for network connectivity
                    if (CheckNetworkStatus.isNetworkAvailable(getApplicationContext())) {
                        String donorId= ((TextView) view.findViewById(R.id.donorId))
                                .getText().toString();
                        Intent intent = new Intent(getApplicationContext(), ViewDonor.class);
                        intent.putExtra(KEY_DONOR_ID, donorId);
                        startActivityForResult(intent, 20);

                    } else {
                        Toast.makeText(getApplicationContext(),
                                "Unable to connect to internet",
                                Toast.LENGTH_LONG).show();

                    }


                }
            });

        }

    }
