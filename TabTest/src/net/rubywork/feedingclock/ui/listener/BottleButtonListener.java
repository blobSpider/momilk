package net.rubywork.feedingclock.ui.listener;

import net.rubywork.feedingclock.R;
import android.view.View;

public class BottleButtonListener extends StartableButtonListener {

	public BottleButtonListener(View view) {
		super(view);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected String getCurrentType() {
		return "B";
	}

	@Override
	protected void setVisibilityImageView() {
		appContext.setMlMeasure(true);
		milkAmountTextView.setText("70");
		
		bottleMeasureButton.setBackgroundResource(appContext.isMlMeasure() ? R.drawable.ml : R.drawable.oz);
	}
	
}
