package com.herokuapp.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;


import com.herokuapp.R;
import com.herokuapp.databinding.ActivityMainBinding;
import com.herokuapp.view.base.BaseActivity;
import com.herokuapp.view.fragment.AuthorFragment;
import com.herokuapp.view.fragment.FragmentUtils;

public class MainActivity extends BaseActivity<ActivityMainBinding> {

    @Override
    public int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentUtils.replaceFragment(this, AuthorFragment.newInstance(), R.id.fragContainer, true);
    }
}
