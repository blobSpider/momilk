package net.rubywork.feedingclock.ui.view;

import java.util.Map;

import net.rubywork.feedingclock.R;
import net.rubywork.feedingclock.model.FeedingSession;
import net.rubywork.feedingclock.ui.support.ResourceUtils;
import net.rubywork.feedingclock.ui.support.TimeFormatUtils;
import android.content.Context;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

public class FeedingSessionView extends LinearLayout {
	private Context context;
	private TextView feedingGapTimeTextView;
	private TextView feedingTimeTextView;
	private TextView feedingTypeTextView;
	private TextView feedingValueTextView;
	private Map<String, String> typeTitleMap;

	private static final int dateTimeFormat = DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_12HOUR;

	public FeedingSessionView(Context context) {
		super(context);
		this.context = context;
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.view_feeding_session, this, true);

		this.typeTitleMap = ResourceUtils.getTypeTitleMap();
		this.feedingGapTimeTextView = (TextView) findViewById(R.id.feedingGapTimeTextView);
		this.feedingTimeTextView = (TextView) findViewById(R.id.feedingTimeTextView);
		this.feedingTypeTextView = (TextView) findViewById(R.id.feedingTypeTextView);
		this.feedingValueTextView = (TextView) findViewById(R.id.feedingValueTextView);
	}

	public void setRecord(int no, FeedingSession prevRecord, FeedingSession currentRecord) {
		long currentUpdateTime = currentRecord.getUpdatedTimeMillis();

		this.feedingGapTimeTextView.setText(prevRecord == null ? "" : TimeFormatUtils.formatGapTime(currentUpdateTime - prevRecord.getUpdatedTimeMillis()));
		this.feedingTimeTextView.setText(DateUtils.formatDateTime(context, currentUpdateTime, dateTimeFormat));
		this.feedingTypeTextView.setText(this.typeTitleMap.get(currentRecord.getType()));
		
		if("B".equals(currentRecord.getType())){
			this.feedingValueTextView.setText(currentRecord.getValue() + currentRecord.getUnit());
		}else{
			this.feedingValueTextView.setText(TimeFormatUtils.formatDurationTime(currentRecord.getValue()));
		}
	}

}