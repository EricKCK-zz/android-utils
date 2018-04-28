package erickck.android.utils;

import android.content.Context;
import android.os.Vibrator;

public class VibrateUtils {

	static final void vibrateFor(Context context, int millis) {
		if (context != null) {
			Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
			v.vibrate(millis);
		}
	}
}
