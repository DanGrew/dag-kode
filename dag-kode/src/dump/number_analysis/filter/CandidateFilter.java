package dump.number_analysis.filter;

import java.util.Set;

public interface CandidateFilter {
    default boolean isAnalysis() {
        return true;
    }

    public boolean shouldFilter(Set<Integer> combo);

    public void reset();

    public int getFilteredCount();

    public void report(boolean verbose);
}
