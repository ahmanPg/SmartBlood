package com.ahman.smartblood.base;

import androidx.databinding.ViewDataBinding;
import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.material.snackbar.Snackbar;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by riteshksingh on Apr, 2018
 */
abstract public class BaseActivity extends AppCompatActivity {

  protected ViewDataBinding mBinding = null;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  protected void onDestroy() {
    mBinding = null;
    super.onDestroy();
  }

  protected void showSnackBar(String message) {
    showSnackBar(message, findViewById(android.R.id.content));
  }

  protected void showSnackBar(String message, View view) {
    Snackbar snackBar =
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT);
    View sbView = snackBar.getView();
    TextView textView = sbView.findViewById(android.support.design.R.id.snackbar_text);
    textView.setTextColor(ContextCompat.getColor(this, android.R.color.white));
    snackBar.show();
  }

  protected void setTitle(String title) {
    if (title == null) return;
    if (getSupportActionBar() != null) {
      getSupportActionBar().setTitle(title);
    }
  }
//
//  protected void logout() {
//    if (FirebaseAuth.getInstance().getCurrentUser() != null) {
//      FirebaseAuth.getInstance().signOut();
//      Intent loginActivity = new Intent(this, UserLoginActivity.class);
//      startActivity(loginActivity);
//      finish();
//    }
//  }
}
