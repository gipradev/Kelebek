package com.gipra.kelebek;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.gipra.kelebek.MyProfile.MyProfile;

import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.gipra.kelebek.ui.Dashboard.DashBoardFragment;
import com.gipra.kelebek.ui.Genealogy.BinaryGene;
import com.gipra.kelebek.ui.Genealogy.SponsorTree;
import com.gipra.kelebek.ui.IncomeDetails.DirectSalesBonus.DirectSalesBonusFragment;
import com.gipra.kelebek.ui.IncomeDetails.First_Purchase_BV_Report.FirstPurchaseBVFragment;
import com.gipra.kelebek.ui.IncomeDetails.LeaderSuccessBonus.LeaderSuccessBonusFragment;
import com.gipra.kelebek.ui.IncomeDetails.Leader_Level_BV.LeaderLevelBVFragment;
import com.gipra.kelebek.ui.IncomeDetails.Repurchase_BV_Report.RepurchaseBVReportFragment;
import com.gipra.kelebek.ui.IncomeDetails.Repurchase_Income.RepurchaseIncomeFragment;
import com.gipra.kelebek.ui.IncomeDetails.Repurchase_Income_details.RepurchaseIncomeDetailFragment;
import com.gipra.kelebek.ui.IncomeDetails.Team_Sales_BV_matching.TeamSalesBVMatchingFragment;
import com.gipra.kelebek.ui.IncomeDetails.Team_Sales_Bonus_details.TeamSalesBonusFragment;
import com.gipra.kelebek.ui.PinManagement.PinRequestDetailsFragment;
import com.gipra.kelebek.ui.RegistrationFragment;
import com.gipra.kelebek.ui.Report.LeftSideMembers.LeftMember;
import com.gipra.kelebek.ui.Report.LeftSideSales.LeftSales;
import com.gipra.kelebek.ui.Report.My_Product.MyProduct;
import com.gipra.kelebek.ui.Report.RightSideMembers.RightMember;
import com.gipra.kelebek.ui.Report.RightSideSales.RightSales;
import com.gipra.kelebek.ui.Report.SponsorList.SponsorList;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity  {
    private static final String TAG = "MainActivity";
    TextView name,uname;
    CircleImageView userdp;
    String imgpath;
    SharedPreferences sharedPreferences;
    ExpandableListAdapter expandableListAdapter;
    ExpandableListView expandableListView;
    List<MenuModel> headerList = new ArrayList<>();
    HashMap<MenuModel, List<MenuModel>> childList = new HashMap<>();
    private AppBarConfiguration mAppBarConfiguration;
    Toolbar toolbar;
    DrawerLayout drawer;
    NavigationView navigationView;
    Fragment f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView gotoshero = findViewById(R.id.gotoshero);
        gotoshero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), GoToShero.class));

            }
        });

        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        View headerview = navigationView.getHeaderView(0);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels;

        expandableListView = findViewById(R.id.expandableListView);
        expandableListView.setIndicatorBounds(width - GetPixelFromDips(10), width - GetPixelFromDips(10));


        prepareMenuData();
        populateExpandableList();

//       Menu nav_Menu = navigationView.getMenu();
        sharedPreferences = getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
        String u = sharedPreferences.getString("USER_NAME", "");
        String n = sharedPreferences.getString("CUST_NAME", "");
        uname = headerview.findViewById(R.id.txtusername);
        uname.setText(u);
        name = headerview.findViewById(R.id.txtcust_name);
        name.setText(n);
        userdp = headerview.findViewById(R.id.userfemale);
//        String im=sharedPreferences.getString("IMAGE","");
//
//        Glide.with(getApplicationContext())
//                .load(im)
//                .into(userdp);
        dp();
//         Passing each menu ID as a set of Ids because each
//         menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_dashboard, R.id.nav_strategy, R.id.nav_upgrade,
                R.id.nav_regform, R.id.nav_wallet, R.id.nav_news,
                R.id.nav_report, R.id.nav_incomedetails, R.id.nav_newentryposition, R.id.nav_sponsortree, R.id.nav_sponsortree_tabview, R.id.nav_binarygene, R.id.nav_incomewallet,
                R.id.nav_sponsorlist, R.id.nav_cponelist, R.id.nav_cptwolist, R.id.nav_cponesales, R.id.nav_cptwosales, R.id.nav_group_repurchase,
                R.id.nav_personal_firstpurchaseBV, R.id.nav_personal_repurchasePV, R.id.nav_account_statement, R.id.nav_cp_GBVmatching,
                R.id.nav_cpbonus, R.id.nav_per_bonus_details, R.id.nav_per_bonus_income, R.id.nav_royaltyincome, R.id.nav_desig_achiev, R.id.nav_desig_achiev_bonus)
                .setDrawerLayout(drawer)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

    }

    public int GetPixelFromDips(float pixels) {
        // Get the screen's density scale
        final float scale = getResources().getDisplayMetrics().density;
        // Convert the dps to pixels, based on density scale
        return (int) (pixels * scale + 0.5f);
    }

    private void prepareMenuData() {
        MenuModel menuModel = new MenuModel("Dashboard", true,true,R.drawable.dashboard); //Menu of Android Tutorial. No sub menus
        headerList.add(menuModel);
        if (!menuModel.hasChildren) {
            childList.put(menuModel, null);
        }
        menuModel = new MenuModel("Product Store", true,true,R.drawable.ic_shopping_cart); //Menu of Android Tutorial. No sub menus
        headerList.add(menuModel);

        if (!menuModel.hasChildren) {
            childList.put(menuModel, null);
        }

        menuModel = new MenuModel("Genealogy", true, true,R.drawable.genealogy); //Menu of Java Tutorials
        headerList.add(menuModel);
        List<MenuModel> childModelsList = new ArrayList<>();
        MenuModel childModel = new MenuModel(" Binary Genealogy", false, false,R.drawable.ic_arrow);
        childModelsList.add(childModel);

        childModel = new MenuModel(" Sponsor Tree", false, false,R.drawable.ic_arrow);
        childModelsList.add(childModel);

//        childModel = new MenuModel(" PET Genealogy", false, false,R.drawable.ic_arrow);
//        childModelsList.add(childModel);
        if (menuModel.hasChildren) {
            Log.d("API123","here");
            childList.put(menuModel, childModelsList);
        }

        menuModel = new MenuModel("Registration Form", true,true,R.drawable.ic_consent); //Menu of Android Tutorial. No sub menus
        headerList.add(menuModel);

        if (!menuModel.hasChildren) {
            childList.put(menuModel, null);
        }

        childModelsList = new ArrayList<>();
        menuModel = new MenuModel("PIN Management", true, true,R.drawable.genealogy); //Menu of Python Tutorials
        headerList.add(menuModel);

        childModel = new MenuModel(" PIN Request Details", false, false,R.drawable.ic_arrow);
        childModelsList.add(childModel);
        if (menuModel.hasChildren) {
            childList.put(menuModel, childModelsList);
        }

        childModelsList = new ArrayList<>();
        menuModel = new MenuModel("Reports", true, true,R.drawable.ic_chart); //Menu of Python Tutorials
        headerList.add(menuModel);

        childModel = new MenuModel(" My Product", false, false,R.drawable.ic_arrow);
        childModelsList.add(childModel);
        childModel = new MenuModel(" Sponsor List", false, false,R.drawable.ic_arrow);
        childModelsList.add(childModel);
        childModel = new MenuModel(" Left Side Members", false, false,R.drawable.ic_arrow);
        childModelsList.add(childModel);
        childModel = new MenuModel(" Right Side Members", false, false,R.drawable.ic_arrow);
        childModelsList.add(childModel);
        childModel = new MenuModel(" Left Side Sales", false, false,R.drawable.ic_arrow);
        childModelsList.add(childModel);
        childModel = new MenuModel(" Right Side Sales", false, false,R.drawable.ic_arrow);
        childModelsList.add(childModel);
        if (menuModel.hasChildren) {
            childList.put(menuModel, childModelsList);
        }

        childModelsList = new ArrayList<>();
        menuModel = new MenuModel("Income Details", true, true,R.drawable.ic_hand); //Menu of Python Tutorials
        headerList.add(menuModel);

        childModel = new MenuModel(" Direct Sales Bonus", false, false,R.drawable.ic_arrow);
        childModelsList.add(childModel);
        childModel = new MenuModel(" Leader Success Bonus", false, false,R.drawable.ic_arrow);
        childModelsList.add(childModel);
        childModel = new MenuModel(" Leader Level BV", false, false,R.drawable.ic_arrow);
        childModelsList.add(childModel);
        childModel = new MenuModel(" Team Sales BV Matching", false, false,R.drawable.ic_arrow);
        childModelsList.add(childModel);
        childModel = new MenuModel(" Team Sales Bonus Details", false, false,R.drawable.ic_arrow);
        childModelsList.add(childModel);
        childModel = new MenuModel(" First Purchase BV Reports", false, false,R.drawable.ic_arrow);
        childModelsList.add(childModel);
        childModel = new MenuModel(" Repurchase BV Reports", false, false,R.drawable.ic_arrow);
        childModelsList.add(childModel);
        childModel = new MenuModel(" Repurchase Income", false, false,R.drawable.ic_arrow);
        childModelsList.add(childModel);
        childModel = new MenuModel(" Repurchase Income Details", false, false,R.drawable.ic_arrow);
        childModelsList.add(childModel);


        if (menuModel.hasChildren) {
            childList.put(menuModel, childModelsList);
        }


    }


    private void populateExpandableList() {

        final ExpandableListAdapter expandableListAdapter = new ExpandableListAdapter(this, headerList, childList);
        expandableListView.setAdapter(expandableListAdapter);

        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                if(groupPosition==0) {



                    DashBoardFragment fragment = new DashBoardFragment();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.nav_host_fragment, fragment, fragment.getClass().getSimpleName()).addToBackStack(null).commit();
                    //toolbar.setTitle("Dashboard");
                    onBackPressed();
                }

                if(groupPosition==1) {


                    Intent Intent = new Intent(getApplicationContext(), GoToShero.class);
                       startActivity(Intent);
                    onBackPressed();

                }
                if(groupPosition==3) {
                    RegistrationFragment registrationFragment = new RegistrationFragment();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.nav_host_fragment, registrationFragment, registrationFragment.getClass().getSimpleName()).addToBackStack(null).commit();
                    //toolbar.setTitle("Registration");
                    onBackPressed();

                }

                return false;
            }
        });


        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                //MenuModel model = childList.get(headerList.get(groupPosition)).get(childPosition);

//                Toast.makeText(getApplicationContext(), "Loading...",
//                        Toast.LENGTH_SHORT).show();
                if(groupPosition==2){

                    if(childPosition == 0){
                        BinaryGene binaryGene = new BinaryGene();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.nav_host_fragment, binaryGene, binaryGene.getClass().getSimpleName()).addToBackStack(null).commit();
                       // toolbar.setTitle("Binary Genealogy");
                        onBackPressed();
                    }
                    if(childPosition == 1){
                        SponsorTree sponsorTree = new SponsorTree();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.nav_host_fragment, sponsorTree, sponsorTree.getClass().getSimpleName()).addToBackStack(null).commit();
                        //toolbar.setTitle("Sponsor Tree");

                        onBackPressed();
                      }


                }
                if(groupPosition==4) {
                    if (childPosition == 0) {
//PIN REQUEST DETAILS
                        PinRequestDetailsFragment pinRequestDetailsFragment = new PinRequestDetailsFragment();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.nav_host_fragment, pinRequestDetailsFragment, pinRequestDetailsFragment.getClass().getSimpleName()).addToBackStack(null).commit();
                        //toolbar.setTitle("Pin Request");
                        onBackPressed();
//
                    }
                }

                if(groupPosition==5){
                    if(childPosition == 0){
//myproduct
                        MyProduct myProduct = new MyProduct();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.nav_host_fragment, myProduct, myProduct.getClass().getSimpleName()).addToBackStack(null).commit();
                        //toolbar.setTitle("My Product");
                        onBackPressed();

                    }
                    if(childPosition == 1){

                        SponsorList sponsorList = new SponsorList();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.nav_host_fragment, sponsorList, sponsorList.getClass().getSimpleName()).addToBackStack(null).commit();
                        //toolbar.setTitle("Sponsor List");
                        onBackPressed();
                    }
                    if(childPosition == 2){
//LEFTSIDE MEMBERS
                        LeftMember leftMember = new LeftMember();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.nav_host_fragment, leftMember, leftMember.getClass().getSimpleName()).addToBackStack(null).commit();
                        //toolbar.setTitle("LeftSide Members");

                        onBackPressed();
                    }

                    if(childPosition == 3){
//RIGHTSIDE MEMBERS
                        RightMember rightMember = new RightMember();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.nav_host_fragment, rightMember, rightMember.getClass().getSimpleName()).addToBackStack(null).commit();
                       // toolbar.setTitle("RightSide Members");

                        onBackPressed();
                    }

                    if(childPosition == 4){
//LEFTSIDE Sales

                        LeftSales leftSales = new LeftSales();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.nav_host_fragment, leftSales, leftSales.getClass().getSimpleName()).addToBackStack(null).commit();
                       // toolbar.setTitle("LeftSide Sales");
                        onBackPressed();
                    }
                    if(childPosition == 5){
//RIGHTSIDE Sales

                        RightSales rightSales = new RightSales();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.nav_host_fragment, rightSales, rightSales.getClass().getSimpleName()).addToBackStack(null).commit();
                        //toolbar.setTitle("RightSide Sales");
                        onBackPressed();
                    }

                }


                if(groupPosition==6){
                    if(childPosition == 0){
//direct sales bonus
                        DirectSalesBonusFragment directSalesBonusFragment = new DirectSalesBonusFragment();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.nav_host_fragment, directSalesBonusFragment, directSalesBonusFragment.getClass().getSimpleName()).addToBackStack(null).commit();
                        //toolbar.setTitle("Direct Sales Bonus");

                        onBackPressed();
                    }
                    if(childPosition == 1){
//leader success bonus

                        LeaderSuccessBonusFragment leaderSuccessBonusFragment = new LeaderSuccessBonusFragment();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.nav_host_fragment, leaderSuccessBonusFragment, leaderSuccessBonusFragment.getClass().getSimpleName()).addToBackStack(null).commit();
                        //toolbar.setTitle("Leader success bonus");
                         onBackPressed();
                    }
                    if(childPosition == 2){
//leader level BV
                        LeaderLevelBVFragment leaderLevelBVFragment = new LeaderLevelBVFragment();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.nav_host_fragment, leaderLevelBVFragment, leaderLevelBVFragment.getClass().getSimpleName()).addToBackStack(null).commit();
                        //toolbar.setTitle("Leader Level BV");
                        onBackPressed();
                    }

                    if(childPosition == 3){
//team sales bonus details

                        TeamSalesBVMatchingFragment teamSalesBVMatchingFragment = new TeamSalesBVMatchingFragment();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.nav_host_fragment, teamSalesBVMatchingFragment, teamSalesBVMatchingFragment.getClass().getSimpleName()).addToBackStack(null).commit();
                        //toolbar.setTitle("Team Sales BV Matching");
                        onBackPressed();
                    }



                    if(childPosition == 4){
//team sales bonus details

                        TeamSalesBonusFragment teamSalesBonusFragment = new TeamSalesBonusFragment();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.nav_host_fragment, teamSalesBonusFragment, teamSalesBonusFragment.getClass().getSimpleName()).addToBackStack(null).commit();
                        //toolbar.setTitle("Team Sales Bonus Details");

                        onBackPressed();
                    }

                    if(childPosition == 5){
//first purchase bv reports


                        FirstPurchaseBVFragment firstPurchaseBVFragment = new FirstPurchaseBVFragment();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.nav_host_fragment, firstPurchaseBVFragment, firstPurchaseBVFragment.getClass().getSimpleName()).addToBackStack(null).commit();
                       // toolbar.setTitle("First Purchase BV");

                        onBackPressed();
                    }
                    if(childPosition == 6){
//repurchase BV reports
                        RepurchaseBVReportFragment repurchaseBVReportFragment = new RepurchaseBVReportFragment();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.nav_host_fragment, repurchaseBVReportFragment, repurchaseBVReportFragment.getClass().getSimpleName()).addToBackStack(null).commit();
                        //toolbar.setTitle("Repurchase BV Report");


                        onBackPressed();
                    }
                    if(childPosition == 7){
//repurchase income
                        RepurchaseIncomeFragment repurchaseIncomeFragment = new RepurchaseIncomeFragment();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.nav_host_fragment, repurchaseIncomeFragment, repurchaseIncomeFragment.getClass().getSimpleName()).addToBackStack(null).commit();
                        //toolbar.setTitle("Repurchase Income");


                        onBackPressed();
                    }
                    if(childPosition == 8){
//repurchase income details

                        RepurchaseIncomeDetailFragment repurchaseIncomeDetailFragment = new RepurchaseIncomeDetailFragment();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.nav_host_fragment, repurchaseIncomeDetailFragment, repurchaseIncomeDetailFragment.getClass().getSimpleName()).addToBackStack(null).commit();
                        //toolbar.setTitle("Repurchase Income Detail");
                        onBackPressed();
                    }


                }
                return false;
            }});

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.myprofile) {
            startActivity(new Intent(getApplicationContext(), MyProfile.class));

            return true;
        }
        else  if (id==R.id.changepsd){
            startActivity(new Intent(getApplicationContext(),ChangePassword.class));
        }
        else if(id==R.id.logout){
          //  startActivity(new Intent(getApplicationContext(),Login.class));
            logout();
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
    private void dp(){
        String u=sharedPreferences.getString("ID","");
        ApiInterface api=ApiClient.getClient().create(ApiInterface.class);
        assert u != null;
        Call<ResponseUserImageView> usercall=api.userImageView(Integer.parseInt(u));
        usercall.enqueue(new Callback<ResponseUserImageView>() {
            @Override
            public void onResponse(Call<ResponseUserImageView> call, Response<ResponseUserImageView> response) {
           Log.i("onResponse", new Gson().toJson(response.body()));
                if (response.body().getStatus().equals("1"))
                    imgpath = response.body().getPath();
                Glide.with(getApplicationContext())
                        .load(imgpath)
                        .into(userdp);  }
            @Override
            public void onFailure(Call<ResponseUserImageView> call, Throwable t) {

            }
        });
    }

    private void logout(){
        SharedPreferences shpref;
        shpref=getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
        String username=shpref.getString("USER_NAME","");
        ApiInterface api=ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseLogout>usercall=api.Logout(username);
        usercall.enqueue(new Callback<ResponseLogout>() {
            @Override
            public void onResponse(Call<ResponseLogout> call, Response<ResponseLogout> response) {
                Log.i("onResponse", new Gson().toJson(response.body()));
                if(response.body().getStatus().equals("1")){

                    //startActivity(new Intent(getApplicationContext(),Login.class));
                    Intent intent = new Intent(MainActivity.this, Login.class);
                    startActivity(intent);
                }
                else {

                    Toast.makeText(MainActivity.this, "Please exit the Application and Login again", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseLogout> call, Throwable t) {

            }
        });
    }


    public void onBackPressed() {

        DrawerLayout layout = (DrawerLayout)findViewById(R.id.drawer_layout);
        if (layout.isDrawerOpen(GravityCompat.START)) {
            layout.closeDrawer(GravityCompat.START);
        }
        else{
            FragmentManager fm = getFragmentManager();
            if (fm.getBackStackEntryCount() > 0 && fm.getBackStackEntryCount() != 1) {
                fm.popBackStackImmediate();
            } else if (fm.getBackStackEntryCount() == 1) {
                toolbar.setTitle("dashboard..");
                fm.popBackStackImmediate();
            } else {
                toolbar.setTitle("Dashboard");
                super.onBackPressed();

            }

           //MainActivity.super.onBackPressed();

        }



    }


}
