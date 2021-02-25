package com.gipra.kelebek;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.gipra.kelebek.District.DistrictAdapter;
import com.gipra.kelebek.District.DistrictList;
import com.gipra.kelebek.District.ResponseDistrict;
import com.gipra.kelebek.State.ResponseState;
import com.gipra.kelebek.State.StateAdapter;
import com.gipra.kelebek.State.StateList;
import com.gipra.kelebek.ui.ResponseSponsorCheck;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Registration extends AppCompatActivity {
    Calendar calendar;
    EditText reg_sponsorid, reg_name, reg_address, reg_zipcode, reg_mobile, reg_email, reg_username, reg_password, reg_confirm_password;
    TextView reg_dob,text;
    Spinner reg_gender, reg_country, reg_state, reg_district;

    Button signup;
    ImageView backreg;
    String[] gen = {"Select Gender", "Female", "Male"};
    String[] country = {"Select Country", "India"};

    private ArrayList<StateList> statelist;
    private StateAdapter stateAdapter;
    private ArrayList<DistrictList> districtlist;
    private DistrictAdapter districtAdapter;

    String statecode;
    String distname;
    String statecodeba;
    String distnameba;
    String id;
    DatePickerDialog dp_reg, dp_ba;
    SimpleDateFormat dateFormatter;
    boolean invalid;
    View layout;
    LayoutInflater inflater;
    TextInputLayout sponsoridInputLayout,nameInputLayout,addressInputLayout,zipcodeInputLayout,moblieInputLayout,emailInputLayout
            ,usernameInputLayout,passwordInputLayout,confrimpassInputLayout;
    String date2;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        reg_sponsorid = findViewById(R.id.reg_sponsorid);
        reg_name = findViewById(R.id.reg_name);
        reg_dob = findViewById(R.id.reg_dob);
        reg_address = findViewById(R.id.reg_address);
        reg_country = findViewById(R.id.reg_country);
        reg_state = findViewById(R.id.reg_state);
        reg_district = findViewById(R.id.reg_district);
        reg_zipcode = findViewById(R.id.reg_zipcode);
        reg_mobile = findViewById(R.id.reg_mobile);
        reg_email = findViewById(R.id.reg_email);
        reg_username = findViewById(R.id.reg_username);
        reg_password = findViewById(R.id.reg_password);
        reg_confirm_password = findViewById(R.id.reg_confirm_password);
        signup = findViewById(R.id.signup);
        backreg = findViewById(R.id.backarrow);
        final ScrollView view = (ScrollView)findViewById(R.id.scrollView);
        view.setDescendantFocusability(ViewGroup.FOCUS_BEFORE_DESCENDANTS);
        view.setFocusable(true);
        view.setFocusableInTouchMode(true);

        sponsoridInputLayout = (TextInputLayout) findViewById(R.id.zipcodeinputlayout);
        nameInputLayout = (TextInputLayout) findViewById(R.id.mobileinputlayout);
        addressInputLayout = (TextInputLayout) findViewById(R.id.emailinputlayout);
        zipcodeInputLayout = (TextInputLayout) findViewById(R.id.zipcodeinputlayout);
        moblieInputLayout = (TextInputLayout) findViewById(R.id.mobileinputlayout);
        emailInputLayout = (TextInputLayout) findViewById(R.id.emailinputlayout);
        usernameInputLayout = (TextInputLayout) findViewById(R.id.usernameinputlayout);
        passwordInputLayout = (TextInputLayout) findViewById(R.id.passwordinputlayout);
        confrimpassInputLayout = (TextInputLayout) findViewById(R.id.confrimpassinputlayout);

        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
               view.clearFocus();
                return false;
            }
        });

        backreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                final Animation myAnim = AnimationUtils.loadAnimation(Registration.this, R.anim.bounce);
//                backreg.startAnimation(myAnim);
                startActivity(new Intent(getApplicationContext(), Login.class));
            }
        });

        reg_sponsorid.addTextChangedListener(new TextWatcher() {
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





        reg_zipcode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() != zipcodeInputLayout.getCounterMaxLength())
                    zipcodeInputLayout.setError("zipcode must be " + zipcodeInputLayout.getCounterMaxLength() + " digits");
                else
                    zipcodeInputLayout.setError(null);
            }
        });

        reg_mobile.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() != moblieInputLayout.getCounterMaxLength())
                    moblieInputLayout.setError("zipcode must be " + moblieInputLayout.getCounterMaxLength()+"digits");
                else
                    moblieInputLayout.setError(null);
            }
        });

        reg_email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String email = reg_email.getText().toString();
                if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches())
                    emailInputLayout.setError("Enter a valid email-Id !");
                else
                    emailInputLayout.setError(null);
            }
        });

        SharedPreferences shpref;
        shpref=getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
        id=shpref.getString("ID","0");
//
//        ArrayAdapter gender = new ArrayAdapter(this, android.R.layout.simple_spinner_item, gen);
//        gender.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        reg_gender.setAdapter(gender);

        ArrayAdapter con = new ArrayAdapter(this, android.R.layout.simple_spinner_item, country);
        con.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        reg_country.setAdapter(con);


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userReg();
            }
        });
        loadstate();


        String date1=reg_dob.getText().toString();
        SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy", Locale.US);
        Date dt = null;
        try {
            dt = format.parse(date1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        date2 = dateFormatter.format(dt);
        reg_dob.setInputType(InputType.TYPE_NULL);
        reg_dob.requestFocus();
        reg_dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dp_reg.show();
            }
        });
        Calendar newCalendar = Calendar.getInstance();
        dp_reg = new DatePickerDialog(Registration.this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                reg_dob.setText(dateFormatter.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
    }
    private void loadstate() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseState> usercall = apiInterface.loadstatelist();
        usercall.enqueue(new Callback<ResponseState>() {
            @Override
            public void onResponse(Call<ResponseState> call, Response<ResponseState> response) {
                Log.i("onResponse", new Gson().toJson(response.body()));
                final ResponseState responseStateList = response.body();
                statelist = (ArrayList<StateList>) responseStateList.getData();
                stateAdapter = new StateAdapter(Registration.this, statelist);
                reg_state.setAdapter(stateAdapter);
                reg_state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        statecode = statelist.get(position).getStateCode();
                        Log.e("statecode",statecode);
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

    private void SponsorCheck() {
        String sponsor=reg_sponsorid.getText().toString();
        ApiInterface api=ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseSponsorCheck>usercall=api.SponsorCheck(sponsor);
        usercall.enqueue(new Callback<ResponseSponsorCheck>() {
            @Override
            public void onResponse(Call<ResponseSponsorCheck> call, Response<ResponseSponsorCheck> response) {
                if (response.body().getStatus().equals("0")){
                    reg_sponsorid.setError(response.body().getMessage());
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

    private void loaddistrict() {
        ApiInterface api = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseDistrict> usercall = api.loaddistrictlist(statecode);
        usercall.enqueue(new Callback<ResponseDistrict>() {
            @Override
            public void onResponse(Call<ResponseDistrict> call, Response<ResponseDistrict> response) {
                Log.i("onResponse", new Gson().toJson(response.body()));
                final ResponseDistrict responseDistrict = response.body();
                districtlist = (ArrayList<DistrictList>) responseDistrict.getData();
                districtAdapter = new DistrictAdapter(getApplicationContext(), districtlist);
                reg_district.setAdapter(districtAdapter);
                reg_district.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        distname = districtlist.get(position).getDistrictId();
                        Log.e("districtname",distname);
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

    private void userReg() {
        // Toast.makeText(Registration.this, "Successfuly Registered..Login for more", Toast.LENGTH_SHORT).show();


        String sponser_uid = reg_sponsorid.getText().toString();
        String name = reg_name.getText().toString();
        //String gender = reg_gender.getSelectedItem().toString();
        String dob = date2;
        String address = reg_address.getText().toString();
        String country = reg_country.getSelectedItem().toString();
        String state = statecode;
        String district = distname;
        String zipcode = reg_zipcode.getText().toString();
        String mobile = reg_mobile.getText().toString();
        String email = reg_email.getText().toString();
        String username = reg_username.getText().toString();
        String password = reg_password.getText().toString();
        String confpassword = reg_confirm_password.getText().toString();
        TextView errorTextcountry = (TextView)reg_country.getSelectedView();
        //TextView errorTextstate = (TextView)reg_state.getSelectedView();
        invalid = false;
        Drawable warning = (Drawable)getResources().getDrawable(R.drawable.ic_warn);
        warning.setBounds(0, 0, warning.getIntrinsicWidth(), warning.getIntrinsicHeight());
        if (sponser_uid.equals("")) {
            invalid = true;
            reg_sponsorid.requestFocus();
            reg_sponsorid.setError("Please! fill your sponsors name",warning);

        } else if (name.equals("")) {
            invalid = true;
            reg_name.requestFocusFromTouch();
            reg_name.setError("Please! fill your name",warning);

        } else if (dob.equals("")) {
            invalid = true;
            reg_dob.requestFocusFromTouch();
            reg_dob.setError("Please! fill your date of birth",warning);

        } else if (address.equals("")) {
            invalid = true;
            reg_address.requestFocusFromTouch();
            reg_address.setError("Please! fill your postal address", warning);

        } else if (errorTextcountry.getText().equals("Select Country")) {
            invalid = true;
            reg_country.requestFocusFromTouch();
            errorTextcountry.setError("");
            errorTextcountry.setTextColor(Color.RED);//just to highlight that this is an error
            errorTextcountry.setText("Please! select your country");

        } else if (state.equals("")) {
            invalid = true;
            inflater = getLayoutInflater();
            layout = inflater.inflate(R.layout.custom_toast, (ViewGroup) findViewById(R.id.custom_toast_container));
            reg_state.requestFocusFromTouch();
            text = layout.findViewById(R.id.text);
            text.setText(" Please,Select your State ");

            Toast toast = new Toast(getApplicationContext());
            toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 40);
            toast.setDuration(Toast.LENGTH_LONG);
            toast.setView(layout);
            toast.show();
        } else if (district.equals("")) {
            invalid = true;
            reg_district.requestFocusFromTouch();
            text.setText(" District is required! ");

            Toast toast = new Toast(getApplicationContext());
            toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 40);
            toast.setDuration(Toast.LENGTH_LONG);
            toast.setView(layout);
            toast.show();
        }else if(zipcode.equals(""))
        {
            reg_zipcode.requestFocusFromTouch();
            reg_zipcode.setError("Please! fill your PINCode",warning);

        }else if(zipcode.length()!=6)
        {
            reg_zipcode.requestFocusFromTouch();
            reg_zipcode.setError("Postal index number must be 6 digit",warning);


        }else if (mobile.equals("")) {
            invalid = true;
            reg_mobile.requestFocusFromTouch();
            reg_mobile.setError("Please! fill your mobile number",warning);
        }else if(mobile.length()!=10)
        {
            reg_mobile.requestFocusFromTouch();
            reg_mobile.setError("Please enter a valid mobile number",warning);

        }else if (email.equals("")) {
            invalid = true;
            reg_email.requestFocusFromTouch();
            reg_email.setError("Please! fill your Email-id",warning);

        }else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
        invalid = true;
            reg_email.requestFocusFromTouch();
            reg_email.setError("Please! enter a valid email address",warning);

    }else if (username.equals("")) {
            invalid = true;
            reg_username.requestFocusFromTouch();
            reg_username.setError("Please! fill username",warning);

        } else if (password.equals("")) {
            invalid = true;
            reg_password.requestFocusFromTouch();
            reg_password.setError("Please! fill 6 character password", warning);


        }else if (!(password.length() >= 6))
        {
            invalid = true;
            reg_password.requestFocusFromTouch();
            reg_password.setError("Password must be at least 6 characters",warning);


        }else if (confpassword.equals("")) {
            invalid = true;
            reg_confirm_password.requestFocusFromTouch();
            reg_confirm_password.setError("Re-enter above password",warning);

        }
        else if (!(confpassword.length() >= 6))
        {
            invalid = true;
            reg_confirm_password.requestFocusFromTouch();
            reg_confirm_password.setError(null);
        }
        else if (!password.equals(confpassword))
        {
            invalid = true;
            reg_confirm_password.requestFocusFromTouch();
            reg_confirm_password.setError("Password mismatch. Please try again",warning);
            }
        else {
            invalid=false;
            ApiInterface api = ApiClient.getClient().create(ApiInterface.class);
            Call<ResponseUserRegistration> usercall = api.UserRegistration(sponser_uid, name, dob, address, country, state, district, zipcode, mobile, email, username, password);
            usercall.enqueue(new Callback<ResponseUserRegistration>() {
                @Override
                public void onResponse(Call<ResponseUserRegistration> call, Response<ResponseUserRegistration> response) {
                    Log.i("onResponse", new Gson().toJson(response.body()));
                    if (response.body().getStatus().equals("1")) {

                        Toast.makeText(Registration.this, "Successfuly Registered..Login for more", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), Login.class));
                    }else{
                        Toast.makeText(Registration.this, response.body().getMessage(), Toast.LENGTH_LONG).show();
                    }

                }

                @Override
                public void onFailure(Call<ResponseUserRegistration> call, Throwable t) {

                }
            });




            }
    }






}


