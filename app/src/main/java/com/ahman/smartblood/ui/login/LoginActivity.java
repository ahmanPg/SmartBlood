package com.ahman.smartblood.ui.login;

import android.content.Intent;
import com.google.android.material.textfield.TextInputEditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.ahman.smartblood.R;
import com.ahman.smartblood.data.SharedPrefManager;
import com.ahman.smartblood.helper.URLs;
import com.ahman.smartblood.helper.VolleySingleton;
import com.ahman.smartblood.model.User;
import com.ahman.smartblood.ui.MainActivity;
import com.ahman.smartblood.ui.home.HomeActivity;
import com.ahman.smartblood.ui.registration.DonorForm;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    private TextInputEditText mUsernameView;
    private TextInputEditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (SharedPrefManager.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, HomeActivity.class));
        }
        mProgressView = findViewById(R.id.login_progress_bar);
        mUsernameView = findViewById(R.id.textInputEditUsername);
        mPasswordView = findViewById(R.id.textInputEditPassword);

        //calling the method userLogin() for login the user
        findViewById(R.id.emailLoginButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userLogin();
            }
        });

        //if user presses on not register textView calling RegisterActivity
        findViewById(R.id.textViewLinkRegister).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(getApplicationContext(), DonorForm.class));
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

    private void userLogin() {
        //first getting the values

        final String username = mUsernameView.getText().toString();
        final String password = mPasswordView.getText().toString();
        //validating inputs
        if (TextUtils.isEmpty(username)) {
            mUsernameView.setError("Please enter your username");
            mUsernameView.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            mPasswordView.setError("Please enter your password");
            mPasswordView.requestFocus();
            return;
        }

        //if everything is fine
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        mPasswordView.setVisibility(View.GONE);

                        try {
                            //converting response to json object
                            JSONObject obj = new JSONObject(response);

                            //if no error in response
                            if (!obj.getBoolean("error")) {
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();

                                //getting the user from the response
                                JSONObject userJson = obj.getJSONObject("user");

                                //creating a new user object
                                User user = new User(
                                        userJson.getInt("id"),
                                        userJson.getString("username"),
                                        userJson.getString("email"),
                                        userJson.getString("type")
                                );

                                //storing the user in shared preferences
                                SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);
                                //starting the home activity
                                finish();
                                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                            } else {
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.e("ConnectError", e.getMessage());
                        e.printStackTrace();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("username", username);
                params.put("password", password);
                return params;
            }
        };

        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }
}