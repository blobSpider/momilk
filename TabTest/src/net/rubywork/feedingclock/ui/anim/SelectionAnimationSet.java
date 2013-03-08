package net.rubywork.feedingclock.ui.anim;

import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;

public class SelectionAnimationSet extends AnimationSet {
	private static final int DURATION_ALPHA = 300;
	private static final int DURATION_SCALE = 300;

	public SelectionAnimationSet(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(2.0f);
	}

	public SelectionAnimationSet(boolean shareInterpolator) {
		super(shareInterpolator);
		init(2.0f);
	}

	public SelectionAnimationSet(boolean shareInterpolator, float toValue) {
		super(shareInterpolator);
		init(toValue);
	}

	private void init(float toValue) {
		Animation alpha = new AlphaAnimation(1.0f, 0.0f);
		Animation scale = new ScaleAnimation(0.5f, toValue, 0.5f, toValue, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

		alpha.setDuration(DURATION_ALPHA);
		scale.setDuration(DURATION_SCALE);

		this.setFillAfter(false);
		this.addAnimation(scale);
		this.addAnimation(alpha);

	}
}
