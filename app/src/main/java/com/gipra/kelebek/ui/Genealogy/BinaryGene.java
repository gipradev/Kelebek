package com.gipra.kelebek.ui.Genealogy;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;


import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gipra.kelebek.ApiClient;
import com.gipra.kelebek.ApiInterface;
import com.gipra.kelebek.Gene.GeneList;
import com.gipra.kelebek.Gene.ResponseGene;
import com.gipra.kelebek.R;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class BinaryGene extends Fragment {
    PopupWindow genepopup;
    TextView txtinfo;
    FrameLayout gene_layout;
    private static final String TAG = "BinaryGene";
    ImageView imgone,imgtwo,imgthree,imgfour,imgfive,imgsix,imgseven;
    LinearLayout llone,lltwo,llthree,llfour,llfive,llsix,llseven;
    LinearLayout llmain;
    TextView txtone,txttwo,txtthree,txtfour,txtfive,txtsix,txtseven;
    private ArrayList<GeneList> geneList;
    TextView username_one,sponsor_userid_one,name_one,sponsor_username_one,leftGBV_one,rightGBV_one,leftcount_one,rightcount_one,leftactive_one,rightactive_one;
    TextView username_two,sponsor_userid_two,name_two,sponsor_username_two,leftGBV_two,rightGBV_two,leftcount_two,rightcount_two,leftactive_two,rightactive_two;
    TextView username_three,sponsor_userid_three,name_three,sponsor_username_three,leftGBV_three,rightGBV_three,leftcount_three,rightcount_three,leftactive_three,rightactive_three;
    TextView username_four,sponsor_userid_four,name_four,sponsor_username_four,leftGBV_four,rightGBV_four,leftcount_four,rightcount_four,leftactive_four,rightactive_four;
    TextView username_five,sponsor_userid_five,name_five,sponsor_username_five,leftGBV_five,rightGBV_five,leftcount_five,rightcount_five,leftactive_five,rightactive_five;
    TextView username_six,sponsor_userid_six,name_six,sponsor_username_six,leftGBV_six,rightGBV_six,leftcount_six,rightcount_six,leftactive_six,rightactive_six;
    TextView username_seven,sponsor_userid_seven,name_seven,sponsor_username_seven,leftGBV_seven,rightGBV_seven,leftcount_seven,rightcount_seven,leftactive_seven,rightactive_seven;
    String uid0,uid1,uid2,uid3,uid4,uid5,uid6;
    String rank1,rank2,rank3,rank4,rank5,rank6,rank7;
    String rank;


    public BinaryGene() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_binary_gene, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Binary Genealogy");

        txtinfo=view.findViewById(R.id.txtinfo);
         txtinfo.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 GenePopup();
             }
         });
        gene_layout=view.findViewById(R.id.gene_layout);

        username_one=view.findViewById(R.id.username_one);
        sponsor_userid_one=view.findViewById(R.id.sponsor_userid_one);
         name_one=view.findViewById(R.id.name_one);
         sponsor_username_one=view.findViewById(R.id.sponsor_username_one);
        leftGBV_one=view.findViewById(R.id.leftGBV_one);
        rightGBV_one=view.findViewById(R.id.rightGBV_one);
        leftcount_one=view.findViewById(R.id.leftcount_one);
        rightcount_one=view.findViewById(R.id.rightcount_one);
        leftactive_one=view.findViewById(R.id.leftactive_members_one);
        rightactive_one=view.findViewById(R.id.rightactive_members_one);

        username_two=view.findViewById(R.id.username_two);
        sponsor_userid_two=view.findViewById(R.id.sponsor_userid_two);
        name_two=view.findViewById(R.id.name_two);
        sponsor_username_two=view.findViewById(R.id.sponsor_username_two);
        leftGBV_two=view.findViewById(R.id.leftGBV_two);
        rightGBV_two=view.findViewById(R.id.rightGBV_two);
        leftcount_two=view.findViewById(R.id.leftcount_two);
        rightcount_two=view.findViewById(R.id.rightcount_two);
        leftactive_two=view.findViewById(R.id.leftactive_members_two);
        rightactive_two=view.findViewById(R.id.rightactive_members_two);

        username_three=view.findViewById(R.id.username_three);
        sponsor_userid_three=view.findViewById(R.id.sponsor_userid_three);
        name_three=view.findViewById(R.id.name_three);
        sponsor_username_three=view.findViewById(R.id.sponsor_username_three);
        leftGBV_three=view.findViewById(R.id.leftGBV_three);
        rightGBV_three=view.findViewById(R.id.rightGBV_three);
        leftcount_three=view.findViewById(R.id.leftcount_three);
        rightcount_three=view.findViewById(R.id.rightcount_three);
        leftactive_three=view.findViewById(R.id.leftactive_members_three);
        rightactive_three=view.findViewById(R.id.rightactive_members_three);

        username_four=view.findViewById(R.id.username_four);
        sponsor_userid_four=view.findViewById(R.id.sponsor_userid_four);
        name_four=view.findViewById(R.id.name_four);
        sponsor_username_four=view.findViewById(R.id.sponsor_username_four);
        leftGBV_four=view.findViewById(R.id.leftGBV_four);
        rightGBV_four=view.findViewById(R.id.rightGBV_four);
        leftcount_four=view.findViewById(R.id.leftcount_four);
        rightcount_four=view.findViewById(R.id.rightcount_four);
        leftactive_four=view.findViewById(R.id.leftactive_members_four);
        rightactive_four=view.findViewById(R.id.rightactive_members_four);

        username_five=view.findViewById(R.id.username_five);
        sponsor_userid_five=view.findViewById(R.id.sponsor_userid_five);
        name_five=view.findViewById(R.id.name_five);
        sponsor_username_five=view.findViewById(R.id.sponsor_username_five);
        leftGBV_five=view.findViewById(R.id.leftGBV_five);
        rightGBV_five=view.findViewById(R.id.rightGBV_five);
        leftcount_five=view.findViewById(R.id.leftcount_five);
        rightcount_five=view.findViewById(R.id.rightcount_five);
        leftactive_five=view.findViewById(R.id.leftactive_members_five);
        rightactive_five=view.findViewById(R.id.rightactive_members_five);

        username_six=view.findViewById(R.id.username_six);
        sponsor_userid_six=view.findViewById(R.id.sponsor_userid_six);
        name_six=view.findViewById(R.id.name_six);
        sponsor_username_six=view.findViewById(R.id.sponsor_username_six);
        leftGBV_six=view.findViewById(R.id.leftGBV_six);
        rightGBV_six=view.findViewById(R.id.rightGBV_six);
        leftcount_six=view.findViewById(R.id.leftcount_six);
        rightcount_six=view.findViewById(R.id.rightcount_six);
        leftactive_six=view.findViewById(R.id.leftactive_members_six);
        rightactive_six=view.findViewById(R.id.rightactive_members_six);

        username_seven=view.findViewById(R.id.username_seven);
        sponsor_userid_seven=view.findViewById(R.id.sponsor_userid_seven);
        name_seven=view.findViewById(R.id.name_seven);
        sponsor_username_seven=view.findViewById(R.id.sponsor_username_seven);
        leftGBV_seven=view.findViewById(R.id.leftGBV_seven);
        rightGBV_seven=view.findViewById(R.id.rightGBV_seven);
        leftcount_seven=view.findViewById(R.id.leftcount_seven);
        rightcount_seven=view.findViewById(R.id.rightcount_seven);
        leftactive_seven=view.findViewById(R.id.leftactive_members_seven);
        rightactive_seven=view.findViewById(R.id.rightactive_members_seven);


        llmain=view.findViewById(R.id.llmain);
        txtone=view.findViewById(R.id.txtone);
        txttwo=view.findViewById(R.id.txttwo);
        txtthree=view.findViewById(R.id.txtthree);
        txtfour=view.findViewById(R.id.txtfour);
        txtfive=view.findViewById(R.id.txtfive);
        txtsix=view.findViewById(R.id.txtsix);
        txtseven=view.findViewById(R.id.txtseven);

        imgone=view.findViewById(R.id.imgone);
        imgtwo=view.findViewById(R.id.imgtwo);
        imgthree=view.findViewById(R.id.imgthree);
        imgfour=view.findViewById(R.id.imgfour);
        imgfive=view.findViewById(R.id.imgfive);
        imgsix=view.findViewById(R.id.imgsix);
        imgseven=view.findViewById(R.id.imgseven);
        llone=view.findViewById(R.id.llimgone);
        lltwo=view.findViewById(R.id.llimgtwo);
        llthree=view.findViewById(R.id.llimgthree);
        llfour=view.findViewById(R.id.llimgfour);
        llfive=view.findViewById(R.id.llimgfive);
        llsix=view.findViewById(R.id.llimgsix);
        llseven=view.findViewById(R.id.llimgseven);
        imgone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llone.setVisibility(View.VISIBLE );
                lltwo.setVisibility(View.GONE);
                llthree.setVisibility(View.GONE);
                llfour.setVisibility(View.GONE);
                llfive.setVisibility(View.GONE);
                llsix.setVisibility(View.GONE);
                llseven.setVisibility(View.GONE);
            }
        });
        imgtwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lltwo.setVisibility(View.VISIBLE);
                llone.setVisibility(View.GONE);
                llthree.setVisibility(View.GONE);
                llfour.setVisibility(View.GONE);
                llfive.setVisibility(View.GONE);
                llsix.setVisibility(View.GONE);
                llseven.setVisibility(View.GONE);
            }
        });
        imgthree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llthree.setVisibility(View.VISIBLE);
                llone.setVisibility(View.GONE);
                lltwo.setVisibility(View.GONE);
                llfour.setVisibility(View.GONE);
                llfive.setVisibility(View.GONE);
                llsix.setVisibility(View.GONE);
                llseven.setVisibility(View.GONE);
            }
        });
        imgfour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llfour.setVisibility(View.VISIBLE);
                llone.setVisibility(View.GONE);
                lltwo.setVisibility(View.GONE);
                llthree.setVisibility(View.GONE);
                llfive.setVisibility(View.GONE);
                llsix.setVisibility(View.GONE);
                llseven.setVisibility(View.GONE);
            }
        });
        imgfive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llfive.setVisibility(View.VISIBLE);
                llone.setVisibility(View.GONE);
                lltwo.setVisibility(View.GONE);
                llthree.setVisibility(View.GONE);
                llfour.setVisibility(View.GONE);
                llsix.setVisibility(View.GONE);
                llseven.setVisibility(View.GONE);
            }
        });
        imgsix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llsix.setVisibility(View.VISIBLE);
                llone.setVisibility(View.GONE);
                lltwo.setVisibility(View.GONE);
                llthree.setVisibility(View.GONE);
                llfour.setVisibility(View.GONE);
                llfive.setVisibility(View.GONE);
                llseven.setVisibility(View.GONE);
            }
        });
        imgseven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llseven.setVisibility(View.VISIBLE);
                llone.setVisibility(View.GONE);
                lltwo.setVisibility(View.GONE);
                llthree.setVisibility(View.GONE);
                llfour.setVisibility(View.GONE);
                llfive.setVisibility(View.GONE);
                llsix.setVisibility(View.GONE);

            }
        });
        llmain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llone.setVisibility(View.GONE);
                lltwo.setVisibility(View.GONE);
                llthree.setVisibility(View.GONE);
                llfour.setVisibility(View.GONE);
                llfive.setVisibility(View.GONE);
                llsix.setVisibility(View.GONE);
                llseven.setVisibility(View.GONE);
            }
        });
        SharedPreferences shpref;
        shpref=getContext().getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
        String u=shpref.getString("ID","");
        gene(u);
        txttwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (uid1==null){

                    // Toast.makeText(getActivity(), "nulllllllllllll", Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(getActivity(), RegVacant.class);
                    getContext().startActivity(i);

//                    getActivity().getSupportFragmentManager().beginTransaction()
//                            .replace(R.id.nav_host_fragment, new RegistrationFragment())
//                            .addToBackStack("")
//                            .commit();



                }
                else{
                    gene(uid1);
                }


            }
        });
        txtthree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (uid2==null){
                    Intent i=new Intent(getActivity(), RegVacant.class);
                    getContext().startActivity(i);
//                    getActivity().getSupportFragmentManager().beginTransaction()
//                            .replace(R.id.nav_host_fragment, new RegistrationFragment())
//                            .addToBackStack("")
//                            .commit();
                }else {
                    gene(uid2);
                }


            }
        });
        txtfour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (uid3==null){
                    Intent i=new Intent(getActivity(), RegVacant.class);
                    getContext().startActivity(i);
//                    getActivity().getSupportFragmentManager().beginTransaction()
//                            .replace(R.id.nav_host_fragment, new RegistrationFragment())
//                            .addToBackStack("")
//                            .commit();
                }
                else {
                    gene(uid3);
                }

            }
        });
        txtfive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (uid4==null){
                    Intent i=new Intent(getActivity(), RegVacant.class);
                    getContext().startActivity(i);
//                    getActivity().getSupportFragmentManager().beginTransaction()
//                            .replace(R.id.nav_host_fragment, new RegistrationFragment())
//                            .addToBackStack("")
//                            .commit();
                }else
                {
                    gene(uid4);
                }

            }
        });
        txtsix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (uid5==null){
                    Intent i=new Intent(getActivity(), RegVacant.class);
                    getContext().startActivity(i);
//                    getActivity().getSupportFragmentManager().beginTransaction()
//                            .replace(R.id.nav_host_fragment, new RegistrationFragment())
//                            .addToBackStack("")
//                            .commit();
                }else {
                    gene(uid5);
                }

            }
        });
        txtseven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (uid6==null){
                    Intent i=new Intent(getActivity(), RegVacant.class);
                    getContext().startActivity(i);
//                    getActivity().getSupportFragmentManager().beginTransaction()
//                            .replace(R.id.nav_host_fragment, new RegistrationFragment())
//                            .addToBackStack("")
//                            .commit();
                }else {
                    gene(uid6);
                }

            }
        });


        return view;
    }

    private void GenePopup() {
        LayoutInflater layoutInflater = (LayoutInflater)getActivity().getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View customview=layoutInflater.inflate(R.layout.genepopup,null);
        Button ok_popup=customview.findViewById(R.id.ok_popup);
        ok_popup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                genepopup.dismiss();
            }
        });
        genepopup=new PopupWindow(customview,ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        genepopup.showAtLocation(gene_layout, Gravity.CENTER,0, 0);
        genepopup.setFocusable(true);
        genepopup.update();
    }

    private  void gene(String id){
        ApiInterface api= ApiClient.getClient().create(ApiInterface.class);
        final Call<ResponseGene>usercall=api.Gene(Integer.parseInt(id));
        usercall.enqueue(new Callback<ResponseGene>() {
            @Override
            public void onResponse(Call<ResponseGene> call, final Response<ResponseGene> response) {
                Log.i("onResponse", new Gson().toJson(response.body()));

                   final ResponseGene responseGene = response.body();
                   geneList = (ArrayList<GeneList>) responseGene.getData();
                   uid0=geneList.get(0).getUserid();
                  String a0=geneList.get(0).getActive();

                  //Log.e("a0",a0);

                if (uid0==null){
                    Glide.with(getContext())
                            .load(R.drawable.vacant)
                            .into(imgone);

                }else if(a0.equals("Y")){
                    Glide.with(getContext())
                            .load(R.drawable.active)
                            .into(imgone);
                }else if(a0.equals("N"))
                    {
                    Glide.with(getContext())
                            .load(R.drawable.inactive)
                            .into(imgone);
                }

                   uid1=geneList.get(1).getUserid();
                   String a1=geneList.get(1).getActive();
                Log.i(TAG, "uid1"+uid1);
                if (uid1==null){
                    Glide.with(getContext())
                            .load(R.drawable.vacant)
                            .into(imgtwo);

                }else if(a1.equals("Y")){
                    Glide.with(getContext())
                            .load(R.drawable.active)
                            .into(imgtwo);
                }else if(a1.equals("N"))
                    {
                    Glide.with(getContext())
                            .load(R.drawable.inactive)
                            .into(imgtwo);
                }

                   uid2=geneList.get(2).getUserid();
                String a2=geneList.get(2).getActive();
                Log.i(TAG, "uid2"+uid2);

                if (uid2==null){
                    Glide.with(getContext())
                            .load(R.drawable.vacant)
                            .into(imgthree);

                }else if(a2.equals("Y")){
                    Glide.with(getContext())
                            .load(R.drawable.active)
                            .into(imgthree);
                }else if(a2.equals("N")){
                    Glide.with(getContext())
                            .load(R.drawable.inactive)
                            .into(imgthree);
                }
                   uid3=geneList.get(3).getUserid();
                String a3=geneList.get(3).getActive();
                Log.i(TAG, "uid3"+uid3);

                if (uid3==null){
                    Glide.with(getContext())
                            .load(R.drawable.vacant)
                            .into(imgfour);

                }
                else if (a3.equals("Y")){
                    Glide.with(getContext())
                            .load(R.drawable.active)
                            .into(imgfour);
                }else if(a3.equals("N")) {
                    Glide.with(getContext())
                            .load(R.drawable.inactive)
                            .into(imgfour);
                }
                   uid4=geneList.get(4).getUserid();
                String a4=geneList.get(4).getActive();
                Log.i(TAG, "uid4"+uid4);
                if (uid4==null){
                    Glide.with(getContext())
                            .load(R.drawable.vacant)
                            .into(imgfive);
                }else if (a4.equals("Y")){
                    Glide.with(getContext())
                            .load(R.drawable.active)
                            .into(imgfive);
                }else if(a4.equals("N")) {
                    Glide.with(getContext())
                            .load(R.drawable.inactive)
                            .into(imgfive);
                }
                   uid5=geneList.get(5).getUserid();
                String a5=geneList.get(5).getActive();
                Log.i(TAG, "uid5"+uid5);
                if (uid5==null){
                    Glide.with(getContext())
                            .load(R.drawable.vacant)
                            .into(imgsix);

                }else if (a5.equals("Y")){
                    Glide.with(getContext())
                            .load(R.drawable.active)
                            .into(imgsix);
                }else if(a5.equals("N")) {
                    Glide.with(getContext())
                            .load(R.drawable.inactive)
                            .into(imgsix);
                }
                   uid6=geneList.get(6).getUserid();
                String a6=geneList.get(6).getActive();
                Log.i(TAG, "uid6"+uid6);
                if (uid6==null){
                    Glide.with(getContext())
                            .load(R.drawable.vacant)
                            .into(imgseven);

                }else if (a6.equals("Y")){
                    Glide.with(getContext())
                            .load(R.drawable.active)
                            .into(imgseven);
                }
                else if(a6.equals("N")) {
                    Glide.with(getContext())
                            .load(R.drawable.inactive)
                            .into(imgseven);
                }


                    rank1=geneList.get(0).getCRank();
                Log.e(TAG,"rank1::"+rank1);
                switch (rank1) {

                    case "Star":
                        Glide.with(getContext())
                                .load(R.drawable.star)
                                .into(imgone);
                        break;
                    case "Silver":
                        Glide.with(getContext())
                                .load(R.drawable.silver)
                                .into(imgone);
                        break;
                    case "Gold":
                        Glide.with(getContext())
                                .load(R.drawable.gold)
                                .into(imgone);
                        break;
                    case "Pearl":
                        Glide.with(getContext())
                                .load(R.drawable.peral)
                                .into(imgone);
                        break;
                    case "Emerald":
                        Glide.with(getContext())
                                .load(R.drawable.emerald)
                                .into(imgone);
                        break;
                    case "Platinum":
                        Glide.with(getContext())
                                .load(R.drawable.platinum)
                                .into(imgone);
                        break;
                    case "Diamond":
                        Glide.with(getContext())
                                .load(R.drawable.diamond)
                                .into(imgone);
                        break;
                    case "Double Diamond":
                        Glide.with(getContext())
                                .load(R.drawable.ddiamond)
                                .into(imgone);
                        break;
                    case "Blue Diamond":
                        Glide.with(getContext())
                                .load(R.drawable.bluediamond)
                                .into(imgone);
                        break;
                    case "White Diamond":
                        Glide.with(getContext())
                                .load(R.drawable.white)
                                .into(imgone);
                        break;
                    case "Grown Diamond":
                        Glide.with(getContext())
                                .load(R.drawable.crowndiamond)
                                .into(imgone);
                        break;
                    case "Black Diamond":
                        Glide.with(getContext())
                                .load(R.drawable.blackdiamond)
                                .into(imgone);

                        break;
                }

               rank2=geneList.get(1).getCRank();
                switch (rank2) {
                    case "Star":
                        Glide.with(getContext())
                                .load(R.drawable.star)
                                .into(imgtwo);
                        break;
                    case "Silver":
                        Glide.with(getContext())
                                .load(R.drawable.silver)
                                .into(imgtwo);
                        break;
                    case "Gold":
                        Glide.with(getContext())
                                .load(R.drawable.gold)
                                .into(imgtwo);
                        break;
                    case "Pearl":
                        Glide.with(getContext())
                                .load(R.drawable.peral)
                                .into(imgtwo);
                        break;
                    case "Emerald":
                        Glide.with(getContext())
                                .load(R.drawable.emerald)
                                .into(imgtwo);
                        break;
                    case "Platinum":
                        Glide.with(getContext())
                                .load(R.drawable.platinum)
                                .into(imgtwo);
                        break;
                    case "Diamond":
                        Glide.with(getContext())
                                .load(R.drawable.diamond)
                                .into(imgtwo);
                        break;
                    case "Double Diamond":
                        Glide.with(getContext())
                                .load(R.drawable.ddiamond)
                                .into(imgtwo);
                        break;
                    case "Blue Diamond":
                        Glide.with(getContext())
                                .load(R.drawable.bluediamond)
                                .into(imgtwo);
                        break;
                    case "White Diamond":
                        Glide.with(getContext())
                                .load(R.drawable.white)
                                .into(imgtwo);
                        break;
                    case "Grown Diamond":
                        Glide.with(getContext())
                                .load(R.drawable.crowndiamond)
                                .into(imgtwo);
                        break;
                    case "Black Diamond":
                        Glide.with(getContext())
                                .load(R.drawable.blackdiamond)
                                .into(imgtwo);

                        break;
                }
                rank3=geneList.get(2).getCRank();
                switch (rank3) {
                    case "Star":
                        Glide.with(getContext())
                                .load(R.drawable.star)
                                .into(imgthree);
                        break;
                    case "Silver":
                        Glide.with(getContext())
                                .load(R.drawable.silver)
                                .into(imgthree);
                        break;
                    case "Gold":
                        Glide.with(getContext())
                                .load(R.drawable.gold)
                                .into(imgthree);
                        break;
                    case "Pearl":
                        Glide.with(getContext())
                                .load(R.drawable.peral)
                                .into(imgthree);
                        break;
                    case "Emerald":
                        Glide.with(getContext())
                                .load(R.drawable.emerald)
                                .into(imgthree);
                        break;
                    case "Platinum":
                        Glide.with(getContext())
                                .load(R.drawable.platinum)
                                .into(imgthree);
                        break;
                    case "Diamond":
                        Glide.with(getContext())
                                .load(R.drawable.diamond)
                                .into(imgthree);
                        break;
                    case "Double Diamond":
                        Glide.with(getContext())
                                .load(R.drawable.ddiamond)
                                .into(imgthree);
                        break;
                    case "Blue Diamond":
                        Glide.with(getContext())
                                .load(R.drawable.bluediamond)
                                .into(imgthree);
                        break;
                    case "White Diamond":
                        Glide.with(getContext())
                                .load(R.drawable.white)
                                .into(imgthree);
                        break;
                    case "Grown Diamond":
                        Glide.with(getContext())
                                .load(R.drawable.crowndiamond)
                                .into(imgthree);
                        break;
                    case "Black Diamond":
                        Glide.with(getContext())
                                .load(R.drawable.blackdiamond)
                                .into(imgthree);

                        break;
                }
                rank4=geneList.get(3).getCRank();
                switch (rank4) {
                    case "Star":
                        Glide.with(getContext())
                                .load(R.drawable.star)
                                .into(imgfour);
                        break;
                    case "Silver":
                        Glide.with(getContext())
                                .load(R.drawable.silver)
                                .into(imgfour);
                        break;
                    case "Gold":
                        Glide.with(getContext())
                                .load(R.drawable.gold)
                                .into(imgfour);
                        break;
                    case "Pearl":
                        Glide.with(getContext())
                                .load(R.drawable.peral)
                                .into(imgfour);
                        break;
                    case "Emerald":
                        Glide.with(getContext())
                                .load(R.drawable.emerald)
                                .into(imgfour);
                        break;
                    case "Platinum":
                        Glide.with(getContext())
                                .load(R.drawable.platinum)
                                .into(imgfour);
                        break;
                    case "Diamond":
                        Glide.with(getContext())
                                .load(R.drawable.diamond)
                                .into(imgfour);
                        break;
                    case "Double Diamond":
                        Glide.with(getContext())
                                .load(R.drawable.ddiamond)
                                .into(imgfour);
                        break;
                    case "Blue Diamond":
                        Glide.with(getContext())
                                .load(R.drawable.bluediamond)
                                .into(imgfour);
                        break;
                    case "White Diamond":
                        Glide.with(getContext())
                                .load(R.drawable.white)
                                .into(imgfour);
                        break;
                    case "Grown Diamond":
                        Glide.with(getContext())
                                .load(R.drawable.crowndiamond)
                                .into(imgfour);
                        break;
                    case "Black Diamond":
                        Glide.with(getContext())
                                .load(R.drawable.blackdiamond)
                                .into(imgfour);
                        break;
                }
                rank5=geneList.get(4).getCRank();
                switch (rank5) {
                    case "Star":
                        Glide.with(getContext())
                                .load(R.drawable.star)
                                .into(imgfive);
                        break;
                    case "Silver":
                        Glide.with(getContext())
                                .load(R.drawable.silver)
                                .into(imgfive);
                        break;
                    case "Gold":
                        Glide.with(getContext())
                                .load(R.drawable.gold)
                                .into(imgfive);
                        break;
                    case "Pearl":
                        Glide.with(getContext())
                                .load(R.drawable.peral)
                                .into(imgfive);
                        break;
                    case "Emerald":
                        Glide.with(getContext())
                                .load(R.drawable.emerald)
                                .into(imgfive);
                        break;
                    case "Platinum":
                        Glide.with(getContext())
                                .load(R.drawable.platinum)
                                .into(imgfive);
                        break;
                    case "Diamond":
                        Glide.with(getContext())
                                .load(R.drawable.diamond)
                                .into(imgfive);
                        break;
                    case "Double Diamond":
                        Glide.with(getContext())
                                .load(R.drawable.ddiamond)
                                .into(imgfive);
                        break;
                    case "Blue Diamond":
                        Glide.with(getContext())
                                .load(R.drawable.bluediamond)
                                .into(imgfive);
                        break;
                    case "White Diamond":
                        Glide.with(getContext())
                                .load(R.drawable.white)
                                .into(imgfive);
                        break;
                    case "Grown Diamond":
                        Glide.with(getContext())
                                .load(R.drawable.crowndiamond)
                                .into(imgfive);
                        break;
                    case "Black Diamond":
                        Glide.with(getContext())
                                .load(R.drawable.blackdiamond)
                                .into(imgfive);
                        break;
                }
                rank6=geneList.get(5).getCRank();
                switch (rank6) {
                    case "Star":
                        Glide.with(getContext())
                                .load(R.drawable.star)
                                .into(imgsix);
                        break;
                    case "Silver":
                        Glide.with(getContext())
                                .load(R.drawable.silver)
                                .into(imgsix);
                        break;
                    case "Gold":
                        Glide.with(getContext())
                                .load(R.drawable.gold)
                                .into(imgsix);
                        break;
                    case "Pearl":
                        Glide.with(getContext())
                                .load(R.drawable.peral)
                                .into(imgsix);
                        break;
                    case "Emerald":
                        Glide.with(getContext())
                                .load(R.drawable.emerald)
                                .into(imgsix);
                        break;
                    case "Platinum":
                        Glide.with(getContext())
                                .load(R.drawable.platinum)
                                .into(imgsix);
                        break;
                    case "Diamond":
                        Glide.with(getContext())
                                .load(R.drawable.diamond)
                                .into(imgsix);
                        break;
                    case "Double Diamond":
                        Glide.with(getContext())
                                .load(R.drawable.ddiamond)
                                .into(imgsix);
                        break;
                    case "Blue Diamond":
                        Glide.with(getContext())
                                .load(R.drawable.bluediamond)
                                .into(imgsix);
                        break;
                    case "White Diamond":
                        Glide.with(getContext())
                                .load(R.drawable.white)
                                .into(imgsix);
                        break;
                    case "Grown Diamond":
                        Glide.with(getContext())
                                .load(R.drawable.crowndiamond)
                                .into(imgsix);
                        break;
                    case "Black Diamond":
                        Glide.with(getContext())
                                .load(R.drawable.blackdiamond)
                                .into(imgsix);
                        break;
                }
                rank7=geneList.get(6).getCRank();
                switch (rank7) {
                    case "Star":
                        Glide.with(getContext())
                                .load(R.drawable.star)
                                .into(imgseven);
                        break;
                    case "Silver":
                        Glide.with(getContext())
                                .load(R.drawable.silver)
                                .into(imgseven);
                        break;
                    case "Gold":
                        Glide.with(getContext())
                                .load(R.drawable.gold)
                                .into(imgseven);
                        break;
                    case "Pearl":
                        Glide.with(getContext())
                                .load(R.drawable.peral)
                                .into(imgseven);
                        break;
                    case "Emerald":
                        Glide.with(getContext())
                                .load(R.drawable.emerald)
                                .into(imgseven);
                        break;
                    case "Platinum":
                        Glide.with(getContext())
                                .load(R.drawable.platinum)
                                .into(imgseven);
                        break;
                    case "Diamond":
                        Glide.with(getContext())
                                .load(R.drawable.diamond)
                                .into(imgseven);
                        break;
                    case "Double Diamond":
                        Glide.with(getContext())
                                .load(R.drawable.ddiamond)
                                .into(imgseven);
                        break;
                    case "Blue Diamond":
                        Glide.with(getContext())
                                .load(R.drawable.bluediamond)
                                .into(imgseven);
                        break;
                    case "White Diamond":
                        Glide.with(getContext())
                                .load(R.drawable.white)
                                .into(imgseven);
                        break;
                    case "Grown Diamond":
                        Glide.with(getContext())
                                .load(R.drawable.crowndiamond)
                                .into(imgseven);
                        break;
                    case "Black Diamond":
                        Glide.with(getContext())
                                .load(R.drawable.blackdiamond)
                                .into(imgseven);
                        break;
                }



                //Log.e("username",username);
                String username = geneList.get(0).getName();

                Log.e("username",username);
                   txtone.setText(username);
                    String u = geneList.get(1).getName();
                Log.e("u",u);
                    txttwo.setText(u);
                   String three = geneList.get(2).getName();
                Log.e("three",three);
                   txtthree.setText(three);
                   String four = geneList.get(3).getName();
                   txtfour.setText(four);
                   String five = geneList.get(4).getName();
                   txtfive.setText(five);
                   String six = geneList.get(5).getName();
                   txtsix.setText(six);
                   String seven = geneList.get(6).getName();
                   txtseven.setText(seven);




                   String lcount = geneList.get(0).getLeftCount();
                   leftcount_one.setText(lcount);
                   String rcount = geneList.get(0).getRightCount();
                   rightcount_one.setText(rcount);
                   String lactive = geneList.get(0).getLeftActive();
                   leftactive_one.setText(lactive);
                   String ractive = geneList.get(0).getRightActive();
                   rightactive_one.setText(ractive);
                   String lpv = geneList.get(0).getLeftPv();
                   leftGBV_one.setText(lpv);
                   String rpv = geneList.get(0).getRightPv();
                   rightGBV_one.setText(rpv);
                   String name = geneList.get(0).getName();
                   username_one.setText(name);
                   String spuserid_one = geneList.get(0).getSponsorId();
                   sponsor_userid_one.setText(spuserid_one);
                   String name0=geneList.get(0).getName0();
                   name_one.setText(name0);
                   String sp_username1=geneList.get(0).getSponsorName();
                   sponsor_username_one.setText(sp_username1);

                   Log.e("spuserid_one",spuserid_one);


                   String lcount_two = geneList.get(1).getLeftCount();
                   leftcount_two.setText(lcount_two);
                   String rcount_two = geneList.get(1).getRightCount();
                   rightcount_two.setText(rcount_two);
                   String lactive_two = geneList.get(1).getLeftActive();
                   leftactive_two.setText(lactive_two);
                   String ractive_two = geneList.get(1).getRightActive();
                   rightactive_two.setText(ractive_two);
                   String lpv_two = geneList.get(1).getLeftPv();
                   leftGBV_two.setText(lpv_two);
                   String rpv_two = geneList.get(1).getRightPv();
                   rightGBV_two.setText(rpv_two);
                   String nametwo = geneList.get(1).getName();
                   username_two.setText(nametwo);
                   String spuserid_two = geneList.get(1).getSponsorId();
                   sponsor_userid_two.setText(spuserid_two);
                   String name1=geneList.get(1).getName1();
                   name_two.setText(name1);
                String sp_username2=geneList.get(1).getSponsorName();
                sponsor_username_two.setText(sp_username2);
                Log.e("spuserid_two",spuserid_two);

                   String lcount_three = geneList.get(2).getLeftCount();
                   leftcount_three.setText(lcount_three);
                   String rcount_three = geneList.get(2).getRightCount();
                   rightcount_three.setText(rcount_three);
                   String lactive_three = geneList.get(2).getLeftActive();
                   leftactive_three.setText(lactive_three);
                   String ractive_three = geneList.get(2).getRightActive();
                   rightactive_three.setText(ractive_three);
                   String lpv_three = geneList.get(2).getLeftPv();
                   leftGBV_three.setText(lpv_three);
                   String rpv_three = geneList.get(2).getRightPv();
                   rightGBV_three.setText(rpv_three);
                   String namethree = geneList.get(2).getName();
                   username_three.setText(namethree);
                   String spuserid_three = geneList.get(2).getSponsorId();
                   sponsor_userid_three.setText(spuserid_three);
                   String name2=geneList.get(2).getName2();
                   name_three.setText(name2);
                String sp_username3=geneList.get(2).getSponsorName();
                sponsor_username_three.setText(sp_username3);
                Log.e("spuserid_three",spuserid_three);

                   String lcount_four = geneList.get(3).getLeftCount();
                   leftcount_four.setText(lcount_four);
                   String rcount_four = geneList.get(3).getRightCount();
                   rightcount_four.setText(rcount_four);
                   String lactive_four = geneList.get(3).getLeftActive();
                   leftactive_four.setText(lactive_four);
                   String ractive_four = geneList.get(3).getRightActive();
                   rightactive_four.setText(ractive_four);
                   String lpv_four = geneList.get(3).getLeftPv();
                   leftGBV_four.setText(lpv_four);
                   String rpv_four = geneList.get(3).getRightPv();
                   rightGBV_four.setText(rpv_four);
                   String namefour = geneList.get(3).getName();
                   username_four.setText(namefour);
                   String spuserid_four = geneList.get(3).getSponsorId();
                   sponsor_userid_four.setText(spuserid_four);
                   String name3=geneList.get(3).getName3();
                   name_four.setText(name3);
                String sp_username4=geneList.get(3).getSponsorName();
                sponsor_username_four.setText(sp_username4);

                Log.e("spuserid_four",spuserid_four);

                   String lcount_five = geneList.get(4).getLeftCount();
                   leftcount_five.setText(lcount_five);
                   String rcount_five = geneList.get(4).getRightCount();
                   rightcount_five.setText(rcount_five);
                   String lactive_five = geneList.get(4).getLeftActive();
                   leftactive_five.setText(lactive_five);
                   String ractive_five = geneList.get(4).getRightActive();
                   rightactive_one.setText(ractive_five);
                   String lpv_five = geneList.get(4).getLeftPv();
                   leftGBV_five.setText(lpv_five);
                   String rpv_five = geneList.get(4).getRightPv();
                   rightGBV_five.setText(rpv_five);
                   String namefive = geneList.get(4).getName();
                   username_five.setText(namefive);
                   String spuserid_five = geneList.get(4).getSponsorId();
                   sponsor_userid_five.setText(spuserid_five);
                   String name4=geneList.get(4).getName4();
                   name_five.setText(name4);
                String sp_username5=geneList.get(4).getSponsorName();
                sponsor_username_five.setText(sp_username5);

                Log.e("spuserid_five",spuserid_five);

                   String lcount_six = geneList.get(5).getLeftCount();
                   leftcount_six.setText(lcount_six);
                   String rcount_six = geneList.get(5).getRightCount();
                   rightcount_six.setText(rcount_six);
                   String lactive_six = geneList.get(5).getLeftActive();
                   leftactive_six.setText(lactive_six);
                   String ractive_six = geneList.get(5).getRightActive();
                   rightactive_six.setText(ractive_six);
                   String lpv_six = geneList.get(5).getLeftPv();
                   leftGBV_six.setText(lpv_six);
                   String rpv_six = geneList.get(5).getRightPv();
                   rightGBV_six.setText(rpv_six);
                   String namesix = geneList.get(5).getName();
                   username_six.setText(namesix);
                   String spuserid_six = geneList.get(5).getSponsorId();
                   sponsor_userid_six.setText(spuserid_six);
                String name5=geneList.get(5).getName5();
                name_six.setText(name5);
                String sp_username6=geneList.get(5).getSponsorName();
                sponsor_username_six.setText(sp_username6);


               Log.e("spuserid_six",spuserid_six);

                   String lcount_seven = geneList.get(6).getLeftCount();
                   leftcount_seven.setText(lcount_seven);
                   String rcount_seven = geneList.get(6).getRightCount();
                   rightcount_seven.setText(rcount_seven);
                   String lactive_seven = geneList.get(6).getRightActive();
                   leftactive_seven.setText(lactive_seven);
                   String ractive_seven = geneList.get(6).getRightActive();
                   rightactive_seven.setText(ractive_seven);
                   String lpv_seven = geneList.get(6).getLeftPv();
                   leftGBV_seven.setText(lpv_seven);
                   String rpv_seven = geneList.get(6).getRightPv();
                   rightGBV_seven.setText(rpv_seven);
                   String nameseven = geneList.get(6).getName();
                   username_seven.setText(nameseven);
                   String spuserid_seven = geneList.get(6).getSponsorId();
                   sponsor_userid_seven.setText(spuserid_seven);
                String name6=geneList.get(6).getName6();
                name_seven.setText(name6);
                String sp_username7=geneList.get(6).getSponsorName();
                sponsor_username_seven.setText(sp_username7);

                Log.e("spuserid_seven",spuserid_seven);

               }
               @Override
            public void onFailure(Call<ResponseGene> call, Throwable t) {

            }
        });
    }


}
