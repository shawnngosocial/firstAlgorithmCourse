import java.util.*;
import java.io.*;

public class MaxPairwiseProduct {
    static long getMaxPairwiseProduct(int[] numbers) {
        int n = numbers.length;
        if (n < 2) {
            throw new IllegalArgumentException("Invalid input: " + numbers);
        }

        int maxIndex = 0;

        // get the max index
        for (int i = 1; i < n; i++) {
            if (numbers[i] > numbers[maxIndex]) {
                maxIndex = i;
            }
        }

        // swap max to the end
        if (maxIndex != (n - 1)) {
            int temp = numbers[n - 1];
            numbers[n - 1] = numbers[maxIndex];
            numbers[maxIndex] = temp;
        }

        // get the second max index
        int secondMaxIndex = 0;
        for (int i = 1; i < n - 1; i++) {
            if (numbers[i] > numbers[secondMaxIndex]) {
                secondMaxIndex = i;
            }
        }

        System.out.println("Efficient max index: " + maxIndex + " " + secondMaxIndex);

        return (long) numbers[n - 1] * numbers[secondMaxIndex];
    }
    
    static long naiveImplement(int[] numbers) {
        int n = numbers.length;
        long max = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    long product = ((long) numbers[i]) * numbers[j];
                
                    if (product > max) {
                        max = product;
                    }
                }                
            }
        }

        return max;
    }

    public static void main(String[] args) {
        // FastScanner scanner = new FastScanner(System.in);
        // int n = scanner.nextInt();
        // int[] numbers = new int[n];
        // for (int i = 0; i < n; i++) {
        //     numbers[i] = scanner.nextInt();
        // }
        // System.out.println(getMaxPairwiseProduct(numbers));
        Random r = new Random();

        while (true) {
            int bound = 2 + r.nextInt((int) (100));
            // int bound = 2 + r.nextInt(3);
            int[] numbers = new int[bound];

            for (int i = 0; i < bound; i++) {
                numbers[i] = r.nextInt((int) (2*Math.pow(10,5)));
                // numbers[i] = r.nextInt((int) (10));
            }

            long effientResult = getMaxPairwiseProduct(Arrays.copyOf(numbers, bound));        
            long naiveResult = naiveImplement(Arrays.copyOf(numbers, bound));

            System.out.println(Arrays.toString(numbers));
            if (effientResult == naiveResult) {
                System.out.println("OK");
            } else {
                System.out.println(effientResult + " != " + naiveResult);
                return;
            }

            System.out.println("\n\n");
        }
    }

    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner(InputStream stream) {
            try {
                br = new BufferedReader(new InputStreamReader(stream));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }

}