package beamteam.geotalk.db;

import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityDeletionOrUpdateAdapter;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.database.Cursor;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class PhraseDAO_Impl implements PhraseDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfPhrase;

  private final EntityDeletionOrUpdateAdapter __deletionAdapterOfPhrase;

  private final EntityDeletionOrUpdateAdapter __updateAdapterOfPhrase;

  public PhraseDAO_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfPhrase = new EntityInsertionAdapter<Phrase>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `Phrase`(`id`,`phrase`,`location`,`language`,`generalCategory`,`locationSubcategory`) VALUES (?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Phrase value) {
        stmt.bindLong(1, value.id);
        if (value.phrase == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.phrase);
        }
        if (value.location == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.location);
        }
        if (value.language == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.language);
        }
        if (value.generalCategory == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.generalCategory);
        }
        if (value.locationSubcategory == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.locationSubcategory);
        }
      }
    };
    this.__deletionAdapterOfPhrase = new EntityDeletionOrUpdateAdapter<Phrase>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `Phrase` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Phrase value) {
        stmt.bindLong(1, value.id);
      }
    };
    this.__updateAdapterOfPhrase = new EntityDeletionOrUpdateAdapter<Phrase>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `Phrase` SET `id` = ?,`phrase` = ?,`location` = ?,`language` = ?,`generalCategory` = ?,`locationSubcategory` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Phrase value) {
        stmt.bindLong(1, value.id);
        if (value.phrase == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.phrase);
        }
        if (value.location == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.location);
        }
        if (value.language == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.language);
        }
        if (value.generalCategory == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.generalCategory);
        }
        if (value.locationSubcategory == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.locationSubcategory);
        }
        stmt.bindLong(7, value.id);
      }
    };
  }

  @Override
  public void insert(Phrase phrase) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfPhrase.insert(phrase);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(Phrase phrase) {
    __db.beginTransaction();
    try {
      __deletionAdapterOfPhrase.handle(phrase);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(Phrase phrase) {
    __db.beginTransaction();
    try {
      __updateAdapterOfPhrase.handle(phrase);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<Phrase> getAllLocationPhrases(String language, String location) {
    final String _sql = "SELECT * from Phrase WHERE language=? AND location=? ORDER BY id";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (language == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, language);
    }
    _argIndex = 2;
    if (location == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, location);
    }
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfPhrase = _cursor.getColumnIndexOrThrow("phrase");
      final int _cursorIndexOfLocation = _cursor.getColumnIndexOrThrow("location");
      final int _cursorIndexOfLanguage = _cursor.getColumnIndexOrThrow("language");
      final int _cursorIndexOfGeneralCategory = _cursor.getColumnIndexOrThrow("generalCategory");
      final int _cursorIndexOfLocationSubcategory = _cursor.getColumnIndexOrThrow("locationSubcategory");
      final List<Phrase> _result = new ArrayList<Phrase>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Phrase _item;
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        final String _tmpPhrase;
        _tmpPhrase = _cursor.getString(_cursorIndexOfPhrase);
        final String _tmpLanguage;
        _tmpLanguage = _cursor.getString(_cursorIndexOfLanguage);
        final String _tmpGeneralCategory;
        _tmpGeneralCategory = _cursor.getString(_cursorIndexOfGeneralCategory);
        _item = new Phrase(_tmpId,_tmpPhrase,_tmpLanguage,_tmpGeneralCategory);
        _item.location = _cursor.getString(_cursorIndexOfLocation);
        _item.locationSubcategory = _cursor.getString(_cursorIndexOfLocationSubcategory);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Phrase> getLocationPhrasesByCategory(String language, String location,
      String category) {
    final String _sql = "SELECT * from Phrase WHERE language=? AND location=? AND locationSubcategory=? ORDER BY id";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 3);
    int _argIndex = 1;
    if (language == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, language);
    }
    _argIndex = 2;
    if (location == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, location);
    }
    _argIndex = 3;
    if (category == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, category);
    }
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfPhrase = _cursor.getColumnIndexOrThrow("phrase");
      final int _cursorIndexOfLocation = _cursor.getColumnIndexOrThrow("location");
      final int _cursorIndexOfLanguage = _cursor.getColumnIndexOrThrow("language");
      final int _cursorIndexOfGeneralCategory = _cursor.getColumnIndexOrThrow("generalCategory");
      final int _cursorIndexOfLocationSubcategory = _cursor.getColumnIndexOrThrow("locationSubcategory");
      final List<Phrase> _result = new ArrayList<Phrase>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Phrase _item;
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        final String _tmpPhrase;
        _tmpPhrase = _cursor.getString(_cursorIndexOfPhrase);
        final String _tmpLanguage;
        _tmpLanguage = _cursor.getString(_cursorIndexOfLanguage);
        final String _tmpGeneralCategory;
        _tmpGeneralCategory = _cursor.getString(_cursorIndexOfGeneralCategory);
        _item = new Phrase(_tmpId,_tmpPhrase,_tmpLanguage,_tmpGeneralCategory);
        _item.location = _cursor.getString(_cursorIndexOfLocation);
        _item.locationSubcategory = _cursor.getString(_cursorIndexOfLocationSubcategory);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Phrase> getGeneralPhrasesByCategory(String language, String category) {
    final String _sql = "SELECT * from Phrase WHERE language=? AND generalCategory=? ORDER BY id";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (language == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, language);
    }
    _argIndex = 2;
    if (category == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, category);
    }
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfPhrase = _cursor.getColumnIndexOrThrow("phrase");
      final int _cursorIndexOfLocation = _cursor.getColumnIndexOrThrow("location");
      final int _cursorIndexOfLanguage = _cursor.getColumnIndexOrThrow("language");
      final int _cursorIndexOfGeneralCategory = _cursor.getColumnIndexOrThrow("generalCategory");
      final int _cursorIndexOfLocationSubcategory = _cursor.getColumnIndexOrThrow("locationSubcategory");
      final List<Phrase> _result = new ArrayList<Phrase>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Phrase _item;
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        final String _tmpPhrase;
        _tmpPhrase = _cursor.getString(_cursorIndexOfPhrase);
        final String _tmpLanguage;
        _tmpLanguage = _cursor.getString(_cursorIndexOfLanguage);
        final String _tmpGeneralCategory;
        _tmpGeneralCategory = _cursor.getString(_cursorIndexOfGeneralCategory);
        _item = new Phrase(_tmpId,_tmpPhrase,_tmpLanguage,_tmpGeneralCategory);
        _item.location = _cursor.getString(_cursorIndexOfLocation);
        _item.locationSubcategory = _cursor.getString(_cursorIndexOfLocationSubcategory);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
