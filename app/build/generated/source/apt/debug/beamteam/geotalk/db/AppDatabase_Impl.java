package beamteam.geotalk.db;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.db.SupportSQLiteOpenHelper.Callback;
import android.arch.persistence.db.SupportSQLiteOpenHelper.Configuration;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.RoomOpenHelper;
import android.arch.persistence.room.RoomOpenHelper.Delegate;
import android.arch.persistence.room.util.TableInfo;
import android.arch.persistence.room.util.TableInfo.Column;
import android.arch.persistence.room.util.TableInfo.ForeignKey;
import android.arch.persistence.room.util.TableInfo.Index;
import java.lang.IllegalStateException;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

@SuppressWarnings("unchecked")
public class AppDatabase_Impl extends AppDatabase {
  private volatile LocationDAO _locationDAO;

  private volatile LanguageDAO _languageDAO;

  private volatile PhraseDAO _phraseDAO;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Location` (`name` TEXT NOT NULL, PRIMARY KEY(`name`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Language` (`name` TEXT NOT NULL, PRIMARY KEY(`name`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Phrase` (`id` INTEGER NOT NULL, `phrase` TEXT, `location` TEXT, `language` TEXT, `generalCategory` TEXT, `locationSubcategory` TEXT, PRIMARY KEY(`id`), FOREIGN KEY(`location`) REFERENCES `Location`(`name`) ON UPDATE NO ACTION ON DELETE NO ACTION , FOREIGN KEY(`language`) REFERENCES `Language`(`name`) ON UPDATE NO ACTION ON DELETE NO ACTION )");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"5869f0a44d46570b17894285bfcbca42\")");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `Location`");
        _db.execSQL("DROP TABLE IF EXISTS `Language`");
        _db.execSQL("DROP TABLE IF EXISTS `Phrase`");
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
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
      protected void validateMigration(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsLocation = new HashMap<String, TableInfo.Column>(1);
        _columnsLocation.put("name", new TableInfo.Column("name", "TEXT", true, 1));
        final HashSet<TableInfo.ForeignKey> _foreignKeysLocation = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesLocation = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoLocation = new TableInfo("Location", _columnsLocation, _foreignKeysLocation, _indicesLocation);
        final TableInfo _existingLocation = TableInfo.read(_db, "Location");
        if (! _infoLocation.equals(_existingLocation)) {
          throw new IllegalStateException("Migration didn't properly handle Location(beamteam.geotalk.db.Location).\n"
                  + " Expected:\n" + _infoLocation + "\n"
                  + " Found:\n" + _existingLocation);
        }
        final HashMap<String, TableInfo.Column> _columnsLanguage = new HashMap<String, TableInfo.Column>(1);
        _columnsLanguage.put("name", new TableInfo.Column("name", "TEXT", true, 1));
        final HashSet<TableInfo.ForeignKey> _foreignKeysLanguage = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesLanguage = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoLanguage = new TableInfo("Language", _columnsLanguage, _foreignKeysLanguage, _indicesLanguage);
        final TableInfo _existingLanguage = TableInfo.read(_db, "Language");
        if (! _infoLanguage.equals(_existingLanguage)) {
          throw new IllegalStateException("Migration didn't properly handle Language(beamteam.geotalk.db.Language).\n"
                  + " Expected:\n" + _infoLanguage + "\n"
                  + " Found:\n" + _existingLanguage);
        }
        final HashMap<String, TableInfo.Column> _columnsPhrase = new HashMap<String, TableInfo.Column>(6);
        _columnsPhrase.put("id", new TableInfo.Column("id", "INTEGER", true, 1));
        _columnsPhrase.put("phrase", new TableInfo.Column("phrase", "TEXT", false, 0));
        _columnsPhrase.put("location", new TableInfo.Column("location", "TEXT", false, 0));
        _columnsPhrase.put("language", new TableInfo.Column("language", "TEXT", false, 0));
        _columnsPhrase.put("generalCategory", new TableInfo.Column("generalCategory", "TEXT", false, 0));
        _columnsPhrase.put("locationSubcategory", new TableInfo.Column("locationSubcategory", "TEXT", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysPhrase = new HashSet<TableInfo.ForeignKey>(2);
        _foreignKeysPhrase.add(new TableInfo.ForeignKey("Location", "NO ACTION", "NO ACTION",Arrays.asList("location"), Arrays.asList("name")));
        _foreignKeysPhrase.add(new TableInfo.ForeignKey("Language", "NO ACTION", "NO ACTION",Arrays.asList("language"), Arrays.asList("name")));
        final HashSet<TableInfo.Index> _indicesPhrase = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoPhrase = new TableInfo("Phrase", _columnsPhrase, _foreignKeysPhrase, _indicesPhrase);
        final TableInfo _existingPhrase = TableInfo.read(_db, "Phrase");
        if (! _infoPhrase.equals(_existingPhrase)) {
          throw new IllegalStateException("Migration didn't properly handle Phrase(beamteam.geotalk.db.Phrase).\n"
                  + " Expected:\n" + _infoPhrase + "\n"
                  + " Found:\n" + _existingPhrase);
        }
      }
    }, "5869f0a44d46570b17894285bfcbca42", "7b5695d0868cbad2cfb803bac65f5ba1");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    return new InvalidationTracker(this, "Location","Language","Phrase");
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
      _db.execSQL("DELETE FROM `Phrase`");
      _db.execSQL("DELETE FROM `Location`");
      _db.execSQL("DELETE FROM `Language`");
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
  public LocationDAO getLocationDAO() {
    if (_locationDAO != null) {
      return _locationDAO;
    } else {
      synchronized(this) {
        if(_locationDAO == null) {
          _locationDAO = new LocationDAO_Impl(this);
        }
        return _locationDAO;
      }
    }
  }

  @Override
  public LanguageDAO getLanguageDAO() {
    if (_languageDAO != null) {
      return _languageDAO;
    } else {
      synchronized(this) {
        if(_languageDAO == null) {
          _languageDAO = new LanguageDAO_Impl(this);
        }
        return _languageDAO;
      }
    }
  }

  @Override
  public PhraseDAO getPhraseDAO() {
    if (_phraseDAO != null) {
      return _phraseDAO;
    } else {
      synchronized(this) {
        if(_phraseDAO == null) {
          _phraseDAO = new PhraseDAO_Impl(this);
        }
        return _phraseDAO;
      }
    }
  }
}
