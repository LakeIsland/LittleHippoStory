package com.example.myfirstgame;

import android.content.Context;
import android.util.TypedValue;

public class ScreenConfig {
	
	public static  int screen_width ;
	public static  int screen_height ;

	final private static  int virtual_width = 1920;
	final private static  int virtual_height = 1080;

	ScreenConfig mScreenConfig;
	static double pixelperdip;
	
	public static void setScreenConfig(int ScreenWidth , int ScreenHeight)
	{
		screen_width = ScreenWidth;
		screen_height = ScreenHeight;
	}
	
	
	/*
	public ScreenConfig(double pixelperdip)
	{
		screen_width = ScreenWidth;
		screen_height = ScreenHeight;
		
	}
	
	
	
	public void setSize(int width, int height)
	{
		virtual_width = width;
		virtual_height = height;
	}*/
	
	public static int getX(int x)
	{
		return (int)(  x * screen_width/virtual_width);
	}	
	public static int getY(int y)
	{
		return (int)(  y * screen_height/virtual_height);
	}	
}
