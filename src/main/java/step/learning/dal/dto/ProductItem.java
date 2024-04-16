package step.learning.dal.dto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
public class ProductItem {
    private UUID id;
    private String name;
    private String description;
    private String imgPath;
    private double price;
    private double propPrice;

    public ProductItem(){}

    public ProductItem(ResultSet resultSet) throws SQLException {
        this(
                UUID.fromString( resultSet.getString("product_id")),
                resultSet.getString("product_name"),
                resultSet.getString("product_description"),
                resultSet.getString("product_image"),
                resultSet.getDouble("product_price")


        );
    }
    public ProductItem(UUID id, String name, String description, String imgPath, Double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imgPath = imgPath;
        this.price = price;

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
