//package com.gipra.shero.Gene;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.os.Bundle;
//import android.view.View;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
//import com.gipra.shero.R;
//
//public class BinaryGenealogy extends AppCompatActivity {
//ImageView imgone,imgtwo,imgthree,imgfour,imgfive,imgsix,imgseven;
//LinearLayout llone,lltwo,llthree,llfour,llfive,llsix,llseven;
//LinearLayout llmain;
//TextView txtone,txttwo,txtthree,txtfour,txtfive,txtsix,txtseven;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_binary_genealogy);
//        llmain=findViewById(R.id.llmain);
//        txtone=findViewById(R.id.txtone);
//        txttwo=findViewById(R.id.txttwo);
//        txtthree=findViewById(R.id.txtthree);
//        txtfour=findViewById(R.id.txtfour);
//        txtfive=findViewById(R.id.txtfive);
//        txtsix=findViewById(R.id.txtsix);
//        txtseven=findViewById(R.id.txtseven);
//
//        imgone=findViewById(R.id.imgone);
//        imgtwo=findViewById(R.id.imgtwo);
//        imgthree=findViewById(R.id.imgthree);
//        imgfour=findViewById(R.id.imgfour);
//        imgfive=findViewById(R.id.imgfive);
//        imgsix=findViewById(R.id.imgsix);
//        imgseven=findViewById(R.id.imgseven);
//        llone=findViewById(R.id.llimgone);
//        lltwo=findViewById(R.id.llimgtwo);
//        llthree=findViewById(R.id.llimgthree);
//        llfour=findViewById(R.id.llimgfour);
//        llfive=findViewById(R.id.llimgfive);
//        llsix=findViewById(R.id.llimgsix);
//        llseven=findViewById(R.id.llimgseven);
//        imgone.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                llone.setVisibility(View.VISIBLE );
//                lltwo.setVisibility(View.GONE);
//                llthree.setVisibility(View.GONE);
//                llfour.setVisibility(View.GONE);
//                llfive.setVisibility(View.GONE);
//                llsix.setVisibility(View.GONE);
//                llseven.setVisibility(View.GONE);
//            }
//        });
//        imgtwo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                lltwo.setVisibility(View.VISIBLE);
//                llone.setVisibility(View.GONE);
//                llthree.setVisibility(View.GONE);
//                llfour.setVisibility(View.GONE);
//                llfive.setVisibility(View.GONE);
//                llsix.setVisibility(View.GONE);
//                llseven.setVisibility(View.GONE);
//            }
//        });
//        imgthree.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                llthree.setVisibility(View.VISIBLE);
//                llone.setVisibility(View.GONE);
//                lltwo.setVisibility(View.GONE);
//                llfour.setVisibility(View.GONE);
//                llfive.setVisibility(View.GONE);
//                llsix.setVisibility(View.GONE);
//                llseven.setVisibility(View.GONE);
//            }
//        });
//        imgfour.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                llfour.setVisibility(View.VISIBLE);
//                llone.setVisibility(View.GONE);
//                lltwo.setVisibility(View.GONE);
//                llthree.setVisibility(View.GONE);
//                llfive.setVisibility(View.GONE);
//                llsix.setVisibility(View.GONE);
//                llseven.setVisibility(View.GONE);
//            }
//        });
//        imgfive.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                llfive.setVisibility(View.VISIBLE);
//                llone.setVisibility(View.GONE);
//                lltwo.setVisibility(View.GONE);
//                llthree.setVisibility(View.GONE);
//                llfour.setVisibility(View.GONE);
//                llsix.setVisibility(View.GONE);
//                llseven.setVisibility(View.GONE);
//            }
//        });
//        imgsix.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                llsix.setVisibility(View.VISIBLE);
//                llone.setVisibility(View.GONE);
//                lltwo.setVisibility(View.GONE);
//                llthree.setVisibility(View.GONE);
//                llfour.setVisibility(View.GONE);
//                llfive.setVisibility(View.GONE);
//                llseven.setVisibility(View.GONE);
//            }
//        });
//        imgseven.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                llseven.setVisibility(View.VISIBLE);
//                llone.setVisibility(View.GONE);
//                lltwo.setVisibility(View.GONE);
//                llthree.setVisibility(View.GONE);
//                llfour.setVisibility(View.GONE);
//                llfive.setVisibility(View.GONE);
//                llsix.setVisibility(View.GONE);
//
//            }
//        });
//
//        llmain.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                llone.setVisibility(View.GONE);
//                lltwo.setVisibility(View.GONE);
//                llthree.setVisibility(View.GONE);
//                llfour.setVisibility(View.GONE);
//                llfive.setVisibility(View.GONE);
//                llsix.setVisibility(View.GONE);
//                llseven.setVisibility(View.GONE);
//            }
//        });
//
//    }
//}
