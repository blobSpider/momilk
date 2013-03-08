package net.rubywork.feedingclock.ui.listener;

import net.rubywork.feedingclock.R;
import net.rubywork.feedingclock.ui.anim.SelectionAnimationSet;
import net.rubywork.feedingclock.ui.support.AppContext;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AnimationSet;
import android.widget.TextView;

public class BottleMilkIncreaseButtonListener implements OnClickListener {
	private AppContext appContext;
	private AnimationSet selectionAnimation = new SelectionAnimationSet(false);
	private TextView milkAmountTextView;
	
	public BottleMilkIncreaseButtonListener(){
		this.appContext = AppContext.getInstance();
		Activity activity = appContext.getMainActivity();
		this.milkAmountTextView = (TextView) activity.findViewById(R.id.bottleAmountTextView);
	}
	
	@Override
	public void onClick(View view) {
		float plus = Float.parseFloat(milkAmountTextView.getText().toString());
		float increase;
		if (appContext.isMlMeasure()) {
			increase = (float) (plus + 0.5);
			this.milkAmountTextView.setText(Float.toString(increase));
		} else {
			increase = (int)(plus + 10);
			this.milkAmountTextView.setText(Integer.toString((int) increase));
		}
		
		
		
		
		view.startAnimation(selectionAnimation);

	}

}
