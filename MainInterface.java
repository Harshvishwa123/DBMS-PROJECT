import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.sql.*;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class MainInterface {
    static final String DB_URL = "jdbc:mysql://localhost/RETAILSTORE";
    static final String USER = "root";
    static final String PASS = "Harsh30609";

    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\033[1;37m\033[1mWelcome to the Retail Store Management System!\033[0m");
        System.out.println("------------------------------------------------");
        System.out.println("Select an option:");
        System.out.println("\033[1;32m1. Enter as Admin\033[0m");
        System.out.println("\033[1;32m2. User Login\033[0m");
        System.out.println("\033[1;32m3. User Register\033[0m");
        System.out.println("------------------------------------------------");
        System.out.println("4. Exit");
        System.out.print("\033[1;35mEnter your choice: \033[0m");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                adminLogin(scanner);
                break;
            case 2:
                userLogin(scanner);
                break;
            case 3:
                registerUser(scanner);
                break;
            case 4:
                System.out.println("Exiting the program...");
                break;
            default:
                System.out.println("Invalid choice. Please enter a number between 1 and 4.");
        }

        scanner.close();
    }

    public static void adminLogin(Scanner scanner) {
        boolean loginSuccessful = false;

        while (!loginSuccessful) {
            System.out.println("\nADMIN LOGIN: ");
            System.out.print("\033[1;35mEnter username: \033[0m");
            String username = scanner.nextLine();
            System.out.print("\033[1;35mEnter password: \033[0m");
            String password = scanner.nextLine();

            try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                 Statement stmt = conn.createStatement()) {
                String sql = "SELECT * FROM Admin WHERE AdminName = '" + username + "' AND Password = '" + password + "'";
                ResultSet rs = stmt.executeQuery(sql);

                if (rs.next()) {
                    System.out.println("Admin login successful!");
                    CustomerAnalysisApp.main(new String[0]);
                    loginSuccessful = true;
                } else {
                    System.out.println("Incorrect username or password. Please try again.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void userLogin(Scanner scanner) throws SQLException {
        System.out.println("USER LOGIN: ");
        System.out.print("\033[1;35mEnter username: \033[0m");
        String username = scanner.nextLine();

        int loginAttempts = 0;
        boolean userFound = false;
        boolean accountBlocked = false;

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement()) {
            String checkUserSql = "SELECT * FROM Customer WHERE CustomerName = '" + username + "'";
            ResultSet userResultSet = stmt.executeQuery(checkUserSql);

            if (userResultSet.next()) {
                userFound = true;
                while (loginAttempts < 3 && !accountBlocked) {
                    System.out.print("\033[1;35mEnter password: \033[0m");
                    String password = scanner.nextLine();

                    String sql = "SELECT * FROM Customer WHERE CustomerName = '" + username + "' AND Password = '" + password + "'";
                    ResultSet rs = stmt.executeQuery(sql);

                    if (rs.next()) {
                        System.out.println("User login successful!");
                        OrderItemsApp.main(new String[0]);
                        return;
                    } else {
                        loginAttempts++;
                        if (loginAttempts == 3) {
                            System.out.println("Login attempts exceeded. Account blocked.");
                            accountBlocked = true;
                        } else {
                            System.out.println("Incorrect password. Please try again.\n");
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (!userFound) {
            System.out.println("User not found. Registering new user...\n");
            registerUser(scanner);
        }
    }


    public static void registerUser(Scanner scanner) throws SQLException {
        System.out.println("User Registration");
        // Prompt for user details
        System.out.print("\033[1;35mEnter CustomerName: \033[0m");
        String customerName = scanner.nextLine();
        System.out.print("\033[1;35mEnter House/ Street number: \033[0m");
        String houseNumber = scanner.nextLine();
        System.out.print("\033[1;35mEnter City: \033[0m");
        String city = scanner.nextLine();
        System.out.print("\033[1;35mEnter State: \033[0m");
        String state = scanner.nextLine();
        System.out.print("\033[1;35mEnter Pincode: \033[0m");
        String pincode = scanner.nextLine();
        System.out.print("\033[1;35mEnter EmailID: \033[0m");
        String email = scanner.nextLine();
        System.out.print("\033[1;35mEnter PhoneNumber: \033[0m");
        String phoneNumber = scanner.nextLine();
        System.out.print("\033[1;35mEnter Password: \033[0m");
        String password = scanner.nextLine();
        int newCustomerId = getTotalCustomerCount() + 1;
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement()) {
            String sql = "INSERT INTO Customer (CustomerID, CustomerName, HOUSENUMBER, City, State, Pincode, EmailID, PhoneNumber, Password) VALUES " +
                    "(" + newCustomerId + ", '" + customerName + "', '" + houseNumber + "', '" + city + "', '" + state + "', '" + pincode + "', '" + email + "', '" + phoneNumber + "', '" + password + "')";
            stmt.executeUpdate(sql);
            System.out.println("User registered successfully!\n");
            main(new String[0]);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static int getTotalCustomerCount() throws SQLException {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) AS total FROM Customer");
            if (rs.next()) {
                return rs.getInt("total");
            } else {
                return 0;
            }
        }
    }

}

class Item {
    private String name;
    private int quantity;
    private double price;

    public Item(String name, int quantity, double price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public void updateQuantity(int newQuantity) {
        this.quantity = newQuantity;
    }
}


class OrderItemsApp {
    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost/RETAILSTORE";
        String user = "root";
        String password = "Harsh30609";
        System.out.println("---------------------------------------------------------------------------------------------------------------------");
        System.out.println("\033[1;33mWELCOME TO OUR STORE!\033[0m");
        System.out.println("---------------------------------------------------------------------------------------------------------------------");
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            Scanner scanner = new Scanner(System.in);
            int choice;
            do {
                System.out.println("\n\033[1;32m");
                System.out.println("Select an option:");
                System.out.println("-------------------------");
                System.out.println("1. VIEW ITEMS");
                System.out.println("-------------------------");
                System.out.println("2. VIEW DEALS");
                System.out.println("-------------------------");
                System.out.println("3. EXIT");
                System.out.println("-------------------------");
                System.out.println("\033[0m");

                System.out.print("\033[1;31mEnter your choice: \033[0m");
                choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        viewItems(conn, scanner);
                        break;
                    case 2:
                        viewDeals(conn);
                        break;
                    case 3:
                        System.out.println("Exiting...");
                        System.out.println("\n------------------ THANK YOU FOR VISITING -------------------");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            } while (choice != 3);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void viewItems(Connection conn, Scanner scanner) throws SQLException {
        String sql = "SELECT itemName, QUANTITY, itemPrice FROM Items";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            String itemName = rs.getString("itemName");
            double price = rs.getDouble("itemPrice");
            System.out.println("\033[1;31mITEM\033[0m : " + itemName +
                    ", \033[1;33mPRICE\033[0m: $" + price);
            System.out.println("-------------------------------------------------------------------------------");

        }

        System.out.println("\nSelect Options:");
        System.out.println("\033[1;34m----------------------------------");
        System.out.println("1. ADD ITEMS TO CART");
        System.out.println("----------------------------------");
        System.out.println("2. GO BACK");
        System.out.println("----------------------------------\033[0m");
        System.out.print("\033[1;31mEnter your choice: \033[0m");
        int option = scanner.nextInt();
        switch (option) {
            case 1:
                addItemToCart(conn, scanner);
                break;
            case 2:
                break;
            default:
                System.out.println("Invalid choice.");
                break;
        }
    }

    private static void viewDeals(Connection conn) throws SQLException {

        String sql = "SELECT d.DealCode, d.Discount, d.Descriptions, i.itemName " +
                "FROM Deals d " +
                "JOIN ItemsDeals id ON d.DealCode = id.DealCode " +
                "JOIN Items i ON id.ProductID = i.ProductID";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("DEALS :");
            System.out.println("--------------------------------------------------------------------------------------------");
            while (rs.next()) {
                String dealCode = rs.getString("DealCode");
                double discount = rs.getDouble("Discount");
                String description = rs.getString("Descriptions");
                String itemName = rs.getString("itemName");
                System.out.println("\033[1;31mITEM\033[0m : " + itemName +
                        ", \033[1;32mDEAL CODE\033[0m : " + dealCode +
                        ", \033[1;33mDISCOUNT\033[0m : " + discount + "%" +
                        ", \033[1;34mDESCRIPTION\033[0m : " + description);
                System.out.println("------------------------------------------------------------------------------------------------------------------------");
            }
        }
    }


    private static void addItemToCart(Connection conn, Scanner scanner) throws SQLException {
        List<Item> cartItems = new ArrayList<>();
        double totalAmount = 0;

        while (true) {
            System.out.println("\nOptions:");
            System.out.println("\033[1;33m------------------------");
            System.out.println("1. PRESS 1 FOR ADDING ITEMS");
            System.out.println("------------------------");
            System.out.println("2. PRESS 2 FOR REMOVING ITEMS");
            System.out.println("------------------------");
            System.out.println("3. PRESS 3 FOR DISPLAYING CART");
            System.out.println("------------------------");
            System.out.println("4. PRESS 4 FOR PLACING ORDER");
            System.out.println("------------------------\033[0m");
            System.out.print("\033[1;31mEnter your choice: \033[0m");
            int option = scanner.nextInt();
            System.out.println("------------------------");

            switch (option) {
                case 1:
                    addItem(conn, scanner, cartItems);
                    break;
                case 2:
                    removeItem(scanner, cartItems);
                    break;
                case 3:
                    displayCart(cartItems);
                    break;
                case 4:
                    placeOrder(cartItems,conn);
                    return;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        }
    }

    private static void addItem(Connection conn, Scanner scanner, List<Item> cartItems) throws SQLException {
        System.out.print("Enter item name: ");
        String itemName = scanner.next().trim();
        scanner.nextLine();
        System.out.println("------------------------");
        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();
        System.out.println("------------------------");
        scanner.nextLine();

        String searchSql = "SELECT QUANTITY, itemPrice FROM Items WHERE LOWER(itemName) LIKE LOWER(?)";
        try (PreparedStatement searchStmt = conn.prepareStatement(searchSql)) {
            searchStmt.setString(1, "%" + itemName + "%");
            ResultSet rs = searchStmt.executeQuery();
            if (rs.next()) {
                int availableQuantity = rs.getInt("QUANTITY");
                double itemPrice = rs.getDouble("itemPrice");
                if (quantity <= availableQuantity) {
                    cartItems.add(new Item(itemName, quantity, itemPrice));
                    double totalAmount = calculateTotalAmount(cartItems);
                    System.out.println("\nItem added to cart. Total Amount: $" + totalAmount);
                } else {
                    System.out.println("Insufficient quantity available.");
                }
            } else {
                System.out.println("Item not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static void displayCart(List<Item> cartItems) {
        if (cartItems.isEmpty()) {
            System.out.println("Cart is empty.");
            return;
        }

        System.out.println("\nItems in the cart:");
        for (Item item : cartItems) {
            System.out.println("Item: " + item.getName() + ", Quantity: " + item.getQuantity() + ", Total Amount: $" + (item.getPrice() * item.getQuantity()));
        }
        System.out.println("Total Cart Amount: $" + calculateTotalAmount(cartItems));
    }
    private static double calculateTotalAmount(List<Item> cartItems) {
        double totalAmount = 0;
        for (Item item : cartItems) {
            totalAmount += item.getPrice() * item.getQuantity();
        }
        return totalAmount;
    }


    private static void removeItem(Scanner scanner, List<Item> cartItems) {
        if (cartItems.isEmpty()) {
            System.out.println("Cart is empty.");
            return;
        }

        System.out.println("Items in the cart:");
        for (int i = 0; i < cartItems.size(); i++) {
            System.out.println((i + 1) + ". " + cartItems.get(i).getName() + " - Quantity: " + cartItems.get(i).getQuantity());
        }

        System.out.print("\nEnter the name of the item to remove: ");
        String itemName = scanner.next();
        scanner.nextLine();
        System.out.println("--------------------------------------");
        System.out.print("Enter the quantity to remove: ");
        int quantityToRemove = scanner.nextInt();
        System.out.println("--------------------------------------");
        scanner.nextLine();

        boolean found = false;
        for (Item item : cartItems) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                found = true;
                if (item.getQuantity() > quantityToRemove) {
                    item.updateQuantity(item.getQuantity() - quantityToRemove);
                    double totalAmount = calculateTotalAmount(cartItems);
                    System.out.println("Item removed from cart. Total Amount: $" + totalAmount);
                } else if (item.getQuantity() == quantityToRemove) {
                    cartItems.remove(item);
                    double totalAmount = calculateTotalAmount(cartItems);
                    System.out.println("Item removed from cart. Total Amount: $" + totalAmount);

                } else {
                    System.out.println("Invalid quantity to remove.");
                }
                break;
            }
        }
        if (!found) {
            System.out.println("Item " + itemName + " not found in the cart.");
        }
    }
    private static void placeOrder(List<Item> cartItems, Connection conn) throws SQLException {
        double totalAmount = calculateTotalAmount(cartItems);

        Scanner scanner = new Scanner(System.in);

        System.out.println("\n\033[31mSelect payment method:\033[0m");
        System.out.println("\033[31m1. Pay online\033[0m");
        System.out.println("\033[31m2. Cash on delivery\033[0m");
        System.out.print("\033[31m\nEnter your choice: \033[0m");
        int paymentChoice = scanner.nextInt();

        switch (paymentChoice) {
            case 1:
                payOnline(totalAmount);
                updateItemQuantities(cartItems, conn);
                String deliveryPartner = getRandomDeliveryPartner(conn);
                System.out.println("\nYour order will be delivered by: " + deliveryPartner);
                System.exit(0);
            case 2:
                System.out.println("\n\033[34mOrder placed successfully. Cash on delivery.\033[0m");
                System.out.println("Total Amount: $" + totalAmount);
                String deliveryPartners = getRandomDeliveryPartner(conn);
                System.out.println("\nYour order will be delivered by: " + deliveryPartners);
                System.out.println("\n------------------ THANK YOU FOR ORDERING ------------------");
                updateItemQuantities(cartItems, conn);
                System.exit(0);
            default:
                System.out.println("Invalid choice. Order not placed.");
        }
    }
    private static String getRandomDeliveryPartner(Connection conn) throws SQLException {
        String deliveryPartner = null;
        String sql = "SELECT Name FROM DeliveryPartner ORDER BY RAND() LIMIT 1";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                deliveryPartner = rs.getString("Name");
            }
        }
        return deliveryPartner;
    }
    private static void payOnline(double totalAmount) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter total amount to pay online: $");
        double amountPaid = scanner.nextDouble();
        if (amountPaid == totalAmount) {
            System.out.println("\033[34mPayment successful. Order placed.\033[0m");
            System.out.println("\n------------------ THANK YOU FOR ORDERING ------------------");
        } else {
            System.out.println("Insufficient amount. Order not placed.");
        }
    }
    private static void updateItemQuantities(List<Item> cartItems, Connection conn) {
        try {
            for (Item item : cartItems) {
                String updateSql = "UPDATE Items SET QUANTITY = QUANTITY - ? WHERE itemName = ?";
                try (PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
                    updateStmt.setInt(1, item.getQuantity());
                    updateStmt.setString(2, item.getName());
                    updateStmt.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

class CustomerAnalysisApp {
    static final String DB_URL = "jdbc:mysql://localhost/RETAILSTORE";
    static final String USER = "root";
    static final String PASS = "Harsh30609";
    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost/RETAILSTORE";
        String user = "root";
        String password = "Harsh30609";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            Scanner scanner = new Scanner(System.in);

            boolean exit = false;
            while (!exit) {
                System.out.println("\n\033[1;33mWhat would you like to do?\033[0m");
                System.out.println("\033[1;34m----------------------------\033[0m");
                System.out.println("\033[1;34m\033[1;32m1. View all customers\033[0m");
                System.out.println("\033[1;34m\033[1;32m2. Analyze customers\033[0m");
                System.out.println("\033[1;34m\033[1;32m3. Inventory management\033[0m");
                System.out.println("\033[1;34m----------------------------\033[0m");
                System.out.println("\033[1;34m\033[1;32m4. Exit\033[0m");
                System.out.println("\033[1;34m----------------------------\033[0m");

                System.out.print("\033[1;31mEnter your choice: \033[0m");
                int choice = scanner.nextInt();
                System.out.println("----------------------------");

                switch (choice) {
                    case 1:
                        viewAllCustomers(conn);
                        break;
                    case 2:
                        analyzeCustomers(conn);
                        break;
                    case 3:
                        manageInventory(conn);

                    case 4:
                        exit = true;
                        System.out.println("Exiting the program...");
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 3.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void viewAllCustomers(Connection conn) throws SQLException {

        String sql = "SELECT * FROM Customer";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        System.out.println("\nALL CUSTOMERS :");
        System.out.println("-------------------------");
        while (rs.next()) {
            int customerId = rs.getInt("CustomerID");
            String customerName = rs.getString("CustomerName");
            String houseNumber = rs.getString("HOUSENUMBER");
            String city = rs.getString("City");
            String state = rs.getString("State");
            String pincode = rs.getString("Pincode");
            String email = rs.getString("EmailID");
            String phoneNumber = rs.getString("PhoneNumber");

            System.out.print("\033[1;34mCUSTOMER ID: \033[0m" + customerId + " | ");
            System.out.print("\033[1;32mNAME: \033[0m" + customerName + " | ");
            System.out.print("\033[1;33mHOUSE NUMBER: \033[0m" + houseNumber + " | ");
            System.out.print("\033[1;36mCITY: \033[0m" + city + " | ");
            System.out.print("\033[1;35mSTATE: \033[0m" + state + " | ");
            System.out.print("\033[1;31mPINCODE: \033[0m" + pincode + " | ");
            System.out.print("\033[1;93mEMAIL: \033[0m" + email + " | ");
            System.out.println("\033[1;94mPHONE NUMBER: \033[0m" + phoneNumber + " | ");
            System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

        }
    }

    public static void analyzeCustomers(Connection conn) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\n\033[1;33mCustomer Analysis:\033[0m");
            System.out.println("\033[1;34m-------------------------\033[0m");
            System.out.println("\033[1;32m1. Top 5 Customers with the Most Orders\033[0m");
            System.out.println("\033[1;32m2. Last Order by Customer by Date\033[0m");
            System.out.println("\033[1;32m3. Customers with the Highest Total Purchase Amount\033[0m");
            System.out.println("\033[1;32m4. Customers with the Highest Average Order Amount\033[0m");
            System.out.println("\033[1;32m5. Top 5 Customers with the Most Items Purchased\033[0m");
            System.out.println("\033[1;32m6. Customers who have Availed the Most Deals\033[0m");
            System.out.println("\033[1;34m----------------------------\033[0m");
            System.out.println("\033[1;32m7. Return to main menu\033[0m");
            System.out.print("\033[1;31mEnter your choice: \033[0m");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    top5CustomersWithMostOrders(conn);
                    break;
                case 2:
                    lastOrderByCustomerByDate(conn);
                    break;
                case 3:
                    customersWithHighestTotalPurchaseAmount(conn);
                    break;
                case 4:
                    customersWithHighestAverageOrderAmount(conn);
                    break;
                case 5:
                    top5CustomersWithMostItemsPurchased(conn);
                    break;
                case 6:
                    customersWithMostDeals(conn);
                    break;
                case 7:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 8.");
            }
        }
    }


    public static void top5CustomersWithMostOrders(Connection conn) throws SQLException {
        String sql = "SELECT c.CustomerID, c.CustomerName, COUNT(o.OrderID) AS OrderCount " +
                "FROM Customer c " +
                "LEFT JOIN Orders o ON c.CustomerID = o.CustomerID " +
                "GROUP BY c.CustomerID " +
                "ORDER BY OrderCount DESC " +
                "LIMIT 5";

        System.out.println("\n\033[1;32mTop 5 Customers with the Most Orders:\033[0m");
        System.out.println("\033[1;34m----------------------------------------\033[0m");
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int customerId = rs.getInt("CustomerID");
                String customerName = rs.getString("CustomerName");
                int orderCount = rs.getInt("OrderCount");
                System.out.println(customerId + ". " + customerName + ": " + orderCount + " orders");
            }
        }
    }

    public static void lastOrderByCustomerByDate(Connection conn) throws SQLException {
        String sql = "SELECT c.CustomerID, c.CustomerName, MAX(o.OrderDate) AS LastOrderDate " +
                "FROM Customer c " +
                "LEFT JOIN Orders o ON c.CustomerID = o.CustomerID " +
                "GROUP BY c.CustomerID";

        System.out.println("\n\033[1;32mLast Order by Customer by Date:\033[0m");
        System.out.println("\033[1;34m-------------------------------\033[0m");
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int customerId = rs.getInt("CustomerID");
                String customerName = rs.getString("CustomerName");
                Date lastOrderDate = rs.getDate("LastOrderDate");
                System.out.println(customerId + ". " + customerName + ": " + lastOrderDate);
            }
        }
    }

    public static void customersWithHighestTotalPurchaseAmount(Connection conn) throws SQLException {
        String sql = "SELECT c.CustomerID, c.CustomerName, SUM(ci.TotalSum) AS TotalPurchaseAmount " +
                "FROM Customer c " +
                "LEFT JOIN Cart ci ON c.CustomerID = ci.CustomerID " +
                "GROUP BY c.CustomerID " +
                "ORDER BY TotalPurchaseAmount DESC " +
                "LIMIT 5";

        System.out.println("\n\033[1;32mCustomers with the Highest Total Purchase Amount:\033[0m");
        System.out.println("\033[1;34m-----------------------------------------------\033[0m");
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int customerId = rs.getInt("CustomerID");
                String customerName = rs.getString("CustomerName");
                double totalPurchaseAmount = rs.getDouble("TotalPurchaseAmount");
                System.out.println(customerId + ". " + customerName + ": $" + totalPurchaseAmount);
            }
        }
    }

    public static void customersWithHighestAverageOrderAmount(Connection conn) throws SQLException {
        String sql = "SELECT c.CustomerID, c.CustomerName, AVG(ci.TotalSum) AS AvgOrderAmount " +
                "FROM Customer c " +
                "LEFT JOIN Cart ci ON c.CustomerID = ci.CustomerID " +
                "GROUP BY c.CustomerID " +
                "ORDER BY AvgOrderAmount DESC " +
                "LIMIT 5";

        System.out.println("\n\033[1;32mCustomers with the Highest Average Order Amount:\033[0m");
        System.out.println("\033[1;34m------------------------------------------------\033[0m");
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int customerId = rs.getInt("CustomerID");
                String customerName = rs.getString("CustomerName");
                double avgOrderAmount = rs.getDouble("AvgOrderAmount");
                System.out.println(customerId + ". " + customerName + ": $" + avgOrderAmount);
            }
        }
    }

    public static void top5CustomersWithMostItemsPurchased(Connection conn) throws SQLException {
        String sql = "SELECT c.CustomerID, c.CustomerName, SUM(ci.quantity) AS TotalItemsPurchased " +
                "FROM Customer c " +
                "LEFT JOIN Cart ci ON c.CustomerID = ci.CustomerID " +
                "GROUP BY c.CustomerID " +
                "ORDER BY TotalItemsPurchased DESC " +
                "LIMIT 5";

        System.out.println("\n\033[1;32mTop 5 Customers with the Most Items Purchased:\033[0m");
        System.out.println("\033[1;34m------------------------------------------\033[0m");
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int customerId = rs.getInt("CustomerID");
                String customerName = rs.getString("CustomerName");
                int totalItemsPurchased = rs.getInt("TotalItemsPurchased");
                System.out.println(customerId + ". " + customerName + ": " + totalItemsPurchased + " items");
            }
        }
    }


    public static void customersWithMostDeals(Connection conn) throws SQLException {
        String sql = "SELECT c.CustomerID, c.CustomerName, COUNT(DISTINCT d.DealCode) AS NumDealsAvailed " +
                "FROM Customer c " +
                "LEFT JOIN Cart ci ON c.CustomerID = ci.CustomerID " +
                "LEFT JOIN ItemsDeals id ON ci.ProductID = id.ProductID " +
                "LEFT JOIN Deals d ON id.DealCode = d.DealCode " +
                "GROUP BY c.CustomerID " +
                "ORDER BY NumDealsAvailed DESC " +
                "LIMIT 5";

        System.out.println("\n\033[1;32mCustomers who have Availed the Most Deals:\033[0m");
        System.out.println("\033[1;34m----------------------------------------\033[0m");
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int customerId = rs.getInt("CustomerID");
                String customerName = rs.getString("CustomerName");
                int numDealsAvailed = rs.getInt("NumDealsAvailed");
                System.out.println(customerId + ". " + customerName + ": Availed " + numDealsAvailed + " deals");
            }
        }
    }

    public static void manageInventory(Connection conn) throws SQLException {
        System.out.println("INVENTORY MANAGEMENT");
        System.out.println("--------------------");
        System.out.println("1. Add New Item");
        System.out.println("2. Update Item Details");
        System.out.println("3. Remove Item");
        System.out.println("--------------------");
        System.out.print("Enter your choice: ");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                addNewItem(conn);
                break;
            case 2:
                updateItemDetails();
                break;
            case 3:
                removeItem();
                break;
            default:
                System.out.println("Invalid choice.");
                break;
        }
    }

    public static void addNewItem(Connection conn) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("ADD NEW ITEM IN THE INVENTORY");
        System.out.print("Enter item name: ");
        String itemName = scanner.nextLine();
        System.out.print("Enter item quantity(INT): ");
        int quantity = scanner.nextInt();
        System.out.print("Enter item price(FLOAT): ");
        double price = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter item description(STRING): ");
        String itemDescription = scanner.nextLine();
        System.out.print("Enter item reviews(STRING): ");
        String reviews = scanner.nextLine();
        System.out.print("Enter item ratings(FLOAT OF OF 5): ");
        double ratings = scanner.nextDouble();

        try (PreparedStatement stmt = conn.prepareStatement("INSERT INTO Items (ProductID, itemName, QUANTITY, itemPrice, itemDescription, Reviews, Ratings) VALUES (?, ?, ?, ?, ?, ?, ?)")) {
            int totalItems = getTotalItems(conn);
            int newProductID = totalItems + 1;

            stmt.setInt(1, newProductID);
            stmt.setString(2, itemName);
            stmt.setInt(3, quantity);
            stmt.setDouble(4, price);
            stmt.setString(5, itemDescription);
            stmt.setString(6, reviews);
            stmt.setDouble(7, ratings);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("New item added to inventory.");
            } else {
                System.out.println("Failed to add new item to inventory.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int getTotalItems(Connection conn) {
        int totalItems = 0;
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT COUNT(*) AS TotalItems FROM Items")) {
            if (rs.next()) {
                totalItems = rs.getInt("TotalItems");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalItems;
    }

    public static void updateItemDetails() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("UPDATE ITEM DETAILS");
        System.out.print("Enter Product ID of the item to update: ");
        int productId = scanner.nextInt();
        scanner.nextLine();

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement selectStmt = conn.prepareStatement("SELECT * FROM Items WHERE ProductID = ?")) {
            selectStmt.setInt(1, productId);
            ResultSet resultSet = selectStmt.executeQuery();

            if (!resultSet.next()) {
                System.out.println("Item not found in the inventory.");
                return;
            }
            int currentQuantity = resultSet.getInt("QUANTITY");
            double currentPrice = resultSet.getDouble("itemPrice");
            String currentDescription = resultSet.getString("itemDescription");
            String currentReviews = resultSet.getString("Reviews");
            double currentRatings = resultSet.getDouble("Ratings");

            System.out.print("Enter new quantity (press Enter to keep current value: " + currentQuantity + "): ");
            String quantityInput = scanner.nextLine();
            int quantity = quantityInput.isEmpty() ? currentQuantity : Integer.parseInt(quantityInput);

            System.out.print("Enter new price (press Enter to keep current value: " + currentPrice + "): ");
            String priceInput = scanner.nextLine();
            double price = priceInput.isEmpty() ? currentPrice : Double.parseDouble(priceInput);

            System.out.print("Enter new item description (press Enter to keep current value: " + currentDescription + "): ");
            String itemDescription = scanner.nextLine();

            System.out.print("Enter new reviews (press Enter to keep current value: " + currentReviews + "): ");
            String reviews = scanner.nextLine();

            System.out.print("Enter new ratings (press Enter to keep current value: " + currentRatings + "): ");
            String ratingsInput = scanner.nextLine();
            double ratings = ratingsInput.isEmpty() ? currentRatings : Double.parseDouble(ratingsInput);

            try (PreparedStatement updateStmt = conn.prepareStatement("UPDATE Items SET QUANTITY = ?, itemPrice = ?, itemDescription = ?, Reviews = ?, Ratings = ? WHERE ProductID = ?")) {
                updateStmt.setInt(1, quantity);
                updateStmt.setDouble(2, price);
                updateStmt.setString(3, itemDescription.isEmpty() ? currentDescription : itemDescription);
                updateStmt.setString(4, reviews.isEmpty() ? currentReviews : reviews);
                updateStmt.setDouble(5, ratings);
                updateStmt.setInt(6, productId);
                int rowsAffected = updateStmt.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Item details updated successfully.");
                } else {
                    System.out.println("Failed to update item details.");
                }
            }
        }
    }


    public static void removeItem() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("REMOVE ITEM");
        System.out.print("Enter Product ID of the item to remove: ");
        int productId = scanner.nextInt();
        scanner.nextLine();
        String itemName;
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement selectStmt = conn.prepareStatement("SELECT itemName FROM Items WHERE ProductID = ?")) {
            selectStmt.setInt(1, productId);
            ResultSet resultSet = selectStmt.executeQuery();
            if (resultSet.next()) {
                itemName = resultSet.getString("itemName");
            } else {
                System.out.println("Item with Product ID " + productId + " not found in the inventory.");
                return;
            }
        }
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM Items WHERE ProductID = ?")) {
            stmt.setInt(1, productId);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Item with Product ID " + productId + " (" + itemName + ") removed from inventory.");
            } else {
                System.out.println("Failed to remove item with Product ID " + productId + " from inventory.");
            }
        }
    }

}




