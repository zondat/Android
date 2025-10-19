package vn.fpt.coursesupport.prm.database;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import vn.fpt.coursesupport.prm.database.dao.IBookDAO;
import vn.fpt.coursesupport.prm.database.dao.IPersonDAO;
import vn.fpt.coursesupport.prm.database.dao.sqlite.BookDAOImpl;
import vn.fpt.coursesupport.prm.database.dao.sqlite.DatabaseHelper;
import vn.fpt.coursesupport.prm.database.dao.sqlite.PersonDAOImpl;
import vn.fpt.coursesupport.prm.database.model.Book;
import vn.fpt.coursesupport.prm.database.model.Person;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_main);

        // Initiate DAO
        DatabaseHelper helper = new DatabaseHelper(this);
        IPersonDAO personDAO = (IPersonDAO) PersonDAOImpl.INSTANCE.setHelper(helper);
        IBookDAO bookDAO = (IBookDAO) BookDAOImpl.INSTANCE.setHelper(helper);
        personDAO.createTable();
        bookDAO.createTable();

//        PersonDAOImpl.INSTANCE.getTableSchema("persons");

        // Business model
        Person user = new Person(1, "Nguyen Van A");
        Person romainGary = new Person(2, "Romain Gary");
        Person hectorMalot = new Person(3, "Hector Marlot");

        Book rootOfHeaven = new Book(1, "Roots of Heaven");
        rootOfHeaven.addAuthor(romainGary);
        Book noFamilly = new Book(2, "No Familly");
        noFamilly.addAuthor(hectorMalot);

        personDAO.save(user);
        personDAO.save(romainGary);
        personDAO.save(hectorMalot);

        bookDAO.save(rootOfHeaven);
        bookDAO.save(noFamilly);

        Person loadedUser = personDAO.getPersonById(user.getId());
        Log.d("PersonDAO", loadedUser.getName());
        Person loadedAuthor1 = personDAO.getPersonById(romainGary.getId());
        Log.d("PersonDAO", loadedAuthor1.getName());
        Person loadedAuthor2 = personDAO.getPersonById(hectorMalot.getId());
        Log.d("PersonDAO", loadedAuthor2.getName());

        Book loadedBook1 = bookDAO.getBookById(rootOfHeaven.getId());
        Log.d("BookDAO", loadedBook1.getName());
        Log.d("BookDAO", loadedBook1.getAuthors().get(0).getName());

        Book loadedBook2 = bookDAO.getBookById(noFamilly.getId());
        Log.d("BookDAO", loadedBook2.getName());
        Log.d("BookDAO", loadedBook2.getAuthors().get(0).getName());
    }
}