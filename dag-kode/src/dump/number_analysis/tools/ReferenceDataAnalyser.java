package dump.number_analysis.tools;

import dump.number_analysis.filter.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ReferenceDataAnalyser {

    private final List<CandidateFilter> datasetReducers;

    public ReferenceDataAnalyser() {
        this.datasetReducers = List.of(
                new OddsAndEvensFilter(),
                new LowHighRangeFilter(),
                new SequenceFilter(),
                new DecadeFilter(),
                new EndNumberFilter());
    }

    public void analysePrevious(final Set<Set<Integer>> referenceDataset) {
        int counter = 0;
        for (Set<Integer> combo : referenceDataset) {
            final String filterDesc = datasetReducers.stream()
                    .filter(CandidateFilter::isAnalysis)
                    .filter(f -> f.shouldFilter(combo))
                    .map(f -> f.getClass().getSimpleName())
                    .collect(Collectors.joining("\n"));
            if (!filterDesc.isEmpty()) {
                counter++;
                System.out.printf("Combo: %s\n", combo);
                System.out.printf("Filtered by: %s\n", filterDesc);
            }
        }
        System.out.printf("Filtered %d/%d", counter, referenceDataset.size());
    }
}
