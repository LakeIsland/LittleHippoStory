package com.example.myfirstgame;


import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MainThread extends Thread{
	private SurfaceHolder mSurfaceHolder;
	private MainView mMainView;
	private boolean mRunning = false;
	
	public MainThread(SurfaceHolder surfaceHolder, MainView MainView){
		mSurfaceHolder = surfaceHolder;
		mMainView = MainView; 
	}
	
	public SurfaceHolder getSurfaceHolder(){
		return mSurfaceHolder;
	}
	
	public void setRunning(boolean Running){
		mRunning = Running;
	}
	
	public void run(){
		try{
			Canvas canvas;
			while(mRunning){
				canvas=null;
				try{
					canvas = mSurfaceHolder.lockCanvas(null);
					synchronized(mSurfaceHolder){
						try{
							mMainView.onDraw(canvas); //Why warning for onDraw(canvas)???
							Thread.sleep(2);
						}catch(Exception e){
							
						}
					}
				}finally{
					if(canvas!=null)
						mSurfaceHolder.unlockCanvasAndPost(canvas);
				}
			}
		}catch(Exception e){
			
		}
	}
}
