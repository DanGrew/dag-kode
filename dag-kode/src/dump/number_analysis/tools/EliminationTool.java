package dump.number_analysis.tools;

import dump.number_analysis.filter.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class EliminationTool {

    final int DEFENSE_LIMIT = 500000;

    private final List<CandidateFilter> sanitisers = List.of(
            new RepeatedNumbersFilter());
    private final List<CandidateFilter> datasetReducers;

    private Set<Set<Integer>> successfulCandidates = new HashSet<>();
    private int totalScanned;

    public EliminationTool(final List<Set<Integer>> referenceDataset, final Set<Integer> latestDataItem) {
        this.datasetReducers = List.of(
//                new ReferenceDataFilter(referenceDataset),
//                new ReferenceDataSimilarityFilter(latestDataItem),
//                new ReferenceDataSumFilter(referenceDataset),
//                new OddsAndEvensFilter(),
//                new LowHighRangeFilter(),
//                new SequenceFilter(),
//                new DecadeFilter(),
//                new EndNumberFilter(),
                new ReferenceDataYYYFilter(referenceDataset));
    }

    public void eliminateFromAllCandidates(int spaceSize) {
        final long start = System.currentTimeMillis();

        run(spaceSize);

        final long end = System.currentTimeMillis();
        System.out.printf("Time taken: %d millis\n", end - start);
        datasetReducers.forEach(f -> f.report(false));
        System.out.printf("Candidates identified %d\n", totalScanned);
        final var eliminatedCandidates = datasetReducers.stream()
                .filter(CandidateFilter::isAnalysis)
                .mapToInt(CandidateFilter::getFilteredCount)
                .sum();
        System.out.printf("Eliminated candidates: %d\n", eliminatedCandidates);
        System.out.println("Successful candidates:");
        successfulCandidates.forEach(c -> System.out.println(c));
    }

    private void run(int spaceSize) {
        totalScanned = 0;

        sanitisers.forEach(CandidateFilter::reset);
        datasetReducers.forEach(CandidateFilter::reset);

        for (int a = 1; a < spaceSize; a++) {
            for (int b = 1; b < spaceSize; b++) {
                for (int c = 1; c < spaceSize; c++) {
                    for (int d = 1; d < spaceSize; d++) {
                        for (int e = 1; e < spaceSize; e++) {
                            totalScanned++;
                            if (totalScanned > DEFENSE_LIMIT) {
                                System.out.println("Limit reached...");
                                return;
                            }

                            final Set<Integer> combo = new HashSet<>(List.of(a, b, c, d, e));
                            if (shouldFilterCombo(combo)) {
                                continue;
                            }

                            successfulCandidates.add(combo);
                        }
                    }
                }
            }
        }
    }

    public boolean shouldFilterCombo(final Set<Integer> combo) {
        if (sanitisers.stream().anyMatch(f -> f.shouldFilter(combo))){
            return true;
        }
        final var shouldFilter = datasetReducers.stream().map(filter -> filter.shouldFilter(combo)).collect(Collectors.toSet());
        return shouldFilter.contains(true);
    }

}
