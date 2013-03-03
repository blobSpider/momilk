package net.rubywork.feedingclock.model;

import java.util.Date;

public class FeedingRecord {
	private Long id;
	private Long sessionId;
	private String type;
	private long value;
	private Date updatedTime;

	public FeedingRecord(){}
	
	public FeedingRecord(String type, long value) {
		this.type = type;
		this.value = value;
	}

	public FeedingRecord(Long sessionId, String type, long value) {
		this(type, value);
		this.sessionId = sessionId;
	}

	public FeedingRecord(Long id, Long sessionId, String type, long value) {
		this(sessionId, type, value);
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
		return new StringBuilder().append(String.valueOf(getId())).append(":").append(getType()).append(":").append(getValue()).append(":").append(updatedTime).toString();
	}
}
