package com.gipra.kelebek.ui.PinManagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.loader.content.CursorLoader;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.gipra.kelebek.ApiClient;
import com.gipra.kelebek.ApiInterface;
import com.gipra.kelebek.MyProfile.MyProfile;
import com.gipra.kelebek.R;
import com.gipra.kelebek.ResponseUserImage;
import com.google.gson.Gson;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OtrUpload extends AppCompatActivity {
    private static final String TAG = "OtrUpload";
    String order_id;
    String otrno;
    EditText editotrno;
    ImageView img,back;
    Uri changephoto;
    Button save,upload_image;
    private  static final  int CHANGE_PHOTO=100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otr_upload);
        editotrno=findViewById(R.id.otrnumberupload);
        img=findViewById(R.id.otrimageupload);
        back=findViewById(R.id.backarrowotr);
        upload_image=findViewById(R.id.buttonuploadimageotr);
        save=findViewById(R.id.saveotr);
        upload_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handlePermission();
                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, 100);
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               imageuploadotr(changephoto);
            }
        });
    }

    private void handlePermission(){
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return;
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            //ask for permission
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, CHANGE_PHOTO);

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==100 && resultCode==RESULT_OK && data!=null) {
            changephoto = data.getData();
            if (null != changephoto) {
                String path = getchangephotopath(changephoto);
                Log.i(TAG, "otrimageupload...." + path);
                img.post(new Runnable() {
                    @Override
                    public void run() {
                        img.setImageURI(changephoto);

                    }
                });

            }
        }
    }

            private void imageuploadotr (Uri fileUri){

        try {
            final ProgressDialog progressDialog = new ProgressDialog(getApplicationContext());
            progressDialog.setCancelable(true);
            progressDialog.setMessage("updating...");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.show();
            File file = new File(getchangephotopath(fileUri));
            RequestBody requestBody = RequestBody.create(MediaType.parse(getContentResolver().getType(fileUri)), file);
            SharedPreferences sharedPreferences;
            sharedPreferences = getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
            String memberid = sharedPreferences.getString("ID", "");
            ApiInterface api = ApiClient.getClient().create(ApiInterface.class);
            Bundle bundle = getIntent().getExtras();
            order_id = bundle.getString("order_id");
            if (editotrno.getText().toString().equals("")) {
                editotrno.setError("please enter your otr number");
            }
            otrno = editotrno.getText().toString();
            Log.e(TAG, "orderid" + order_id);
            Log.e(TAG, "otrno" + otrno);

            Call<ResponseOtrUpload> usercall = api.OtrUpload(requestBody,Integer.parseInt(order_id),Integer.parseInt(otrno));
            usercall.enqueue(new Callback<ResponseOtrUpload>() {
                @Override
                public void onResponse(Call<ResponseOtrUpload> call, Response<ResponseOtrUpload> response) {
                    Log.i("onResponse", new Gson().toJson(response.body()));
                    if (response.body().getStatus().equals("1")) {
                        progressDialog.dismiss();
                        Toast.makeText(OtrUpload.this, "Updated successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(OtrUpload.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseOtrUpload> call, Throwable t) {
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
            }

            public String getchangephotopath (Uri photoUri){
                String[] photo = {MediaStore.Images.Media.DATA};
                CursorLoader loader = new CursorLoader(this, photoUri, photo, null, null, null);
                Cursor cursor = loader.loadInBackground();
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                cursor.moveToFirst();
                String result = cursor.getString(column_index);
                cursor.close();
                return result;
            }

        }
