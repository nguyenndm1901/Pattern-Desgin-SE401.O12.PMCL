package Decorator;

public class LaptopBuyServiceDecorator implements ILaptopDecorator {

    protected ILaptopDecorator laptopWrappee;

    public LaptopBuyServiceDecorator(ILaptopDecorator laptopWrappee) {
        this.laptopWrappee = laptopWrappee;
    }

    @Override
    public int getPrice() {
        return laptopWrappee.getPrice();
    }

    @Override
    public int getWarranty() {
        return laptopWrappee.getWarranty();
    }
}
