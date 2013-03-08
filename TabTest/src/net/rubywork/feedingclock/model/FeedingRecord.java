package net.rubywork.feedingclock.model;

import java.util.Date;

public class FeedingRecord {
	private Long id;
	private Long sessionId;
	private String type;
	private long value;
	private String unit; // ml or oz
	private Date updatedTime;

	public FeedingRecord(){}
	
	public FeedingRecord(String type, long value, String unit) {
		this.type = type;
		this.value = value;
		this.unit = unit;
	}

	public FeedingRecord(Long sessionId, String type, long value, String unit) {
		this(type, value, unit);
		this.sessionId = sessionId;
	}

	public FeedingRecord(Long id, Long sessionId, String type, long value, String unit) {
		this(sessionId, type, value, unit);
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSessionId() {
		return sessionId;
	}

	public void setSessionId(Long sessionId) {
		this.sessionId = sessionId;
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

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
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
		return new StringBuilder().append(String.valueOf(getId())).append(":").append(getSessionId()).append(":").append(getType()).append(":").append(getValue()).append(":").append(updatedTime).toString();
	}
}
