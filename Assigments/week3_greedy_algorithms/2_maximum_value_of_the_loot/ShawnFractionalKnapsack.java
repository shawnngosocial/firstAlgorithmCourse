import java.util.*;
import java.lang.*;

public class ShawnFractionalKnapsack {
    private static double getOptimalValue(int capacity, int[] values, int[] weights) {
        double value = 0.0;
        
        sortDescByValuePerUnit(values, weights);

        int index = 0;    
        while (capacity > 0 && index < weights.length) {
            if (capacity >= weights[index]) {
                value += values[index];
                capacity -= weights[index];

                index++;
            } else {
                // value += capacity * divideKeep4Digits(values[index], weights[index]);
                value += capacity * (((double) values[index])/ weights[index]);
                capacity = 0;

                return value;
            } 
        }

        return value;
    }

    private static double getOptimalValueNaive(int capacity, int[] values, int[] weights) {
        double value = 0.0;

        sortDescByValuePerUnitNaive(values, weights);

        for (int i = 0; i < weights.length; i++) {
            if (capacity >= weights[i]) {
                capacity -= weights[i];
                value += values[i];
            } else {
                value += capacity * (((double) values[i])/ weights[i]);
                break;
            }            
        }

        return value;
    }


    private static int get_best_idx(ArrayList<Double> ratios) {
        double best = 0;
        int best_idx = 0;

        for (int i = 0; i < ratios.size(); i++) {
            if (ratios.get(i) > best) {
                best = ratios.get(i);
                best_idx = i;
            }
        }
        return best_idx;
    }
    
    private static double getOptimalValueDifferent(int capacity, int[] values, int[] weights) {
        double value = 0;
        //write your code here
        ArrayList<Integer> valuelist = new ArrayList<Integer>();
        for (int i : values) 
            valuelist.add(i);
        
        ArrayList<Double> weightlist = new ArrayList<Double>();
        for (double i : weights) 
            weightlist.add(i);
        
        ArrayList<Double> ratios = new ArrayList<Double>();
        for(int i=0;i<valuelist.size();i++) {
          ratios.add((double)valuelist.get(i)/weightlist.get(i));
        }
        
        while (capacity > 0) {
            int best_idx = get_best_idx(ratios);
            if (weightlist.get(best_idx) > 0) {

                System.out.println(String.format("\nbest_idx weight: %f", weightlist.get(best_idx)));

                double a = Math.min((double)capacity, (double)weightlist.get(best_idx));

                System.out.println(String.format("\n a: %f", a));

                double ratio = (double) valuelist.get(best_idx) / (double) weightlist.get(best_idx);                

                System.out.println(String.format("\n ratio: %f", ratio));

                value += a * ratio;

                System.out.println(String.format("\n value: %f\n", value));


                weightlist.set(best_idx, weightlist.get(best_idx) - a);
                capacity -= a;
            }
            if (weightlist.get(best_idx) == 0) {
                weightlist.remove(best_idx);
                valuelist.remove(best_idx);
                ratios.remove(best_idx);;
            }
        }
        
        return value;
    }


    private static void sortDescByValuePerUnitNaive(int[] values, int[] weights) {
        for (int i = 0; i < values.length; i++) {
            for (int j = i; j > 0; j--) {
                double unitValueJ = (double) values[j]/weights[j];
                double unitValueJMinus = (double) values[j-1]/weights[j-1];

                if (unitValueJ > unitValueJMinus) {
                    int temp = values[j];
                    values[j] = values[j-1];
                    values[j-1] = temp;

                    temp = weights[j];
                    weights[j] = weights[j-1];
                    weights[j-1] = temp;
                } else {
                    break;
                }
            }
        }

        System.out.println("After sorting.....");
        System.out.println(String.format("values: %s", Arrays.toString(values)));
        System.out.println(String.format("weights: %s", Arrays.toString(weights)));
    }



    private static void sortDescByValuePerUnit(int[] values, int[] weights) {
        if (values.length != weights.length) {
            throw new IllegalArgumentException();
        }

        sort(values, weights, 0, values.length - 1);

        // System.out.println("After sorting.....");
        // System.out.println(String.format("values: %s", Arrays.toString(values)));
        // System.out.println(String.format("weights: %s", Arrays.toString(weights)));
    }


    private static void sort(int[] values, int[] weights, int l, int r) {
        if (l < r) {
            int mid = (l + r)/2;

            sort(values, weights, l, mid);
            sort(values, weights, mid+1, r);

            merge(values, weights, l, mid, r);
        }
    }


    private static void merge(int[] values, int[] weights, int l, int m, int r) {
        if (values.length != weights.length) {
            throw new IllegalArgumentException();
        }

        int leftLength = m - l + 1;
        int rightLength = r - m;

        int[] leftValues = new int[leftLength];
        int[] rightValues = new int[rightLength];

        int[] leftWeights = new int[leftLength];
        int[] rightWeights = new int[rightLength];

        // copy arrays
        for (int i=0; i < leftLength; i++) {
            leftValues[i] = values[l+i];
            leftWeights[i] = weights[l+i];
        }

        for (int i=0; i < rightLength; i++) {
            rightValues[i] = values[m+1+i];
            rightWeights[i] = weights[m+1+i];
        }

        int i = 0, j = 0;
        int index = l;
        while (i < leftLength && j < rightLength) {
            double unitValueLeft = (double) leftValues[i]/leftWeights[i];
            double unitValueRight = (double) rightValues[j]/rightWeights[j];

            if (unitValueLeft >= unitValueRight) {
                values[index] = leftValues[i];
                weights[index] = leftWeights[i];
                i++;
            } else if (unitValueLeft < unitValueRight) {
                values[index] = rightValues[j];
                weights[index] = rightWeights[j];
                j++;
            }

            index++;
        }

        // copy leftover from left
        while(i < leftLength) {
            values[index] = leftValues[i];
            weights[index] = leftWeights[i];
            i++;
            index++;
        }

        // copy leftover from right
        while(j < rightLength) {
            values[index] = rightValues[j];
            weights[index] = rightWeights[j];
            j++;
            index++;
        }
    }


    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int capacity = scanner.nextInt();
        int[] values = new int[n];
        int[] weights = new int[n];
        for (int i = 0; i < n; i++) {
            values[i] = scanner.nextInt();
            weights[i] = scanner.nextInt();
        }
        System.out.println(getOptimalValue(capacity, values, weights));
    }

    // stress test 
    // public static void main(String args[]) {
    //     Random ran = new Random();

    //     while (true) {
    //         int n = ran.nextInt(10) + 1;

    //         int[] values = new int[n];
    //         int[] weights = new int[n];

    //         for (int i = 0; i < n; i++) {
    //             values[i] = ran.nextInt(100) + 1;
    //             weights[i] = ran.nextInt(100) + 1;
    //         }


    //         int[] valuesNaive = new int[n];
    //         int[] weightsNaive = new int[n];

    //         // copy array
    //         valuesNaive = copyArray(values);
    //         weightsNaive = copyArray(weights);

    //         System.out.println(String.format("n: %d", n));

    //         System.out.println("Before sorting............");
    //         System.out.println(String.format("values: %s", Arrays.toString(values)));
    //         System.out.println(String.format("weights: %s", Arrays.toString(weights)));
    //         // System.out.println(String.format("valuesNaive: %s", Arrays.toString(valuesNaive)));
    //         // System.out.println(String.format("weightsNaive: %s", Arrays.toString(weightsNaive)));

    //         double optimalValue = getOptimalValue(n, values, weights);
    //         double optimalValueNaive = getOptimalValueDifferent(n, valuesNaive, weightsNaive);

    //         if (optimalValue == optimalValueNaive) {
    //             System.out.println("Ok.\n"); 
    //         } else {
    //             System.out.println("Wrong!");
    //             System.out.println(String.format("optimalValue: %f, naive: %f", optimalValue, optimalValueNaive));

    //             System.out.println(String.format("values: %s", Arrays.toString(values)));
    //             System.out.println(String.format("weights: %s", Arrays.toString(weights)));
    //             // System.out.println(String.format("valuesNaive: %s", Arrays.toString(valuesNaive)));
    //             // System.out.println(String.format("weightsNaive: %s", Arrays.toString(weightsNaive)));
    //             break;
    //         }
    //     }
    // }


    // main sorting
    // public static void main(String args[]) {
    //     Scanner scanner = new Scanner(System.in);
    //     int n = scanner.nextInt();
    //     int capacity = scanner.nextInt();
    //     int[] values = new int[n];
    //     int[] weights = new int[n];
    //     for (int i = 0; i < n; i++) {
    //         values[i] = scanner.nextInt();
    //         weights[i] = scanner.nextInt();
    //     }
    //     // System.out.println(getOptimalValue(capacity, values, weights));

    //     System.out.println("Before sorting............");
    //     System.out.println(String.format("values: %s", Arrays.toString(values)));
    //     System.out.println(String.format("weights: %s", Arrays.toString(weights)));

    //     // sortDescByValuePerUnit(values, weights);
    //     sortDescByValuePerUnitNaive(values, weights);
    // }

    // stress test sorting
    // public static void main(String args[]) {
    //     Random ran = new Random();

    //     while (true) {
    //         int n = ran.nextInt(50) + 1;

    //         int[] values = new int[n];
    //         int[] weights = new int[n];

    //         int[] valuesNaive = new int[n];
    //         int[] weightsNaive = new int[n];

    //         for (int i = 0; i < n; i++) {
    //             values[i] = ran.nextInt(1000) + 1;
    //             weights[i] = ran.nextInt(1000) + 1;
    //         }

    //         // copy array
    //         valuesNaive = copyArray(values);
    //         weightsNaive = copyArray(weights);

    //         System.out.println("Before sorting............");
    //         System.out.println(String.format("values: %s", Arrays.toString(values)));
    //         System.out.println(String.format("weights: %s", Arrays.toString(weights)));
    //         System.out.println(String.format("valuesNaive: %s", Arrays.toString(valuesNaive)));
    //         System.out.println(String.format("weightsNaive: %s", Arrays.toString(weightsNaive)));

    //         sortDescByValuePerUnit(values, weights);
    //         sortDescByValuePerUnitNaive(valuesNaive, weightsNaive);

    //         if (compare(values, weights, valuesNaive, weightsNaive)) {
    //             System.out.println("Ok.\n"); 
    //         } else {
    //             System.out.println("Wrong!");
    //             System.out.println(String.format("values: %s", Arrays.toString(values)));
    //             System.out.println(String.format("weights: %s", Arrays.toString(weights)));
    //             System.out.println(String.format("valuesNaive: %s", Arrays.toString(valuesNaive)));
    //             System.out.println(String.format("weightsNaive: %s", Arrays.toString(weightsNaive)));
    //             break;
    //         }
    //     }
    // }

    private static int[] copyArray(int[] arr) {
        int[] newArr = new int[arr.length];

        for (int i=0; i < arr.length; i++) {
            newArr[i] = arr[i];
        }

        return newArr;        
    }


    private static boolean compare(int[] values, int[] weights, int[] valuesNaive, int[] weightsNaive) {
        if (values.length != weights.length && weights.length != valuesNaive.length && valuesNaive.length != weights.length) {
            System.out.println(String.format("Length difference! values: %d, weights: %d, valuesNaive: %d, weightsNaive: %d", values.length, weights.length, valuesNaive.length, weightsNaive.length));
            return false;
        }

        for (int i=0; i < values.length; i++) {
            if (values[i] != valuesNaive[i]) {
                System.out.println("values difference!");
                return false;
            }
        }

        for (int i=0; i < weights.length; i++) {
            if (weights[i] != weightsNaive[i]) {
                System.out.println("weights difference!");
                return false;
            }
        }

        return true;
    }
} 
