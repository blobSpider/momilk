package net.rubywork.feedingclock.ui.listener;

import net.rubywork.feedingclock.ui.timechecker.currentTimeChecker;
import android.content.Intent;
import android.os.SystemClock;
import android.util.Log;
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
	private TextView cuTimeTextView;
	private TextView timeRecordTextView;
	private TextView whereBreastTextView;
	
	private currentTimeChecker timeChecker;
	private String sAmPm = null;
	
	private boolean pause = false;
	
	public FeedingStatusOnClickListener(View feedingMainView, View feedingStatusView, Chronometer chronometer, TextView leftTextView, TextView rightTextView, TextView cuTimeTextView, TextView timeRecordTextView, TextView whereBreastTextView, Button...buttons){
		this.feedingMainView = feedingMainView;
		this.feedingStatusView = feedingStatusView;
		this.chronometer = chronometer;
		
		this.leftTextView = leftTextView;
		this.rightTextView = rightTextView;
		this.cuTimeTextView = cuTimeTextView;
		this.timeRecordTextView = timeRecordTextView;
		this.whereBreastTextView = whereBreastTextView;
		
		(this.pauseResumeButton = buttons[0]).setOnClickListener(this);
		(this.changeButton = buttons[1]).setOnClickListener(this);
		(this.stopButton = buttons[2]).setOnClickListener(this);
	}

	private long stoppingTime = 0l;
	
	public void onClick(View v) {
		if(v == this.pauseResumeButton){
			if(pause){
				pauseResumeButton.setText("Pause");
				this.chronometer.setBase(chronometer.getBase() + (System.currentTimeMillis()-stoppingTime));
				this.chronometer.start();
				pause = false;
			}else{
				pauseResumeButton.setText("Resume");
				this.chronometer.stop();
				stoppingTime = System.currentTimeMillis();
				pause = true;
			}
		}else if(v == this.stopButton){
			pauseResumeButton.setText("Pause");
//			this.rightTextView.setText(null);
//			this.leftTextView.setText(null);
			pause = false;			
			this.chronometer.stop();
			
			long elapsedMillis = SystemClock.elapsedRealtime() - this.chronometer.getBase();
			timeRecordTextView.setText(new StringBuilder(String.valueOf(elapsedMillis / 1000 / 60)).append("분").append(String.valueOf(elapsedMillis / 1000)).append("초"));
			
			if(!leftTextView.getText().toString().equals("")){
				whereBreastTextView.setText("왼쪽가슴");
			}else{
				whereBreastTextView.setText("오른쪽가슴");
			}
			
			feedingMainView.setVisibility(View.VISIBLE);
			feedingStatusView.setVisibility(View.INVISIBLE);
		}else if(v == this.changeButton){
			chronometer.setBase(SystemClock.elapsedRealtime());
			chronometer.start();
						
			if(!leftTextView.getText().toString().equals("")){
				this.rightTextView.setText("R");
				this.leftTextView.setText("");
			}else{
				this.leftTextView.setText("L");
				this.rightTextView.setText("");
			}
			currentTimeCheck();
		}
	}
	
	private void currentTimeCheck() {
		timeChecker = new currentTimeChecker();
		if(timeChecker.getAmPm() == 0){
			sAmPm = "AM";
		}else{
			sAmPm = "PM";
		}
		cuTimeTextView.setText(String.format("%s %d:%d 시작", sAmPm, timeChecker.getHour(), timeChecker.getMinute()));
	}
}
