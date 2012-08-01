package com.cesar.libs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import android.content.Context;
import android.os.Environment;
import android.os.Vibrator;
import android.util.Log;
import android.widget.Toast;

public abstract class QuickUtils {

	private static String TAG = "DESIRED_TAG";

	public static final boolean DEVELOPER_MODE = true;
	public static final boolean PRODUCTION_MODE = false;

	private static boolean DEBUG_MODE = QuickUtils.DEVELOPER_MODE;

	/**
	 * Set debug mode (default: QuickUtils.DEBUG_MODE). Set
	 * QuickUtils.PRODUCTION_MODE when you go to production
	 * 
	 * @param debugMode
	 *            the new developer mode value
	 */
	public static void setDebugMode(boolean debugMode) {
		DEBUG_MODE = debugMode;
	}

	/**
	 * Set the log TAG for debug purposes
	 * 
	 * @param TAG
	 *            Desired tag (e.g.: Aplication_X)
	 */
	public static void setTAG(String tag) {
		TAG = tag;
	}

	/**
	 * Log Utils
	 * 
	 * @author cesar
	 * 
	 */
	public static class log {

		/**
		 * Send an ERROR log message
		 * 
		 * @param message
		 *            The message you would like logged.
		 */
		public static void e(String message) {
			if (DEBUG_MODE) {
				Log.e(TAG, message);
			}
		}

		/**
		 * Send an INFO log message.
		 * 
		 * @param message
		 *            The message you would like logged.
		 */
		public static void i(String message) {
			if (DEBUG_MODE) {
				Log.i(TAG, message);
			}
		}

		/**
		 * Send a VERBBOSE log message.
		 * 
		 * @param message
		 *            The message you would like logged.
		 */
		public static void v(String message) {
			if (DEBUG_MODE) {
				Log.v(TAG, message);
			}
		}

		/**
		 * Send a WARNING log message.
		 * 
		 * @param message
		 *            The message you would like logged.
		 */
		public static void w(String message) {
			if (DEBUG_MODE) {
				Log.w(TAG, message);
			}
		}

		/**
		 * Send a DEBUG log message.
		 * 
		 * @param message
		 *            The message you would like logged.
		 */
		public static void d(String message) {
			if (DEBUG_MODE) {
				Log.d(TAG, message);
			}
		}

		/**
		 * Send a DEBUG log message and log the exception.
		 * 
		 * @param message
		 *            The message you would like logged.
		 * @param trowable
		 *            An exception to log
		 */
		public static void d(String message, Throwable trowable) {
			if (DEBUG_MODE) {
				Log.d(TAG, message, trowable);
			}
		}
	}

	/**
	 * Miscelaneous android App Utils
	 * 
	 * @author cesar
	 * 
	 */
	public static class misc {

		/**
		 * Make the smartphone vibrate for a giving time.you need to put the
		 * vibration permission in the manifest as follows: <uses-permission
		 * android:name="android.permission.VIBRATE"/>
		 * 
		 * 
		 * @param context
		 *            context in which the smartphone will vibrate
		 * @param duration
		 *            duration of the vibration in miliseconds
		 * 
		 */
		public void vibrate(Context context, int duration) {
			Vibrator v = (Vibrator) context
					.getSystemService(Context.VIBRATOR_SERVICE);
			v.vibrate(duration);
		}

		/**
		 * Quick toast method with short duration
		 * 
		 * @param context
		 *            context in which will be displayed
		 * @param message
		 *            toast content
		 */
		public static void toast(Context context, String message) {

			Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
			toast.show();
		}

		/**
		 * Quick toast method
		 * 
		 * @param context
		 *            context in which will be displayed
		 * @param message
		 *            The text to show. Can be formatted text.
		 * @param duration
		 *            How long to display the message. Either Toast.LENGTH_SHORT
		 *            or Toast.LENGTH_LONG
		 */
		public static void toast(Context context, String message, int duration) {

			Toast toast = Toast.makeText(context, message, duration);
			toast.show();
		}

		/**
		 * Sleep
		 * 
		 * @param seconds
		 *            seconds that the app will sleep
		 */
		public static void sleep(int seconds) {

			try {
				QuickUtils.log.i("delaying for " + seconds + " seconds");
				Thread.sleep(seconds * 1000);
			} catch (InterruptedException e) {
				QuickUtils.log.e(e.getLocalizedMessage().toString());
			}
		}

		/**
		 * get Current time in miliseconds
		 * 
		 * @return current time in miliseconds
		 */
		public static long getCurrentTimeInMiliseconds() {
			return TimeUnit.MILLISECONDS.toMillis(Calendar.getInstance()
					.getTimeInMillis());
		}

		/**
		 * get Current time in seconds
		 * 
		 * @return current time in seconds
		 */
		public static long getCurrentTimeInSeconds() {
			Calendar c = Calendar.getInstance();
			return c.get(Calendar.SECOND);
		}

		/**
		 * Returns a random number between MIN inclusive and MAX exclusive.
		 * 
		 * @param min
		 *            value inclusive
		 * @param max
		 *            value exclusive
		 * @return an int between MIN inclusive and MAX exclusive.
		 */
		public static int getRandomNumber(int min, int max) {
			Random r = new Random();
			return r.nextInt(max - min + 1) + min;
		}
	}

	/**
	 * SDCard Utils
	 * 
	 * @author cesar
	 * 
	 */
	public static class sdcard {
		/**
		 * Check if the SD Card is Available
		 * 
		 * @return true if the sd card is available and false if it is not
		 */
		public static boolean isSDCardAvailable() {
			boolean mExternalStorageAvailable = false;

			String state = Environment.getExternalStorageState();

			if (Environment.MEDIA_MOUNTED.equals(state)) {
				// We can read and write the media
				mExternalStorageAvailable = true;
			} else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
				// We can only read the media
				mExternalStorageAvailable = true;

			} else {
				// Something else is wrong. It may be one of many other states,
				// but all we need
				// to know is we can neither read nor write
				mExternalStorageAvailable = false;
			}

			return mExternalStorageAvailable;

		}

		/**
		 * Check if the SD Card is writable
		 * 
		 * @return true if the sd card is writable and false if it is not
		 */
		public static boolean isSDCardWritable() {

			boolean mExternalStorageWriteable = false;

			String state = Environment.getExternalStorageState();

			if (Environment.MEDIA_MOUNTED.equals(state)) {
				// We can read and write the media
				mExternalStorageWriteable = true;
			} else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
				// We can only read the media

				mExternalStorageWriteable = false;
			} else {
				// Something else is wrong. It may be one of many other states,
				// but all we need
				// to know is we can neither read nor write
				mExternalStorageWriteable = false;
			}

			return mExternalStorageWriteable;

		}

		/**
		 * Read file from SDCard
		 */
		public static void readFileFromSDCard() {
			File directory = Environment.getExternalStorageDirectory();
			// Assumes that a file article.rss is available on the SD card
			File file = new File(directory + "/article.rss");
			if (!file.exists()) {
				throw new RuntimeException("File not found");
			}
			Log.e("Testing", "Starting to read");
			BufferedReader reader = null;
			try {
				reader = new BufferedReader(new FileReader(file));
				StringBuilder builder = new StringBuilder();
				String line;
				while ((line = reader.readLine()) != null) {
					builder.append(line);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (reader != null) {
					try {
						reader.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

}