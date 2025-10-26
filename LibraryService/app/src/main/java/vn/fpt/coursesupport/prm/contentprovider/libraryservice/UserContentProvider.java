package vn.fpt.coursesupport.prm.contentprovider.libraryservice;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class UserContentProvider extends ContentProvider {

    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    private static final int CODE_PARAM_USER_NAME = 100;
    private static final int CODE_PARAM_USER_ID = 101;

    static {
        String authority = "vn.fpt.library.services";

        // Map URI patterns to our custom codes
        uriMatcher.addURI(authority, "users", CODE_PARAM_USER_NAME);
        uriMatcher.addURI(authority, "users/#", CODE_PARAM_USER_ID);
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        MatrixCursor cursor = new MatrixCursor(LibraryService.INSTANCE.getColumns());

        int match = uriMatcher.match(uri);
        switch (match) {
            // Get all users
            case CODE_PARAM_USER_NAME:
                for (int i=0; i<LibraryService.INSTANCE.getAllUsers().size(); i++) {
                    cursor.addRow(new Object[]{i, LibraryService.INSTANCE.getUserById(i)});
                }
                return cursor;

            // Get user by id
            case CODE_PARAM_USER_ID:
                int userId = Integer.parseInt(uri.getLastPathSegment());
                cursor.addRow(new Object[]{userId, LibraryService.INSTANCE.getUserById(userId)});
                return cursor;

            default:
                throw new IllegalArgumentException("Unknown query URI: " + uri);
        }
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return "";
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public boolean onCreate() {
        return false;
    }



    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
