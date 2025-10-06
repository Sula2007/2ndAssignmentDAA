Sort - Assignment 2

**Student A | Pair 1: Basic Quadratic Sorts**

## Quick Start

```bash
mvn clean compile
mvn test
mvn exec:java
```

## Structure

```
src/main/java/
  ├── algorithms/InsertionSort.java
  ├── metrics/PerformanceMetrics.java
  └── cli/BenchmarkRunner.java
src/test/java/
  └── algorithms/InsertionSortTest.java
```

## Complexity

| Case | Time | Space |
|------|------|-------|
| Best | Θ(n) | Θ(1) |
| Average | Θ(n²) | Θ(1) |
| Worst | Θ(n²) | Θ(1) |

## Implementations

1. **Standard** - Optimized for nearly-sorted data
2. **Binary Insertion** - Binary search for position
3. **Sentinel** - Eliminates boundary checks

## Optimizations

- Early termination for sorted elements
- Binary search reduces comparisons
- Sentinel removes inner loop bounds check

## Testing

- Edge cases (empty, null, single)
- All input types (random, sorted, reverse, nearly-sorted)
- 100 property-based random tests
- Large arrays (10K elements)

## Benchmarks

Sizes: 100, 1K, 5K, 10K, 50K, 100K

Output: `benchmark_results.csv`

## Git Workflow

- `main` - releases only
- `feature/algorithm` - implementation
- `feature/metrics` - tracking
- `feature/testing` - tests
- `feature/cli` - benchmark
- `feature/optimization` - improvements

## Author

Student A - Insertion Sort with nearly-sorted optimizations
