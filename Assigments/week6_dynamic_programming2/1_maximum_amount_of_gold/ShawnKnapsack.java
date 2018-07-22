import java.util.*;
import java.lang.*;

public class ShawnKnapsack {

    // =================================== Dynamic Programming ===============================
    private static int getOptimalWeight(int capacity, int[] weights) {
        // initialize to matrix
        int weightLength = weights.length;

        int[][] result = new int[weightLength][capacity+1];
        for (int i = 0; i < weightLength; i++) {
            for (int j = 0; j <= capacity; j++) {
                result[i][j] = 0;
            }            
        }

        // initialze the first row of weights[0]
        for (int j = 0; j <= capacity; j++) {
            if (weights[0] <= j) {
                result[0][j] = weights[0];
            }
        }

        // calculate optimal weight matrix
        for (int i = 1; i < weightLength; i++) {
            for (int j = 1; j <= capacity; j++) {
                result[i][j] = result[i-1][j];

                if (j >= weights[i] && (result[i-1][j - weights[i]] + weights[i]) > result[i][j]) {
                    result[i][j] = result[i-1][j - weights[i]] + weights[i];
                }
            }
        }

        return result[weightLength - 1][capacity];
    }

    // ======================================= Naive ==========================================
    private static int getOptimalWeightNaive(int capacity, int[] weights) {
        int max = 0;
        for (int i = 0; i < Math.pow(2, weights.length); i++) {
            String permutation = String.format("%0" + weights.length + "d", decToBin(i, ""));
            int possibleWeight = 0;

            for (int j = 0; j < permutation.length(); j++) {
                if (permutation.charAt(j) == '1' && (possibleWeight + weights[j]) <= capacity) {
                    possibleWeight += weights[j];
                }
            }

            if (possibleWeight > max) {
                max = possibleWeight;
            }
        }

        return max;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int W, n;
        W = scanner.nextInt();
        n = scanner.nextInt();
        int[] w = new int[n];
        for (int i = 0; i < n; i++) {
            w[i] = scanner.nextInt();
        }
        System.out.println(getOptimalWeight(W, w));
    }


    // ============================== STRESS TEST =============================================
    // public static void main(String[] args) {
    //     Random ran = new Random();

    //     while (true) {
    //         int capacity = ran.nextInt(20);
    //         System.out.println("Capacity: " + capacity);

    //         int noOfWeights = ran.nextInt(10) + 1;
    //         System.out.println("Number of weights: " + noOfWeights);

    //         int[] weights = new int[noOfWeights];
    //         for (int i = 0; i < noOfWeights; i++) {
    //             weights[i] = ran.nextInt(10);
    //         }
    //         System.out.println("Weights: " + Arrays.toString(weights));


    //         int dpWeight = getOptimalWeight(capacity, weights);
    //         int naiveWeight = getOptimalWeightNaive(capacity, weights);

    //         if (dpWeight == naiveWeight) {
    //             System.out.println("Ok.");
    //         } else {
    //             System.out.println("Wrong!");
    //             System.out.println(String.format("Naive: %d, Dp: %d", naiveWeight, dpWeight));
    //             break;
    //         }

    //         System.out.println("\n\n\n");
    //     }
    // }


    // =============================== UNTILITY METHOD ===========================================
    private static int decToBin(int dec, String bin) {
        int quot = dec / 2;
        int remainder = dec % 2;
        if (quot == 0)
            return Integer.parseInt("" + remainder + bin);
        return decToBin(quot, "" + remainder + bin);
    }

    // ============================== PROVIDED METHOD ====================================
    static int optimalWeight(int W, int[] w) {
        //write you code here
        int result = 0;
        for (int i = 0; i < w.length; i++) {
          if (result + w[i] <= W) {
            result += w[i];
          }
        }
        return result;
    }
}

