package com.sunland.new_im.db;


import android.net.Uri;

import com.j256.ormlite.misc.BaseDaoEnabled;

/**
 * 返回数据库映射POJO的ContentUri,用于 {@code ContentResolver} 的相关方法, 用于通知数据改变
 * 
 */
public class ContentUriFactory {

	public static final Uri BASE_CONTENT_URI = Uri.parse("content://com.sunlands.staff/");

	private static ContentUriFactory instance = new ContentUriFactory();

	public static ContentUriFactory get() {
		return instance;
	}

	public <T extends BaseDaoEnabled<?, ?>> Uri getUri(Class<T> clazz) {
		String className = clazz.getSimpleName();
		return BASE_CONTENT_URI.buildUpon().appendPath(className)
				.build();
	}

}
