package id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Product{
    private String productId;
    private String productName;
    private int productQuantity;

    public void setProductId(String s) {
        this.productId = s;
    }

    public String getProductId() {
        return productId;
    }
}