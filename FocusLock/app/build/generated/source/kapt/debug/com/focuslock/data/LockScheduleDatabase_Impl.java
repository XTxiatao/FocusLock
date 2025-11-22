package com.focuslock.data;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.RoomOpenHelper.ValidationResult;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class LockScheduleDatabase_Impl extends LockScheduleDatabase {
  private volatile LockScheduleDao _lockScheduleDao;

  private volatile WhitelistedAppDao _whitelistedAppDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(2) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `lock_schedule` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `start_minutes` INTEGER NOT NULL, `end_minutes` INTEGER NOT NULL, `days_bitmask` INTEGER NOT NULL, `is_enabled` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `whitelisted_apps` (`packageName` TEXT NOT NULL, `label` TEXT NOT NULL, PRIMARY KEY(`packageName`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '5b1f4613ff74903768bad4f3a2e021a4')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `lock_schedule`");
        _db.execSQL("DROP TABLE IF EXISTS `whitelisted_apps`");
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onDestructiveMigration(_db);
          }
        }
      }

      @Override
      public void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      public void onPreMigrate(SupportSQLiteDatabase _db) {
        DBUtil.dropFtsSyncTriggers(_db);
      }

      @Override
      public void onPostMigrate(SupportSQLiteDatabase _db) {
      }

      @Override
      public RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsLockSchedule = new HashMap<String, TableInfo.Column>(5);
        _columnsLockSchedule.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLockSchedule.put("start_minutes", new TableInfo.Column("start_minutes", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLockSchedule.put("end_minutes", new TableInfo.Column("end_minutes", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLockSchedule.put("days_bitmask", new TableInfo.Column("days_bitmask", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLockSchedule.put("is_enabled", new TableInfo.Column("is_enabled", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysLockSchedule = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesLockSchedule = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoLockSchedule = new TableInfo("lock_schedule", _columnsLockSchedule, _foreignKeysLockSchedule, _indicesLockSchedule);
        final TableInfo _existingLockSchedule = TableInfo.read(_db, "lock_schedule");
        if (! _infoLockSchedule.equals(_existingLockSchedule)) {
          return new RoomOpenHelper.ValidationResult(false, "lock_schedule(com.focuslock.data.LockScheduleEntity).\n"
                  + " Expected:\n" + _infoLockSchedule + "\n"
                  + " Found:\n" + _existingLockSchedule);
        }
        final HashMap<String, TableInfo.Column> _columnsWhitelistedApps = new HashMap<String, TableInfo.Column>(2);
        _columnsWhitelistedApps.put("packageName", new TableInfo.Column("packageName", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWhitelistedApps.put("label", new TableInfo.Column("label", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysWhitelistedApps = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesWhitelistedApps = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoWhitelistedApps = new TableInfo("whitelisted_apps", _columnsWhitelistedApps, _foreignKeysWhitelistedApps, _indicesWhitelistedApps);
        final TableInfo _existingWhitelistedApps = TableInfo.read(_db, "whitelisted_apps");
        if (! _infoWhitelistedApps.equals(_existingWhitelistedApps)) {
          return new RoomOpenHelper.ValidationResult(false, "whitelisted_apps(com.focuslock.data.WhitelistedAppEntity).\n"
                  + " Expected:\n" + _infoWhitelistedApps + "\n"
                  + " Found:\n" + _existingWhitelistedApps);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "5b1f4613ff74903768bad4f3a2e021a4", "552d4feac33cec04583383daf0cb928c");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "lock_schedule","whitelisted_apps");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `lock_schedule`");
      _db.execSQL("DELETE FROM `whitelisted_apps`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(LockScheduleDao.class, LockScheduleDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(WhitelistedAppDao.class, WhitelistedAppDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  public List<Migration> getAutoMigrations(
      @NonNull Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecsMap) {
    return Arrays.asList();
  }

  @Override
  public LockScheduleDao lockScheduleDao() {
    if (_lockScheduleDao != null) {
      return _lockScheduleDao;
    } else {
      synchronized(this) {
        if(_lockScheduleDao == null) {
          _lockScheduleDao = new LockScheduleDao_Impl(this);
        }
        return _lockScheduleDao;
      }
    }
  }

  @Override
  public WhitelistedAppDao whitelistedAppDao() {
    if (_whitelistedAppDao != null) {
      return _whitelistedAppDao;
    } else {
      synchronized(this) {
        if(_whitelistedAppDao == null) {
          _whitelistedAppDao = new WhitelistedAppDao_Impl(this);
        }
        return _whitelistedAppDao;
      }
    }
  }
}
