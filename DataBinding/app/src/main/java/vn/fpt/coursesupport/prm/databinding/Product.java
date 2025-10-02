package vn.fpt.coursesupport.prm.databinding;

public class Product {
    private String name;
    private float price;
    private int imageId;

    public Product(String name, float price, int imageId) {
        setName(name);
        setPrice(price);
        setImageId(imageId);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getPrice() {
        return price;
    }

    public void setImageId(int imgId) {
        this.imageId = imgId;
    }

    public int getImageId() {
        return imageId;
    }
}

