
package priority_queue_hospital_management;

import java.util.PriorityQueue;
import java.util.LinkedList;
import java.util.Comparator;
import java.util.Queue;

public class Priority_Queue_Hospital_Management {
    
    public static void main(String[] args) {
        
        Queue<String> patients = new LinkedList<>();
        
        patients.offer("Anna (Severity: 3)");
        patients.offer("Bob (Severity: 1)");
        patients.offer("Carla (Severity: 2)");
        patients.offer("Dave (Severity: 1)");
        
        System.out.println("=== Arrival Order ===");
        System.out.println("Arrival Order: " + patients);
        
        // Priority Queue to treat lowest severity number first
        PriorityQueue<String> treating = new PriorityQueue<>(
            Comparator.comparingInt(s -> Integer.parseInt(s.replaceAll("[^0-9]", "")))
        );
        
        treating.addAll(patients);
        
        System.out.println("\n=== Treatment Order ===");
        while (!treating.isEmpty()) {
            System.out.println("Treating: " + treating.poll());
        }
    }
}
