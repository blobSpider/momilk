package net.rubywork.feedingclock.ui.listener;

import net.rubywork.feedingclock.R;
import android.app.Activity;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;

public class ChangeButtonOnClickListener extends FeedingOnClickListener {
	public ChangeButtonOnClickListener(Activity activity) {
		super(activity);
		((Button) activity.findViewById(R.id.changeButton)).setOnClickListener(this);
	}

	public void onClick(View v) {
		chronometer.setBase(SystemClock.elapsedRealtime());
		chronometer.start();
					
		if(!leftTextView.getText().toString().equals("")){
			this.rightTextView.setText("R");
			this.leftTextView.setText("");
		}else{
			this.leftTextView.setText("L");
			this.rightTextView.setText("");
		}
		
		currentTimeCheck(cuTimeTextView, "");
	}
}
