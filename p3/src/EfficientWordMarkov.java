import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class EfficientWordMarkov extends BaseWordMarkov {
    private Map<WordGram, ArrayList<String>> myMap;

    public EfficientWordMarkov() {
        this(2);
    }
    public EfficientWordMarkov(int order) {
        super(order);
        myOrder = order;
    }
    public void setTraining(String text) {
        myMap = new HashMap<>();
        myWords = text.split("\\s+");
        for (int k = 0; k<myWords.length-myOrder+1; k++) {
            WordGram current = new WordGram(myWords, k, myOrder);
            if (!myMap.containsKey(current)) {
                myMap.put(current, new ArrayList<>());
            }
            if (myWords.length == myOrder + k) {
                myMap.get(current).add(PSEUDO_EOS);
            }
            else {
                String c = myWords[myOrder+k];
                myMap.get(current).add(c);
            }
        }
    }

    public ArrayList<String> getFollows(WordGram key) {
        if (!myMap.containsKey(key)) {
            throw new NoSuchElementException(key+" not in map");
        }
        return myMap.get(key);
    }
}
