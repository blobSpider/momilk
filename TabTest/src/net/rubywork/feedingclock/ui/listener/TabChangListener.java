package net.rubywork.feedingclock.ui.listener;

import java.util.ArrayList;
import java.util.List;

import net.rubywork.feedingclock.R;
import net.rubywork.feedingclock.dao.FeedingRecordDao;
import net.rubywork.feedingclock.dao.impl.FeedingRecordDaoImpl;
import net.rubywork.feedingclock.model.FeedingRecord;
import net.rubywork.feedingclock.model.FeedingSession;
import net.rubywork.feedingclock.ui.support.AppContext;
import net.rubywork.feedingclock.ui.view.FeedingSessionListViewAdapter;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;

public class TabChangListener implements OnTabChangeListener {
	private static final long DURATION_TRANSLATE = 300;
	
	private TabHost tabHost;
	private View previousView;
	
	public TabChangListener(TabHost tabHost){
		this.tabHost = tabHost;
	}
	
	@Override
	public void onTabChanged(String tabId) {
		applyAnimation();
		
		if(tabId.equals(AppContext.TAB_IDs[0])){ // record

		}else if(tabId.equals(AppContext.TAB_IDs[1])){	// history
			
			// make the list of histories.
			this.makeListView();
			
			
		}else if(tabId.equals(AppContext.TAB_IDs[2])){	// music
			
		}else if(tabId.equals(AppContext.TAB_IDs[3])){	// settings
			
		}
	}

	private void makeListView(){
		FeedingRecordDao feedingRecordDao = FeedingRecordDaoImpl.getInstance();

		List<Long> sessionIds = feedingRecordDao.getSessionIds(new String[]{});
		List<FeedingSession> feedingSessions = new ArrayList<FeedingSession>(sessionIds.size());
		for(Long sessionId : sessionIds){
			List<FeedingRecord> list = feedingRecordDao.getAllBySessionId(sessionId);
			feedingSessions.add(new FeedingSession(list));
		}

		FeedingSessionListViewAdapter listAdapter = new FeedingSessionListViewAdapter(AppContext.getInstance().getMainActivity());
		listAdapter.addAll(feedingSessions);
		
		ListView listView = (ListView) AppContext.getInstance().getMainActivity().findViewById(R.id.historyListView);
		listView.setAdapter(listAdapter);
//		listView.setOnItemClickListener(demoListItemClickListener);
	}
	
	private void applyAnimation(){
		if(previousView != null){
			previousView.setAnimation(getOutAnimation());
		}
		
		View currentView = tabHost.getCurrentView();
		currentView.setAnimation(getInAnimation());
		previousView = currentView;
	}

	private Animation getInAnimation() {
		Animation animation = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, +1.0f, Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0.0f);
		animation.setDuration(DURATION_TRANSLATE);
		return animation;
	}

	private Animation getOutAnimation() {
		Animation animation = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, -1.0f, Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0.0f);
		animation.setDuration(DURATION_TRANSLATE);
		return animation;
	}
}
