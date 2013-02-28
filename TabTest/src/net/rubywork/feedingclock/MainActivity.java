package net.rubywork.feedingclock;

import net.rubywork.feedingclock.ui.listener.BottleButtonOnClickListener;
import net.rubywork.feedingclock.ui.listener.ChangeButtonOnClickListener;
import net.rubywork.feedingclock.ui.listener.LeftButtonOnClickListener;
import net.rubywork.feedingclock.ui.listener.PauseButtonOnClickListener;
import net.rubywork.feedingclock.ui.listener.RightButtonOnClickListener;
import net.rubywork.feedingclock.ui.listener.StopButtonOnClickListener;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

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
		new LeftButtonOnClickListener(this);
		new RightButtonOnClickListener(this);
		new BottleButtonOnClickListener(this);

		new PauseButtonOnClickListener(this);
		new StopButtonOnClickListener(this);
		new ChangeButtonOnClickListener(this);
		
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