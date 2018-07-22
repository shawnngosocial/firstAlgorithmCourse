import java.util.*;

public class ShawnCoveringSegments {


    // TEST QUICK SORT
    // public static void main(String[] args) {
    //     Scanner scanner = new Scanner(System.in);
    //     int n = scanner.nextInt();
    //     Segment[] segments = new Segment[n];
    //     for (int i = 0; i < n; i++) {
    //         int start, end;
    //         start = scanner.nextInt();
    //         end = scanner.nextInt();
    //         segments[i] = new Segment(start, end);
    //     }
        
    //     System.out.println("\nBefore sorting...........");
    //     printSegments(segments);

    //     // sort segments
    //     // quickSort(segments, 0, segments.length - 1);
    //     sortNaive(segments);
        
    //     System.out.println("\nAfter sorting...........");
    //     printSegments(segments);
    // }

    // STRESS TEST
    // public static void main(String[] args) {
    //     Random ran = new Random();

    //     while (true) {
    //         int n = ran.nextInt(30);

    //         Segment[] quickSegment = new Segment[n];
    //         Segment[] naiveSegment = new Segment[n];

    //         // create random segments
    //         for (int i = 0; i < n; i++) {
    //             int start = ran.nextInt(100);
    //             int end = ran.nextInt(100) + start;

    //             Segment segment = new Segment(start, end);
    //             quickSegment[i] = segment;
    //             naiveSegment[i] = segment;
    //         }
            
    //         printSegments(quickSegment);
    //         System.out.println("\n");

    //         // sort
    //         quickSort(quickSegment, 0, quickSegment.length - 1);
    //         sortNaive(naiveSegment);

    //         boolean isTrue = true;
    //         for (int i = 0; i < n; i++) {
    //             if (quickSegment[i] != naiveSegment[i]) {
    //                 isTrue = false;
    //                 break;
    //             }
    //         }

    //         if (isTrue) {
    //             System.out.println("Ok.\n");

    //             System.out.println("quickSort:\n");
    //             printSegments(quickSegment);

    //             System.out.println("\nnaiveSort:\n");
    //             printSegments(naiveSegment);

    //             System.out.println("\n");

    //         } else {
    //             System.out.println("Wrong.\n");

    //             System.out.println("quickSort:\n");
    //             printSegments(quickSegment);

    //             System.out.println("\nnaiveSort:\n");
    //             printSegments(naiveSegment);

    //             System.out.println("\n");
    //             break;
    //         }
    //     }
    // }

    private static void printSegments(Segment[] arr) {        
        for (int i = 0; i < arr.length; i++) {
            if (i == arr.length - 1) {
                System.out.println(arr[i].toString());
            } else {
                System.out.println(arr[i].toString() + ", ");
            }        
        }
    }


    private static void sortNaive(Segment[] segments) {
        for (int i = 0; i < segments.length; i++) {
            for (int j = i; j > 0; j--) {
                if (segments[j].getStart() < segments[j-1].getStart()) {
                    Segment temp = segments[j];
                    segments[j] = segments[j-1];
                    segments[j-1] = temp;
                }
            }
        }
    }


    private static void quickSort(Segment[] arr, int l, int r) {
        if (l < r) {
            int mid = (l+r)/2;

            quickSort(arr, l, mid);
            quickSort(arr, mid+1, r);

            merge(arr, l, mid, r);
        }
    }

    private static void merge(Segment[] arr, int l, int m, int r) {
        int leftLength = m - l + 1;
        int rightLength = r - m;

        Segment[] leftSegments = new Segment[leftLength];
        Segment[] rightSegments = new Segment[rightLength];

        // copy Segments
        for (int i = 0; i < leftLength; i++) {
            leftSegments[i] = arr[l+i];
        }

        for (int i = 0; i < rightLength; i++) {
            rightSegments[i] = arr[m + 1 + i];
        }

        int k = 0; 
        int j = 0;
        int index = l;
        while (k < leftLength && j < rightLength) {
            if (leftSegments[k].getStart() <= rightSegments[j].getStart()) {
                arr[index] = leftSegments[k];
                k++;
            } else {
                arr[index] = rightSegments[j];
                j++;
            }

            index++;
        }

        // copy remaining elements from left
        while (k < leftLength) {
            arr[index] = leftSegments[k];
            k++;
            index++;
        }

        // copy remaining elements from right
        while (j < rightLength) {
            arr[index] = rightSegments[j];
            j++;
            index++;
        }
    }


    private static int[] getOptimalPoints(Segment[] segments) {
        if (segments.length == 1) {
            return new int[]{segments[0].getStart()};
        }

        // sort
        quickSort(segments, 0, segments.length - 1);

        int index = 0;
        Segment[] result = new Segment[segments.length];        
        result[index] = segments[0];

        for (int i = 1; i < segments.length; i++) {                    
            Segment intersectSegment = Segment.intersect(result[index], segments[i]);

            if (intersectSegment == null) {
                result[++index] = segments[i];
            } else {
                result[index] = intersectSegment;
            }
        }

        int[] points = new int[index+1];
        for (int i = 0; i < index+1; i++) {
            points[i] = result[i].getStart();
        }

        return points;
    }

    private static class Segment {
        int start, end;

        Segment(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int getStart() {
            return start;            
        }

        public int getEnd() {
            return end;
        }

        public String toString() {
            return "[" + start + "," + end + "]";
        }

        public static Segment intersect(Segment firstSegment, Segment secondSegment) {
            int startDifference = secondSegment.getStart() - firstSegment.getStart();
            int endDifference = secondSegment.getEnd() - firstSegment.getEnd();

            if (startDifference >= 0) {
                if (endDifference >= 0) {
                    if (firstSegment.getEnd() >= secondSegment.getStart()) {
                        return new Segment(secondSegment.getStart(), firstSegment.getEnd());
                    } else {
                        return null;
                    }
                } else {
                    return secondSegment;
                }
            } else {
                if (endDifference >= 0) {
                    return firstSegment;
                } else {
                    if (firstSegment.getStart() > secondSegment.getEnd()) {
                        return null;
                    } else {
                        return new Segment(firstSegment.getStart(), secondSegment.getEnd());
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Segment[] segments = new Segment[n];
        for (int i = 0; i < n; i++) {
            int start, end;
            start = scanner.nextInt();
            end = scanner.nextInt();
            segments[i] = new Segment(start, end);
        }
        int[] points = getOptimalPoints(segments);

        System.out.println(points.length);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < points.length; i++) {
            sb.append(points[i]).append(" ");
        }
        System.out.println(sb.toString());
    }
}
 
