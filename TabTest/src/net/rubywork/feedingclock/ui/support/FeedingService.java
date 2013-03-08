package net.rubywork.feedingclock.ui.support;

import net.rubywork.feedingclock.R;
import net.rubywork.feedingclock.dao.FeedingRecordDao;
import net.rubywork.feedingclock.dao.impl.FeedingRecordDaoImpl;
import net.rubywork.feedingclock.model.FeedingRecord;
import android.app.Activity;
import android.os.SystemClock;
import android.view.View;
import android.widget.Chronometer;

public class FeedingService {
	private static final FeedingService instance = new FeedingService();
	private View bottleStopButton;

	private FeedingService() {
	}

	public static FeedingService getInstance() {
		return instance;
	}

	public void saveCurrentFeedingRecord(View view) {
		AppContext appContext = AppContext.getInstance();
		Activity activity = appContext.getMainActivity();
		this.bottleStopButton = activity.findViewById(R.id.bottleFeedingStopButton);

		FeedingRecordDao feedingRecordDao = FeedingRecordDaoImpl.getInstance();
		long measureValue;

		if (view == bottleStopButton) {
			this.bottleStopFeeding();
			measureValue = (long) appContext.getBottleAmount();
		} else {
			measureValue = this.stopFeeding();
		}
		String type = appContext.getCurrentType();
		String unit = appContext.isMlMeasure() ? "ml" : "oz";
		
		Long currentSessionId = appContext.getCurrentSessionId();
		if (currentSessionId == null || currentSessionId == 0) {
			FeedingRecord feedingRecord = new FeedingRecord(type, measureValue, unit);
			Long pkId = feedingRecordDao.save(feedingRecord);
			appContext.setCurrentSessionId(pkId);
			feedingRecord.setId(pkId);
			feedingRecord.setSessionId(pkId);
			feedingRecordDao.save(feedingRecord);
		} else {
			FeedingRecord feedingRecord = new FeedingRecord(currentSessionId, type, measureValue, unit);
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

	public void bottleStartFeeding() {
		Chronometer chronometer = AppContext.getInstance().getBottleChronometer();
		chronometer.setBase(SystemClock.elapsedRealtime());
		chronometer.start();
	}

	public void bottlePauseFeeding() {
		AppContext appContext = AppContext.getInstance();
		Chronometer chronometer = appContext.getBottleChronometer();
		chronometer.stop();
		appContext.setPausedTime(System.currentTimeMillis());
	}

	public void bottleResumeFeeding() {
		AppContext appContext = AppContext.getInstance();
		Chronometer chronometer = appContext.getBottleChronometer();
		chronometer.setBase(chronometer.getBase() + (System.currentTimeMillis() - appContext.getPausedTime()));
		chronometer.start();
	}

	public long bottleStopFeeding() {
		Chronometer chronometer = AppContext.getInstance().getBottleChronometer();
		chronometer.stop();
		return SystemClock.elapsedRealtime() - chronometer.getBase();
	}
}
