package vn.fpt.coursesupport.prm.database.dao;

import vn.fpt.coursesupport.prm.database.model.Book;

public interface IBookDAO extends IGenericDAO {

    boolean save(Book book);
    void update(Book book);
    Book get(long id);
    void delete(long id);
}
