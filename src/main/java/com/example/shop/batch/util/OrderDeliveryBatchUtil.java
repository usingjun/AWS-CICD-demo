package com.example.shop.batch.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public final class OrderDeliveryBatchUtil {

    public static String getOrderKey() {
        return "orders:" + LocalDate.now()
                .minusDays(1)
                .format(DateTimeFormatter.ISO_LOCAL_DATE);
    }
}
