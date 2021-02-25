package com.gipra.kelebek;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePassword extends AppCompatActivity {
    EditText currentpsd,newpsd,confirmpsd;
    Button submitpsd,cancelpsd;
    ImageView back_changepsd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        back_changepsd=findViewById(R.id.back_changepsd);
        back_changepsd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });
        currentpsd=findViewById(R.id.currentpsd);
        newpsd=findViewById(R.id.newpsd);
        confirmpsd=findViewById(R.id.confirmpsd);
        submitpsd=findViewById(R.id.sumbitpsd);
        submitpsd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()) {
                    changepsd();
                }

            }
        });
//        cancelpsd=findViewById(R.id.cancelpsd);
//        cancelpsd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                currentpsd.setText("");
//                newpsd.setText("");
//                confirmpsd.setText("");
//            }
//        });
    }
    public  Boolean validate() {
        boolean valid = true;
        String cur = currentpsd.getText().toString();
        String n = newpsd.getText().toString();
        String con = confirmpsd.getText().toString();
        String currentpass=currentpsd.getText().toString();
        SharedPreferences shpref=getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
        String oldpass=shpref.getString("PASS","");
        if (cur.isEmpty()) {
            valid=false;
            currentpsd.setError("Please enter password");
        } else if (!currentpass.equals(oldpass)) {
            valid=false;
            currentpsd.setError("Wrong password,please enter your old password");

        } else if (n.isEmpty()) {
            valid=false;
            newpsd.setError("Please enter new password");
        } else if (!(n.length() >= 6)) {
            valid=false;
            confirmpsd.setError("Please enter confirm password");
        } else if (con.isEmpty()) {
            valid=false;
            confirmpsd.setError("Please enter confirm password");

        } else if (!con.equals(n)) {
            valid=false;
            confirmpsd.setError("Password not matching");

        }else{
                valid = true;
                changepsd();
            }

            return valid;
        }

    private  void changepsd(){


        SharedPreferences shpref=getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
        String u=shpref.getString("ID","");
        String npsd=newpsd.getText().toString();
        String oldpsd=shpref.getString("PASS","");
        //String cpsd=confirmpsd.getText().toString();

        ApiInterface api= ApiClient.getClient().create(ApiInterface.class);

        Call<ResponseChangePsd> usercall=api.Changepsd(Integer.parseInt(u),npsd);
        usercall.enqueue(new Callback<ResponseChangePsd>() {
            @Override
            public void onResponse(Call<ResponseChangePsd> call, Response<ResponseChangePsd> response) {
                Log.i("onResponse", new Gson().toJson(response.body()));
                if (response.body().getStatus().equals("1")){
                    Toast.makeText(ChangePassword.this, "Password updated successfully", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(ChangePassword.this, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseChangePsd> call, Throwable t) {

            }
        });

    }

}
