package com.ahman.smartblood.ui.registration;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.ahman.smartblood.R;
import com.ahman.smartblood.ui.login.LoginActivity;


public class    UserRegistrationFrag extends Fragment {
//    private OnFragmentInteractionListener mListener;
    private static final String KEY_BUNDLE_NAME = "user_data";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_QUESTION = "question";
    private static final String KEY_ANSWER = "answer";

    private EditText mUsernameView;
    private EditText mPasswordView;
    private EditText mEmailView;
    private Spinner mQuestionView;
    private EditText mAnswerView;
    private String username;
    private String password;
    private String email;
    private String secQuestion;
    private String answer;

    private UserRegistrationViewModel mViewModel;

    public static UserRegistrationFrag newInstance() {
        return new UserRegistrationFrag();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.user_registration_fragment, container, false);
        return view;


    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button mContinueBtn = view.findViewById(R.id.button_continue);
        AppCompatTextView mLoginLink = view.findViewById(R.id.appCompatTextViewLoginLink);
        mLoginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), LoginActivity.class));
            }
        });
        mUsernameView = view.findViewById(R.id.username_editText);
        mEmailView = view.findViewById(R.id.email_editText);
        mPasswordView = view.findViewById(R.id.password_editText);
        mQuestionView = view.findViewById(R.id.spinnerDropdownQuestions);
        mAnswerView = view.findViewById(R.id.editText_answer);

        mContinueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = mUsernameView.getText().toString();
                password = mPasswordView.getText().toString();
                email = mEmailView.getText().toString();
                secQuestion = mQuestionView.getSelectedItem().toString();
                answer = mAnswerView.getText().toString();

               SharedPreferences preferences = getActivity().getSharedPreferences("Registration_info", Context.MODE_PRIVATE);
               SharedPreferences.Editor editor = preferences.edit();
               editor.putString(KEY_USERNAME,username);
               editor.putString(KEY_PASSWORD,password);
               editor.putString(KEY_EMAIL, email);
               editor.putString(KEY_QUESTION, secQuestion);
               editor.putString(KEY_ANSWER, answer);
               editor.apply();
               Toast.makeText(getActivity(), preferences.getString(KEY_USERNAME, null), Toast.LENGTH_SHORT).show();
               attemptNextFragment();

            }
        });
    }
    private void attemptNextFragment() {
        // Reset errors.
        mUsernameView.setError(null);
        mPasswordView.setError(null);
        mEmailView.setError(null);

        boolean cancel = false;
        View focusView = null;

        // Validate password
        if (TextUtils.isEmpty(password)) {
            mPasswordView.setError(getString(R.string.error_field_required));
            focusView = mPasswordView;
            cancel = true;
        }else if (!isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // validate username and email
        if (TextUtils.isEmpty(username)) {
            mUsernameView.setError(getString(R.string.error_field_required));
            focusView = mUsernameView;
            cancel = true;
        }
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }
        if (TextUtils.isEmpty(answer)) {
            mAnswerView.setError(getString(R.string.error_field_required));
            focusView = mAnswerView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't go next fragment and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            DonorForm activity = (DonorForm) getActivity();
            activity.selectIndex(1);
        }

    }
    private boolean isEmailValid (String username){
        //TODO: Replace this with your own logic
        if (username.contains("@"))
            return true;

        else
            return false;
    }

    private boolean isPasswordValid (String password){
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }
}
