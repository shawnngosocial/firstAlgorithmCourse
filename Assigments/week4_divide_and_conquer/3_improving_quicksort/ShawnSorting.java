import java.io.*;
import java.util.*;

public class ShawnSorting {
    private static Random random = new Random();

    private static int[] partition3(int[] a, int l, int r) {
        //write your code here
        int x = a[l];
        int j = l; // position of first position of pivot elements
        int k = l; // position of last position of pivot elements

        for (int i = l + 1; i <= r; i++) {
            if (a[i] == x) {
                k++;
                int t = a[i];
                a[i] = a[k];
                a[k] = t;
            } else if (a[i] < x) {
                int t = a[i];
                a[i] = a[j];
                a[j] = t;
                j++;

                k++;
                t = a[i];
                a[i] = a[k];
                a[k] = t;
            }
        }

        int[] m = {j, k};
        return m;
    }

    private static int[] partition3Different(int[] a, int l, int r) {
        int m1 = l;
        int m2 = l;
        int pivot = a[l];
        for (int j = l + 1; j <= r; j++) {
            if (a[j] > pivot) continue;
            if (a[j] < pivot) {
                swap(a, m1, j);
                m1++;
                  
            }
            swap(a, m2 + 1, j);
            m2++;
        }
        int[] m = {m1, m2};
        return m;
    }
    private static void swap(int[] a, int i, int j) {
        int tmp = a[i]; a[i] = a[j]; a[j] = tmp;
    }

    private static int partition2(int[] a, int l, int r) {
        int x = a[l];
        int j = l;
        for (int i = l + 1; i <= r; i++) {
            if (a[i] <= x) {
                j++;
                int t = a[i];
                a[i] = a[j];
                a[j] = t;
            }
        }
        int t = a[l];
        a[l] = a[j];
        a[j] = t;
        return j;
    }

    private static void randomizedQuickSortOf3(int[] a, int l, int r) {
        if (l >= r) {
            return;
        }
        int k = random.nextInt(r - l + 1) + l;
        int t = a[l];
        a[l] = a[k];
        a[k] = t;

        //use partition3
        int[] m = partition3(a, l, r);
        randomizedQuickSortOf3(a, l, m[0] - 1);
        randomizedQuickSortOf3(a, m[0] + 1, r);
    }

    private static void randomizedQuickSortOf3Different(int[] a, int l, int r) {
        if (l >= r) {
            return;
        }
        int k = random.nextInt(r - l + 1) + l;
        int t = a[l];
        a[l] = a[k];
        a[k] = t;
        // use partition2:
        // int m = partition2(a, l, r);
        // randomizedQuickSort(a, l, m - 1);
        // randomizedQuickSort(a, m + 1, r);
        int[] ms = partition3Different(a, l, r);
        int m1 = ms[0], m2 = ms[1];
        randomizedQuickSortOf3Different(a, l, m1 - 1);
        randomizedQuickSortOf3Different(a, m2 + 1, r);
    }


    private static void randomizedQuickSortOf2(int[] a, int l, int r) {
        if (l >= r) {
            return;
        }
        int k = random.nextInt(r - l + 1) + l;
        int t = a[l];
        a[l] = a[k];
        a[k] = t;
        //use partition2
        int m = partition2(a, l, r);
        randomizedQuickSortOf2(a, l, m - 1);
        randomizedQuickSortOf2(a, m + 1, r);
    }

    // public static void main(String[] args) {
    //     FastScanner scanner = new FastScanner(System.in);
    //     int n = scanner.nextInt();
    //     int[] a = new int[n];
    //     for (int i = 0; i < n; i++) {
    //         a[i] = scanner.nextInt();
    //     }
    //     randomizedQuickSortOf3Different(a, 0, n - 1);
    //     for (int i = 0; i < n; i++) {
    //         System.out.print(a[i] + " ");
    //     }
    // }


    // public static void main(String[] args) {
    //     Random ran = new Random();

    //     while (true) {
    //         int n = ran.nextInt(10);

    //         int[] arr1 = new int[n];
    //         for (int i = 0; i < n; i++) {
    //             arr1[i] = ran.nextInt(5);
    //         }
    //         int[] arr2 = copyArr(arr1);

    //         System.out.println("arr1: " + Arrays.toString(arr1));
    //         System.out.println("arr2: " + Arrays.toString(arr2));

    //         randomizedQuickSortOf3(arr1, 0, n - 1);
    //         randomizedQuickSortOf3Different(arr2, 0, n - 1);

    //         System.out.println("n: " + n);

    //         System.out.println("arr1: " + Arrays.toString(arr1));
    //         System.out.println("arr2: " + Arrays.toString(arr2));

    //         if (equalsArr(arr1, arr2)) {
    //             System.out.println("True.");
    //         } else {
    //             System.out.println("Wrong.");
    //             System.out.println(Arrays.toString(arr1));
    //             System.out.println(Arrays.toString(arr2));
    //         }
    //     }
    // }

    public static void main(String[] args) {
        Random ran = new Random();

        while (true) {
            int n = ran.nextInt(100000);

            int[] arr1 = new int[n];
            for (int i = 0; i < n; i++) {
                arr1[i] = ran.nextInt(1000000000);
            }
            
            System.out.println("n: " + n);
            
            randomizedQuickSortOf3(arr1, 0, n - 1);
            
            for (int i = 0; i < n-2; i++) {
                if (arr1[i] > arr1[i+1]) {
                    System.out.println("Wrong.");
                    System.out.println("arr1: " + Arrays.toString(arr1));
                    return;
                }
            }
            
            System.out.println("True.");            
        }
    }


    private static boolean equalsArr(int[] a, int[] b) {
        if (a.length != b.length) {
            return false;
        }

        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) {
                return false;
            }
        }

        return true;
    }

    private static int[] copyArr(int[] a) {
        int[] b = new int[a.length];

        for (int i = 0; i < a.length; i++) {
            b[i] = a[i];
        }

        return b;
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

