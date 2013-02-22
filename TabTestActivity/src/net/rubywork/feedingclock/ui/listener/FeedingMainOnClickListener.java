package net.rubywork.feedingclock.ui.listener;

import android.os.SystemClock;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Chronometer;

public class FeedingMainOnClickListener implements OnClickListener {
	private View feedingStatusView;
	private View feedingMainViewView;
	private Chronometer chronometer;
	private Button leftBreastButton;
	private Button bottleButton;
	private Button rightBreastButton;
	
	public FeedingMainOnClickListener(View feedingMainView, View feedingStatusView, Chronometer chronometer, Button...buttons){
		this.feedingMainViewView = feedingMainView;
		this.feedingStatusView = feedingStatusView;
		this.chronometer = chronometer;
		
		(this.leftBreastButton = buttons[0]).setOnClickListener(this);
		(this.bottleButton = buttons[1]).setOnClickListener(this);
		(this.rightBreastButton = buttons[2]).setOnClickListener(this);
	}

	public void onClick(View v) {
		feedingStatusView.setVisibility(View.VISIBLE);
		feedingMainViewView.setVisibility(View.INVISIBLE);
		chronometer.setBase(SystemClock.elapsedRealtime());
		chronometer.start();
	}
}
