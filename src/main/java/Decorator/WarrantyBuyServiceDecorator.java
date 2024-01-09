package Decorator;

public class WarrantyBuyServiceDecorator extends LaptopBuyServiceDecorator {

    private int warrantyTime;
    private int warrantyCost;


    public WarrantyBuyServiceDecorator(ILaptopDecorator laptopWrappee, int warrantyTime, int warrantyCost) {
        super(laptopWrappee);
        this.warrantyTime = warrantyTime;
        this.warrantyCost = warrantyCost;
    }

    @Override
    public int getPrice() {
        return super.getPrice() + warrantyCost;
    }

    @Override
    public int getWarranty() {
        return super.getWarranty() + warrantyTime;
    }
}
