package com.gipra.kelebek.ui.IncomeDetails;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gipra.kelebek.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AccountStatement extends Fragment {


    public AccountStatement() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_account_statement, container, false);
        return view;
    }

}
