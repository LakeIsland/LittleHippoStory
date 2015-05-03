package com.example.myfirstgame;

import java.io.BufferedInputStream;
import java.util.ArrayList;
import java.util.Random;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MainView extends SurfaceView implements SurfaceHolder.Callback {
	private MainActivity mMainActivity;
	private MainThread mMainThread;
	Context mMainContext;

	private final static int GAMEMODE_GAME = 1;
	private final static int GAMEMODE_SUCCESS = 2;
	private final static int GAMEMODE_FAIL = 3;
	private final static int GAMEMODE_IDLE = 4;
	private int mGameMode;
	private int mSuccessCount = 0;
	private int mFailCount = 0;

	MyPlayer mMyPlayer;
	MyCrow mMyCrow;
	MyMap mMyMap;
	// Button jump = (Button)findViewById(R.id.jump_button);
	
	Bitmap [] bitmap_obstacle;
	Bitmap [] bitmap_item;
	
	Bitmap bitmap_fire;
	Bitmap bitmap_health;
	
	Bitmap bitmap_grass;
	Bitmap bitmap_dirt;
	Bitmap bitmap_cloud;
	
	ArrayList<MyFire> mFireList;
	ArrayList<MyObstacle> mObstacleList;
	ArrayList<MyCloud> mCloudList;

	public MainView(Context context, AttributeSet att) {
		super(context, att);
		getHolder().addCallback(this);
		mMainThread = new MainThread(getHolder(), this);
		setFocusable(true);
		mMainContext = context;
		
		bitmap_obstacle = new Bitmap[4];
		bitmap_obstacle[0] = BitmapFactory.decodeResource(getResources(), R.drawable.ob0);
		bitmap_obstacle[1] = BitmapFactory.decodeResource(getResources(), R.drawable.ob1);
		bitmap_obstacle[2] = BitmapFactory.decodeResource(getResources(), R.drawable.ob2);
		bitmap_obstacle[3] = BitmapFactory.decodeResource(getResources(), R.drawable.ob3);
		
		bitmap_item = new Bitmap[4];
		bitmap_item[0] = BitmapFactory.decodeResource(getResources(), R.drawable.item_0);
		bitmap_item[1] = BitmapFactory.decodeResource(getResources(), R.drawable.item_1);
		bitmap_item[2] = BitmapFactory.decodeResource(getResources(), R.drawable.item_2);
		bitmap_item[3] = BitmapFactory.decodeResource(getResources(), R.drawable.item_3);
		
		bitmap_fire = BitmapFactory.decodeResource(getResources(),
				R.drawable.fire_1);
		bitmap_health = BitmapFactory.decodeResource(getResources(),
				R.drawable.player_heart);
		bitmap_grass = BitmapFactory.decodeResource(getResources(),
				R.drawable.map_grass);
		bitmap_dirt = BitmapFactory.decodeResource(getResources(),
				R.drawable.map_dirt);
		bitmap_cloud = BitmapFactory.decodeResource(getResources(),
				R.drawable.map_cloud);
	}

	public void init(int width, int height, MainActivity MainActivity) {
		mMainActivity = MainActivity;
		mMyPlayer = new MyPlayer(loadBitmap("player_pose_1.png"),
				loadBitmap("player_pose_2.png"),
				loadBitmap("player_pose_3.png"), bitmap_health);
		mMyCrow = new MyCrow(loadBitmap("crow_pose_1.png"),
				loadBitmap("crow_pose_2.png"));
		
		mFireList = new ArrayList<MyFire>();
		mObstacleList = new ArrayList<MyObstacle>();
		mCloudList = new ArrayList<MyCloud>();
		
		mMyMap = new MyMap(bitmap_grass, bitmap_dirt,bitmap_cloud, bitmap_obstacle, bitmap_item);

		mGameMode = GAMEMODE_GAME;
	}

	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			// double dx = event.getX() - mMyPlayer.getX();
			// double dy = mMyPlayer.getY() - event.getY();
			double angle = Math.PI / 2
					* (1 - event.getY() / ScreenConfig.screen_height);
			// Random rand = new Random();
			MyFire f = new MyFire(mMyPlayer.mFireType, mMyPlayer.getX(), mMyPlayer.getY(), angle,
					bitmap_fire);
			mFireList.add(f);
		}
		return true;
	}


	public void onDraw(Canvas canvas) {
		switch (mGameMode) {
		case GAMEMODE_GAME:
			if(mSuccessCount<1 && mFailCount<1)
				onDrawGame(canvas);
			break;
		case GAMEMODE_SUCCESS:
				try{
					mSuccessCount+=1;
					onDrawSuccess();
				} catch(Exception e){}
			
			break;
		case GAMEMODE_FAIL:
				try{
					onDrawFail();
				} catch(Exception e){}
			
			break;
			
		case GAMEMODE_IDLE:
			break;
		}
	}
	
	synchronized public void onDrawSuccess(){
		mGameMode= GAMEMODE_IDLE;
		mMainThread.interrupt();
		mMainActivity.startActivity(new Intent(mMainActivity
				.getApplicationContext(), SuccessActivity.class));
		mMainActivity.finish();
	}
	
	synchronized public void onDrawFail(){
		mGameMode = GAMEMODE_IDLE;
		mMainThread.interrupt();
		mMainActivity.startActivity(new Intent(mMainActivity
				.getApplicationContext(), FailActivity.class));
		mMainActivity.finish();
	}

	public void onDrawGame(Canvas canvas) {
		if(mMyPlayer.mState == MyPlayer.SUPERPOWER)
			canvas.drawColor(Color.argb(50, 0, 0, 40));
		else if(mMyPlayer.mState == MyPlayer.INVINCIBLE)
			canvas.drawColor(Color.rgb(0, 0, 40));
		else 
			canvas.drawColor(Color.WHITE);
		
		
		mMyPlayer.setGroundHeight(mMyMap.getHeightAtX(300));
		
		mMyPlayer.getItem(mMyMap.mItemList);
		
		if(mMyPlayer.mState == MyPlayer.NORMAL)
			mMyPlayer.collideObstacle(mMyMap.mObstacleList);
		
		mMyCrow.draw(canvas);
		
		for(MyCloud c: mMyMap.mCloudList){
			c.collideFire(mFireList);
		}
		
		mMyMap.draw(canvas);
		
		
		
		mMyPlayer.draw(canvas);
		
		
		for (MyFire f : mFireList) {
			if (f.getX() > ScreenConfig.screen_width
					|| f.getY() > ScreenConfig.screen_height)
				mFireList.remove(f);
			else
				f.draw(canvas);
		}
		
		/*
		if(mMyPlayer.mGauge> MyPlayer.mMaxGauge * 0.5){
			mMainActivity.skillButton.setEnabled(true);
			mMainActivity.skillButton.setClickable(true);
		}*/

		mMyCrow.getClosest(mFireList);
		

		if(mMyCrow.mEndedSuccess)
			mGameMode = GAMEMODE_SUCCESS;
		if(mMyPlayer.mEndedFail)
			mGameMode = GAMEMODE_FAIL;
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		mMainThread.setRunning(true);
		try {
			if (mMainThread.getState() == Thread.State.TERMINATED) {
				mMainThread = new MainThread(getHolder(), this);
				mMainThread.setRunning(true);
				setFocusable(true);
				mMainThread.start();
			} else {
				mMainThread.start();
			}
		} catch (Exception ex) {
			Log.i("MainView", "ex:" + ex.toString());
		}
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		boolean retry = true;
		mMainThread.setRunning(false);
		while (retry) {
			try {
				mMainThread.join();
				retry = false;
			} catch (Exception e) {

			}
		}
	}

	public Bitmap loadBitmap(String filename) {
		Bitmap bm = null;
		try {
			AssetManager am = mMainContext.getAssets();
			BufferedInputStream buf = new BufferedInputStream(am.open(filename));
			bm = BitmapFactory.decodeStream(buf);
		} catch (Exception ex) {
		}
		return bm;
	}

}
