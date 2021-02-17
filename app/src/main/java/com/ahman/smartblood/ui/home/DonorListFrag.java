package com.ahman.smartblood.ui.home;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.ahman.smartblood.R;
import com.ahman.smartblood.helper.CheckNetworkStatus;
import com.ahman.smartblood.helper.HttpJsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


public class DonorListFrag extends Fragment {
    private static final String KEY_SUCCESS = "success";
    private static final String KEY_DATA = "data";
    private static final String KEY_DONOR_ID= "donor_id";
    private static final String KEY_DONOR_NAME = "donor_name";
    private static final String KEY_DONOR_GROUP = "donor_group";
    private static final String KEY_DONOR_GENDER = "donor_sex";
    private static final String KEY_DONOR_AGE = "donor_age";
    private static final String KEY_DISTRICT = "district";
    private static final String KEY_GROUP_CHOICE = "group";
    private static final String KEY_DONOR_LOCATION = "donor_location";
//    private static final String KEY_DONOR_PROFILE
//    /= "donor_profile";

    private static final String BASE_URL = "http://192.168.43.156/html/SmartBlood/android/";
    private ArrayList<HashMap<String, String>> donorList;
    private ProgressDialog pDialog;
    private ListView donorListView;
    private String district;
    private String group;
    private HashMap<String, String> map;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_donor_list, container, false);
        donorListView = view.findViewById(R.id.listView);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        new FetchDonorsAsyncTask().execute();

    }

    public class FetchDonorsAsyncTask extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //Display progress bar
            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Loading donors. Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            HttpJsonParser httpJsonParser = new HttpJsonParser();
            HashMap<String, String> httpParams = new HashMap<String, String>();
            httpParams.put(KEY_GROUP_CHOICE, group);
            httpParams.put(KEY_DISTRICT, district);
            JSONObject jsonObject = httpJsonParser.makeHttpRequest(
                    BASE_URL + "view_donors.php", "GET", httpParams);
            try {
                int success = jsonObject.getInt(KEY_SUCCESS);
                JSONArray donors;
                if (success == 1) {
                    donorList = new ArrayList<HashMap<String, String>>();
                    donors = jsonObject.getJSONArray(KEY_DATA);
                    //Iterate through the response and populate donors list
                    for (int i = 0; i < donors.length(); i++) {
                        JSONObject donor = donors.getJSONObject(i);
                        Integer donorId = donor.getInt(KEY_DONOR_ID);
//                        Integer donorProfile = donor.getInt(KEY_DONOR_PROFILE);
                        String donorName = donor.getString(KEY_DONOR_NAME);
                        String donorGroup = donor.getString(KEY_DONOR_GROUP);
                        String donorDob = donor.getString(KEY_DONOR_AGE);
                        String donorSex = donor.getString(KEY_DONOR_GENDER);
                        String donorLocation = donor.getString(KEY_DONOR_LOCATION);
                        map = new HashMap<String, String>();
                        map.put(KEY_DONOR_ID, donorId.toString());
                        map.put(KEY_DONOR_NAME, donorName);
                        map.put(KEY_DONOR_GROUP, donorGroup);
                        map.put(KEY_DONOR_AGE, donorDob);
                        map.put(KEY_DONOR_GENDER, donorSex);
                        map.put(KEY_DONOR_LOCATION, donorLocation);
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
            getActivity().runOnUiThread(new Runnable() {
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
                getContext(), donorList,
                R.layout.donor_row, new String[]{KEY_DONOR_ID,
                KEY_DONOR_NAME, KEY_DONOR_GROUP, KEY_DONOR_AGE, KEY_DONOR_GENDER, KEY_DONOR_LOCATION},
                new int[]{R.id.donorId, R.id.donorName, R.id.donorGroup, R.id.donorDob, R.id.donorSex, R.id.donorLocation});
        // updating listView
        donorListView.setAdapter(adapter);
        //Call MovieUpdateDeleteActivity when a movie is clicked
        donorListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Check for network connectivity
                if (CheckNetworkStatus.isNetworkAvailable(getContext())) {
                    String donorId= ((TextView) view.findViewById(R.id.donorId))
                            .getText().toString();
                    Intent intent = new Intent(getContext(), EditDonor.class);
                    intent.putExtra(KEY_DONOR_ID, donorId);
                    startActivityForResult(intent, 20);

                } else {
                    Toast.makeText(getContext(),
                            "Unable to connect to internet",
                            Toast.LENGTH_LONG).show();

                }


            }
        });

    }

}
