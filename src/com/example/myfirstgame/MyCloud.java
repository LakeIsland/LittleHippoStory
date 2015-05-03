package com.example.myfirstgame;

import java.util.ArrayList;
import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class MyCloud extends Sprite{
	private Random rand;
	private int mTime;
	
	public MyCloud(Bitmap bitmap) {
		super(bitmap);
		rand = new Random();
		
		mWidth = 140 + 70 * rand.nextInt(3);
		mHeight = mWidth * 3/7;
		
		mY = 100 + 50 * rand.nextInt(3);
		mX = 1920;
		
		mSpeed_X = -(5+5*rand.nextInt(3));
		
		mTime = 0;
		
		mBitmap = Bitmap.createScaledBitmap(bitmap, mWidth, mHeight, false);
		// TODO Auto-generated constructor stub
	}
	
	public void update(){
		mX+=mSpeed_X;
		if(mTime%20 == 0)
			mY+=(((mTime/20)%5-2)*5);
	}
	
	public void draw(Canvas canvas){
		mTime +=1;
		update();
		canvas.drawBitmap(mBitmap, ScreenConfig.getX((int)(mX - mWidth/2)), ScreenConfig.getY((int)(mY - mHeight/2)), null);
	}
	
	public void collideFire(ArrayList<MyFire> totalFireSet) {
		
		for (MyFire f : totalFireSet) {
			if(Math.abs(f.mX - mX) < 0.4*(mWidth+f.mWidth) && Math.abs(f.mY - mY) < 0.4*(mHeight+f.mHeight)){
				totalFireSet.remove(f);
			}
		}
	}
	
}
