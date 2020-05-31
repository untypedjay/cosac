package swe4.util;

import java.text.DecimalFormat;

public class PriceUtil {
  public static String formatPrice(long priceInCents) {
    DecimalFormat priceFormat = new DecimalFormat("#.00");
    return String.valueOf(priceFormat.format(priceInCents / 100.0)) + " €";
  }

  public static long convertToCents(String price) {
    return (long) (Double.parseDouble(price) * 100);
  }
}
