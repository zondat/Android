package vn.fpt.coursesupport.prm.database.model;

import java.util.ArrayList;
import java.util.List;

public class Person {

    private Integer id;
    private String name;

    public Person() {}

    public Person(Integer id) {
        this();
        setId(id);
    }

    public Person(Integer id, String name) {
        this(id);
        setName(name);
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

}
