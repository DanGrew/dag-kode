package dump.number_analysis.filter;

import java.util.Set;

public class ReferenceDataSumFilter implements CandidateFilter {

    private final Set<Set<Integer>> referenceData;
    private final int minSum;
    private final int maxSum;
    private int filtered;

    public ReferenceDataSumFilter(final Set<Set<Integer>> referenceData) {
        this.referenceData = referenceData;
        this.minSum = referenceData.stream()
                .mapToInt(combo -> combo.stream().mapToInt(v -> v).sum())
                .min()
                .getAsInt();
        this.maxSum = referenceData.stream()
                .mapToInt(combo -> combo.stream().mapToInt(v -> v).sum())
                .max()
                .getAsInt();
    }

    @Override
    public boolean shouldFilter(Set<Integer> combo) {
        if (combo.size() != 5) {
            return false;
        }

        final int sum = combo.stream().mapToInt(v -> v).sum();
        if (sum < minSum) {
            filtered++;
            return true;
        } else if (sum > maxSum) {
            filtered++;
            return true;
        }
        return false;
    }

    @Override
    public void reset() {
        filtered = 0;
    }

    @Override
    public int getFilteredCount() {
        return filtered;
    }

    @Override
    public void report(boolean verbose) {
        System.out.printf("Sums filtered: %d\n", filtered);
    }
}
