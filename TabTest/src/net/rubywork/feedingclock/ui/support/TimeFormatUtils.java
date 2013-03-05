package net.rubywork.feedingclock.ui.support;

import android.text.format.DateUtils;

public class TimeFormatUtils {

	public static final String formatGapTime(long millis) {
		String[] formatters = AppContext.getInstance().getGapTimeFormatters();
		Object[] timeElements = DateUtils.formatElapsedTime(millis / 1000L).split(":");
		
		int length = timeElements.length;
		String str = String.format(formatters[length - 1], timeElements[0], timeElements[1]);
		if (length == 2 && "00".equals(timeElements[0])) {
			str = String.format(formatters[0], timeElements[1]);
		}
		
		return str;
	}

	public static final String formatDurationTime(long millis) {
		String[] formatters = AppContext.getInstance().getDurationTimeFormatters();
		Object[] timeElements = DateUtils.formatElapsedTime(millis / 1000L).split(":");

		int length = timeElements.length;
		String str = String.format(formatters[length - 1], timeElements[0], timeElements[1]);
		if (length == 2 && "00".equals(timeElements[0])) {
			str = String.format(formatters[0], timeElements[1]);
		}
		
		return str;
	}

	public static final String formatAgoTime(long millis) {
		String[] formatters = AppContext.getInstance().getAgoTimeFormatters();
		Object[] timeElements = DateUtils.formatElapsedTime(millis / 1000L).split(":");

		int length = timeElements.length;
		String str = String.format(formatters[2], timeElements[0], timeElements[1]);
		
		if (length == 2) {
			if ("00".equals(timeElements[0])) {
				str = String.format(formatters[0], timeElements[1]);
			} else {
				str = String.format(formatters[1], timeElements[0]);
			}
		}
		
		return str;
	}
}
