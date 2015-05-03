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
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

public class SuccessActivity extends Activity{
	ImageView success_player,success_yes,success_no;
	TextView success_message1, success_message2, success_message3;
	//private Bitmap mBitmap;
	private int mTimeCount;

	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        		WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.success);
        
        success_player = (ImageView)findViewById(R.id.success_player);
        success_yes = (ImageView)findViewById(R.id.success_yes);
        success_no = (ImageView)findViewById(R.id.success_no);
        success_message1 = (TextView)findViewById(R.id.success_message1);
        success_message2 = (TextView)findViewById(R.id.success_message2);
        success_message3 = (TextView)findViewById(R.id.success_message3);
        
        ScaleAnimation scale = new ScaleAnimation(1,2,1,2);
        scale.setFillAfter(true);
        scale.setStartOffset(2000);
        scale.setDuration(4000);
        success_player.setAnimation(scale);
        success_player.startAnimation(scale);
        
        AlphaAnimation alpha1 = new AlphaAnimation(0,1);
        alpha1.setDuration(2000);
        alpha1.setStartOffset(7500);
        success_message1.setAnimation(alpha1);
        success_message1.startAnimation(alpha1);
        
        AlphaAnimation alpha2 = new AlphaAnimation(0,1);
        alpha2.setDuration(2000);
        alpha2.setStartOffset(9500);
        success_message2.setAnimation(alpha2);
        success_message2.startAnimation(alpha2);
        
        AlphaAnimation alpha3 = new AlphaAnimation(0,1);
        alpha3.setDuration(2000);
        alpha3.setStartOffset(12000);
        success_message3.setAnimation(alpha3);
        success_message3.startAnimation(alpha3);
        
        AlphaAnimation alpha4 = new AlphaAnimation(0,1);
        alpha4.setDuration(500);
        alpha4.setStartOffset(14000);
        success_yes.setAnimation(alpha4);
        success_yes.startAnimation(alpha4);
        alpha4.setAnimationListener(new AnimationListener(){

			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onAnimationEnd(Animation animation) {
				success_yes.setClickable(true);
				//init_button.setEnabled(true);
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub
				
			}
        	
        });
        
        AlphaAnimation alpha5 = new AlphaAnimation(0,1);
        alpha5.setDuration(500);
        alpha5.setStartOffset(14500);
        success_no.setAnimation(alpha5);
        success_no.startAnimation(alpha5);
        alpha5.setAnimationListener(new AnimationListener(){

			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onAnimationEnd(Animation animation) {
				success_no.setClickable(true);
				//init_button.setEnabled(true);
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub
				
			}
        	
        });
        
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
