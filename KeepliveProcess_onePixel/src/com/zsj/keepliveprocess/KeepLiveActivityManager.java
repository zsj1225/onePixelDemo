package com.zsj.keepliveprocess;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

public class KeepLiveActivityManager {
	private static KeepLiveActivityManager instance;
	private Context mContext;
	private Activity mKeepLiveActivity;

	public KeepLiveActivityManager(Context context) {
		this.mContext = context;
	}

	public static KeepLiveActivityManager getInstance(Context context) {
		if (instance == null) {
			instance = new KeepLiveActivityManager(
					context.getApplicationContext());
		}
		return instance;
	}

	public void setKeepLiveActivity(Activity keepliveActivity) {
		this.mKeepLiveActivity = keepliveActivity;
	}

	public void finishKeepLiveActivity() {
		mKeepLiveActivity.finish();
	}

	public void startKeepLiveActivity() {
		Intent intent = new Intent(mContext, KeepLiveActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		mContext.startActivity(intent);
	}
}
