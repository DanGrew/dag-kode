package dump.number_analysis.filter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ReferenceDataSimilarityFilter implements CandidateFilter{

    private final Set<Integer> referenceDataItem;
    private int filterCount = 0;

    public ReferenceDataSimilarityFilter(final Set<Integer> referenceDataItem) {
        this.referenceDataItem = referenceDataItem;
    }

    @Override
    public boolean shouldFilter(Set<Integer> combo) {
        final List<Integer> copy = new ArrayList<>(combo);
        copy.retainAll(referenceDataItem);

        if (copy.size() > 3) {
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
        System.out.printf("Reference data similarity filtered: %d\n", filterCount);
    }
}
