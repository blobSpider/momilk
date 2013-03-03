package net.rubywork.feedingclock.ui.anim;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

public class SelectionAnimationEndAdapter implements AnimationListener {
	private View view;
	private AnimatioEndCallback callback;

	public SelectionAnimationEndAdapter(View view, AnimatioEndCallback callback){
		this.view = view;
		this.callback = callback;
	}
	public SelectionAnimationEndAdapter(AnimatioEndCallback callback){
		this(null, callback);
	}
	
	@Override
	public void onAnimationEnd(Animation animation) {
		if(callback != null){
			callback.call(this.view);
		}
	}

	@Override public void onAnimationRepeat(Animation animation) {}
	@Override public void onAnimationStart(Animation animation) {}
	
	public static interface AnimatioEndCallback {
		public void call(View view);
	}
}
