package net.rubywork.feedingclock.ui.support;

import net.rubywork.feedingclock.dao.FeedingRecordDao;
import net.rubywork.feedingclock.dao.impl.FeedingRecordDaoImpl;
import net.rubywork.feedingclock.model.FeedingRecord;
import android.os.SystemClock;
import android.widget.Chronometer;

public class FeedingService {
	private static final FeedingService instance = new FeedingService();

	private FeedingService() {
	}

	public static FeedingService getInstance() {
		return instance;
	}

	public void saveCurrentFeedingRecord() {
		AppContext appContext = AppContext.getInstance();
		FeedingRecordDao feedingRecordDao = FeedingRecordDaoImpl.getInstance();

		long elapsedTime = this.stopFeeding();
		String type = appContext.getCurrentType();

		Long currentSessionId = appContext.getCurrentSessionId();
		if (currentSessionId == null || currentSessionId == 0) {
			FeedingRecord feedingRecord = new FeedingRecord(type, elapsedTime);
			Long pkId = feedingRecordDao.save(feedingRecord);
			appContext.setCurrentSessionId(pkId);
			feedingRecord.setId(pkId);
			feedingRecord.setSessionId(pkId);
			feedingRecordDao.save(feedingRecord);
		} else {
			FeedingRecord feedingRecord = new FeedingRecord(currentSessionId, type, elapsedTime);
			feedingRecordDao.save(feedingRecord);
		}
	}

	public void startFeeding() {
		Chronometer chronometer = AppContext.getInstance().getChronometer();
		chronometer.setBase(SystemClock.elapsedRealtime());
		chronometer.start();
	}

	public void pauseFeeding() {
		AppContext appContext = AppContext.getInstance();
		Chronometer chronometer = appContext.getChronometer();
		chronometer.stop();
		appContext.setPausedTime(System.currentTimeMillis());
	}

	public void resumeFeeding() {
		AppContext appContext = AppContext.getInstance();
		Chronometer chronometer = appContext.getChronometer();
		chronometer.setBase(chronometer.getBase() + (System.currentTimeMillis() - appContext.getPausedTime()));
		chronometer.start();
	}

	public long stopFeeding() {
		Chronometer chronometer = AppContext.getInstance().getChronometer();
		chronometer.stop();
		return SystemClock.elapsedRealtime() - chronometer.getBase();
	}
}
