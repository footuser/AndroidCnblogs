package com.arlen.cnblogs.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.arlen.cnblogs.utils.AppConfig;

public class CookieDao implements BaseDao {

	private DbOpenHelper helper = null;

	public CookieDao(Context context) {
		helper = new DbOpenHelper(context, AppConfig.DATABASE_NAME, null,
				AppConfig.DATABASE_VERSION);
	}

	@Override
	public boolean addData(Object[] params) {
		boolean flag = false;
		SQLiteDatabase database = null;

		try {
			String sql = "insert into cookies(userName, cookie) values (?, ?)";
			database = helper.getWritableDatabase();
			database.execSQL(sql, params);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (database != null) {
				database.close();
			}
		}

		return flag;
	}

	@Override
	public boolean deleteData(Object[] params) {
		return false;
	}

	@Override
	public boolean updateData(Object[] params) {
		return false;
	}

	@Override
	public boolean cleanData(Object[] params) {
		return false;
	}

	@Override
	public Map<String, String> viewData(String[] selectionArgs) {
		Map<String, String> map = new HashMap<String, String>();
		SQLiteDatabase database = null;

		try {
			String sql = "select * from cookies where userName = ?";

			database = helper.getReadableDatabase();
			Cursor cursor = database.rawQuery(sql, selectionArgs);

			while (cursor.moveToNext()) {
				for (int i = 0; i < cursor.getColumnCount(); i++) {
					String key = cursor.getColumnName(i);
					String value = cursor.getString(cursor.getColumnIndex(key));
					if (value == null) {
						value = "";
					}
					map.put(key, value);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (database != null) {
				database.close();
			}
		}

		return map;
	}

	@Override
	public List<Map<String, String>> listData(String[] selectionArgs) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		SQLiteDatabase database = null;
		try {
			String sql = "select * from cookies";
			database = helper.getReadableDatabase();
			Cursor cursor = database.rawQuery(sql, selectionArgs);

			while (cursor.moveToNext()) {
				Map<String, String> map = new HashMap<String, String>();
				for (int i = 0; i < cursor.getColumnCount(); i++) {
					String key = cursor.getColumnName(i);
					String value = cursor.getString(cursor.getColumnIndex(key));
					if (value == null) {
						value = "";
					}
					map.put(key, value);
				}
				list.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (database != null) {
				database.close();
			}
		}
		return list;
	}

}
