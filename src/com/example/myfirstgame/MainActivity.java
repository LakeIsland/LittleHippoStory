package com.example.myfirstgame;

import android.app.Activity;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends Activity {

	MainView mainView;
	Button jumpButton, skillButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        		WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_main);
		
		int ScreenWidth = getWindowManager().getDefaultDisplay().getWidth();
        int ScreenHeight = getWindowManager().getDefaultDisplay().getHeight();
        //ScreenConfig.setScreenConfig(ScreenWidth,ScreenHeight);	
        //setContentView(mainView);
        mainView = (MainView)findViewById(R.id.main_view);
        mainView.init(ScreenWidth, ScreenHeight, this);
        jumpButton = (Button)findViewById(R.id.jump_button);
        jumpButton.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mainView.mMyPlayer.jump();
			}
        	
        });
        skillButton = (Button)findViewById(R.id.skill_button);
        skillButton.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mainView.mMyPlayer.skill();
			}
        	
        });
		//ScreenConfig.setContext((Context)this);
		
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
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
