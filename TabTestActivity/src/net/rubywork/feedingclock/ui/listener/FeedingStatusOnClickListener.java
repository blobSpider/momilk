package net.rubywork.feedingclock.ui.listener;

import android.os.SystemClock;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

public class FeedingStatusOnClickListener implements OnClickListener {
	private View feedingMainView;
	private View feedingStatusView;
	private Button pauseResumeButton;
	private Button changeButton;
	private Button stopButton;
	private Chronometer chronometer;
	private TextView leftTextView;
	private TextView rightTextView;
	
	private boolean pause = false;
	
	public FeedingStatusOnClickListener(View feedingMainView, View feedingStatusView, Chronometer chronometer, TextView leftTextView, TextView rightTextView, Button...buttons){
		this.feedingMainView = feedingMainView;
		this.feedingStatusView = feedingStatusView;
		this.chronometer = chronometer;
		
		this.leftTextView = leftTextView;
		this.rightTextView = rightTextView;
		
		(this.pauseResumeButton = buttons[0]).setOnClickListener(this);
		(this.changeButton = buttons[1]).setOnClickListener(this);
		(this.stopButton = buttons[2]).setOnClickListener(this);
	}

	private long stoppingTime = 0l;
	
	public void onClick(View v) {
		if(v == this.pauseResumeButton){
			if(pause){
				pauseResumeButton.setText("Pause");
				chronometer.setBase(chronometer.getBase() + (System.currentTimeMillis()-stoppingTime));
				chronometer.start();
				pause = false;
			}else{
				pauseResumeButton.setText("Resume");
				chronometer.stop();
				stoppingTime = System.currentTimeMillis();
				pause = true;
			}
		}else if(v == this.stopButton){
			pauseResumeButton.setText("Pause");
			this.rightTextView.setText(null);
			this.leftTextView.setText(null);
			pause = false;
			
			chronometer.stop();
			chronometer.setBase(SystemClock.elapsedRealtime());
			feedingMainView.setVisibility(View.VISIBLE);
			feedingStatusView.setVisibility(View.INVISIBLE);
		}
	}
}
