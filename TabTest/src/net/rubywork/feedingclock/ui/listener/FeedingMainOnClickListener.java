package net.rubywork.feedingclock.ui.listener;

import net.rubywork.feedingclock.ui.timechecker.currentTimeChecker;
import android.content.Intent;
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
	private TextView cuTimeTextView;
	
	private currentTimeChecker timeChecker;
	private String sAmPm = null;
	
	private boolean left = false;

	public FeedingMainOnClickListener(View feedingMainView, View feedingStatusView, Chronometer chronometer, TextView leftTextView, TextView rightTextView, TextView cuTimeTextView,  Button... buttons) {
		this.feedingMainViewView = feedingMainView;
		this.feedingStatusView = feedingStatusView;
		this.chronometer = chronometer;

		this.leftTextView = leftTextView;
		this.rightTextView = rightTextView;
		this.cuTimeTextView = cuTimeTextView;

		(this.leftBreastButton = buttons[0]).setOnClickListener(this);
		(this.bottleButton = buttons[1]).setOnClickListener(this);
		(this.rightBreastButton = buttons[2]).setOnClickListener(this);
	}

	public void onClick(View v) {		
		chronometer.setBase(SystemClock.elapsedRealtime());		
		chronometer.start();

		currentTimeCheck();

		if (v == this.leftBreastButton) {
			feedingStatusView.setVisibility(View.VISIBLE);
			feedingMainViewView.setVisibility(View.INVISIBLE);
			
			this.leftTextView.setText("L");
			this.rightTextView.setText("");
		} else if (v == this.rightBreastButton) {
			feedingStatusView.setVisibility(View.VISIBLE);
			feedingMainViewView.setVisibility(View.INVISIBLE);
			
			this.rightTextView.setText("R");
			this.leftTextView.setText("");
		}else if(v == this.bottleButton){
			this.rightTextView.setText("");
			this.leftTextView.setText("");
		}
	}

	private void currentTimeCheck() {
		timeChecker = new currentTimeChecker();
		if(timeChecker.getAmPm() == 0){
			sAmPm = "AM";
		}else{
			sAmPm = "PM";
		}
		cuTimeTextView.setText(String.format("(마지막 수유) %s %d:%d", sAmPm, timeChecker.getHour(), timeChecker.getMinute()));
	}

}
