package canteen_ordering_queue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;

public class Canteen_Ordering_Queue {
    
    
    private static final Queue<Integer> orderQueue = new LinkedList<>();
    private static final Map<Integer, String> orderStorage = new HashMap<>();
    private static int nextOrderId = 101; 

 
    private static final Map<Integer, String> simpleMenu = new HashMap<>();
    
    
    static {
        simpleMenu.put(1, "Hotdog (25.00Php)");
        simpleMenu.put(2, "Fries (15.00Php)");
        simpleMenu.put(3, "Soda (20.00Php)");
        simpleMenu.put(4, "Bananaque (10.00Php)");
        simpleMenu.put(5, "Coffee (5.00Php)");
    }

    public static void main(String[] args) {
        
        while (true) {
            String menu = """
                \n Canteen Ordering Queue System (GUI) 
                1. Place New Order:
                2. Serve Next Order:
                3. Check Next Order:
                4. View All Orders in Queue:
                5. Exit
                \n""";
            
            String choiceInput = JOptionPane.showInputDialog(
                null, 
                menu, 
                "Canteen Queue Manager", 
                JOptionPane.QUESTION_MESSAGE
            );

           
            if (choiceInput == null) {
    
                JOptionPane.showMessageDialog(null, "Exiting system. Goodbye!");
                System.exit(0);
            }

            int choice;
            try {
           
                choice = Integer.parseInt(choiceInput.trim());
            } catch (NumberFormatException e) {
        
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a menu number (1-5).", "Error", JOptionPane.ERROR_MESSAGE);
                continue;
            }

            switch (choice) {
                case 1:
                    placeOrder();
                    break;
                case 2:
                    serveOrder();
                    break;
                case 3:
                    peekNextOrder();
                    break;
                case 4:
                    viewAllOrders();
                    break;
                case 5:
                    JOptionPane.showMessageDialog(null, "Exiting system. Goodbye!");
                    System.exit(0);
                default:
                    JOptionPane.showMessageDialog(null, "Invalid choice. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    public static void placeOrder() {

        String StudentName = JOptionPane.showInputDialog(
            null, 
            "Student's Name: ", 
            "Place Order", 
            JOptionPane.PLAIN_MESSAGE
        );
        
    
        if (StudentName == null || StudentName.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Order placement cancelled or name was empty.");
            return;
        }

      
        StringBuilder menuBuilder = new StringBuilder();
        menuBuilder.append(" Canteen Menu \n");
        for (Map.Entry<Integer, String> entry : simpleMenu.entrySet()) {
            menuBuilder.append(entry.getKey()).append(". ").append(entry.getValue()).append("\n");
        }
        menuBuilder.append("--------------------\n");
        menuBuilder.append("Enter Product ID (1-5) or type your custom order (e.g., 'Double Hotdog'):");
        
      
        String rawInput = JOptionPane.showInputDialog(
            null, 
            menuBuilder.toString(), 
            "Select Item", 
            JOptionPane.PLAIN_MESSAGE
        );

       
        if (rawInput == null || rawInput.trim().isEmpty()) {
             JOptionPane.showMessageDialog(null, "Order placement cancelled or item was empty.");
            return;
        }
        
        String orderDetails = "";
        String trimmedInput = rawInput.trim();

        try {
            
            int productId = Integer.parseInt(trimmedInput);
            
            if (simpleMenu.containsKey(productId)) {
                
                orderDetails = simpleMenu.get(productId);
            } else {
               
                orderDetails = rawInput;
            }
        } catch (NumberFormatException e) {
            
            orderDetails = rawInput;
        }

        String finalOrder = StudentName + " ordered: " + orderDetails;

        int newOrderId = nextOrderId;
        orderStorage.put(newOrderId, finalOrder);
        orderQueue.offer(newOrderId);
        
        JOptionPane.showMessageDialog(
            null, 
            "Order #" + newOrderId + " added:\n\"" + finalOrder + "\"",
            "Order Confirmation",
            JOptionPane.INFORMATION_MESSAGE
        );
        
        nextOrderId++;
    }
    public static void serveOrder() {
        Integer servedOrderId = orderQueue.poll();
        
               if (servedOrderId != null) {
            String servedOrder = orderStorage.get(servedOrderId);

            JOptionPane.showMessageDialog(
                null,
                "Serving Order #" + servedOrderId + ":\n" + servedOrder,
                "Order Served",
                JOptionPane.INFORMATION_MESSAGE
            );

            // Remove served order from storage
            orderStorage.remove(servedOrderId);

        } else {
            JOptionPane.showMessageDialog(
                null,
                "No orders in the queue.",
                "Serve Order",
                JOptionPane.WARNING_MESSAGE
            );
        }
    }

    public static void peekNextOrder() {
        Integer nextOrderId = orderQueue.peek();

        if (nextOrderId != null) {
            String nextOrder = orderStorage.get(nextOrderId);

            JOptionPane.showMessageDialog(
                null,
                "Next Order in Queue:\nOrder #" + nextOrderId + "\n" + nextOrder,
                "Next Order",
                JOptionPane.INFORMATION_MESSAGE
            );
        } else {
            JOptionPane.showMessageDialog(
                null,
                "No orders currently in the queue.",
                "Next Order",
                JOptionPane.WARNING_MESSAGE
            );
        }
    }

    public static void viewAllOrders() {
        if (orderQueue.isEmpty()) {
            JOptionPane.showMessageDialog(
                null,
                "The order queue is empty.",
                "All Orders",
                JOptionPane.INFORMATION_MESSAGE
            );
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("All Current Orders in Queue:\n\n");

        for (Integer orderId : orderQueue) {
            sb.append("Order #").append(orderId)
              .append(": ").append(orderStorage.get(orderId))
              .append("\n");
        }

        JOptionPane.showMessageDialog(
            null,
            sb.toString(),
            "Order Queue",
            JOptionPane.INFORMATION_MESSAGE
        );
    }
}