package net.rubywork.feedingclock.ui.view;

import net.rubywork.feedingclock.ui.support.AppContext;
import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;

public class MainMenuButton extends Button implements View.OnClickListener {
	private boolean hasOpened;
	private ImageView imageView;
	private SubMenuButton[] buttons;
	private boolean animateFlag;
	private int menuButtonWidth = 0;

	private Animation openRotateAnimation = new RotateAnimation(0, 45, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
	private Animation closeRotateAnimation = new RotateAnimation(-45, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

	private AnimationListener openAnimationListener = new AnimationListener() {
		@Override
		public void onAnimationStart(Animation animation) {
		}

		@Override
		public void onAnimationRepeat(Animation animation) {
		}

		@Override
		public void onAnimationEnd(Animation animation) {
			animateFlag = false;
		}
	};

	public MainMenuButton(Context context) {
		super(context);
		this.initAnimation();
		this.setOnClickListener(this);
	}

	public MainMenuButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.initAnimation();
		this.setOnClickListener(this);
	}

	public MainMenuButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.initAnimation();
		this.setOnClickListener(this);
	}

	private void initAnimation() {
		this.openRotateAnimation.setInterpolator(AnimationUtils.loadInterpolator(getContext(), android.R.anim.anticipate_overshoot_interpolator));
		this.openRotateAnimation.setFillAfter(true);
		this.openRotateAnimation.setAnimationListener(openAnimationListener);

		this.closeRotateAnimation.setInterpolator(AnimationUtils.loadInterpolator(getContext(), android.R.anim.anticipate_overshoot_interpolator));
		this.closeRotateAnimation.setInterpolator(AnimationUtils.loadInterpolator(getContext(), android.R.anim.anticipate_overshoot_interpolator));
		this.closeRotateAnimation.setFillAfter(true);
		this.closeRotateAnimation.setAnimationListener(openAnimationListener);
	}

	public void setImageView(ImageView imageView) {
		this.imageView = imageView;
	}

	public void setButtons(SubMenuButton[] buttons) {
		this.buttons = buttons;
	}

	@Override
	public void onClick(View v) {
		if (!this.isOnAnimateMenuButtons()) {
			animateFlag = true;
			toggle();
		}
	}

	public void toggle() {
		this.toggleTitle();
		for (SubMenuButton button : this.buttons) {
			button.toggle();
		}
	}

	public void toggleTitle() {
		this.imageView.startAnimation(this.hasOpened ? this.closeRotateAnimation : this.openRotateAnimation);
		this.hasOpened = !this.hasOpened;
	}

//	public boolean isAnimateFlag() {
//		return animateFlag;
//	}

	private DisplayMetrics displayMetrics = new DisplayMetrics();

	public int getMenuButtonWidth() {
		if (this.menuButtonWidth == 0) {
			AppContext.getInstance().getMainActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
			this.menuButtonWidth = displayMetrics.widthPixels / 3;
		}
		return menuButtonWidth;
	}

	public boolean isOnAnimateMenuButtons() {
		boolean flag = animateFlag;
		for (SubMenuButton subButton : this.buttons) {
			flag = flag || subButton.isAnimateFlag();
		}
		return flag;
	}

}
