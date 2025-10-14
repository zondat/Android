package vn.fpt.coursesupport.prm.database.dao;

import vn.fpt.coursesupport.prm.database.model.Library;

public interface ILibraryDAO extends IGenericDAO {
    boolean save(Library library);
    void update(Library library);
    Library get(long id);
    void delete(long id);
}
