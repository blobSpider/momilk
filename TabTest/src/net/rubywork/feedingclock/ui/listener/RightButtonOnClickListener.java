package net.rubywork.feedingclock.ui.listener;

import net.rubywork.feedingclock.R;
import android.app.Activity;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;

public class RightButtonOnClickListener extends FeedingOnClickListener {
	public RightButtonOnClickListener(Activity activity) {
		super(activity);
		((Button) activity.findViewById(R.id.rightBreastButton)).setOnClickListener(this);
	}

	public void onClick(View v) {		
		chronometer.setBase(SystemClock.elapsedRealtime());		
		chronometer.start();

		currentTimeCheck(cuTimeMainTextView, cuTimeMainTextTitle);

		feedingStatusView.setVisibility(View.VISIBLE);
		feedingMainView.setVisibility(View.INVISIBLE);
		
		this.rightTextView.setText("R");
		this.leftTextView.setText("");
	}
}
