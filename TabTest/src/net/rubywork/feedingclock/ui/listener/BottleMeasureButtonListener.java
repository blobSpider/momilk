package net.rubywork.feedingclock.ui.listener;

import net.rubywork.feedingclock.R;
import net.rubywork.feedingclock.ui.anim.SelectionAnimationEndAdapter;
import net.rubywork.feedingclock.ui.anim.SelectionAnimationEndAdapter.AnimatioEndCallback;
import net.rubywork.feedingclock.ui.anim.SelectionAnimationSet;
import net.rubywork.feedingclock.ui.support.AppContext;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AnimationSet;
import android.widget.TextView;

public class BottleMeasureButtonListener implements OnClickListener {
	private AppContext appContext;
	private AnimationSet selectionAnimation = new SelectionAnimationSet(false);
	private TextView milkAmountTextView;

	public BottleMeasureButtonListener(View view) {
		this.appContext = AppContext.getInstance();
		Activity activity = appContext.getMainActivity();
		this.milkAmountTextView = (TextView) activity.findViewById(R.id.bottleAmountTextView);
		selectionAnimation.setAnimationListener(new SelectionAnimationEndAdapter(view, new AnimatioEndCallback() {
			@Override
			public void call(View view) {
				view.setBackgroundResource(appContext.isMlMeasure() ? R.drawable.oz : R.drawable.ml);
			}
		}));
	}

	public void onClick(View view) {
		if (appContext.isMlMeasure()) {
			int number = Integer.parseInt(milkAmountTextView.getText().toString());
			int ml = number * 30;
			this.milkAmountTextView.setText(Integer.toString(ml));
			appContext.setMlMeasure(false);
		} else {
			int number = Integer.parseInt(milkAmountTextView.getText().toString());
			int oz = number / 30;
			this.milkAmountTextView.setText(Integer.toString(oz));
			appContext.setMlMeasure(true);
		}
		
		view.startAnimation(selectionAnimation);
	}
}
