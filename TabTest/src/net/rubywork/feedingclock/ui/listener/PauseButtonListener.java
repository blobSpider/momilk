package net.rubywork.feedingclock.ui.listener;

import net.rubywork.feedingclock.R;
import net.rubywork.feedingclock.ui.anim.SelectionAnimationEndAdapter;
import net.rubywork.feedingclock.ui.anim.SelectionAnimationEndAdapter.AnimatioEndCallback;
import net.rubywork.feedingclock.ui.anim.SelectionAnimationSet;
import net.rubywork.feedingclock.ui.support.AppContext;
import net.rubywork.feedingclock.ui.support.FeedingService;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AnimationSet;

public class PauseButtonListener implements OnClickListener {
	private AppContext appContext;
	private FeedingService feedingService = FeedingService.getInstance();
	private AnimationSet selectionAnimation = new SelectionAnimationSet(false);
	private View pauseButton;

	public PauseButtonListener(final View pauseButton) {
		this.appContext = AppContext.getInstance();
		Activity activity = appContext.getMainActivity();
		this.pauseButton = activity.findViewById(R.id.pauseResumeButton);
		
		selectionAnimation.setAnimationListener(new SelectionAnimationEndAdapter(new AnimatioEndCallback() {
			@Override
			public void call(View v) {
				pauseButton.setBackgroundResource(appContext.isPause() ? R.drawable.button_resume : R.drawable.button_pause);
			}
		}));
	}

	public void onClick(View view) {
		if (view == pauseButton) {
			if (appContext.isPause()) {
				feedingService.resumeFeeding();
				appContext.setPause(false);
			} else {
				feedingService.pauseFeeding();
				appContext.setPause(true);
			}
		}else{
			if (appContext.isPause()) {
				feedingService.bottleResumeFeeding();
				appContext.setPause(false);
			} else {
				feedingService.bottlePauseFeeding();
				appContext.setPause(true);
			}
		}
		view.startAnimation(selectionAnimation);
	}
}
