package com.gipra.kelebek;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Forgotpassword extends AppCompatActivity {
Button buttonsubmit;
EditText editTextuser,editTextmoblile,editTextOTP,editTextrepassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);

        editTextuser=findViewById(R.id.forgotusername);
        editTextmoblile=findViewById(R.id.forgotmobile);
        buttonsubmit=findViewById(R.id.forgotsubmit);



        buttonsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendotp();

                AlertDialog.Builder builder = new AlertDialog.Builder(
                        Forgotpassword.this, R.style.RoundedCornersDialog);
                builder.setTitle("RESET PASSWORD?");
                builder.setMessage("Enter OTP from your mail and add new password!");
                // set the custom layout
                final View customLayout = getLayoutInflater().inflate(R.layout.reset_password_layout, null);
                builder.setView(customLayout);
                builder.setNeutralButton("CANCEL",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int which) {
                            }
                        });
                builder.setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                editTextOTP = customLayout.findViewById(R.id.otp);
                                sendDialogDataToActivity(editTextOTP.getText().toString());
                                EditText editTextnewpassword = customLayout.findViewById(R.id.newpassword);
                                sendDialogDataToActivity(editTextnewpassword.getText().toString());
                                editTextrepassword = customLayout.findViewById(R.id.repassword);
                                sendDialogDataToActivity(editTextrepassword.getText().toString());
                                resetpassword();

                            }
                        });
                builder.show();
            }
        });

    }

    private void sendDialogDataToActivity(String data) {
        Toast.makeText(this, data, Toast.LENGTH_SHORT).show();
    }




    private  void sendotp(){

        SharedPreferences shpref=getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
        String u=shpref.getString("ID","");

        String username=editTextuser.getText().toString().trim();
        String mobile=editTextmoblile.getText().toString().trim();


        ApiInterface api= ApiClient.getClient().create(ApiInterface.class);

        Call<ResponseForgotPassword> usercall=api.ForgotPassword(username,mobile);
        usercall.enqueue(new Callback<ResponseForgotPassword>() {
            @Override
            public void onResponse(Call<ResponseForgotPassword> call, Response<ResponseForgotPassword> response) {
                Log.i("onResponse", new Gson().toJson(response.body()));
                if (response.body().getStatus().equals("1")){
                    Toast.makeText(Forgotpassword.this, "Reset successfully", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(Forgotpassword.this, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseForgotPassword> call, Throwable t) {

            }
        });

    }


    private  void resetpassword(){

        SharedPreferences shpref=getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
        String u=shpref.getString("ID","");

        String otp=editTextOTP.getText().toString().trim();
        String newpassword=editTextmoblile.getText().toString().trim();

        ApiInterface api= ApiClient.getClient().create(ApiInterface.class);

        Call<ResponseResetPassword> usercall=api.ResetPassword(otp,newpassword);
        usercall.enqueue(new Callback<ResponseResetPassword>() {
            @Override
            public void onResponse(Call<ResponseResetPassword> call, Response<ResponseResetPassword> response) {
                Log.i("onResponse", new Gson().toJson(response.body()));
                if (response.body().getStatus().equals("1")){
                    Toast.makeText(Forgotpassword.this, "check your inbox", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(Forgotpassword.this, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseResetPassword> call, Throwable t) {

            }
        });

    }

}
