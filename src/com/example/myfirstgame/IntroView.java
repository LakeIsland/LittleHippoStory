package com.example.myfirstgame;


import android.content.Context;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class IntroView extends SurfaceView implements SurfaceHolder.Callback{
	private IntroActivity mIntroActivity;
	private MainThread mMainThread;
	private int mTimeLine;
	
	MyCrow mMyCrow;
	
	public IntroView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		getHolder().addCallback(this);
		//mMainThread = new MainThread(getHolder(),this);
		setFocusable(true);
		//mMainContext = context;
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		
	}

}
