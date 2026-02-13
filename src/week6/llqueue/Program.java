package week6.llqueue;

public class Program {
    public static void main(String[] args) {
        int[] numbers = { 83, 44, 57, 66, 92, 49, 64, 55 };

        // Initialize a new Queue and add numbers
        Queue numQueue = new Queue();
        for (int number : numbers) {
            numQueue.enqueue(number);
        }

        // Print queue
        System.out.print("Queue: ");
        numQueue.print(System.out);

        // Dequeue each item and print the queue until empty
        while (!numQueue.isEmpty()) {
            System.out.println("Dequeued " + numQueue.dequeue());
            System.out.print("Queue: ");
            numQueue.print(System.out);
        }
    }
}
