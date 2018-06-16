import java.util.*;
import java.lang.*;

public class ShawnFibonacciLastDigit {
    private static int getFibonacciLastDigitNaive(int n) {
        if (n <= 1)
            return n;

        int previous = 0;
        int current  = 1;

        for (int i = 0; i < n - 1; ++i) {
            int tmp_previous = previous;
            previous = current;
            current = tmp_previous + current;
        }

        return current % 10;
    }

    private static int getFibonacciLastDigitAdvance(int n) {
        if (n <= 1) {
            return n;
        }

        int temp_previous;
        int previous = 0;
        int current = 1;

        for (int i = 2; i <= n; i++) {
            temp_previous = previous;
            previous = current;

            current = (previous + temp_previous) % 10;
        }

        return current;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        // int c = getFibonacciLastDigitNaive(n);
        int c = getFibonacciLastDigitAdvance(n);
        System.out.println(c);
    }


    // public static void main(String[] args) {        
    //     Random ran = new Random();

    //     while (true) {
    //         int n = ran.nextInt((int) (30 + 1));            

    //         int c = getFibonacciLastDigitNaive(n);
    //         int advance = getFibonacciLastDigitAdvance(n);

    //         System.out.println(n);

    //         if (c == advance) {
    //             System.out.println("Okay");
    //         } else {
    //             System.out.println(String.format("Wrong answer! advance: %d, naive: %d", advance, c));
    //             break;
    //         }

    //         System.out.println("\n");
    //     }        
    // }
}

