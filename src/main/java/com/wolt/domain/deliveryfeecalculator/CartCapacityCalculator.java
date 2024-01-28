package com.wolt.domain.deliveryfeecalculator;

import java.math.BigInteger;

class CartCapacityCalculator {

    private final static BigInteger MAX_NUMBER_OF_ITEMS_WITHOUT_FEE = BigInteger.valueOf(4);
    private final static BigInteger MAX_NUMBER_OF_ITEMS_WITHOUT_BULK_FEE = BigInteger.valueOf(12);
    private final static BigInteger BASE_SURCHARGE = BigInteger.valueOf(50);
    private final static BigInteger BULK_FEE = BigInteger.valueOf(1_20);

    BigInteger calculate(BigInteger numberOfItems) {
        BigInteger deliveryFee = BigInteger.ZERO;
        if (isAmountOfItemsGreaterThanFour(numberOfItems)){
            deliveryFee = numberOfItems.subtract(MAX_NUMBER_OF_ITEMS_WITHOUT_FEE)
                    .multiply(BASE_SURCHARGE);
        }
        if (isAmountOfItemsGreaterThanTwelve(numberOfItems)) {
            deliveryFee = deliveryFee.add(BULK_FEE);
        }
        return deliveryFee;
    }
    private boolean isAmountOfItemsGreaterThanFour(BigInteger numberOfItems){
        return numberOfItems.compareTo(MAX_NUMBER_OF_ITEMS_WITHOUT_FEE) > 0;
    }
    private boolean isAmountOfItemsGreaterThanTwelve(BigInteger numberOfItems) {
        return numberOfItems.compareTo(MAX_NUMBER_OF_ITEMS_WITHOUT_BULK_FEE) > 0;
    }
}
