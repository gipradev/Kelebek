package com.gipra.kelebek.ui;


import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.gipra.kelebek.ApiClient;
import com.gipra.kelebek.ApiInterface;
import com.gipra.kelebek.District.DistrictAdapter;
import com.gipra.kelebek.District.DistrictList;
import com.gipra.kelebek.District.ResponseDistrict;
import com.gipra.kelebek.R;
import com.gipra.kelebek.ResponseInnerRegistration;
import com.gipra.kelebek.State.ResponseState;
import com.gipra.kelebek.State.StateAdapter;
import com.gipra.kelebek.State.StateList;
import com.gipra.kelebek.ui.Dashboard.DashBoardFragment;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RegistrationFragment extends DashBoardFragment {
    Button inner_reg;
    TextView dob_innerreg,text;

    EditText name_innerreg,mobile_innerreg,email_innerreg,address_innerreg,zipcode_innerreg,username_innerreg,sponsor_innerreg,upline_innerreg,password_innerreg,confirmpassowrd_innerreg;
    Spinner gender_innerreg,country_innerreg,state_innerreg,district_innerreg,position_innerreg;
    String[] gen={"Select Gender","Female","Male","Other"};
    String[] position={"--Select--","R","L"};
    String[] country={"Select Country","India"};

    private ArrayList<StateList> statelist;
    private StateAdapter stateAdapter;
    private ArrayList<DistrictList> districtlist;
    private DistrictAdapter districtAdapter;

    String statecode;
    String distname;
    DatePickerDialog dp;
    boolean invalid;
    View layout;
    LayoutInflater inflater;
    SimpleDateFormat dateFormatter;

    public RegistrationFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        if (container!=null)
//        {
//            container.removeAllViewsInLayout();
//        }
        // Inflate the layout for this fragment

       View view= inflater.inflate(R.layout.fragment_registration, container, false);

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Registration");

        name_innerreg=view.findViewById(R.id.name_innerreg);
        mobile_innerreg=view.findViewById(R.id.mobile_innerreg);
        email_innerreg=view.findViewById(R.id.email_innerreg);
        address_innerreg=view.findViewById(R.id.address_innerreg);

        layout = inflater.inflate(R.layout.custom_toast, (ViewGroup) view.findViewById(R.id.custom_toast_container));
        zipcode_innerreg=view.findViewById(R.id.zipcode_innerreg);

        username_innerreg=view.findViewById(R.id.username_innerreg);
        sponsor_innerreg=view.findViewById(R.id.sponsorid_innerreg);

        upline_innerreg=view.findViewById(R.id.upline_innerreg);

        upline_innerreg.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                uplineCheck();

            }
        });
        sponsor_innerreg.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                SponsorCheck();

            }
        });

        password_innerreg=view.findViewById(R.id.password_innerreg);
        confirmpassowrd_innerreg=view.findViewById(R.id.confirm_password_innerreg);
       inner_reg=view.findViewById(R.id.innerregister);
       inner_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InnerReg();
            }
        });
       gender_innerreg=view.findViewById(R.id.gender_innerreg);
       ArrayAdapter aa = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, gen);
       aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
       gender_innerreg.setAdapter(aa);

        position_innerreg=view.findViewById(R.id.position_innerreg);
        ArrayAdapter p = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, position);
        p.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        position_innerreg.setAdapter(p);

        country_innerreg=view.findViewById(R.id.country_innerreg);
        ArrayAdapter coun = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, country);
        coun.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        country_innerreg.setAdapter(coun);

        state_innerreg=view.findViewById(R.id.state_innerreg);
        loadstate();
        district_innerreg=view.findViewById(R.id.district_innerreg);
        dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        //long date = System.currentTimeMillis();
        dob_innerreg=view.findViewById(R.id.dob_innerreg);
        dob_innerreg.requestFocus();
//        String dateString = dateFormatter.format(date);
//        dob_regvacant.setText(dateString);

        dob_innerreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dp.show();
            }
        });
        Calendar newCalendar = Calendar.getInstance();
        dp = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                dob_innerreg.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        return  view;
    }

    private void uplineCheck() {
        String upline=upline_innerreg.getText().toString();
        ApiInterface api=ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseUplineCheck>usercall=api.UplineCheck(upline);
        usercall.enqueue(new Callback<ResponseUplineCheck>() {
            @Override
            public void onResponse(Call<ResponseUplineCheck> call, Response<ResponseUplineCheck> response) {
                if (response.body().getStatus().equals("0")){
                    upline_innerreg.setError(response.body().getMessage());

                    //Toast.makeText(RegVacant.this, "User does not exists", Toast.LENGTH_SHORT).show();
                }
                else {

                }
            }
            @Override
            public void onFailure(Call<ResponseUplineCheck> call, Throwable t) {

            }
        });
    }

    private void SponsorCheck() {
        String sponsor=sponsor_innerreg.getText().toString();
        ApiInterface api=ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseSponsorCheck>usercall=api.SponsorCheck(sponsor);
        usercall.enqueue(new Callback<ResponseSponsorCheck>() {
            @Override
            public void onResponse(Call<ResponseSponsorCheck> call, Response<ResponseSponsorCheck> response) {
                if (response.body().getStatus().equals("0")){
                    sponsor_innerreg.setError(response.body().getMessage());
                    //Toast.makeText(RegVacant.this, "User does not exists", Toast.LENGTH_SHORT).show();
                }
                else {

                }
            }
            @Override
            public void onFailure(Call<ResponseSponsorCheck> call, Throwable t) {

            }
        });
    }

    private void loadstate(){
        ApiInterface api= ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseState> usercall=api.loadstatelist();
        usercall.enqueue(new Callback<ResponseState>() {
            @Override
            public void onResponse(Call<ResponseState> call, Response<ResponseState> response) {
                Log.i("onResponsestate", new Gson().toJson(response.body()));
                final ResponseState responseState=response.body();
                statelist=(ArrayList<StateList>)responseState.getData();
                stateAdapter=new StateAdapter(getActivity(),statelist);
                state_innerreg.setAdapter(stateAdapter);
                state_innerreg.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        statecode=statelist.get(position).getStateCode();
                        loaddistrict();
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }
            @Override
            public void onFailure(Call<ResponseState> call, Throwable t) {
            }
        });
    }

    private void loaddistrict(){
        ApiInterface api=ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseDistrict>usercall=api.loaddistrictlist(statecode);
        usercall.enqueue(new Callback<ResponseDistrict>() {
            @Override
            public void onResponse(Call<ResponseDistrict> call, Response<ResponseDistrict> response) {
                Log.i("onResponsedist", new Gson().toJson(response.body()));
                final  ResponseDistrict responseDistrict=response.body();
                districtlist=(ArrayList<DistrictList>)responseDistrict.getData();
                districtAdapter=new DistrictAdapter(getActivity(),districtlist);
                district_innerreg.setAdapter(districtAdapter);
                district_innerreg.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        distname=districtlist.get(position).getDistrictId();
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });
            }
            @Override
            public void onFailure(Call<ResponseDistrict> call, Throwable t) {
            }
        });
    }

    private  void InnerReg() {
        String n = name_innerreg.getText().toString();
        String gen = gender_innerreg.getSelectedItem().toString();
        String dob = dob_innerreg.getText().toString();
        String mob = mobile_innerreg.getText().toString();
        String em = email_innerreg.getText().toString();
        String add = address_innerreg.getText().toString();
        String zip = zipcode_innerreg.getText().toString();
        String con = country_innerreg.getSelectedItem().toString();
        String st = statecode;
        String dis = distname;
        String uname = username_innerreg.getText().toString();
        String spon = sponsor_innerreg.getText().toString();
        String uplineuser = upline_innerreg.getText().toString();
        String pos = position_innerreg.getSelectedItem().toString();
        String psd = password_innerreg.getText().toString();
        String cpsd = confirmpassowrd_innerreg.getText().toString();
        TextView errorTextcountry = (TextView) country_innerreg.getSelectedView();
        TextView errorTextgender = (TextView) gender_innerreg.getSelectedView();
        TextView errorTextpostion = (TextView) position_innerreg.getSelectedView();
        //TextView errorTextstate = (TextView)reg_state.getSelectedView();
        invalid = false;
        Drawable warning = (Drawable) getResources().getDrawable(R.drawable.ic_warn);
        warning.setBounds(0, 0, warning.getIntrinsicWidth(), warning.getIntrinsicHeight());

        if (n.equals("")) {
            invalid = true;
            name_innerreg.requestFocusFromTouch();
            name_innerreg.setError("Please! fill your name", warning);

        }else if (errorTextgender.getText().equals("Select Gender")) {
            invalid = true;
            gender_innerreg.requestFocusFromTouch();
            errorTextgender.setError("");
            errorTextgender.setTextColor(Color.RED);//just to highlight that this is an error
            errorTextgender.setText("Please! select gender");
        } else if (dob.equals("")) {
            invalid = true;
            dob_innerreg.requestFocusFromTouch();
            dob_innerreg.setError("Please! fill your date of birth", warning);

        } else if (mob.equals("")) {
            invalid = true;
            mobile_innerreg.requestFocusFromTouch();
            mobile_innerreg.setError("Please! fill your mobile number", warning);

        } else if (mob.length() != 10) {
            mobile_innerreg.requestFocusFromTouch();
            mobile_innerreg.setError("Please enter a valid mobile number", warning);

        } else if (em.equals("")) {
            invalid = true;
            email_innerreg.requestFocusFromTouch();
            email_innerreg.setError("Please! fill your Email-id", warning);

        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(em).matches()) {
            invalid = true;
            email_innerreg.requestFocusFromTouch();
            email_innerreg.setError("Please! enter a valid email address", warning);

        } else if (add.equals("")) {
            invalid = true;
            address_innerreg.requestFocusFromTouch();
            address_innerreg.setError("Please! fill your postal address", warning);
        } else if (zip.equals("")) {
            zipcode_innerreg.requestFocusFromTouch();
            zipcode_innerreg.setError("Please! fill your PINCode", warning);

        } else if (zip.length() != 6) {
            zipcode_innerreg.requestFocusFromTouch();
            zipcode_innerreg.setError("Postal index number must be 6 digit", warning);

        } else if (errorTextcountry.getText().equals("Select Country")) {
            invalid = true;
            country_innerreg.requestFocusFromTouch();
            errorTextcountry.setError("");
            errorTextcountry.setTextColor(Color.RED);//just to highlight that this is an error
            errorTextcountry.setText("Please! select your country");

        } else if (st.equals("")) {
            invalid = true;
            inflater = getLayoutInflater();
            state_innerreg.requestFocusFromTouch();
            text = layout.findViewById(R.id.text);
            text.setText(" Please,Select your State ");
            Toast toast = new Toast(getContext());
            toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 40);
            toast.setDuration(Toast.LENGTH_LONG);
            toast.setView(layout);
            toast.show();

        } else if (dis.equals("")) {
            invalid = true;
            district_innerreg.requestFocusFromTouch();
            text.setText(" District is required! ");

            Toast toast = new Toast(getContext());
            toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 40);
            toast.setDuration(Toast.LENGTH_LONG);
            toast.setView(layout);
            toast.show();

        }else if (uplineuser.equals("")) {
            invalid = true;
            upline_innerreg.requestFocus();
            upline_innerreg.setError("Please! fill Upline name", warning);
        } else if (errorTextpostion.getText().equals("Select Position")) {
            invalid = true;
            position_innerreg.requestFocusFromTouch();
            errorTextpostion.setError("");
            errorTextpostion.setTextColor(Color.RED);//just to highlight that this is an error
            errorTextpostion.setText("Please! Select position you want to fill");


        }else if (spon.equals("")) {
            invalid = true;
            sponsor_innerreg.requestFocus();
            sponsor_innerreg.setError("Please! fill your sponsors name", warning);

        } else if (uname.equals("")) {
            invalid = true;
            username_innerreg.requestFocusFromTouch();
            username_innerreg.setError("Please! fill username", warning);

        } else if (psd.equals("")) {
            invalid = true;
            password_innerreg.requestFocusFromTouch();
            password_innerreg.setError("Please! fill 6 character password", warning);


        } else if (!(psd.length() >= 6)) {
            invalid = true;
            password_innerreg.requestFocusFromTouch();
            password_innerreg.setError("Password must be at least 6 characters", warning);


        } else if (cpsd.equals("")) {
            invalid = true;
            confirmpassowrd_innerreg.requestFocusFromTouch();
            confirmpassowrd_innerreg.setError("Re-enter above password", warning);

        } else if (!(cpsd.length() >= 6)) {
            invalid = true;
            confirmpassowrd_innerreg.requestFocusFromTouch();
            confirmpassowrd_innerreg.setError(null);
        } else if (!psd.equals(cpsd)) {
            invalid = true;
            confirmpassowrd_innerreg.requestFocusFromTouch();
            confirmpassowrd_innerreg.setError("Password mismatch. Please try again", warning);
        } else {
            invalid = false;
            ApiInterface api = ApiClient.getClient().create(ApiInterface.class);
            Call<ResponseInnerRegistration> usercall = api.InnerReg(n, gen, dob, add, zip, con, st, dis, mob, uname, cpsd, em, spon, uplineuser, pos);
            usercall.enqueue(new Callback<ResponseInnerRegistration>() {
                @Override
                public void onResponse(Call<ResponseInnerRegistration> call, Response<ResponseInnerRegistration> response) {
                    Log.e("onResponse", new Gson().toJson(response.body()));
                    if (response.body().getStatus().equals("1")) {
                        Toast.makeText(getContext(), "Register Succesfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseInnerRegistration> call, Throwable t) {

                }
            });
        }
    }


//
//            DashBoardFragment DashboardFragment = new DashBoardFragment();
//
//            FragmentTransaction transaction = getFragmentManager().beginTransaction();
//            transaction.replace(R.id.nav_host_fragment, DashboardFragment);
//            transaction.commit();







    }



