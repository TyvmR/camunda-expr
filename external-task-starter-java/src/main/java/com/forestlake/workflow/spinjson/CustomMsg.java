package com.forestlake.workflow.spinjson;

import java.util.Date;
import java.util.List;

public class CustomMsg {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public int getProductAge() {
        return productAge;
    }

    public void setProductAge(int productAge) {
        this.productAge = productAge;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    public List<String> getRepairSite() {
        return repairSite;
    }

    public void setRepairSite(List<String> repairSite) {
        this.repairSite = repairSite;
    }

    private String name;
    private String product;
    private int productAge;
    private Date purchaseDate;
    private ProductType type;
    private List<String> repairSite;
}
