package com.gipra.kelebek;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {
    EditText username,password;
    Button sumbit;
    TextView reghere,forgot;

    private static final String TAG = "Login";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        sumbit=findViewById(R.id.submit);
        sumbit.setVisibility(View.VISIBLE);
        sumbit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sumbit.setVisibility(View.VISIBLE);
                    login();

            }
        });
        reghere=findViewById(R.id.reghere);
        reghere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Registration.class));
            }
        });
        forgot=findViewById(R.id.forgot);
        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(getApplicationContext(),Forgotpassword.class));
                startActivity(new Intent(getApplicationContext(), Forgotpassword.class));
            }
        });
    }

    private void login(){
        sumbit.setVisibility(View.VISIBLE);
        boolean valid=true;
        String u=username.getText().toString();
        String p=password.getText().toString();
        if (TextUtils.isEmpty(u)) {
            username.setError("Username is not entered");
            username.requestFocus();
            valid = false;
        }else if (TextUtils.isEmpty(p)) {
            password.setError("Please enter Password");
            password.requestFocus();
            valid = false;
        }else if (p.length() < 6) {
            password.setError("Password must contain 6  characters");
            password.requestFocus();
            valid = false;
        }else if(valid=true){
            u = username.getText().toString();
            p = password.getText().toString();
            final ProgressDialog progressDialog = new ProgressDialog(Login.this);
            progressDialog.setCancelable(true);
            progressDialog.setMessage("Please Wait");
            progressDialog.show();
            ApiInterface api = ApiClient.getClient().create(ApiInterface.class);
            Call<ResponseLogin> usercall = api.login(u, p);
            final String finalP = p;
            usercall.enqueue(new Callback<ResponseLogin>() {
                @Override
                public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                    sumbit.setVisibility(View.VISIBLE);
                    Log.e(TAG, new Gson().toJson(response.body()));

                    SharedPreferences sharedPreferences;
                    sharedPreferences = getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
                    String name = response.body().getName();
                    String uname = response.body().getUsername();
                    String id = response.body().getId();
                    String mobile = response.body().getMobile();
                    String email = response.body().getEmail();
                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    editor.putString("CUST_NAME", name);
                    editor.putString("USER_NAME", uname);
                    editor.putString("PASS", finalP);
                    editor.putString("ID", id);
                    editor.putString("MOBILE", mobile);
                    editor.putString("EMAIL", email);


                    editor.apply();

                    if (response.body().getStatus().equals("1")) {
                        sumbit.setVisibility(View.VISIBLE);
                        progressDialog.dismiss();
                        //Animation animmove = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.move);
                        //sumbit.startAnimation(animmove);
                        //overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        sumbit.setVisibility(View.VISIBLE);
                        //finish();
                    } else {
                        progressDialog.dismiss();
                        sumbit.setVisibility(View.VISIBLE);
                        Toast.makeText(Login.this, "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        sumbit.setVisibility(View.VISIBLE);
                    }
                }

                @Override
                public void onFailure(Call<ResponseLogin> call, Throwable t) {

                }
            });
        }
    }

    public  void onBackPressed(){
            AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
            builder.setIcon(R.drawable.ic_warning);
            builder.setTitle("Are You Sure You Want To Exit?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
        Intent a=new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        a.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(a);
        finish();

                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();

        }


    }

