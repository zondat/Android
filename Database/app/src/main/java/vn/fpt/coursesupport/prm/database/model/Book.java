package vn.fpt.coursesupport.prm.database.model;

import java.util.ArrayList;
import java.util.List;

public class Book {

    private Integer id;
    private String name;
    private List<Person> authors;

    public Book() {
        authors = new ArrayList<>();
    }

    public Book(Integer id) {
        this();
        setId(id);
    }

    public Book(Integer id, String name) {
        this(id);
        setName(name);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public void setAuthors(List<Person> authors) {
        this.authors = authors;
    }

}
