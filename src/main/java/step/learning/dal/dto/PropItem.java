package step.learning.dal.dto;

import java.util.UUID;
public class PropItem {
    private UUID id;
    private String description;
    private String imgPath;
    private String price;
    private String propPrice;

    public PropItem(UUID id, String description, String imgPath, String price, String propPrice) {
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

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPropPrice() {
        return propPrice;
    }

    public void setPropPrice(String propPrice) {
        this.propPrice = propPrice;
    }


}
