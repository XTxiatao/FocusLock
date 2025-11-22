package com.focuslock.data;

import android.database.Cursor;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class WhitelistedAppDao_Impl implements WhitelistedAppDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<WhitelistedAppEntity> __insertionAdapterOfWhitelistedAppEntity;

  private final SharedSQLiteStatement __preparedStmtOfClearAll;

  private final SharedSQLiteStatement __preparedStmtOfDeleteByPackage;

  public WhitelistedAppDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfWhitelistedAppEntity = new EntityInsertionAdapter<WhitelistedAppEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `whitelisted_apps` (`packageName`,`label`) VALUES (?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, WhitelistedAppEntity value) {
        if (value.getPackageName() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getPackageName());
        }
        if (value.getLabel() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getLabel());
        }
      }
    };
    this.__preparedStmtOfClearAll = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM whitelisted_apps";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteByPackage = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM whitelisted_apps WHERE packageName = ?";
        return _query;
      }
    };
  }

  @Override
  public void insert(final WhitelistedAppEntity app) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfWhitelistedAppEntity.insert(app);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void clearAll() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfClearAll.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfClearAll.release(_stmt);
    }
  }

  @Override
  public void deleteByPackage(final String packageName) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteByPackage.acquire();
    int _argIndex = 1;
    if (packageName == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, packageName);
    }
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteByPackage.release(_stmt);
    }
  }

  @Override
  public Flow<List<WhitelistedAppEntity>> getAll() {
    final String _sql = "SELECT * FROM whitelisted_apps ORDER BY label ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[]{"whitelisted_apps"}, new Callable<List<WhitelistedAppEntity>>() {
      @Override
      public List<WhitelistedAppEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfPackageName = CursorUtil.getColumnIndexOrThrow(_cursor, "packageName");
          final int _cursorIndexOfLabel = CursorUtil.getColumnIndexOrThrow(_cursor, "label");
          final List<WhitelistedAppEntity> _result = new ArrayList<WhitelistedAppEntity>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final WhitelistedAppEntity _item;
            final String _tmpPackageName;
            if (_cursor.isNull(_cursorIndexOfPackageName)) {
              _tmpPackageName = null;
            } else {
              _tmpPackageName = _cursor.getString(_cursorIndexOfPackageName);
            }
            final String _tmpLabel;
            if (_cursor.isNull(_cursorIndexOfLabel)) {
              _tmpLabel = null;
            } else {
              _tmpLabel = _cursor.getString(_cursorIndexOfLabel);
            }
            _item = new WhitelistedAppEntity(_tmpPackageName,_tmpLabel);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
