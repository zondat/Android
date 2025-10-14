package vn.fpt.coursesupport.prm.database.dao.sqlite;

import android.database.sqlite.SQLiteDatabase;
import vn.fpt.coursesupport.prm.database.dao.IPersonDAO;
import vn.fpt.coursesupport.prm.database.model.Person;

public class PersonDAOImpl implements IPersonDAO {

    private String tableName = "persons";
    private SQLiteDatabase database;
    public static IPersonDAO INSTANCE = new PersonDAOImpl();

    private PersonDAOImpl() {}

    @Override
    public boolean save(Person person) {
        return false;
    }

    @Override
    public void update(Person person) {

    }

    @Override
    public Person get(long id) {
        return null;
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public boolean createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS " + "tableName (" +
                "id" + " LONG PRIMARY KEY AUTOINCREMENT," +
                "name" + " TEXT," +
                "bookId" + " LONG," +
                "libraryId" + "LONG" +
                ");";
        database.execSQL(sql);
        return true;
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
