package com.example.myfirstgame;

import java.util.ArrayList;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class MyPlayer extends Sprite {

	final static int NORMAL = 1;
	final static int INVINCIBLE = 2;
	final static int SUPERPOWER = 3;
	int mState;
	
	public double mYGround;

	private boolean mIsJumping;
	private int mJumpCount;

	private Bitmap mBitmap1;
	private Bitmap mBitmap2;
	private Bitmap mBitmap3;
	private Bitmap mBitmapHealth;

	final private int mMaxHealth = 5;
	//final private int mDamageDistance = 70;
	final static int mMaxGauge = 1000;
	
	public int mFireType;
	private int mBigFireTimeRemain;
	

	private int mHealth;
	int mGauge;
	boolean mEndedFail;

	private int mTimeLine;
	
	private Paint mPaint;
	private Paint mPaint2;

	public MyPlayer(Bitmap bitmap1, Bitmap bitmap2, Bitmap bitmap3,
			Bitmap bitmap4) {
		super(bitmap1);
		mWidth = 100;
		mHeight = 175;

		mBitmap1 = Bitmap.createScaledBitmap(bitmap1, mWidth, mHeight, false);
		mBitmap2 = Bitmap.createScaledBitmap(bitmap2, mWidth, mHeight, false);
		mBitmap3 = Bitmap.createScaledBitmap(bitmap3, mWidth, mHeight, false);

		mX = 300;
		mYGround = 800;
		mY = mYGround - mHeight / 2;

		mSpeed_X = 0;
		mSpeed_Y = 0;
		mJumpCount = 0;

		mHealth = mMaxHealth;
		mGauge = 0;

		mTimeLine = 0;
		mBigFireTimeRemain = 0;
		
		mState = NORMAL;

		mBitmapHealth = Bitmap.createScaledBitmap(bitmap4, 30, 30, false);
		mEndedFail = false;
		
		mFireType = MyFire.FIRE_TYPE_NORMAL;

		mPaint = new Paint();
		mPaint2 = new Paint();
		mPaint2.setColor(Color.YELLOW);
		mPaint2.setStrokeWidth(5);
		mPaint2.setStyle(Paint.Style.STROKE);
	}

	public void jump() {
		mIsJumping = true;
		if (mJumpCount < 2) {
			mSpeed_Y = 25;
			mJumpCount += 1;
		}
	}
	
	public void skill(){
		
		if(mGauge > 0.8* mMaxGauge && mState == NORMAL){
			mState = SUPERPOWER;
			mFireType = MyFire.FIRE_TYPE_FAST;
		}
		else if(mGauge > 0.5* mMaxGauge  && mState == NORMAL)
		mState = INVINCIBLE;
	}

	public void update() {
		mY -= (mSpeed_Y);
		if (mIsJumping)
			mSpeed_Y -= mGravity;
		if (mY > mYGround - mHeight / 2) {
			mIsJumping = false;
			mJumpCount = 0;
			mSpeed_Y = 0;
			mY = mYGround - mHeight / 2;
		} 
		
		if(!mIsJumping){
			mY = mYGround - mHeight / 2;
		}
		
		if(mBigFireTimeRemain == 1 && mState != SUPERPOWER){
			mFireType=MyFire.FIRE_TYPE_NORMAL;
			mBigFireTimeRemain = 0;
		} else if(mBigFireTimeRemain>1){
			mBigFireTimeRemain -=1;
		}
		mTimeLine += 1;
	}

	public void setGroundHeight(int height) {
		mYGround = height;
	}

	public void collideObstacle(ArrayList<MyObstacle> totalObstacle) {
		for (MyObstacle o : totalObstacle) {
			//double distance = Math.sqrt((o.mX - mX) * (o.mX - mX) + (o.mY - mY)
			//		* (o.mY - mY));
			//if (distance < mDamageDistance) {
			if(Math.abs(o.mX - mX) < 0.3*(mWidth+o.mWidth) && Math.abs(o.mY - mY) < 0.3*(mHeight+o.mHeight)){
				//totalObstacle.remove(o);
				if(o.isDamagable){
					mHealth -= 1;
					mGauge -= 100;
					if (mHealth < 1)
						mEndedFail = true;
					o.isDamagable=false;
				}
				
			}
		}
	}
	
	public void getItem(ArrayList<MyItem> totalItem) {
		for (MyItem it : totalItem) {
			//double distance = Math.sqrt((o.mX - mX) * (o.mX - mX) + (o.mY - mY)
			//		* (o.mY - mY));
			//if (distance < mDamageDistance) {
			if(Math.abs(it.mX - mX) < 0.4*(mWidth+it.mWidth) && Math.abs(it.mY - mY) < 0.4*(mHeight+it.mHeight)){
				totalItem.remove(it);
				switch(it.type){
				case 0:
					mHealth +=1;
					break;
				case 1:
					if(mState!=SUPERPOWER){
						mFireType = MyFire.FIRE_TYPE_BIG;
						mBigFireTimeRemain = 500;
					}
					break;
				case 2:
					if(mGauge<mMaxGauge - 500)
						mGauge +=500;
					else
						mGauge = mMaxGauge;
					break;
				case 3:
					mGauge = 0;
					break;
				}
			}
		}
	}

	public void addGauge() {
		if(mState == NORMAL){
			if (mGauge < mMaxGauge)
				mGauge += 1;
		}
		else {
			if(mGauge>0)
				mGauge -=2;
			else if(mGauge<=0){
				mState = NORMAL;
				mFireType = MyFire.FIRE_TYPE_NORMAL;
			}
		}
		
	}

	public void draw(Canvas canvas) {

		update();
		addGauge();

		
		if (mIsJumping)
			mBitmap = mBitmap3;
		else {
			if (mTimeLine % 20 < 10)
				mBitmap = mBitmap1;
			else
				mBitmap = mBitmap2;
		}
		canvas.drawBitmap(mBitmap, ScreenConfig.getX((int) (mX - mWidth / 2)),
				ScreenConfig.getY((int) ( mY - mHeight / 2)), null);
		
		for (int i = 0; i < mHealth; i++) {
			canvas.drawBitmap(mBitmapHealth, ScreenConfig.getX(250 + i * 40),
					ScreenConfig.getY(100), null);
		}
		
		if(mState != NORMAL)
			canvas.drawCircle(ScreenConfig.getX((int)mX), ScreenConfig.getY((int)(mY)), (float) (mHeight/1.5), mPaint2);

		mPaint.setColor(Color.BLACK);
		canvas.drawRect(ScreenConfig.getX(50), ScreenConfig.getY(200),
				ScreenConfig.getX(100), ScreenConfig.getY(700), mPaint);
		
		if(mGauge < mMaxGauge * 0.5){
			mPaint.setColor(Color.GREEN);
		} else if(mGauge >= mMaxGauge*0.5 && mGauge<mMaxGauge*0.85){
			mPaint.setColor(Color.YELLOW);
		} else {
			mPaint.setColor(Color.RED);
		}
		
		canvas.drawRect(
				ScreenConfig.getX(50),
				ScreenConfig.getY((int) (200 + 500 * (1 - (double) mGauge
						/ mMaxGauge))), ScreenConfig.getX(100),
				ScreenConfig.getY(700), mPaint);
	}
}
