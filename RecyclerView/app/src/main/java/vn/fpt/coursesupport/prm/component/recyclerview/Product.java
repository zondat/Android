package vn.fpt.coursesupport.prm.component.recyclerview;

// Product.java
public class Product {
    private String name;
    private String imageName; // or use int for drawable resources
    private double price;

    public Product(String name, String imageName, double price) {
        this.name = name;
        this.imageName = imageName;
        this.price = price;
    }

    // Getters
    public String getName() { return name; }
    public String getImageName() { return imageName; }
    public double getPrice() { return price; }
}