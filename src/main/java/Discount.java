public class Discount extends MenuItem {
    private String discountType;
    private double discountPercentage;
    private double minimumSubtotal;
    private String targetCategory;
    private String targetItemName;

    public Discount(String name, String discountType, double discountPercentage, double minimumSubtotal,
                    String targetCategory, String targetItemName) {
        super(name, 0, "Discount");
        this.discountType = discountType;
        this.discountPercentage = discountPercentage;
        this.minimumSubtotal = minimumSubtotal;
        this.targetCategory = targetCategory;
        this.targetItemName = targetItemName;
    }

    public String getDiscountType() {
        return discountType;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public double getMinimumSubtotal() {
        return minimumSubtotal;
    }

    public String getTargetCategory() {
        return targetCategory;
    }

    public String getTargetItemName() {
        return targetItemName;
    }

    @Override
    public String displayMenu() {
        if (discountType.equals("PERCENTAGE")) {
            return String.format("%-20s %.0f%% off, min subtotal Rp %,.0f",
                    getName(), discountPercentage, minimumSubtotal);
        }

        if (discountType.equals("BOGO")) {
            return String.format("%-20s Buy 1 Get 1 for %s, min subtotal Rp %,.0f",
                    getName(), targetCategory, minimumSubtotal);
        }

        if (discountType.equals("ITEM_ONLY")) {
            return String.format("%-20s %.0f%% off for %s, min subtotal Rp %,.0f",
                    getName(), discountPercentage, targetItemName, minimumSubtotal);
        }

        return String.format("%-20s Discount rule", getName());
    }

    @Override
    public String toFileString() {
        return "DISCOUNT|" + getName() + "|" + getPrice() + "|" + discountType + "|"
                + discountPercentage + "|" + minimumSubtotal + "|" + targetCategory + "|" + targetItemName;
    }
}
