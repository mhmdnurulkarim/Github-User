package com.mhmdnurulkarim.core.data.source.local.room;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.mhmdnurulkarim.core.data.source.local.entity.UserEntity;
import java.lang.Boolean;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class UserDao_Impl implements UserDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<UserEntity> __insertionAdapterOfUserEntity;

  private final EntityDeletionOrUpdateAdapter<UserEntity> __deletionAdapterOfUserEntity;

  public UserDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfUserEntity = new EntityInsertionAdapter<UserEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `user` (`id`,`username`,`name`,`following`,`followers`,`repository`,`avatar`,`isFavorite`) VALUES (?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final UserEntity entity) {
        if (entity.getId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindLong(1, entity.getId());
        }
        if (entity.getLogin() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getLogin());
        }
        if (entity.getName() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getName());
        }
        if (entity.getFollowing() == null) {
          statement.bindNull(4);
        } else {
          statement.bindLong(4, entity.getFollowing());
        }
        if (entity.getFollowers() == null) {
          statement.bindNull(5);
        } else {
          statement.bindLong(5, entity.getFollowers());
        }
        if (entity.getPublicRepos() == null) {
          statement.bindNull(6);
        } else {
          statement.bindLong(6, entity.getPublicRepos());
        }
        if (entity.getAvatarUrl() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getAvatarUrl());
        }
        final Integer _tmp = entity.isFavorite() == null ? null : (entity.isFavorite() ? 1 : 0);
        if (_tmp == null) {
          statement.bindNull(8);
        } else {
          statement.bindLong(8, _tmp);
        }
      }
    };
    this.__deletionAdapterOfUserEntity = new EntityDeletionOrUpdateAdapter<UserEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `user` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final UserEntity entity) {
        if (entity.getId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindLong(1, entity.getId());
        }
      }
    };
  }

  @Override
  public Object insertFavoriteUser(final UserEntity user,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfUserEntity.insert(user);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteFavoriteUser(final UserEntity user,
      final Continuation<? super Integer> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        int _total = 0;
        __db.beginTransaction();
        try {
          _total += __deletionAdapterOfUserEntity.handle(user);
          __db.setTransactionSuccessful();
          return _total;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<UserEntity>> getFavoriteListUser() {
    final String _sql = "SELECT * from user ORDER BY username ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"user"}, new Callable<List<UserEntity>>() {
      @Override
      @NonNull
      public List<UserEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfLogin = CursorUtil.getColumnIndexOrThrow(_cursor, "username");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfFollowing = CursorUtil.getColumnIndexOrThrow(_cursor, "following");
          final int _cursorIndexOfFollowers = CursorUtil.getColumnIndexOrThrow(_cursor, "followers");
          final int _cursorIndexOfPublicRepos = CursorUtil.getColumnIndexOrThrow(_cursor, "repository");
          final int _cursorIndexOfAvatarUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "avatar");
          final int _cursorIndexOfIsFavorite = CursorUtil.getColumnIndexOrThrow(_cursor, "isFavorite");
          final List<UserEntity> _result = new ArrayList<UserEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final UserEntity _item;
            final Integer _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getInt(_cursorIndexOfId);
            }
            final String _tmpLogin;
            if (_cursor.isNull(_cursorIndexOfLogin)) {
              _tmpLogin = null;
            } else {
              _tmpLogin = _cursor.getString(_cursorIndexOfLogin);
            }
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final Integer _tmpFollowing;
            if (_cursor.isNull(_cursorIndexOfFollowing)) {
              _tmpFollowing = null;
            } else {
              _tmpFollowing = _cursor.getInt(_cursorIndexOfFollowing);
            }
            final Integer _tmpFollowers;
            if (_cursor.isNull(_cursorIndexOfFollowers)) {
              _tmpFollowers = null;
            } else {
              _tmpFollowers = _cursor.getInt(_cursorIndexOfFollowers);
            }
            final Integer _tmpPublicRepos;
            if (_cursor.isNull(_cursorIndexOfPublicRepos)) {
              _tmpPublicRepos = null;
            } else {
              _tmpPublicRepos = _cursor.getInt(_cursorIndexOfPublicRepos);
            }
            final String _tmpAvatarUrl;
            if (_cursor.isNull(_cursorIndexOfAvatarUrl)) {
              _tmpAvatarUrl = null;
            } else {
              _tmpAvatarUrl = _cursor.getString(_cursorIndexOfAvatarUrl);
            }
            final Boolean _tmpIsFavorite;
            final Integer _tmp;
            if (_cursor.isNull(_cursorIndexOfIsFavorite)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getInt(_cursorIndexOfIsFavorite);
            }
            _tmpIsFavorite = _tmp == null ? null : _tmp != 0;
            _item = new UserEntity(_tmpId,_tmpLogin,_tmpName,_tmpFollowing,_tmpFollowers,_tmpPublicRepos,_tmpAvatarUrl,_tmpIsFavorite);
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
  public Flow<UserEntity> getFavoriteDetailUser(final String username) {
    final String _sql = "SELECT * FROM user WHERE username = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, username);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"user"}, new Callable<UserEntity>() {
      @Override
      @NonNull
      public UserEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfLogin = CursorUtil.getColumnIndexOrThrow(_cursor, "username");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfFollowing = CursorUtil.getColumnIndexOrThrow(_cursor, "following");
          final int _cursorIndexOfFollowers = CursorUtil.getColumnIndexOrThrow(_cursor, "followers");
          final int _cursorIndexOfPublicRepos = CursorUtil.getColumnIndexOrThrow(_cursor, "repository");
          final int _cursorIndexOfAvatarUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "avatar");
          final int _cursorIndexOfIsFavorite = CursorUtil.getColumnIndexOrThrow(_cursor, "isFavorite");
          final UserEntity _result;
          if (_cursor.moveToFirst()) {
            final Integer _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getInt(_cursorIndexOfId);
            }
            final String _tmpLogin;
            if (_cursor.isNull(_cursorIndexOfLogin)) {
              _tmpLogin = null;
            } else {
              _tmpLogin = _cursor.getString(_cursorIndexOfLogin);
            }
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final Integer _tmpFollowing;
            if (_cursor.isNull(_cursorIndexOfFollowing)) {
              _tmpFollowing = null;
            } else {
              _tmpFollowing = _cursor.getInt(_cursorIndexOfFollowing);
            }
            final Integer _tmpFollowers;
            if (_cursor.isNull(_cursorIndexOfFollowers)) {
              _tmpFollowers = null;
            } else {
              _tmpFollowers = _cursor.getInt(_cursorIndexOfFollowers);
            }
            final Integer _tmpPublicRepos;
            if (_cursor.isNull(_cursorIndexOfPublicRepos)) {
              _tmpPublicRepos = null;
            } else {
              _tmpPublicRepos = _cursor.getInt(_cursorIndexOfPublicRepos);
            }
            final String _tmpAvatarUrl;
            if (_cursor.isNull(_cursorIndexOfAvatarUrl)) {
              _tmpAvatarUrl = null;
            } else {
              _tmpAvatarUrl = _cursor.getString(_cursorIndexOfAvatarUrl);
            }
            final Boolean _tmpIsFavorite;
            final Integer _tmp;
            if (_cursor.isNull(_cursorIndexOfIsFavorite)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getInt(_cursorIndexOfIsFavorite);
            }
            _tmpIsFavorite = _tmp == null ? null : _tmp != 0;
            _result = new UserEntity(_tmpId,_tmpLogin,_tmpName,_tmpFollowing,_tmpFollowers,_tmpPublicRepos,_tmpAvatarUrl,_tmpIsFavorite);
          } else {
            _result = null;
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

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
