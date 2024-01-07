package Builder;

import Entities.Laptop;

public interface ILaptopBuilder {

    ILaptopBuilder setCode(String code);
    ILaptopBuilder setName(String name);
    ILaptopBuilder setBrand(String brand);
    ILaptopBuilder setProcessor(String processor);
    ILaptopBuilder setMemory(String memory);
    ILaptopBuilder setStorage(String storage);
    ILaptopBuilder setPrice(int price);

    Laptop build();
}
