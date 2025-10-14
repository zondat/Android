package vn.fpt.coursesupport.prm.database.dao.sqlite;

import android.database.sqlite.SQLiteDatabase;
import vn.fpt.coursesupport.prm.database.dao.ILibraryDAO;
import vn.fpt.coursesupport.prm.database.model.Library;

public class LibraryDAOImpl implements ILibraryDAO {

    private static String tableName = "libraries";
    private SQLiteDatabase database;
    public static ILibraryDAO INSTANCE = new LibraryDAOImpl();

    private LibraryDAOImpl() {}

    @Override
    public boolean save(Library library) {
        return false;
    }

    @Override
    public void update(Library library) {

    }

    @Override
    public Library get(long id) {
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
                            "address" + " TEXT," +
                            "managerId" + " LONG" +
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
