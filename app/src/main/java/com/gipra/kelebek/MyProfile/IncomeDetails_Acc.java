package com.gipra.kelebek.MyProfile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gipra.kelebek.R;

public class IncomeDetails_Acc extends AppCompatActivity {
    TextView binary_income_grossamount,binary_income_tds,binary_income_netamount;
    ImageView back_income;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income_details__acc);
        binary_income_grossamount=findViewById(R.id.binary_income_grossamount);
        binary_income_tds=findViewById(R.id.binary_income_tds);
        binary_income_netamount=findViewById(R.id.binary_income_netamount);
        back_income=findViewById(R.id.back_income);
        back_income.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MyProfile.class));
                finish();
            }
        });
    }
}
