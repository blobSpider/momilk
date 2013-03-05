package net.rubywork.feedingclock.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;

public class MainMenuButton extends Button implements View.OnClickListener {
	private boolean hasOpened;
	private ImageView imageView;
	private SubMenuButton[] buttons;

	private Animation openRotateAnimation = new RotateAnimation(0, 45, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
	private Animation closeRotateAnimation = new RotateAnimation(-45, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

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

		this.closeRotateAnimation.setInterpolator(AnimationUtils.loadInterpolator(getContext(), android.R.anim.anticipate_overshoot_interpolator));
		this.closeRotateAnimation.setInterpolator(AnimationUtils.loadInterpolator(getContext(), android.R.anim.anticipate_overshoot_interpolator));
		this.closeRotateAnimation.setFillAfter(true);
	}

	public void setImageView(ImageView imageView) {
		this.imageView = imageView;
	}

	public void setButtons(SubMenuButton[] buttons) {
		this.buttons = buttons;
	}

	@Override
	public void onClick(View v) {
		toggle();
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
}
