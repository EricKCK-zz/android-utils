package erickck.android.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

public class UpdateUtils {

	public static void downloadNewUpdate(final Context context, final String appUpdateLink) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				update(context, appUpdateLink);
			}
		}).start();
	}

	public static void update(final Context context, String apkUrl) {
		try {
			URL url = new URL(apkUrl);
			HttpURLConnection c = (HttpURLConnection) url.openConnection();
			c.setRequestMethod("GET");
			c.setDoOutput(true);
			c.connect();

			String fileName = "YOUR_APP_NAME.apk";
			FileOutputStream fos = context.openFileOutput(fileName, Context.MODE_WORLD_READABLE | Context.MODE_WORLD_WRITEABLE);

			InputStream is = c.getInputStream();

			byte[] buffer = new byte[1024];
			int len1 = 0;
			while ((len1 = is.read(buffer)) != -1) {
				fos.write(buffer, 0, len1);
			}
			fos.close();
			is.close();
			
			Intent intent = new Intent(Intent.ACTION_VIEW);
			intent.setDataAndType(Uri.fromFile(new File(context.getFilesDir(), fileName)), "application/vnd.android.package-archive");
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(intent);

		} catch (IOException e) {
			e.printStackTrace();
			if(context != null && context instanceof Activity){
				((Activity)context).runOnUiThread(new Runnable(){

					@Override
					public void run() {
						ToastUtils.showText(context.getApplicationContext(), "Cannot Update App!", Toast.LENGTH_LONG);
					}
					
				});
			}
		}
	}
}
