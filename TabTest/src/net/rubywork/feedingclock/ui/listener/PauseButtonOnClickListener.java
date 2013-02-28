package net.rubywork.feedingclock.ui.listener;

import net.rubywork.feedingclock.R;
import android.app.Activity;
import android.view.View;
import android.widget.Button;

public class PauseButtonOnClickListener extends FeedingOnClickListener {
	protected Button pauseResumeButton;
	
	public PauseButtonOnClickListener(Activity activity) {
		super(activity);
		(this.pauseResumeButton = (Button) activity.findViewById(R.id.pauseResumeButton)).setOnClickListener(this);
	}

	public void onClick(View v) {
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
	}
}
