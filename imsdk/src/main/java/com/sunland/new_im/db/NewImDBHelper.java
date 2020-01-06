package com.sunland.new_im.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.misc.TransactionManager;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.concurrent.Callable;

/**
 * Created by fengd on 2018/8/24.
 */

public class NewImDBHelper extends OrmLiteSqliteOpenHelper {

    final static int VERSION_INIT = 1;

    final static int VERSION_URGED_MSG = 2;


    private static final int DB_EMOJI = 4;

    private static final int DB_RETURN_ORDER = 5;

    private static final int DB_VOICE = 6;

    public NewImDBHelper(Context context, long imId) {
        super(context.getApplicationContext(), "im" + imId + ".db", null, DB_VOICE);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            TableUtils.createTableIfNotExists(connectionSource, Draft.class);
            TableUtils.createTableIfNotExists(connectionSource, Message.class);
            TableUtils.createTableIfNotExists(connectionSource, QueuedCmd.class);
            TableUtils.createTableIfNotExists(connectionSource, ReadReply.class);
            TableUtils.createTableIfNotExists(connectionSource, Session.class);
            TableUtils.createTableIfNotExists(connectionSource, TmpMessage.class);

            TableUtils.createTableIfNotExists(connectionSource, Emoji.class);
        } catch (SQLException e) {
            //throw new IllegalStateException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int oldVersion, int newVersion) {
//        if (oldVersion == VERSION_INIT && newVersion == VERSION_URGED_MSG) {
//            reCreateTable(connectionSource, Message.class);
//            reCreateTable(connectionSource, Session.class);
//        }
//
//        if (newVersion >= DB_EMOJI) {
//            reCreateTable(connectionSource, Emoji.class); //收藏表情建表
//            // IM外显异常工作状态更改session表
//            reCreateTable(connectionSource, Session.class);
//        }
//        reCreateTable();
        reCreateTable(connectionSource, Message.class);
        reCreateTable(connectionSource, Session.class);
        reCreateTable(connectionSource, Emoji.class);
        reCreateTable(connectionSource, TmpMessage.class);
        reCreateTable(connectionSource, ReadReply.class);
        reCreateTable(connectionSource, QueuedCmd.class);
        reCreateTable(connectionSource, Draft.class);

    }

    public <V> V callInTransaction(Callable<V> callable) throws SQLException {
        return TransactionManager.callInTransaction(getConnectionSource(),
                callable);
    }

    private static <T> void reCreateTable(ConnectionSource connectionSource, Class<T> clazz) {
        try {
            TableUtils.dropTable(connectionSource, clazz, true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            TableUtils.createTableIfNotExists(connectionSource, clazz);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
