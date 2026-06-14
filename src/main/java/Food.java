public class Food extends MenuItem {
    private String foodType;

    public Food(String name, double price, String foodType) {
        super(name, price, "Food");
        this.foodType = foodType;
    }

    public String getFoodType() {
        return foodType;
    }

    @Override
    public String displayMenu() {
        return String.format("%-20s Rp %,.0f (%s)", getName(), getPrice(), foodType);
    }

    @Override
    public String toFileString() {
        return "FOOD|" + getName() + "|" + getPrice() + "|" + foodType;
    }
}
