public class Beverage extends MenuItem {
    private String beverageType;

    public Beverage(String name, double price, String beverageType) {
        super(name, price, "Beverage");
        this.beverageType = beverageType;
    }

    public String getBeverageType() {
        return beverageType;
    }

    @Override
    public String displayMenu() {
        return String.format("%-20s Rp %,.0f (%s)", getName(), getPrice(), beverageType);
    }

    @Override
    public String toFileString() {
        return "BEVERAGE|" + getName() + "|" + getPrice() + "|" + beverageType;
    }
}
