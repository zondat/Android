package vn.fpt.coursesupport.prm.database.model;

import java.util.ArrayList;
import java.util.List;

public class Book {

    private Long id;
    private String name;
    private List<Person> authors;

    public Book() {
        authors = new ArrayList<>();
    }

    public Book(String name) {
        this();
        setName(name);
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

    public List<Person> getAuthors() {
        return authors;
    }

    public void addAuthor(Person author) {
        if (!authors.contains(author)) authors.add(author);
    }

}
