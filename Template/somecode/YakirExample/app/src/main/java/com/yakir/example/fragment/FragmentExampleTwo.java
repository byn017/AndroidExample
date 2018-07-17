package com.yakir.example.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yakir.example.R;

/**
 * Created by Yakir Nahum on 6/25/2018.
 */

public class FragmentExampleTwo extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Inflate the layout for this fragment
        return inflater.inflate(
                R.layout.fragment_two, container, false);
    }
}
