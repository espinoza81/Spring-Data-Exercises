package cardealer.constant;

import java.util.Random;

public enum Discount {
    ZERO(0),
    FIVE(5),
    TEN(10),
    FIFTEEN(15),
    TWENTY(20),
    THIRTY(30),
    FORTY(40),
    FIFTY(50);

    private final Integer percentage;
    Discount(Integer percentage) {
        this.percentage = percentage;
    }

    public static Integer getDiscount(){
        final Random random = new Random();

        Discount[] discounts = values();

        final int discountIndex = random.nextInt(discounts.length);

        return discounts[discountIndex].percentage;
    }
}
