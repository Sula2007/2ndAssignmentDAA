package algorithms;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.Random;

/**
 * Complete test suite for InsertionSort.
 */
class InsertionSortTest {

    private InsertionSort sorter;

    @BeforeEach
    void setUp() {
        sorter = new InsertionSort();
    }

    @Test
    void testEmpty() {
        assertArrayEquals(new int[]{}, sorter.sort(new int[]{}));
    }

    @Test
    void testNull() {
        assertNull(sorter.sort(null));
    }

    @Test
    void testSingle() {
        int[] arr = {42};
        assertArrayEquals(new int[]{42}, sorter.sort(arr));
        assertTrue(InsertionSort.isSorted(arr));
    }

    @Test
    void testTwo() {
        assertArrayEquals(new int[]{3, 5}, sorter.sort(new int[]{5, 3}));
    }

    @Test
    void testSorted() {
        int[] arr = {1, 2, 3, 4, 5};
        sorter.sort(arr);
        assertTrue(InsertionSort.isSorted(arr));
        assertTrue(sorter.getMetrics().getComparisons() <= arr.length);
    }

    @Test
    void testReverse() {
        int[] arr = {5, 4, 3, 2, 1};
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, sorter.sort(arr));
    }

    @Test
    void testRandom() {
        int[] arr = {3, 7, 1, 9, 2, 5, 8, 4, 6};
        sorter.sort(arr);
        assertTrue(InsertionSort.isSorted(arr));
        assertEquals(9, arr.length);
    }

    @Test
    void testDuplicates() {
        int[] arr = {5, 2, 8, 2, 9, 1, 5, 5};
        int[] expected = Arrays.copyOf(arr, arr.length);
        Arrays.sort(expected);
        assertArrayEquals(expected, sorter.sort(arr));
    }

    @Test
    void testAllSame() {
        assertArrayEquals(new int[]{7, 7, 7, 7}, sorter.sort(new int[]{7, 7, 7, 7}));
    }

    @Test
    void testNegatives() {
        int[] arr = {-5, 3, -1, 7, -9, 0};
        assertArrayEquals(new int[]{-9, -5, -1, 0, 3, 7}, sorter.sort(arr));
    }

    @Test
    void testBinaryInsertion() {
        int[] arr = {9, 3, 7, 1, 5};
        sorter.binaryInsertionSort(arr);
        assertTrue(InsertionSort.isSorted(arr));
    }

    @Test
    void testSentinel() {
        int[] arr = {9, 3, 7, 1, 5};
        sorter.sentinelSort(arr);
        assertTrue(InsertionSort.isSorted(arr));
    }

    @Test
    void testPropertyBased() {
        Random rand = new Random(42);

        for (int i = 0; i < 100; i++) {
            int size = rand.nextInt(100) + 1;
            int[] arr = new int[size];

            for (int j = 0; j < size; j++) {
                arr[j] = rand.nextInt(1000);
            }

            int[] expected = Arrays.copyOf(arr, arr.length);
            Arrays.sort(expected);

            assertArrayEquals(expected, sorter.sort(Arrays.copyOf(arr, arr.length)));
        }
    }

    @Test
    void testMetrics() {
        sorter.sort(new int[]{5, 2, 8, 1, 9});
        assertTrue(sorter.getMetrics().getComparisons() > 0);
        assertTrue(sorter.getMetrics().getSwaps() > 0);
        assertTrue(sorter.getMetrics().getExecutionTime() > 0);
    }

    @Test
    void testNearlySortedOptimization() {
        int[] nearly = {1, 2, 3, 5, 4, 6, 7, 8, 9, 10};
        sorter.sort(Arrays.copyOf(nearly, nearly.length));
        long nearlyComps = sorter.getMetrics().getComparisons();

        int[] reverse = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        sorter.sort(Arrays.copyOf(reverse, reverse.length));
        long reverseComps = sorter.getMetrics().getComparisons();

        assertTrue(nearlyComps < reverseComps);
    }

    @Test
    void testLarge() {
        int[] arr = new int[10000];
        Random r = new Random(42);
        for (int i = 0; i < arr.length; i++) {
            arr[i] = r.nextInt(100000);
        }
        sorter.sort(arr);
        assertTrue(InsertionSort.isSorted(arr));
    }
}
