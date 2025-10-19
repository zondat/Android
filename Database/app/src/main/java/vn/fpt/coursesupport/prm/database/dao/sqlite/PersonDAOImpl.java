package vn.fpt.coursesupport.prm.database.dao.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import vn.fpt.coursesupport.prm.database.dao.AGenericDAO;
import vn.fpt.coursesupport.prm.database.dao.IGenericDAO;
import vn.fpt.coursesupport.prm.database.dao.IPersonDAO;
import vn.fpt.coursesupport.prm.database.model.Person;

public class PersonDAOImpl extends AGenericDAO implements IPersonDAO {

    private final String columnName = "name";
    private final String columnBookId = "book_id";
    public static PersonDAOImpl INSTANCE = new PersonDAOImpl();

    private PersonDAOImpl() {
        tableName = "persons";
    }

    @Override
    public boolean createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS " + tableName + "(" +
                columnId + " INTEGER" + "," +
                columnName + " TEXT" + "," +
                columnBookId + " INTEGER" +
                ");";

        Log.d("PersonDAO", sql);
        try {
            databaseHelper.getWritableDatabase().execSQL(sql);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public synchronized boolean save(Person person) {
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        database.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(columnId, person.getId());
            values.put(columnName, person.getName());
            database.insert(tableName, null, values);
            database.setTransactionSuccessful();
        } catch (Exception e) {
            Log.e(tableName, "Error on saving person", e);
            return false;
        } finally {
            database.endTransaction();
        }
        return true;
    }

    @Override
    public synchronized void update(Person person) {
        SQLiteDatabase database = databaseHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(columnName, person.getName());
        database.update(tableName, values,columnId + " = ?",
                new String[]{String.valueOf(person.getId())});
    }

    @Override
    public synchronized void updateAuthorship(Person author, int bookId) {
        SQLiteDatabase database = databaseHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(columnBookId, bookId);
        database.update(tableName, values,columnId + " = ?",
                new String[]{String.valueOf(author.getId())});
    }

    @Override
    public Person getPersonById(int id) {
        SQLiteDatabase database = databaseHelper.getReadableDatabase();
        Cursor cursor = database.query(
                tableName,
                new String[]{columnId, columnName},
                columnId + " = ?",
                new String[]{String.valueOf(id)},
                null, null, null
        );

        Person person = null;
        if (cursor.moveToFirst()) {
            person = new Person();
            person.setId(id);
            person.setName(cursor.getString(cursor.getColumnIndexOrThrow(columnName)));
        }

        cursor.close();
        return person;
    }

    @Override
    public List<Person> getAllPersons() {
        SQLiteDatabase database = databaseHelper.getReadableDatabase();

        // Prepare query
        Cursor cursor = database.query(
                tableName,
                new String[]{columnId, columnName},
                "*",
                null,
                null, null, null
        );

        List<Person> allPersons = new ArrayList<>();
        Person person = null;
        if (cursor.moveToFirst()) {
            do {
                // Create person
                person = new Person();
                person.setId(cursor.getInt(cursor.getColumnIndexOrThrow(columnId)));
                person.setName(cursor.getString(cursor.getColumnIndexOrThrow(columnName)));

                // Add to list
                allPersons.add(person);
            }
            while (cursor.moveToNext());
        }

        cursor.close();
        return allPersons;
    }

    @Override
    public List<Person> getPersonsByName(String name) {
        SQLiteDatabase database = databaseHelper.getReadableDatabase();

        // Create query
        Cursor cursor = database.query(
                tableName,
                new String[]{columnId, columnName},
                columnName + " = ?",
                new String[]{name},
                null, null, null
        );

        List<Person> personList = new ArrayList<>();
        Person person = null;
        if (cursor.moveToFirst()) {
            do {
                // Create person
                person = new Person();
                person.setId(cursor.getInt(cursor.getColumnIndexOrThrow(columnId)));
                person.setName(name);

                // Add to list
                personList.add(person);
            }
            while (cursor.moveToNext());
        }

        cursor.close();
        return personList;
    }

    @Override
    public List<Person> getAuthors(int bookId) {
        SQLiteDatabase database = databaseHelper.getReadableDatabase();

        // Create query
        Cursor cursor = database.query(
                tableName,
                new String[]{columnId, columnName},
                columnBookId + " = ?",
                new String[]{String.valueOf(bookId)},
                null, null, null
        );

        List<Person> authorList = new ArrayList<>();
        Person author = null;
        if (cursor.moveToFirst()) {
            // Create person
            author = new Person();
            author.setId(cursor.getInt(cursor.getColumnIndexOrThrow(columnId)));
            author.setName(cursor.getString(cursor.getColumnIndexOrThrow(columnName)));

            // Add author to list
            authorList.add(author);
        }

        cursor.close();
        return authorList;
    }
}
