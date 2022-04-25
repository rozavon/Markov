import java.util.*;

public class EfficientMarkov extends BaseMarkov {
	private Map<String,ArrayList<String>> myMap;
	
	public EfficientMarkov() {
		this(3);
		myMap = new HashMap<>();
	}

	public EfficientMarkov(int order) {
		super(order);
		myMap = new HashMap<>();
	}

	@Override
	public void setTraining(String text) {
		super.setTraining(text);
		myMap.clear();

		for (int k=0; k<myText.length()-myOrder+1; k++) {
			String current = myText.substring(k, k+myOrder);
			if (!myMap.containsKey(current)){
				myMap.put(current, new ArrayList<>());
			}
			if(myText.length() == myOrder + k)
				myMap.get(current).add(PSEUDO_EOS);
			else
			{
				String c = myText.substring(myOrder + k, myOrder + k + 1);
				myMap.get(current).add(c);
			}
		}
	}

	@Override
	public ArrayList<String> getFollows(String key) {
		if (!myMap.containsKey(key)) {
			throw new NoSuchElementException(key+" not in map");
		}
		return myMap.get(key);
	}
}	
