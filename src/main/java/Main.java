import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static final int TAX_PERCENT = 10;
    static final int SERVICE_FEE = 20000;

    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Menu> foods = new ArrayList<>();
    static ArrayList<Menu> beverages = new ArrayList<>();
    static ArrayList<Menu> orderedMenus = new ArrayList<>();
    static ArrayList<Integer> orderedQuantities = new ArrayList<>();

    public static void main(String[] args) {
        mainMenu();
    }

    static void mainMenu() {
        boolean running = true;

        while (running) {
            System.out.println("\n========== MAIN MENU ==========");
            System.out.println("1. Input Menu");
            System.out.println("2. Order");
            System.out.println("0. Exit");

            int choice = readInt("Choose option: ");

            if (choice == 1) {
                inputMenu();
            } else if (choice == 2) {
                orderMenu();
            } else if (choice == 0) {
                running = false;
                System.out.println("Thank you. Goodbye!");
            } else {
                System.out.println("Invalid option. Please choose again.");
            }
        }
    }

    static void inputMenu() {
        boolean inInputMenu = true;

        while (inInputMenu) {
            System.out.println("\n===== INPUT MENU =====");
            System.out.println("1. Input Foods");
            System.out.println("2. Input Beverages");
            System.out.println("0. Back");

            int choice = readInt("Choose option: ");

            if (choice == 1) {
                inputItems("Food");
            } else if (choice == 2) {
                inputItems("Beverage");
            } else if (choice == 0) {
                inInputMenu = false;
            } else {
                System.out.println("Invalid option. Please choose again.");
            }
        }
    }

    static void orderMenu() {
        boolean inOrderMenu = true;

        while (inOrderMenu) {
            System.out.println("\n===== ORDER MENU =====");
            System.out.println("1. View Menu");
            System.out.println("2. Place Order");
            System.out.println("0. Back");

            int choice = readInt("Choose option: ");

            if (choice == 1) {
                displayMenu();
            } else if (choice == 2) {
                if (foods.isEmpty() && beverages.isEmpty()) {
                    System.out.println("No menu items. Please input menu first.");
                } else {
                    placeOrder();
                }
            } else if (choice == 0) {
                inOrderMenu = false;
            } else {
                System.out.println("Invalid option. Please choose again.");
            }
        }
    }

    static void inputItems(String category) {
        boolean adding = true;

        while (adding) {
            System.out.println("\n===== INPUT " + category.toUpperCase() + " =====");
            String name = readNonEmptyText(category + " name: ");
            int price = readPositiveInt(category + " price: ");

            if (category.equals("Food")) {
                foods.add(new Menu(name, price, category));
            } else {
                beverages.add(new Menu(name, price, category));
            }

            System.out.println(category + " added successfully.");
            adding = readYesNo("Add more? (y/n): ");
        }
    }

    static void displayMenu() {
        int id = 1;

        System.out.println("\n============== MENU ==============");
        System.out.println("--- Foods ---");
        for (int i = 0; i < foods.size(); i++) {
            Menu menu = foods.get(i);
            System.out.printf("%d. %-20s Rp %,d\n", id, menu.getName(), menu.getPrice());
            id++;
        }

        System.out.println("--- Beverages ---");
        for (int i = 0; i < beverages.size(); i++) {
            Menu menu = beverages.get(i);
            System.out.printf("%d. %-20s Rp %,d\n", id, menu.getName(), menu.getPrice());
            id++;
        }
        System.out.println("==================================");
    }

    static void placeOrder() {
        orderedMenus.clear();
        orderedQuantities.clear();

        System.out.println("\n===== PLACE ORDER =====");
        boolean ordering = true;

        while (ordering) {
            displayMenu();
            System.out.print("Enter menu number or type done: ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("done")) {
                ordering = false;
            } else {
                try {
                    int menuId = Integer.parseInt(input);
                    Menu selectedMenu = getMenuById(menuId);

                    if (selectedMenu == null) {
                        System.out.println("Invalid menu number. Please try again.");
                    } else {
                        int quantity = readPositiveInt("Quantity: ");
                        addOrderItem(selectedMenu, quantity);
                        System.out.println(selectedMenu.getName() + " added to order.");
                    }
                } catch (NumberFormatException exception) {
                    System.out.println("Invalid input. Enter a menu number or type done.");
                }
            }
        }

        if (orderedMenus.isEmpty()) {
            System.out.println("No valid items ordered.");
        } else {
            printReceipt();
        }
    }

    static Menu getMenuById(int id) {
        if (id < 1 || id > foods.size() + beverages.size()) {
            return null;
        }

        if (id <= foods.size()) {
            return foods.get(id - 1);
        }

        return beverages.get(id - foods.size() - 1);
    }

    static void addOrderItem(Menu selectedMenu, int quantity) {
        for (int i = 0; i < orderedMenus.size(); i++) {
            if (orderedMenus.get(i) == selectedMenu) {
                int updatedQuantity = orderedQuantities.get(i) + quantity;
                orderedQuantities.set(i, updatedQuantity);
                return;
            }
        }

        orderedMenus.add(selectedMenu);
        orderedQuantities.add(quantity);
    }

    static void printReceipt() {
        int subtotal = calculateSubtotal();
        int tax = subtotal * TAX_PERCENT / 100;
        int service = SERVICE_FEE;
        int bogoDiscount = calculateBogoDiscount(subtotal);
        int totalAfterBogo = subtotal - bogoDiscount;
        int discount = 0;

        if (subtotal > 100000) {
            discount = totalAfterBogo * 10 / 100;
        }

        int grandTotal = totalAfterBogo - discount + tax + service;

        System.out.println("\n==============================================");
        System.out.println("                   RECEIPT                    ");
        System.out.println("==============================================");
        System.out.printf("%-20s %5s %10s %10s\n", "Item", "Qty", "Price", "Total");
        System.out.println("----------------------------------------------");

        for (int i = 0; i < orderedMenus.size(); i++) {
            Menu menu = orderedMenus.get(i);
            int quantity = orderedQuantities.get(i);
            int lineTotal = menu.getPrice() * quantity;
            System.out.printf("%-20s %5d %10d %10d\n", menu.getName(), quantity, menu.getPrice(), lineTotal);
        }

        System.out.println("----------------------------------------------");
        System.out.printf("%-35s %10d\n", "Subtotal", subtotal);
        System.out.printf("%-35s %10d\n", "Tax (10%)", tax);
        System.out.printf("%-35s %10d\n", "Service Fee", service);

        if (bogoDiscount > 0) {
            System.out.printf("%-35s %10d\n", "Buy 1 Get 1", -bogoDiscount);
            System.out.printf("%-35s %10d\n", "Total after BOGO", totalAfterBogo);
        }

        if (discount > 0) {
            System.out.printf("%-35s %10d\n", "Discount (10%)", -discount);
        }

        System.out.println("==============================================");
        System.out.printf("%-35s %10d\n", "GRAND TOTAL", grandTotal);
        System.out.println("==============================================");
    }

    static int calculateSubtotal() {
        int subtotal = 0;

        for (int i = 0; i < orderedMenus.size(); i++) {
            subtotal += orderedMenus.get(i).getPrice() * orderedQuantities.get(i);
        }

        return subtotal;
    }

    static int calculateBogoDiscount(int subtotal) {
        if (subtotal <= 50000) {
            return 0;
        }

        for (int i = 0; i < orderedMenus.size(); i++) {
            Menu menu = orderedMenus.get(i);
            int quantity = orderedQuantities.get(i);

            if (menu.getCategory().equals("Beverage") && quantity >= 2) {
                return (quantity / 2) * menu.getPrice();
            }
        }

        return 0;
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

    static boolean readYesNo(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("y")) {
                return true;
            }

            if (input.equalsIgnoreCase("n")) {
                return false;
            }

            System.out.println("Please enter y or n.");
        }
    }
}
