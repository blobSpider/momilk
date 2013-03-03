package net.rubywork.feedingclock.ui.listener;

import android.view.View;

public class RightButtonListener extends StartableButtonListener {
	@Override
	protected String getCurrentType() {
		return "R";
	}

	@Override
	protected void setVisibilityImageView() {
		leftImageView.setVisibility(View.INVISIBLE);
		rightImageView.setVisibility(View.VISIBLE);
	}
}
