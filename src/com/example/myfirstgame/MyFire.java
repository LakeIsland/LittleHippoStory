package com.example.myfirstgame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;

public class MyFire extends Sprite{
	
	private double mSpeed;
	private float mAngle;
	private int mDamage;
	private Matrix mMatrix;
	
	public static int FIRE_TYPE_NORMAL = 1;
	public static int FIRE_TYPE_FAST = 2;
	public static int FIRE_TYPE_BIG = 3;
	
	public MyFire(int type, double X, double Y, double angle, Bitmap bitmap){
		super(bitmap);
		mX = X;
		mY = Y;
		mWidth = 40;
		mHeight = 20;
		mSpeed = 30;
		mDamage = 10;
		
		if(type==FIRE_TYPE_FAST){
			mWidth*=2;
			mHeight*=2;
			mSpeed = 100;
			mDamage = 20;
		}
		
		if(type==FIRE_TYPE_BIG){
			mWidth*=2;
			mHeight*=2;
			mSpeed = 30;
			mDamage = 10;
		}
		
		
		mSpeed_X = mSpeed*Math.cos(angle);
		mSpeed_Y = mSpeed*Math.sin(angle);
		
		mBitmap= Bitmap.createScaledBitmap(bitmap, mWidth, mHeight, false);
		
	}
	
	public int getDamage(){
		return mDamage;
	}

	public void destory(){ 
		try{
			if(mBitmap != null){ 
				mBitmap.recycle();
			}
		}
		catch(Exception e){}
	}	
	
	public void move(double x, double y){
		mX = x;
		mY = y;
	}
	
	public void update(){
		mY-=(mSpeed_Y);
		mX+=(mSpeed_X);
		mAngle = (float) (-Math.atan(mSpeed_Y/mSpeed_X) * (180/Math.PI));
		mSpeed_Y-=mGravity*0.7;
	}

	
	public void draw(Canvas canvas){
		Matrix matrix = new Matrix();
		matrix.postRotate(mAngle);
		update();
		Bitmap rotated = Bitmap.createBitmap(mBitmap, 0, 0, mWidth, mHeight, matrix, true);
		canvas.drawBitmap(rotated, ScreenConfig.getX((int)(mX - mWidth/2)), ScreenConfig.getY((int)(mY - mHeight/2)), null);
	}
}
