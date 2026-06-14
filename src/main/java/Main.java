import java.io.IOException;
import java.util.Scanner;

public class Main {
    static final String MENU_FILE = "data/menu.txt";
    static final String RECEIPT_FILE = "data/receipt.txt";

    static Scanner scanner = new Scanner(System.in);
    static RestaurantMenu restaurantMenu = new RestaurantMenu();
    static Order currentOrder = new Order();

    public static void main(String[] args) {
        mainMenu();
    }

    static void mainMenu() {
        boolean running = true;

        while (running) {
            System.out.println("\n========== RESTAURANT MANAGEMENT ==========");
            System.out.println("1. Add Menu Item");
            System.out.println("2. Display Restaurant Menu");
            System.out.println("3. Create Customer Order");
            System.out.println("4. Print Receipt");
            System.out.println("5. Save Menu to File");
            System.out.println("6. Load Menu from File");
            System.out.println("7. Load Last Receipt");
            System.out.println("0. Exit");

            int choice = readInt("Choose option: ");

            if (choice == 1) {
                addMenuItem();
            } else if (choice == 2) {
                restaurantMenu.displayMenu();
            } else if (choice == 3) {
                createCustomerOrder();
            } else if (choice == 4) {
                printReceipt();
            } else if (choice == 5) {
                saveMenuToFile();
            } else if (choice == 6) {
                loadMenuFromFile();
            } else if (choice == 7) {
                loadLastReceipt();
            } else if (choice == 0) {
                running = false;
                System.out.println("Thank you. Goodbye!");
            } else {
                System.out.println("Invalid option. Please choose again.");
            }
        }
    }

    static void addMenuItem() {
        boolean adding = true;

        while (adding) {
            System.out.println("\n===== ADD MENU ITEM =====");
            System.out.println("1. Food");
            System.out.println("2. Beverage");
            System.out.println("3. Discount");
            System.out.println("0. Back");

            int choice = readInt("Choose item type: ");

            if (choice == 1) {
                addFood();
            } else if (choice == 2) {
                addBeverage();
            } else if (choice == 3) {
                addDiscount();
            } else if (choice == 0) {
                adding = false;
            } else {
                System.out.println("Invalid option. Please choose again.");
            }
        }
    }

    static void addFood() {
        String name = readNonEmptyText("Food name: ");
        double price = readPositiveDouble("Price: ");
        String foodType = readNonEmptyText("Food type: ");

        restaurantMenu.addItem(new Food(name, price, foodType));
        System.out.println("Food added successfully.");
    }

    static void addBeverage() {
        String name = readNonEmptyText("Beverage name: ");
        double price = readPositiveDouble("Price: ");
        String beverageType = readNonEmptyText("Beverage type: ");

        restaurantMenu.addItem(new Beverage(name, price, beverageType));
        System.out.println("Beverage added successfully.");
    }

    static void addDiscount() {
        String name = readNonEmptyText("Discount name: ");
        String discountType = readDiscountType();
        double percentage = 0;
        double minimumSubtotal = readNonNegativeDouble("Minimum subtotal: ");
        String targetCategory = "";
        String targetItemName = "";

        if (discountType.equals("PERCENTAGE")) {
            percentage = readPositiveDouble("Discount percentage: ");
        } else if (discountType.equals("BOGO")) {
            targetCategory = readTargetCategory();
        } else if (discountType.equals("ITEM_ONLY")) {
            targetItemName = readNonEmptyText("Target item name: ");
            percentage = readPositiveDouble("Discount percentage: ");
        }

        restaurantMenu.addItem(new Discount(name, discountType, percentage, minimumSubtotal, targetCategory, targetItemName));
        System.out.println("Discount added successfully.");
    }

    static void createCustomerOrder() {
        if (restaurantMenu.isEmpty()) {
            System.out.println("Menu is empty. Please add menu items first.");
            return;
        }

        currentOrder = new Order();
        boolean ordering = true;

        while (ordering) {
            restaurantMenu.displayMenu();
            System.out.print("Enter menu number or type done: ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("done")) {
                ordering = false;
            } else {
                try {
                    int menuNumber = Integer.parseInt(input);
                    MenuItem selectedItem = restaurantMenu.getItemByNumber(menuNumber);

                    if (selectedItem instanceof Discount) {
                        System.out.println("Discount items cannot be ordered directly.");
                    } else {
                        int quantity = readPositiveInt("Quantity: ");
                        currentOrder.addItem(selectedItem, quantity);
                        System.out.println(selectedItem.getName() + " added to order.");
                    }
                } catch (NumberFormatException exception) {
                    System.out.println("Invalid input. Enter a menu number or type done.");
                } catch (MenuItemNotFoundException exception) {
                    System.out.println(exception.getMessage());
                }
            }
        }

        if (currentOrder.isEmpty()) {
            System.out.println("No order was created.");
        } else {
            System.out.println("Order created successfully.");
        }
    }

    static void printReceipt() {
        if (currentOrder.isEmpty()) {
            System.out.println("No order available. Please create an order first.");
            return;
        }

        Discount discount = restaurantMenu.getFirstDiscount();
        String receipt = currentOrder.buildReceipt(discount);
        System.out.print(receipt);

        try {
            currentOrder.saveReceipt(RECEIPT_FILE, discount);
            System.out.println("Receipt saved to " + RECEIPT_FILE + ".");
        } catch (IOException exception) {
            System.out.println("Failed to save receipt: " + exception.getMessage());
        }
    }

    static void saveMenuToFile() {
        try {
            restaurantMenu.saveToFile(MENU_FILE);
            System.out.println("Menu saved to " + MENU_FILE + ".");
        } catch (IOException exception) {
            System.out.println("Failed to save menu: " + exception.getMessage());
        }
    }

    static void loadMenuFromFile() {
        try {
            restaurantMenu.loadFromFile(MENU_FILE);
            System.out.println("Menu loaded from " + MENU_FILE + ".");
        } catch (IOException | NumberFormatException exception) {
            System.out.println("Failed to load menu: " + exception.getMessage());
        }
    }

    static void loadLastReceipt() {
        try {
            String receipt = Order.loadReceipt(RECEIPT_FILE);
            System.out.print(receipt);
        } catch (IOException exception) {
            System.out.println("Failed to load receipt: " + exception.getMessage());
        }
    }

    static String readNonEmptyText(String prompt) {
        String value;

        do {
            System.out.print(prompt);
            value = scanner.nextLine().trim();

            if (value.isEmpty()) {
                System.out.println("Input cannot be empty.");
            }
        } while (value.isEmpty());

        return value;
    }

    static int readPositiveInt(String prompt) {
        int value;

        do {
            value = readInt(prompt);

            if (value <= 0) {
                System.out.println("Value must be greater than 0.");
            }
        } while (value <= 0);

        return value;
    }

    static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException exception) {
                System.out.println("Invalid number. Please try again.");
            }
        }
    }

    static double readPositiveDouble(String prompt) {
        double value;

        do {
            value = readDouble(prompt);

            if (value <= 0) {
                System.out.println("Value must be greater than 0.");
            }
        } while (value <= 0);

        return value;
    }

    static double readNonNegativeDouble(String prompt) {
        double value;

        do {
            value = readDouble(prompt);

            if (value < 0) {
                System.out.println("Value cannot be negative.");
            }
        } while (value < 0);

        return value;
    }

    static double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException exception) {
                System.out.println("Invalid number. Please try again.");
            }
        }
    }

    static String readDiscountType() {
        while (true) {
            System.out.println("Discount type:");
            System.out.println("1. Percentage");
            System.out.println("2. Buy 1 Get 1");
            System.out.println("3. Specific Item");

            int choice = readInt("Choose discount type: ");

            if (choice == 1) {
                return "PERCENTAGE";
            }

            if (choice == 2) {
                return "BOGO";
            }

            if (choice == 3) {
                return "ITEM_ONLY";
            }

            System.out.println("Invalid discount type. Please choose again.");
        }
    }

    static String readTargetCategory() {
        while (true) {
            System.out.println("Target category:");
            System.out.println("1. Food");
            System.out.println("2. Beverage");

            int choice = readInt("Choose target category: ");

            if (choice == 1) {
                return "Food";
            }

            if (choice == 2) {
                return "Beverage";
            }

            System.out.println("Invalid category. Please choose again.");
        }
    }
}
