import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);

    static Menu[] foods = new Menu[5];
    static Menu[] beverages = new Menu[5];
    static int foodCount = 0;
    static int beverageCount = 0;

    static String[] orderNames = new String[4];
    static int[] orderQty = new int[4];
    static int orderCount = 0;

    public static void main(String[] args) {
        mainMenu();
    }

    static void mainMenu() {
        System.out.println("\n========== MAIN MENU ==========");
        System.out.println("1. Input Menu");
        System.out.println("2. Order");
        System.out.println("0. Exit");
        System.out.print(">> ");
        int choice = sc.nextInt();
        sc.nextLine();

        if (choice == 1) {
            inputMenu();
            mainMenu();
        } else if (choice == 2) {
            orderMenu();
            mainMenu();
        } else if (choice == 0) {
            System.out.println("Thank you. Goodbye!");
        } else {
            System.out.println("Invalid choice.");
            mainMenu();
        }
    }

    static void inputMenu() {
        System.out.println("\n===== INPUT MENU =====");
        System.out.println("1. Input Foods");
        System.out.println("2. Input Beverages");
        System.out.println("0. Back");
        System.out.print(">> ");
        int choice = sc.nextInt();
        sc.nextLine();

        if (choice == 1) {
            inputFoods();
            inputMenu();
        } else if (choice == 2) {
            inputBeverages();
            inputMenu();
        } else if (choice == 0) {
            return;
        } else {
            System.out.println("Invalid choice.");
            inputMenu();
        }
    }

    static void orderMenu() {
        System.out.println("\n===== ORDER MENU =====");
        System.out.println("1. View Menu");
        System.out.println("2. Place Order");
        System.out.println("0. Back");
        System.out.print(">> ");
        int choice = sc.nextInt();
        sc.nextLine();

        if (choice == 1) {
            displayMenu();
            orderMenu();
        } else if (choice == 2) {
            if (foodCount == 0 && beverageCount == 0) {
                System.out.println("No menu items. Please input menu first.");
            } else {
                placeOrder();
            }
            orderMenu();
        } else if (choice == 0) {
            return;
        } else {
            System.out.println("Invalid choice.");
            orderMenu();
        }
    }

    static void inputFoods() {
        boolean hasMore = true;

        if (hasMore && foodCount < 5) {
            System.out.print("Food name: ");
            String name = sc.nextLine().trim();
            System.out.print("Food price: ");
            int price = sc.nextInt();
            sc.nextLine();
            foods[foodCount] = new Menu(name, price, "Food");
            foodCount++;
            if (foodCount < 5) {
                System.out.print("Add more? (y/n): ");
                hasMore = sc.next().equalsIgnoreCase("y");
                sc.nextLine();
            } else {
                System.out.println("Food menu full (max 5).");
                hasMore = false;
            }
        }

        if (hasMore && foodCount < 5) {
            System.out.print("Food name: ");
            String name = sc.nextLine().trim();
            System.out.print("Food price: ");
            int price = sc.nextInt();
            sc.nextLine();
            foods[foodCount] = new Menu(name, price, "Food");
            foodCount++;
            if (foodCount < 5) {
                System.out.print("Add more? (y/n): ");
                hasMore = sc.next().equalsIgnoreCase("y");
                sc.nextLine();
            } else {
                System.out.println("Food menu full (max 5).");
                hasMore = false;
            }
        }

        if (hasMore && foodCount < 5) {
            System.out.print("Food name: ");
            String name = sc.nextLine().trim();
            System.out.print("Food price: ");
            int price = sc.nextInt();
            sc.nextLine();
            foods[foodCount] = new Menu(name, price, "Food");
            foodCount++;
            if (foodCount < 5) {
                System.out.print("Add more? (y/n): ");
                hasMore = sc.next().equalsIgnoreCase("y");
                sc.nextLine();
            } else {
                System.out.println("Food menu full (max 5).");
                hasMore = false;
            }
        }

        if (hasMore && foodCount < 5) {
            System.out.print("Food name: ");
            String name = sc.nextLine().trim();
            System.out.print("Food price: ");
            int price = sc.nextInt();
            sc.nextLine();
            foods[foodCount] = new Menu(name, price, "Food");
            foodCount++;
            if (foodCount < 5) {
                System.out.print("Add more? (y/n): ");
                hasMore = sc.next().equalsIgnoreCase("y");
                sc.nextLine();
            } else {
                System.out.println("Food menu full (max 5).");
                hasMore = false;
            }
        }

        if (hasMore && foodCount < 5) {
            System.out.print("Food name: ");
            String name = sc.nextLine().trim();
            System.out.print("Food price: ");
            int price = sc.nextInt();
            sc.nextLine();
            foods[foodCount] = new Menu(name, price, "Food");
            foodCount++;
            if (foodCount < 5) {
                System.out.print("Add more? (y/n): ");
                hasMore = sc.next().equalsIgnoreCase("y");
                sc.nextLine();
            } else {
                System.out.println("Food menu full (max 5).");
                hasMore = false;
            }
        }

        System.out.println("Food input finished. (" + foodCount + " items)");
    }

    static void inputBeverages() {
        boolean hasMore = true;

        if (hasMore && beverageCount < 5) {
            System.out.print("Beverage name: ");
            String name = sc.nextLine().trim();
            System.out.print("Beverage price: ");
            int price = sc.nextInt();
            sc.nextLine();
            beverages[beverageCount] = new Menu(name, price, "Beverage");
            beverageCount++;
            if (beverageCount < 5) {
                System.out.print("Add more? (y/n): ");
                hasMore = sc.next().equalsIgnoreCase("y");
                sc.nextLine();
            } else {
                System.out.println("Beverage menu full (max 5).");
                hasMore = false;
            }
        }

        if (hasMore && beverageCount < 5) {
            System.out.print("Beverage name: ");
            String name = sc.nextLine().trim();
            System.out.print("Beverage price: ");
            int price = sc.nextInt();
            sc.nextLine();
            beverages[beverageCount] = new Menu(name, price, "Beverage");
            beverageCount++;
            if (beverageCount < 5) {
                System.out.print("Add more? (y/n): ");
                hasMore = sc.next().equalsIgnoreCase("y");
                sc.nextLine();
            } else {
                System.out.println("Beverage menu full (max 5).");
                hasMore = false;
            }
        }

        if (hasMore && beverageCount < 5) {
            System.out.print("Beverage name: ");
            String name = sc.nextLine().trim();
            System.out.print("Beverage price: ");
            int price = sc.nextInt();
            sc.nextLine();
            beverages[beverageCount] = new Menu(name, price, "Beverage");
            beverageCount++;
            if (beverageCount < 5) {
                System.out.print("Add more? (y/n): ");
                hasMore = sc.next().equalsIgnoreCase("y");
                sc.nextLine();
            } else {
                System.out.println("Beverage menu full (max 5).");
                hasMore = false;
            }
        }

        if (hasMore && beverageCount < 5) {
            System.out.print("Beverage name: ");
            String name = sc.nextLine().trim();
            System.out.print("Beverage price: ");
            int price = sc.nextInt();
            sc.nextLine();
            beverages[beverageCount] = new Menu(name, price, "Beverage");
            beverageCount++;
            if (beverageCount < 5) {
                System.out.print("Add more? (y/n): ");
                hasMore = sc.next().equalsIgnoreCase("y");
                sc.nextLine();
            } else {
                System.out.println("Beverage menu full (max 5).");
                hasMore = false;
            }
        }

        if (hasMore && beverageCount < 5) {
            System.out.print("Beverage name: ");
            String name = sc.nextLine().trim();
            System.out.print("Beverage price: ");
            int price = sc.nextInt();
            sc.nextLine();
            beverages[beverageCount] = new Menu(name, price, "Beverage");
            beverageCount++;
            if (beverageCount < 5) {
                System.out.print("Add more? (y/n): ");
                hasMore = sc.next().equalsIgnoreCase("y");
                sc.nextLine();
            } else {
                System.out.println("Beverage menu full (max 5).");
                hasMore = false;
            }
        }

        System.out.println("Beverage input finished. (" + beverageCount + " items)");
    }

    static void displayMenu() {
        System.out.println("\n============== MENU =============");
        System.out.println("--- Foods ---");
        int id = 1;

        if (foods[0] != null) {
            System.out.printf("%d. %-18s Rp %,d\n", id, foods[0].getName(), foods[0].getPrice());
            id++;
        }
        if (foods[1] != null) {
            System.out.printf("%d. %-18s Rp %,d\n", id, foods[1].getName(), foods[1].getPrice());
            id++;
        }
        if (foods[2] != null) {
            System.out.printf("%d. %-18s Rp %,d\n", id, foods[2].getName(), foods[2].getPrice());
            id++;
        }
        if (foods[3] != null) {
            System.out.printf("%d. %-18s Rp %,d\n", id, foods[3].getName(), foods[3].getPrice());
            id++;
        }
        if (foods[4] != null) {
            System.out.printf("%d. %-18s Rp %,d\n", id, foods[4].getName(), foods[4].getPrice());
            id++;
        }

        System.out.println("--- Beverages ---");

        if (beverages[0] != null) {
            System.out.printf("%d. %-18s Rp %,d\n", id, beverages[0].getName(), beverages[0].getPrice());
            id++;
        }
        if (beverages[1] != null) {
            System.out.printf("%d. %-18s Rp %,d\n", id, beverages[1].getName(), beverages[1].getPrice());
            id++;
        }
        if (beverages[2] != null) {
            System.out.printf("%d. %-18s Rp %,d\n", id, beverages[2].getName(), beverages[2].getPrice());
            id++;
        }
        if (beverages[3] != null) {
            System.out.printf("%d. %-18s Rp %,d\n", id, beverages[3].getName(), beverages[3].getPrice());
            id++;
        }
        if (beverages[4] != null) {
            System.out.printf("%d. %-18s Rp %,d\n", id, beverages[4].getName(), beverages[4].getPrice());
            id++;
        }

        System.out.println("================================");
    }

    static void placeOrder() {
        System.out.println("\n===== PLACE ORDER (max 4 items) =====");
        orderCount = 0;
        orderNames[0] = null;
        orderNames[1] = null;
        orderNames[2] = null;
        orderNames[3] = null;
        orderQty[0] = 0;
        orderQty[1] = 0;
        orderQty[2] = 0;
        orderQty[3] = 0;

        displayMenu();

        boolean hasMore = true;

        // Slot 0
        if (hasMore && orderCount < 4) {
            System.out.print("Item number: ");
            int id = sc.nextInt();
            sc.nextLine();
            Menu item = findMenuById(id);
            if (item == null) {
                System.out.println("Invalid menu number, skipping.");
            } else {
                System.out.print("Quantity: ");
                orderQty[0] = sc.nextInt();
                sc.nextLine();
                orderNames[0] = item.getName();
                orderCount++;
            }
            if (orderCount < 4) {
                System.out.print("Add more items? (y/n): ");
                hasMore = sc.next().equalsIgnoreCase("y");
                sc.nextLine();
            } else {
                System.out.println("Maximum 4 items reached.");
                hasMore = false;
            }
        }

        // Slot 1
        if (hasMore && orderCount < 4) {
            System.out.print("Item number: ");
            int id = sc.nextInt();
            sc.nextLine();
            Menu item = findMenuById(id);
            if (item == null) {
                System.out.println("Invalid menu number, skipping.");
            } else {
                System.out.print("Quantity: ");
                orderQty[1] = sc.nextInt();
                sc.nextLine();
                orderNames[1] = item.getName();
                orderCount++;
            }
            if (orderCount < 4) {
                System.out.print("Add more items? (y/n): ");
                hasMore = sc.next().equalsIgnoreCase("y");
                sc.nextLine();
            } else {
                System.out.println("Maximum 4 items reached.");
                hasMore = false;
            }
        }

        // Slot 2
        if (hasMore && orderCount < 4) {
            System.out.print("Item number: ");
            int id = sc.nextInt();
            sc.nextLine();
            Menu item = findMenuById(id);
            if (item == null) {
                System.out.println("Invalid menu number, skipping.");
            } else {
                System.out.print("Quantity: ");
                orderQty[2] = sc.nextInt();
                sc.nextLine();
                orderNames[2] = item.getName();
                orderCount++;
            }
            if (orderCount < 4) {
                System.out.print("Add more items? (y/n): ");
                hasMore = sc.next().equalsIgnoreCase("y");
                sc.nextLine();
            } else {
                System.out.println("Maximum 4 items reached.");
                hasMore = false;
            }
        }

        // Slot 3
        if (hasMore && orderCount < 4) {
            System.out.print("Item number: ");
            int id = sc.nextInt();
            sc.nextLine();
            Menu item = findMenuById(id);
            if (item == null) {
                System.out.println("Invalid menu number, skipping.");
            } else {
                System.out.print("Quantity: ");
                orderQty[3] = sc.nextInt();
                sc.nextLine();
                orderNames[3] = item.getName();
                orderCount++;
            }
            if (orderCount < 4) {
                System.out.print("Add more items? (y/n): ");
                hasMore = sc.next().equalsIgnoreCase("y");
                sc.nextLine();
            } else {
                System.out.println("Maximum 4 items reached.");
                hasMore = false;
            }
        }

        if (orderCount == 0) {
            System.out.println("No valid items ordered.");
            return;
        }

        calculateAndPrintReceipt();
    }

    static Menu findMenuById(int id) {
        int currentId = 1;

        if (foods[0] != null) {
            if (currentId == id) return foods[0];
            currentId++;
        }
        if (foods[1] != null) {
            if (currentId == id) return foods[1];
            currentId++;
        }
        if (foods[2] != null) {
            if (currentId == id) return foods[2];
            currentId++;
        }
        if (foods[3] != null) {
            if (currentId == id) return foods[3];
            currentId++;
        }
        if (foods[4] != null) {
            if (currentId == id) return foods[4];
            currentId++;
        }

        if (beverages[0] != null) {
            if (currentId == id) return beverages[0];
            currentId++;
        }
        if (beverages[1] != null) {
            if (currentId == id) return beverages[1];
            currentId++;
        }
        if (beverages[2] != null) {
            if (currentId == id) return beverages[2];
            currentId++;
        }
        if (beverages[3] != null) {
            if (currentId == id) return beverages[3];
            currentId++;
        }
        if (beverages[4] != null) {
            if (currentId == id) return beverages[4];
            currentId++;
        }

        return null;
    }

    static int findPrice(String name) {
        if (foods[0] != null && foods[0].getName().equalsIgnoreCase(name.trim()))
            return foods[0].getPrice();
        if (foods[1] != null && foods[1].getName().equalsIgnoreCase(name.trim()))
            return foods[1].getPrice();
        if (foods[2] != null && foods[2].getName().equalsIgnoreCase(name.trim()))
            return foods[2].getPrice();
        if (foods[3] != null && foods[3].getName().equalsIgnoreCase(name.trim()))
            return foods[3].getPrice();
        if (foods[4] != null && foods[4].getName().equalsIgnoreCase(name.trim()))
            return foods[4].getPrice();

        if (beverages[0] != null && beverages[0].getName().equalsIgnoreCase(name.trim()))
            return beverages[0].getPrice();
        if (beverages[1] != null && beverages[1].getName().equalsIgnoreCase(name.trim()))
            return beverages[1].getPrice();
        if (beverages[2] != null && beverages[2].getName().equalsIgnoreCase(name.trim()))
            return beverages[2].getPrice();
        if (beverages[3] != null && beverages[3].getName().equalsIgnoreCase(name.trim()))
            return beverages[3].getPrice();
        if (beverages[4] != null && beverages[4].getName().equalsIgnoreCase(name.trim()))
            return beverages[4].getPrice();

        return 0;
    }

    static boolean isBeverage(String name) {
        if (beverages[0] != null && beverages[0].getName().equalsIgnoreCase(name.trim()))
            return true;
        if (beverages[1] != null && beverages[1].getName().equalsIgnoreCase(name.trim()))
            return true;
        if (beverages[2] != null && beverages[2].getName().equalsIgnoreCase(name.trim()))
            return true;
        if (beverages[3] != null && beverages[3].getName().equalsIgnoreCase(name.trim()))
            return true;
        if (beverages[4] != null && beverages[4].getName().equalsIgnoreCase(name.trim()))
            return true;
        return false;
    }

    static void calculateAndPrintReceipt() {
        int subtotal = 0;
        int[] lineTotal = new int[4];
        int[] prices = new int[4];

        if (orderQty[0] > 0 && orderNames[0] != null) {
            prices[0] = findPrice(orderNames[0]);
            lineTotal[0] = prices[0] * orderQty[0];
            subtotal += lineTotal[0];
        }
        if (orderQty[1] > 0 && orderNames[1] != null) {
            prices[1] = findPrice(orderNames[1]);
            lineTotal[1] = prices[1] * orderQty[1];
            subtotal += lineTotal[1];
        }
        if (orderQty[2] > 0 && orderNames[2] != null) {
            prices[2] = findPrice(orderNames[2]);
            lineTotal[2] = prices[2] * orderQty[2];
            subtotal += lineTotal[2];
        }
        if (orderQty[3] > 0 && orderNames[3] != null) {
            prices[3] = findPrice(orderNames[3]);
            lineTotal[3] = prices[3] * orderQty[3];
            subtotal += lineTotal[3];
        }

        int tax = subtotal * 10 / 100;
        int service = 20000;

        boolean bogoApplied = false;
        int bogoDiscount = 0;
        String bogoItem = "";
        boolean bogoEligible = subtotal > 50000;

        if (bogoEligible && orderQty[0] > 0 && orderNames[0] != null && isBeverage(orderNames[0]) && !bogoApplied) {
            bogoDiscount = (orderQty[0] / 2) * prices[0];
            bogoItem = orderNames[0];
            bogoApplied = true;
        }
        if (bogoEligible && orderQty[1] > 0 && orderNames[1] != null && isBeverage(orderNames[1]) && !bogoApplied) {
            bogoDiscount = (orderQty[1] / 2) * prices[1];
            bogoItem = orderNames[1];
            bogoApplied = true;
        }
        if (bogoEligible && orderQty[2] > 0 && orderNames[2] != null && isBeverage(orderNames[2]) && !bogoApplied) {
            bogoDiscount = (orderQty[2] / 2) * prices[2];
            bogoItem = orderNames[2];
            bogoApplied = true;
        }
        if (bogoEligible && orderQty[3] > 0 && orderNames[3] != null && isBeverage(orderNames[3]) && !bogoApplied) {
            bogoDiscount = (orderQty[3] / 2) * prices[3];
            bogoItem = orderNames[3];
            bogoApplied = true;
        }

        int afterBogo = subtotal - bogoDiscount;

        int discount = 0;
        if (subtotal > 100000) {
            discount = afterBogo * 10 / 100;
        }

        int grandTotal = afterBogo - discount + tax + service;

        System.out.println("\n==========================================");
        System.out.println("               R E C E I P T              ");
        System.out.println("==========================================");
        System.out.printf("%-20s %4s  %10s  %10s\n", "Item", "Qty", "@ Price", "Total");
        System.out.println("------------------------------------------");

        if (orderQty[0] > 0 && orderNames[0] != null)
            System.out.printf("%-20s %4d  %10d  %10d\n",
                    orderNames[0], orderQty[0], prices[0], lineTotal[0]);
        if (orderQty[1] > 0 && orderNames[1] != null)
            System.out.printf("%-20s %4d  %10d  %10d\n",
                    orderNames[1], orderQty[1], prices[1], lineTotal[1]);
        if (orderQty[2] > 0 && orderNames[2] != null)
            System.out.printf("%-20s %4d  %10d  %10d\n",
                    orderNames[2], orderQty[2], prices[2], lineTotal[2]);
        if (orderQty[3] > 0 && orderNames[3] != null)
            System.out.printf("%-20s %4d  %10d  %10d\n",
                    orderNames[3], orderQty[3], prices[3], lineTotal[3]);

        System.out.println("------------------------------------------");
        System.out.printf("%-36s  %10d\n", "Subtotal", subtotal);
        System.out.printf("%-36s  %10d\n", "Tax (10%)", tax);
        System.out.printf("%-36s  %10d\n", "Service Fee", service);

        if (bogoApplied) {
            System.out.printf("%-36s  %10d\n", "Buy 1 Get 1 (" + bogoItem + ")", -bogoDiscount);
            System.out.println("------------------------------------------");
            System.out.printf("%-36s  %10d\n", "Total after BOGO", afterBogo);
        }

        if (discount > 0) {
            System.out.printf("%-36s  %10d\n", "Discount (10%)", -discount);
        }

        System.out.println("==========================================");
        System.out.printf("%-36s  %10d\n", "GRAND TOTAL", grandTotal);
        System.out.println("==========================================");
    }
}
