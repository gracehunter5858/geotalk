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

@SuppressWarnings("unchecked")
public class LanguageDAO_Impl implements LanguageDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfLanguage;

  private final EntityDeletionOrUpdateAdapter __deletionAdapterOfLanguage;

  private final EntityDeletionOrUpdateAdapter __updateAdapterOfLanguage;

  public LanguageDAO_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfLanguage = new EntityInsertionAdapter<Language>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `Language`(`name`) VALUES (?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Language value) {
        if (value.name == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.name);
        }
      }
    };
    this.__deletionAdapterOfLanguage = new EntityDeletionOrUpdateAdapter<Language>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `Language` WHERE `name` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Language value) {
        if (value.name == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.name);
        }
      }
    };
    this.__updateAdapterOfLanguage = new EntityDeletionOrUpdateAdapter<Language>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `Language` SET `name` = ? WHERE `name` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Language value) {
        if (value.name == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.name);
        }
        if (value.name == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.name);
        }
      }
    };
  }

  @Override
  public void insert(Language language) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfLanguage.insert(language);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(Language language) {
    __db.beginTransaction();
    try {
      __deletionAdapterOfLanguage.handle(language);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(Language language) {
    __db.beginTransaction();
    try {
      __updateAdapterOfLanguage.handle(language);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public Language getLanguage(String name) {
    final String _sql = "SELECT * FROM Language WHERE name=?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (name == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, name);
    }
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfName = _cursor.getColumnIndexOrThrow("name");
      final Language _result;
      if(_cursor.moveToFirst()) {
        final String _tmpName;
        _tmpName = _cursor.getString(_cursorIndexOfName);
        _result = new Language(_tmpName);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
