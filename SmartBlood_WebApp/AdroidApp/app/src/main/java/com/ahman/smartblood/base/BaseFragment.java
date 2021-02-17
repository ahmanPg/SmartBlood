package com.ahman.smartblood.base;

import androidx.databinding.ViewDataBinding;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by riteshksingh on Apr, 2018
 */
public class BaseFragment extends Fragment {

  protected ViewDataBinding mBinding = null;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                           @Nullable Bundle savedInstanceState) {
    return super.onCreateView(inflater, container, savedInstanceState);
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    mBinding = null;
  }
}
