package Builder;

import Entities.Laptop;

public class LaptopBuilder implements ILaptopBuilder {

    private String code;
    private String name;
    private String brand;
    private String processor;
    private String memory;
    private String storage;
    private int warranty;
    private int price;

    private Laptop laptop;

    @Override
    public ILaptopBuilder setCode(String code) {
        this.code = code;
        return this;
    }

    @Override
    public ILaptopBuilder setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public ILaptopBuilder setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    @Override
    public ILaptopBuilder setProcessor(String processor) {
        this.processor = processor;
        return this;
    }

    @Override
    public ILaptopBuilder setMemory(String memory) {
        this.memory = memory;
        return this;
    }

    @Override
    public ILaptopBuilder setStorage(String storage) {
        this.storage = storage;
        return this;
    }

    @Override
    public ILaptopBuilder setWarranty(int warranty) {
        this.warranty = warranty;
        return this;
    }

    @Override
    public ILaptopBuilder setPrice(int price) {
        this.price = price;
        return this;
    }

    @Override
    public Laptop build() {
        return new Laptop(code, name, brand, processor, memory, storage, warranty, price);
    }
}
