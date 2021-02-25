package com.gipra.kelebek.ui.Genealogy;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
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
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.gipra.kelebek.ApiClient;
import com.gipra.kelebek.ApiInterface;
import com.gipra.kelebek.District.DistrictAdapter;
import com.gipra.kelebek.District.DistrictList;
import com.gipra.kelebek.District.ResponseDistrict;
import com.gipra.kelebek.MainActivity;
import com.gipra.kelebek.R;
import com.gipra.kelebek.ResponseInnerRegistration;
import com.gipra.kelebek.State.ResponseState;
import com.gipra.kelebek.State.StateAdapter;
import com.gipra.kelebek.State.StateList;
import com.gipra.kelebek.ui.ResponseSponsorCheck;
import com.gipra.kelebek.ui.ResponseUplineCheck;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegVacant extends AppCompatActivity {
    Button regvacant;
    TextView dob_regvacant,text;
    ImageView back_regvacant;
    View layout;
    LayoutInflater inflater;

    EditText name_regvacant,mobile_regvacant,email_regvacant,address_regvacant,zipcode_regvacant,username_regvacant,sponsor_regvacant,upline_regvacant,password_regvacant,confirmpassowrd_regvacant;
    Spinner gender_regvacant,country_regvacant,state_regvacant,district_regvacant,position_regvacant;
    String[] gen={"Select Gender","Female","Male"};
    String[] country={"Select Country","India"};
    String[] position={"Left","Right"};
    private ArrayList<StateList> statelist;
    private StateAdapter stateAdapter;
    private ArrayList<DistrictList> districtlist;
    private DistrictAdapter districtAdapter;

    String statecode;
    String distname;
    DatePickerDialog dp;
    SimpleDateFormat dateFormatter;
    boolean invalid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_vacant);
        back_regvacant=findViewById(R.id.back_regvacant);
        back_regvacant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });
        name_regvacant=findViewById(R.id.name_regvacant);
        mobile_regvacant=findViewById(R.id.mobile_regvacant);
        email_regvacant=findViewById(R.id.email_regvacant);
        address_regvacant=findViewById(R.id.address_regvacant);
        zipcode_regvacant=findViewById(R.id.zipcode_regvacant);
        username_regvacant=findViewById(R.id.username_regvacant);
        sponsor_regvacant=findViewById(R.id.sponsorid_regvacant);
        upline_regvacant=findViewById(R.id.upline_regvacant);
        password_regvacant=findViewById(R.id.password_regvacant);
        confirmpassowrd_regvacant=findViewById(R.id.confirm_password_regvacant);
        regvacant=findViewById(R.id.regvacant);
        regvacant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VacantReg();
            }
        });



        upline_regvacant.addTextChangedListener(new TextWatcher() {
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
        sponsor_regvacant.addTextChangedListener(new TextWatcher() {
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






        gender_regvacant=findViewById(R.id.gender_regvacant);
        ArrayAdapter aa = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_spinner_item, gen);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gender_regvacant.setAdapter(aa);

        country_regvacant=findViewById(R.id.country_regvacant);
        ArrayAdapter coun = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_spinner_item, country);
        coun.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        country_regvacant.setAdapter(coun);

        position_regvacant=findViewById(R.id.position_regvacant);
        ArrayAdapter pos = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_spinner_item, position);
        pos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        position_regvacant.setAdapter(pos);

        state_regvacant=findViewById(R.id.state_regvacant);
        loadstate();
        district_regvacant=findViewById(R.id.district_regvacant);


        dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);

        dob_regvacant=findViewById(R.id.dob_regvacant);

        dob_regvacant.requestFocus();


        dob_regvacant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dp.show();
            }
        });


        Calendar newCalendar = Calendar.getInstance();
        dp = new DatePickerDialog(RegVacant.this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                dob_regvacant.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

    }

    private void uplineCheck(){
        String upline=upline_regvacant.getText().toString();
        ApiInterface api=ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseUplineCheck>usercall=api.UplineCheck(upline);
        usercall.enqueue(new Callback<ResponseUplineCheck>() {
            @Override
            public void onResponse(Call<ResponseUplineCheck> call, Response<ResponseUplineCheck> response) {
                if (response.body().getStatus().equals("0")){
                    upline_regvacant.setError(response.body().getMessage());

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
        String sponsor=sponsor_regvacant.getText().toString();
        ApiInterface api=ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseSponsorCheck>usercall=api.SponsorCheck(sponsor);
        usercall.enqueue(new Callback<ResponseSponsorCheck>() {
            @Override
            public void onResponse(Call<ResponseSponsorCheck> call, Response<ResponseSponsorCheck> response) {
                if (response.body().getStatus().equals("0")){
                    sponsor_regvacant.setError(response.body().getMessage());
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
                Log.i("onResponse", new Gson().toJson(response.body()));
                final ResponseState responseState=response.body();
                statelist=(ArrayList<StateList>)responseState.getData();
                stateAdapter=new StateAdapter(getApplicationContext(),statelist);
                state_regvacant.setAdapter(stateAdapter);
                state_regvacant.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
                final  ResponseDistrict responseDistrict=response.body();
                districtlist=(ArrayList<DistrictList>)responseDistrict.getData();
                districtAdapter=new DistrictAdapter(getApplicationContext(),districtlist);
                district_regvacant.setAdapter(districtAdapter);
                district_regvacant.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

    private  void VacantReg() {
        String n = name_regvacant.getText().toString();
        String gen = gender_regvacant.getSelectedItem().toString();
        String dob = dob_regvacant.getText().toString();
        String mob = mobile_regvacant.getText().toString();
        String em = email_regvacant.getText().toString();
        String add = address_regvacant.getText().toString();
        String zip = zipcode_regvacant.getText().toString();
        String con = country_regvacant.getSelectedItem().toString();
        String st = statecode;
        String dis = distname;
        String uname = username_regvacant.getText().toString();
        String spon = sponsor_regvacant.getText().toString();
        String uplineuser = upline_regvacant.getText().toString();
        String pos = position_regvacant.getSelectedItem().toString();
        String psd = password_regvacant.getText().toString();
        String conpsd = confirmpassowrd_regvacant.getText().toString();
        TextView errorTextcountry = (TextView)country_regvacant.getSelectedView();
        TextView errorTextgender = (TextView) gender_regvacant.getSelectedView();
        TextView errorTextpostion = (TextView) position_regvacant.getSelectedView();

        invalid = false;
        Drawable warning = (Drawable) getResources().getDrawable(R.drawable.ic_warn);
        warning.setBounds(0, 0, warning.getIntrinsicWidth(), warning.getIntrinsicHeight());

        if (n.equals("")) {
            invalid = true;
            name_regvacant.requestFocusFromTouch();
            name_regvacant.setError("Please! fill your name", warning);

        }else if (errorTextgender.getText().equals("Select Gender")) {
            invalid = true;
            gender_regvacant.requestFocusFromTouch();
            errorTextgender.setError("");
            errorTextgender.setTextColor(Color.RED);//just to highlight that this is an error
            errorTextgender.setText("Please! select gender");
        } else if (dob.equals("")) {
            invalid = true;
            dob_regvacant.requestFocusFromTouch();
            dob_regvacant.setError("Please! fill your date of birth", warning);

        } else if (mob.equals("")) {
            invalid = true;
            mobile_regvacant.requestFocusFromTouch();
            mobile_regvacant.setError("Please! fill your mobile number", warning);

        } else if (mob.length() != 10) {
            mobile_regvacant.requestFocusFromTouch();
            mobile_regvacant.setError("Please enter a valid mobile number", warning);

        } else if (em.equals("")) {
            invalid = true;
            email_regvacant.requestFocusFromTouch();
            email_regvacant.setError("Please! fill your Email-id", warning);

        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(em).matches()) {
            invalid = true;
            email_regvacant.requestFocusFromTouch();
            email_regvacant.setError("Please! enter a valid email address", warning);

        } else if (add.equals("")) {
            invalid = true;
            address_regvacant.requestFocusFromTouch();
            address_regvacant.setError("Please! fill your postal address", warning);
        } else if (zip.equals("")) {
            zipcode_regvacant.requestFocusFromTouch();
            zipcode_regvacant.setError("Please! fill your PINCode", warning);

        } else if (zip.length() != 6) {
            zipcode_regvacant.requestFocusFromTouch();
            zipcode_regvacant.setError("Postal index number must be 6 digit", warning);

        } else if (errorTextcountry.getText().equals("Select Country")) {
            invalid = true;
            country_regvacant.requestFocusFromTouch();
            errorTextcountry.setError("");
            errorTextcountry.setTextColor(Color.RED);//just to highlight that this is an error
            errorTextcountry.setText("Please! select your country");

        } else if (st.equals("")) {
            invalid = true;
            inflater = getLayoutInflater();
            state_regvacant.requestFocusFromTouch();
            text = layout.findViewById(R.id.text);
            text.setText(" Please,Select your State ");
            Toast toast = new Toast(this);
            toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 40);
            toast.setDuration(Toast.LENGTH_LONG);
            toast.setView(layout);
            toast.show();

        } else if (dis.equals("")) {
            invalid = true;
            district_regvacant.requestFocusFromTouch();
            text.setText(" District is required! ");

            Toast toast = new Toast(this);
            toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 40);
            toast.setDuration(Toast.LENGTH_LONG);
            toast.setView(layout);
            toast.show();

        }else if (uplineuser.equals("")) {
            invalid = true;
            upline_regvacant.requestFocus();
            upline_regvacant.setError(null, warning);

        } else if (errorTextpostion.getText().equals("Select Position")) {
            invalid = true;
            position_regvacant.requestFocusFromTouch();
            errorTextpostion.setError("");
            errorTextpostion.setTextColor(Color.RED);//just to highlight that this is an error
            errorTextpostion.setText("Please! Select position you want to fill");

        }else if (spon.equals("")) {
            invalid = true;
            sponsor_regvacant.requestFocus();
            sponsor_regvacant.setError("Please! fill your sponsors name", warning);

        } else if (uname.equals("")) {
            invalid = true;
            username_regvacant.requestFocusFromTouch();
            username_regvacant.setError("Please! fill username", warning);

        } else if (psd.equals("")) {
            invalid = true;
            password_regvacant.requestFocusFromTouch();
            password_regvacant.setError("Please! fill 6 character password", warning);

        } else if (!(psd.length() >= 6)) {
            invalid = true;
            password_regvacant.requestFocusFromTouch();
            password_regvacant.setError("Password must be at least 6 characters", warning);

        } else if (conpsd.equals("")) {
            invalid = true;
            confirmpassowrd_regvacant.requestFocusFromTouch();
            confirmpassowrd_regvacant.setError("Re-enter above password", warning);

        } else if (!(conpsd.length() >= 6)) {
            invalid = true;
            confirmpassowrd_regvacant.requestFocusFromTouch();
            confirmpassowrd_regvacant.setError(null);
        } else if (!psd.equals(conpsd)) {
            invalid = true;
            confirmpassowrd_regvacant.requestFocusFromTouch();
            confirmpassowrd_regvacant.setError("Password mismatch. Please try again", warning);
        } else {
            invalid = false;

//        SharedPreferences shpref;
////        shpref=getContext().getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
////        String refid=shpref.getString("USER_NAME","");
            ApiInterface api = ApiClient.getClient().create(ApiInterface.class);
            Call<ResponseInnerRegistration> usercall = api.InnerReg(n, em, mob, gen, dob, add, zip, con, dis, st, spon, uplineuser, pos, conpsd, uname);
            usercall.enqueue(new Callback<ResponseInnerRegistration>() {
                @Override
                public void onResponse(Call<ResponseInnerRegistration> call, Response<ResponseInnerRegistration> response) {
                    Log.e("onResponse", new Gson().toJson(response.body()));
                    if (response.body().getStatus().equals("1")) {
                        // startActivity(new Intent(getActivity(), ChangePassword.class));
                        Toast.makeText(getApplicationContext(), "Register Successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseInnerRegistration> call, Throwable t) {

                }
            });
        }
    }
}


