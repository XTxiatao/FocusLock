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
public final class ReminderDao_Impl implements ReminderDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<ReminderEntity> __insertionAdapterOfReminderEntity;

  private final EntityDeletionOrUpdateAdapter<ReminderEntity> __deletionAdapterOfReminderEntity;

  private final EntityDeletionOrUpdateAdapter<ReminderEntity> __updateAdapterOfReminderEntity;

  public ReminderDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfReminderEntity = new EntityInsertionAdapter<ReminderEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `reminders` (`id`,`title`,`description`,`anchor_date_time`,`recurrence_type`,`weekly_days_mask`,`is_active`,`is_completed`,`is_archived`,`created_at`,`updated_at`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, ReminderEntity value) {
        stmt.bindLong(1, value.getId());
        if (value.getTitle() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getTitle());
        }
        if (value.getDescription() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getDescription());
        }
        stmt.bindLong(4, value.getAnchorDateTime());
        if (value.getRecurrenceType() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getRecurrenceType());
        }
        stmt.bindLong(6, value.getWeeklyDaysMask());
        final int _tmp = value.isActive() ? 1 : 0;
        stmt.bindLong(7, _tmp);
        final int _tmp_1 = value.isCompleted() ? 1 : 0;
        stmt.bindLong(8, _tmp_1);
        final int _tmp_2 = value.isArchived() ? 1 : 0;
        stmt.bindLong(9, _tmp_2);
        stmt.bindLong(10, value.getCreatedAt());
        stmt.bindLong(11, value.getUpdatedAt());
      }
    };
    this.__deletionAdapterOfReminderEntity = new EntityDeletionOrUpdateAdapter<ReminderEntity>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `reminders` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, ReminderEntity value) {
        stmt.bindLong(1, value.getId());
      }
    };
    this.__updateAdapterOfReminderEntity = new EntityDeletionOrUpdateAdapter<ReminderEntity>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `reminders` SET `id` = ?,`title` = ?,`description` = ?,`anchor_date_time` = ?,`recurrence_type` = ?,`weekly_days_mask` = ?,`is_active` = ?,`is_completed` = ?,`is_archived` = ?,`created_at` = ?,`updated_at` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, ReminderEntity value) {
        stmt.bindLong(1, value.getId());
        if (value.getTitle() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getTitle());
        }
        if (value.getDescription() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getDescription());
        }
        stmt.bindLong(4, value.getAnchorDateTime());
        if (value.getRecurrenceType() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getRecurrenceType());
        }
        stmt.bindLong(6, value.getWeeklyDaysMask());
        final int _tmp = value.isActive() ? 1 : 0;
        stmt.bindLong(7, _tmp);
        final int _tmp_1 = value.isCompleted() ? 1 : 0;
        stmt.bindLong(8, _tmp_1);
        final int _tmp_2 = value.isArchived() ? 1 : 0;
        stmt.bindLong(9, _tmp_2);
        stmt.bindLong(10, value.getCreatedAt());
        stmt.bindLong(11, value.getUpdatedAt());
        stmt.bindLong(12, value.getId());
      }
    };
  }

  @Override
  public long insert(final ReminderEntity reminder) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      long _result = __insertionAdapterOfReminderEntity.insertAndReturnId(reminder);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final ReminderEntity reminder) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfReminderEntity.handle(reminder);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final ReminderEntity reminder) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfReminderEntity.handle(reminder);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public Flow<List<ReminderEntity>> observeAll() {
    final String _sql = "SELECT * FROM reminders";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[]{"reminders"}, new Callable<List<ReminderEntity>>() {
      @Override
      public List<ReminderEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfAnchorDateTime = CursorUtil.getColumnIndexOrThrow(_cursor, "anchor_date_time");
          final int _cursorIndexOfRecurrenceType = CursorUtil.getColumnIndexOrThrow(_cursor, "recurrence_type");
          final int _cursorIndexOfWeeklyDaysMask = CursorUtil.getColumnIndexOrThrow(_cursor, "weekly_days_mask");
          final int _cursorIndexOfIsActive = CursorUtil.getColumnIndexOrThrow(_cursor, "is_active");
          final int _cursorIndexOfIsCompleted = CursorUtil.getColumnIndexOrThrow(_cursor, "is_completed");
          final int _cursorIndexOfIsArchived = CursorUtil.getColumnIndexOrThrow(_cursor, "is_archived");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "created_at");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updated_at");
          final List<ReminderEntity> _result = new ArrayList<ReminderEntity>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final ReminderEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpTitle;
            if (_cursor.isNull(_cursorIndexOfTitle)) {
              _tmpTitle = null;
            } else {
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final long _tmpAnchorDateTime;
            _tmpAnchorDateTime = _cursor.getLong(_cursorIndexOfAnchorDateTime);
            final String _tmpRecurrenceType;
            if (_cursor.isNull(_cursorIndexOfRecurrenceType)) {
              _tmpRecurrenceType = null;
            } else {
              _tmpRecurrenceType = _cursor.getString(_cursorIndexOfRecurrenceType);
            }
            final int _tmpWeeklyDaysMask;
            _tmpWeeklyDaysMask = _cursor.getInt(_cursorIndexOfWeeklyDaysMask);
            final boolean _tmpIsActive;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsActive);
            _tmpIsActive = _tmp != 0;
            final boolean _tmpIsCompleted;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsCompleted);
            _tmpIsCompleted = _tmp_1 != 0;
            final boolean _tmpIsArchived;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfIsArchived);
            _tmpIsArchived = _tmp_2 != 0;
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _item = new ReminderEntity(_tmpId,_tmpTitle,_tmpDescription,_tmpAnchorDateTime,_tmpRecurrenceType,_tmpWeeklyDaysMask,_tmpIsActive,_tmpIsCompleted,_tmpIsArchived,_tmpCreatedAt,_tmpUpdatedAt);
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
  public int countByTitle(final String title) {
    final String _sql = "SELECT COUNT(*) FROM reminders WHERE LOWER(title) = LOWER(?)";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (title == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, title);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _result;
      if(_cursor.moveToFirst()) {
        _result = _cursor.getInt(0);
      } else {
        _result = 0;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public int countByTitleExcluding(final String title, final long excludeId) {
    final String _sql = "SELECT COUNT(*) FROM reminders WHERE LOWER(title) = LOWER(?) AND id != ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (title == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, title);
    }
    _argIndex = 2;
    _statement.bindLong(_argIndex, excludeId);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _result;
      if(_cursor.moveToFirst()) {
        _result = _cursor.getInt(0);
      } else {
        _result = 0;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
