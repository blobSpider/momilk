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
	protected View breastFeedingStatusView;
	protected View bottleFeedingStatusView;
	protected View leftImageView;
	protected View rightImageView;
	protected View bottleButton;

	private FeedingService feedingService = FeedingService.getInstance();
	private AnimationSet selectionAnimation = new SelectionAnimationSet(false);

	public StartableButtonListener(View view) {
		this.appContext = AppContext.getInstance();
		Activity activity = appContext.getMainActivity();
		this.feedingMainView = activity.findViewById(R.id.feedingMainView);
		this.breastFeedingStatusView = activity.findViewById(R.id.breastFeedingStatusView);
		this.bottleFeedingStatusView = activity.findViewById(R.id.bottleFeedingStatusView);
		this.bottleButton = activity.findViewById(R.id.bottleButton);
		this.leftImageView = activity.findViewById(R.id.leftImageView);
		this.rightImageView = activity.findViewById(R.id.rightImageView);

		selectionAnimation.setAnimationListener(new SelectionAnimationEndAdapter(view, new AnimatioEndCallback() {
			@Override
			public void call(View view) {
				if (view == bottleButton) {
					bottleFeedingStatusView.setVisibility(View.VISIBLE);
					breastFeedingStatusView.setVisibility(View.INVISIBLE);
					feedingMainView.setVisibility(View.INVISIBLE);
				}else{
					breastFeedingStatusView.setVisibility(View.VISIBLE);
					bottleFeedingStatusView.setVisibility(View.INVISIBLE);
					feedingMainView.setVisibility(View.INVISIBLE);
				}
			}
		}));
	}

	@Override
	public void onClick(View view) {
		view.startAnimation(selectionAnimation);

		if(view == bottleButton){
			feedingService.bottleStartFeeding();
		}else{
			feedingService.startFeeding();	
		}		
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
