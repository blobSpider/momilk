package net.rubywork.feedingclock.ui.view;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;

public class SubMenuButton extends Button {
	private static final int OFFSET_ANIM_START = 50;
	private static final int DURATION_TRANS = 150;
	private static final int DURATION_ALPHA = 200;
	private static final int DURATION_SCALE = 200;
	
	private int width = 150;
	private boolean animateFlag;
	private boolean hasShown;
	
	private float openOffsetX;
	private float openOffsetY;
	private float closeOffsetX;
	private float closeOffsetY;

	private Handler handler = new Handler();
	private AnimationSet openAnimationSet = new AnimationSet(false);
	private AnimationSet closeAnimationSet = new AnimationSet(false);
	private AnimationSet hideAnimationSet = new AnimationSet(false);
	private AnimationSet selectAnimationSet = new AnimationSet(false);
	private Animation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
	private Animation openRotateAnimation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
	private Animation closeRotateAnimation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
	
	private AnimationListener openAnimationListener = new AnimationListener() {
		@Override public void onAnimationStart(Animation animation) {}
		@Override public void onAnimationRepeat(Animation animation) {}
		@Override public void onAnimationEnd(Animation animation) {
			handler.postDelayed(new Runnable(){
				public void run(){
					offsetLeftAndRight((int) openOffsetX);
					offsetTopAndBottom((int) openOffsetY);
					animateFlag = false;
				}
			}, 50);
		}
	};

	private AnimationListener closeAnimationListener = new AnimationListener() {
		@Override public void onAnimationStart(Animation animation) {}
		@Override public void onAnimationRepeat(Animation animation) {}
		@Override public void onAnimationEnd(Animation animation) {
			offsetLeftAndRight((int) closeOffsetX);
			offsetTopAndBottom((int) closeOffsetY);
			animateFlag = false;
		}
	};

	public SubMenuButton(Context context) {
		super(context);
	}

	public SubMenuButton(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public SubMenuButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	
	public void initView(MainMenuButton mainMenuButton){
		this.width = mainMenuButton.getMenuButtonWidth();
	}

	public void initAnimation(int buttonTotalCount, int currentIndex) {
		double xValue = width * Math.cos((double) (Math.PI * 0.5d * (currentIndex) / (buttonTotalCount - 1)));
		double yValue = width * Math.sin((double) (Math.PI * 0.5d * (currentIndex) / (buttonTotalCount - 1)));
		
		this.openOffsetX = (float)xValue;
		this.openOffsetY = (float)-yValue;
		this.closeOffsetX = (float)-xValue;
		this.closeOffsetY = (float)yValue;
		int startOffset = OFFSET_ANIM_START * currentIndex;

		initRotateAnimation(startOffset);
		initOpenAnimation(startOffset);
		initCloseAnimation(startOffset);
		initSelectAnimation();
		initHideAnimation();
	}

	private void initHideAnimation() {
		Animation hideScaleAnimation = new ScaleAnimation(1.0f, 0.0f, 1.0f, 0.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		hideScaleAnimation.setDuration(DURATION_SCALE);

		alphaAnimation.setDuration(DURATION_ALPHA);
		
		hideAnimationSet.setFillAfter(false);
		hideAnimationSet.addAnimation(hideScaleAnimation);
		hideAnimationSet.addAnimation(alphaAnimation);
		hideAnimationSet.setAnimationListener(closeAnimationListener);
	}

	private void initSelectAnimation() {
		Animation selectScaleAnimation = new ScaleAnimation(1.0f, 2.5f, 1.0f, 2.5f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		selectScaleAnimation.setDuration(DURATION_SCALE);

		alphaAnimation.setDuration(DURATION_ALPHA);
		
		selectAnimationSet.setFillAfter(false);
		selectAnimationSet.addAnimation(selectScaleAnimation);
		selectAnimationSet.addAnimation(alphaAnimation);
		selectAnimationSet.setAnimationListener(closeAnimationListener);
	}

	private void initCloseAnimation(int startOffset) {
		Animation closeTranslateAnimation = new TranslateAnimation(0.0f, this.closeOffsetX, 0.0f, this.closeOffsetY);
		closeTranslateAnimation.setDuration(DURATION_TRANS);
		closeTranslateAnimation.setInterpolator(AnimationUtils.loadInterpolator(getContext(), android.R.anim.overshoot_interpolator));
		closeTranslateAnimation.setStartOffset(startOffset);

		closeAnimationSet.setFillAfter(false);
		closeAnimationSet.addAnimation(closeRotateAnimation);
		closeAnimationSet.addAnimation(closeTranslateAnimation);
		closeAnimationSet.setAnimationListener(closeAnimationListener);
	}

	private void initOpenAnimation(int startOffset) {
		Animation openTranslateAnimation = new TranslateAnimation(0.0f, this.openOffsetX, 0.0f, this.openOffsetY);
		openTranslateAnimation.setDuration(DURATION_TRANS);
		openTranslateAnimation.setInterpolator(AnimationUtils.loadInterpolator(getContext(), android.R.anim.overshoot_interpolator));
		openTranslateAnimation.setStartOffset(startOffset);

		openAnimationSet.setFillAfter(false);
		openAnimationSet.addAnimation(openRotateAnimation);
		openAnimationSet.addAnimation(openTranslateAnimation);
		openAnimationSet.setAnimationListener(openAnimationListener);
	}

	private void initRotateAnimation(int startOffset) {
		openRotateAnimation.setDuration(DURATION_TRANS);
		openRotateAnimation.setRepeatCount(1);
		openRotateAnimation.setInterpolator(AnimationUtils.loadInterpolator(getContext(), android.R.anim.accelerate_interpolator));
		openRotateAnimation.setStartOffset(startOffset);
		
		closeRotateAnimation.setDuration(DURATION_TRANS);
		closeRotateAnimation.setRepeatCount(0);
		closeRotateAnimation.setInterpolator(AnimationUtils.loadInterpolator(getContext(), android.R.anim.accelerate_interpolator));
		closeRotateAnimation.setStartOffset(startOffset);
	}

	public void toggle() {
		animateFlag = true;
		startAnimation(hasShown ? closeAnimationSet : openAnimationSet);
		this.hasShown = !this.hasShown;
	}

	public void toggle(View v) {
		animateFlag = true;
		startAnimation(this == v ? selectAnimationSet : hideAnimationSet);
		this.hasShown = !this.hasShown;
	}

	public boolean isAnimateFlag() {
		return animateFlag;
	}
	
}
