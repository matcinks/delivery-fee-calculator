package com.wolt.domain.deliveryfeecalculator;

public class DeliveryFeeCalculatorFacade {

    public int calculateDeliveryFee(int cartValue, int deliveryDistance, int numberOfItems, String orderTime) {
        int deliveryFee = 0;

        if (cartValue < 10_00) {
            deliveryFee += 10_00 - cartValue;
        }

        deliveryFee += 2;
        int remainingDistance = deliveryDistance - 1_000;
        if (remainingDistance > 0) {
            do {
                deliveryFee += 1;
                remainingDistance -= 500;
            } while(remainingDistance > 0);
        }

        return deliveryFee;
    }

}
