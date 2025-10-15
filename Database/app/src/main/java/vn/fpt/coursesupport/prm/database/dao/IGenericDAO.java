package vn.fpt.coursesupport.prm.database.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public interface IGenericDAO {
    boolean createTable();
    boolean openDatabase(Context context);
    void closeDatabase();
    void deleteTable();
}
