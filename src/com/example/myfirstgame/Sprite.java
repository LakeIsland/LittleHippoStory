package com.example.myfirstgame;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Sprite {
	
	protected Bitmap mBitmap;
	protected int mWidth;
	protected int mHeight;
	
	protected double mX;
	protected double mY;
	
	protected double mSpeed_X;
	protected double mSpeed_Y;
	
	final static double mGravity = 1;
	
	public Sprite(Bitmap bitmap){
		mBitmap = bitmap;
	}
	
	public Sprite(Bitmap bitmap, int Width, int Height, double X, double Y, double Speed_X, double Speed_Y){
		mWidth = Width;
		mHeight = Height;
		mX = X;
		mY = Y;
		mSpeed_X = Speed_X;
		mSpeed_Y = Speed_Y;
		
		mBitmap = Bitmap.createScaledBitmap(bitmap, mWidth, mHeight, false);;
	}
	
	public void update(){
		mX+=(mSpeed_X);
		mY-=(mSpeed_Y);
	}
	
	public double getX(){
		return mX;
	}
	
	public double getY(){
		return mY;
	}
	
	public void moveX(double X){
		mX = X;
	}
	
	public void moveY(double Y){
		mY = Y;
	}
	
	public void moveXY(double X, double Y){
		mX = X;
		mY = Y;
	}
	
	public void draw(Canvas canvas){
		update();
		canvas.drawBitmap(mBitmap, ScreenConfig.getX((int)(mX - mWidth/2)), ScreenConfig.getY((int)(mY - mHeight/2)), null);
	}
}
