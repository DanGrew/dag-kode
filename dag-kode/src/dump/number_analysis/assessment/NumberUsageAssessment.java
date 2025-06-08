package dump.number_analysis.assessment;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Map.Entry.comparingByValue;

public class NumberUsageAssessment {

    private final Map<Integer, Integer> usedPercent = new HashMap<>();
    private final Collection<Set<Integer>> dataset;

    public NumberUsageAssessment(final Collection<Set<Integer>> referenceData) {
        this.dataset = referenceData;
        final Map<Integer, Integer> freqs = new HashMap<>();
        for (Set<Integer> combo : referenceData) {
            for (Integer v : combo) {
                final int newFreq = freqs.getOrDefault(v, 0) + 1;
                freqs.put(v, newFreq);
            }
        }
        usedPercent.putAll(freqs);
//        freqs.forEach((key, value) -> usedPercent.put(key, percentage(value, referenceData.size())));
    }

    private int percentage(int freq, int datasetSize) {
        return (int) ((((double) freq) / datasetSize) * 100);
    }

    public List<Integer> findMostPopular(int count) {
        return find(count, comparingByValue(Comparator.reverseOrder()));
    }

    public List<Integer> findLeastPopular(int count) {
        return find(count, comparingByValue());
    }

    public int getFrequency(int number) {
        return usedPercent.get(number);
    }

    private List<Integer> find(int count, Comparator<Map.Entry<Integer, Integer>> comparator) {
        final Set<Integer> topFreqs = usedPercent.entrySet()
                .stream()
                .sorted(comparator)
                .limit(count)
                .map(Map.Entry::getValue)
                .collect(Collectors.toSet());

        return usedPercent.entrySet()
                .stream()
                .filter(e -> topFreqs.contains(e.getValue()))
                .sorted(comparator)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public double findAverageUseOfStrongNumbers(int count) {
        List<Integer> strongNums = findMostPopular(count);
        double avgStrong = findAvgUsage(strongNums);
        return avgStrong;
    }

    public double findAverageUseOfWeakNumbers(int count) {
        List<Integer> weakNums = findLeastPopular(count);
        double avgWeak = findAvgUsage(weakNums);
        return avgWeak;
    }

    private double findAvgUsage(List<Integer> numbersToUse) {
        int totalCount = 0;
        for (Set<Integer> previousCombo : dataset) {
            Set<Integer> copy = new HashSet<>(previousCombo);
            copy.retainAll(numbersToUse);
            totalCount += copy.size();
        }
        return (double) totalCount / dataset.size();
    }
}
