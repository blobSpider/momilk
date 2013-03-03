package net.rubywork.feedingclock.dao;

import java.io.Serializable;
import java.util.List;

import android.database.Cursor;

public interface GenericDao<T, PK extends Serializable> {
	public Cursor queryForCursor(String sql, String[] selectionArgs);

	public T queryForObject(String sql, String[] selectionArgs);

	public List<T> queryForList(String sql, String[] selectionArgs);

	public void remove(PK id);

	public PK save(final T object);

	// public List<T> getAll();
	// public T get(PK id);
	// public PK save(T object);
	// public void remove(PK id);

}