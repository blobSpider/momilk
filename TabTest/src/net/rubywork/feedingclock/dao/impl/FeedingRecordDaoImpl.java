package net.rubywork.feedingclock.dao.impl;

import static net.rubywork.feedingclock.dao.FeedingRecordDao.table;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.rubywork.feedingclock.dao.FeedingRecordDao;
import net.rubywork.feedingclock.dao.GenericDaoImpl;
import net.rubywork.feedingclock.model.FeedingRecord;
import net.rubywork.feedingclock.ui.support.AppContext;
import net.rubywork.feedingclock.ui.support.Constants;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class FeedingRecordDaoImpl extends GenericDaoImpl<FeedingRecord, Long> implements FeedingRecordDao {
	private static FeedingRecordDao instance;

	private FeedingRecordDaoImpl(Context context) {
		super(FeedingRecord.class, context);
	}

	public static FeedingRecordDao getInstance() {
		if (instance == null) {
			instance = new FeedingRecordDaoImpl(AppContext.getInstance().getMainActivity());
		}
		return instance;
	}

	@Override
	protected FeedingRecord setBindCursor(Cursor cursor) {
		FeedingRecord record = new FeedingRecord();
		record.setId(cursor.getLong(cursor.getColumnIndex(columnId)));
		record.setSessionId(cursor.getLong(cursor.getColumnIndex(columnSessionId)));
		record.setType(cursor.getString(cursor.getColumnIndex(columnType)));
		record.setValue(cursor.getLong(cursor.getColumnIndex(columnValue)));
		record.setUpdatedTime(new Date(cursor.getLong(cursor.getColumnIndex(columnUpdatedTime))));
		return record;
	}

	@Override
	protected String[] getTableCreateStmt() {
		return new String[] { tableCreateQuery };
	}

	@Override
	protected String[] getIndexCreateStmt() {
		return new String[] { indexCreateQuery };
	}

	@Override
	protected ContentValues getContentValues(FeedingRecord record) {
		ContentValues recordValues = new ContentValues();
		recordValues.put(columnType, record.getType());
		recordValues.put(columnValue, record.getValue());
		recordValues.put(columnUpdatedTime, System.currentTimeMillis());
		
		Long sessionId = record.getSessionId();
		if(sessionId != null && sessionId != 0){
			recordValues.put(columnSessionId, sessionId);
		}

		return recordValues;
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.i(Constants.LOG_TAG, clazz.getSimpleName() + "Dao - onUpgrade() : Table Upgrade Action");
		db.execSQL(tableDropQuery);
		db.execSQL(indexDropQuery);
		onCreate(db);
	}

	@Override
	public void removeAll() {
		this.sqllite.delete(table, null, null);
	}

	private static final String SQL_SELECT_ALL_SESSION = "select sessionId from feedingrecord group by sessionId order by sessionId asc";
	@Override
	public List<Long> getSessionIds(String[] selectionArgs) {
		List<Long> list = new ArrayList<Long>();
		Cursor cursor = this.queryForCursor(SQL_SELECT_ALL_SESSION, selectionArgs);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			list.add(cursor.getLong(cursor.getColumnIndex(columnSessionId)));
			cursor.moveToNext();
		}
		if (this.cursor != null) {
			this.cursor.close();
		}
		return list;
	}

	private static final String SQL_SELECT_ALL_BY_SESSIONID = "select * from feedingrecord where sessionId=? order by _id asc";
	@Override
	public List<FeedingRecord> getAllBySessionId(Long sessionId) {
		return this.queryForList(SQL_SELECT_ALL_BY_SESSIONID, new String[]{String.valueOf(sessionId)});
	}
}
