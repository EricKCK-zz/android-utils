package erickck.android.utils;

public class StringUtils {
	public static final boolean isNullOrEmpty(String str) {
		return (str == null || str.length() == 0);
	}

	public static final String getValue(String str) {
		return (str == null) ? "" : str;
	}
}
