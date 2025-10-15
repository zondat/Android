package vn.fpt.coursesupport.prm.database.model;

import java.util.ArrayList;
import java.util.List;

public class Library {

    private Long id;
    private String name;
    private String address;
    private Person manager;

    private List<Person> allUsers;
    private List<Book> allBooks;

    public Library() {
        allUsers = new ArrayList<>();
        allBooks = new ArrayList<>();
    }

    public Library(String name) {
        this();
        setName(name);
    }

    public Library(String name, String address) {
        this(name);
        setAddress(address);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Person getManager() {
        return manager;
    }

    public void setManager(Person manager) {
        this.manager = manager;
    }

    public void addBook(Book book) {
        if (!allBooks.contains(book)) allBooks.add(book);
    }

    public void addUser(Person user) {
        if (!allUsers.contains(user)) allUsers.add(user);
    }
}
