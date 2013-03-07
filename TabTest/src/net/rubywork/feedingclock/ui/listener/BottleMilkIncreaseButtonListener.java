package net.rubywork.feedingclock.ui.listener;

import net.rubywork.feedingclock.R;
import net.rubywork.feedingclock.ui.anim.SelectionAnimationSet;
import net.rubywork.feedingclock.ui.support.AppContext;
import android.app.Activity;
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
		int plus = Integer.parseInt(milkAmountTextView.getText().toString());
		int increase = plus + 10;
		this.milkAmountTextView.setText(Integer.toString(increase));
		
		view.startAnimation(selectionAnimation);

	}

}
