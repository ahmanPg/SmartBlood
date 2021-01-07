package com.ahman.smartblood.ui.request;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ahman.smartblood.R;
import com.ahman.smartblood.helper.HttpJsonParser;
import com.ahman.smartblood.ui.home.AdminActivity;
import com.ahman.smartblood.ui.home.EditDonor;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class ViewDonor extends AppCompatActivity {
    private static final String BASE_URL = "http://10.0.2.2/SmartBlood/android/";
    private static final String KEY_SUCCESS = "success";
    private static final String KEY_DATA = "data";
    private static final String KEY_FAILURE = "fail";
    private static final String KEY_DONOR_ID = "donor_id";
    private static final String KEY_DONOR_NAME = "donor_name";
    private static final String KEY_DONOR_GROUP = "donor_group";
    private static final String KEY_DONOR_GENDER = "donor_sex";
    private static final String KEY_DONOR_PHONE = "donor_phone";
    private static final String KEY_DONOR_AGE = "donor_age";
    private static final String KEY_DONOR_LOCATION = "donor_location";


    private String donorId;
    private String donorName;
    private String donorGroup;
    private String donorSex;
    private String donorPhone;
    private String donorAddress;
    private String donorAge;
    private String sms;

    private TextView mDonorName_tv;
    private TextView mDonorGroup_tv;
    private TextView mDonorSex_tv;
    private TextView mDonorPhone_tv;
    private TextView mDonorAddress_tv;
    private TextView mDonorAge_tv;
    private ProgressDialog pDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_donor);

        Intent intent = getIntent();
        donorId = intent.getStringExtra(KEY_DONOR_ID);
        new FetchDonorDetailsAsyncTask().execute();

        mDonorName_tv = findViewById(R.id.donorName_textView);
        mDonorGroup_tv = findViewById(R.id.donorGroup_textView);
        mDonorSex_tv = findViewById(R.id.donorSex_textView);
        mDonorPhone_tv = findViewById(R.id.donorTel_textView);
        mDonorAddress_tv = findViewById(R.id.donorAddress_textView);
        mDonorAge_tv = findViewById(R.id.donorAge_textView);
        Button mSMSButton = findViewById(R.id.SMS_btn);
        donorPhone = (mDonorPhone_tv).getText().toString();
        Button mCallButton = findViewById(R.id.call_btn);
        mCallButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callPhoneNumber();
            }
        });

        mSMSButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sms = "Hi "+donorName+" ,someone need your help to save his/her life";
                Uri uri = Uri.parse("smsto:"+donorPhone);
                Intent it = new Intent(Intent.ACTION_SENDTO, uri);
                it.putExtra("sms_body", sms);
                startActivity(it);
            }
        });
    }
    public void callPhoneNumber()
    {
        try
        {
            if(Build.VERSION.SDK_INT > 22)
            {
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling

                    ActivityCompat.requestPermissions(ViewDonor.this, new String[]{Manifest.permission.CALL_PHONE}, 101);

                    return;
                }
                new SaveRequestAsyncTask().execute();
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + donorPhone));
                startActivity(callIntent);
            }
            else {
                new SaveRequestAsyncTask().execute();
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + donorPhone));
                startActivity(callIntent);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults)
    {
        if(requestCode == 101)
        {
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                callPhoneNumber();
            }
            else
            {
                Log.e("Tag", "Permission not Granted");
            }
        }
    }
    /**
     * Fetches single donor details from the server
     */
    private class FetchDonorDetailsAsyncTask extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //Display progress bar
            pDialog = new ProgressDialog(ViewDonor.this);
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
                    BASE_URL + "get_donor_details.php", "GET", httpParams);
            try {
                int success = jsonObject.getInt(KEY_SUCCESS);
                JSONObject donor;
                if (success == 1) {
                    //Parse the JSON response
                    donor = jsonObject.getJSONObject(KEY_DATA);
                    donorName = donor.getString(KEY_DONOR_NAME);
                    donorGroup = donor.getString(KEY_DONOR_GROUP);
                    donorSex = donor.getString(KEY_DONOR_GENDER);
                    donorPhone = donor.getString(KEY_DONOR_PHONE);
                    donorAddress = donor.getString(KEY_DONOR_LOCATION);
                    donorAge = donor.getString(KEY_DONOR_AGE);

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
                    mDonorName_tv.setText(donorName);
                    mDonorGroup_tv.setText(donorGroup);
                    mDonorSex_tv.setText(donorSex);
                    mDonorPhone_tv.setText(donorPhone);
                    mDonorAddress_tv.setText(donorAddress);
                    mDonorAge_tv.setText(donorAge);

                }
            });
        }


    }
    private class SaveRequestAsyncTask extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //Display progress bar
            pDialog = new ProgressDialog(ViewDonor.this);
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
            httpParams.put(KEY_DONOR_PHONE, donorPhone);
            JSONObject jsonObject = httpJsonParser.makeHttpRequest(
                    BASE_URL + "save_request.php", "POST", httpParams);
            try {
                int success = jsonObject.getInt(KEY_SUCCESS);
//                JSONObject donor;
                if (success == 1) {
                    //Parse the JSON response
                    return KEY_SUCCESS;

                }else if (success == 0) {
                    return KEY_FAILURE;
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
                        Toast.makeText(getApplicationContext(), "Request Saved Successfully", Toast.LENGTH_LONG).show();
//                        Intent intent = new Intent(getApplicationContext(), .class);
//                        startActivity(intent);
                    }else{
                        Toast.makeText(getApplicationContext(), KEY_FAILURE, Toast.LENGTH_LONG).show();
                    }
                }
            });
        }


    }
}
