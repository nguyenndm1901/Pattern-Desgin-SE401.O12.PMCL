package Entities;

public class Customer {
    private String id;
    private String code;
    private String name;
    private String phone;

    public Customer() {
    }

    public Customer(String code, String name, String phone) {
        this.code = code;
        this.name = name;
        this.phone = phone;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
