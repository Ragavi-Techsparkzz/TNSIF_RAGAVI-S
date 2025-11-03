package com.tns.fooddeliverysystem.application;
import com.tns.fooddeliverysystem.entities.*;
import com.tns.fooddeliverysystem.services.*;

import java.util.*;

public class FoodDeliverySystem {
    static Map<Integer, Restaurant> restaurants = new HashMap<>();
    static Map<Integer, FoodItem> foodItems = new HashMap<>();
    static Map<Integer, Customer> customers = new HashMap<>();
    static Map<Integer, DeliveryPerson> deliveryPersons = new HashMap<>();
    static Map<Integer, Order> orders = new HashMap<>();
    static int orderCounter = 1;

    static CustomerService customerService = new CustomerService();
    static FoodService foodService = new FoodService();
    static OrderService orderService = new OrderService();

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println(ANSI_GREEN + "**************************************" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "       Welcome to Food Delivery       " + ANSI_RESET);
            System.out.println(ANSI_GREEN + "**************************************" + ANSI_RESET);
            System.out.println("1. Admin Menu");
            System.out.println("2. Customer Menu");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int mainOpt = sc.nextInt();
            if (mainOpt == 1) adminMenu(sc);
            else if (mainOpt == 2) customerMenu(sc);
            else break;
        }
    }

    static void adminMenu(Scanner sc) {
        while (true) {
            System.out.println(ANSI_YELLOW + "\n************** Admin Menu **************" + ANSI_RESET);
            System.out.println("1. Add Restaurant");
            System.out.println("2. Add Food Item to Restaurant");
            System.out.println("3. Remove Food Item from Restaurant");
            System.out.println("4. View Restaurants and Menus");
            System.out.println("5. View Orders");
            System.out.println("6. Add Delivery Person");
            System.out.println("7. Assign Delivery Person to Order");
            System.out.println("8. Search Restaurants");
            System.out.println("9. Search Food Items");
            System.out.println("10. Exit");
            System.out.print("Choose an option: ");
            int opt = sc.nextInt();

            if (opt == 1) {
                System.out.print("Enter Restaurant ID: "); int rid = sc.nextInt();
                System.out.print("Enter Restaurant Name: "); sc.nextLine(); String rname = sc.nextLine();
                restaurants.put(rid, new Restaurant(rid, rname));
                System.out.println("Restaurant added successfully!");
            } else if (opt == 2) {
                System.out.print("Enter Restaurant ID: "); int rid = sc.nextInt();
                if (!restaurants.containsKey(rid)) { System.out.println("Restaurant not found."); continue; }
                System.out.print("Enter Food Item ID: "); int fid = sc.nextInt();
                System.out.print("Enter Food Item Name: "); sc.nextLine(); String fname = sc.nextLine();
                System.out.print("Enter Food Item Price: "); double fprice = sc.nextDouble();
                FoodItem fi = new FoodItem(fid, fname, fprice);
                foodService.addFoodToRestaurant(restaurants.get(rid), fi);
                foodItems.put(fid, fi);
                System.out.println("Food item added successfully!");
            } else if (opt == 3) {
                System.out.print("Enter Restaurant ID: "); int rid = sc.nextInt();
                if (!restaurants.containsKey(rid)) { System.out.println("Restaurant not found."); continue; }
                System.out.print("Enter Food Item ID: "); int fid = sc.nextInt();
                foodService.removeFoodFromRestaurant(restaurants.get(rid), fid);
                foodItems.remove(fid);
                System.out.println("Food item removed successfully!");
            } else if (opt == 4) {
                for (Restaurant r : restaurants.values())
                    System.out.println(r);
            } else if (opt == 5) {
                for (Order o : orders.values()) System.out.println(o);
            } else if (opt == 6) {
                System.out.print("Enter Delivery Person ID: "); int did = sc.nextInt();
                System.out.print("Enter Delivery Person Name: "); sc.nextLine(); String dname = sc.nextLine();
                System.out.print("Enter Contact No.: "); long dnum = sc.nextLong();
                deliveryPersons.put(did, new DeliveryPerson(did, dname, dnum));
                System.out.println("Delivery person added successfully!");
            } else if (opt == 7) {
                System.out.print("Enter Order ID: "); int oid = sc.nextInt();
                if (!orders.containsKey(oid)) { System.out.println("Order not found."); continue; }
                System.out.print("Enter Delivery Person ID: "); int did = sc.nextInt();
                if (!deliveryPersons.containsKey(did)) { System.out.println("Delivery person not found."); continue; }
                orderService.assignDeliveryPerson(orders.get(oid), deliveryPersons.get(did));
                System.out.println("Delivery person assigned to order successfully!");
            } else if (opt == 8) {
                searchRestaurantsByName(sc);
            } else if (opt == 9) {
                searchFoodItemsByName(sc);
            } else if (opt == 10) {
                break;
            } else {
                System.out.println("Invalid option. Please try again.");
            }
        }
    }

    static void customerMenu(Scanner sc) {
        while (true) {
            System.out.println(ANSI_YELLOW + "\n************* Customer Menu *************" + ANSI_RESET);
            System.out.println("1. Add Customer");
            System.out.println("2. View Food Items");
            System.out.println("3. Add Food to Cart");
            System.out.println("4. View Cart");
            System.out.println("5. Place Order");
            System.out.println("6. View Orders");
            System.out.println("7. Search Restaurants");
            System.out.println("8. Search Food Items");
            System.out.println("9. Exit");
            System.out.print("Choose an option: ");
            int opt = sc.nextInt();

            if (opt == 1) {
                System.out.print("Enter User ID: "); int uid = sc.nextInt();
                System.out.print("Enter Username: "); sc.nextLine(); String uname = sc.nextLine();
                System.out.print("Enter Contact No.: "); long unum = sc.nextLong();
                customers.put(uid, new Customer(uid, uname, unum));
                System.out.println("Customer created successfully!");
            } else if (opt == 2) {
                for (Restaurant r : restaurants.values())
                    System.out.println(r);
            } else if (opt == 3) {
                System.out.print("Enter Customer ID: "); int cid = sc.nextInt();
                if (!customers.containsKey(cid)) { System.out.println("Customer not found."); continue; }
                System.out.print("Enter Restaurant ID: "); int rid = sc.nextInt();
                if (!restaurants.containsKey(rid)) { System.out.println("Restaurant not found."); continue; }
                System.out.print("Enter Food Item ID: "); int fid = sc.nextInt();
                FoodItem f = null;
                for (FoodItem fi : restaurants.get(rid).getMenu())
                    if (fi.getId() == fid) f = fi;
                if (f == null) { System.out.println("Food item not found."); continue; }
                System.out.print("Enter Quantity: "); int qty = sc.nextInt();
                customerService.addToCart(customers.get(cid), f, qty);
                System.out.println("Food item added to cart!");
            } else if (opt == 4) {
                System.out.print("Enter Customer ID: "); int cid = sc.nextInt();
                if (!customers.containsKey(cid)) { System.out.println("Customer not found."); continue; }
                System.out.println(customers.get(cid).getCart());
            } else if (opt == 5) {
                System.out.print("Enter Customer ID: "); int cid = sc.nextInt();
                if (!customers.containsKey(cid)) { System.out.println("Customer not found."); continue; }
                System.out.print("Enter Delivery Address: "); sc.nextLine(); String addr = sc.nextLine();
                Customer c = customers.get(cid);
                Cart cart = c.getCart();
                if (cart.getItems().isEmpty()) { System.out.println("Cart is empty."); continue; }
                Order o = orderService.placeOrder(orderCounter, c, new HashMap<>(cart.getItems()), addr);
                orders.put(orderCounter, o);
                System.out.println("Order placed successfully! Your order ID is " + orderCounter);
                orderCounter++;
                cart.getItems().clear();
            } else if (opt == 6) {
                System.out.print("Enter Customer ID: "); int cid = sc.nextInt();
                for (Order o : orders.values())
                    if (o.getCustomer().getUserId() == cid)
                        System.out.println(o);
            } else if (opt == 7) {
                searchRestaurantsByName(sc);
            } else if (opt == 8) {
                searchFoodItemsByName(sc);
            } else if (opt == 9) {
                break;
            } else {
                System.out.println("Invalid option. Please try again.");
            }
        }
    }

    // Search restaurants by partial name match
    public static void searchRestaurantsByName(Scanner sc) {
        System.out.print("Enter restaurant name or keyword to search: ");
        sc.nextLine(); // clear buffer
        String keyword = sc.nextLine().toLowerCase();
        boolean found = false;
        System.out.println("\nSearch Results:");
        for (Restaurant r : restaurants.values()) {
            if (r.getName().toLowerCase().contains(keyword)) {
                System.out.println("ID: " + r.getId() + ", Name: " + r.getName());
                found = true;
            }
        }
        if (!found) System.out.println("No restaurants found matching '" + keyword + "'");
    }

    // Search food items by partial name match across all restaurants
    public static void searchFoodItemsByName(Scanner sc) {
        System.out.print("Enter food item name or keyword to search: ");
        sc.nextLine(); // clear buffer
        String keyword = sc.nextLine().toLowerCase();
        boolean found = false;
        System.out.println("\nSearch Results:");
        for (Restaurant r : restaurants.values()) {
            for (FoodItem f : r.getMenu()) {
                if (f.getName().toLowerCase().contains(keyword)) {
                    System.out.println("Restaurant: " + r.getName() + " --> ID: " + f.getId() + ", Name: " + f.getName() + ", Price: " + f.getPrice());
                    found = true;
                }
            }
        }
        if (!found) System.out.println("No food items found matching '" + keyword + "'");
    }
}
