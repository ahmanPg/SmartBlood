package com.ahman.smartblood.ui.registration;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.ahman.smartblood.R;
import com.ahman.smartblood.helper.HttpJsonParser;
import com.ahman.smartblood.ui.login.LoginActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class DonorRegistrationFrag extends Fragment {
//    private UserRegistrationFrag.OnFragmentInteractionListener mListener;
    private static final String BASE_URL = "http://192.168.43.156/html/SmartBlood/android/";
    private static final String KEY_SUCCESS = "success";
    private static final String KEY_FAILURE = "fail";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_QUESTION = "question";
    private static final String KEY_ANSWER = "answer";

    private static final String KEY_FNAME = "fname";
    private static final String KEY_LNAME = "lname";
    private static final String KEY_TEL = "phone";
    private static final String KEY_DISTRICT = "district";
    private static final String KEY_PROVINCE = "province";
    private static final String KEY_DOB = "dob";
    private static final String KEY_SEX = "sex";
    private static final String KEY_WEIGHT = "weight";
    private static final String KEY_GROUP = "group";

    private String username;
    private String password;
    private String email;
    private String secQuestion;
    private String answer;

    private String district;
    private String province;
    private String firstName;
    private String lastName;
    private String tel;
    private String dob;
    private String sex;
    private String weight;
    private String group;
    private ProgressDialog pDialog;
    Button mSaveButton;
    Calendar myCalendar;
    EditText mDob_edt;


    private UserRegistrationViewModel mViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);






    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.donor_registration_fragment, container, false);

//        Toast.makeText(getActivity(), "hjjjhhj", Toast.LENGTH_LONG).show();

//        if (username != null) {
////        DonorForm activity = (DonorForm) getActivity();
//            try {
////            Bundle savedData = activity.getSavedData();
////            username = savedData.getString(KEY_USERNAME);
//
//                Toast.makeText(getActivity(), username, Toast.LENGTH_LONG).show();
//            } catch (NullPointerException e) {
//                Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_LONG).show();
//                Log.e("NullPointerException", e.toString());
//            }
//        }
        return view;
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final Spinner mProvinceChoice = view.findViewById(R.id.dropdownProvince);
        myCalendar = Calendar.getInstance();

        mDob_edt = view.findViewById(R.id.dob_editText);
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        mDob_edt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(getActivity(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        mSaveButton = view.findViewById(R.id.btn_saveDonor);
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getActivity().getSharedPreferences("Registration_info", Context.MODE_PRIVATE);
                username = preferences.getString(KEY_USERNAME, null);
                password = preferences.getString(KEY_PASSWORD, null);
                email = preferences.getString(KEY_EMAIL, null);
                secQuestion = preferences.getString(KEY_QUESTION, null);
                answer = preferences.getString(KEY_ANSWER, null);
                firstName = ((EditText) view.findViewById(R.id.fname_editText)).getText().toString();
                lastName = ((EditText) view.findViewById(R.id.lname_editText)).getText().toString();
                tel = ((EditText) view.findViewById(R.id.tel_editText)).getText().toString();
                province = ((Spinner) view.findViewById(R.id.dropdownProvince)).getSelectedItem().toString();
                district = ((Spinner) view.findViewById(R.id.dropDownDistrict)).getSelectedItem().toString();
                dob = ((EditText) view.findViewById(R.id.dob_editText)).getText().toString();
                sex = ((Spinner) view.findViewById(R.id.dropDownSex)).getSelectedItem().toString();
                weight = ((EditText) view.findViewById(R.id.weight_editText)).getText().toString();
                group = ((Spinner) view.findViewById(R.id.dropDownGroup)).getSelectedItem().toString();
                if (username != null){
                    new RegisterUserAsyncTask().execute();
                }
            }
        });

    }
    private void updateLabel() {
        String myFormat = "yy/MM/dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        mDob_edt.setText(sdf.format(myCalendar.getTime()));
    }

//    @Override
//    public void onFragmentInteraction(String key, String value) {
//        Toast.makeText(getActivity(), key, Toast.LENGTH_LONG).show();
//
//    }



    private class RegisterUserAsyncTask extends AsyncTask<String, String, String> {
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
            httpParams.put(KEY_EMAIL, email);
            httpParams.put(KEY_QUESTION, secQuestion);
            httpParams.put(KEY_ANSWER, answer);

            httpParams.put(KEY_FNAME, firstName);
            httpParams.put(KEY_LNAME, lastName);
            httpParams.put(KEY_PROVINCE, province);
            httpParams.put(KEY_DISTRICT, district);
            httpParams.put(KEY_TEL, tel);
            httpParams.put(KEY_DOB, dob);
            httpParams.put(KEY_SEX, sex);
            httpParams.put(KEY_WEIGHT, weight);
            httpParams.put(KEY_GROUP, group);
            JSONObject jsonObject = httpJsonParser.makeHttpRequest(
                    BASE_URL + "save_registration.php", "POST", httpParams);
            try {
                int success = jsonObject.getInt(KEY_SUCCESS);
                JSONObject donor;
                if (success == 1) {
                    //Parse the JSON response
                    return KEY_SUCCESS;

                }else if (success == 0) {
                    return KEY_FAILURE;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return KEY_SUCCESS;
        }
        protected void onPostExecute(final String result) {
            pDialog.dismiss();
            getActivity().runOnUiThread(new Runnable() {
                public void run() {

                   if (result.equals(KEY_SUCCESS)) {
                       Toast.makeText(getActivity(), KEY_SUCCESS, Toast.LENGTH_LONG).show();
                       Intent intent = new Intent(getActivity(), LoginActivity.class);
                       intent.putExtra(KEY_USERNAME, username);
                       startActivity(intent);
                   }else{
                       Toast.makeText(getActivity(), KEY_FAILURE, Toast.LENGTH_LONG).show();
                   }
                }
            });
        }


    }

}
