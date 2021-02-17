package com.ahman.smartblood.ui.request;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.ahman.smartblood.R;
import com.ahman.smartblood.helper.HttpJsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class ViewDonor extends AppCompatActivity {
    private static final String BASE_URL = "http://10.0.2.2/html/SmartBlood/android/";
    private static final String KEY_SUCCESS = "success";
    private static final String KEY_DATA = "data";
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
    private String sms;

    private TextView mDonorName_tv;
    private TextView mDonorGroup_tv;
    private TextView mDonorSex_tv;
    private TextView mDonorPhone_tv;
    private TextView mDonorAddress_tv;
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
                sms = "Hi [First Name], someone need your help to save his/her life";
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

                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + donorPhone));
                startActivity(callIntent);
            }
            else {
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
            Map<String, String> httpParams = new HashMap<>();
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

                }
            });
        }


    }
}
