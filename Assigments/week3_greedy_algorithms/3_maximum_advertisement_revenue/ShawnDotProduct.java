import java.util.*;
import java.lang.*;

public class ShawnDotProduct {
    private static long maxDotProduct(int[] ad, int[] clicks) {
        // sort ad and clicks in desc order
        sortAdandClick(ad, clicks);

        if (ad.length != clicks.length) {
            throw new IllegalArgumentException();
        }        

        long revenue = 0;
        for (int i = 0; i < ad.length; i++) {
            revenue += ad[i]*clicks[i];
        }

        return revenue;
    }


    private static void sortAdandClick(int[] ad, int[] clicks) {    
        if (ad.length > 1) {
            sortDesc(ad, 0, ad.length - 1);    
        }

        if (clicks.length > 1) {
            sortDesc(clicks, 0, clicks.length - 1);
        }        

        System.out.println("\nAfter sorting.....");
        System.out.println(Arrays.toString(ad));
        System.out.println(Arrays.toString(clicks));
        System.out.println("\n");
    }

    private static void sortAdandClickNaive(int[] ad, int[] clicks) {
        if (ad.length > 1) {
            sortDescNaive(ad);
        }

        if (clicks.length > 1) {
            sortDescNaive(clicks);
        }

        // System.out.println("After sorting.....");
        // System.out.println(Arrays.toString(ad));
        // System.out.println(Arrays.toString(clicks));
    }


    private static void sortDescNaive(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j] > arr[j-1]) {
                    int temp = arr[j];
                    arr[j] = arr[j-1];
                    arr[j-1] = temp;
                } else {
                    break;
                }
            }
        }
    }


    private static void sortDesc(int[] arr, int l, int r) {
        if (l < r) {
            int mid = (l + r)/2;

            sortDesc(arr, l, mid);
            sortDesc(arr, mid+1, r);

            merge(arr, l, mid, r);
        }
    }


    private static void merge(int[] arr, int l, int m, int r) {
        int leftLength = m - l + 1;
        int rightLength = r - m;

        int[] leftArr = new int[leftLength];
        int[] rightArr = new int[rightLength];

        // copy arrays
        for (int i=0; i < leftLength; i++) {
            leftArr[i] = arr[l+i];
        }

        for (int i=0; i < rightLength; i++) {
            rightArr[i] = arr[m+1+i];
        }

        int i = 0, j = 0;
        int index = l;
        while (i < leftLength && j < rightLength) {
            if (leftArr[i] >= rightArr[j]) {
                arr[index] = leftArr[i];
                i++;
            } else if (leftArr[i] < rightArr[j]) {
                arr[index] = rightArr[j];
                j++;
            }

            index++;
        }

        // copy leftover from left
        while(i < leftLength) {
            arr[index] = leftArr[i];
            i++;
            index++;
        }

        // copy leftover from right
        while(j < rightLength) {
            arr[index] = rightArr[j];
            j++;
            index++;
        }
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        int[] b = new int[n];
        for (int i = 0; i < n; i++) {
            b[i] = scanner.nextInt();
        }
        System.out.println(maxDotProduct(a, b));

        // sortAdandClick(a, b);
        // sortAdandClickNaive(a, b);
    }
}

