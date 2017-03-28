package controllors;



import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;



abstract class HuffmanTree implements Comparable<HuffmanTree> {
	public final int frequency; // the frequency of this tree



	public HuffmanTree(int freq) { 
		frequency = freq;
	}

	// compares on the frequency
	public int compareTo(HuffmanTree tree) {
		return frequency - tree.frequency;
	}
}

class HuffmanLeaf extends HuffmanTree {
	public final char value; // the character this leaf represents

	public HuffmanLeaf( char val, int freq) {
		super(freq);
		value = val;
	}



}

class HuffmanNode extends HuffmanTree {
	public final HuffmanTree left, right; // subtrees

	public HuffmanNode(HuffmanTree l, HuffmanTree r) {
		super(l.frequency + r.frequency);
		left = l;
		right = r;
	}
}

public class Huffman {

	HashMap<Character, String> code = new HashMap<>();
	HashMap<String, Character> decode = new HashMap<>();
	HashMap<String, Character> decodeMap = new HashMap<String, Character>();
	
	StringBuffer test;
	String result;



	// input is an array of frequencies, indexed by character code
	public HuffmanTree buildTree(char[] key ,int[] value) {
		PriorityQueue<HuffmanTree> trees = new PriorityQueue<HuffmanTree>();
		// initially, we have a forest of leaves
		// one for each non-empty character

		for (int i = 0; i < key.length; i++)
			if (key[i] > 0)
				trees.offer(new HuffmanLeaf(key[i], value[i]));

		assert trees.size() > 0;
		// loop until there is only one tree left
		while (trees.size() > 1) {
			// two trees with least frequency
			HuffmanTree a = trees.poll();
			HuffmanTree b = trees.poll();

			// put into new node and re-insert into queue
			trees.offer(new HuffmanNode(a, b));
		}

		return trees.poll();

	}


	public  void printMap(CharacterCounter c) {


		char[] key = new char[c.counts.size()];
		int[] value = new int[c.counts.size()];
		int index = 0;
		for (Map.Entry<Character, Integer> mapEntry : c.getCharacterCounts()) {
			key[index] = mapEntry.getKey();
			value[index] = mapEntry.getValue();
			index++;
		}
		HuffmanTree tree = buildTree(key,value);

		outputTree(tree, "");




	}



	// Print out the codes; insert these codes into CodeTable
	public  void outputTree(HuffmanTree tree, String prefix)  {
		assert tree != null : "Bad input tree";




		// This is a full binary tree so must not be null subtree
		if (tree instanceof HuffmanLeaf) {
			HuffmanLeaf leaf = (HuffmanLeaf)tree;
			System.out.println(leaf.value + "\t" + prefix);
			char temp  = leaf.value;

			//converting to bytes

			//byte b = binaryStringToByte(prefix);
			//String s1 = String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0');


			code.put(temp, prefix);
			decode.put(prefix, temp);
			// ByteBuffer bytearray = ByteBuffer.allocate(decode.size());
			//bytearray.put(b);
			//bytearray.flip();
			//byte[] bytes = new byte[bytearray.remaining()]; 
			//bytearray.get(bytes);
			//String bytearrayString = new String(bytearray.array(), "UTF-8");
			//System.out.println(bytearrayString);




			//total += prefix.length() * node.weight();

			//print string of codes
			StringBuffer huff =  new StringBuffer();
			String hexCode= "0CADD099";
			
			//huff.append(hexCode);




			for(int i = 0; i < Item.item.length(); i++){
				char temp4 = Item.item.charAt(i);
				huff.append(code.get(temp4));
			}
			test = huff;

		}
		else if (tree instanceof HuffmanNode) {
			HuffmanNode node = (HuffmanNode)tree; {
				outputTree(node.left, prefix + "0");
				outputTree(node.right, prefix + "1");
			}

		}

	}


	public String printDecode(String binary, String result) {
    
			
				for (int i = 0; i <= binary.length(); i++) {

					String temp = binary.substring(0, i);

					if (decodeMap.containsKey(temp)) {
						
						result = result + decodeMap.get(temp);

						result = printDecode(binary.substring(i, binary.length()), result);
						return result;
						}
					
				}// end of for
	
		
		return result;
		}
		
	
	
	
	
	public  byte binaryStringToByte(String s) {
	    //also check for null, length = 8, contain 0/1 only etc.
	    if (s.startsWith("0")) {
	        return Byte.parseByte(s, 2);
	    } else {
	        StringBuilder sBuilder = new StringBuilder(s);
	        sBuilder.setCharAt(0, '0');
	        byte temp = Byte.parseByte(sBuilder.toString(), 2);
	        return (byte) (temp | (1 << 7));
	    }
	}
	
}// end of Huffman
