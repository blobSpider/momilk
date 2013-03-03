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
	private Chronometer chronometer;
	private LastFeedingRecordThread lastFeedingRecordThread;
	private String currentType;
	private Long currentSessionId;
	private boolean pause;
	private long pausedTime;

	private String[] agoTimeFormats;
	private String[] durationTimeFormats;
	private Map<String, String> typeTitleMap;
	private String onlyJustTitle;

	public Activity getMainActivity() {
		return mainActivity;
	}

	public void setMainActivity(Activity mainActivity) {
		this.chronometer = (Chronometer) mainActivity.findViewById(R.id.chronometer);
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
		return chronometer;
	}

	public void setChronometer(Chronometer chronometer) {
		this.chronometer = chronometer;
	}

	public long getPausedTime() {
		return pausedTime;
	}

	public void setPausedTime(long pausedTime) {
		this.pausedTime = pausedTime;
	}

	public String[] getAgoTimeFormats() {
		if (this.agoTimeFormats == null) {
			this.agoTimeFormats = mainActivity.getResources().getStringArray(R.array.agoTimeFormats);
		}
		return agoTimeFormats;
	}

	public String[] getDurationTimeFormats() {
		if (this.durationTimeFormats == null) {
			this.durationTimeFormats = mainActivity.getResources().getStringArray(R.array.durationTimeFormats);
		}
		return durationTimeFormats;
	}

	public Map<String, String> getTypeTitleMap() {
		if (this.typeTitleMap == null) {
			this.typeTitleMap = new HashMap<String, String>();

			String[] typeTitles = mainActivity.getResources().getStringArray(R.array.typeTitles);
			for (String title : typeTitles) {
				String[] temp = title.split(":");
				this.typeTitleMap.put(temp[0], temp[1]);
			}
		}
		return this.typeTitleMap;
	}

	public String getOnlyJustTitle() {
		if(onlyJustTitle == null){
			this.onlyJustTitle = mainActivity.getResources().getString(R.string.str_only_just_title);
		}
		return onlyJustTitle;
	}
	
}
