package dump.number_analysis.filter;

import java.util.Set;

public class RepeatedNumbersFilter implements CandidateFilter{

    private int filterCount = 0;

    @Override
    public boolean isAnalysis() {
        return false;
    }

    @Override
    public boolean shouldFilter(Set<Integer> combo) {
        if ( combo.size() < 5){
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
        System.out.printf("Repeated numbers: %d\n", filterCount);
    }
}
