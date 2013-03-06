package net.rubywork.feedingclock.ui.support;

import java.util.HashMap;
import java.util.Map;

import net.rubywork.feedingclock.R;
import android.app.Activity;
import android.widget.Chronometer;

public class AppContext {
	private static final AppContext instance = new AppContext();

	private AppContext() {
	}

	public static AppContext getInstance() {
		return instance;
	}

	private Activity mainActivity;
	private LastFeedingRecordThread lastFeedingRecordThread;
	private String currentType;
	private Long currentSessionId;
	private boolean pause;
	private long pausedTime;

	private String justNowTitle;
	
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

	public Long getCurrentSessionId() {
		return currentSessionId;
	}

	public void setCurrentSessionId(Long currentSessionId) {
		this.currentSessionId = currentSessionId;
	}

	public Chronometer getChronometer() {
		return (Chronometer) mainActivity.findViewById(R.id.chronometer);
	}

	public long getPausedTime() {
		return pausedTime;
	}

	public void setPausedTime(long pausedTime) {
		this.pausedTime = pausedTime;
	}

	public String[] getAgoTimeFormatters() {
		return mainActivity.getResources().getStringArray(R.array.agoTimeFormats);
	}

	public String[] getDurationTimeFormatters() {
		return mainActivity.getResources().getStringArray(R.array.durationTimeFormats);
	}

	public String[] getGapTimeFormatters() {
		return mainActivity.getResources().getStringArray(R.array.gapTimeFormats);
	}

	public Map<String, String> getTypeTitleMap() {
		Map<String, String> typeTitleMap = new HashMap<String, String>();
		String[] typeTitles = mainActivity.getResources().getStringArray(R.array.typeTitles);
		for (String title : typeTitles) {
			String[] temp = title.split(":");
			typeTitleMap.put(temp[0], temp[1]);
		}
		
		return typeTitleMap;
	}

	public String getJustNowTitle() {
		this.justNowTitle = mainActivity.getResources().getString(R.string.str_just_now_title);
		return justNowTitle;
	}
}
