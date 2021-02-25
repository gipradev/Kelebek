package com.gipra.kelebek;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
//#f70100 orange
public class SplashScreen extends AppCompatActivity {
    //ImageView img;
    String u;

    View viewProgress;
    AnimationSet animationSet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                getWindow().getDecorView()
                        .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        );

        }
        ImageView img=findViewById(R.id.txt);
        Animation animFadeIn = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.fade_in);
        img.setVisibility(View.INVISIBLE);
        img.startAnimation(animFadeIn);
        ImageView imageView = findViewById(R.id.gify);
        GlideDrawableImageViewTarget splashScreen = new GlideDrawableImageViewTarget(imageView);
        Glide.with(this).load(R.drawable.giphybutterfly).into(splashScreen);
        viewProgress = findViewById(R.id.view_progress);
        int viewWidth = viewProgress.getWidth();

        TranslateAnimation move = new TranslateAnimation(-(getScreenWidth() / 2) + viewWidth / 2, (getScreenWidth() / 2) + viewWidth / 2 + viewWidth, 0, 0);
        move.setDuration(1000);
        TranslateAnimation move1 = new TranslateAnimation(-viewWidth, 0, 0, 0);
        move1.setDuration(900);
        ScaleAnimation laftOut = new ScaleAnimation(0, 1, 1, 1);
        laftOut.setDuration(900);

        animationSet = new AnimationSet(true);
        animationSet.addAnimation(move);
        animationSet.addAnimation(move1);
        animationSet.addAnimation(laftOut);
        animationSet.addAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slideout));

        startAnimation();
        viewProgress.startAnimation(animationSet);
    }

    private void startAnimation() {

        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences shpref;
                shpref=getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
                String username=shpref.getString("USER_NAME","0");
                Log.e("user",username);

                ApiInterface api=ApiClient.getClient().create(ApiInterface.class);
                Call<ResponseLoginCheck>call=api.LoginCheck(username);
                call.enqueue(new Callback<ResponseLoginCheck>() {
                    @Override
                    public void onResponse(Call<ResponseLoginCheck> call, Response<ResponseLoginCheck> response) {
                        Log.d("onResponse", "" +response.body().getMessage());
                        Log.e("status",response.body().getStatus());
                        if (response.body().getStatus().equals("1")){

                            startActivity(new Intent(getApplicationContext(),MainActivity.class));

                            overridePendingTransition(R.anim.slide_in,R.anim.zoom_out);
                            finish();
                        }
                        else {

                            startActivity(new Intent(getApplicationContext(),Login.class));
                            overridePendingTransition(R.anim.fade_in,R.anim.slide_in);

                            //finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseLoginCheck> call, Throwable t) {

                    }
                });

            }
        }, 1000);
    }

    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }


}
