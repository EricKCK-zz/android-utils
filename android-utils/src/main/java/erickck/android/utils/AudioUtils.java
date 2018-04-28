package erickck.android.utils;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;

public class AudioUtils {

	private static MediaPlayer AUDIO_MEDIA_PLAYER;

	static void playSound(Context context, int soundResId) {
		if (context != null) {
			try {
				AUDIO_MEDIA_PLAYER = new MediaPlayer();
				AssetFileDescriptor afd = context.getResources().openRawResourceFd(soundResId);
				if (afd != null) {
					AUDIO_MEDIA_PLAYER.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
					afd.close();
				}
				if (AUDIO_MEDIA_PLAYER != null) {
					AUDIO_MEDIA_PLAYER.setOnCompletionListener(new OnCompletionListener() {

						@Override
						public void onCompletion(MediaPlayer mp) {
							try {
								AUDIO_MEDIA_PLAYER.reset();
								AUDIO_MEDIA_PLAYER.release();
							} catch (Exception e) {
							} finally {
								mp = null;
							}
						}
					});
					AUDIO_MEDIA_PLAYER.setOnErrorListener(new OnErrorListener() {

						@Override
						public boolean onError(MediaPlayer mp, int what, int extra) {
							try {
								AUDIO_MEDIA_PLAYER.reset();
								AUDIO_MEDIA_PLAYER.release();
							} catch (Exception e) {
							} finally {
								mp = null;
							}
							return true;
						}

					});
					AudioManager am = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
					am.setStreamVolume(AudioManager.STREAM_ALARM, am.getStreamMaxVolume(AudioManager.STREAM_MUSIC), 0);
					AUDIO_MEDIA_PLAYER.setAudioStreamType(AudioManager.STREAM_ALARM);
					AUDIO_MEDIA_PLAYER.prepare();
					AUDIO_MEDIA_PLAYER.start();
				}
			} catch (Exception e) {

			}
		}
	}
}
