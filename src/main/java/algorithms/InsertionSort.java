package algorithms;

import metrics.PerformanceMetrics;

/**
 * Optimized Insertion Sort implementation with performance tracking.
 * Student A - Pair 1: Basic Quadratic Sorts
 */
public class InsertionSort {

    private PerformanceMetrics metrics;

    public InsertionSort() {
        this.metrics = new PerformanceMetrics();
    }

    /**
     * Standard insertion sort with optimizations for nearly-sorted data.
     * Time: O(n²) worst, O(n) best | Space: O(1)
     */
    public int[] sort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return arr;
        }

        metrics.reset();
        long startTime = System.nanoTime();

        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;

            metrics.incrementComparison();

            // Optimization: skip if already in position
            if (arr[j] <= key) {
                continue;
            }

            // Shift elements
            while (j >= 0 && arr[j] > key) {
                metrics.incrementComparison();
                metrics.incrementSwap();
                arr[j + 1] = arr[j];
                j--;
            }

            arr[j + 1] = key;
            metrics.incrementSwap();
        }

        metrics.setExecutionTime(System.nanoTime() - startTime);
        return arr;
    }

    /**
     * Binary Insertion Sort - uses binary search for insertion position.
     */
    public int[] binaryInsertionSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return arr;
        }

        metrics.reset();
        long startTime = System.nanoTime();

        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int pos = binarySearch(arr, key, 0, i - 1);

            for (int j = i - 1; j >= pos; j--) {
                metrics.incrementSwap();
                arr[j + 1] = arr[j];
            }
            arr[pos] = key;
        }

        metrics.setExecutionTime(System.nanoTime() - startTime);
        return arr;
    }

    private int binarySearch(int[] arr, int key, int low, int high) {
        while (low <= high) {
            metrics.incrementComparison();
            int mid = low + (high - low) / 2;

            if (arr[mid] == key) {
                return mid + 1;
            } else if (arr[mid] < key) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }

    /**
     * Sentinel Sort - eliminates boundary check.
     */
    public int[] sentinelSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return arr;
        }

        metrics.reset();
        long startTime = System.nanoTime();

        // Place minimum at start
        int minIdx = 0;
        for (int i = 1; i < arr.length; i++) {
            metrics.incrementComparison();
            if (arr[i] < arr[minIdx]) {
                minIdx = i;
            }
        }

        int temp = arr[0];
        arr[0] = arr[minIdx];
        arr[minIdx] = temp;
        metrics.incrementSwap();

        // Sort with sentinel
        for (int i = 2; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;

            while (arr[j] > key) {
                metrics.incrementComparison();
                metrics.incrementSwap();
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }

        metrics.setExecutionTime(System.nanoTime() - startTime);
        return arr;
    }

    public PerformanceMetrics getMetrics() {
        return metrics;
    }

    public static boolean isSorted(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < arr[i - 1]) {
                return false;
            }
        }
        return true;
    }
}
