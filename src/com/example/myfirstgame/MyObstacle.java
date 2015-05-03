package com.example.myfirstgame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;

public class MyObstacle extends Sprite{
	
	public double mYGround;
	int type;
	public boolean isDamagable;
	
	public MyObstacle(int type,int height, double angle, Bitmap bitmap){
		super(bitmap);
		this.type = type;
		
		switch(type){
		case 0:
			mWidth = 200;
			mHeight = 100;
			break;
		case 1:
			mWidth = 150;
			mHeight = 250;
			break;
		case 2:
			mWidth = 70;
			mHeight = 70;
			break;
		case 3:
			mWidth = 200;
			mHeight = 200;
			break;
		}
		
		//mWidth = 200;
		//mHeight = 200;
		Matrix matrix = new Matrix();
		matrix.setRotate((float)angle);
		
		mBitmap = Bitmap.createScaledBitmap(bitmap, mWidth, mHeight, false);
		mBitmap = Bitmap.createBitmap(mBitmap, 0,0, mWidth, mHeight, matrix, true);
		
		
		mX = ScreenConfig.screen_width;
		mYGround = height;
		
		int offset= 0;
		if(type ==0)
			offset = 5;
		if(type ==1)
			offset = 90;
		
		mY = offset + mYGround - mHeight/2;
		
		mSpeed_X=-10;
		
		isDamagable = true;
	}
	
	public void update(){
		mX+=mSpeed_X;
	}
	
	public void draw(Canvas canvas){
		update();
		canvas.drawBitmap(mBitmap, ScreenConfig.getX((int)(mX - mWidth/2)), ScreenConfig.getY((int)(mY - mHeight/2)), null);
	}
}
