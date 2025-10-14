package vn.fpt.coursesupport.prm.database.model;

public class Person {

    private Long id;
    private String name;

    public Person() {

    }

    public Person(String name) {
        this();
        setName(name);
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
