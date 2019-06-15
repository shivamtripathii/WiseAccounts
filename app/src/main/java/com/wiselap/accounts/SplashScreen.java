package com.wiselap.accounts;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.wiselap.accounts.SignIn.LoginActivity;
import com.wiselap.accounts.databinding.ActivitySplashScreenBinding;

public class SplashScreen extends AppCompatActivity {
    ActivitySplashScreenBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_splash_screen);
        Animation animation= AnimationUtils.loadAnimation(this,R.anim.splash);
        binding.text.startAnimation(animation);
        final Intent intent=new Intent(this, LoginActivity.class);
        Thread thread=new Thread(){
            public void run(){
                try {
                    sleep(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    startActivity(intent);
                    finish();
                }
            }

        }; thread.start();
    }
}
