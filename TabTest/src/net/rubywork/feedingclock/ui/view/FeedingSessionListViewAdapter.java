package net.rubywork.feedingclock.ui.view;

import java.util.ArrayList;
import java.util.List;

import net.rubywork.feedingclock.model.FeedingSession;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class FeedingSessionListViewAdapter extends BaseAdapter {
	private Context context;
	private List<FeedingSession> records;

	public FeedingSessionListViewAdapter(Context context) {
		this.context = context;
		this.records = new ArrayList<FeedingSession>();
	}

	@Override
	public int getCount() {
		return this.records.size();
	}

	@Override
	public Object getItem(int position) {
		return this.records.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		FeedingSessionView itemView = null;
		if(view == null){
			itemView = new FeedingSessionView(this.context);
		}else{
			itemView = (FeedingSessionView) view;
		}
		
		FeedingSession currentRecord = this.records.get(position);
		FeedingSession prevRecord = position > 0 ? this.records.get(position-1) : null;
		itemView.setRecord(position+1, prevRecord, currentRecord);
		
		return itemView;
	}

	public void clear() {
		records.clear();
		notifyDataSetChanged();
	}

	public void addItem(FeedingSession item) {
		records.add(item);
		notifyDataSetChanged();
	}

	public void removeItem(FeedingSession item) {
		records.remove(item);
		notifyDataSetChanged();
	}

	public void addAll(List<FeedingSession> items) {
		records.addAll(items);
		notifyDataSetChanged();
	}

	public void reset(List<FeedingSession> items) {
		records.clear();
		records.addAll(items);
		notifyDataSetChanged();
	}

	public boolean isSelectable(int position) {
		return true;
	}
}