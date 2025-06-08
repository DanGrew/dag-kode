package dump.number_analysis.filter;

import java.util.Set;

public class ReferenceDataFilter implements CandidateFilter{

    private final Set<Set<Integer>> referenceData;
    private int filterCount = 0;

    public ReferenceDataFilter(final Set<Set<Integer>> referenceData) {
        this.referenceData = referenceData;
    }

    @Override
    public boolean shouldFilter(Set<Integer> combo) {
        if (referenceData.contains(combo)) {
            filterCount++;
            return true;
        }
        return false;
    }

    @Override
    public void reset() {
        filterCount = 0;
    }

    @Override
    public int getFilteredCount() {
        return filterCount;
    }

    @Override
    public void report(boolean verbose) {
        System.out.printf("Reference data filtered: %d\n", filterCount);
    }
}
