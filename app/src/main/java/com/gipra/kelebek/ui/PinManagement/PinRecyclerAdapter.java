package com.gipra.kelebek.ui.PinManagement;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.gipra.kelebek.ApiClient;
import com.gipra.kelebek.ApiInterface;
import com.gipra.kelebek.R;
import com.gipra.kelebek.ui.Report.My_Product.GetProductListView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PinRecyclerAdapter extends RecyclerView.Adapter<PinRecyclerAdapter.ViewHolder>{

    private static final int CHANGE_PHOTO = 100;
    private Context context;
    private List<PinListView> pinListViews;
    private List<PinProductList> pinProductList;
    private List<GetProductListView> getProductListViews;
    private static final String TAG = "PinRecyclerAdapter";
    ViewGroup viewGroup;
    String imgpath,otrnumber,otrdate,orderdate,u,order_id;
    Dialog dialog,dialog1;

    public  PinRecyclerAdapter(List<PinListView> pinListViews, Context context){
        this.pinListViews=pinListViews;
        this.context=context;


    }


    public PinRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.pin_requestlist_layout,viewGroup,false);

        return new PinRecyclerAdapter.ViewHolder(view);
    }



    public  void onBindViewHolder(final PinRecyclerAdapter.ViewHolder viewHolder, final int i) {
        try {

            viewHolder.pin_no.setText(String.valueOf(pinListViews.get(i).getCount()));
            viewHolder.pin_date.setText(pinListViews.get(i).getDDate());
            viewHolder.pin_orderid.setText(pinListViews.get(i).getNOrderId());
            viewHolder.pin_totalamount.setText(pinListViews.get(i).getNGrandTotal());
            viewHolder.pin_totalbv.setText(pinListViews.get(i).getNTotalBv());
            viewHolder.pin_status.setText(pinListViews.get(i).getPinStatus());
            orderdate = pinListViews.get(i).getDDate();
            u = pinListViews.get(i).getNId();
            order_id = pinListViews.get(i).getNOrderId();


            //viewHolder.llotr.setVisibility(View.GONE);
            viewHolder.pin_productdetails.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    dialog = new Dialog(context);
                    // dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setCancelable(false);
                    dialog.setContentView(R.layout.popup_pinproductdetails);

                    Button btndialog = (Button) dialog.findViewById(R.id.buttonOkpdt);
                    btndialog.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            dialog.dismiss();
                        }
                    });


                    Productdetails();


                }
            });

//
//            viewHolder.pin_otrdetails.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent iotrupload = new Intent(context, OtrUpload.class);
//                    iotrupload.putExtra("order_id", order_id);
//                    context.startActivity(iotrupload);
//
//                }
//            });


            viewHolder.pin_otrdetails.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    dialog1 = new Dialog(context);
                    // dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog1.setCancelable(true);
                    dialog1.setContentView(R.layout.popup_pinotrpaymentdet);



                    Button btndialog1 = (Button) dialog1.findViewById(R.id.buttonOkotr);
                    btndialog1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            dialog1.dismiss();
                        }
                    });
                    String path="http://www.mykelebek.com/assets/otr/";
                    ApiInterface api = ApiClient.getClient().create(ApiInterface.class);
                    Log.e("order_id",order_id);
                    Call<ResponseOtrView> call = api.OtrView(Integer.parseInt(order_id),path);
                    call.enqueue(new Callback<ResponseOtrView>() {
                        @Override
                        public void onResponse(Call<ResponseOtrView> call, Response<ResponseOtrView> response) {
                            String image=response.body().getPath();
                            Log.e("otr view",image);
                            if (response.body().getStatus().equals("1")){
                                imgpath = response.body().getPath();
                                otrnumber= response.body().getCOtrno();
                                otrdate= response.body().getDOtrDate();

                                Log.e("path",imgpath);
                                Log.e("otrno",otrnumber);
                                Log.e("otrdate",otrdate);

                                TextView otr = dialog1.findViewById(R.id.otrno);
                                otr.setText(otrnumber);

                                TextView otrdateset = dialog1.findViewById(R.id.otruploaddate);
                                otrdateset.setText(otrdate);
                                ImageView otrimage = dialog1.findViewById(R.id.otrimage);
                                Glide.with(context)
                                        .load(imgpath)
                                        .error(R.drawable.imagenotfound)
                                        .diskCacheStrategy(DiskCacheStrategy.RESULT)
                                        .into(otrimage);

                                dialog1.show();
                                Window window = dialog1.getWindow();
                                window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                            }
                            else{
                                Intent iotrupload = new Intent(context,OtrUpload.class);
                                iotrupload.putExtra("order_id", order_id);
                                context.startActivity(iotrupload);




//
//                                ApiInterface api = ApiClient.getClient().create(ApiInterface.class);
//                                Call<ResponseOtrUpload>call = api.OtrUpload(requestBody, Integer.valueOf(order_id), Integer.valueOf(otrno));
//                                call.enqueue(new Callback<ResponseOtrUpload>() {
//                                    @Override
//                                    public void onResponse(Call<ResponseOtrUpload> call, Response<ResponseOtrUpload> response) {
//                                        if (response.body().getStatus().equals("1")) {
//
//
//
//
//                                            dialog.show();
//                                            Window window = dialog.getWindow();
//                                            window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//                                        }
//
//                                    }
//
//                                    @Override
//                                    public void onFailure(Call<ResponseOtrUpload> call, Throwable t) {
//
//                                    }
//                                });




                            }

                        }

                        @Override
                        public void onFailure(Call<ResponseOtrView> call, Throwable t) {

                        }
                    });

                }
            });


        }catch (Exception e){
            Log.e(TAG,"error"+e);
        }


    }
    private void Productdetails() {
        ApiInterface api = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponsePinProduct> call = api.PinProductMoreView(order_id);
        call.enqueue(new Callback<ResponsePinProduct>() {
            @Override
            public void onResponse(Call<ResponsePinProduct> call, Response<ResponsePinProduct> response) {
                if (response.body().getStatus().equals("1")) {
                    //inside
                    Log.e("PinProductMoreView","data ::::"+response.body().getData());

                    ResponsePinProduct responsePinProduct = response.body();
                    RecyclerView recyclerView = dialog.findViewById(R.id.recycler_pinpdt);
                    pinProductList = responsePinProduct.getData();
                    PinProductRecyclerAdapter pinProductRecyclerAdapter = new PinProductRecyclerAdapter(pinProductList, context);
                    recyclerView.setAdapter(pinProductRecyclerAdapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
                    dialog.show();
                    Window window = dialog.getWindow();
                    window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                }

            }

            @Override
            public void onFailure(Call<ResponsePinProduct> call, Throwable t) {

            }
        });



    }

    @Override
    public int getItemCount() {

        return pinListViews.size();
    }
    public  class ViewHolder extends RecyclerView.ViewHolder{
        private TextView pin_no,pin_date,pin_orderid, pin_totalamount,pin_totalbv,pin_productdetails,pin_otrdetails,pin_status,pin_otrno,pin_otruploaddate,pin_editotrno;
        ImageView pin_otrimage;
//        LinearLayout llotr;
        public ViewHolder(View view) {
            super(view);
            viewGroup = view.findViewById(android.R.id.content);

            pin_no=view.findViewById(R.id.pin_slno);
            pin_date=view.findViewById(R.id.pin_date);
            pin_orderid=view.findViewById(R.id.pin_orderid);
            pin_totalamount=view.findViewById(R.id.pin_totalamount);
            pin_totalbv=view.findViewById(R.id.pin_totalBV);
            pin_productdetails=view.findViewById(R.id.pin_productdetails);
            pin_otrdetails=view.findViewById(R.id.pin_otrdetails);
            pin_status=view.findViewById(R.id.pin_status);



//            pin_otrno=view.findViewById(R.id.otrno);
            pin_otrimage=view.findViewById(R.id.otrimage);
            pin_otruploaddate=view.findViewById(R.id.otruploaddate);
//           //upload
//            pin_editotrno=view.findViewById(R.id.editotrno);
//            pin_otrimageupload=view.findViewById(R.id.otrimageupload);
//            llotr=view.findViewById(R.id.linearotrupload);



        }
    }



}
