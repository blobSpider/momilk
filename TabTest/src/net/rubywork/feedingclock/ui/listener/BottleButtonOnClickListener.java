package net.rubywork.feedingclock.ui.listener;

import net.rubywork.feedingclock.R;
import android.app.Activity;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;

public class BottleButtonOnClickListener extends FeedingOnClickListener {
	public BottleButtonOnClickListener(Activity activity) {
		super(activity);
		((Button) activity.findViewById(R.id.bottleButton)).setOnClickListener(this);
	}

	public void onClick(View v) {
		chronometer.setBase(SystemClock.elapsedRealtime());
		chronometer.start();

		currentTimeCheck(cuTimeMainTextView, cuTimeMainTextTitle);

		this.rightTextView.setText("");
		this.leftTextView.setText("");
	}
}
