package com.example.myfirstgame;

import java.util.ArrayList;
import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class MyCrow extends Sprite {
	private int mTimeLine = 0;
	private MyFire mClosestFire = null;
	private boolean mCanChangeMoving;

	private Bitmap mBitmap1;
	private Bitmap mBitmap2;
	private int mTimeLine2 = 0;
	
	final private int mMaxHealth = 500;
	private int mHealth;
	private int mDamageDistance = 60;
	private Paint paint;
	
	boolean mEndedSuccess;
	private int mEndCount;

	public MyCrow(Bitmap bitmap1, Bitmap bitmap2) {
		super(bitmap1);
		mWidth = 200;
		mHeight = 200;

		mBitmap1 = Bitmap.createScaledBitmap(bitmap1, mWidth, mHeight, false);
		mBitmap2 = Bitmap.createScaledBitmap(bitmap2, mWidth, mHeight, false);

		mX = 1200;
		mY = 300;

		mSpeed_X = 0;
		mSpeed_Y = 0;
		mHealth = mMaxHealth;
		paint = new Paint();
		mEndedSuccess = false;
	}

	public void update() {
		mY -= (mSpeed_Y);
		mX += (mSpeed_X);
		mTimeLine += 1;
		if (mHealth > 0) {
			if (mClosestFire != null && mCanChangeMoving) {
				Random rand = new Random();
				int sign;

				double angle = Math.atan(mClosestFire.mSpeed_Y
						/ mClosestFire.mSpeed_X);
				if (rand.nextInt(2) == 0)
					sign = 1;
				else
					sign = -1;
				mSpeed_X = sign * 20 * Math.cos(angle);
				if (rand.nextInt(2) == 0)
					sign = -1;
				else
					sign = 1;
				mSpeed_Y = sign * 20 * Math.sin(angle);
				mCanChangeMoving = false;
				mTimeLine = 0;
			} else if (mClosestFire == null) {
				mSpeed_X = 0;
				mSpeed_Y = 0;
			}
			if (mX > 1800)
				mX = 1800;
			if (mX < 600)
				mX = 600;
			if (mY > 400)
				mY = 400;
			if (mY < 200)
				mY = 200;
			mTimeLine += 10;
			if (mTimeLine > 100)
				mCanChangeMoving = true;
			mTimeLine2 += 1;
		} else if (mHealth <= 0) {
			mSpeed_X = 0;
			mSpeed_Y -= mGravity;
		}
	}

	public void getClosest(ArrayList<MyFire> totalFireSet) {
		mClosestFire = null;
		double minDistance = 500;

		for (MyFire f : totalFireSet) {
			double dx = mX - f.mX;
			if (dx > 0) {
				double dy = mY - f.mY;
				double distance = Math.sqrt(dx * dx + dy * dy);
				if (distance < minDistance) {
					mClosestFire = f;
					minDistance = distance;
				}
				if (distance < mDamageDistance) {
					
					mHealth -= f.getDamage();
					if(mHealth<0)
						mHealth = 0;
					totalFireSet.remove(f);
				}
			}
		}
	}

	public void draw(Canvas canvas) {
		if(mEndedSuccess==false){
			update();
			if (mTimeLine2 % 20 < 10)
				mBitmap = mBitmap1;
			else
				mBitmap = mBitmap2;

			paint.setColor(Color.BLACK);
			canvas.drawRect(ScreenConfig.getX((int) (mX - mWidth / 2)),
					ScreenConfig.getY((int) (mY - 30 - mHeight / 2)),
					ScreenConfig.getX((int) (mX + mWidth / 2)),
					ScreenConfig.getY((int) (mY - 10 - mHeight / 2)), paint);
			paint.setColor(Color.GREEN);
			canvas.drawRect(
					ScreenConfig.getX((int) (mX - mWidth / 2)),
					ScreenConfig.getY((int) (mY - 30 - mHeight / 2)),
					ScreenConfig.getX((int) (mX - mWidth / 2 + (mHealth /(double)mMaxHealth)
							* (mWidth))),
					ScreenConfig.getY((int) (mY - 10 - mHeight / 2)), paint);
			canvas.drawBitmap(mBitmap, ScreenConfig.getX((int) (mX - mWidth / 2)),
					ScreenConfig.getY((int) (mY - mHeight / 2)), null);
			if(mHealth <= 0)
				mEndCount+=1;
			if(mEndCount > 180)
				mEndedSuccess=true;
			
		}
		
	}
}
