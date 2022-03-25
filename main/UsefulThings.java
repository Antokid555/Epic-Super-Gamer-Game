package com.epicGamer.main;

public class UsefulThings {
	public static int clamp(int var, int max, int min) {
		if(var > max) return max;
		if(var < min) return min;
		return var;
	}
	
	public static float clamp(float var, float max, float min) {
		if(var > max) return max;
		if(var < min) return min;
		return var;
	}
}
