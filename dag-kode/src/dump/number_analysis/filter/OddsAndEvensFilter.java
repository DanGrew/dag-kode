package dump.number_analysis.filter;

import java.util.Set;

public class OddsAndEvensFilter implements CandidateFilter {

    private int filtered;

    @Override
    public boolean shouldFilter(Set<Integer> combo) {
        final boolean allOdd = combo.stream().allMatch(n -> n % 2 == 1);
        final boolean allEven = combo.stream().allMatch(n -> n % 2 == 0);
        if (allOdd || allEven) {
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
        System.out.printf("All odds/evens filtered: %d\n", filtered);
    }
}
