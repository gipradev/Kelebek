package com.gipra.kelebek.ui.Report.My_Product;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gipra.kelebek.ApiClient;
import com.gipra.kelebek.ApiInterface;
import com.gipra.kelebek.R;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.Future;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.NOTIFICATION_SERVICE;

public class ProductListViewAdapter extends RecyclerView.Adapter<ProductListViewAdapter.ViewHolder> {
    private Context context;
    private List<MyProductListView> myProductListViews;
    private List<GetProductListView> getProductListViews;
    private List<Datum> datumList;
    private static final String TAG = "ProductListViewAdapter";
    private Dialog dialog;
    String orderdate,orderid;
    String filename;
    String u;
    Uri uri;
    TextView bill_no,bill_date,bill_name,bill_address,bill_district,bill_state,bill_mobile,
            bill_email,bill_pdtgrandtotal,bill_pdttotalbv;
    Button downloadpdf;
    FileOutputStream fos;
    Bitmap b;
    View vv;
    File filePath;
    String path1,path,imagesUri,signature_pdf_="kelebekinvoice",signature_img_;

    File folder;
    Button download;
    TextView downloadCount;
    ProgressBar progressBar;

    // --- Add these variables
    private NotificationManager mNotifyManager;
    private NotificationCompat.Builder mBuilder;
    // ---

    Future<File> downloading;



    public ProductListViewAdapter(List<MyProductListView> myProductListViews, Context context,String u) {
        this.myProductListViews = myProductListViews;
        this.context = context;
        this.u=u;
    }
    public ProductListViewAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_my_productlist, viewGroup, false);
        return new ViewHolder(view);
    }

    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
        try {
            viewHolder.mypdt_count.setText(String.valueOf(myProductListViews.get(i).getCount()));
            viewHolder.mypdt_orderdate.setText(myProductListViews.get(i).getOrdereddate());
            orderdate=viewHolder.mypdt_orderdate.getText().toString();
            orderid=String.valueOf(myProductListViews.get(i).getNOrderId());
            Log.e(TAG,"ordered date : "+orderdate);

            viewHolder.mypdt_pdtdetails.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog = new Dialog(context);
                    // dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

                    dialog.setCancelable(true);
                    dialog.setContentView(R.layout.popup_getproduct);

                    Button btndialog = (Button) dialog.findViewById(R.id.getpdt_okbtn);
                    btndialog.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            dialog.dismiss();
                        }
                    });
                    ApiInterface api = ApiClient.getClient().create(ApiInterface.class);
                    Call<ResponseGetProduct> call = api.GetProduct(u,orderdate);
                    call.enqueue(new Callback<ResponseGetProduct>() {
                        @Override
                        public void onResponse(Call<ResponseGetProduct> call, Response<ResponseGetProduct> response) {
                            if (response.body().getStatus().equals("1")) {

                                ResponseGetProduct responseGetProduct = response.body();
                                RecyclerView recyclerView = dialog.findViewById(R.id.recycler_getpdt);
                                getProductListViews = responseGetProduct.getData();
                                GetProductRecyclerAdapter getProductRecyclerAdapter = new GetProductRecyclerAdapter(getProductListViews, context);
                                recyclerView.setAdapter(getProductRecyclerAdapter);
                                recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));

                                dialog.show();
                                Window window = dialog.getWindow();
                                window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                            }
                        }
                        @Override
                        public void onFailure(Call<ResponseGetProduct> call, Throwable t) {

                        }
                    });

                }
            });

            viewHolder.mypdt_billdetails.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog = new Dialog(context);
                    // dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

                    dialog.setCancelable(true);
                    dialog.setContentView(R.layout.invoice_myproduct);

                    ImageView btnback = (ImageView) dialog.findViewById(R.id.back_invoice);
                    btnback.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            dialog.dismiss();
                        }
                    });

                    bill_no=dialog.findViewById(R.id.bill_no);
                    bill_date= dialog.findViewById(R.id.bill_date);
                    bill_name= dialog.findViewById(R.id.bill_name);
                    bill_address=dialog.findViewById(R.id.bill_address);
                    bill_district= dialog.findViewById(R.id.bill_district);
                    bill_state= dialog.findViewById(R.id.bill_state);
                    bill_mobile=dialog.findViewById(R.id.bill_mobile);
                    bill_email= dialog.findViewById(R.id.bill_email);
                    bill_pdtgrandtotal=dialog.findViewById(R.id.bill_grandtotal);
                    bill_pdttotalbv=dialog.findViewById(R.id.bill_grandtotalbv);

                    downloadpdf=dialog.findViewById(R.id.downloadpdf);
                    downloadpdf.setOnClickListener(new View.OnClickListener() {
                        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                        @Override
                        public void onClick(View v) {
                            takeScreenShot();
                        }
                    });

                    ApiInterface api = ApiClient.getClient().create(ApiInterface.class);
                    Call<ResponseGetBill> call = api.GetBill(u,orderdate,orderid);
                    call.enqueue(new Callback<ResponseGetBill>() {
                        @Override
                        public void onResponse(Call<ResponseGetBill> call, Response<ResponseGetBill> response) {


                            if (response.body().getStatus().equals("1")) {
                                ResponseGetBill responseGetBill = response.body();


                                String billno= String.valueOf(responseGetBill.getNOrderId());
                                String billdate= responseGetBill.getInvdate();
                                String billname= responseGetBill.getCFNAME();
                                String billaddress= responseGetBill.getCADDRESS1();
                                String billdistrict= responseGetBill.getDistrictame();
                                String billstate= responseGetBill.getStatename();
                                String billmobile=responseGetBill.getCMOBILE();
                                String billemail= responseGetBill.getCEMAIL();
                                String billpdtgrandtotal= String.valueOf(responseGetBill.getGrandTotal());
                                String billpdttotalbv= String.valueOf(responseGetBill.getGrandTotalPv());

                                bill_no.setText(billno);
                                bill_date.setText(billdate);
                                bill_name.setText(billname);
                                bill_address.setText(billaddress);
                                bill_district.setText(billdistrict);
                                bill_state.setText(billstate);
                                bill_email.setText(billemail);
                                bill_mobile.setText(billmobile);
                                bill_pdtgrandtotal.setText(billpdtgrandtotal);
                                bill_pdttotalbv.setText(billpdttotalbv);

                                RecyclerView recyclerView1 = dialog.findViewById(R.id.recycler_invoice);
                                datumList = responseGetBill.getData();
                                GetInvoiceRecyclerAdapter getInvoiceRecyclerAdapter = new GetInvoiceRecyclerAdapter(datumList, context);
                                recyclerView1.setAdapter(getInvoiceRecyclerAdapter);
                                recyclerView1.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));

                                dialog.show();
                                Window window = dialog.getWindow();
                                window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseGetBill> call, Throwable t) {

                        }
                    });

                }
            });

        } catch (Exception e) {
            Log.e(TAG, "error" + e);
        }
    }

    private void takeScreenShot() {

        folder = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Kelebek/");

        if (!folder.exists()) {
            boolean success = folder.mkdir();
        }

        path = folder.getAbsolutePath();

        filename=signature_pdf_ + System.currentTimeMillis() + ".pdf";
        path = path + "/" +filename;// path where pdf will be stored

        View vv = dialog.findViewById(R.id.scroll);
        ScrollView z = (ScrollView) dialog.findViewById(R.id.scroll); // parent view
        int totalHeight = z.getChildAt(0).getHeight();// parent view height
        int totalWidth = z.getChildAt(0).getWidth();// parent view width

        //Save bitmap to  below path
        String extr = Environment.getExternalStorageDirectory() + "/Kelebekjpg/";
        File file = new File(extr);
        if (!file.exists())
            file.mkdir();
        String fileName = signature_img_ + ".jpg";
        File myPath = new File(extr, fileName);
        imagesUri = myPath.getPath();
        FileOutputStream fos = null;
        b = getBitmapFromView(vv, totalHeight, totalWidth);

        try {

          //  if (ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                // Do the file write
            fos = new FileOutputStream(imagesUri);
            b.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.flush();
            fos.close();
          //  } else {
                // Request permission from the user
           //     ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
           // }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        createPdf();// create pdf after creating bitmap and saving

    }

    private void createPdf() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
        PdfDocument document = null;
        document = new PdfDocument();
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(b.getWidth(), b.getHeight(), 1).create();
        PdfDocument.Page page = document.startPage(pageInfo);
        Canvas canvas = page.getCanvas();
        Paint paint = new Paint();
        paint.setColor(Color.parseColor("#ffffff"));
        canvas.drawPaint(paint);
        Bitmap bitmap = Bitmap.createScaledBitmap(b, b.getWidth(), b.getHeight(), true);
        paint.setColor(Color.BLUE);
        canvas.drawBitmap(bitmap, 0, 0, null);
        document.finishPage(page);
        filePath = new File(path);
        Log.e(TAG,"file path.."+filePath);

        try {

            if (ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE
                    ) == PackageManager.PERMISSION_GRANTED) {
                // Do the file write
                document.writeTo(new FileOutputStream(filePath));
                sendNotification();
                openPdf();
                Toast.makeText(context, "downloaded: " + path, Toast.LENGTH_LONG).show();
                document.close();
           } else {
                // Request permission from the user
                ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
            }

        } catch (IOException e) {
            Log.e(TAG,"createpdf error.. "+e.toString());
            e.printStackTrace();
            Toast.makeText(context, "Something wrong: " + e.toString(), Toast.LENGTH_LONG).show();
        }
            // close the document
             // You can open pdf after complete
    }
    }

    private void sendNotification() {
        String NOTIFICATION_CHANNEL_ID = "my_channel_id_01";
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);


        try{


    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "My Notifications", NotificationManager.IMPORTANCE_DEFAULT);

        // Configure the notification channel.
        notificationChannel.setDescription("Channel description");
        notificationChannel.enableLights(true);
        notificationChannel.setLightColor(Color.BLUE);
        notificationChannel.enableVibration(true);
        notificationManager.createNotificationChannel(notificationChannel);
    }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context,NOTIFICATION_CHANNEL_ID);
        builder.setSmallIcon(android.R.drawable.stat_sys_download_done);
            //Uri URI = FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName() + ".provider", folder);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        //intent.setDataAndType(Uri.parse(folder+filename),"application/pdf");
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            intent.setDataAndType(uri,"application/pdf");
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        builder.setContentIntent(pendingIntent);
        builder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.logo_new));
        builder.setContentTitle(filename);
        builder.setContentText("Invoice Downloaded");
        builder.setSubText("Tap to view the pdf.");
       // NotificationManager notificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);

        // Will display the notification in the notification bar

        notificationManager.notify(1, builder.build());

        } catch (Exception e) {
        Log.e(TAG,"notification error.. "+e.toString());
        e.printStackTrace();
        Toast.makeText(context, "Something wrong notification " + e.toString(), Toast.LENGTH_LONG).show();
    }
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void openPdf() {

File file=new File(Environment.getExternalStorageDirectory() + "/Kelebek/"+filename);

Log.e(TAG,"path "+path);

        if (Build.VERSION.SDK_INT < 24) {
            uri = Uri.fromFile(file);
        }
        else {
            uri = Uri.parse(path); //  doesn't work in Android 10.
        }

        if (filePath.exists()) //Checking for the file is exist or not
        {
            Log.e(TAG,"exist pdf ");
            Log.e(TAG,"filepath "+filePath);
            Log.e(TAG,"uri "+uri.toString());
            //Uri uri = Uri.fromFile(folder);
            try {
                if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {

            Intent objIntent = new Intent(Intent.ACTION_VIEW);
            objIntent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            objIntent.setFlags(Intent. FLAG_ACTIVITY_CLEAR_TOP);
            objIntent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            objIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
           objIntent.setDataAndType(uri,"application/pdf");
                    PackageManager pm = context.getPackageManager();
                    List<ResolveInfo> activities = pm.queryIntentActivities(objIntent, 0);
                    if (activities.size() > 0) {
                        context.startActivity(objIntent);
                    }
                    //objIntent.setDataAndType(Uri.parse(folder+filename),"application/pdf");
               // context.startActivity(objIntent);//Staring the pdf viewer
                   // context.startActivity(objIntent);
                     }else {
            // Request permission from the user
            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
        }

            } catch (ActivityNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(context,"You don't have a pdf viewer!!! please install a pdf viewer",Toast.LENGTH_SHORT).show();// Instruct the user to install a PDF reader here, or something
            }
        }

        else
        {
            Log.e(TAG,"file not exist");
        }
    }

    public Bitmap getBitmapFromView(View vv, int totalHeight, int totalWidth) {

        Bitmap returnedBitmap = Bitmap.createBitmap(totalWidth,totalHeight , Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(returnedBitmap);
        Drawable bgDrawable = vv.getBackground();
        if (bgDrawable != null)
            bgDrawable.draw(canvas);
        else
            canvas.drawColor(Color.WHITE);
        vv.draw(canvas);
        return returnedBitmap;
    }

    @Override
    public int getItemCount() {

        return myProductListViews.size();
    }
    public  class ViewHolder extends RecyclerView.ViewHolder{
        private TextView mypdt_count,mypdt_orderdate,mypdt_pdtdetails,mypdt_billdetails;
        public ViewHolder(View view) {
            super(view);

            mypdt_count=view.findViewById(R.id.mypdt_count);
            mypdt_orderdate=view.findViewById(R.id.mypdt_orderdate);
            mypdt_pdtdetails=view.findViewById(R.id.mypdt_viewdetail);
            mypdt_billdetails=view.findViewById(R.id.mypdt_viewbill);

        }
    }
}








