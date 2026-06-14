import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class Order {
    private ArrayList<MenuItem> items = new ArrayList<>();
    private ArrayList<Integer> quantities = new ArrayList<>();

    public void addItem(MenuItem item, int quantity) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i) == item) {
                quantities.set(i, quantities.get(i) + quantity);
                return;
            }
        }

        items.add(item);
        quantities.add(quantity);
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public double calculateSubtotal() {
        double subtotal = 0;

        for (int i = 0; i < items.size(); i++) {
            subtotal += items.get(i).getPrice() * quantities.get(i);
        }

        return subtotal;
    }

    public double calculateDiscountAmount(Discount discount) {
        if (discount == null) {
            return 0;
        }

        double subtotal = calculateSubtotal();

        if (subtotal < discount.getMinimumSubtotal()) {
            return 0;
        }

        if (discount.getDiscountType().equals("PERCENTAGE")) {
            return subtotal * discount.getDiscountPercentage() / 100;
        }

        if (discount.getDiscountType().equals("BOGO")) {
            return calculateBogoDiscount(discount);
        }

        if (discount.getDiscountType().equals("ITEM_ONLY")) {
            return calculateItemOnlyDiscount(discount);
        }

        return 0;
    }

    public double calculateTotal(Discount discount) {
        return calculateSubtotal() - calculateDiscountAmount(discount);
    }

    public String buildReceipt(Discount discount) {
        double subtotal = calculateSubtotal();
        double discountAmount = calculateDiscountAmount(discount);
        double total = subtotal - discountAmount;

        StringBuilder builder = new StringBuilder();
        builder.append("\n==============================================\n");
        builder.append("                   RECEIPT                    \n");
        builder.append("==============================================\n");
        builder.append(String.format("%-20s %5s %10s %10s\n", "Item", "Qty", "Price", "Total"));
        builder.append("----------------------------------------------\n");

        for (int i = 0; i < items.size(); i++) {
            MenuItem item = items.get(i);
            int quantity = quantities.get(i);
            double lineTotal = item.getPrice() * quantity;
            builder.append(String.format("%-20s %5d %10.0f %10.0f\n", item.getName(), quantity, item.getPrice(), lineTotal));
        }

        builder.append("----------------------------------------------\n");
        builder.append(String.format("%-35s %10.0f\n", "Subtotal", subtotal));

        if (discountAmount > 0) {
            builder.append(String.format("%-35s %10.0f\n", "Discount (" + discount.getName() + ")", -discountAmount));
        }

        builder.append("==============================================\n");
        builder.append(String.format("%-35s %10.0f\n", "TOTAL", total));
        builder.append("==============================================\n");
        return builder.toString();
    }

    public void saveReceipt(String filePath, Discount discount) throws IOException {
        Path path = Path.of(filePath);
        Path parent = path.getParent();

        if (parent != null) {
            Files.createDirectories(parent);
        }

        Files.writeString(path, buildReceipt(discount));
    }

    public static String loadReceipt(String filePath) throws IOException {
        Path path = Path.of(filePath);

        if (!Files.exists(path)) {
            throw new IOException("Receipt file does not exist: " + filePath);
        }

        return Files.readString(path);
    }

    private double calculateBogoDiscount(Discount discount) {
        for (int i = 0; i < items.size(); i++) {
            MenuItem item = items.get(i);
            int quantity = quantities.get(i);

            if (item.getCategory().equals(discount.getTargetCategory()) && quantity >= 2) {
                return (quantity / 2) * item.getPrice();
            }
        }

        return 0;
    }

    private double calculateItemOnlyDiscount(Discount discount) {
        double discountAmount = 0;

        for (int i = 0; i < items.size(); i++) {
            MenuItem item = items.get(i);
            int quantity = quantities.get(i);

            if (item.getName().equalsIgnoreCase(discount.getTargetItemName())) {
                double lineTotal = item.getPrice() * quantity;
                discountAmount += lineTotal * discount.getDiscountPercentage() / 100;
            }
        }

        return discountAmount;
    }
}
