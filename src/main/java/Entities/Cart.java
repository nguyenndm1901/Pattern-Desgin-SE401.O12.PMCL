package Entities;

public class Cart {
    private String id;
    private String name;
    private int unitPrice;
    private String unit;
    private int warranty;
    private int total;

    public Cart() {
    }

    public Cart(String name, int unitPrice, String unit, int warranty, int total) {
        this.name = name;
        this.unitPrice = unitPrice;
        this.unit = unit;
        this.warranty = warranty;
        this.total = total;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getWarranty() {
        return warranty;
    }

    public void setWarranty(int warranty) {
        this.warranty = warranty;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
