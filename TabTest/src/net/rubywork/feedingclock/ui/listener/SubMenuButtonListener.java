package net.rubywork.feedingclock.ui.listener;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import net.rubywork.feedingclock.R;
import net.rubywork.feedingclock.ui.support.AppContext;
import net.rubywork.feedingclock.ui.view.MainMenuButton;
import net.rubywork.feedingclock.ui.view.SubMenuButton;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class SubMenuButtonListener implements View.OnClickListener {
	private MainMenuButton mainButton;
	private Map<SubMenuButton, MenuViewEntry> buttonViewMap;

	private Animation inFromLeftAnimation = AnimationUtils.loadAnimation(AppContext.getInstance().getMainActivity(), R.anim.trans_in_from_left);
	private Animation outToLeftAnimation = AnimationUtils.loadAnimation(AppContext.getInstance().getMainActivity(), R.anim.trans_out_to_left);
	private Animation inFromRightAnimation = AnimationUtils.loadAnimation(AppContext.getInstance().getMainActivity(), R.anim.trans_in_from_right);
	private Animation outToRightAnimation = AnimationUtils.loadAnimation(AppContext.getInstance().getMainActivity(), R.anim.trans_out_to_right);

	public SubMenuButtonListener(MainMenuButton mainButton, Map<SubMenuButton, MenuViewEntry> buttonViewMap) {
		this.mainButton = mainButton;
		this.buttonViewMap = buttonViewMap;
	}

	@Override
	public void onClick(View v) {
		MenuViewEntry showingViewEntry = buttonViewMap.get(v);
		View showingView = showingViewEntry.view;
		int showingOrder = showingViewEntry.order;

		for (Iterator<Entry<SubMenuButton, MenuViewEntry>> it = buttonViewMap.entrySet().iterator(); it.hasNext();) {
			Entry<SubMenuButton, MenuViewEntry> entry = it.next();
			SubMenuButton subMenuButton = entry.getKey();
			subMenuButton.toggle(v);
			
			MenuViewEntry hidingViewEntry = entry.getValue();
			View hidingView = hidingViewEntry.view;
			int hidingOrder = hidingViewEntry.order;
			if(showingOrder != hidingOrder && hidingView.isShown()){
				hidingView.startAnimation(showingOrder < hidingOrder ? outToLeftAnimation : outToRightAnimation);
				hidingView.setVisibility(View.INVISIBLE);
				
				showingView.startAnimation(showingOrder < hidingOrder ? inFromLeftAnimation : inFromRightAnimation);
				showingView.setVisibility(View.VISIBLE);
				showingViewEntry.invokeCallback();
			}
		}
		mainButton.toggleTitle();
	}

	public static class MenuViewEntry {
		int order;
		View view;
		private MenuViewCallback viewCallback;

		public MenuViewEntry(int order, View view) {
			this.order = order;
			this.view = view;
		}

		public MenuViewEntry(int order, View view, MenuViewCallback viewCallback) {
			this.order = order;
			this.view = view;
			this.viewCallback = viewCallback;
		}

		void invokeCallback() {
			if (viewCallback != null) {
				this.viewCallback.call();
			}
		}
	}

	public static interface MenuViewCallback {
		public void call();
	}
}