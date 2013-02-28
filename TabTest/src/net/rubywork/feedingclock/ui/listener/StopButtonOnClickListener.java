package net.rubywork.feedingclock.ui.listener;

import net.rubywork.feedingclock.R;
import android.app.Activity;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;

public class StopButtonOnClickListener extends FeedingOnClickListener {
	public StopButtonOnClickListener(Activity activity) {
		super(activity);
		((Button) activity.findViewById(R.id.stopButton)).setOnClickListener(this);
	}

	public void onClick(View v) {
		this.chronometer.stop();

		long elapsedMillis = SystemClock.elapsedRealtime() - this.chronometer.getBase();
		timeRecordTextView.setText(new StringBuilder(String.valueOf(elapsedMillis / 1000 / 60)).append("분").append(String.valueOf(elapsedMillis / 1000)).append("초"));

		if (!leftTextView.getText().toString().equals("")) {
			whereBreastTextView.setText("왼쪽가슴");
		} else {
			whereBreastTextView.setText("오른쪽가슴");
		}

		feedingMainView.setVisibility(View.VISIBLE);
		feedingStatusView.setVisibility(View.INVISIBLE);

		pause = false;
	}
}
