package erickck.android.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastUtils {
	
	public static final void showText(Context context, String text, int duration){
		if(context != null) Toast.makeText(context, text, duration).show();
	}
	
	public static final void showText(Context context, int resId, int duration){
		if(context != null) Toast.makeText(context, resId, duration).show();
	}
	
}
