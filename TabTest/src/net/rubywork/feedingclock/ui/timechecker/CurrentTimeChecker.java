package net.rubywork.feedingclock.ui.timechecker;

import java.util.Calendar;

public class CurrentTimeChecker {
	private Calendar calendar;

	public int getYear(){
		calendar = Calendar.getInstance();
		return calendar.get(Calendar.YEAR);
	}
	
	public int getMonth(){
		calendar = Calendar.getInstance();
		return calendar.get(Calendar.MONTH);
	}
	
	public int getDay(){
		calendar = Calendar.getInstance();
		return calendar.get(Calendar.DAY_OF_MONTH);
	}
	
	public int getHour() {
		calendar = Calendar.getInstance();
		return calendar.get(Calendar.HOUR_OF_DAY);
	}
	
	public int getMinute(){
		calendar = Calendar.getInstance();
		return calendar.get(Calendar.MINUTE);
	}
	
	public int getAmPm(){
		calendar = Calendar.getInstance();
		return calendar.get(Calendar.AM_PM);
	}
	
	public String getAmPmString(){
		return this.getAmPm() == 0 ? "AM" : "PM";
	}
}
