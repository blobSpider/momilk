package net.rubywork.feedingclock;

import net.rubywork.feedingclock.ui.listener.ChangeButtonListener;
import net.rubywork.feedingclock.ui.listener.LeftButtonListener;
import net.rubywork.feedingclock.ui.listener.MusicButtonListener;
import net.rubywork.feedingclock.ui.listener.PauseButtonListener;
import net.rubywork.feedingclock.ui.listener.RightButtonListener;
import net.rubywork.feedingclock.ui.listener.StopButtonListener;
import net.rubywork.feedingclock.ui.listener.TabChangListener;
import net.rubywork.feedingclock.ui.support.AppContext;
import net.rubywork.feedingclock.ui.support.LastFeedingRecordThread;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class MainActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		init();
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		AppContext.getInstance().setLastFeedingRecordThread(new LastFeedingRecordThread()).start();
	}

	private void init() {
		AppContext.getInstance().setMainActivity(this); // * important : it must do invocation at first of all!

		initView();
		initTab();
		initListeners();
	}
	
	private void initView(){

	}

	private void initTab() {
		final TabHost tabHost = (TabHost) findViewById(android.R.id.tabhost);
		tabHost.setup();
		tabHost.setOnTabChangedListener(new TabChangListener(tabHost));

		for (int i = 0; i < AppContext.TAB_CONTENT_LAYOUT_IDs.length; i++) {
			TabSpec tabSpec = tabHost.newTabSpec(AppContext.TAB_IDs[i])
					.setContent(AppContext.TAB_CONTENT_LAYOUT_IDs[i])
					.setIndicator(AppContext.TAB_LABELs[i], getResources().getDrawable(AppContext.TAB_IMG_OFF_IDs[i]));
			tabHost.addTab(tabSpec);
		}
	}

	private void initListeners() {
		findViewById(R.id.leftButton).setOnClickListener(new LeftButtonListener());
		findViewById(R.id.rightButton).setOnClickListener(new RightButtonListener());
		// new BottleButtonOnClickListener(this);

		View pauseButton = findViewById(R.id.pauseResumeButton);
		pauseButton.setOnClickListener(new PauseButtonListener(pauseButton));
		View stopButton = findViewById(R.id.stopButton);
		StopButtonListener stopButtonListener = new StopButtonListener();
		stopButton.setOnTouchListener(stopButtonListener);
		stopButton.setOnClickListener(stopButtonListener);

		View changeButton = findViewById(R.id.changeButton);
		ChangeButtonListener changeButtonListener = new ChangeButtonListener();
		changeButton.setOnTouchListener(changeButtonListener);
		changeButton.setOnClickListener(changeButtonListener);
		
		findViewById(R.id.ticTok).setOnClickListener(new MusicButtonListener());
	}
}