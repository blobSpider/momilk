package net.rubywork.feedingclock.ui.listener;

import android.view.View;

public class LeftButtonListener extends StartableButtonListener {
	public LeftButtonListener(View view) {
		super(view);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected String getCurrentType() {
		return "L";
	}

	@Override
	protected void setVisibilityImageView() {
		leftImageView.setVisibility(View.VISIBLE);
		rightImageView.setVisibility(View.INVISIBLE);
	}
}
