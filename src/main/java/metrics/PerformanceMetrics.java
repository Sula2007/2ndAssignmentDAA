package metrics;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Performance tracking for algorithm analysis.
 */
public class PerformanceMetrics {

    private long comparisons;
    private long swaps;
    private long arrayAccesses;
    private long executionTime;
    private long memoryUsed;

    public PerformanceMetrics() {
        reset();
    }

    public void reset() {
        this.comparisons = 0;
        this.swaps = 0;
        this.arrayAccesses = 0;
        this.executionTime = 0;
        this.memoryUsed = 0;
    }

    public void incrementComparison() {
        this.comparisons++;
    }

    public void incrementSwap() {
        this.swaps++;
    }

    public void incrementArrayAccess() {
        this.arrayAccesses++;
    }

    public void setExecutionTime(long nanos) {
        this.executionTime = nanos;
    }

    public void measureMemory() {
        Runtime runtime = Runtime.getRuntime();
        runtime.gc();
        this.memoryUsed = runtime.totalMemory() - runtime.freeMemory();
    }

    public long getComparisons() { return comparisons; }
    public long getSwaps() { return swaps; }
    public long getArrayAccesses() { return arrayAccesses; }
    public long getExecutionTime() { return executionTime; }
    public double getExecutionTimeMs() { return executionTime / 1_000_000.0; }
    public long getMemoryUsed() { return memoryUsed; }

    public void exportToCSV(String filename, int inputSize, String algorithmName) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename, true))) {
            writer.printf("%s,%d,%d,%d,%d,%.3f,%d%n",
                    algorithmName, inputSize, comparisons, swaps,
                    arrayAccesses, getExecutionTimeMs(), memoryUsed
            );
        } catch (IOException e) {
            System.err.println("Error writing CSV: " + e.getMessage());
        }
    }

    public static void createCSVHeader(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename, false))) {
            writer.println("Algorithm,InputSize,Comparisons,Swaps,ArrayAccesses,TimeMs,MemoryBytes");
        } catch (IOException e) {
            System.err.println("Error creating CSV: " + e.getMessage());
        }
    }

    @Override
    public String toString() {
        return String.format("Comparisons=%d, Swaps=%d, Time=%.3fms, Memory=%dKB",
                comparisons, swaps, getExecutionTimeMs(), memoryUsed / 1024);
    }
}
