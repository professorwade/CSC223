import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class W1Csc223Examples {

    /*
    1.10.1
    Write a short Java method, inputAllBaseTypes, that inputs a different value of each base type from the
    standard input device and prints it back to the standard output device.
     */
    public static void inputAllBaseTypes() {
        Scanner s = new Scanner(System.in);
        
        // byte
        System.out.print("Enter a byte value: ");
        byte byteVal = s.nextByte();
        System.out.println("You entered " + byteVal);

        // short
        System.out.print("Enter a short value: ");
        short shortVal = s.nextShort();
        System.out.println("You entered " + shortVal);

        // integer
        System.out.print("Enter an integer value: ");
        int intVal = s.nextInt();
        System.out.println("You entered " + intVal);

        // long
        System.out.print("Enter a long value: ");
        long longVal = s.nextLong();
        System.out.println("You entered " + longVal);

        // float
        System.out.print("Enter a float value: ");
        float floatVal = s.nextFloat();
        System.out.println("You entered " + floatVal);

        // double
        System.out.print("Enter a double value: ");
        double dVal = s.nextDouble();
        System.out.println("You entered " + dVal);

        // boolean
        System.out.print("Enter a boolean value (true/false): ");
        boolean boolVal = s.nextBoolean();
        System.out.println("You entered " + boolVal);

        // char
        System.out.print("Enter a character: ");
        char charVal = s.next().charAt(0);
        System.out.println("You entered " + charVal);
    }

    // 1.10.2 GameEntry in Main

    /*
    1.10.3 Write a short Java method, isMultiple, that takes two long values n & m and returns true
    if n is a multiple of m
     */
    public static boolean isMultiple(long n, long m) {
        return n % m == 0;
    }

    /*
    1.10.4
    Write a short Java method, isEven, that takes an int and returns true if and only if
    is even. Your method cannot use the multiplication, modulus, or division operators, however.
    */
    public static boolean isEven(int i) {
        return i % 2 == 0;
        //alternative: return ((i & 1) == 0);
    }

    /*
    1.10.5 Write a short Java method that takes an integer and returns the sum of all positive integers less than
    or equal to n.
    */
    public static int sumToN(int n) {
        int total = 0;
        for (int j=1; j <= n; j++)
            total += j;
        return total;
    }

    /*
    1.10.6 Write a short Java method that takes an integer and returns the sum of all odd positive integers
    less than or equal to n.
    */
    public static int sumOdd(int n) {
        int total = 0;
        for (int j=1; j <= n; j += 2)
            total += j;
        return total;
    }

    /* 1.10.8 Write a short Java method that counts the number of vowels in a given character string. */
    public static int numVowels(String text) {
        int total = 0;
        for (int j=0; j < text.length(); j++) {
            switch (text.toUpperCase().charAt(j)) {
                case 'A':
                case 'E':
                case 'I':
                case 'O':
                case 'U':
                    total += 1;
            }
        }
        return total;
    }

    /* 1.10.9 Write a short Java method that uses a StringBuilder instance to remove all the punctuation
        from a string storing a sentence, for example, transforming the string "Let's try, Mike!" to
        "Lets try Mike". */
    public static String stripPunctuation(String s) {
        StringBuilder sb = new StringBuilder(s);
        for (int i = 0; i < sb.length(); i++) {
            if (!Character.isLetterOrDigit(sb.charAt(i)) && !Character.isWhitespace(sb.charAt(i))) {
                sb.deleteCharAt(i);
            }
        }
        return sb.toString();
    }

    // 1.10.10 - Separate Class see main()

    /* 1.10.28
    A common punishment for school children is to write out a sentence multiple times. Write a Java stand-alone
    program that will write out the following sentence one hundred times: "I will never spam my friends again."
    Your program should number each of the sentences, and it should make eight different random-looking typos.
     */
    public static void writeSentencePunishment(int repeat) {
        Random random = new Random();
        Set<Integer> locations = new HashSet<>();

        int error_count = 8;
        if (repeat <= error_count)
            error_count = repeat; // make certain set can be built

        while (locations.size() < error_count) {
            locations.add(random.nextInt(0,repeat));
        }

        String s = "I will never spam my friends again.";
        for (int i = 0; i < repeat; i++) {
            if (locations.contains(i)) {
                StringBuilder bad_string = new StringBuilder(s);
                bad_string.setCharAt(random.nextInt(0, s.length()), (char)random.nextInt(97, 123)); // a-z
                System.out.println(i+1 + " " + bad_string);
            } else {
                System.out.println(i + 1 + " " + s);
            }
        }
    }

    /* 1.10.26 Write a short Java program that takes all the lines input to standard input and
        writes them to standard output in reverse order. That is, the last line input should be the
        first line output, and vice versa. The content within an individual line is unchanged.
     */
    public static void writeLinesInReverse() {
        Scanner s = new Scanner(System.in);
        while (true) {
            String line = s.nextLine();
            if (line.charAt(0) == 'q')
                return;
            StringBuilder sb = new StringBuilder();
            for (int i = line.length() - 1; i >= 0; i--) {
                sb.append(line.charAt(i));
            }
            System.out.println(sb);
        }
    }

    public static void main(String[] args) {
        // 1.10.1
        System.out.println("1.10.1");
        //inputAllBaseTypes();

        // 1.10.2
        System.out.println("1.10.2");
        GameEntry [] A = new GameEntry[5];
        for (int i = 0; i < A.length; i++) {
            A[i] = new GameEntry();
        }
        GameEntry [] B = A;
        A[0].score = 500;
        System.out.println("A[0].score is " + A[0].score);
        System.out.println("B[0].score is " + B[0].score);

        // 1.10.3
        System.out.println("1.10.3");
        System.out.print("10 is a multiple of 2: ");
        System.out.println(isMultiple(10, 2));

        // 1.10.4
        System.out.println("1.10.4");
        System.out.println("Is 5 an even number? " + isEven(5));

        // 1.10.5
        System.out.println("1.10.5");
        System.out.println("output of sumToN(10) is " + sumToN(10));

        // 1.10.6
        System.out.println("1.10.6");
        System.out.println("output of sumOdd(10) is " + sumOdd(10));

        // 1.10.8
        System.out.println("1.10.8");
        System.out.println("The number of vowels in my name is " + numVowels("Wade Schofield"));

        // 1.10.9
        System.out.println("1.10.9");
        System.out.println("Remove punctuation from \"Let's try, Mike!\" -> " + stripPunctuation("Let's try, Mike!"));

        // 1.10.10 (Flower class)
        System.out.println("1.10.10");
        Flower f = new Flower("Rose", 32, 5.99f);
        System.out.println("Flower: " + f.getName() + ", Petals: " + f.getPetals() + ", Price: $" + f.getPrice());
        f.setPrice(6.50f);
        System.out.println("New price: $" + f.getPrice());

        // 1.10.28 Spam Friends
        /*
        A common punishment for school children is to write out a sentence multiple times. Write a Java stand-alone
         program that will write out the following sentence one hundred times: "I will never spam my friends again."
         Your program should number each of the sentences, and it should make eight different random-looking typos.
         */
        writeSentencePunishment(4);

        /* 1.10.26
        Write a short Java program that takes all the lines input to standard input and writes them to standard output in
        reverse order. That is, the last line input should be the first line output, and vice versa. The content within an
        individual line is unchanged. Use 'q' alone to stop.
        */
        writeLinesInReverse();
    }




}


