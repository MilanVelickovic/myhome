package com.myhome.utils;

import java.text.NumberFormat;
import java.util.Currency;

public class Converter {

    private NumberFormat currencyInstance;

    public Converter() {
        currencyInstance = NumberFormat.getCurrencyInstance();
    }

    public String convertPrice(String currency, Long price) {
        currencyInstance.setCurrency(Currency.getInstance(currency.toUpperCase()));
        return  currencyInstance.format(price);
    }

}
