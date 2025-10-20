package vn.fpt.coursesupport.prm.database.dao;

import java.util.List;

import vn.fpt.coursesupport.prm.database.model.Person;

public interface IPersonDAO extends IGenericDAO {
    boolean save(Person person);
    void update(Person person);
    void updateAuthorship(Person author, int bookId);
    Person getPersonById(int id);
    List<Person> getAllPersons();
    List<Person> getPersonsByName(String name);
    List<Person> getAuthors(int bookId);
}