package net.rubywork.feedingclock.ui.listener;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import net.rubywork.feedingclock.R;
import net.rubywork.feedingclock.ui.support.AppContext;
import net.rubywork.feedingclock.ui.view.MainMenuButton;
import net.rubywork.feedingclock.ui.view.SubMenuButton;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class SubMenuButtonListener implements View.OnClickListener {
	private MainMenuButton mainButton;
	private Map<SubMenuButton, MenuViewEntry> buttonViewMap;
	
	private Animation showAnimation = AnimationUtils.loadAnimation(AppContext.getInstance().getMainActivity(), R.anim.translate_show);
	private Animation hideAnimation = AnimationUtils.loadAnimation(AppContext.getInstance().getMainActivity(), R.anim.translate_hide);

	public SubMenuButtonListener(MainMenuButton mainButton, Map<SubMenuButton, MenuViewEntry> buttonViewMap) {
		this.mainButton = mainButton;
		this.buttonViewMap = buttonViewMap;
	}

	@Override
	public void onClick(View v) {
		for (Iterator<Entry<SubMenuButton, MenuViewEntry>> it = buttonViewMap.entrySet().iterator(); it.hasNext();) {
			Entry<SubMenuButton, MenuViewEntry> entry = it.next();
			
			SubMenuButton subMenuButton = entry.getKey();
			subMenuButton.toggle(v);
			if(subMenuButton != v){
				View hidingView = entry.getValue().getView();
				if(hidingView.isShown()){
					hidingView.startAnimation(hideAnimation);
					hidingView.setVisibility(View.INVISIBLE);
				}
			}
		}
		mainButton.toggleTitle();

		MenuViewEntry menuViewEntry = buttonViewMap.get(v);
		View showingView = menuViewEntry.getView();
		if(!menuViewEntry.getView().isShown()){
			showingView.startAnimation(showAnimation);
			showingView.setVisibility(View.VISIBLE);
		}
		menuViewEntry.invokeCallback();
	}

	public static class MenuViewEntry {
		private View view;
		private MenuViewCallback viewCallback;

		public MenuViewEntry(View view) {
			this.view = view;
		}

		public MenuViewEntry(View view, MenuViewCallback viewCallback) {
			this.view = view;
			this.viewCallback = viewCallback;
		}

		public View getView() {
			return view;
		}

		public void invokeCallback() {
			if (viewCallback != null) {
				this.viewCallback.call();
			}
		}
	}

	public static interface MenuViewCallback {
		public void call();
	}
}