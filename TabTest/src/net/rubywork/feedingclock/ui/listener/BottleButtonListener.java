package net.rubywork.feedingclock.ui.listener;

import android.view.View;
import android.view.View.OnClickListener;

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
		// TODO Auto-generated method stub
		
	}
	
}
