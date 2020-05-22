package swe4.entities;

public class Meal {
  private String mealType = "";
  private String description = "";
  private long priceInCents = 0;

  public Meal(String mealType, String description, long priceInCents) {
    this.mealType = mealType;
    this.description = description;
    this.priceInCents = priceInCents;
  }

  public String getMealType() {
    return mealType;
  }

  public void setMealType(String mealType) {
    this.mealType = mealType;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public long getPriceInCents() {
    return priceInCents;
  }

  public void setPriceInCents(long priceInCents) {
    this.priceInCents = priceInCents;
  }
}
