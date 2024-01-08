package Factory;

import Builder.LaptopBuilder;
import Entities.Laptop;

public class WindowsLaptopCreator implements LaptopCreator {

    @Override
    public Laptop createLaptop() {
        return new LaptopBuilder().build();
        // return new LaptopBuilder()
        //      .setCode(code).setName(name)
        //      .setBrand(brand)
        //      .setProcessor(processor)
        //      .setMemory(memory)
        //      .setStorage(storage)
        //      .setPrice(price)
        //      .build();
        //btnNewLaptop
    }
}
