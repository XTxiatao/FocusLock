package com.focuslock.data;

import android.database.Cursor;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
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
public final class LockScheduleDao_Impl implements LockScheduleDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<LockScheduleEntity> __insertionAdapterOfLockScheduleEntity;

  private final EntityDeletionOrUpdateAdapter<LockScheduleEntity> __deletionAdapterOfLockScheduleEntity;

  private final EntityDeletionOrUpdateAdapter<LockScheduleEntity> __updateAdapterOfLockScheduleEntity;

  public LockScheduleDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfLockScheduleEntity = new EntityInsertionAdapter<LockScheduleEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `lock_schedule` (`id`,`start_minutes`,`end_minutes`,`days_bitmask`,`is_enabled`) VALUES (nullif(?, 0),?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, LockScheduleEntity value) {
        stmt.bindLong(1, value.getId());
        stmt.bindLong(2, value.getStartMinutes());
        stmt.bindLong(3, value.getEndMinutes());
        stmt.bindLong(4, value.getDaysBitmask());
        final int _tmp = value.isEnabled() ? 1 : 0;
        stmt.bindLong(5, _tmp);
      }
    };
    this.__deletionAdapterOfLockScheduleEntity = new EntityDeletionOrUpdateAdapter<LockScheduleEntity>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `lock_schedule` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, LockScheduleEntity value) {
        stmt.bindLong(1, value.getId());
      }
    };
    this.__updateAdapterOfLockScheduleEntity = new EntityDeletionOrUpdateAdapter<LockScheduleEntity>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `lock_schedule` SET `id` = ?,`start_minutes` = ?,`end_minutes` = ?,`days_bitmask` = ?,`is_enabled` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, LockScheduleEntity value) {
        stmt.bindLong(1, value.getId());
        stmt.bindLong(2, value.getStartMinutes());
        stmt.bindLong(3, value.getEndMinutes());
        stmt.bindLong(4, value.getDaysBitmask());
        final int _tmp = value.isEnabled() ? 1 : 0;
        stmt.bindLong(5, _tmp);
        stmt.bindLong(6, value.getId());
      }
    };
  }

  @Override
  public long insert(final LockScheduleEntity arg0) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      long _result = __insertionAdapterOfLockScheduleEntity.insertAndReturnId(arg0);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final LockScheduleEntity arg0) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfLockScheduleEntity.handle(arg0);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final LockScheduleEntity arg0) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfLockScheduleEntity.handle(arg0);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public Flow<List<LockScheduleEntity>> getAll() {
    final String _sql = "SELECT * FROM lock_schedule ORDER BY id DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[]{"lock_schedule"}, new Callable<List<LockScheduleEntity>>() {
      @Override
      public List<LockScheduleEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfStartMinutes = CursorUtil.getColumnIndexOrThrow(_cursor, "start_minutes");
          final int _cursorIndexOfEndMinutes = CursorUtil.getColumnIndexOrThrow(_cursor, "end_minutes");
          final int _cursorIndexOfDaysBitmask = CursorUtil.getColumnIndexOrThrow(_cursor, "days_bitmask");
          final int _cursorIndexOfIsEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "is_enabled");
          final List<LockScheduleEntity> _result = new ArrayList<LockScheduleEntity>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final LockScheduleEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final int _tmpStartMinutes;
            _tmpStartMinutes = _cursor.getInt(_cursorIndexOfStartMinutes);
            final int _tmpEndMinutes;
            _tmpEndMinutes = _cursor.getInt(_cursorIndexOfEndMinutes);
            final int _tmpDaysBitmask;
            _tmpDaysBitmask = _cursor.getInt(_cursorIndexOfDaysBitmask);
            final boolean _tmpIsEnabled;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsEnabled);
            _tmpIsEnabled = _tmp != 0;
            _item = new LockScheduleEntity(_tmpId,_tmpStartMinutes,_tmpEndMinutes,_tmpDaysBitmask,_tmpIsEnabled);
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

  @Override
  public Flow<List<LockScheduleEntity>> getEnabled() {
    final String _sql = "SELECT * FROM lock_schedule WHERE is_enabled = 1 ORDER BY id DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[]{"lock_schedule"}, new Callable<List<LockScheduleEntity>>() {
      @Override
      public List<LockScheduleEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfStartMinutes = CursorUtil.getColumnIndexOrThrow(_cursor, "start_minutes");
          final int _cursorIndexOfEndMinutes = CursorUtil.getColumnIndexOrThrow(_cursor, "end_minutes");
          final int _cursorIndexOfDaysBitmask = CursorUtil.getColumnIndexOrThrow(_cursor, "days_bitmask");
          final int _cursorIndexOfIsEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "is_enabled");
          final List<LockScheduleEntity> _result = new ArrayList<LockScheduleEntity>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final LockScheduleEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final int _tmpStartMinutes;
            _tmpStartMinutes = _cursor.getInt(_cursorIndexOfStartMinutes);
            final int _tmpEndMinutes;
            _tmpEndMinutes = _cursor.getInt(_cursorIndexOfEndMinutes);
            final int _tmpDaysBitmask;
            _tmpDaysBitmask = _cursor.getInt(_cursorIndexOfDaysBitmask);
            final boolean _tmpIsEnabled;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsEnabled);
            _tmpIsEnabled = _tmp != 0;
            _item = new LockScheduleEntity(_tmpId,_tmpStartMinutes,_tmpEndMinutes,_tmpDaysBitmask,_tmpIsEnabled);
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
