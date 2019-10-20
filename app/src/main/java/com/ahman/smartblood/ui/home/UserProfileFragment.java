package com.ahman.smartblood.ui.home;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.ahman.smartblood.R;
import com.ahman.smartblood.helper.HttpJsonParser;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

public class UserProfileFragment extends Fragment {
    private static final String BASE_URL = "http://192.168.43.156/html/SmartBlood/android/";
    private static final String KEY_SUCCESS = "success";
    private static final String KEY_DATA = "data";
    private static final String KEY_DONOR_EMAIL = "user_email";
    private static final String KEY_DONOR_NAME = "donor_name";
    private static final String KEY_DONOR_GROUP = "donor_group";
    private static final String KEY_DONOR_GENDER = "donor_sex";
    private static final String KEY_DONOR_PHONE = "donor_phone";
    private static final String KEY_DONOR_DOB = "donor_dob";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_DONOR_LOCATION = "donor_location";
    private ProgressDialog pDialog;

    private String donorName;
    private String donorGroup;
    private String donorSex;
    private String donorPhone;
    private String donorAddress;
    private String donorDob;
    private String donorEmail;

    private String username;
    private String password;
    private TextView mUserName;
    private TextView mDonorName;
    private TextView mUserPhone;
    private TextView mUserAddress;
    private TextView mBloodGroup;
    private TextView mUserSex;
    private TextView mUserDob;
    private TextView mUserEmail;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_profilefragment, container, false);
        SharedPreferences preferences = getActivity().getSharedPreferences("login_info", MODE_PRIVATE);
        username = preferences.getString("USERNAME", null);
        password = preferences.getString("PASSWORD", null);
        new FetchProfileDetailsAsyncTask().execute();
//        Toast.makeText(getContext(), username+password, Toast.LENGTH_LONG).show();
        mDonorName = view.findViewById(R.id.name);
        mUserName = view.findViewById(R.id.designation);
        mUserPhone = view.findViewById(R.id.mobileNumber);
        mUserAddress = view.findViewById(R.id.location);
        mBloodGroup = view.findViewById(R.id.blood_group);
        mUserSex = view.findViewById(R.id.gender);
        mUserDob = view.findViewById(R.id.dob);
        mUserEmail = view.findViewById(R.id.email);
        mUserName.setText(username);

        return view;

    }
    private class FetchProfileDetailsAsyncTask extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //Display progress bar
            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Loading your Details. Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            HttpJsonParser httpJsonParser = new HttpJsonParser();
            Map<String, String> httpParams = new HashMap<>();
            httpParams.put(KEY_USERNAME, username);
            httpParams.put(KEY_PASSWORD, password);
            JSONObject jsonObject = httpJsonParser.makeHttpRequest(
                    BASE_URL + "get_profile_details.php", "GET", httpParams);
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
                    donorEmail = donor.getString(KEY_DONOR_EMAIL);
                    donorDob = donor.getString(KEY_DONOR_DOB);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
        protected void onPostExecute(String result) {
            pDialog.dismiss();
            getActivity().runOnUiThread(new Runnable() {
                public void run() {

                    //Populate the Edit Texts once the network activity is finished executing
                    mDonorName.setText(donorName);
                    mUserName.setText("("+username+")");
                    mUserPhone.setText(donorPhone);
                    mUserAddress.setText(donorAddress);
                    mUserSex.setText(donorSex);
                    mUserDob.setText(donorDob);
                    mBloodGroup.setText(donorGroup);
                    mUserEmail.setText(donorEmail);


                }
            });
        }


    }
}
