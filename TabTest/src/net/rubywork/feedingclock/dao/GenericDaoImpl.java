package net.rubywork.feedingclock.dao;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import net.rubywork.feedingclock.ui.support.Constants;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public abstract class GenericDaoImpl<T, PK extends Serializable> extends SQLiteOpenHelper implements GenericDao<T, PK> {
	protected Class<T> clazz;
	protected SQLiteDatabase sqllite;
	protected Cursor cursor;
	private String table;

	protected GenericDaoImpl(Class<T> clazz, Context context) {
		super(context, Constants.DB_NAME, null, Constants.DB_VERSION);
		this.clazz = clazz;
		this.initialize(context);
		Log.i(Constants.LOG_TAG, clazz.getSimpleName() + "Dao : it has created or opened the database (" + Constants.DB_NAME + ")");
	}

	protected GenericDaoImpl(Class<T> clazz, Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
		this.clazz = clazz;
		this.initialize(context);
		Log.i(Constants.LOG_TAG, clazz.getSimpleName() + "Dao : it has created or opened the database (" + Constants.DB_NAME + ")");
	}

	private void initialize(Context context) {
		Log.i(Constants.LOG_TAG, clazz.getSimpleName() + "Dao - it is trying to create the sqlite instance of the database (" + Constants.DB_NAME + ")");

		try {
			Log.i(Constants.LOG_TAG, "it is creating or opening the database ( " + Constants.DB_NAME + " )");
			table = clazz.getSimpleName().toLowerCase();
			sqllite = this.getWritableDatabase();
		} catch (SQLiteException se) {
			Log.e(Constants.LOG_TAG, "Cound not create and/or open the database ( " + Constants.DB_NAME + " ) that will be used for reading and writing.", se);
		}
		Log.i(Constants.LOG_TAG, clazz.getSimpleName() + "Dao - it has created the sqlite instance of the database (" + Constants.DB_NAME + ")");
	}

	@Override
	public Cursor queryForCursor(String sql, String[] selectionArgs) {
		if (this.cursor != null) {
			this.cursor.close();
		}
		cursor = this.sqllite.rawQuery(sql, selectionArgs);
		return cursor;
	}

	@Override
	public T queryForObject(String sql, String[] selectionArgs) {
		Cursor cursor = this.queryForCursor(sql, selectionArgs);
		cursor.moveToFirst();
		if (!cursor.isAfterLast()) {
			T object = setBindCursor(cursor);
			if (this.cursor != null) {
				this.cursor.close();
			}
			return object;
		} else {
			return null;
		}
	}

	@Override
	public List<T> queryForList(String sql, String[] selectionArgs) {
		List<T> list = new ArrayList<T>();
		Cursor cursor = this.queryForCursor(sql, selectionArgs);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			list.add(setBindCursor(cursor));
			cursor.moveToNext();
		}
		if (this.cursor != null) {
			this.cursor.close();
		}
		return list;
	}

	@Override
	public void remove(PK id) {
		this.sqllite.delete(table, getPkColumn() + "=?", new String[] { String.valueOf(id) });
	}

	@Override
	@SuppressWarnings("unchecked")
	public PK save(final T object) {
		PK keyId = null;
		if (object != null) {
			keyId = (PK) this.getPrimaryKeyValue(object);

			if (keyId != null) {
				this.sqllite.update(table, getContentValues(object), getPkColumn() + "=?", new String[] { String.valueOf(keyId) });
			} else {
				keyId = (PK) (Long) this.sqllite.insert(table, null, getContentValues(object));
			}
		}
		return keyId;
	}

	protected String getPrimaryKeyFieldName(Object obj) {
		Field fieldlist[] = obj.getClass().getDeclaredFields();
		String fieldName = null;
		for (int i = 0; i < fieldlist.length; i++) {
			Field fld = fieldlist[i];
			if (fld.getName().equals("id") || fld.getName().indexOf("Id") > -1) {
				fieldName = fld.getName();
				break;
			}
		}
		return fieldName;
	}

	protected Object getPrimaryKeyValue(Object obj) {
		String fieldName = getPrimaryKeyFieldName(obj);
		String getterMethod = "get" + Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);

		try {
			return obj.getClass().getMethod(getterMethod).invoke(obj);
		} catch (Exception e) {
			Log.e(Constants.LOG_TAG, clazz.getSimpleName() + "Dao - Could not invoke method '" + getterMethod + "' on " + obj.getClass().getSimpleName());
		}
		return null;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		try {
			String[] tableCreateStmt = getTableCreateStmt();
			if (tableCreateStmt != null && tableCreateStmt.length > 0) {
				for (String stmt : tableCreateStmt) {
					Log.i(Constants.LOG_TAG, clazz.getSimpleName() + "Dao.onCreate() - it is creating the table : " + stmt);
					db.execSQL(stmt);
				}
			}

			String[] indexCreateStmt = getIndexCreateStmt();
			if (indexCreateStmt != null && indexCreateStmt.length > 0) {
				for (String stmt : indexCreateStmt) {
					Log.i(Constants.LOG_TAG, clazz.getSimpleName() + "Dao.onCreate() - it is creating the index : " + stmt);
					db.execSQL(stmt);
				}
			}
		} catch (SQLException e) {
			Log.e(Constants.LOG_TAG, clazz.getSimpleName() + "Dao.onCreate() - it has occured the error while creating the table or the index.", e);
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.i(Constants.LOG_TAG, clazz.getSimpleName() + "Dao.onUpgrade() - it has invoked the table upgrade action.");
	}

	protected abstract T setBindCursor(final Cursor cursor);

	protected abstract ContentValues getContentValues(T param);

	protected abstract String[] getTableCreateStmt();

	protected abstract String[] getIndexCreateStmt();

	protected String getPkColumn() {
		return "_id";
	}
}
