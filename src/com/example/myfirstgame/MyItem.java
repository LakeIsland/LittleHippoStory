package com.example.myfirstgame;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class MyItem extends Sprite{
	int type;
	private int mYGround;
	private int mTime;
	
	public MyItem(int type,int height, Bitmap bitmap){
		super(bitmap);
		this.type = type;
		
		switch(type){
		case 0:
			mWidth = 100;
			mHeight = 100;
			break;
		case 1:
			mWidth = 200;
			mHeight = 200;
			break;
		case 2:
			mWidth = 200;
			mHeight = 200;
			break;
		case 3:
			mWidth = 200;
			mHeight = 200;
			break;
		}
		
		mTime = 0;
		
		mBitmap = Bitmap.createScaledBitmap(bitmap, mWidth, mHeight, false);
		
		mX = ScreenConfig.screen_width;
		mYGround = height;
		mY = mYGround - mHeight/2;
		
		mSpeed_X=-10;
	}
	
	
	public void update(){
		mX+=mSpeed_X;
		if(mTime%10 == 0)
			mY+=(((mTime/10)%5-2)*5);
	}
	
	public void draw(Canvas canvas){
		mTime +=1;
		update();
		canvas.drawBitmap(mBitmap, ScreenConfig.getX((int)(mX - mWidth/2)), ScreenConfig.getY((int)(mY - mHeight/2)), null);
	}
}
