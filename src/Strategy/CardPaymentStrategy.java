package Strategy;

public class CardPaymentStrategy implements IPaymentStrategy {

    @Override
    public String paymentType() {
        return "card";
    }
}
