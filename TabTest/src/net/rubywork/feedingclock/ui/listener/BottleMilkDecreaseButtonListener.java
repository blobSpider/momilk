package net.rubywork.feedingclock.ui.listener;

import net.rubywork.feedingclock.ui.anim.SelectionAnimationSet;
import net.rubywork.feedingclock.ui.support.AppContext;
import net.rubywork.feedingclock.ui.support.FeedingService;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AnimationSet;

public class BottleMilkDecreaseButtonListener implements OnClickListener {

	private AppContext appContext;
	private FeedingService feedingService = FeedingService.getInstance();
	private AnimationSet selectionAnimation = new SelectionAnimationSet(false);

	@Override
	public void onClick(View view) {
		view.startAnimation(selectionAnimation);

	}

}
