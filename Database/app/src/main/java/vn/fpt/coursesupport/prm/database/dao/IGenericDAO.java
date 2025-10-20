package vn.fpt.coursesupport.prm.database.dao;

import vn.fpt.coursesupport.prm.database.dao.sqlite.DatabaseHelper;

public interface IGenericDAO {
    boolean createTable();
    IGenericDAO setHelper(DatabaseHelper helper);
    void deleteTable();
}
