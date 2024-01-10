package Entities;

public class Cart {
    private String id;
    private String name;
    private int quantity;
    private int unitPrice;
    private String serviceId;
    private String service;
    private int total;

    public Cart() {
    }

    public Cart(String name, int quantity, int unitPrice, String serviceId, String service, int total) {
        this.name = name;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.serviceId = serviceId;
        this.service = service;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
