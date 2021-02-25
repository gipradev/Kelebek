package com.gipra.kelebek.ui.Dashboard;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import com.gipra.kelebek.ApiClient;
import com.gipra.kelebek.ApiInterface;
import com.gipra.kelebek.MainActivity;
import com.gipra.kelebek.R;
import com.gipra.kelebek.ResponseDashboard;
import com.gipra.kelebek.ui.Report.LeftSideMembers.LeftMember;
import com.gipra.kelebek.ui.Report.LeftSideSales.LeftSales;
import com.gipra.kelebek.ui.Report.RightSideMembers.RightMember;
import com.gipra.kelebek.ui.Report.RightSideSales.RightSales;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class DashBoardFragment extends Fragment {
    TextView rank,shoppingwallet, incomewallet,leftcount, rightcount, leftactive,rightactive,lefttotalBV,righttotalBV,totalincome;
    LinearLayout llrightcount,llleftcount,llleftactive,llrightactive,llleftbv,llrightbv,lltotalearned;
    Animation animZoomIn;
    DrawerLayout drawer;
    private static final String TAG = "DashBoardFragment";
    MainActivity activity = (MainActivity) getActivity();
    public DashBoardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dash_board, container, false);
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("DashBoard");
        rank = view.findViewById(R.id.rank);
        shoppingwallet = view.findViewById(R.id.shoppingwallet);
        incomewallet = view.findViewById(R.id.incomewallet);
        leftcount = view.findViewById(R.id.leftcount);
        rightcount = view.findViewById(R.id.rightcount);
        leftactive = view.findViewById(R.id.left_active_members);
        rightactive = view.findViewById(R.id.right_active_members);
        lefttotalBV = view.findViewById(R.id.left_total_bv);
        righttotalBV = view.findViewById(R.id.right_total_bv);
        totalincome = view.findViewById(R.id.total_earned_income);

        ///layouts
        llleftcount = view.findViewById(R.id.layoutleftcount);
        llrightcount = view.findViewById(R.id.layoutrightcount);
        llleftactive = view.findViewById(R.id.layout_left_active_members);
        llrightactive = view.findViewById(R.id.layoutrightactivemembers);
        llleftbv = view.findViewById(R.id.layout_left_total_bv);
        llrightbv = view.findViewById(R.id.layout_Right_total_bv);

        drawer = view.findViewById(R.id.drawer_layout);
        dashboard();
        animZoomIn = AnimationUtils.loadAnimation(getContext(),
                R.anim.fade_in);


        llleftcount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LeftMember leftMember = new LeftMember();
                getActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.zoom_out,R.anim.fade_in)
                        .replace(R.id.nav_host_fragment, leftMember, leftMember.getClass().getSimpleName()).addToBackStack(null).commit();
                //.setCustomAnimations(R.anim.pull_in_from_left, R.anim.push_out_to_left, R.anim.pull_in_from_left, R.anim.push_out_to_left);
            }
        });

        llrightcount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RightMember rightMember = new RightMember();
                getActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.zoom_out,R.anim.fade_in)
                        .replace(R.id.nav_host_fragment, rightMember, rightMember.getClass().getSimpleName()).addToBackStack(null).commit();

            }
        });


        llleftactive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LeftMember leftMember = new LeftMember();
                getActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.zoom_out,R.anim.fade_in)
                        .replace(R.id.nav_host_fragment, leftMember, leftMember.getClass().getSimpleName()).addToBackStack(null).commit();

            }
        });



        llrightactive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RightMember rightMember = new RightMember();
                getActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.zoom_out,R.anim.fade_in)
                        .replace(R.id.nav_host_fragment, rightMember, rightMember.getClass().getSimpleName()).addToBackStack(null).commit();

            }
        });


        llleftbv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LeftSales leftSales = new LeftSales();
                getActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.zoom_out,R.anim.fade_in)
                        .replace(R.id.nav_host_fragment, leftSales, leftSales.getClass().getSimpleName()).addToBackStack(null).commit();

            }
        });

        llrightbv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RightSales rightSales = new RightSales();
                getActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.zoom_out,R.anim.fade_in)
                        .replace(R.id.nav_host_fragment, rightSales, rightSales.getClass().getSimpleName()).addToBackStack(null).commit();

            }
        });


        return view;
    }



    private void dashboard() {
        SharedPreferences shpref;
        shpref = getContext().getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
        String u = shpref.getString("ID", "");
        ApiInterface api = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseDashboard> usercall = api.Dashboard(Integer.parseInt(u));
        usercall.enqueue(new Callback<ResponseDashboard>() {
            @Override
            public void onResponse(Call<ResponseDashboard> call, Response<ResponseDashboard> response) {
                if (response.body().getStatus().equals("1")) {

                    String swallet = response.body().getShoppingAmount();
                    String iwallet = response.body().getNIncomeamount();
                    String leftcnt = response.body().getNLeftCount();
                    String rightcnt = response.body().getNRightCount();
                    String leftact = response.body().getNLeftActive();
                    String rightact = response.body().getNRightActive();
                    String leftbv = response.body().getNLeftPv();
                    String rightbv = response.body().getNRightPv();
                    String totalearnedincome = response.body().getTotalIncome().toString();
                    Log.e(TAG,"......................"+response.body().getCRank());

                    rank.setText(response.body().getCRank());

                    shoppingwallet.setText(swallet);
                    incomewallet.setText(iwallet);

                    leftcount.setText(leftcnt);
                    rightcount.setText(rightcnt);

                    leftactive.setText(leftact);
                    rightactive.setText(rightact);

                    lefttotalBV.setText(leftbv);
                    righttotalBV.setText(rightbv);

                    totalincome.setText(totalearnedincome);



                    shoppingwallet.startAnimation(animZoomIn);

                    incomewallet.startAnimation(animZoomIn);
                }
            }

            @Override
            public void onFailure(Call<ResponseDashboard> call, Throwable t) {

            }
        });
    }


    public boolean onBackPressed() {


            return false;
        }


}

