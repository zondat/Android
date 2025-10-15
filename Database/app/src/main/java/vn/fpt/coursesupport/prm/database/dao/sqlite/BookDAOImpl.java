package vn.fpt.coursesupport.prm.database.dao.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import vn.fpt.coursesupport.prm.database.dao.IBookDAO;
import vn.fpt.coursesupport.prm.database.model.Book;
import vn.fpt.coursesupport.prm.database.model.Person;

public class BookDAOImpl implements IBookDAO {

    private DatabaseHelper dbHelper;
    private SQLiteDatabase database = null;

    private final String tableName = "books";
    private final String columnId = "id";
    private final String columnName = "name";

    public static IBookDAO INSTANCE = new BookDAOImpl();

    private BookDAOImpl() {}

    @Override
    public synchronized boolean save(Book book) {
        database.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(columnId, book.getId());
            values.put(columnName, book.getName());
            database.insert(tableName, null, values);
            database.setTransactionSuccessful();

            for (Person author : book.getAuthors()) {
                PersonDAOImpl.INSTANCE.save(author);// update bookId column ;
            }
        } catch (Exception e) {
            Log.e("BookDao", "Error on saving book", e);
            return false;
        } finally {
            database.endTransaction();
        }
        return true;
    }

    @Override
    public synchronized void update(Book book) {
        ContentValues values = new ContentValues();
        values.put(columnId, book.getId());
        values.put(columnName, book.getName());

        database.update(tableName,
                            values,
                columnId + " = ?",
                            new String[]{String.valueOf(book.getId())});
    }

    @Override
    public Book get(long id) {

        Cursor cursor = database.query(
                tableName,
                new String[]{columnId, columnName},
                columnId + " = ?",
                new String[]{String.valueOf(id)},
                null, null, null
        );

        Book book = null;
        if (cursor.moveToFirst()) {
            book = new Book();
            book.setId(id);
            book.setName(cursor.getString(cursor.getColumnIndexOrThrow(columnName)));
        }
        cursor.close();
        return book;
    }

    @Override
    public synchronized void delete(long id) {
        database.delete(tableName, columnId + " = ?", new String[]{String.valueOf(id)});
    }

    @Override
    public boolean createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS " + "tableName (" +
                columnId + " LONG PRIMARY KEY AUTOINCREMENT," +
                columnName + " TEXT" +
                ");";
        database.execSQL(sql);
        return true;
    }

    @Override
    public boolean openDatabase(Context context) {
        if (database == null) {
            dbHelper = new DatabaseHelper(context);
            database = dbHelper.openDatabase();
        }
        return database != null;
    }

    @Override
    public void closeDatabase() {
        dbHelper.closeDatabase();
        database = null;
    }

    @Override
    public void deleteTable() {
        String sql = "DROP TABLE IF EXISTS " + "tableName" + ";";
        database.execSQL(sql);
    }

    public void setDatabase(SQLiteDatabase db) {
        database = db;
    }
}
