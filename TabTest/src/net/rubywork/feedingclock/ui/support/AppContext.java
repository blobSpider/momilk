package net.rubywork.feedingclock.ui.support;

import net.rubywork.feedingclock.R;
import android.app.Activity;
import android.content.res.Resources;
import android.widget.Chronometer;

public class AppContext {
	private static final AppContext instance = new AppContext();
	private AppContext() {}
	public static AppContext getInstance() {return instance;}

	private Activity mainActivity;
	private LastFeedingRecordThread lastFeedingRecordThread;
	private String currentType;
	private Long currentSessionId;
	private boolean pause;
	private long pausedTime;
	private boolean mlMeasure;
	
	public Activity getMainActivity() {
		return mainActivity;
	}

	public void setMainActivity(Activity mainActivity) {
		this.mainActivity = mainActivity;
	}

	public LastFeedingRecordThread getLastFeedingRecordThread() {
		return lastFeedingRecordThread;
	}

	public LastFeedingRecordThread setLastFeedingRecordThread(LastFeedingRecordThread lastFeedingRecordThread) {
		this.lastFeedingRecordThread = lastFeedingRecordThread;
		return this.lastFeedingRecordThread;
	}

	public String getCurrentType() {
		return currentType;
	}

	public void setCurrentType(String currentType) {
		this.currentType = currentType;
	}

	public boolean isPause() {
		return pause;
	}

	public void setPause(boolean pause) {
		this.pause = pause;
	}

	public boolean isMlMeasure() {
		return mlMeasure;
	}

	public void setMlMeasure(boolean mlMeasure) {
		this.mlMeasure = mlMeasure;
	}

	public Long getCurrentSessionId() {
		return currentSessionId;
	}

	public void setCurrentSessionId(Long currentSessionId) {
		this.currentSessionId = currentSessionId;
	}

	public Chronometer getChronometer() {
		return (Chronometer) mainActivity.findViewById(R.id.chronometer);
	}

	public Chronometer getBottleChronometer() {
		return (Chronometer) mainActivity.findViewById(R.id.bottleChronometer);
	}

	public long getPausedTime() {
		return pausedTime;
	}

	public void setPausedTime(long pausedTime) {
		this.pausedTime = pausedTime;
	}

	public Resources getResources() {
		return mainActivity.getResources();
	}
}
