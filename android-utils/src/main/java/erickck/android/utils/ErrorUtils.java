package erickck.android.utils;

import android.content.Context;

import erickck.android.R;


public class ErrorUtils {
	public static final int SOUND_RES_ID = R.raw.error;
	public static final int VIRBATE_DEFAULT_TIME = 1000;
	
	
	public static void playSoundAndVirbate(Context context){
		AudioUtils.playSound(context, SOUND_RES_ID);
		VibrateUtils.vibrateFor(context, VIRBATE_DEFAULT_TIME);
	}
}
