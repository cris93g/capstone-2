package gameHelpers;

import org.lwjgl.Sys;

public class TimeHelper {
	private static boolean paused=false;
	public static long lastFrame,totalTime;
	public static float  d=0,multiplier=1;
	
	public static long getTime() {
		return Sys.getTime()* 1000/ Sys.getTimerResolution();
	}
	//checks state of now vs last last state
	public static float getDelta() {
		long currentTime=getTime();
		int delta=(int)(currentTime-lastFrame);
		lastFrame=getTime();
		if(delta * 0.001f>0.05f) {
			return  0.05f;
		}
		return delta * 0.001f;
	}
	//if we want ti pause
	public static float Delta() {
		if(paused) {
			return 0;
		}else {
			return d * multiplier;
		}
	}
	public static float totalTime() {
		return totalTime;
	}
	
	public static float Multipliet() {
		return multiplier;
	}
	//keeps adding delta to get total time
	public static void update() {
		d= getDelta();
		totalTime+=d;
	}
	
	
	public static void pause() {
		if(paused) {
			paused=false;
		}else {
			paused=true;
		}
	}

}
