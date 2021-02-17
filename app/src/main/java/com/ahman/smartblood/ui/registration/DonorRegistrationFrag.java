package com.ahman.smartblood.ui.registration;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.textfield.TextInputEditText;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Toast;

import com.ahman.smartblood.R;
import com.ahman.smartblood.data.SharedPrefManager;
import com.ahman.smartblood.helper.URLs;
import com.ahman.smartblood.helper.VolleySingleton;
import com.ahman.smartblood.model.User;
import com.ahman.smartblood.ui.login.LoginActivity;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class DonorRegistrationFrag extends Fragment {
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_QUESTION = "question";
    private static final String KEY_ANSWER = "answer";

    private String username;
    private String password;
    private String email;
    private String secQuestion;
    private String answer;
    private String firstName;
    private String lastName;
    private String tel;
    private String province;
    private String district;
    private String dob;
    private String sex;
    private String weight;
    private String group;

    private ProgressDialog pDialog;
    Button mSaveButton;
    TextInputEditText mBirthDate;
    Calendar mCalendar;

    private UserRegistrationViewModel mViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.donor_registration_fragment, container, false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final Spinner mProvinceChoice = view.findViewById(R.id.dropdownProvince);
        mCalendar = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                mCalendar.set(Calendar.YEAR, year);
                mCalendar.set(Calendar.MONTH, monthOfYear);
                mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        mBirthDate = view.findViewById(R.id.textInputEditDOB);
        mBirthDate.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            new DatePickerDialog(getActivity(), date, mCalendar
                    .get(Calendar.YEAR), mCalendar.get(Calendar.MONTH),
                    mCalendar.get(Calendar.DAY_OF_MONTH)).show();
        }
        });


        mSaveButton = view.findViewById(R.id.btn_saveDonor);
        mSaveButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            firstName = ((TextInputEditText) view.findViewById(R.id.textInputEditFirstName)).getText().toString();
            lastName = ((TextInputEditText) view.findViewById(R.id.textInputEditLastName)).getText().toString();
            tel = ((TextInputEditText) view.findViewById(R.id.textInputEditPhone)).getText().toString();
            province = ((Spinner) view.findViewById(R.id.dropdownProvince)).getSelectedItem().toString();
            district = ((Spinner) view.findViewById(R.id.dropDownDistrict)).getSelectedItem().toString();
            dob = ((TextInputEditText) view.findViewById(R.id.textInputEditDOB)).getText().toString();
            sex = ((Spinner) view.findViewById(R.id.dropDownSex)).getSelectedItem().toString();
            weight = ((TextInputEditText) view.findViewById(R.id.textInputEditWeight)).getText().toString();
            group = ((Spinner) view.findViewById(R.id.dropDownGroup)).getSelectedItem().toString();

            SharedPreferences pref = getActivity().getSharedPreferences("Registration_info", Context.MODE_PRIVATE);
            username = pref.getString(KEY_USERNAME, null);
            password = pref.getString(KEY_PASSWORD, null);
            email = pref.getString(KEY_EMAIL, null);
            secQuestion = pref.getString(KEY_QUESTION, null);
            answer = pref.getString(KEY_ANSWER, null);
            if (username != null){
               registerUser(view);
            }
        }
    });

    }



    private void updateLabel() {
        String myFormat = "yy/MM/dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        mBirthDate.setText(sdf.format(mCalendar.getTime()));
    }

    private void registerUser(View view) {

        //first we will do the validations
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.URL_REGISTER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                            pDialog.cancel();

                        try {
                            //converting response to json object
                            JSONObject obj = new JSONObject(response);
                            //if no error in response
                            if (!obj.getBoolean("error")) {
                                Toast.makeText(getActivity(), obj.getString("message"), Toast.LENGTH_SHORT).show();

                                //getting the user from the response
                                JSONObject userJson = obj.getJSONObject("user");

                                //creating a new user object
                                User user = new User(
                                        userJson.getInt("id"),
                                        userJson.getString("username"),
                                        userJson.getString("email"),
                                        userJson.getString("type")
                                );

                                //storing the user in shared pref
                                SharedPrefManager.getInstance(getActivity()).userLogin(user);

                                //starting the login Activity
                                getActivity().finish();
                                startActivity(new Intent(getActivity(), LoginActivity.class));
                            } else {
                                Toast.makeText(getActivity(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
                        error.printStackTrace();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("username", username);
                params.put("email", email);
                params.put("password", password);
                params.put("question", secQuestion);
                params.put("answer", answer);
                params.put("first_name", firstName);
                params.put("last_name", lastName);
                params.put("phone", tel);
                params.put("blood_type", group);
                params.put("weight", weight);
                params.put("gender", sex);
                params.put("birth_date", dob);
                params.put("district", district);
                params.put("province", province);
                params.put("user_type", "2");

                return params;
            }
        };

        VolleySingleton.getInstance(getActivity()).addToRequestQueue(stringRequest);
    }

}