package com.example.myfirstgame;

import java.util.ArrayList;
import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader.TileMode;

public class MyMap {
	private int mTime;
	private Paint mPaint;
	
	
	private ArrayList<Integer> mHeightSet;

	private ArrayList<MapFragment> mMapData2;
	
	
	private int mRandomCount;
	private Random rand;

	final private int mInterval = 70;
	private int mShowCount;

	private ArrayList<MapPoint> mMapData;
	
	ArrayList<MyObstacle> mObstacleList;
	ArrayList<MyItem> mItemList;
	ArrayList<MyCloud> mCloudList;
	
	private Bitmap mGrass_down_2;
	private Bitmap mGrass_down_1;
	private Bitmap mGrass;
	private Bitmap mGrass_up_1;
	private Bitmap mGrass_up_2;
	
	private Bitmap mCloud;
	
	private Bitmap[] mObstaclebitmap = new Bitmap[4];
	private Bitmap[] mItembitmap = new Bitmap[4];
	
	private Bitmap mResized;
	private Matrix matrix1,matrix2,matrix3,matrix4;
	
	private int mLastHeight;
	
	public MyMap(Bitmap Grassbitmap, Bitmap Dirtbitmap, Bitmap Cloudbitmap, Bitmap[] Obstaclebitmap, Bitmap[] Itembitmap) {
		
		mPaint = new Paint();
		mPaint.setStrokeWidth(50);
		//mPaint.setColor(Color.rgb(112, 56, 10));
		mPaint.setStyle(Paint.Style.FILL);
		mPaint.setShader(new BitmapShader(Dirtbitmap,TileMode.REPEAT,TileMode.REPEAT));

		mTime = 0;
		
		/*
		mHeightSet = new ArrayList<Integer>();
		mHeightSet.add(0);
		mHeightSet.add(0);
		mHeightSet.add(0);
		mHeightSet.add(0);
		mHeightSet.add(0);

		/*
		mMapData = new ArrayList<MapPoint>();
		mMapData.add(new MapPoint(0, 0));
		mMapData.add(new MapPoint(500, 0));
		mMapData.add(new MapPoint(700, 50));
		mMapData.add(new MapPoint(800, 100));
		mMapData.add(new MapPoint(1200, 0));
		mMapData.add(new MapPoint(1500, 50));
		mMapData.add(new MapPoint(1800, 50));
		mMapData.add(new MapPoint(2100, -100));
		*/
		
		mMapData2 = new ArrayList<MapFragment>();
		mMapData2.add(new MapFragment(0,0));
		mMapData2.add(new MapFragment(0,0));
		mMapData2.add(new MapFragment(0,0));
		mMapData2.add(new MapFragment(0,0));
		mMapData2.add(new MapFragment(0,0));
		
		mGrass = Bitmap.createScaledBitmap(Grassbitmap, 750, 70, false);
		
		mObstaclebitmap = Obstaclebitmap;
		mItembitmap = Itembitmap;
		mCloud = Cloudbitmap;
		
		
		mRandomCount = 0;
		mShowCount = 0;
		
		mLastHeight = 0;

		rand = new Random();
		
		mObstacleList = new ArrayList<MyObstacle>();
		mItemList = new ArrayList<MyItem>();
		mCloudList = new ArrayList<MyCloud>();
		
		matrix1 = new Matrix();
		matrix2 = new Matrix();
		matrix3 = new Matrix();
		matrix4 = new Matrix();
		
		matrix1.postRotate((float) 8.13);
		mResized = Bitmap.createScaledBitmap(Grassbitmap, 758, 70, false);
		mGrass_down_2 = Bitmap.createBitmap(mResized,0,0,758,70, matrix1, true);
		
		matrix2.postRotate((float) 4.08);
		mResized = Bitmap.createScaledBitmap(Grassbitmap, 754, 70, false);
		mGrass_down_1 = Bitmap.createBitmap(mResized,0,0,754,70, matrix2, true);
		
		matrix3.postRotate((float) -4.08);
		mResized = Bitmap.createScaledBitmap(Grassbitmap, 754, 70, false);
		mGrass_up_1 = Bitmap.createBitmap(mResized,0,0,754,70, matrix3, true);
		
		matrix4.postRotate((float) -8.13);
		mResized = Bitmap.createScaledBitmap(Grassbitmap, 758, 70, false);
		mGrass_up_2 = Bitmap.createBitmap(mResized,0,0,758,70, matrix4, true);
		
		
	}

	public int getHeight(int distance) {
		MapPoint lowerBound = null;
		MapPoint upperBound = null;

		for (MapPoint mp : mMapData) {
			if (mp.x >= distance)
				lowerBound = mp;
			if (mp.x < distance)
				upperBound = mp;
		}

		if (lowerBound != null && upperBound != null)
			return (lowerBound.y * (upperBound.x - distance) + upperBound.y
					* (distance - lowerBound.x))
					/ (upperBound.x - lowerBound.x);
		else
			return 0;
	}
	
	
	public void createRandomObject() {
		if (mTime % 60 == 0) {
			Random rand = new Random();
			int i= rand.nextInt(10);
			if (i < 4) {
				MyObstacle o = new MyObstacle(i, getHeightAtX(1920), getSlopeAtX(1920), mObstaclebitmap[i]);
				mObstacleList.add(o);
			}
		}
		
		if (mTime % 120 == 30) {
			Random rand = new Random();
			int i= rand.nextInt(10);
			if (i < 4) {
				MyItem item = new MyItem(i, getHeightAtX(1920), mItembitmap[i]);
				mItemList.add(item);
			}
		}
		
		if(mTime % 150 == 0){
			Random rand = new Random();
			int i= rand.nextInt(10);
			if (i < 5) {
				MyCloud c = new MyCloud(mCloud);
				mCloudList.add(c);
			}
		}
	}

	public void createRandomMap() {
		if (mRandomCount == mInterval) {
			
			
			if(mLastHeight == -2){
				int i = rand.nextInt(3);
				mMapData2.add(new MapFragment(mLastHeight,i));
				mLastHeight +=i;
			} else if(mLastHeight == -1){
				int i = rand.nextInt(4);
				mMapData2.add(new MapFragment(mLastHeight,i-1));
				mLastHeight +=(i-1);
			} else if(mLastHeight == 0){
				int i = rand.nextInt(5);
				mMapData2.add(new MapFragment(mLastHeight,i-2));
				mLastHeight +=(i-2);
			} else if(mLastHeight == 1){
				int i = rand.nextInt(4);
				mMapData2.add(new MapFragment(mLastHeight,1-i));
				mLastHeight +=(1-i);
			} else {
				int i = rand.nextInt(3);
				mMapData2.add(new MapFragment(mLastHeight,-i));
				mLastHeight +=(-i);
			}
			
			/*
			int i = rand.nextInt(3);
			mHeightSet.add(i-1);
			mShowCount +=1;
			*/
			
			mRandomCount = 0;
		} else {
			mRandomCount += 1;
		}
	}

	/*
	 * public int getHeight(int time){ if(0<=time && time< 1000) return 800;
	 * 
	 * if(1000<=time && time< 2000) return (int)(800 + 0.1*(time-1000));
	 * 
	 * if(2000<=time && time< 3000) return (int)(900 - 0.1*(time-2000));
	 * 
	 * else return 800; }
	 */

	public int getHeightAtX(int x){
		int i = (x+10*mTime) / 700;
		int i_x = (x+10*mTime) % 700;
		double r = mMapData2.get(i).start + mMapData2.get(i).type * i_x / 700.0;
		
		return (int)(800 - 50 * r); 
	}
	
	public double getSlopeAtX(int x){
		int i = (x+10*mTime) / 700;
		return -4.05 * mMapData2.get(i).type;
	}
	
	/*
	public int getPlayerHeight() {
		
		int i = (300+10*mTime) / 700;
		int i_x = (300+10*mTime) % 700;
		double r = mMapData2.get(i).start + mMapData2.get(i).type * i_x / 700.0;
		
		return (int)(800 - 50 * r); 
		
		
		if (700 - 10 * mRandomCount >= 300)
			return (int) (800 - 100 * ((400 - 10 * mRandomCount)
					* mHeightSet.get(mShowCount) / 700.0 + (300 + 10 * mRandomCount)
					* mHeightSet.get(mShowCount + 1) / 700.0));
		else
			return (int) (800 - 100 * ((1100 - 10 * mRandomCount)
					* mHeightSet.get(mShowCount + 1) / 700.0 + (10 * mRandomCount - 400)
					* mHeightSet.get(mShowCount + 2) / 700.0));
		
		// return getHeight((mTime+30)*10);
	}*/

	public void draw(Canvas canvas){
		createRandomMap();
		createRandomObject();
		
		
		Path mPath = new Path();
		int offset = (10 * mTime) / 700;
		/*
		Path mPath1 = new Path();
		mPath1.moveTo(ScreenConfig.getX(-(10 * mTime)%700),ScreenConfig.getY(mMapData2.get(offset).getStart()));
		mPath1.lineTo(ScreenConfig.getX(700-(10 * mTime)%700),ScreenConfig.getY(mMapData2.get(offset+1).getStart()));
		mPath1.moveTo(ScreenConfig.getX(700-(10 * mTime)%700),ScreenConfig.getY(1080));
		mPath1.moveTo(ScreenConfig.getX(-(10 * mTime)%700),ScreenConfig.getY(1080));
		mPath1.moveTo(ScreenConfig.getX(-(10 * mTime)%700),ScreenConfig.getY(mMapData2.get(offset).getStart()));
		
		canvas.drawPath(mPath1,mPaint);*/
		
		mPath.moveTo(ScreenConfig.getX(-(10 * mTime)%700), ScreenConfig.getY(mMapData2.get(offset).getStart()));
		for(int i=1+(10 * mTime) / 700 ; i<=1+(10 * mTime + 1920)/700; i++){
			mPath.lineTo(ScreenConfig.getX(700*(i-offset) - (10 * mTime)%700), ScreenConfig.getY(mMapData2.get(i).getStart()));
			/*
			Path Path = new Path();
			Path.moveTo(ScreenConfig.getX(700*(i-offset) - (10 * mTime)%700), ScreenConfig.getY(mMapData2.get(i).getStart()));
			Path.lineTo(ScreenConfig.getX(700*(i+1-offset) - (10 * mTime)%700), ScreenConfig.getY(mMapData2.get(i+1).getStart()));
			Path.lineTo(ScreenConfig.getX(700*(i+1-offset) - (10 * mTime)%700), ScreenConfig.getY(1080));
			Path.lineTo(ScreenConfig.getX(700*(i-offset) - (10 * mTime)%700), ScreenConfig.getY(1080));
			Path.lineTo(ScreenConfig.getX(700*(i-offset) - (10 * mTime)%700), ScreenConfig.getY(mMapData2.get(i).getStart()));
			canvas.drawPath(Path, mPaint);
			*/
		}
		
		mPath.lineTo(ScreenConfig.getX(700*(1+(10 * mTime + 1920)/700-offset) - (10 * mTime)%700), ScreenConfig.getY(1080));
		mPath.lineTo(ScreenConfig.getX(-(10 * mTime)%700), ScreenConfig.getY(1080));
		mPath.lineTo(ScreenConfig.getX(-(10 * mTime)%700), ScreenConfig.getY(mMapData2.get(offset).getStart()));
		
		canvas.drawPath(mPath, mPaint);
		
		//Matrix skew = new Matrix();
		

		for (MyObstacle o : mObstacleList) {
			if (o.getX() < 0)
				mObstacleList.remove(o);
			else
				o.draw(canvas);
		}
		
		for (MyItem it : mItemList) {
			if (it.getX() < 0)
				mItemList.remove(it);
			else
				it.draw(canvas);
		}
		
		for (MyCloud c : mCloudList) {
			if (c.getX() < 0)
				mItemList.remove(c);
			else
				c.draw(canvas);
		}
		
		
		for(int i= (10 * mTime) / 700 ; i<=(10 * mTime + 1920)/700; i++){
		//for(int i=0; i<4; i++){
			//canvas.drawLine(ScreenConfig.getX(700*(i-offset) - (10 * mTime)%700), ScreenConfig.getY(mMapData2.get(i).getStart()), ScreenConfig.getX(700*(i+1-offset)-(10 * mTime) % 700), ScreenConfig.getY(mMapData2.get(i).getEnd()), mPaint );
			//skew.setSkew(mTime%20, mTime%20 / 2);
			switch(mMapData2.get(i).type){
			case -2:
				canvas.drawBitmap(mGrass_down_2,ScreenConfig.getX(-20+700*(i-offset) - (10 * mTime)%700) , ScreenConfig.getY(mMapData2.get(i).getStart()-30), null);
				break;
			case -1:
				canvas.drawBitmap(mGrass_down_1,ScreenConfig.getX(-20+700*(i-offset) - (10 * mTime)%700) , ScreenConfig.getY(mMapData2.get(i).getStart()-30), null);
				break;
			case 0:
				canvas.drawBitmap(mGrass,ScreenConfig.getX(-20+700*(i-offset) - (10 * mTime)%700) , ScreenConfig.getY(mMapData2.get(i).getStart()-30), null);
				break;
			case 1:
				canvas.drawBitmap(mGrass_up_1,ScreenConfig.getX(-20+700*(i-offset) - (10 * mTime)%700) , ScreenConfig.getY(mMapData2.get(i).getStart()-80), null);
				break;
			case 2:
				canvas.drawBitmap(mGrass_up_2,ScreenConfig.getX(-20+700*(i-offset) - (10 * mTime)%700) , ScreenConfig.getY(mMapData2.get(i).getStart()-130), null);
				break;
			}
			
			
			//canvas.drawBitmap(mBitmap,ScreenConfig.getX(700*(i-offset) - (10 * mTime)%700) , ScreenConfig.getY(mMapData2.get(i).getStart()), null);
			
			//int length = (int) Math.sqrt(490000+10000*Math.pow((mHeightSet.get(i+mShowCount)-mHeightSet.get(i+1+mShowCount)),2));
			//double angle = Math.atan((mHeightSet.get(i+mShowCount+1) - mHeightSet.get(i+mShowCount)) / 7.0);
			
			//matrix.postRotate((float) (angle * 180/Math.PI));
			//mResized = Bitmap.createScaledBitmap(mGrass, length, length/10, false);
			//mRotated= Bitmap.createBitmap(mResized, 0,0, length, length/10, matrix, true);
			
			//canvas.drawLine(ScreenConfig.getX(700*i-10*mRandomCount), ScreenConfig.getY(800-100*mHeightSet.get(i+mShowCount)), ScreenConfig.getX(700*(i+1)-10*mRandomCount), ScreenConfig.getY(800-100*mHeightSet.get(i+1+mShowCount)), mPaint);
			//canvas.drawBitmap(mRotated, ScreenConfig.getX(700*i-10*mRandomCount), ScreenConfig.getY(800-100*mHeightSet.get(i+mShowCount)), null);
			
		}
		
		
		
		
		
		
		/*
		for(MapPoint mp: mMapData){
			
		}
		
		for(int i=0; i<192; i++){
			canvas.drawLine(ScreenConfig.getX(i*10), ScreenConfig.getY(getHeight((i+mTime)*10)), ScreenConfig.getX((i+1)*10), ScreenConfig.getY(getHeight((i+1+mTime)*10)), mPaint);
		}*/
		
		mTime+=1;
	}
}

class MapPoint {
	int x;
	int y;

	public MapPoint(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

class MapFragment{
	int start;
	int type;

	public MapFragment(int x, int y) {
		this.start = x;
		this.type = y;
	}
	
	public int getStart(){
		return 800-50*start;
	}
	
	public int getEnd(){
		return 800-50*(start+type);
	}
}
