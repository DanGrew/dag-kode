package dump.number_analysis.filter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SequenceFilter implements CandidateFilter {

    private int filtered;

    @Override
    public boolean shouldFilter(Set<Integer> combo) {
        if (combo.size() != 5) {
            return false;
        }
        final List<Integer> list = new ArrayList<>(combo);
        int count = 0;
        for (int i = 0; i < 4; i++) {
            if (list.get(i + 1) - list.get(i) == 1) {
                count++;
            }
        }
        if (count > 2) {
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
        System.out.printf("Sequences filtered: %d\n", filtered);
    }
}
