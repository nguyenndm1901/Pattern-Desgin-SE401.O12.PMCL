package Factory;

import Builder.LaptopBuilder;
import Entities.Laptop;
import Enum.LaptopOS;

public class LaptopFactory {
    private LaptopFactory() {

    }

    public static LaptopBuilder createLaptop(LaptopOS os) {
        switch (os) {
            case Windows:
                return new WindowsLaptopCreator().createLaptop();

            case MacOS:
                return new MacOSLaptopCreator().createLaptop();

            default:
                throw new IllegalArgumentException();
        }
    }
}
