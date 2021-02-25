package com.gipra.kelebek.ui.Genealogy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ExpandableListView;

import com.gipra.kelebek.Gene.GeneList;
import com.gipra.kelebek.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TreeSponsers extends AppCompatActivity {

    ExpandableListView expandableListView;
    List<TreeClass> headerList = new ArrayList<>();
    HashMap<TreeClass, List<TreeClass>> childList = new HashMap<>();
    HashMap<TreeClass, List<TreeClass>> childofchildList = new HashMap<>();
    private ArrayList<GeneList> geneList;
    String uid0,uid1,uid2,uid3,uid4,uid5,uid6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tree_sponsers);

//
//        DisplayMetrics metrics = new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics(metrics);
//        int width = metrics.widthPixels;
//
//        expandableListView = findViewById(R.id.treeexpandableListView);
//        expandableListView.setIndicatorBounds(width - GetPixelFromDips(10), width - GetPixelFromDips(10));
//
//
//
//
//        SharedPreferences shpref;
//        shpref=getApplicationContext().getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
//        String u=shpref.getString("ID","");
       // gene(u);
    }






//    private  void gene(String id){
//        ApiInterface api= ApiClient.getClient().create(ApiInterface.class);
//        final Call<ResponseGene> usercall=api.Gene(Integer.parseInt(id));
//        usercall.enqueue(new Callback<ResponseGene>() {
//            @Override
//            public void onResponse(Call<ResponseGene> call, final Response<ResponseGene> response) {
//                Log.i("onResponse", new Gson().toJson(response.body()));
//
//                final ResponseGene responseGene = response.body();
//                geneList = (ArrayList<GeneList>) responseGene.getData();
//
//
//                uid0=geneList.get(0).getUserid();
//                String a0=geneList.get(0).getActive();
//                prepareMenuData();
//                populateExpandableList();
//
//
//            }
//            @Override
//            public void onFailure(Call<ResponseGene> call, Throwable t) {
//
//            }
//        });
//    }
//
//
//    private void prepareMenuData() {
//
//
//        List<TreeClass> childModelsList = new ArrayList<>();
//        List<TreeClass> childofchildModelsList = new ArrayList<>();
//
//        String username = geneList.get(0).getName();
//        String lcount = geneList.get(0).getLeftCount();
//        String rcount = geneList.get(0).getRightCount();
//        String lactive = geneList.get(0).getLeftActive();
//        String ractive = geneList.get(0).getRightActive();
//        String lpv = geneList.get(0).getLeftPv();
//        String rpv = geneList.get(0).getRightPv();
//        String name = geneList.get(0).getName();
//        //String spuserid_one = geneList.get(0).getSponsorId();
//
//
//        String spuserid_one = geneList.get(0).getSponsorId();
//        String spuserid_two = geneList.get(1).getSponsorId();
//        String spuserid_tree = geneList.get(2).getSponsorId();
//        String spuserid_four = geneList.get(3).getSponsorId();
//        String spuserid_five = geneList.get(4).getSponsorId();
//        String spuserid_six = geneList.get(5).getSponsorId();
//        String spuserid_seven = geneList.get(6).getSponsorId();
//        //String a1=geneList.get(1).getActive();
//        Log.i("first", "uid1"+spuserid_one);
//
//        if (spuserid_one==null){
//
//            TreeClass childModel = new TreeClass("no data", false, false);
//            childModelsList.add(childModel);
//
//        }else {
//
//            TreeClass treeClass = new TreeClass(spuserid_one, true,true); //Menu of Android Tutorial. No sub menus
//            headerList.add(treeClass);
//
//
//            TreeClass childModel = new TreeClass(geneList.get(1).getName(), true, true);
//            headerList.add(childModel);
//
//            childModel = new TreeClass(geneList.get(2).getName(), true, true);
//            headerList.add(childModel);
//
//            childModel = new TreeClass(geneList.get(3).getName(), true, true);
//            headerList.add(childModel);
//
//            childModel = new TreeClass(geneList.get(4).getName(), true, true);
//            headerList.add(childModel);
//
//            childModel = new TreeClass(geneList.get(5).getName(), true, true);
//            headerList.add(childModel);
//
//            childModel = new TreeClass(geneList.get(6).getName(), true, true);
//            headerList.add(childModel);
//
//            if (treeClass.hasChildren) {
//                Log.d("API123", "here");
//                childList.put(treeClass, headerList);
//            }
//
//
//            if (!treeClass.hasChildren) {
//                childList.put(treeClass, null);
//            }
//
//
//            TreeClass childModel1 = new TreeClass(geneList.get(0).getName(), true, true);
//            headerList.add(childModel1);
//
//
//            TreeClass cchildModel = new TreeClass(geneList.get(1).getName(), true, true);
//            childModelsList.add(cchildModel);
//
//            cchildModel = new TreeClass(geneList.get(2).getName(), true, true);
//            childModelsList.add(cchildModel);
//
//
//            if (treeClass.hasChildren) {
//                childList.put(childModel1, childModelsList);
//            }
//
//        }
//
//    }
//
//
//
//    private void populateExpandableList() {
//
//        final TreeExpandableListAdapter treeExpandableListAdapter = new TreeExpandableListAdapter(this, headerList, childList);
//        expandableListView.setAdapter(treeExpandableListAdapter);
//
//        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
//            @Override
//            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
//
////                if(groupPosition==0) {
////                    DashBoardFragment fragment = new DashBoardFragment();
////                    getSupportFragmentManager().beginTransaction()
////                            .replace(R.id.nav_host_fragment, fragment, fragment.getClass().getSimpleName()).addToBackStack(null).commit();
////                    onBackPressed();
////
////                }
////
////                if(groupPosition==1) {
////
////
////                    Intent Intent = new Intent(getApplicationContext(), GoToShero.class);
////                    startActivity(Intent);
////                    onBackPressed();
////
////                }
////                if(groupPosition==3) {
////                    RegistrationFragment registrationFragment = new RegistrationFragment();
////                    getSupportFragmentManager().beginTransaction()
////                            .replace(R.id.nav_host_fragment, registrationFragment, registrationFragment.getClass().getSimpleName()).addToBackStack(null).commit();
////                    onBackPressed();
////
////                }
//
//                return false;
//            }
//        });
//
//
//        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
//            @Override
//            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
//                //TreeClass model = childList.get(headerList.get(groupPosition)).get(childPosition);
//
//
//                Toast.makeText(getApplicationContext(), "Clicked On Child"+childPosition+"group"+groupPosition,
//                        Toast.LENGTH_SHORT).show();
//
//                return false;
//            }});
//
//    }
//
//
//
//
//
//
//
//    public int GetPixelFromDips(float pixels) {
//        // Get the screen's density scale
//        final float scale = getResources().getDisplayMetrics().density;
//        // Convert the dps to pixels, based on density scale
//        return (int) (pixels * scale + 0.5f);
//    }

}
