package step.learning.dal.dto;

import java.util.UUID;
public class ProductItem {
    private UUID id;
    private String name;
    private String description;
    private String imgPath;
    private double price;
    private double propPrice;

    public ProductItem(){}

    public ProductItem(UUID id, String description, String imgPath, int price, int propPrice) {
        this.id = id;
        this.description = description;
        this.imgPath = imgPath;
        this.price = price;
        this.propPrice = propPrice;
    }
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPropPrice() {
        return propPrice;
    }

    public void setPropPrice(double propPrice) {
        this.propPrice = propPrice;
    }


}
