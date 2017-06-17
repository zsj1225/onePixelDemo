package com.zsj.keepliveprocess;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

public class ScreenListener {

	private ScreenStateListener mScreenStateListener;
	private Context mContext;
	private ScreenBroadcastReceiver mScreenReceiver;

	public ScreenListener(Context context) {
		this.mContext = context;
		mScreenReceiver = new ScreenBroadcastReceiver();
	}

	public void begin(ScreenStateListener listener) {
		this.mScreenStateListener = listener;
		registerResceiver();
	}

	private void registerResceiver() {
		IntentFilter filter = new IntentFilter();
		filter.addAction(Intent.ACTION_SCREEN_ON);
		filter.addAction(Intent.ACTION_SCREEN_OFF);
		mContext.registerReceiver(mScreenReceiver, filter);
	}

	interface ScreenStateListener {
		void onScreenOn();

		void onScreenOff();
	}

	/**
	 * screen状态广播接收者
	 */
	private class ScreenBroadcastReceiver extends BroadcastReceiver {
		private String action = null;

		@Override
		public void onReceive(Context context, Intent intent) {
			action = intent.getAction();
			if (Intent.ACTION_SCREEN_ON.equals(action)) { // 开屏
				mScreenStateListener.onScreenOn();
			} else if (Intent.ACTION_SCREEN_OFF.equals(action)) { // 锁屏
				mScreenStateListener.onScreenOff();
			}
		}
	}
}
