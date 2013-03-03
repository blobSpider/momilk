package net.rubywork.feedingclock.ui.listener;

import net.rubywork.feedingclock.R;
import net.rubywork.feedingclock.ui.anim.SelectionAnimationEndAdapter;
import net.rubywork.feedingclock.ui.anim.SelectionAnimationEndAdapter.AnimatioEndCallback;
import net.rubywork.feedingclock.ui.anim.SelectionAnimationSet;
import net.rubywork.feedingclock.ui.support.AppContext;
import net.rubywork.feedingclock.ui.support.FeedingService;
import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.AnimationSet;

public class StopButtonListener implements OnClickListener, OnTouchListener {
	private AppContext appContext;
	private View feedingMainView;
	private View feedingStatusView;
	private View pauseResumeButton;

	private FeedingService feedingService = FeedingService.getInstance();
	private AnimationSet selectionAnimation = new SelectionAnimationSet(false);

	public StopButtonListener() {
		this.appContext = AppContext.getInstance();
		Activity activity = appContext.getMainActivity();
		this.feedingMainView = activity.findViewById(R.id.feedingMainView);
		this.feedingStatusView = activity.findViewById(R.id.feedingStatusView);
		this.pauseResumeButton = activity.findViewById(R.id.pauseResumeButton);

		selectionAnimation.setAnimationListener(new SelectionAnimationEndAdapter(new AnimatioEndCallback() {
			@Override
			public void call(View view) {
				feedingMainView.setVisibility(View.VISIBLE);
				feedingStatusView.setVisibility(View.INVISIBLE);
			}
		}));
	}

	@Override
	public void onClick(View view) {
		feedingService.saveCurrentFeedingRecord();
		appContext.setPause(false);
		pauseResumeButton.setBackgroundResource(R.drawable.button_pause);
	}

	@Override
	public boolean onTouch(View view, MotionEvent event) {
		if(event.getAction() == MotionEvent.ACTION_DOWN){
			view.startAnimation(selectionAnimation);
		}
		return false;
	}
}
