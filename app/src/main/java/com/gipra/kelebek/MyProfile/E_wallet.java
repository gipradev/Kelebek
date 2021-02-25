package com.gipra.kelebek.MyProfile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gipra.kelebek.R;

public class E_wallet extends AppCompatActivity {
    SearchView ewallet_search;
    RecyclerView recycler_ewallet;
    TextView txt_ewallet_nodata;
    ImageView back_ewallet;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_e_wallet);
        txt_ewallet_nodata=findViewById(R.id.txt_ewallet_nodata);
        ewallet_search=findViewById(R.id.ewallet_search);
        recycler_ewallet=findViewById(R.id.recycler_ewallet);
        back_ewallet=findViewById(R.id.back_ewallet);
        back_ewallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MyProfile.class));
            }
        });
    }
}
