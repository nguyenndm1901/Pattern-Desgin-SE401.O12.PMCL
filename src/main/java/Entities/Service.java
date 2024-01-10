package Entities;

public class Service {
    private String id;
    private String code;
    private String name;
    private int warranty;
    private int price;

    public Service(String code, String name, int warranty, int price) {
        this.code = code;
        this.name = name;
        this.warranty = warranty;
        this.price = price;
    }

    public Service() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWarranty() {
        return warranty;
    }

    public void setWarranty(int warranty) {
        this.warranty = warranty;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return name;
    }
}
