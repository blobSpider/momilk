package net.rubywork.feedingclock.dao.impl;

import java.util.Date;

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
}
