package dump.number_analysis.filter;

import dump.number_analysis.assessment.NumberUsageAssessment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ReferenceDataYYYFilter implements CandidateFilter {

    private final List<Set<Integer>> referenceData;
    private final int minFreq;
    private final Map<Integer, Integer> scores;
    private final int avgComboScore;
    private int maxComboScore;

    private int filtered;

    public ReferenceDataYYYFilter(final List<Set<Integer>> referenceData) {
        this.referenceData = referenceData;
        this.scores = new HashMap<>();
        NumberUsageAssessment ass = new NumberUsageAssessment(referenceData);
        int minNumber = ass.findLeastPopular(1).get(0);
        minFreq = ass.getFrequency(minNumber);
        for (int i = 1; i <= 47; i++) {
            int freq = ass.getFrequency(i);
            int score = freq - minFreq;
            scores.put(i, score);
        }

        int total = 0;
        for (int i = 0; i < referenceData.size(); i++) {
            Set<Integer> combo = referenceData.get(i);
            int sum = sum(combo);
            maxComboScore = Math.max(maxComboScore, sum);
//            System.out.printf("Combo [%s]: score [%d]\n", combo, sum);

            total += sum;
        }

        avgComboScore = total / referenceData.size();
    }

    @Override
    public boolean shouldFilter(Set<Integer> combo) {
        if (combo.size() != 5) {
            return false;
        }

        final int sum = sum(combo);
        if (sum > maxComboScore - 10 && sum < maxComboScore) {
            return true;
        } else {
            return false;
        }
    }

    public int sum(Set<Integer> combo) {
        return combo.stream()
                .mapToInt(scores::get)
                .sum();
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
        System.out.printf("Sums filtered: %d\n", filtered);
    }
}
