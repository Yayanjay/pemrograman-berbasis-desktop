import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class RestaurantMenu {
    private ArrayList<MenuItem> items = new ArrayList<>();

    public void addItem(MenuItem item) {
        items.add(item);
    }

    public ArrayList<MenuItem> getItems() {
        return items;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public MenuItem getItemByNumber(int number) throws MenuItemNotFoundException {
        if (number < 1 || number > items.size()) {
            throw new MenuItemNotFoundException("Menu item number " + number + " was not found.");
        }

        return items.get(number - 1);
    }

    public Discount getFirstDiscount() {
        for (MenuItem item : items) {
            if (item instanceof Discount) {
                return (Discount) item;
            }
        }

        return null;
    }

    public String buildMenuText() {
        StringBuilder builder = new StringBuilder();
        builder.append("\n============== RESTAURANT MENU ==============\n");
        appendCategory(builder, "Food");
        appendCategory(builder, "Beverage");
        appendCategory(builder, "Discount");
        builder.append("=============================================\n");
        return builder.toString();
    }

    public void displayMenu() {
        System.out.print(buildMenuText());
    }

    public void saveToFile(String filePath) throws IOException {
        Path path = Path.of(filePath);
        Path parent = path.getParent();

        if (parent != null) {
            Files.createDirectories(parent);
        }

        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            for (MenuItem item : items) {
                writer.write(item.toFileString());
                writer.newLine();
            }
        }
    }

    public void loadFromFile(String filePath) throws IOException {
        Path path = Path.of(filePath);

        if (!Files.exists(path)) {
            throw new IOException("Menu file does not exist: " + filePath);
        }

        ArrayList<MenuItem> loadedItems = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    loadedItems.add(parseMenuItem(line));
                }
            }
        }

        items = loadedItems;
    }

    private void appendCategory(StringBuilder builder, String category) {
        builder.append("--- ").append(category).append("s ---\n");

        for (int i = 0; i < items.size(); i++) {
            MenuItem item = items.get(i);
            if (item.getCategory().equals(category)) {
                builder.append(i + 1).append(". ").append(item.displayMenu()).append("\n");
            }
        }
    }

    private MenuItem parseMenuItem(String line) throws IOException {
        String[] parts = line.split("\\|", -1);

        if (parts.length < 4) {
            throw new IOException("Invalid menu data: " + line);
        }

        String type = parts[0];
        String name = parts[1];
        double price = Double.parseDouble(parts[2]);

        if (type.equals("FOOD")) {
            return new Food(name, price, parts[3]);
        }

        if (type.equals("BEVERAGE")) {
            return new Beverage(name, price, parts[3]);
        }

        if (type.equals("DISCOUNT")) {
            if (parts.length < 8) {
                throw new IOException("Invalid discount data: " + line);
            }

            String discountType = parts[3];
            double percentage = Double.parseDouble(parts[4]);
            double minimumSubtotal = Double.parseDouble(parts[5]);
            String targetCategory = parts[6];
            String targetItemName = parts[7];
            int maxClaim = parts.length >= 9 ? Integer.parseInt(parts[8]) : 0;
            return new Discount(name, discountType, percentage, minimumSubtotal,
                    targetCategory, targetItemName, maxClaim);
        }

        throw new IOException("Unknown menu item type: " + type);
    }
}
