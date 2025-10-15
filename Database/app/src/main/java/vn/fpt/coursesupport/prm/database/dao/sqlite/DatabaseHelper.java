package vn.fpt.coursesupport.prm.database.dao.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import vn.fpt.coursesupport.prm.database.model.Book;
import vn.fpt.coursesupport.prm.database.model.Person;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = DatabaseConfig.userDBName;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        BookDAOImpl.INSTANCE.createTable();
        PersonDAOImpl.INSTANCE.createTable();
        LibraryDAOImpl.INSTANCE.createTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        BookDAOImpl.INSTANCE.deleteTable();
        PersonDAOImpl.INSTANCE.deleteTable();
        LibraryDAOImpl.INSTANCE.deleteTable();

        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public SQLiteDatabase openDatabase() {
        return this.getWritableDatabase();
    }

    public void closeDatabase() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen()) {
            db.close();
        }
    }
}