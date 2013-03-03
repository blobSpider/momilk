package net.rubywork.feedingclock.ui.view;

import net.rubywork.feedingclock.ui.support.AppContext;
import net.rubywork.feedingclock.ui.support.LastFeedingRecordThread;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

public class FeedingMainView extends LinearLayout {
	public FeedingMainView(Context context) {
		super(context);
	}

	public FeedingMainView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void onVisibilityChanged(View changedView, int visibility) {
		LastFeedingRecordThread lastFeedingRecordThread = AppContext.getInstance().getLastFeedingRecordThread();
		if(lastFeedingRecordThread != null){
			lastFeedingRecordThread.updateLastRecordView();
		}
		super.onVisibilityChanged(changedView, visibility);
	}
}
