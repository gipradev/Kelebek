package com.gipra.kelebek.MyProfile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.loader.content.CursorLoader;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.gipra.kelebek.ApiClient;
import com.gipra.kelebek.ApiInterface;
import com.gipra.kelebek.District.DistAdapter;
import com.gipra.kelebek.District.DistrictList;
import com.gipra.kelebek.District.ResponseDistrict;
import com.gipra.kelebek.MainActivity;
import com.gipra.kelebek.R;
import com.gipra.kelebek.ResponseUserImage;
import com.gipra.kelebek.ResponseUserImageView;
import com.gipra.kelebek.ResponseViewBankdetails;
import com.gipra.kelebek.State.ResponseState;
import com.gipra.kelebek.State.StAdapter;
import com.gipra.kelebek.State.StateList;
import com.google.gson.Gson;

import java.io.File;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyProfile extends AppCompatActivity {

LinearLayout llpersonal_info,llbank_info;
Button btnpersonal_info,btnbank_info,btnsocail_prof,update_profdetails,update_bankdetails;
TextView prof_userid,prof_sponsorid,prof_name,prof_dob,prof_gender,prof_mobile,myprofile_name;
    private static final String TAG = "MyProfile";
    EditText edit_dob,edit_mobile,edit_email,edit_address,edit_zipcode,edit_pannumber,edit_kycnumber;
    EditText edit_bankname,edit_branch,edit_accnum,edit_ifsc;
    Spinner edit_country,edit_state,edit_district;
    ImageView imgpan,imgkyc,imgbankproof,change_photo;

    ProgressDialog progressDialog1;
    ProgressDialog progressDialog2;
    Button btnpan,btnkyc,btnbankproof;
    private ArrayList<StateList> statelist;
    private StAdapter stateAdapter;
    private ArrayList<DistrictList> districtlist;
    private DistAdapter distAdapter;

    String statecode;
    String distname;
    String countrys;
    String[] country={"Select Country","India"};
    private static final int SELECT_PAN = 400;
    private static final  int SELECT_KYC=200;
    private static final  int SELECT_BANKPROOF=300;
    private  static final  int CHANGE_PHOTO=100;
    CircleImageView userimg;
    TextView inc_details,reff,binary_details,ewallet;
    ImageView back_myprof;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        back_myprof=findViewById(R.id.back_myprof);
        back_myprof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
            }
        });
        myprofile_name=findViewById(R.id.myprofile_name);
        inc_details=findViewById(R.id.inc_details);
        inc_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),IncomeDetails_Acc.class));
            }
        });
        reff=findViewById(R.id.reff);
        reff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Referrals.class));

            }
        });
        binary_details=findViewById(R.id.binary_details);
        binary_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),BinaryDetails_CpGBV.class));
            }
        });
        ewallet=findViewById(R.id.ewallet);
        ewallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),E_wallet.class));
            }
        });
        update_profdetails=findViewById(R.id.update_profdetails);
        update_profdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editprof();
            }
        });
        update_bankdetails=findViewById(R.id.update_bankinfo);
        update_bankdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatebankproof();
            }
        });
userimg=findViewById(R.id.imgchangephoto);
change_photo=findViewById(R.id.changephoto);
        viewdp();
        viewprofile();
        viewBankdetails();
        panView();
        kycView();
        bankProofView();


        change_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handlePermission();
                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, 100);
            }
        });
        btnpan=findViewById(R.id.btnpanupload);
        btnpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, 400);
            }
        });
        btnkyc=findViewById(R.id.btnkyc);
        btnkyc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, 200);
            }
        });
        btnbankproof=findViewById(R.id.btnbankproof);
        btnbankproof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, 300);
            }
        });
        edit_bankname=findViewById(R.id.edit_bankname);
        edit_branch=findViewById(R.id.edit_branch);
        edit_accnum=findViewById(R.id.edit_accnum);
        edit_ifsc=findViewById(R.id.edit_ifsc);

       imgpan=findViewById(R.id.imgpanupload);

        imgkyc=findViewById(R.id.imgkycupload);
        imgbankproof=findViewById(R.id.imgbankproof);

        SharedPreferences sharedPreferences;
        sharedPreferences = getSharedPreferences("MYPREF", Context.MODE_PRIVATE);

        edit_country=findViewById(R.id.edit_country);
        ArrayAdapter coun = new ArrayAdapter(this, android.R.layout.simple_spinner_item, country);
        coun.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        edit_country.setAdapter(coun);
        if (country != null) {
            int spinnerPosition = coun.getPosition(country);
            edit_country.setSelection(spinnerPosition);
        }


        edit_state=findViewById(R.id.edit_state);
        loadstate();
        edit_district=findViewById(R.id.editdistrict);

        try {
            llpersonal_info=findViewById(R.id.llpersonal_info);
            llbank_info=findViewById(R.id.llbank_info);
//            llsocial_prof=findViewById(R.id.llsocial_prof);
            btnbank_info=findViewById(R.id.btnbank_info);
            btnpersonal_info=findViewById(R.id.btnpersonal_info);
            btnsocail_prof=findViewById(R.id.btnsocial_prof);
            btnpersonal_info.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    llpersonal_info.setVisibility(View.VISIBLE);
                    llbank_info.setVisibility(View.GONE);
                  //  llsocial_prof.setVisibility(View.GONE);

                    btnpersonal_info.setBackgroundResource(R.drawable.ic_map);
                    btnpersonal_info.setTextColor(Color.WHITE);

                    btnbank_info.setBackgroundColor(Color.LTGRAY);
                    btnbank_info.setTextColor(Color.BLACK);

                    btnsocail_prof.setBackgroundColor(Color.LTGRAY);
                    btnsocail_prof.setTextColor(Color.BLACK);
                }
            });
            btnbank_info.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    llpersonal_info.setVisibility(View.GONE);
                   // llsocial_prof.setVisibility(View.GONE);
                    llbank_info.setVisibility(View.VISIBLE);

                    btnpersonal_info.setBackgroundColor(Color.LTGRAY);
                    btnpersonal_info.setTextColor(Color.BLACK);

                    btnbank_info.setBackgroundResource(R.drawable.ic_map);
                    btnbank_info.setTextColor(Color.WHITE);

                    btnsocail_prof.setBackgroundColor(Color.LTGRAY);
                    btnsocail_prof.setTextColor(Color.BLACK);
                }
            });
            btnsocail_prof.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    llpersonal_info.setVisibility(View.GONE);
                    llbank_info.setVisibility(View.GONE);
                   //llsocial_prof.setVisibility(View.VISIBLE);
                    btnpersonal_info.setBackgroundColor(Color.LTGRAY);
                    btnpersonal_info.setTextColor(Color.BLACK);
                    btnbank_info.setBackgroundColor(Color.LTGRAY);
                    btnbank_info.setTextColor(Color.BLACK);
                    btnsocail_prof.setBackgroundResource(R.drawable.ic_socialmedia);
                    btnsocail_prof.setTextColor(Color.BLACK);
                    socialmedia();

                }
            });
        }catch (Exception e){
            Log.e(TAG, "click" + e);
        }
    }
    private void socialmedia(){
        Intent intent = new Intent(android.content.Intent.ACTION_SEND);
        intent.setType("text/plain");
        String shareBodyText = "https://www.mykelebek.com/";
        intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject/Title");
        intent.putExtra(android.content.Intent.EXTRA_TEXT, shareBodyText);
        startActivity(Intent.createChooser(intent, "Share"));
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
                stateAdapter=new StAdapter(getApplicationContext(),statelist);

                edit_state.setAdapter(stateAdapter);


                edit_state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        statecode=statelist.get(position).getStateCode();
                       // edit_state.setSelection(Integer.parseInt(((ArrayList<StateList>)edit_state.getAdapter()).get(position).getStateCode()));
                        loaddist();
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
    private void loaddist(){
        ApiInterface api=ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseDistrict>usercall=api.loaddistrictlist(statecode);
        usercall.enqueue(new Callback<ResponseDistrict>() {
            @Override
            public void onResponse(Call<ResponseDistrict> call, Response<ResponseDistrict> response) {
                final  ResponseDistrict responseDistrict=response.body();
                districtlist=(ArrayList<DistrictList>)responseDistrict.getData();
                distAdapter=new DistAdapter(getApplicationContext(),districtlist);
                edit_district.setAdapter(distAdapter);
                edit_district.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 400 && resultCode == RESULT_OK && data != null) {

            final Uri panimage = data.getData();
            if (null !=panimage){
                String path=getpanpath(panimage);
                Log.i(TAG, "Image Path : " + path);

                findViewById(R.id.imgpanupload).post(new Runnable() {
                    @Override
                    public void run() {
                        ((ImageView)findViewById(R.id.imgpanupload)).setImageURI(panimage);
                        panupload(panimage,"PAN");
                    }
                });
            }

        }
        if (requestCode==200 && resultCode==RESULT_OK && data!=null){
            final Uri kycimage=data.getData();
            if (null!=kycimage){
                String path=getkycpath(kycimage);
                Log.i(TAG, "Kyc"+path);
                findViewById(R.id.imgkycupload).post(new Runnable() {
                    @Override
                    public void run() {
                        ((ImageView)findViewById(R.id.imgkycupload)).setImageURI(kycimage);
                        kycupload(kycimage,"KYC");
                    }
                });
            }
        }
        if (requestCode==300 && resultCode==RESULT_OK && data!=null){
            final Uri bankproofimage=data.getData();
            if (null!=bankproofimage){
                String path=getbankproofpath(bankproofimage);
                Log.i(TAG, "BankProof"+path);
                findViewById(R.id.imgbankproof).post(new Runnable() {
                    @Override
                    public void run() {
                        ((ImageView)findViewById(R.id.imgbankproof)).setImageURI(bankproofimage);
                        bankproofupload(bankproofimage,"BANK");
                    }
                });
            }
        }
        if (requestCode==100 && resultCode==RESULT_OK && data!=null){
            final Uri changephoto=data.getData();
            if (null!=changephoto){
                String path=getchangephotopath(changephoto);
                Log.i(TAG, "BankProof"+path);
                userimg.post(new Runnable() {
                    @Override
                    public void run() {
                       userimg.setImageURI(changephoto);
                       imageupload(changephoto,"DP");
                    }
                });

            }
        }
    }

    private String getpanpath(Uri contentUri) {
        String[] proj = {MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(this, contentUri, proj, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(column_index);
        cursor.close();
        return result;
    }
    private  String getkycpath(Uri kycUri){
        String[] kyc={MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(this, kycUri, kyc, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(column_index);
        cursor.close();
        return result;
    }
    private  String getbankproofpath(Uri bankUri){
        String[] bank={MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(this, bankUri, bank, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(column_index);
        cursor.close();
        return result;
    }
    private  String getchangephotopath(Uri photoUri){
        String[] photo={MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(this, photoUri, photo, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(column_index);
        cursor.close();
        return result;
    }
    private void handlePermission(){
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return;
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            //ask for permission
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    CHANGE_PHOTO  );
        }else if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            //ask for permission
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    SELECT_PAN);
        }
        else if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            //ask for permission
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    SELECT_KYC);
        }
        else if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            //ask for permission
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    SELECT_BANKPROOF);

    }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case CHANGE_PHOTO:
                for (int i = 0; i < permissions.length; i++) {
                    String permission = permissions[i];
                    if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                        boolean showRationale = ActivityCompat.shouldShowRequestPermissionRationale(this, permission);
                        if (showRationale) {
                            //  Show your own message here
                        } else {
                            showSettingsAlert();
                        }
                    }
                }
            case SELECT_PAN:
                for (int i = 0; i < permissions.length; i++) {
                    String permission = permissions[i];
                    if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                        boolean showRationale = ActivityCompat.shouldShowRequestPermissionRationale(this, permission);
                        if (showRationale) {
                            //  Show your own message here
                        } else {
                            showSettingsAlert();
                        }
                    }
                }
            case SELECT_KYC:
                for (int i = 0; i < permissions.length; i++) {
                    String permission = permissions[i];
                    if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                        boolean showRationale = ActivityCompat.shouldShowRequestPermissionRationale(this, permission);
                        if (showRationale) {
                            //  Show your own message here
                        } else {
                            showSettingsAlert();
                        }
                    }
                }
            case SELECT_BANKPROOF:
                for (int i = 0; i < permissions.length; i++) {
                    String permission = permissions[i];
                    if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                        boolean showRationale = ActivityCompat.shouldShowRequestPermissionRationale(this, permission);
                        if (showRationale) {
                            //  Show your own message here
                        } else {
                            showSettingsAlert();
                        }
                    }
                }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
    private void showSettingsAlert() {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("App needs to access the Camera.");
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "DON'T ALLOW",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        //finish();
                    }
                });
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "SETTINGS",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        openAppSettings(MyProfile.this);
                    }
                });
        alertDialog.show();
    }
    public static void openAppSettings(final Activity context) {
        if (context == null) {
            return;
        }
        final Intent i = new Intent();
        i.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        i.addCategory(Intent.CATEGORY_DEFAULT);
        i.setData(Uri.parse("package:" + context.getPackageName()));
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        i.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
        context.startActivity(i);
    }
    private void imageupload(Uri fileUri, String userid){
        File file=new File(getchangephotopath(fileUri));
        RequestBody requestBody=RequestBody.create(MediaType.parse(getContentResolver().getType(fileUri)),file);
        SharedPreferences sharedPreferences;
        sharedPreferences = getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
        String memberid=sharedPreferences.getString("ID","");
        ApiInterface api=ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseUserImage>usercall=api.userimageupload(requestBody, Integer.valueOf(memberid));
        usercall.enqueue(new Callback<ResponseUserImage>() {
            @Override
            public void onResponse(Call<ResponseUserImage> call, Response<ResponseUserImage> response) {
                Log.i("onResponse", new Gson().toJson(response.body()));
                if (response.body().getStatus().equals("1")){
                    Toast.makeText(MyProfile.this, "Profile picture updated successfully", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MyProfile.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ResponseUserImage> call, Throwable t) {

            }
        }); }
    private void viewdp(){
        SharedPreferences shpref;
        shpref=getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
        String u=shpref.getString("ID","");
        Log.e("idsssss",u);
        ApiInterface api=ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseUserImageView>usercall=api.userImageView(Integer.parseInt(u));
        usercall.enqueue(new Callback<ResponseUserImageView>() {
            @Override
            public void onResponse(Call<ResponseUserImageView> call, Response<ResponseUserImageView> response) {
                Log.e("TAG", new Gson().toJson(response.body()));
                String image=response.body().getPath();

                if (response.body().getStatus().equals("1")){
                   String imgpath = response.body().getPath();
                    Log.e("imagepath view",imgpath);
                    Glide.with(getApplicationContext())
                            .load(imgpath)
                            .override(130,120)
                            .error(R.drawable.ic_userprofil)
                            .into(userimg);


//                   Glide.with(getApplicationContext())
//                            .load(imgpath)
//                            .into(userimg);
                    SharedPreferences sharedPreferences;
                    sharedPreferences = getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString("IMAGE",image);
                    editor.apply();
                }
            }
            @Override
            public void onFailure(Call<ResponseUserImageView> call, Throwable t) {

            }
        });
    }
    private void viewprofile(){
        SharedPreferences shpref;
        shpref=getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
        String u=shpref.getString("ID","");
        ApiInterface api=ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseViewProfile>usercall=api.viewprofile(Integer.parseInt(u));
        usercall.enqueue(new Callback<ResponseViewProfile>() {
            @Override
            public void onResponse(Call<ResponseViewProfile> call, Response<ResponseViewProfile> response) {
                Log.e(TAG, new Gson().toJson(response.body()));
                String uname=response.body().getCUsername();
                String sponsorUsername=response.body().getSponsorUsername();
                String cfname=response.body().getCFNAME();
                String cgender=response.body().getCGENDER();
                String cmobile=response.body().getCMOBILE();
                String address=response.body().getCADDRESS();
                String czipcode=response.body().getCZIPCODE();
                String email=response.body().getCEMAIL();
                String dob=response.body().getCDOB();
//                countrys=response.body().getCountryName();
//                String state=response.body().getStateName();
//                //String district=response.body().di();
                String pannumber=response.body().getCPAN();
                String kycnumber=response.body().getNKycNumber();



                if(response.body().getStatus().equals("1")){
                    prof_userid=findViewById(R.id.prof_userid);
                    prof_userid.setText(uname);
                    prof_sponsorid=findViewById(R.id.prof_sponsorid);
                    prof_sponsorid.setText(sponsorUsername);
                    prof_gender=findViewById(R.id.prof_gender);
                    prof_gender.setText(cgender);
                    prof_name=findViewById(R.id.prof_name);
                    prof_name.setText(cfname);
                    myprofile_name.setText(cfname);
                    prof_mobile=findViewById(R.id.prof_mobile);
                    prof_mobile.setText(cmobile);
                    prof_dob=findViewById(R.id.prof_dob);
                    prof_dob.setText(dob);

                    edit_dob=findViewById(R.id.edit_dob);
                    edit_dob.setText(dob);
                    edit_email=findViewById(R.id.edit_email);
                    edit_email.setText(email);
                    edit_mobile=findViewById(R.id.edit_mobile);
                    edit_mobile.setText(cmobile);
                    edit_address=findViewById(R.id.edit_address);
                    edit_address.setText(address);
                    edit_zipcode=findViewById(R.id.edit_zipcode);
                    edit_zipcode.setText(czipcode);

                    edit_pannumber=findViewById(R.id.edit_pancard);
                    edit_pannumber.setText(pannumber);
                    edit_kycnumber=findViewById(R.id.edit_kycnumber);
                    edit_kycnumber.setText(kycnumber);


                }
            }
            @Override
            public void onFailure(Call<ResponseViewProfile> call, Throwable t) {

            }
        });
    }

    private void viewBankdetails(){
        SharedPreferences shpref;
        shpref=getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
        String u=shpref.getString("ID","");
        ApiInterface api=ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseViewBankdetails>usercall=api.viewbankdetails(Integer.parseInt(u));
        usercall.enqueue(new Callback<ResponseViewBankdetails>() {
            @Override
            public void onResponse(Call<ResponseViewBankdetails> call, Response<ResponseViewBankdetails> response) {
                Log.e(TAG, new Gson().toJson(response.body()));


                String bankname=response.body().getCBANK();
                String branch=response.body().getCBRANCH();
                String accno=response.body().getCACCNO();
                String ifscno=response.body().getCIFCCODE();

                if(response.body().getStatus().equals("1")){

                    edit_bankname.setText(bankname);
                    edit_branch.setText(branch);
                    edit_accnum.setText(accno);
                    edit_ifsc.setText(ifscno);


                }
            }

            @Override
            public void onFailure(Call<ResponseViewBankdetails> call, Throwable t) {

            }


        });
    }

    private void kycView(){
        SharedPreferences shpref;
        shpref=getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
        String u=shpref.getString("ID","");
        Log.e("idsssss",u);
        ApiInterface api=ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseKycView>usercall=api.KycView(Integer.parseInt(u));
        usercall.enqueue(new Callback<ResponseKycView>() {
            @Override
            public void onResponse(Call<ResponseKycView> call, Response<ResponseKycView> response) {
                Log.i("panview", new Gson().toJson(response.body()));
                String image=response.body().getPath();
                Log.e("panview",image);
                if (response.body().getStatus().equals("1")){
                    String imgpath = response.body().getPath();

                    Glide.with(getApplicationContext())
                            .load(imgpath)
                            .override(200,200)
                            .error(R.drawable.imagenotfound)
                            .into(imgkyc);
                    SharedPreferences sharedPreferences;
                    sharedPreferences = getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString("KYCIMAGE",image);
                    editor.apply();
                }
            }
            @Override
            public void onFailure(Call<ResponseKycView> call, Throwable t) {

            }
        });
    }
    private void panView(){
        SharedPreferences shpref;
        shpref=getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
        String u=shpref.getString("ID","");
        Log.e("id",u);
        ApiInterface api=ApiClient.getClient().create(ApiInterface.class);
        Call<ResponsePanView>usercall=api.PanView(Integer.parseInt(u));
        usercall.enqueue(new Callback<ResponsePanView>() {
            @Override
            public void onResponse(Call<ResponsePanView> call, Response<ResponsePanView> response) {
                Log.i("imageview", new Gson().toJson(response.body()));
                String image=response.body().getPath();
                Log.e("imagepath view",image);
                if (response.body().getStatus().equals("1")){
                    String imgpath = response.body().getPath();

                    Glide.with(getApplicationContext())
                            .load(imgpath)
                            .override(200,200)
                            .error(R.drawable.imagenotfound)
                            .into(imgpan);
                    SharedPreferences sharedPreferences;
                    sharedPreferences = getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString("PANIMAGE",image);
                    editor.apply();
                }
            }
            @Override
            public void onFailure(Call<ResponsePanView> call, Throwable t) {

            }
        });
    }



    private void bankProofView(){
        SharedPreferences shpref;
        shpref=getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
        String u=shpref.getString("ID","");
        Log.e("idu",u);
        ApiInterface api=ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseBankProofView>usercall=api.BankProofView(Integer.parseInt(u));
        usercall.enqueue(new Callback<ResponseBankProofView>() {
            @Override
            public void onResponse(Call<ResponseBankProofView> call, Response<ResponseBankProofView> response) {
                Log.i("bankproodf", new Gson().toJson(response.body()));
                String image=response.body().getPath();
                Log.e("bankproof view",image);
                if (response.body().getStatus().equals("1")){
                    String imgpath = response.body().getPath();

                    Glide.with(getApplicationContext())
                            .load(imgpath)
                            .override(200,200)
                            .error(R.drawable.imagenotfound)
                            .into(imgbankproof);
                    SharedPreferences sharedPreferences;
                    sharedPreferences = getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString("BNKPROFIMAGE",image);
                    editor.apply();
                }
            }
            @Override
            public void onFailure(Call<ResponseBankProofView> call, Throwable t) {

            }
        });
    }
    private void editprof(){
        progressDialog1 = new ProgressDialog(getApplicationContext());
        progressDialog1.setCancelable(true);
        progressDialog1.setMessage("updating...");
        progressDialog1.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog1.show();
        SharedPreferences shpref;
        shpref=getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
        String u=shpref.getString("ID","");
        String dob=edit_dob.getText().toString();
        String mob=edit_mobile.getText().toString();
        String em=edit_email.getText().toString();
        String add=edit_address.getText().toString();
        String con=edit_country.getSelectedItem().toString();
        String st=edit_state.getSelectedItem().toString();
        String dis=edit_district.getSelectedItem().toString();
        String zip=edit_zipcode.getText().toString();
        String pan=edit_pannumber.getText().toString();
        String kyc=edit_kycnumber.getText().toString();
        ApiInterface api=ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseEditProfile>usercall=api.editprofile(u,dob,mob,em,add,con,st,dis,zip,pan,kyc);
        usercall.enqueue(new Callback<ResponseEditProfile>() {
            @Override
            public void onResponse(Call<ResponseEditProfile> call, Response<ResponseEditProfile> response) {
                Log.e(TAG, new Gson().toJson(response.body()));
                if (response.body().getStatus().equals("1")){
                    progressDialog1.dismiss();
                    Toast.makeText(MyProfile.this, "Profile Updated Succesfully", Toast.LENGTH_SHORT).show();
                }else{
                    progressDialog1.dismiss();
                    Toast.makeText(MyProfile.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseEditProfile> call, Throwable t) {

            }
        });
    }
    private  void updatebankproof(){
        progressDialog2 = new ProgressDialog(getApplicationContext());
        progressDialog2.setCancelable(true);
        progressDialog2.setMessage("updating...");
        progressDialog2.show();
        SharedPreferences shpref;
        shpref=getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
        String u=shpref.getString("ID","");
        String bank=edit_bankname.getText().toString();
        String br=edit_branch.getText().toString();
        String accnum=edit_accnum.getText().toString();
        String ifsc=edit_ifsc.getText().toString();
        ApiInterface api=ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseBankEditProfile>usercall=api.BankEditProfile(u,bank,br,accnum,ifsc);
        usercall.enqueue(new Callback<ResponseBankEditProfile>() {
            @Override
            public void onResponse(Call<ResponseBankEditProfile> call, Response<ResponseBankEditProfile> response) {
                Log.e(TAG, new Gson().toJson(response.body()));
                if(response.body().getStatus().equals("1")){
                    progressDialog2.dismiss();
                    Toast.makeText(MyProfile.this, "Bank details updated successfully", Toast.LENGTH_SHORT).show();
                }
                else{
                    progressDialog2.dismiss();
                    Toast.makeText(MyProfile.this, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBankEditProfile> call, Throwable t) {

            }
        });
    }
    private void panupload(Uri fileUri,String userid){
        SharedPreferences shpref;
        shpref=getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
        String u=shpref.getString("ID","");
        File file=new File(getpanpath(fileUri));
        RequestBody requestBody=RequestBody.create(MediaType.parse(getContentResolver().getType(fileUri)),file);

        ApiInterface api=ApiClient.getClient().create(ApiInterface.class);
        Call<ResponsePanUpload>usercall=api.Panupload(requestBody, Integer.parseInt(u));
        usercall.enqueue(new Callback<ResponsePanUpload>() {
            @Override
            public void onResponse(Call<ResponsePanUpload> call, Response<ResponsePanUpload> response) {
                Log.e(TAG, new Gson().toJson(response.body()));
                if (response.body().getStatus().equals("1")){
                    Toast.makeText(MyProfile.this, "Pan card updated successfully", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MyProfile.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponsePanUpload> call, Throwable t) {

            }
        });

    }
    private void kycupload(Uri fileUri,String userid){
        SharedPreferences shpref;
        shpref=getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
        String u=shpref.getString("ID","");
        File file=new File(getkycpath(fileUri));
        RequestBody requestBody=RequestBody.create(MediaType.parse(getContentResolver().getType(fileUri)),file);
        ApiInterface api=ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseAadharUpload>usercall=api.Aadharupload(requestBody, Integer.parseInt(u));
        usercall.enqueue(new Callback<ResponseAadharUpload>() {
            @Override
            public void onResponse(Call<ResponseAadharUpload> call, Response<ResponseAadharUpload> response) {
                Log.e(TAG, new Gson().toJson(response.body()));
                if (response.body().getStatus().equals("1")){
                    Toast.makeText(MyProfile.this, "Aadhaar card updated succesfully", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MyProfile.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseAadharUpload> call, Throwable t) {

            }
        });


    }
    private void bankproofupload(Uri fileUri,String userid){
        SharedPreferences shpref;
        shpref=getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
        String u=shpref.getString("ID","");
        File file=new File(getbankproofpath(fileUri));
        RequestBody requestBody=RequestBody.create(MediaType.parse(getContentResolver().getType(fileUri)),file);
        ApiInterface api=ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseAccountProof>usercall=api.Accountproofupload(requestBody, Integer.parseInt(u));
        usercall.enqueue(new Callback<ResponseAccountProof>() {
            @Override
            public void onResponse(Call<ResponseAccountProof> call, Response<ResponseAccountProof> response) {
                Log.e(TAG, new Gson().toJson(response.body()));
                if (response.body().getStatus().equals("1")){
                    Toast.makeText(MyProfile.this, "Account proof updated successfully", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MyProfile.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseAccountProof> call, Throwable t) {

            }
        });

    }
    public void onBackPressed(){
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }
}
