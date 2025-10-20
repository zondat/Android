package vn.fpt.coursesupport.prm.database.dao;

import java.util.List;

import vn.fpt.coursesupport.prm.database.model.Book;

public interface IBookDAO extends IGenericDAO {

    boolean save(Book book);
    void update(Book book);
    Book getBookById(int id);
    List<Book> getAllBooks();
    List<Book> getBooksByName(String name);
}
