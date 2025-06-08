package dump.number_analysis.filter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DecadeFilter implements CandidateFilter {

    private int filtered;

    @Override
    public boolean shouldFilter(Set<Integer> combo) {
        if (combo.size() != 5) {
            return false;
        }

        final Set<Integer> decades = new HashSet<>();
        for (Integer v : combo) {
            decades.add(v / 10);
        }
        if (decades.size() < 3) {
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
        System.out.printf("Decades filtered: %d\n", filtered);
    }
}
