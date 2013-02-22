package net.rubywork.feedingclock;

import net.rubywork.feedingclock.ui.listener.FeedingMainOnClickListener;
import net.rubywork.feedingclock.ui.listener.FeedingStatusOnClickListener;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

public class MainActivity extends Activity {
	private static final String [] TAB_TAGs = {"Tab1", "Tab2", "Tab3", "Tab4"};
	private static final int [] TAB_CONTENT_LAYOUT_IDS = {R.id.feedingContainer, R.id.historyContainer, R.id.soundContainer, R.id.moreContainer};
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		initView();
	}
	
	private void initView(){
		View feedingMainView = findViewById(R.id.feedingMainView);
		View feedingStatusView = findViewById(R.id.feedingStatusView);
		Chronometer chronometer = (Chronometer)findViewById(R.id.chronometer);
		
		new FeedingMainOnClickListener(feedingMainView, feedingStatusView, chronometer, (TextView) findViewById(R.id.leftTextView), (TextView) findViewById(R.id.rightTextView), (Button) findViewById(R.id.leftBreastButton), (Button) findViewById(R.id.bottleButton), (Button) findViewById(R.id.rightBreastButton));
		new FeedingStatusOnClickListener(feedingMainView, feedingStatusView, chronometer, (TextView) findViewById(R.id.leftTextView), (TextView) findViewById(R.id.rightTextView), (Button) findViewById(R.id.pauseResumeButton), (Button) findViewById(R.id.changeButton), (Button) findViewById(R.id.stopButton));
		
		initTab();
	}
	
	private void initTab(){
		TabHost tabHost = (TabHost) findViewById(android.R.id.tabhost);
		tabHost.setup();
		
		for(int i = 0; i<TAB_CONTENT_LAYOUT_IDS.length; i++){
			TabSpec tabSpec = tabHost.newTabSpec(TAB_TAGs[i]);
			tabSpec.setContent(TAB_CONTENT_LAYOUT_IDS[i]);
			tabSpec.setIndicator(TAB_TAGs[i]);
			tabHost.addTab(tabSpec);
		}
	}
}