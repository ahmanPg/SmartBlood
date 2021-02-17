package com.ahman.smartblood.ui.home;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

public class UserHomeFragment extends Fragment {
    private static final String BASE_URL = "http://10.0.2.2/html/SmartBlood/android/";
    private static final String KEY_SUCCESS = "success";
    private static final String KEY_DATA = "data";
    private static final String KEY_DONOR_GROUP = "donor_group";
    private static final String KEY_USERNAME = "user_name";
    private static final String KEY_PASSWORD = "password";

    private static final String KEY_DONATION_COUNT = "donation_count";
    private ProgressDialog pDialog;

    private String donationCount;
    private String donorGroup;


    private String username;
    private String password;
    private TextView mDonationCount;
    private TextView mBloodGroup;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_homefragment, container, false);
        SharedPreferences preferences = getActivity().getSharedPreferences("login_info", MODE_PRIVATE);
        username = preferences.getString("USERNAME", null);
        password = preferences.getString("PASSWORD", null);
        TextView mWelcomemsg = view.findViewById(R.id.welcome_tv);
        mWelcomemsg.append(", "+username);
        mDonationCount = view.findViewById(R.id.donationCount_tv);
        mBloodGroup = view.findViewById(R.id.bloodType_tv);
        return view;
    }

//    private class FetchProfileDetailsAsyncTask extends AsyncTask<String, String, String> {
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            //Display progress bar
//            pDialog = new ProgressDialog(getActivity());
//            pDialog.setMessage("Loading your Details. Please wait...");
//            pDialog.setIndeterminate(false);
//            pDialog.setCancelable(false);
//            pDialog.show();
//        }
//
//        @Override
//        protected String doInBackground(String... params) {
//            HttpJsonParser httpJsonParser = new HttpJsonParser();
//            Map<String, String> httpParams = new HashMap<>();
//            httpParams.put(KEY_USERNAME, username);
//            httpParams.put(KEY_PASSWORD, password);
//            JSONObject jsonObject = httpJsonParser.makeHttpRequest(
//                    BASE_URL + "get_dashboard_details.php", "GET", httpParams);
//            try {
//                int success = jsonObject.getInt(KEY_SUCCESS);
//                JSONObject donor;
//                if (success == 1) {
//                    //Parse the JSON response
//                    donor = jsonObject.getJSONObject(KEY_DATA);
//                    donationCount = donor.getString(KEY_DONATION_COUNT);
//                    donorGroup = donor.getString(KEY_DONOR_GROUP);
//
//                }
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//            return null;
//        }
//        protected void onPostExecute(String result) {
//            pDialog.dismiss();
//            getActivity().runOnUiThread(new Runnable() {
//                public void run() {
//
//                    //Populate the Edit Texts once the network activity is finished executing
//                    mBloodGroup.setText(donorGroup);
//                    mDonationCount.setText(donationCount);
//
//
//                }
//            });
//        }
//
//
//    }
}
