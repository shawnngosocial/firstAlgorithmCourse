import java.util.*;
import java.lang.*;
import java.io.*;

public class ShawnChangeDP {
    private static int getChangeMemoization(int m) {
    	int[] minCoins = new int[m+1];
    	if (m == 0) {
    		return 0;
    	}

    	minCoins[m] = Math.min(Math.min(method(m-1, minCoins), method(m-3, minCoins)), method(m-4, minCoins)) + 1;    	
    	return minCoins[m];
    }

    private static int method(int m, int[] minCoins) {
    	if (m < 0) {
    		return 10000;
    	} else if (m == 0) {
    		return 0;
    	}

    	if (m == 1 || m == 3 || m == 4) {
    		minCoins[m] = 1;
    		return minCoins[m];
    	}

    	if (minCoins[m] > 0) {
    		return minCoins[m];
    	}

    	minCoins[m] = Math.min(Math.min(method(m -1, minCoins), method(m-3, minCoins)), method(m - 4, minCoins)) + 1;
    	return minCoins[m];
    }

    private static int getChangeMemoizationImprove(int m, int[] coinSet) {
    	// intialize array
    	int[] minCoins = new int[m+1];

		// initialize base
    	minCoins[0] = 0;

    	for (int i = 1; i < minCoins.length; i++) {
    		minCoins[i] = Integer.MAX_VALUE;
    	}

    	// recursive call
    	minCoins[m] = methodMemoizationImprove(m, coinSet, minCoins);

    	return minCoins[m];
    }

    private static int methodMemoizationImprove(int m, int[] coinSet, int[] minCoins) {
    	// validate m 
    	if (m < 0) {
    		return Integer.MAX_VALUE;
    	} else if (minCoins[m] != Integer.MAX_VALUE) {
    		return minCoins[m];
    	}    	

    	// recursive call
    	for (int i = 0; i < coinSet.length; i++) {
    		if (coinSet[i] <= m) {
    			int min = methodMemoizationImprove(m - coinSet[i], coinSet, minCoins);

    			if (min + 1 < minCoins[m])  {
    				minCoins[m] = min + 1;
    			}
    		}
    	}



    	return minCoins[m];
    }

    private static int getChangeTabulation(int m) {
    	int length = Math.max(5,m+1);
    	int[] minCoins = new int[length];

    	// initialize
    	minCoins[0] = 0;
    	minCoins[1] = 1;
    	minCoins[2] = 2;
    	minCoins[3] = 1;
    	minCoins[4] = 1;

    	// compute the table
    	for (int i = 5; i <=m; i++) {    		
    		minCoins[i] = Math.min(minCoins[i-4], Math.min(minCoins[i-3], minCoins[i-1])) + 1;
    	}

    	return minCoins[m];
    }

    private static int getChangeTabulationImprove(int m, int[] coinSet) {
    	int[] minCoins = new int[m+1];

    	// initialize elements in minCoins to MAX_INTEGER
    	for (int i = 0; i < minCoins.length; i++) {
    		minCoins[i] = Integer.MAX_VALUE; 
    	}

    	// initialize base
    	minCoins[0] = 0;

    	// compute min element in each index
    	for (int i = 1; i <= m; i++) {
    		for (int j = 0; j < coinSet.length; j++) {
    			if (coinSet[j] <= i) {
    				if (minCoins[i] > minCoins[i - coinSet[j]] + 1) {
    					minCoins[i] = minCoins[i - coinSet[j]] + 1;
    				}
    			}
    		}
    	}

    	return minCoins[m];
    }

    // public static void main(String[] args) {
    //     Scanner scanner = new Scanner(System.in);
    //     int m = scanner.nextInt();
    //     System.out.println(getChangeTabulation(m));
    // }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        System.out.println(getChangeTabulationImprove(m, new int[]{1,3,4}));
    }


    // stress test
    // public static void main(String[] args) {
    // 	Random ran = new Random();

    // 	while (true) {
    // 		int m = ran.nextInt(999) + 1;

    // 		System.out.println("Change: " + m);

    // 		int tabulation = getChangeTabulation(m);
    // 		int memoization = getChangeMemoization(m);

    // 		if (tabulation == memoization) {
    // 			System.out.println("Ok.");
    // 		} else {
    // 			System.out.println("Wrong!");
    // 			System.out.println(String.format("tabulation: %d, memoization: %d", tabulation, memoization));

    // 			break;
    // 		}
    // 	}
    // }
}

