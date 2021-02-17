package com.ahman.smartblood.ui.home;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ahman.smartblood.R;
import com.ahman.smartblood.helper.HttpJsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import static android.content.Context.MODE_PRIVATE;

public class AdminHomFrag extends Fragment {

    private static final String BASE_URL = "http://192.168.43.156/html/SmartBlood/android/";
    private static final String KEY_SUCCESS = "success";
    private static final String KEY_DATA = "data";
    private static final String KEY_REQUEST_COUNT = "request_count";
    private ProgressDialog pDialog;

    private String requestCount;
    private TextView mRequestCount;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.admin_homefrag, container, false);
        SharedPreferences preferences = getActivity().getSharedPreferences("login_info", MODE_PRIVATE);
        String username = preferences.getString("USERNAME", null);
//        password = preferences.getString("PASSWORD", null);
        TextView mWelcomemsg = view.findViewById(R.id.welcome_tv);
        mWelcomemsg.append(", "+ username);
        mRequestCount = view.findViewById(R.id.request_tv);
//        mBloodGroup = view.findViewById(R.id.request_tv);
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        new ViewRequestCountAsyncTask().execute();
        mRequestCount.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), ViewRequest.class);
                startActivity(i);
            }
        });
    }

    private class ViewRequestCountAsyncTask extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //Display progress bar
            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Loading. Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            HttpJsonParser httpJsonParser = new HttpJsonParser();
            JSONObject jsonObject = httpJsonParser.makeHttpRequest(
                    BASE_URL + "count_request.php", "GET", null);
            try {
                int success = jsonObject.getInt(KEY_SUCCESS);
                JSONObject donor;
                if (success == 1) {
                    //Parse the JSON response
                    donor = jsonObject.getJSONObject(KEY_DATA);
                    requestCount = donor.getString(KEY_REQUEST_COUNT);
                    return KEY_SUCCESS;

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(String result) {
            pDialog.dismiss();
            try {
                if (result.equals(KEY_SUCCESS)) {
                    getActivity().runOnUiThread(new Runnable() {
                        public void run() {

                            //Populate the Edit Texts once the network activity is finished executing
                            mRequestCount.setText(requestCount);


                        }

                    });
                }
            }catch (NullPointerException e){
               e.printStackTrace();
            }

        }


    }
}
