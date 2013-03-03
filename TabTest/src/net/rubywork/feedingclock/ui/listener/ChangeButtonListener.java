package net.rubywork.feedingclock.ui.listener;

import net.rubywork.feedingclock.R;
import net.rubywork.feedingclock.ui.anim.SelectionAnimationSet;
import net.rubywork.feedingclock.ui.support.AppContext;
import net.rubywork.feedingclock.ui.support.FeedingService;
import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;

public class ChangeButtonListener implements OnTouchListener, OnClickListener {
	private AppContext appContext;
	private Activity activity;
	private View leftImageView;
	private View rightImageView;
	private View pauseResumeButton;
	
	private FeedingService feedingService = FeedingService.getInstance();
	private Animation rotateAnimation;
	private AnimationSet selectionAnimation = new SelectionAnimationSet(false);
	
	public ChangeButtonListener() {
		this.appContext = AppContext.getInstance();
		this.activity = appContext.getMainActivity();
		this.leftImageView = activity.findViewById(R.id.leftImageView);
		this.rightImageView = activity.findViewById(R.id.rightImageView);
		this.pauseResumeButton = activity.findViewById(R.id.pauseResumeButton);
		
		this.rotateAnimation = AnimationUtils.loadAnimation(activity, R.anim.rotate);
	}
	
	@Override
	public boolean onTouch(final View v, MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_UP:
			if("L".equals(appContext.getCurrentType())){
				leftImageView.setVisibility(View.INVISIBLE);
				rightImageView.setVisibility(View.VISIBLE);
				rightImageView.startAnimation(selectionAnimation);
				appContext.setCurrentType("R");
			}else{
				leftImageView.setVisibility(View.VISIBLE);
				rightImageView.setVisibility(View.INVISIBLE);
				leftImageView.startAnimation(selectionAnimation);
				appContext.setCurrentType("L");
			}
			break;

		case MotionEvent.ACTION_DOWN:
			activity.runOnUiThread(new Runnable(){
				public void run(){
					v.startAnimation(rotateAnimation);
				}
			});
			break;
		}
		
		return false;
	}

	@Override
	public void onClick(View v) {
		feedingService.saveCurrentFeedingRecord();
		feedingService.startFeeding();
		
		appContext.setPause(false);
		pauseResumeButton.setBackgroundResource(R.drawable.button_pause);
	}
}
