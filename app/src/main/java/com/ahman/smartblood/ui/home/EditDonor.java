package com.ahman.smartblood.ui.home;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.ahman.smartblood.R;
import com.ahman.smartblood.helper.HttpJsonParser;
//import com.ahman.smartblood.ui.login.LoginActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class EditDonor extends AppCompatActivity {
    private static final String BASE_URL = "http://192.168.43.156/html/SmartBlood/android/";
    private static final String KEY_SUCCESS = "success";
    private static final String KEY_FAILURE = "fail";
    private static final String KEY_FAILURE3 = "No change has been made!";
    private static final String KEY_DATA = "data";
    private static final String KEY_DONOR_ID = "donor_id";
    private static final String KEY_DONOR_FNAME = "fName";
    private static final String KEY_DONOR_STATUS = "status";
    private static final String KEY_DONOR_LNAME = "lName";
    private static final String KEY_DONOR_GROUP = "donor_group";
    private static final String KEY_DONOR_WEIGHT = "weight";
    private static final String KEY_DONOR_GENDER = "donor_sex";
    private static final String KEY_DONOR_PHONE = "donor_phone";
    private static final String KEY_DONOR_AGE = "donor_age";
    private static final String KEY_DONOR_NO = "donor_no";
    private static final String KEY_DONOR_DISTRICT = "district";
    private static final String KEY_DONOR_PROVINCE = "province";


    private String donorId;
    private String donorNo;
    private String fName;
    private String donorGroup;
    private String donorSex;
    private String donorPhone;
    private String weight;
    private String district;
    private String province;
    private String lName;
    private String status;

    private EditText mFName_edt;
    private EditText mDonorNo_edt;
    private EditText mLName_edt;
    private EditText mDonorGroup_tv;
    private EditText mDonorSex_tv;
    private EditText mWeight_edt;
    private EditText mDonorPhone_tv;
    private EditText mDistrict_edt;
    private EditText mProvince_edt;
    private ProgressDialog pDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_donor);

        Intent intent = getIntent();
        donorId = intent.getStringExtra(KEY_DONOR_ID);
        new EditDonorDetailsAsyncTask().execute();

        mDonorNo_edt = findViewById(R.id.donorNo_textView);
        mFName_edt = findViewById(R.id.fName_textView);
        mLName_edt = findViewById(R.id.lName_textView);
        mDonorGroup_tv = findViewById(R.id.donorGroup_textView);
        mDonorSex_tv = findViewById(R.id.donorSex_textView);
        mDonorPhone_tv = findViewById(R.id.donorTel_textView);
        mWeight_edt = findViewById(R.id.weight_textView);
        mDistrict_edt = findViewById(R.id.district_textView);
        mProvince_edt = findViewById(R.id.province_textView);

//        donorPhone = (mDonorPhone_tv).getText().toString();
        Button mSaveButton = findViewById(R.id.call_btn);
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fName = mFName_edt.getText().toString();
                lName = mLName_edt.getText().toString();
                donorPhone = mDonorPhone_tv.getText().toString();
                province = mProvince_edt.getText().toString();
                district = mDistrict_edt.getText().toString();
                donorNo = mDonorNo_edt.getText().toString();
                donorSex = mDonorSex_tv.getText().toString();
                weight = mWeight_edt.getText().toString();
                donorGroup = mDonorGroup_tv.getText().toString();
                status = ((Spinner) findViewById(R.id.status_spinner)).getSelectedItem().toString().toLowerCase();
                new SaveEditsAsyncTask().execute();
//                
            }
        });

    }

    /**
     * Fetches single donor details from the server
     */
    private class EditDonorDetailsAsyncTask extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //Display progress bar
            pDialog = new ProgressDialog(EditDonor.this);
            pDialog.setMessage("Loading Donor Details. Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            HttpJsonParser httpJsonParser = new HttpJsonParser();
            Map<String, String> httpParams = new HashMap<String, String>();
            httpParams.put(KEY_DONOR_ID, donorId);
            JSONObject jsonObject = httpJsonParser.makeHttpRequest(
                    BASE_URL + "edit_donor_details.php", "GET", httpParams);
            try {
                int success = jsonObject.getInt(KEY_SUCCESS);
                JSONObject donor;
                if (success == 1) {
                    //Parse the JSON response
                    donor = jsonObject.getJSONObject(KEY_DATA);
                    fName = donor.getString(KEY_DONOR_FNAME);
                    lName = donor.getString(KEY_DONOR_LNAME);
                    weight = donor.getString(KEY_DONOR_WEIGHT);
                    donorGroup = donor.getString(KEY_DONOR_GROUP);
                    donorSex = donor.getString(KEY_DONOR_GENDER);
                    donorPhone = donor.getString(KEY_DONOR_PHONE);
                    district = donor.getString(KEY_DONOR_DISTRICT);
                    province = donor.getString(KEY_DONOR_PROVINCE);
                    donorNo = donor.getString(KEY_DONOR_NO);

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

                    //Populate the Edit Texts once the network activity is finished executing
                    mFName_edt.setText(fName);
                    mLName_edt.setText(lName);
                    mWeight_edt.setText(weight);
                    mDonorNo_edt.setText(donorNo);
                    mDonorGroup_tv.setText(donorGroup);
                    mDonorSex_tv.setText(donorSex);
                    mDonorPhone_tv.setText(donorPhone);
                    mDistrict_edt.setText(district);
                    mProvince_edt.setText(province);

                }
            });
        }


    }
    private class SaveEditsAsyncTask extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //Display progress bar
            pDialog = new ProgressDialog(EditDonor.this);
            pDialog.setMessage("Saving Details. Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }
        @Override
        protected String doInBackground(String... params) {
            HttpJsonParser httpJsonParser = new HttpJsonParser();
            Map<String, String> httpParams = new HashMap<String, String>();
            httpParams.put(KEY_DONOR_ID, donorId);
            httpParams.put(KEY_DONOR_FNAME, fName);
            httpParams.put(KEY_DONOR_LNAME, lName);
            httpParams.put(KEY_DONOR_PROVINCE, province);
            httpParams.put(KEY_DONOR_DISTRICT, district);
            httpParams.put(KEY_DONOR_PHONE, donorPhone);
            httpParams.put(KEY_DONOR_NO, donorNo);
            httpParams.put(KEY_DONOR_GENDER, donorSex);
            httpParams.put(KEY_DONOR_WEIGHT, weight);
            httpParams.put(KEY_DONOR_GROUP, donorGroup);
            httpParams.put(KEY_DONOR_STATUS, status);
            JSONObject jsonObject = httpJsonParser.makeHttpRequest(
                BASE_URL + "save_edited_donor.php", "POST", httpParams);
            try {
                int success = jsonObject.getInt(KEY_SUCCESS);
//                JSONObject donor;
                if (success == 1) {
                    //Parse the JSON response
                    return KEY_SUCCESS;

                }else if (success == 0) {
                    return KEY_FAILURE;

                }else if (success == 3) {
                    return KEY_FAILURE3;
                }
            } catch (JSONException e) {
            e.printStackTrace();
            }
            return null;
        }
        protected void onPostExecute(final String result) {
            pDialog.dismiss();
            runOnUiThread(new Runnable() {
                public void run() {

                    if (result.equals(KEY_SUCCESS)) {
                        Toast.makeText(getApplicationContext(), "Donor Updated Successfully", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
                        startActivity(intent);
                    }else if (result.equals(KEY_FAILURE3)){
                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();

                    }else{
                        Toast.makeText(getApplicationContext(), KEY_FAILURE, Toast.LENGTH_LONG).show();
                    }
                }
            });
        }


    }

}


