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

	public static final String[] TAB_LABELs = { "Recod", "History", "Music", "Settings" };
	public static final String[] TAB_IDs = { "recordTabId", "historyTabId", "musicTabId", "settingsTabId" };
	// public static final int[] TAB_IMG_ON_IDs = {
	// R.drawable.tab_button_timer_on, R.drawable.tab_button_history_on,
	// R.drawable.tab_button_music_on, R.drawable.tab_button_settings_on };
	public static final int[] TAB_IMG_OFF_IDs = { R.drawable.tab_button_timer_off, R.drawable.tab_button_history_off, R.drawable.tab_button_music_off, R.drawable.tab_button_settings_off };
	public static final int[] TAB_CONTENT_LAYOUT_IDs = { R.id.feedingContainer, R.id.historyContainer, R.id.soundContainer, R.id.moreContainer };

	private Activity mainActivity;
	private Chronometer chronometer;
	private LastFeedingRecordThread lastFeedingRecordThread;
	private String currentType;
	private Long currentSessionId;
	private boolean pause;
	private long pausedTime;

	private String[] agoTimeFormatters;
	private String[] durationTimeFormatters;
	private String[] gapTimeFormatters;
	private Map<String, String> typeTitleMap;
	private String justNowTitle;

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

	public String[] getAgoTimeFormatters() {
		if (this.agoTimeFormatters == null) {
			this.agoTimeFormatters = mainActivity.getResources().getStringArray(R.array.agoTimeFormats);
		}
		return agoTimeFormatters;
	}

	public String[] getDurationTimeFormatters() {
		if (this.durationTimeFormatters == null) {
			this.durationTimeFormatters = mainActivity.getResources().getStringArray(R.array.durationTimeFormats);
		}
		return durationTimeFormatters;
	}

	public String[] getGapTimeFormatters() {
		if (this.gapTimeFormatters == null) {
			this.gapTimeFormatters = mainActivity.getResources().getStringArray(R.array.gapTimeFormats);
		}
		return gapTimeFormatters;
	}

	private Map<String, String> getTypeTitleMap() {
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

	public String getTypeTitle(String type) {
		return this.getTypeTitleMap().get(type);
	}

	public String getJustNowTitle() {
		if (justNowTitle == null) {
			this.justNowTitle = mainActivity.getResources().getString(R.string.str_just_now_title);
		}
		return justNowTitle;
	}

}
