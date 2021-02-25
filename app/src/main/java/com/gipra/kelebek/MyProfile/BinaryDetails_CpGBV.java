package com.gipra.kelebek.MyProfile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import com.gipra.kelebek.R;

public class BinaryDetails_CpGBV extends AppCompatActivity {
    Spinner binary_date_gbv;
    Button binary_search_gbv;
    RecyclerView recycler_binarygbv;
    ImageView back_binary;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binary_details__cp_gbv);
        recycler_binarygbv=findViewById(R.id.recycler_binarygbv);
        binary_date_gbv=findViewById(R.id.binary_date_gbv);
        binary_search_gbv=findViewById(R.id.binary_search_gbv);
        back_binary=findViewById(R.id.back_binary);
        back_binary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MyProfile.class));
                finish();
            }
        });

    }
}
