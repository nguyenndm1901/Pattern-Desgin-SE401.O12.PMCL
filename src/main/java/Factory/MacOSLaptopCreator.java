package Factory;

import Builder.LaptopBuilder;
import Entities.Laptop;
import Enum.LaptopOS;

public class MacOSLaptopCreator {

    public LaptopBuilder createLaptop() {
        return new LaptopBuilder();
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
