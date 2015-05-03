package com.example.myfirstgame;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

public class IntroActivity extends Activity{
	ImageView init_crow,init_blood,init_player,init_title1,init_title2, init_button;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        		WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.intro);
        
        
        int ScreenWidth = getWindowManager().getDefaultDisplay().getWidth();
        int ScreenHeight = getWindowManager().getDefaultDisplay().getHeight();
        
        ScreenConfig.setScreenConfig(ScreenWidth,ScreenHeight);	
        
        init_crow = (ImageView)findViewById(R.id.init_crow);
        init_blood = (ImageView)findViewById(R.id.init_blood);
        init_player = (ImageView)findViewById(R.id.init_player);
        
        init_title2 = (ImageView)findViewById(R.id.init_title2);
        init_title1 = (ImageView)findViewById(R.id.init_title1);
        init_button = (ImageView)findViewById(R.id.init_button);
        
        
        Bitmap image_init_crow = BitmapFactory.decodeResource(getResources(), R.drawable.init_crow);
        Bitmap resized_crow = Bitmap.createScaledBitmap(image_init_crow , ScreenConfig.getX(500),ScreenConfig.getY(500), true);
        init_crow.setImageBitmap(resized_crow);
        init_crow.setScaleType(ImageView.ScaleType.FIT_XY);
        /*
        init_blood = (ImageView)findViewById(R.id.init_crow);
        Bitmap image_init_blood = BitmapFactory.decodeResource(getResources(), R.drawable.init_blood);
        Bitmap resized_blood = Bitmap.createScaledBitmap(image_init_blood , ScreenConfig.getX(500),ScreenConfig.getY(500), true);
        init_crow.setImageBitmap(resized_blood);
        init_crow.setScaleType(ImageView.ScaleType.FIT_XY);
        */
        /*
        ImageView init_crow = (ImageView)findViewById(R.id.init_crow);
        init_crow.setAnimation(roat);
        init_crow.startAnimation(roat);
        
        Button start = (Button)findViewById(R.id.button);
        
        //ImageView init_blood = (ImageView)findViewById(R.id.init_blood);
        start.setOnClickListener(new Button.OnClickListener(){
	        public void onClick(View v){
	        	//startActivity(new Intent(getApplicationContext(), MainActivity.class));
	        	init_crow.startAnimation(roat);
	        }
        });*/
        
        
        AnimationSet ani = new AnimationSet(true);
		ani.setInterpolator(new LinearInterpolator());
        
        TranslateAnimation trans = new TranslateAnimation(Animation.RELATIVE_TO_PARENT,0.4f,Animation.RELATIVE_TO_PARENT,0.4f,Animation.RELATIVE_TO_PARENT,-0.2f,Animation.RELATIVE_TO_PARENT,0.8f);
        trans.setDuration(2000);
        //trans.setFillAfter(true);
        ani.addAnimation(trans);
        
        RotateAnimation roat = new RotateAnimation(0,20,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        roat.setDuration(1000);
        roat.setStartOffset(1000);
       // roat.setFillAfter(true);
        //roat.setFillBefore(false);
        ani.addAnimation(roat);
        
        ani.setFillAfter(true);
        ani.setAnimationListener(new AnimationListener(){

			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onAnimationEnd(Animation animation) {
				init_crow.setVisibility(View.GONE);
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub
				
			}
        	
        });
        
        init_crow.setAnimation(ani);
        init_crow.setVisibility(View.VISIBLE);
        init_crow.startAnimation(ani);

        
        
        AlphaAnimation anim_blood = new AlphaAnimation(0,1);
        anim_blood.setDuration(100);
        anim_blood.setStartOffset(3000);
        anim_blood.setAnimationListener(new AnimationListener(){

			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onAnimationEnd(Animation animation) {
				init_blood.setAlpha(0.5f);
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub
				
			}
        	
        });
        init_blood.setAnimation(anim_blood);
        init_blood.setVisibility(View.VISIBLE);
        init_blood.startAnimation(anim_blood);
        
        
        AlphaAnimation anim_player = new AlphaAnimation(0,1);
        anim_player.setDuration(500);
        anim_player.setStartOffset(4000);
        init_player.setAnimation(anim_player);
        init_player.setVisibility(View.VISIBLE);
        init_player.startAnimation(anim_player);
        
        AlphaAnimation anim_title1 = new AlphaAnimation(0,1);
        anim_title1.setDuration(500);
        anim_title1.setStartOffset(4700);
        init_title1.setVisibility(View.VISIBLE);
        init_title1.setAnimation(anim_title1);
        init_title1.startAnimation(anim_title1);
        
        AlphaAnimation anim_title2 = new AlphaAnimation(0,1);
        anim_title2.setDuration(500);
        anim_title2.setStartOffset(5400);
        init_title2.setVisibility(View.VISIBLE);
        init_title2.setAnimation(anim_title2);
        init_title2.startAnimation(anim_title2);
        
        AlphaAnimation anim_button = new AlphaAnimation(0,1);
        anim_button.setDuration(500);
        anim_button.setStartOffset(6100);
        init_button.setVisibility(View.VISIBLE);
        anim_button.setAnimationListener(new AnimationListener(){

			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onAnimationEnd(Animation animation) {
				init_button.setClickable(true);
				//init_button.setEnabled(true);
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub
				
			}
        	
        });
        init_button.setAnimation(anim_button);
        
	}
	
	
	public void mOnClick(View v){
		startActivity(new Intent(getApplicationContext(), MainActivity.class));
    	finish();
	}
}
