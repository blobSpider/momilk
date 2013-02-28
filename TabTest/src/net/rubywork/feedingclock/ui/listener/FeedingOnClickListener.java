package net.rubywork.feedingclock.ui.listener;

import net.rubywork.feedingclock.R;
import net.rubywork.feedingclock.ui.timechecker.CurrentTimeChecker;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Chronometer;
import android.widget.TextView;

public abstract class FeedingOnClickListener implements OnClickListener {
	protected Activity activity;

	protected Chronometer chronometer;
	
	protected View feedingMainView;
	protected View feedingStatusView;
	
	protected TextView leftTextView;
	protected TextView rightTextView;
	protected TextView cuTimeTextView;
	protected TextView cuTimeMainTextView;
	protected TextView timeRecordTextView;
	protected TextView whereBreastTextView;

	protected CurrentTimeChecker timeChecker = new CurrentTimeChecker();
	
	protected String sAmPm;
	protected boolean left;
	protected boolean pause;
	protected long stoppingTime;
	
	protected String cuTimeMainTextTitle = "(마지막 수유)";

	public FeedingOnClickListener(Activity activity) {
		this.activity = activity;

		this.feedingMainView = activity.findViewById(R.id.feedingMainView);
		this.feedingStatusView = activity.findViewById(R.id.feedingStatusView);
		this.chronometer = (Chronometer) activity.findViewById(R.id.chronometer);

		this.leftTextView = (TextView) activity.findViewById(R.id.leftTextView);
		this.rightTextView = (TextView) activity.findViewById(R.id.rightTextView);
		this.cuTimeTextView = (TextView) activity.findViewById(R.id.cuTimeTextView);
		this.cuTimeMainTextView = (TextView) activity.findViewById(R.id.cuTimeMainTextView);

		this.timeRecordTextView = (TextView) activity.findViewById(R.id.timeRecordTextView);
		this.whereBreastTextView = (TextView) activity.findViewById(R.id.whereBreastTextView);
	}

	protected void currentTimeCheck(TextView view, String title) {
		view.setText(String.format(title + " %s %d:%d", timeChecker.getAmPmString(), timeChecker.getHour(), timeChecker.getMinute()));
	}
}
