package com.focuslock.data;

import android.database.Cursor;
import androidx.collection.LongSparseArray;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.StringUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Override;
import java.lang.String;
import java.lang.StringBuilder;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class AppRestrictionPlanDao_Impl implements AppRestrictionPlanDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<AppRestrictionPlanEntity> __insertionAdapterOfAppRestrictionPlanEntity;

  private final EntityInsertionAdapter<AppRestrictionPlanAppCrossRef> __insertionAdapterOfAppRestrictionPlanAppCrossRef;

  private final EntityDeletionOrUpdateAdapter<AppRestrictionPlanEntity> __deletionAdapterOfAppRestrictionPlanEntity;

  private final EntityDeletionOrUpdateAdapter<AppRestrictionPlanEntity> __updateAdapterOfAppRestrictionPlanEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteMappings;

  public AppRestrictionPlanDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfAppRestrictionPlanEntity = new EntityInsertionAdapter<AppRestrictionPlanEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `app_restriction_plans` (`id`,`startMinutes`,`endMinutes`,`daysBitmask`,`isEnabled`) VALUES (nullif(?, 0),?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, AppRestrictionPlanEntity value) {
        stmt.bindLong(1, value.getId());
        stmt.bindLong(2, value.getStartMinutes());
        stmt.bindLong(3, value.getEndMinutes());
        stmt.bindLong(4, value.getDaysBitmask());
        final int _tmp = value.isEnabled() ? 1 : 0;
        stmt.bindLong(5, _tmp);
      }
    };
    this.__insertionAdapterOfAppRestrictionPlanAppCrossRef = new EntityInsertionAdapter<AppRestrictionPlanAppCrossRef>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `app_restriction_plan_apps` (`planId`,`packageName`) VALUES (?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, AppRestrictionPlanAppCrossRef value) {
        stmt.bindLong(1, value.getPlanId());
        if (value.getPackageName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getPackageName());
        }
      }
    };
    this.__deletionAdapterOfAppRestrictionPlanEntity = new EntityDeletionOrUpdateAdapter<AppRestrictionPlanEntity>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `app_restriction_plans` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, AppRestrictionPlanEntity value) {
        stmt.bindLong(1, value.getId());
      }
    };
    this.__updateAdapterOfAppRestrictionPlanEntity = new EntityDeletionOrUpdateAdapter<AppRestrictionPlanEntity>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `app_restriction_plans` SET `id` = ?,`startMinutes` = ?,`endMinutes` = ?,`daysBitmask` = ?,`isEnabled` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, AppRestrictionPlanEntity value) {
        stmt.bindLong(1, value.getId());
        stmt.bindLong(2, value.getStartMinutes());
        stmt.bindLong(3, value.getEndMinutes());
        stmt.bindLong(4, value.getDaysBitmask());
        final int _tmp = value.isEnabled() ? 1 : 0;
        stmt.bindLong(5, _tmp);
        stmt.bindLong(6, value.getId());
      }
    };
    this.__preparedStmtOfDeleteMappings = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM app_restriction_plan_apps WHERE planId = ?";
        return _query;
      }
    };
  }

  @Override
  public long insertPlan(final AppRestrictionPlanEntity entity) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      long _result = __insertionAdapterOfAppRestrictionPlanEntity.insertAndReturnId(entity);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertMappings(final List<AppRestrictionPlanAppCrossRef> mappings) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfAppRestrictionPlanAppCrossRef.insert(mappings);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deletePlan(final AppRestrictionPlanEntity entity) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfAppRestrictionPlanEntity.handle(entity);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updatePlan(final AppRestrictionPlanEntity entity) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfAppRestrictionPlanEntity.handle(entity);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public int deleteMappings(final long planId) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteMappings.acquire();
    int _argIndex = 1;
    _stmt.bindLong(_argIndex, planId);
    __db.beginTransaction();
    try {
      final int _result = _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteMappings.release(_stmt);
    }
  }

  @Override
  public Flow<List<AppRestrictionPlanWithApps>> observePlans() {
    final String _sql = "SELECT * FROM app_restriction_plans ORDER BY id DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, true, new String[]{"app_restriction_plan_apps","whitelisted_apps","app_restriction_plans"}, new Callable<List<AppRestrictionPlanWithApps>>() {
      @Override
      public List<AppRestrictionPlanWithApps> call() throws Exception {
        __db.beginTransaction();
        try {
          final Cursor _cursor = DBUtil.query(__db, _statement, true, null);
          try {
            final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
            final int _cursorIndexOfStartMinutes = CursorUtil.getColumnIndexOrThrow(_cursor, "startMinutes");
            final int _cursorIndexOfEndMinutes = CursorUtil.getColumnIndexOrThrow(_cursor, "endMinutes");
            final int _cursorIndexOfDaysBitmask = CursorUtil.getColumnIndexOrThrow(_cursor, "daysBitmask");
            final int _cursorIndexOfIsEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "isEnabled");
            final LongSparseArray<ArrayList<WhitelistedAppEntity>> _collectionApps = new LongSparseArray<ArrayList<WhitelistedAppEntity>>();
            while (_cursor.moveToNext()) {
              final long _tmpKey = _cursor.getLong(_cursorIndexOfId);
              ArrayList<WhitelistedAppEntity> _tmpAppsCollection = _collectionApps.get(_tmpKey);
              if (_tmpAppsCollection == null) {
                _tmpAppsCollection = new ArrayList<WhitelistedAppEntity>();
                _collectionApps.put(_tmpKey, _tmpAppsCollection);
              }
            }
            _cursor.moveToPosition(-1);
            __fetchRelationshipwhitelistedAppsAscomFocuslockDataWhitelistedAppEntity(_collectionApps);
            final List<AppRestrictionPlanWithApps> _result = new ArrayList<AppRestrictionPlanWithApps>(_cursor.getCount());
            while(_cursor.moveToNext()) {
              final AppRestrictionPlanWithApps _item;
              final AppRestrictionPlanEntity _tmpPlan;
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
              _tmpPlan = new AppRestrictionPlanEntity(_tmpId,_tmpStartMinutes,_tmpEndMinutes,_tmpDaysBitmask,_tmpIsEnabled);
              ArrayList<WhitelistedAppEntity> _tmpAppsCollection_1 = null;
              final long _tmpKey_1 = _cursor.getLong(_cursorIndexOfId);
              _tmpAppsCollection_1 = _collectionApps.get(_tmpKey_1);
              if (_tmpAppsCollection_1 == null) {
                _tmpAppsCollection_1 = new ArrayList<WhitelistedAppEntity>();
              }
              _item = new AppRestrictionPlanWithApps(_tmpPlan,_tmpAppsCollection_1);
              _result.add(_item);
            }
            __db.setTransactionSuccessful();
            return _result;
          } finally {
            _cursor.close();
          }
        } finally {
          __db.endTransaction();
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

  private void __fetchRelationshipwhitelistedAppsAscomFocuslockDataWhitelistedAppEntity(
      final LongSparseArray<ArrayList<WhitelistedAppEntity>> _map) {
    if (_map.isEmpty()) {
      return;
    }
    // check if the size is too big, if so divide;
    if(_map.size() > RoomDatabase.MAX_BIND_PARAMETER_CNT) {
      LongSparseArray<ArrayList<WhitelistedAppEntity>> _tmpInnerMap = new LongSparseArray<ArrayList<WhitelistedAppEntity>>(androidx.room.RoomDatabase.MAX_BIND_PARAMETER_CNT);
      int _tmpIndex = 0;
      int _mapIndex = 0;
      final int _limit = _map.size();
      while(_mapIndex < _limit) {
        _tmpInnerMap.put(_map.keyAt(_mapIndex), _map.valueAt(_mapIndex));
        _mapIndex++;
        _tmpIndex++;
        if(_tmpIndex == RoomDatabase.MAX_BIND_PARAMETER_CNT) {
          __fetchRelationshipwhitelistedAppsAscomFocuslockDataWhitelistedAppEntity(_tmpInnerMap);
          _tmpInnerMap = new LongSparseArray<ArrayList<WhitelistedAppEntity>>(RoomDatabase.MAX_BIND_PARAMETER_CNT);
          _tmpIndex = 0;
        }
      }
      if(_tmpIndex > 0) {
        __fetchRelationshipwhitelistedAppsAscomFocuslockDataWhitelistedAppEntity(_tmpInnerMap);
      }
      return;
    }
    StringBuilder _stringBuilder = StringUtil.newStringBuilder();
    _stringBuilder.append("SELECT `whitelisted_apps`.`packageName` AS `packageName`,`whitelisted_apps`.`label` AS `label`,_junction.`planId` FROM `app_restriction_plan_apps` AS _junction INNER JOIN `whitelisted_apps` ON (_junction.`packageName` = `whitelisted_apps`.`packageName`) WHERE _junction.`planId` IN (");
    final int _inputSize = _map.size();
    StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
    _stringBuilder.append(")");
    final String _sql = _stringBuilder.toString();
    final int _argCount = 0 + _inputSize;
    final RoomSQLiteQuery _stmt = RoomSQLiteQuery.acquire(_sql, _argCount);
    int _argIndex = 1;
    for (int i = 0; i < _map.size(); i++) {
      long _item = _map.keyAt(i);
      _stmt.bindLong(_argIndex, _item);
      _argIndex ++;
    }
    final Cursor _cursor = DBUtil.query(__db, _stmt, false, null);
    try {
      final int _itemKeyIndex = 2; // _junction.planId;
      if (_itemKeyIndex == -1) {
        return;
      }
      final int _cursorIndexOfPackageName = 0;
      final int _cursorIndexOfLabel = 1;
      while(_cursor.moveToNext()) {
        final long _tmpKey = _cursor.getLong(_itemKeyIndex);
        ArrayList<WhitelistedAppEntity> _tmpRelation = _map.get(_tmpKey);
        if (_tmpRelation != null) {
          final WhitelistedAppEntity _item_1;
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
          _item_1 = new WhitelistedAppEntity(_tmpPackageName,_tmpLabel);
          _tmpRelation.add(_item_1);
        }
      }
    } finally {
      _cursor.close();
    }
  }
}
