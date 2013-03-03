package net.rubywork.feedingclock;

import net.rubywork.feedingclock.ui.listener.ChangeButtonListener;
import net.rubywork.feedingclock.ui.listener.LeftButtonListener;
import net.rubywork.feedingclock.ui.listener.PauseButtonListener;
import net.rubywork.feedingclock.ui.listener.RightButtonListener;
import net.rubywork.feedingclock.ui.listener.StopButtonListener;
import net.rubywork.feedingclock.ui.support.AppContext;
import net.rubywork.feedingclock.ui.support.LastFeedingRecordThread;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class MainActivity extends Activity {
	private static final String[] TAB_TAGs = { "Tab1", "Tab2", "Tab3", "Tab4" };
	private static final int[] TAB_CONTENT_LAYOUT_IDs = { R.id.feedingContainer, R.id.historyContainer, R.id.soundContainer, R.id.moreContainer };

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
		AppContext.getInstance().setMainActivity(this);

		initTab();
		initListener();
	}

	private void initTab() {
		TabHost tabHost = (TabHost) findViewById(android.R.id.tabhost);
		tabHost.setup();

		for (int i = 0; i < TAB_CONTENT_LAYOUT_IDs.length; i++) {
			TabSpec tabSpec = tabHost.newTabSpec(TAB_TAGs[i]);
			tabSpec.setContent(TAB_CONTENT_LAYOUT_IDs[i]);
			tabSpec.setIndicator(TAB_TAGs[i]);
			tabHost.addTab(tabSpec);
		}
	}
	
	private void initListener(){
		findViewById(R.id.leftButton).setOnClickListener(new LeftButtonListener());
		findViewById(R.id.rightButton).setOnClickListener(new RightButtonListener());
//		new BottleButtonOnClickListener(this);

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
	}
}