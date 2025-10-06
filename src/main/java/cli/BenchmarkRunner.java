package cli;

import algorithms.InsertionSort;
import metrics.PerformanceMetrics;
import java.util.Arrays;
import java.util.Random;

/**
 * Benchmark runner for Insertion Sort analysis.
 */
public class BenchmarkRunner {

    private static final int[] SIZES = {100, 1000, 5000, 10000, 50000, 100000};
    private static final String CSV = "benchmark_results.csv";

    public static void main(String[] args) {
        System.out.println("=== Insertion Sort Benchmark ===\n");

        PerformanceMetrics.createCSVHeader(CSV);
        InsertionSort sorter = new InsertionSort();

        for (int size : SIZES) {
            System.out.println("Size: " + size);

            test(sorter, generateRandom(size), size, "Random");
            test(sorter, generateSorted(size), size, "Sorted");
            test(sorter, generateReverse(size), size, "Reverse");
            test(sorter, generateNearlySorted(size), size, "NearlySorted");

            System.out.println();
        }

        System.out.println("Complete! Results: " + CSV);
    }

    private static void test(InsertionSort sorter, int[] arr, int size, String type) {
        System.out.print("  " + type + "... ");
        int[] copy = Arrays.copyOf(arr, arr.length);
        sorter.sort(copy);

        if (!InsertionSort.isSorted(copy)) {
            System.err.println("ERROR: Not sorted!");
        }

        PerformanceMetrics m = sorter.getMetrics();
        m.measureMemory();
        m.exportToCSV(CSV, size, "InsertionSort-" + type);
        System.out.printf("%.3fms%n", m.getExecutionTimeMs());
    }

    private static int[] generateRandom(int size) {
        Random r = new Random(42);
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = r.nextInt(size * 10);
        }
        return arr;
    }

    private static int[] generateSorted(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = i;
        }
        return arr;
    }

    private static int[] generateReverse(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = size - i;
        }
        return arr;
    }

    private static int[] generateNearlySorted(int size) {
        int[] arr = generateSorted(size);
        Random r = new Random(42);

        int swaps = size / 20;
        for (int i = 0; i < swaps; i++) {
            int a = r.nextInt(size);
            int b = r.nextInt(size);
            int temp = arr[a];
            arr[a] = arr[b];
            arr[b] = temp;
        }
        return arr;
    }
}
