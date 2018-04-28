package erickck.android.utils;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class KeyboardUtils {
	public static void hideKeyboard(final Context context, final View view, int delay) {
		if (context != null && view != null) {
			view.clearFocus();
			view.postDelayed(new Runnable() {
				@Override
				public void run() {
					hideKeyboard(context, view);
				}
			}, delay);			
		}
	}
	
	public static void hideKeyboard(Context context, View view) {
		if (context != null && view != null) {
			if (view.isFocused()) view.clearFocus();
			
			InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
		}
	}

	public static void showKeyboard(Context context, View view) {
		if (context != null && view != null) {
			InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.toggleSoftInputFromWindow(view.getWindowToken(), InputMethodManager.SHOW_FORCED, 0);
		}
	}	
}
