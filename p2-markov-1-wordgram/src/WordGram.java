import java.util.Arrays;

/**
 * A WordGram represents a sequence of strings
 * just as a String represents a sequence of characters
 * 
 * @author Robert Zavon
 *
 */
public class WordGram {
	
	private String[] myWords;   
	private String myToString;  // cached string
	private int myHash;         // cached hash value

	/**
	 * Create WordGram by creating instance variable myWords and copying
	 * size strings from source starting at index start
	 * @param source is array of strings from which copying occurs
	 * @param start starting index in source for strings to be copied
	 * @param size the number of strings copied
	 */
	public WordGram(String[] source, int start, int size) {
		myWords = new String[size];
		myToString = ""; // Leave this alone, you'll change myToString in toString
		myHash = 0; //Leave this alone, you'll change myHash in hashCode

		myWords = Arrays.copyOfRange(source, start, start+size);
		}


	/**
	 * Return string at specific index in this WordGram
	 * @param index in range [0..length() ) for string 
	 * @return string at index
	 */
	public String wordAt(int index) {
		if (index < 0 || index >= myWords.length) {
			throw new IndexOutOfBoundsException("bad index in wordAt "+index);
		}
		return myWords[index];
	}

	@Override
	public String toString(){
		myToString = String.join(" ", myWords);
		return myToString;
	}

	@Override
	public int hashCode(){
		myHash = this.toString().hashCode();
		return myHash;
	}

	@Override
	public boolean equals(Object o) {
		if (! (o instanceof WordGram) || o == null){
			return false;
		}
		if (!(((WordGram) o).length() == this.myWords.length)){
			return false;
		}
		for (int i=0; i<((WordGram) o).length(); i++){
			if (!(((WordGram) o).myWords[i].equals(this.myWords[i]))){
				return false;
			}
		}
		return true;
	}

	public int length(){
		return myWords.length;
	}

	public WordGram shiftAdd(String last) {
		WordGram wg = new WordGram(myWords,0,myWords.length);
		wg.myWords[myWords.length-1] = last;
		for (int i=1; i<myWords.length; i++){
			wg.myWords[i-1] = myWords[i];
		}

		return wg;
	}
}
