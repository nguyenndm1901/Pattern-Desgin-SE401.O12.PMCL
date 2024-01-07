package Strategy;

public class CashPaymentStrategy implements IPaymentStrategy {

    @Override
    public String paymentType() {
        return "cash";
    }
}
