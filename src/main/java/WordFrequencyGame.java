import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WordFrequencyGame {

    public static final String SPACE_SEPARATOR = "\\s+";

    public String getResult(String inputStr) {
        String[] words = inputStr.split(SPACE_SEPARATOR);
        if (words.length == 1) {
            return inputStr + " 1";
        }
        Map<String, Long> word2Count = Stream.of(words)
                .collect(Collectors.groupingBy(s -> s, Collectors.counting()));

        List<Input> list = word2Count.entrySet().stream()
                .map(entry -> new Input(entry.getKey(), entry.getValue().intValue()))
                .sorted((w1, w2) -> w2.count() - w1.count())
                .toList();

        return composeOutput(list).toString();
    }

    private static StringJoiner composeOutput(List<Input> list) {
        StringJoiner joiner = new StringJoiner("\n");
        list.forEach(w -> joiner.add(w.value() + " " + w.count()));
        return joiner;
    }
}
