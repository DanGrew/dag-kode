package dump.number_analysis.filter;

import java.util.Set;

public class LowHighRangeFilter implements CandidateFilter {

    private int filtered;

    @Override
    public boolean shouldFilter(Set<Integer> combo) {
        final boolean allLow = combo.stream().allMatch(n -> n < 24);
        final boolean allHigh = combo.stream().allMatch(n -> n > 23);
        if (allLow || allHigh) {
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
        System.out.printf("All lows/highs filtered: %d\n", filtered);
    }
}
