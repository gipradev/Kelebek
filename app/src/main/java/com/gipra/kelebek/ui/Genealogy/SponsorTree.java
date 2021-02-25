package com.gipra.kelebek.ui.Genealogy;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.gipra.kelebek.ApiClient;
import com.gipra.kelebek.ApiInterface;
import com.gipra.kelebek.R;
import com.gipra.kelebek.atv.MyHolder;
import com.gipra.kelebek.atv.model.TreeNode;
import com.gipra.kelebek.atv.view.AndroidTreeView;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class SponsorTree extends Fragment {

    private ArrayList<SponsortreeList> sponsortreeLists;

    String uid0,uid1,uid2,uid3,uid4,uid5,uid6;
    View view;
    public SponsorTree() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_sponsor_tree, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Sponsor Tree");

        SharedPreferences shpref;
        shpref=getContext().getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
        String u=shpref.getString("ID","");
        gene(u);

        return view;
    }

    private  void gene(String id){


        ApiInterface api= ApiClient.getClient().create(ApiInterface.class);
        final Call<ResponseSponsorTree> usercall=api.SponsorTree(Integer.parseInt(id));
        usercall.enqueue(new Callback<ResponseSponsorTree>() {
            @Override
            public void onResponse(Call<ResponseSponsorTree> call, final Response<ResponseSponsorTree> response) {
                Log.i("onResponse", new Gson().toJson(response.body()));

                final ResponseSponsorTree responseSponsorTree = response.body();
                sponsortreeLists = (ArrayList<SponsortreeList>) responseSponsorTree.getData();


//                String basenode=sponsortreeLists.get(0).getCFNAME();
//                String a0=sponsortreeLists.get(0).getActive();



                Log.e("first", "array size"+sponsortreeLists.size());

                //Root
                TreeNode root = TreeNode.root();
                //TreeNode child1 = null,subChild1;

                //Parent
                MyHolder.IconTreeItem nodeItem = new MyHolder.IconTreeItem(R.drawable.ic_arrow_drop_down,responseSponsorTree.getcUsername1());
                TreeNode parent = new TreeNode(nodeItem).setViewHolder(new MyHolder(getContext(), true, MyHolder.DEFAULT, MyHolder.DEFAULT));

                // loop for child

                for(int i=0; i<sponsortreeLists.size(); i++) {
                    //Child
                    MyHolder.IconTreeItem childItem1 = new MyHolder.IconTreeItem(R.drawable.ic_plustree, sponsortreeLists.get(i).getCUSERNAME());
                     TreeNode child1 = new TreeNode(childItem1).setViewHolder(new MyHolder(getContext(), true, R.layout.child, 25));
                    parent.addChildren(child1);

                }

                root.addChild(parent);
                //Add AndroidTreeView into view.
                AndroidTreeView tView = new AndroidTreeView(getContext(),root);
                ((LinearLayout)view.findViewById(R.id.parent1)).addView(tView.getView());


/// add api to the view of child node---future


            }

            @Override
            public void onFailure(Call<ResponseSponsorTree> call, Throwable t) {

            }
        });
    }






}
