package net.rubywork.feedingclock.ui.listener;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;

public class TabChangListener implements OnTabChangeListener {
	private static final long DURATION_TRANSLATE = 300;
	
	private TabHost tabHost;
	private View previousView;
	
	public TabChangListener(TabHost tabHost){
		this.tabHost = tabHost;
	}
	
	@Override
	public void onTabChanged(String tabId) {
		if(previousView != null){
			previousView.setAnimation(getOutAnimation());
		}
		
		View currentView = tabHost.getCurrentView();
		currentView.setAnimation(getInAnimation());
		previousView = currentView;
	}


	private Animation getInAnimation() {
		Animation animation = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, +1.0f, Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0.0f);
		animation.setDuration(DURATION_TRANSLATE);
		return animation;
	}

	private Animation getOutAnimation() {
		Animation animation = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, -1.0f, Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0.0f);
		animation.setDuration(DURATION_TRANSLATE);
		return animation;
	}
}
