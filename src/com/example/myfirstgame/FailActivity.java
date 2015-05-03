package com.example.myfirstgame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

public class FailActivity extends Activity{
	ImageView fail_player,fail_yes,fail_no;
	TextView fail_message1, fail_message2, fail_message3;
	//private Bitmap mBitmap;
	private int mTimeCount;
	
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        		WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.fail);
        
        fail_player = (ImageView)findViewById(R.id.fail_player);
        fail_yes = (ImageView)findViewById(R.id.fail_yes);
        fail_no = (ImageView)findViewById(R.id.fail_no);
        fail_message1 = (TextView)findViewById(R.id.fail_message1);
        fail_message2 = (TextView)findViewById(R.id.fail_message2);
        fail_message3 = (TextView)findViewById(R.id.fail_message3);
        
        AnimationSet ani = new AnimationSet(true);
        ScaleAnimation scale = new ScaleAnimation(1,0,1,0);
        scale.setFillAfter(true);
        scale.setStartOffset(2000);
        scale.setDuration(2000);
        ani.addAnimation(scale);
        
        TranslateAnimation trans = new TranslateAnimation(Animation.RELATIVE_TO_PARENT,-0.3f,Animation.RELATIVE_TO_PARENT,0.5f,Animation.RELATIVE_TO_PARENT,0.2f,Animation.RELATIVE_TO_PARENT,-0.3f);
        scale.setStartOffset(2000);
        trans.setDuration(2000);
        ani.addAnimation(trans);
        
        ani.setFillAfter(true);
        fail_player.setAnimation(ani);
        fail_player.startAnimation(ani);
        
        AlphaAnimation alpha1 = new AlphaAnimation(0,1);
        alpha1.setDuration(2000);
        alpha1.setStartOffset(4500);
        fail_message1.setAnimation(alpha1);
        fail_message1.startAnimation(alpha1);
        
        AlphaAnimation alpha2 = new AlphaAnimation(0,1);
        alpha2.setDuration(2000);
        alpha2.setStartOffset(7500);
        fail_message2.setAnimation(alpha2);
        fail_message2.startAnimation(alpha2);
        
        AlphaAnimation alpha3 = new AlphaAnimation(0,1);
        alpha3.setDuration(2000);
        alpha3.setStartOffset(10000);
        fail_message3.setAnimation(alpha3);
        fail_message3.startAnimation(alpha3);
        
        AlphaAnimation alpha4 = new AlphaAnimation(0,1);
        alpha4.setDuration(500);
        alpha4.setStartOffset(12000);
        
        alpha4.setAnimationListener(new AnimationListener(){

			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onAnimationEnd(Animation animation) {
				fail_yes.setClickable(true);
				//init_button.setEnabled(true);
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub
				
			}
        	
        });
        fail_yes.setAnimation(alpha4);
        fail_yes.startAnimation(alpha4);
        
        AlphaAnimation alpha5 = new AlphaAnimation(0,1);
        alpha5.setDuration(500);
        alpha5.setStartOffset(12500);
        
        alpha5.setAnimationListener(new AnimationListener(){

			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onAnimationEnd(Animation animation) {
				fail_no.setClickable(true);
				//init_button.setEnabled(true);
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub
				
			}
        	
        });
        fail_no.setAnimation(alpha5);
        fail_no.startAnimation(alpha5);
	}
	
	
	@Override  
    protected void onStart() {  
        super.onStart();  
    }  
  
    @Override  
    protected void onStop() {  
        super.onStop();  
    }  

    @Override  
    protected void onDestroy() {  
        super.onDestroy();  
    }
    
    
    public void mYesOnClick(View v){
    	startActivity(new Intent(getApplicationContext(), MainActivity.class));
    	finish();
    }
    
    public void mNoOnClick(View v){
    	finish();
    }
}
