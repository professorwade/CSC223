package week3;
import java.util.stream.IntStream;
import java.util.Scanner;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.TimeUnit;

public class RecursiveMethodExamples {
    public static final int[] ONE_TO_1000 = IntStream.rangeClosed(1, 100000).toArray();

    public static int SearchInterface(int key, char type) {
        ThreadMXBean threadMxBean = ManagementFactory.getThreadMXBean();
        int r = -1;
        if (threadMxBean.isCurrentThreadCpuTimeSupported()) {

                long startTime = threadMxBean.getCurrentThreadCpuTime(); // Capture the CPU time at the start
                long wallStartTime = System.nanoTime(); // Capture the wall clock start time

                int[] rcount = {0};
                try {

                    if (type == 'b') {
                        r = binarySearch(ONE_TO_1000, 0, ONE_TO_1000.length - 1, key, rcount);
                    } else {
                        r = linearSearch(ONE_TO_1000, key, rcount);
                    }
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                long endTime = threadMxBean.getCurrentThreadCpuTime(); // Capture the CPU time at the end
                long wallEndTime = System.nanoTime(); // Capture the wall clock end time

                // Calculate the elapsed CPU and wall time
                long cpuTimeNano = endTime - startTime;
                long cpuTimeMicros = TimeUnit.NANOSECONDS.toMicros(cpuTimeNano);
                long wallDurationInNanos = wallEndTime - wallStartTime;
                long wallDurationInMicros = wallDurationInNanos / 1_000; // calculate wall duration in millis

                System.out.println("Recursions/Iterations: " + rcount[0]);
                System.out.println("Total CPU time used: " + cpuTimeNano + " ns");
                System.out.println("Total wall clock time used: " + wallDurationInNanos + " ns");
        } else {
            System.out.println("Thread CPU time measurement not supported on this JVM/OS.");
        }
        return r;
    }


    public static int linearSearch(int[] numbers, int key, int[] rcount) {
        for (int i = 0; i < numbers.length; i++) {
            rcount[0]++;
            if (numbers[i] == key) {
                return i;
            }
        }
        return -1;
    }


    public static int binarySearch(int[] numbers, int low, int high, int key, int[] rcount) {
        rcount[0]++;
        if (low > high) {
            return -1;
        }
        int mid = (low + high) / 2;
        if (numbers[mid] < key) {
            return binarySearch(numbers, mid + 1, high, key, rcount);
        }
        else if (numbers[mid] > key) {
            return binarySearch(numbers, low, mid - 1, key, rcount);
        }
        return mid;
    }

    /**
     * Calculate the factorial of n
     * @param n - supplied positive integer
     * @return n!
     */
    public static int factorial(int n) {
        if (n <= 1) {
            return 1;
        }
        return n * factorial(n - 1);
    }

    /**
     * Calculate the cumulative sum from 0 to n
     * @param n
     * @return
     */
    public static int cumulativeSum(int n) {
        if (n == 0) {
            return 0;
        }
        else {
            return n + cumulativeSum(n - 1);
        }
    }

    /**
     * Calculate the nth term of the Fibonacci sequence
     * @param termIndex
     * @return Fibonacci number at termIndex
     */
    static int fibonacciNumber(int termIndex) {
        if (termIndex == 0) {
            return 0;
        }
        else if (termIndex == 1) {
            return 1;
        }
        else {
            return fibonacciNumber(termIndex - 1) + fibonacciNumber(termIndex - 2);
        }
    }

    /**
     * Reverse an array from startIndex to endIndex
     * @param array
     * @param startIndex
     * @param endIndex
     */
    public static void reverseArray(char[] array, int startIndex, int endIndex) {
        if (startIndex >= endIndex) {
            return;
        }
        else {
            char temp = array[startIndex];
            array[startIndex] = array[endIndex];
            array[endIndex] = temp;
            reverseArray(array, startIndex + 1, endIndex - 1);
        }
    }

    public static void main(String[] args) {
        int rval;
        for (int i = 1; i < 100000; i+=1000) {
            rval = SearchInterface(i, 'b');
            IO.println("Binary search returned " + rval + "\n");
            rval = SearchInterface(i, 'l');
            IO.println("Linear search returned " + rval + "\n");
        }

        IO.println("\nMax Recursion Depth:");
        rval = SearchInterface(100001, 'b');
        IO.println("Binary search returned " + rval);

        IO.println("\nMin Recursion Depth:");
        rval = SearchInterface(50000, 'b');
        IO.println("Binary search returned " + rval);


        IO.print( "Enter a number: ");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        System.out.println("Factorial of " + n + " is " + factorial(n));

        IO.println("Cumulative Sum of " + n + " is " + cumulativeSum(n));
        IO.println("Fibonacci number at " + n + " is " + fibonacciNumber(n));
        char[] array = {'a', 'b', 'c', 'd', 'e'};
        reverseArray(array, 0, array.length - 1);
        for (int i = 0; i < array.length; i++) {
            IO.print(array[i] + ",");
        }
        IO.println();
    }

}
