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

public abstract class StartableButtonListener implements OnClickListener {
	protected AppContext appContext;
	protected View feedingMainView;
	protected View feedingStatusView;
	protected View leftImageView;
	protected View rightImageView;
	
	private FeedingService feedingService = FeedingService.getInstance();
	private AnimationSet selectionAnimation = new SelectionAnimationSet(false);

	public StartableButtonListener() {
		this.appContext = AppContext.getInstance();
		Activity activity = appContext.getMainActivity();
		this.feedingMainView = activity.findViewById(R.id.feedingMainView);
		this.feedingStatusView = activity.findViewById(R.id.feedingStatusView);
		this.leftImageView = activity.findViewById(R.id.leftImageView);
		this.rightImageView = activity.findViewById(R.id.rightImageView);

		selectionAnimation.setAnimationListener(new SelectionAnimationEndAdapter(new AnimatioEndCallback() {
			@Override
			public void call(View view) {
				feedingStatusView.setVisibility(View.VISIBLE);
				feedingMainView.setVisibility(View.INVISIBLE);
			}
		}));
	}

	@Override
	public void onClick(View view) {
		view.startAnimation(selectionAnimation);
		
		feedingService.startFeeding();
		appContext.setCurrentType(getCurrentType());
		appContext.setCurrentSessionId(0l);

		setVisibilityAllImageView(View.INVISIBLE);
		setVisibilityImageView();
	}

	private void setVisibilityAllImageView(int flag) {
		leftImageView.setVisibility(flag);
		rightImageView.setVisibility(flag);
	}

	protected abstract String getCurrentType();

	protected abstract void setVisibilityImageView();
}
