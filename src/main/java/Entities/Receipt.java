package Entities;

import Strategy.IPaymentStrategy;

import java.util.List;

public class Receipt {
    private String id;
    private String code;
    private String customerId;
    private String staffId;
    private String purchaseDate;
    private int total;
    private List<Laptop> laptopList;
    private String paymentType;

    public Receipt() {
    }

    public Receipt(String code, String customerId, String staffId, String purchaseDate, int total, List<Laptop> laptopList, String paymentType) {
        this.code = code;
        this.customerId = customerId;
        this.staffId = staffId;
        this.purchaseDate = purchaseDate;
        this.total = total;
        this.laptopList = laptopList;
        this.paymentType = paymentType;
    }

    public String getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Laptop> getLaptopList() {
        return laptopList;
    }

    public void setLaptopList(List<Laptop> laptopList) {
        this.laptopList = laptopList;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }
}
