package vn.fpt.coursesupport.prm.database.dao.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import vn.fpt.coursesupport.prm.database.dao.AGenericDAO;
import vn.fpt.coursesupport.prm.database.dao.IBookDAO;
import vn.fpt.coursesupport.prm.database.dao.IGenericDAO;
import vn.fpt.coursesupport.prm.database.model.Book;
import vn.fpt.coursesupport.prm.database.model.Person;

public class BookDAOImpl extends AGenericDAO implements IBookDAO {

    private final String columnName = "name";
    public static BookDAOImpl INSTANCE = new BookDAOImpl();

    private BookDAOImpl() {
        tableName = "books";
    }

    @Override
    public boolean createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS " + tableName + "(" +
                columnId +" INTEGER" + "," +
                columnName + " TEXT" +
                ");";
        Log.d("BookDAO", sql);
        try {
            databaseHelper.getWritableDatabase().execSQL(sql);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public synchronized boolean save(Book book) {
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        database.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(columnId, book.getId());
            values.put(columnName, book.getName());
            database.insert(tableName, null, values);
            database.setTransactionSuccessful();

            // Update authorship
            for (Person author: book.getAuthors()) {
                PersonDAOImpl.INSTANCE.updateAuthorship(author, book.getId());
            }
        } catch (Exception e) {
            Log.e(tableName, "Error on saving book", e);
            return false;
        } finally {
            database.endTransaction();
        }
        return true;
    }

    @Override
    public synchronized void update(Book book) {
        ContentValues values = new ContentValues();
        values.put(columnName, book.getName());

        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        database.update(tableName, values,columnId + " = ?",
                        new String[]{String.valueOf(book.getId())});

        // Update authorship
        List<Person> authors = book.getAuthors();
        for (Person author: authors) {
            PersonDAOImpl.INSTANCE.updateAuthorship(author, book.getId());
        }
    }

    @Override
    public Book getBookById(int bookId) {
        SQLiteDatabase database = databaseHelper.getReadableDatabase();
        Cursor cursor = database.query(
                tableName,
                new String[]{columnId, columnName},
                columnId + " = ?",
                new String[]{String.valueOf(bookId)},
                null, null, null
        );

        Book book = null;
        if (cursor.moveToFirst()) {
            book = new Book();
            book.setId(bookId);
            book.setName(cursor.getString(cursor.getColumnIndexOrThrow(columnName)));

            // Search for authors
            List<Person> authors = PersonDAOImpl.INSTANCE.getAuthors(bookId);
            book.setAuthors(authors);
        }

        cursor.close();
        return book;
    }

    @Override
    public List<Book> getAllBooks() {
        SQLiteDatabase database = databaseHelper.getReadableDatabase();

        Cursor cursor = database.query(
                tableName,
                new String[]{columnId, columnName},
                "*",
                null,
                null, null, null
        );

        List<Book> allBooks = new ArrayList<>();
        Book book = null;
        if (cursor.moveToFirst()) {
            do {
                // Create book
                book = new Book();
                book.setId(cursor.getInt(cursor.getColumnIndexOrThrow(columnId)));
                book.setName(cursor.getString(cursor.getColumnIndexOrThrow(columnName)));

                // Search for authors
                List<Person> authors = PersonDAOImpl.INSTANCE.getAuthors(book.getId());
                book.setAuthors(authors);

                // Add to list
                allBooks.add(book);
            }
            while (cursor.moveToNext());
        }

        cursor.close();
        return allBooks;
    }

    @Override
    public List<Book> getBooksByName(String name) {
        SQLiteDatabase database = databaseHelper.getReadableDatabase();

        // Create query
        Cursor cursor = database.query(
                tableName,
                new String[]{columnId, columnName},
                columnName + " = ?",
                new String[]{name},
                null, null, null
        );

        Book book = null;
        List<Book> bookList = new ArrayList<>();
        if (cursor.moveToFirst()) {
            int bookId = cursor.getInt(cursor.getColumnIndexOrThrow(columnId));

            // Create book
            book = new Book();
            book.setId(bookId);
            book.setName(name);

            // Search for authors
            List<Person> authors = PersonDAOImpl.INSTANCE.getAuthors(bookId);
            book.setAuthors(authors);

            // Add book to list
            bookList.add(book);
        }

        cursor.close();
        return bookList;
    }

}
