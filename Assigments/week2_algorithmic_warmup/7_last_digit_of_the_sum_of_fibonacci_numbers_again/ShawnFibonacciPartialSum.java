import java.util.*;
import java.lang.*;

public class ShawnFibonacciPartialSum {
    private static long getFibonacciPartialSumNaive(long from, long to) {
        long sum = 0;

        long current = 0;
        long next  = 1;

        for (long i = 0; i <= to; ++i) {
            if (i >= from) {
                sum += current;
                sum %= 10;
            }

            long new_current = next;
            next = (next + current) % 10;
            current = new_current;
        }

        return sum % 10;
    }

    private static long getFibonacciPartialSumAdvance(long from, long to) {
        int lastDigitSum = getLastDigitFibonacciSumAdvance(to) - getLastDigitFibonacciSumAdvance(from - 1);
        
        if (lastDigitSum < 0) {
            // since last digit can't be negative
            lastDigitSum += 10;
        }        

        return lastDigitSum % 10;
    }

    private static int getLastDigitFibonacciSumAdvance(long n) {
        int period = 60;

        // Since S(n) = F(n+2) - 1, S(n) % 10 == (F(n+2) - 1) % 10 == (F((n +2) % period) - 1) % 10
        long fibonacciIndex = (n + 2) % period;

        if (fibonacciIndex == 0) {
            return 9;
        } else if (fibonacciIndex == 1) {
            return 0;
        }

        int previous = 0;
        int current = 1;
        int temp_previous;

        for (long i = 2; i <= fibonacciIndex; i++) {
            temp_previous = previous;
            previous = current;

            current = (previous + temp_previous) % 10;
        }

        if (current == 0) {
            return 9;
        }

        return current - 1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long from = scanner.nextLong();
        long to = scanner.nextLong();
        // System.out.println(getFibonacciPartialSumNaive(from, to));
        System.out.println(getFibonacciPartialSumAdvance(from, to));
    }

    // public static void main(String[] args) {
    //     Random ran = new Random();

    //     while(true) {
    //         long limit = (long) Math.pow(10, 7);
    //         long m = (long) (ran.nextDouble() * limit);
    //         long n = (long) (ran.nextDouble() * (limit - m) + m);

    //         System.out.println(String.format("m: %d, n: %d", m, n));

    //         long lastDigitSumNaive = getFibonacciPartialSumNaive(m, n);
    //         long lastDigitSumAdvance = (long) getFibonacciPartialSumAdvance(m,n);

    //         if (lastDigitSumNaive == lastDigitSumAdvance) {
    //             System.out.println("Ok.\n");
    //         } else {
    //             System.out.println(String.format("Wrong! lastDigitSumNaive: %d, lastDigitSumAdvance: %d", lastDigitSumNaive, lastDigitSumAdvance));
    //             break;
    //         }
    //     }
    // }
}

