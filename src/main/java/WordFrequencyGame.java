import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class WordFrequencyGame {

    public static final String SPACE_SEPARATOR = "\\s+";

    public String getResult(String inputStr) {
        String[] words = inputStr.split(SPACE_SEPARATOR);
        if (words.length == 1) {
            return inputStr + " 1";
        }
        try {
            List<String> inputList = List.of(words);

            Map<String, Long> map = inputList.stream()
                .collect(Collectors.groupingBy(s -> s, Collectors.counting()));

            List<Input> list = new ArrayList<>();
            for (Map.Entry<String, Long> entry : map.entrySet()) {
                Input input = new Input(entry.getKey(), entry.getValue().intValue());
                list.add(input);
            }

            list.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());

            return  composeOutput(list).toString();
        } catch (Exception e) {
            return "Calculate Error";
        }
    }

    private static StringJoiner composeOutput(List<Input> list) {
        StringJoiner joiner = new StringJoiner("\n");
        list.forEach(w -> joiner.add(w.getValue() + " " + w.getWordCount()));
        return joiner;
    }
}
