package week6.llbasedstack;

public class Program {
    public static void main(String[] args) {
        int[] numbers = { 76, 81, 91, 34, 62, 88, 77, 21, 18 };

        // Initialize a new Stack and add numbers
        Stack numStack = new Stack();
        for (int number : numbers) {
            numStack.push(number);
        }

        // Show the stack before any pop operations occur
        System.out.print("Stack: ");
        numStack.print(System.out);

        // Pop until stack is empty, printing each popped item and remaining stack
        while (!numStack.isEmpty()) {
            System.out.println("Popped " + numStack.pop());
            System.out.print("Stack: ");
            numStack.print(System.out);
        }
    }
}