package dump.number_analysis.filter;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class EndNumberFilter implements CandidateFilter {

    private int filtered;

    @Override
    public boolean shouldFilter(Set<Integer> combo) {
        if (combo.size() != 5) {
            return false;
        }

        final Set<String> strings = combo.stream().map(Object::toString).collect(Collectors.toSet());
        final Set<String> numbers = Set.of("1", "2", "3", "4", "5", "6", "7", "8", "9");
        for (String n : numbers){
            if (strings.stream().allMatch(s -> s.endsWith(n))){
                filtered++;
                return true;
            }
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
        System.out.printf("Common endings filtered: %d\n", filtered);
    }
}
