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
import android.widget.TextView;

public class StopButtonListener implements OnClickListener {
	private AppContext appContext;
	private View feedingMainView;
	private View breastFeedingStatusView;
	private View bottleFeedingStatusView;
	private TextView bottleAmountTextView;
	private View pauseResumeButton;

	private FeedingService feedingService = FeedingService.getInstance();
	private AnimationSet selectionAnimation = new SelectionAnimationSet(false);

	public StopButtonListener() {
		this.appContext = AppContext.getInstance();
		Activity activity = appContext.getMainActivity();
		this.feedingMainView = activity.findViewById(R.id.feedingMainView);
		this.breastFeedingStatusView = activity.findViewById(R.id.breastFeedingStatusView);
		this.bottleFeedingStatusView = activity.findViewById(R.id.bottleFeedingStatusView);
		this.bottleAmountTextView = (TextView) activity.findViewById(R.id.bottleAmountTextView);
		this.pauseResumeButton = activity.findViewById(R.id.pauseResumeButton);

		selectionAnimation.setAnimationListener(new SelectionAnimationEndAdapter(new AnimatioEndCallback() {
			@Override
			public void call(View view) {
				feedingMainView.setVisibility(View.VISIBLE);
				breastFeedingStatusView.setVisibility(View.INVISIBLE);
				bottleFeedingStatusView.setVisibility(View.INVISIBLE);
			}
		}));
	}

	@Override
	public void onClick(View view) {
		appContext.setBottleAmount(Float.parseFloat(bottleAmountTextView.getText().toString()));
		feedingService.saveCurrentFeedingRecord(view);
		appContext.setPause(false);
		pauseResumeButton.setBackgroundResource(R.drawable.button_pause);
		view.startAnimation(selectionAnimation);
	}
}
