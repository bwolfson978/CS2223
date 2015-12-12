package USERID.hw1;

public class BinaryStringSearch {
	
	final int K;
	final String tokens;
	
	/**
	 * Construct search structure.
	 * @param initial
	 * @param K
	 */
	public BinaryStringSearch(String initial, int K) {
		this.K = K;
		this.tokens = initial;
	}
	
	
	/**
	 * Modify this code to return the rank of the token within the tokens string
	 * if it exists, otherwise return -1.
	 * 
	 * note that rank is to be a number from 0 up to and including N-1 where N
	 * is the number of tokens in the string.
	 * 
	 * Take inspiration from the cod on page 47 of the text.
	 * @param token   a string of exactly K characters
	 * @return    -1 if token does not exist in tokens string, otherwise rank of that
	 *            token in our tokens string
	 */
	public int rank (String token) {
		int comparisons = 0;
		int lo = 0;
		int hi = this.tokens.length() -1;
		while(lo <= hi){
			int mid = lo + (hi - lo)/2;
			if(this.tokens.charAt(mid) == ' '){
				mid++;
			}
			String part2 = this.tokens.substring(mid,hi+1); //second half of entire string
			int end = part2.indexOf(" ");
			if(end == -1){
				end = part2.length();
			}
			String secondHalf = part2.substring(0, end);
			int endIndex = mid + secondHalf.length() -1; //end index of current token
			int startIndex = endIndex - this.K; //start index of current token
			String key = this.tokens.substring(startIndex + 1, endIndex + 1); //current token
			if(key.equals(token)){
				comparisons++;
				System.out.println("number of comparisons "+comparisons);
				return (startIndex + 1)/(this.K+1) + 1; //if they're equal return the rank
			}
			else if (key.compareTo(token)<0){ //if current token comes before target, search right half
				comparisons++;
				lo = endIndex + 2;
			}
			else if (key.compareTo(token)>0){ //if current token comes after target, search left half
				comparisons++;
				hi = startIndex - 1;
			}
		}
		return -1;
	}
	
	// sample driver for your code.
	public static void main(String[] args) {
		String target = "ant apt awl box boy car cat dog man nap pot try you";
		String target2 = "add cat god mat";
		String target3 = "barry harry";
		String target4 = "b";
		int k1 = 3;
		int k2 = 5;
		int k3 = 1;
		int rank1 = new BinaryStringSearch(target, k1).rank("box");
		System.out.println(rank1 + "th token in the string");
		int rank2 = new BinaryStringSearch(target2, k1).rank("add");
		System.out.println(rank2 + "th token in the string");
		int rank3 = new BinaryStringSearch(target3, k2).rank("barry");
		System.out.println(rank3 + "th token in the string");
		//edge case with only one character
		int rank4 = new BinaryStringSearch(target4, k3).rank("b");
		System.out.println(rank4 + "th token in the string");
		//attempt to search for a token not in the string
		int rank5 = new BinaryStringSearch(target3, k2).rank("a");
		System.out.println(rank5 + "th token in the string");

	}
}
