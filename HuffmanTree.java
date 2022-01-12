import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
public class HuffmanTree {
	
static String readFile(String path, Charset encoding)
			  throws IOException
			{
			  byte[] encoded = Files.readAllBytes(Paths.get(path));
			  return new String(encoded, encoding);
	         }

	public static void convertToHuffmanTree(String text)
	{
	if (text.length() == 0) {
	  return;
	}
	Map<Character, Integer> freqencyTable = new HashMap<>();
	for (char c: text.toCharArray()) {
		freqencyTable.put(c, freqencyTable.getOrDefault(c, 0) + 1);
	}
	PriorityQueue<HuffmanNode> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(HuffManNode -> HuffManNode.freq));

	for (var eachNode: freqencyTable.entrySet()) {
	HuffmanNode temp =new HuffmanNode(eachNode.getKey(), eachNode.getValue());
	priorityQueue.add(temp);
	}

	while (priorityQueue.size() > 1)
	{
	  HuffmanNode left = priorityQueue.poll();
	  HuffmanNode right = priorityQueue.poll();
	  int sum = left.freq + right.freq;
	  HuffmanNode temp=new HuffmanNode(null, sum, left, right);
	  priorityQueue.add(temp);
	}

	HuffmanNode root = priorityQueue.peek();
	Map<Character, String> dictionary = new HashMap<>();
	encode(root, "", dictionary);
	StringBuilder codeString = new StringBuilder();
	for (char letter: text.toCharArray()) {
	  codeString.append(dictionary.get(letter));
	}
	System.out.println("encoded string: " + codeString);
	System.out.print("decoded string: ");

	if (root.left == null && root.right == null)
	{
	  while (root.freq-- > 0) {
	      System.out.print(root.letter);
	  }
	}
	else {
	  int index = -1;
	  while (index < codeString.length()-1) {
	      index = decode(root, index, codeString);
	  }
	}
	System.out.println();
	System.out.println("The dictionary (letter to code): " + dictionary);
	}
	public static int decode(HuffmanNode root, int index, StringBuilder codeString)
	{
	if (root == null) {
	  return index;
	}
	if (root.left == null && root.right == null)
	{
	  System.out.print(root.letter);
	  return index;
	}
	index++;
	root = (codeString.charAt(index) == '0') ? root.left : root.right;
	index = decode(root, index, codeString);
	return index;
	}
	  public static void encode(HuffmanNode root, String str,
              Map<Character, String> dictionary)
{
if (root == null) {
  return;
}
if (root.left == null && root.right == null) {
  dictionary.put(root.letter, str.length() > 0 ? str : "1");
  System.out.println("enconding the letter "+root.letter + " to:"+ str);
}
encode(root.left, str + '0', dictionary);
encode(root.right, str + '1', dictionary);
}

public static void main(String[] args) throws IOException
{
String text = HuffmanTree.readFile("C:/Users/pcpc/Desktop/huffman.txt", StandardCharsets.US_ASCII);
convertToHuffmanTree(text);
}
}
