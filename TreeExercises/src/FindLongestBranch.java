

/**
 * This class helps us to find the number of element of the longest branch in a tree
 * 
 * @author Gerardo Travesedo
 */

public class FindLongestBranch {
	
	public static int findLongestPath(BinaryTreeElement root){
		
		if (root == null){
			return 0;
		}
		
		int leftSide = findLongestPath(root.getLeftObject()) + 1;
		
		int rightSide = findLongestPath(root.getRightObject()) + 1;
		
		if (leftSide > rightSide) {
			return leftSide;
		}
		else {
			return rightSide;
		}
		
	}
	
	
	public static void main(String[] args) {
		
		BinaryTreeElement root = new BinaryTreeElement(8);
		BinaryTreeElement level1right = new BinaryTreeElement(6);
		BinaryTreeElement level1left = new BinaryTreeElement(10);
		root.setLeftObject(level1left);
		root.setRightObject(level1right);
		BinaryTreeElement level21 = new BinaryTreeElement(3);
		level1right.setRightObject(level21);
		BinaryTreeElement level23 = new BinaryTreeElement(1);
		level1left.setRightObject(level23);
		BinaryTreeElement level33 = new BinaryTreeElement(1);
		level23.setRightObject(level33);
		
		
		int longestPath = findLongestPath(root);
		System.out.print("\n\nTHE LONGEST PATH IS: " + longestPath);
		
	}
}
