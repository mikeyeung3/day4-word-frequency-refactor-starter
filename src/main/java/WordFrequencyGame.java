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
            List<Input> inputList = new ArrayList<>();
            for (String s : words) {
                Input input = new Input(s, 1);
                inputList.add(input);
            }
            //get the map for the next step of sizing the same word
            Map<String, List<Input>> map = inputList.stream().collect(Collectors.groupingBy(Input::getValue));
            List<Input> list = new ArrayList<>();
            for (Map.Entry<String, List<Input>> entry : map.entrySet()) {
                Input input = new Input(entry.getKey(), entry.getValue().size());
                list.add(input);
            }
            inputList = list;
            inputList.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());
            StringJoiner joiner = new StringJoiner("\n");
            for (Input w : inputList) {
                String s = w.getValue() + " " + w.getWordCount();
                joiner.add(s);
            }
            return joiner.toString();
        } catch (Exception e) {
            return "Calculate Error";
        }
    }
}
