package net.rubywork.feedingclock.ui.listener;

import net.rubywork.feedingclock.R;
import android.app.Activity;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;

public class LeftButtonOnClickListener extends FeedingOnClickListener {
	public LeftButtonOnClickListener(Activity activity) {
		super(activity);
		((Button) activity.findViewById(R.id.leftBreastButton)).setOnClickListener(this);
	}

	public void onClick(View v) {
		chronometer.setBase(SystemClock.elapsedRealtime());
		chronometer.start();

		currentTimeCheck(cuTimeMainTextView, cuTimeMainTextTitle);

		feedingStatusView.setVisibility(View.VISIBLE);
		feedingMainView.setVisibility(View.INVISIBLE);

		this.leftTextView.setText("L");
		this.rightTextView.setText("");
	}
}
