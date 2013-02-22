package net.rubywork.feedingclock.ui.listener;

import android.graphics.Color;
import android.os.SystemClock;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

public class FeedingMainOnClickListener implements OnClickListener {
	private View feedingStatusView;
	private View feedingMainViewView;
	private Chronometer chronometer;
	private Button leftBreastButton;
	private Button bottleButton;
	private Button rightBreastButton;
	private TextView leftTextView;
	private TextView rightTextView;

	private boolean left = false;

	public FeedingMainOnClickListener(View feedingMainView, View feedingStatusView, Chronometer chronometer, TextView leftTextView, TextView rightTextView, Button... buttons) {
		this.feedingMainViewView = feedingMainView;
		this.feedingStatusView = feedingStatusView;
		this.chronometer = chronometer;

		this.leftTextView = leftTextView;
		this.rightTextView = rightTextView;

		(this.leftBreastButton = buttons[0]).setOnClickListener(this);
		(this.bottleButton = buttons[1]).setOnClickListener(this);
		(this.rightBreastButton = buttons[2]).setOnClickListener(this);
	}

	public void onClick(View v) {
		feedingStatusView.setVisibility(View.VISIBLE);
		feedingMainViewView.setVisibility(View.INVISIBLE);
		chronometer.setBase(SystemClock.elapsedRealtime());
		chronometer.start();

		if (v == this.leftBreastButton) {
			this.leftTextView.setText("L");			
			this.leftTextView.setTextColor(Color.BLACK);
			this.leftTextView.setTextSize(50);

		} else if (v == this.rightBreastButton) {
			this.rightTextView.setText("R");
			this.rightTextView.setTextColor(Color.BLACK);
			this.rightTextView.setTextSize(50);
		}
	}
}
