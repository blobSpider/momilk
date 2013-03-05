package net.rubywork.feedingclock.ui.listener;

import java.util.ArrayList;
import java.util.List;

import net.rubywork.feedingclock.R;
import net.rubywork.feedingclock.dao.FeedingRecordDao;
import net.rubywork.feedingclock.dao.impl.FeedingRecordDaoImpl;
import net.rubywork.feedingclock.model.FeedingRecord;
import net.rubywork.feedingclock.model.FeedingSession;
import net.rubywork.feedingclock.ui.listener.SubMenuButtonListener.MenuViewCallback;
import net.rubywork.feedingclock.ui.support.AppContext;
import net.rubywork.feedingclock.ui.view.FeedingSessionListViewAdapter;
import android.widget.ListView;

public class HistoryMenuViewCallback implements MenuViewCallback {
	private FeedingRecordDao feedingRecordDao;
	private FeedingSessionListViewAdapter listAdapter;
	
	public HistoryMenuViewCallback(){
		this.feedingRecordDao = FeedingRecordDaoImpl.getInstance();
		this.listAdapter = new FeedingSessionListViewAdapter(AppContext.getInstance().getMainActivity());
		
		ListView listView = (ListView) AppContext.getInstance().getMainActivity().findViewById(R.id.historyListView);
		listView.setAdapter(listAdapter);
	}
	
	@Override
	public void call() {
		List<Long> sessionIds = feedingRecordDao.getSessionIds(new String[]{});
		List<FeedingSession> feedingSessions = new ArrayList<FeedingSession>(sessionIds.size());
		for(Long sessionId : sessionIds){
			List<FeedingRecord> list = feedingRecordDao.getAllBySessionId(sessionId);
			feedingSessions.add(new FeedingSession(list));
		}
		listAdapter.reset(feedingSessions);
	}

}
