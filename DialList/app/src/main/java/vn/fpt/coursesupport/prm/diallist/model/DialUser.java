package vn.fpt.coursesupport.prm.diallist.model;

public class DialUser {
    private String name;
    private String imageName;

    public DialUser(String name, String imageName) {
        setName(name);
        setImageName(imageName);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
}
