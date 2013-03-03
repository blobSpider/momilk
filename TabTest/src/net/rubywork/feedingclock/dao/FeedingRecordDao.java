package net.rubywork.feedingclock.dao;

import net.rubywork.feedingclock.model.FeedingRecord;

public interface FeedingRecordDao extends GenericDao<FeedingRecord, Long>{
	public final String table = "feedingrecord";
	public final String columnId = "_id";
	public final String columnSessionId = "sessionId";
	public final String columnType = "feedType";
	public final String columnValue = "measureValue";
	public final String columnUpdatedTime = "updatedTime";
	public final Object [] columns = {columnId, columnSessionId, columnType, columnValue, columnUpdatedTime};

	public final String tableCreateQuery = String.format("CREATE TABLE IF NOT EXISTS %s ( %s INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, %s LONG, %s TEXT, %s LONG, %s LONG);", table, columnId, columnSessionId, columnType, columnValue, columnUpdatedTime);
	public final String indexCreateQuery = String.format("CREATE UNIQUE INDEX %s_pk ON %s (%s);", table, table, columnId);
	public final String tableDropQuery = "DROP TABLE IF EXISTS " + table;
	public final String indexDropQuery = "DROP INDEX IF EXISTS " + table;

	public void removeAll();
}