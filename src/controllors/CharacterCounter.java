package controllors;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Keeps a running count of how many times each unique character is seen.
 */
public class CharacterCounter
{
    public final HashMap<Character, Integer> counts = new HashMap<>();

    /**
     * Increments the count of the given character,
     * setting it to one on first appearance.
     *
     * @param c the character to count
     */
    public void increment(Character c) {
        Integer freq = counts.get(c);
        if (freq == null) {
            counts.put(c, 1);
        } else {
            counts.put(c, freq + 1);
        }
    }

    /**
     * Increments the count of each character in the given text.
     *
     * @param text contains the characters to count
     */
    public void increment(String text) {
        	for (int i = 0, n = text.length(); i < n; i++) {
        	    char c = text.charAt(i);
        	    increment(c);
        	}
        
    }

    
    
    public HashMap<Character, Integer> getCounts() {
		return counts;
	}

	/**
     * Returns the set of characters seen.
     *
     * @return set containing each character seen while counting
     */
    public Set<Character> getCharacters() {
        return counts.keySet();
    }

    public Integer getValue(String i) {
        return counts.get(i);
    }
    public  Collection<Integer> getValues() {
        return counts.values();
    }
    /**
     * Returns the set of characters seen along with their counts.
     *collection you receive is not a copy, but still refers to the original
     * HashMap contents and therefore changes when you update the HashMap
     * @return set containing each character seen while counting and its count
     */
    public Set<Map.Entry<Character, Integer>> getCharacterCounts() {
        return counts.entrySet();
    }
}