package net.rubywork.feedingclock.model;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FeedingSession {
	private static final Map<String, String> TYPE_MAP = new HashMap<String, String>();
	static {
		TYPE_MAP.put("[B]", "B");
		TYPE_MAP.put("[L]", "L");
		TYPE_MAP.put("[R]", "R");
		TYPE_MAP.put("[B, L]", "BLR");
		TYPE_MAP.put("[B, R]", "BLR");
		TYPE_MAP.put("[L, R]", "LR");
		TYPE_MAP.put("[B, L, R]", "BLR");
	}

	private Long sessionId;
	private List<FeedingRecord> records;
	private String type;
	private long value;
	private Date updatedTime;

	public FeedingSession(List<FeedingRecord> records) {
		this.populate(records);
	}

	public void populate(List<FeedingRecord> records) {
		this.records = records;

		long sum = 0l;
		Set<String> typeSet = new HashSet<String>();
		for (Iterator<FeedingRecord> it = records.iterator(); it.hasNext();) {
			FeedingRecord record = it.next();
			typeSet.add(record.getType());
			sum += record.getValue();

			if (!it.hasNext()) { // is last record ?
				this.sessionId = record.getSessionId();
				this.type = TYPE_MAP.get(typeSet.toString());
				this.value = sum;
				this.updatedTime = record.getUpdatedTime();
			}
		}
	}

	public Long getSessionId() {
		return sessionId;
	}

	public void setSessionId(Long sessionId) {
		this.sessionId = sessionId;
	}

	public List<FeedingRecord> getRecords() {
		return records;
	}

	public void setRecords(List<FeedingRecord> records) {
		this.records = records;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public long getValue() {
		return value;
	}

	public void setValue(long value) {
		this.value = value;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	public long getUpdatedTimeMillis() {
		return updatedTime.getTime();
	}

	public long getAgoTimeMillis() {
		return System.currentTimeMillis() - updatedTime.getTime();
	}

	public String toString() {
		 return new StringBuilder().append(String.valueOf(getSessionId())).append(":").append(records.size()).append(":").append(getType()).append(":").append(getValue()).append(":").append(updatedTime).toString();
	}
}
