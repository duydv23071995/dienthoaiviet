package com.example.dienthoaiviet.utils;

import java.text.DecimalFormat;

public class MoneyUtil {
    public static String getPriceVnd(int money) {
        DecimalFormat format = new DecimalFormat("###,###,###");
        return format.format(money)+" VND";
    }
}
