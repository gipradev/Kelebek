package com.gipra.kelebek.atv;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.gipra.kelebek.Gene.GeneList;
import com.gipra.kelebek.R;

import java.util.ArrayList;


public class Treewithsponsers extends AppCompatActivity {
    private ArrayList<GeneList> geneList;
    String uid0,uid1,uid2,uid3,uid4,uid5,uid6;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_treewithsponser);




        SharedPreferences shpref;
        shpref=getApplicationContext().getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
        String u=shpref.getString("ID","");
//        gene(u);
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
//
//                List<TreeClass> childModelsList = new ArrayList<>();
//                List<TreeClass> childofchildModelsList = new ArrayList<>();
//
//                String username = geneList.get(0).getName();
//                String lcount = geneList.get(0).getLeftCount();
//                String rcount = geneList.get(0).getRightCount();
//                String lactive = geneList.get(0).getLeftActive();
//                String ractive = geneList.get(0).getRightActive();
//                String lpv = geneList.get(0).getLeftPv();
//                String rpv = geneList.get(0).getRightPv();
//                String name = geneList.get(0).getName();
//                //String spuserid_one = geneList.get(0).getSponsorId();
//
//
//                String spuserid_one = geneList.get(0).getSponsorId();
//                String spuserid_two = geneList.get(1).getSponsorId();
//                String spuserid_tree = geneList.get(2).getSponsorId();
//                String spuserid_four = geneList.get(3).getSponsorId();
//                String spuserid_five = geneList.get(4).getSponsorId();
//                String spuserid_six = geneList.get(5).getSponsorId();
//                String spuserid_seven = geneList.get(6).getSponsorId();
//                //String a1=geneList.get(1).getActive();
//                Log.i("first", "uid1"+spuserid_one);
//                //Root
//                TreeNode root = TreeNode.root();
//
//                //Parent
//                MyHolder.IconTreeItem nodeItem = new MyHolder.IconTreeItem(R.drawable.ic_arrow_drop_down,spuserid_one);
//                TreeNode parent = new TreeNode(nodeItem).setViewHolder(new MyHolder(getApplicationContext(), true, MyHolder.DEFAULT, MyHolder.DEFAULT));
//
//                //Child
//                MyHolder.IconTreeItem childItem1 = new MyHolder.IconTreeItem(R.drawable.ic_plustree, geneList.get(0).getName());
//                TreeNode child1 = new TreeNode(childItem1).setViewHolder(new MyHolder(getApplicationContext(), true, R.layout.child, 25));
//
//                //Sub Child
//                MyHolder.IconTreeItem subChildItem1 = new MyHolder.IconTreeItem(R.drawable.ic_plustree, "Sub Child");
//                TreeNode subChild1 = new TreeNode(subChildItem1).setViewHolder(new MyHolder(getApplicationContext(), false, R.layout.child, 50));
//
//
//                MyHolder.IconTreeItem childItem2 = new MyHolder.IconTreeItem(R.drawable.ic_plustree, geneList.get(1).getName());
//                TreeNode child2 = new TreeNode(childItem2).setViewHolder(new MyHolder(getApplicationContext(), true, R.layout.child, 25));
//
//                //Sub Child
//                MyHolder.IconTreeItem subChildItem2 = new MyHolder.IconTreeItem(R.drawable.ic_plustree, "Sub Child");
//                TreeNode subChild2 = new TreeNode(subChildItem2).setViewHolder(new MyHolder(getApplicationContext(), false, R.layout.child, 50));
//                //
////                childItem = new MyHolder.IconTreeItem(R.drawable.ic_plustree, geneList.get(2).getName());
////                child = new TreeNode(childItem).setViewHolder(new MyHolder(getApplicationContext(), true, R.layout.child, 25));
////
////
////                childItem = new MyHolder.IconTreeItem(R.drawable.ic_plustree, geneList.get(3).getName());
////                child = new TreeNode(childItem).setViewHolder(new MyHolder(getApplicationContext(), true, R.layout.child, 25));
////
////                childItem = new MyHolder.IconTreeItem(R.drawable.ic_plustree, geneList.get(4).getName());
////                child = new TreeNode(childItem).setViewHolder(new MyHolder(getApplicationContext(), true, R.layout.child, 25));
////                childItem = new MyHolder.IconTreeItem(R.drawable.ic_plustree, geneList.get(5).getName());
////                child = new TreeNode(childItem).setViewHolder(new MyHolder(getApplicationContext(), true, R.layout.child, 25));
////                childItem = new MyHolder.IconTreeItem(R.drawable.ic_plustree, geneList.get(6).getName());
////                child = new TreeNode(childItem).setViewHolder(new MyHolder(getApplicationContext(), true, R.layout.child, 25));
////
//                child1.addChildren(subChild1);
//                //Add child.
//                child2.addChild(subChild2);
//                //Add child.
//                parent.addChildren(child1,child2);
//                root.addChild(parent);
//
//                //Add AndroidTreeView into view.
//                AndroidTreeView tView = new AndroidTreeView(getApplicationContext(),root);
//                ((LinearLayout) findViewById(R.id.parent2)).addView(tView.getView());
//
//            }
//            @Override
//            public void onFailure(Call<ResponseGene> call, Throwable t) {
//
//            }
//        });
//    }




}