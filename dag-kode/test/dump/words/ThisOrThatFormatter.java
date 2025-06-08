package dump.words;

import java.util.*;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ThisOrThatFormatter {
    private final Random random;

    private final Map<String, String> matches;

    public ThisOrThatFormatter(Random random) {
        this.random = random;
        this.matches = new HashMap<>();
    }

    public void matchWords(List<String> gridWords, List<String> allWords) {
        final var matchList = new ArrayList<>(allWords);
        matchList.removeAll(gridWords);
        gridWords.sort(Comparator.comparingInt(String::length).reversed());

        tryMatch(gridWords, matchList, (s, l) -> s.length() == l);
        tryMatch(gridWords, matchList, (s, l) -> l - s.length() == 1);
        tryMatch(gridWords, matchList, (s, l) -> l - s.length() == 2);
        tryMatch(gridWords, matchList, (s, l) -> s.length() - l == 1);
        tryMatch(gridWords, matchList, (s, l) -> s.length() - l == 2);
        tryMatch(gridWords, matchList, (s, l) -> s.length() >= l);
        tryMatch(gridWords, matchList, (s, l) -> s.length() < l);
        for (String gridWord : gridWords) {
            System.out.printf("Can't match: [%s]\n", gridWord);
        }
    }

    private void tryMatch(List<String> gridWords, List<String> matchList, BiPredicate<String, Integer> filter) {
        final var matched = new ArrayList<>();
        for (String gridWord : gridWords) {
            if (gridWord.equalsIgnoreCase("quoits")){
//                System.out.println();
            }
            int length = gridWord.length();
            final var match = matchList.stream()
                    .filter(s -> filter.test(s, length))
                    .sorted(Comparator.comparing(String::length))
                    .findFirst();
            if (match.isPresent()) {
                handleMatch(gridWord, match.get(), matchList);
                matched.add(gridWord);
            }
        }
        gridWords.removeAll(matched);
    }

    private void handleMatch(String gridWord, String match, List<String> matchList) {
        if (match.isEmpty()) {
            System.out.printf("Can't match: [%s]\n", gridWord);
        } else {
            matches.put(gridWord, match);
            matchList.remove(match);
        }
    }

    public String getMatch(String gridWord) {
        return matches.getOrDefault(gridWord, null);
    }

    public List<String> buildWordList() {
        return matches.entrySet().stream()//.sorted(Comparator.comparing(e -> e.getKey(), String::compareToIgnoreCase))
                .map(e -> format(e.getKey(), e.getValue()))
                .collect(Collectors.toList());
    }

    private String format(String gridWord, String match) {
        final var gridWordFirst = random.nextBoolean();
        if (gridWordFirst) {
            return gridWord + " OR " + match;// + String.format("[gw: %d, m: %d]", gridWord.length(), match.length());
        } else {
            return match + " OR " + gridWord;// + String.format("[gw: %d, m: %d]", gridWord.length(), match.length());
        }
    }
}
