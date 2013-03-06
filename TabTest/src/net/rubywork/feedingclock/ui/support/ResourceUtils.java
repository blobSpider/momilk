package net.rubywork.feedingclock.ui.support;

import java.util.HashMap;
import java.util.Map;

import net.rubywork.feedingclock.R;
import android.content.res.Resources;

public class ResourceUtils {

	public static final Resources getResources() {
		return AppContext.getInstance().getResources();
	}

	public static final Map<String, String> getTypeTitleMap() {
		Map<String, String> typeTitleMap = new HashMap<String, String>();
		String[] typeTitles = getResources().getStringArray(R.array.typeTitles);
		for (String title : typeTitles) {
			String[] temp = title.split(":");
			typeTitleMap.put(temp[0], temp[1]);
		}
		return typeTitleMap;
	}

	public static final String[] getAgoTimeFormatters() {
		return getResources().getStringArray(R.array.agoTimeFormats);
	}

	public static final String[] getDurationTimeFormatters() {
		return getResources().getStringArray(R.array.durationTimeFormats);
	}

	public static final String[] getGapTimeFormatters() {
		return getResources().getStringArray(R.array.gapTimeFormats);
	}

	public static final String titleJustNow() {
		return getResources().getString(R.string.str_just_now_title);
	}

	public static final String titleAppQuit() {
		return getResources().getString(R.string.str_app_quit_title);
	}

	public static final String titleAppQuitConfirm() {
		return getResources().getString(R.string.str_app_quit_confirm_title);
	}

	public static final String titlePositive() {
		return getResources().getString(R.string.str_positive);
	}

	public static final String titleNegative() {
		return getResources().getString(R.string.str_negative);
	}
}
