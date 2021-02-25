package com.gipra.kelebek.ui.Report.My_Product;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.gipra.kelebek.R;

import java.util.List;

class GetInvoiceRecyclerAdapter extends RecyclerView.Adapter<GetInvoiceRecyclerAdapter.ViewHolder>{

    private Context context;
    private List<Datum> datumList;
    private static final String TAG = "GetInvoiceRecycler";
    ViewGroup viewGroup;
    public GetInvoiceRecyclerAdapter(List<Datum> datumList, Context context){
        this.datumList=datumList;
        this.context=context;


    }



    public GetInvoiceRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_invoicerecycler,viewGroup,false);
        return new GetInvoiceRecyclerAdapter.ViewHolder(view);
    }

    public  void onBindViewHolder(GetInvoiceRecyclerAdapter.ViewHolder viewHolder, final int i){
        try{

                            String bill_pdtname= datumList.get(i).getProductname();
                            String bill_pdtprice= datumList.get(i).getNAmount();
                            String bill_pdtbv= datumList.get(i).getBv();
                            String bill_pdtquantity= datumList.get(i).getNQuantity();
                            String bill_pdtcgst= String.valueOf(datumList.get(i).getNCgst());
                            String bill_pdtsgst= String.valueOf(datumList.get(i).getNSgst());
                            String bill_pdtigst= String.valueOf(datumList.get(i).getNIgst());
                            String bill_pdtkfcs= datumList.get(i).getNKfcs();
                            String bill_pdttotalprice= datumList.get(i).getNSubtotal();
                            String bill_pdtsubtotalbv= String.valueOf(datumList.get(i).getNTotalBv());




                                viewHolder.bill_pdtname.setText(bill_pdtname);
                                viewHolder.bill_pdtprice.setText(bill_pdtprice);
                                viewHolder.bill_pdtbv.setText(bill_pdtbv);
                                viewHolder.bill_pdtquantity.setText(bill_pdtquantity);
                                viewHolder.bill_pdtcgst.setText(bill_pdtcgst);
                                viewHolder.bill_pdtsgst.setText(bill_pdtsgst);
                                viewHolder.bill_pdtigst.setText(bill_pdtigst);
                                viewHolder.bill_pdtkfcs.setText(bill_pdtkfcs);
                                viewHolder.bill_pdttotalprice.setText(bill_pdttotalprice);
                                viewHolder.bill_pdtsubtotalbv.setText(bill_pdtsubtotalbv);





        }catch (Exception e){
            Log.e(TAG,"error"+e);
        }

    }  @Override
    public int getItemCount() {

        return datumList.size();
    }
    public  class ViewHolder extends RecyclerView.ViewHolder{
        private TextView bill_pdtname,bill_pdtprice,bill_pdtbv,bill_pdtquantity,bill_pdtcgst,
                bill_pdtsgst,bill_pdtigst,bill_pdtkfcs,bill_pdttotalprice,bill_pdtsubtotalbv;

        public ViewHolder(View view) {
            super(view);
            viewGroup = view.findViewById(android.R.id.content);


            bill_pdtname= view.findViewById(R.id.bill_productname);
            bill_pdtprice=view.findViewById(R.id.bill_productamount);
            bill_pdtbv= view.findViewById(R.id.bill_bv);
            bill_pdtquantity= view.findViewById(R.id.bill_quantity);
            bill_pdtcgst=view.findViewById(R.id.bill_cgst);
            bill_pdtsgst=view.findViewById(R.id.bill_sgst);
            bill_pdtigst= view.findViewById(R.id.bill_igst);
            bill_pdtkfcs= view.findViewById(R.id.bill_kfcs);
            bill_pdttotalprice=view.findViewById(R.id.bill_subtotal);
            bill_pdtsubtotalbv=view.findViewById(R.id.bill_subtotalbv);

        }
    }
}