package net.rubywork.feedingclock.ui.listener;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import net.rubywork.feedingclock.ui.view.MainMenuButton;
import net.rubywork.feedingclock.ui.view.SubMenuButton;


import android.view.View;

public class SubMenuButtonListener implements View.OnClickListener {
	private MainMenuButton mainButton;
	private Map<SubMenuButton, MenuViewEntry> buttonViewMap;

	public SubMenuButtonListener(MainMenuButton mainButton, Map<SubMenuButton, MenuViewEntry> buttonViewMap) {
		this.mainButton = mainButton;
		this.buttonViewMap = buttonViewMap;
	}

	@Override
	public void onClick(View v) {
		for (Iterator<Entry<SubMenuButton, MenuViewEntry>> it = buttonViewMap.entrySet().iterator(); it.hasNext();) {
			Entry<SubMenuButton, MenuViewEntry> entry = it.next();
			entry.getKey().toggle(v);
			entry.getValue().getView().setVisibility(View.INVISIBLE);
		}
		mainButton.toggleTitle();

		MenuViewEntry menuViewEntry = buttonViewMap.get(v);
		menuViewEntry.getView().setVisibility(View.VISIBLE);
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