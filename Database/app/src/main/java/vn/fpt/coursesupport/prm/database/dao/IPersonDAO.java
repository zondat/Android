package vn.fpt.coursesupport.prm.database.dao;

import vn.fpt.coursesupport.prm.database.model.Person;

public interface IPersonDAO extends IGenericDAO {
    boolean save(Person person);
    void update(Person person);
    Person get(long id);
    void delete(long id);
}