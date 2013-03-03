package net.rubywork.feedingclock.ui.support;

import java.util.List;
import java.util.Map;

import net.rubywork.feedingclock.R;
import net.rubywork.feedingclock.dao.FeedingRecordDao;
import net.rubywork.feedingclock.dao.impl.FeedingRecordDaoImpl;
import net.rubywork.feedingclock.model.FeedingRecord;
import android.app.Activity;
import android.os.Handler;
import android.text.format.DateUtils;
import android.widget.TextView;

public class LastFeedingRecordThread extends Thread {
	private Runnable runnable;
	private Handler handler = new Handler();
	private String[] agoTimeFormats;
	private String[] durationTimeFormats;
	private Map<String, String> typeTitleMap;

	private FeedingRecordDao feedingRecordDao;
	private Activity activity;

	private TextView agoTimeView;
	private TextView feedingTypeView;
	private TextView lastTimeView;
	private TextView durationView;
	
	public void run() {
		while (true) {
			handler.post(runnable);
			try {
				Thread.sleep(1000 * 60);
			} catch (Exception e) {
			}
		}
	}

	public LastFeedingRecordThread() {
		this.feedingRecordDao = FeedingRecordDaoImpl.getInstance();
		AppContext appContext = AppContext.getInstance();
		this.activity = appContext.getMainActivity();
		this.agoTimeFormats = appContext.getAgoTimeFormats();
		this.durationTimeFormats = appContext.getDurationTimeFormats();
		this.typeTitleMap = appContext.getTypeTitleMap();
		
		this.agoTimeView = (TextView) activity.findViewById(R.id.agoTimeView);
		this.feedingTypeView = (TextView) activity.findViewById(R.id.feedingTypeView);
		this.lastTimeView = (TextView) activity.findViewById(R.id.lastTimeView);
		this.durationView = (TextView) activity.findViewById(R.id.durationView);

		runnable = new Runnable() {
			public void run() {
				updateLastRecordView();
			}
		};
	}

	private static final String SQL_LATEST_ONE = "select * from feedingrecord order by _id desc limit 1";
	private static final String SQL_LATEST_SESSION = "select * from feedingrecord where sessionId=? order by _id asc";
	public void updateLastRecordView() {
		FeedingRecord lastRecord = feedingRecordDao.queryForObject(SQL_LATEST_ONE, null);
		if (lastRecord != null) {
			List<FeedingRecord> list = feedingRecordDao.queryForList(SQL_LATEST_SESSION, new String [] {String.valueOf(lastRecord.getSessionId())});
			if(list.size() > 1){
				long total = 0l;
				for(FeedingRecord r : list){
					total += r.getValue();
				}
				
				lastRecord.setType("LR");
				lastRecord.setValue(total);
			}

			Object[] durationTimeElements = DateUtils.formatElapsedTime(lastRecord.getValue() / 1000L).split(":");
			Object[] agoTimeElements = DateUtils.formatElapsedTime(lastRecord.getAgoTimeMillis() / 1000L).split(":");
			
			agoTimeView.setText(String.format(agoTimeFormats[agoTimeElements.length - 2], agoTimeElements));
			durationView.setText(String.format(durationTimeFormats[durationTimeElements.length - 1], durationTimeElements));
			feedingTypeView.setText(typeTitleMap.get(lastRecord.getType()));
			lastTimeView.setText(DateUtils.formatDateTime(activity, lastRecord.getUpdatedTimeMillis(), DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_12HOUR));
		}
	}
}
