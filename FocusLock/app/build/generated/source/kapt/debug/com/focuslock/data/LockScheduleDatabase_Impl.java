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

  private volatile AppRestrictionPlanDao _appRestrictionPlanDao;

  private volatile ReminderDao _reminderDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(5) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `lock_schedule` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `start_minutes` INTEGER NOT NULL, `end_minutes` INTEGER NOT NULL, `days_bitmask` INTEGER NOT NULL, `is_enabled` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `whitelisted_apps` (`packageName` TEXT NOT NULL, `label` TEXT NOT NULL, PRIMARY KEY(`packageName`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `app_restriction_plans` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `startMinutes` INTEGER NOT NULL, `endMinutes` INTEGER NOT NULL, `daysBitmask` INTEGER NOT NULL, `isEnabled` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `app_restriction_plan_apps` (`planId` INTEGER NOT NULL, `packageName` TEXT NOT NULL, PRIMARY KEY(`planId`, `packageName`), FOREIGN KEY(`planId`) REFERENCES `app_restriction_plans`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE , FOREIGN KEY(`packageName`) REFERENCES `whitelisted_apps`(`packageName`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        _db.execSQL("CREATE INDEX IF NOT EXISTS `index_app_restriction_plan_apps_planId` ON `app_restriction_plan_apps` (`planId`)");
        _db.execSQL("CREATE INDEX IF NOT EXISTS `index_app_restriction_plan_apps_packageName` ON `app_restriction_plan_apps` (`packageName`)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `reminders` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `title` TEXT NOT NULL, `description` TEXT, `anchor_date_time` INTEGER NOT NULL, `recurrence_type` TEXT NOT NULL, `weekly_days_mask` INTEGER NOT NULL, `is_completed` INTEGER NOT NULL, `end_date_time` INTEGER)");
        _db.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS `index_reminders_title` ON `reminders` (`title`)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '6c596a08e93ae74780110bcff84a1f40')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `lock_schedule`");
        _db.execSQL("DROP TABLE IF EXISTS `whitelisted_apps`");
        _db.execSQL("DROP TABLE IF EXISTS `app_restriction_plans`");
        _db.execSQL("DROP TABLE IF EXISTS `app_restriction_plan_apps`");
        _db.execSQL("DROP TABLE IF EXISTS `reminders`");
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
        _db.execSQL("PRAGMA foreign_keys = ON");
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
        final HashMap<String, TableInfo.Column> _columnsAppRestrictionPlans = new HashMap<String, TableInfo.Column>(5);
        _columnsAppRestrictionPlans.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAppRestrictionPlans.put("startMinutes", new TableInfo.Column("startMinutes", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAppRestrictionPlans.put("endMinutes", new TableInfo.Column("endMinutes", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAppRestrictionPlans.put("daysBitmask", new TableInfo.Column("daysBitmask", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAppRestrictionPlans.put("isEnabled", new TableInfo.Column("isEnabled", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysAppRestrictionPlans = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesAppRestrictionPlans = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoAppRestrictionPlans = new TableInfo("app_restriction_plans", _columnsAppRestrictionPlans, _foreignKeysAppRestrictionPlans, _indicesAppRestrictionPlans);
        final TableInfo _existingAppRestrictionPlans = TableInfo.read(_db, "app_restriction_plans");
        if (! _infoAppRestrictionPlans.equals(_existingAppRestrictionPlans)) {
          return new RoomOpenHelper.ValidationResult(false, "app_restriction_plans(com.focuslock.data.AppRestrictionPlanEntity).\n"
                  + " Expected:\n" + _infoAppRestrictionPlans + "\n"
                  + " Found:\n" + _existingAppRestrictionPlans);
        }
        final HashMap<String, TableInfo.Column> _columnsAppRestrictionPlanApps = new HashMap<String, TableInfo.Column>(2);
        _columnsAppRestrictionPlanApps.put("planId", new TableInfo.Column("planId", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAppRestrictionPlanApps.put("packageName", new TableInfo.Column("packageName", "TEXT", true, 2, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysAppRestrictionPlanApps = new HashSet<TableInfo.ForeignKey>(2);
        _foreignKeysAppRestrictionPlanApps.add(new TableInfo.ForeignKey("app_restriction_plans", "CASCADE", "NO ACTION",Arrays.asList("planId"), Arrays.asList("id")));
        _foreignKeysAppRestrictionPlanApps.add(new TableInfo.ForeignKey("whitelisted_apps", "CASCADE", "NO ACTION",Arrays.asList("packageName"), Arrays.asList("packageName")));
        final HashSet<TableInfo.Index> _indicesAppRestrictionPlanApps = new HashSet<TableInfo.Index>(2);
        _indicesAppRestrictionPlanApps.add(new TableInfo.Index("index_app_restriction_plan_apps_planId", false, Arrays.asList("planId"), Arrays.asList("ASC")));
        _indicesAppRestrictionPlanApps.add(new TableInfo.Index("index_app_restriction_plan_apps_packageName", false, Arrays.asList("packageName"), Arrays.asList("ASC")));
        final TableInfo _infoAppRestrictionPlanApps = new TableInfo("app_restriction_plan_apps", _columnsAppRestrictionPlanApps, _foreignKeysAppRestrictionPlanApps, _indicesAppRestrictionPlanApps);
        final TableInfo _existingAppRestrictionPlanApps = TableInfo.read(_db, "app_restriction_plan_apps");
        if (! _infoAppRestrictionPlanApps.equals(_existingAppRestrictionPlanApps)) {
          return new RoomOpenHelper.ValidationResult(false, "app_restriction_plan_apps(com.focuslock.data.AppRestrictionPlanAppCrossRef).\n"
                  + " Expected:\n" + _infoAppRestrictionPlanApps + "\n"
                  + " Found:\n" + _existingAppRestrictionPlanApps);
        }
        final HashMap<String, TableInfo.Column> _columnsReminders = new HashMap<String, TableInfo.Column>(8);
        _columnsReminders.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReminders.put("title", new TableInfo.Column("title", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReminders.put("description", new TableInfo.Column("description", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReminders.put("anchor_date_time", new TableInfo.Column("anchor_date_time", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReminders.put("recurrence_type", new TableInfo.Column("recurrence_type", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReminders.put("weekly_days_mask", new TableInfo.Column("weekly_days_mask", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReminders.put("is_completed", new TableInfo.Column("is_completed", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReminders.put("end_date_time", new TableInfo.Column("end_date_time", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysReminders = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesReminders = new HashSet<TableInfo.Index>(1);
        _indicesReminders.add(new TableInfo.Index("index_reminders_title", true, Arrays.asList("title"), Arrays.asList("ASC")));
        final TableInfo _infoReminders = new TableInfo("reminders", _columnsReminders, _foreignKeysReminders, _indicesReminders);
        final TableInfo _existingReminders = TableInfo.read(_db, "reminders");
        if (! _infoReminders.equals(_existingReminders)) {
          return new RoomOpenHelper.ValidationResult(false, "reminders(com.focuslock.data.ReminderEntity).\n"
                  + " Expected:\n" + _infoReminders + "\n"
                  + " Found:\n" + _existingReminders);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "6c596a08e93ae74780110bcff84a1f40", "c68650a0e7232b98875f264b5c7d8b25");
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
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "lock_schedule","whitelisted_apps","app_restriction_plans","app_restriction_plan_apps","reminders");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    boolean _supportsDeferForeignKeys = android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP;
    try {
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = FALSE");
      }
      super.beginTransaction();
      if (_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA defer_foreign_keys = TRUE");
      }
      _db.execSQL("DELETE FROM `lock_schedule`");
      _db.execSQL("DELETE FROM `whitelisted_apps`");
      _db.execSQL("DELETE FROM `app_restriction_plans`");
      _db.execSQL("DELETE FROM `app_restriction_plan_apps`");
      _db.execSQL("DELETE FROM `reminders`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = TRUE");
      }
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
    _typeConvertersMap.put(AppRestrictionPlanDao.class, AppRestrictionPlanDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(ReminderDao.class, ReminderDao_Impl.getRequiredConverters());
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

  @Override
  public AppRestrictionPlanDao appRestrictionPlanDao() {
    if (_appRestrictionPlanDao != null) {
      return _appRestrictionPlanDao;
    } else {
      synchronized(this) {
        if(_appRestrictionPlanDao == null) {
          _appRestrictionPlanDao = new AppRestrictionPlanDao_Impl(this);
        }
        return _appRestrictionPlanDao;
      }
    }
  }

  @Override
  public ReminderDao reminderDao() {
    if (_reminderDao != null) {
      return _reminderDao;
    } else {
      synchronized(this) {
        if(_reminderDao == null) {
          _reminderDao = new ReminderDao_Impl(this);
        }
        return _reminderDao;
      }
    }
  }
}
