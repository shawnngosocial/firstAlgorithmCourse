import java.util.*;

class ShawnEditDistance {
  public static int editDistance(String s, String t) {
    // intialize array
    int[][] minEditMatrix = new int[s.length()+1][t.length()+1];
    for (int i = 0; i <= s.length(); i++) {
      minEditMatrix[i][0] = i;
    }

    for (int j = 1; j <= t.length(); j++) {
      minEditMatrix[0][j] = j;
    }

    // compute
    for (int i = 1; i <= s.length(); i++) {
      for (int j = 1; j <= t.length(); j++) {
        int minEdit = Integer.MAX_VALUE;

        if (s.charAt(i-1) == t.charAt(j-1)) {
          minEdit = minEditMatrix[i-1][j-1];
          minEditMatrix[i][j] = minEdit;
          continue;
        }

        // deletion
        if (minEditMatrix[i-1][j] + 1 < minEdit) {
          minEdit = minEditMatrix[i-1][j] + 1;
        }

        // insertion
        if (minEditMatrix[i][j-1] + 1 < minEdit) {
          minEdit = minEditMatrix[i][j-1] + 1;
        }

        // substitution
        if (minEditMatrix[i-1][j-1] + 1 < minEdit) {
          minEdit = minEditMatrix[i-1][j-1] + 1;
        }

        minEditMatrix[i][j] = minEdit;
      }
    }

    return minEditMatrix[s.length()][t.length()];
  }

  public static void main(String args[]) {
    Scanner scan = new Scanner(System.in);

    String s = scan.next();
    String t = scan.next();

    System.out.println(editDistance(s, t));
  }
}
