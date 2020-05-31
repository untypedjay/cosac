package swe4.util;

public class PriceUtil {
  public static String formatPrice(long priceInCents) {
    return String.valueOf(priceInCents / 100.0);
  }
}
