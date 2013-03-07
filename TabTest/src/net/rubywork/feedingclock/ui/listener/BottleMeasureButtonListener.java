package net.rubywork.feedingclock.ui.listener;

import net.rubywork.feedingclock.R;
import net.rubywork.feedingclock.ui.anim.SelectionAnimationEndAdapter;
import net.rubywork.feedingclock.ui.anim.SelectionAnimationEndAdapter.AnimatioEndCallback;
import net.rubywork.feedingclock.ui.anim.SelectionAnimationSet;
import net.rubywork.feedingclock.ui.support.AppContext;
import net.rubywork.feedingclock.ui.support.FeedingService;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AnimationSet;

public class BottleMeasureButtonListener implements OnClickListener {
	private AppContext appContext;
	private FeedingService feedingService = FeedingService.getInstance();
	private AnimationSet selectionAnimation = new SelectionAnimationSet(false);

	public BottleMeasureButtonListener(View view) {
		this.appContext = AppContext.getInstance();
		selectionAnimation.setAnimationListener(new SelectionAnimationEndAdapter(view, new AnimatioEndCallback() {
			@Override
			public void call(View view) {
				view.setBackgroundResource(appContext.isMlMeasure() ? R.drawable.oz : R.drawable.ml);
			}
		}));
	}

	public void onClick(View view) {
		if (appContext.isMlMeasure()) {
//			feedingService.resumeFeeding();
			appContext.setMlMeasure(false);
		} else {
//			feedingService.pauseFeeding();
			appContext.setMlMeasure(true);
		}
		
		view.startAnimation(selectionAnimation);
	}
}
