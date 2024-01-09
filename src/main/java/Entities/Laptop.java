package Entities;


import Decorator.ILaptopDecorator;

public class Laptop implements ILaptopDecorator {
    private String id;
    private String code;
    private String name;
    private String brand;
    private String processor;
    private String memory;
    private String storage;
    private int warranty;
    private int price;

    public Laptop() {
    }

    public Laptop(String code, String name, String brand, String processor, String memory, String storage, int warranty, int price) {
        this.code = code;
        this.name = name;
        this.brand = brand;
        this.processor = processor;
        this.memory = memory;
        this.storage = storage;
        this.warranty = warranty;
        this.price = price;
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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    @Override
    public int getWarranty() {
        return warranty;
    }

    public void setWarranty(int warranty) {
        this.warranty = warranty;
    }

    @Override
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
