import java.util.*;
import java.lang.*;

public class ShawnFibonacciSumLastDigit {
    private static long getFibonacciSumNaive(long n) {
        if (n <= 1)
            return n;

        long previous = 0;
        long current  = 1;
        long sum      = 1;

        for (long i = 0; i < n - 1; ++i) {
            long tmp_previous = previous;
            previous = current;
            current = (tmp_previous + current) % 10;
            sum += current;
            sum %= 10;
        }

        return sum;
    }

    private static long getFibonacciSumAdvance(long n) {
        // int period = getPisanoPeriodOf10();
        int period = 60;

        // Since sum of fibonacci series of n = F(n+2) - 1
        // so S(n) % 10 == (F(n+2) - 1) % 10 = (F((n+2) % period) - 1) % 10;
        long fibonacciIndex = (n + 2) % period;

        // System.out.println(String.format("fibonacciIndex: %d", fibonacciIndex));

        if (fibonacciIndex == 0) {
            // since F(0) = 0, so F(0) - 1 = -1
            // -1 % 10 = 9 in this case as last digit can't be negative
            return 9;
        } else if (fibonacciIndex == 1) {
            // since F(1) = 1 and (F(1) - 1) % 10 == 0 % 10
            return 0;
        }

        long temp_previous;
        long previous = 0;         
        long current = 1;

        for (int i = 2; i <= fibonacciIndex; i++) {                    
            temp_previous = previous;
            previous = current;

            current = (previous + temp_previous) % 10;

            // System.out.println(String.format("current: %d with i: %d", current, i));
        }

        if (current == 0) {
            return 9;
        }

        return current - 1;
    }

    private static int getPisanoPeriodOf10() {
        int previous = 0;
        int current = 1;

        for (int i = 2; i <= 100; i++) {
            int temp_previous = previous;
            previous = current;

            current = (previous + temp_previous) % 10;

            if (previous == 0 && current == 1) {
                // System.out.println(String.format("i-1: %d", i-1));
                return i - 1;
            }
        }

        throw new IllegalStateException("Unable to find pisano period of 10.");
    }

    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
        // long s = getFibonacciSumNaive(n);
        long s = getFibonacciSumAdvance(n);
        System.out.println(s);
    }

    // public static void main(String[] args) {
    //     Random ran = new Random();

    //     while(true) {
    //         long n = (long) (ran.nextDouble() * Math.pow(10, 7));
    //         // long n = (long) (ran.nextInt(100));

    //         long lastDigitNaive = getFibonacciSumNaive(n);
    //         long lastDigitAdvance = getFibonacciSumAdvance(n);

    //         System.out.println(String.format("n: %d", n));

    //         if (lastDigitAdvance == lastDigitNaive) {
    //             System.out.println("Ok.");
    //         } else {
    //             System.out.println(String.format("naive: %d, advance: %d", lastDigitNaive, lastDigitAdvance));
    //             break;
    //         }            
    //     }
    // }
}

