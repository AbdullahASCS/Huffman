
public class HuffmanNode {
	Character letter;
    Integer freq;
    HuffmanNode left = null, right = null;
 
    HuffmanNode(Character letter, Integer freq)
    {
        this.letter =letter;
        this.freq = freq;
    }
 
    public HuffmanNode(Character letter, Integer freq, HuffmanNode left, HuffmanNode right)
    {
        this.letter =letter;
        this.freq = freq;
        this.left = left;
        this.right = right;
    }
}
