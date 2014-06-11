

public class CheckIsBalanced {
	
	// A balance tree is a tree such that no two leaf nodes differ in distance
	// from the root by more than one

	public static int maxLength(BinaryTreeElement root) {
	
			if (root == null) {
				return 0;
			}
		
			int leftLength = maxLength(root.getLeftObject());
			
			int rightLength = maxLength(root.getRightObject());
			
			return 1 + Math.max(leftLength, rightLength);
	}
	
	public static int minLength(BinaryTreeElement root) {
		
		if (root == null) {
			return 0;
		}
		
		int leftLength = minLength(root.getLeftObject());
		
		int rightLength = minLength(root.getRightObject());
		
		return 1 + Math.min(leftLength, rightLength);
	}
	
	
	// In order to know if it is balanced we just have to compare
	// the max and min length. Is the difference is bigger than 2
	// it is not balanced
	public static boolean isBalanced(BinaryTreeElement root) {
		
		int min = minLength(root);
		
		int max = maxLength(root);
		
		if ((max - min) > 1) {
			return false;
		}
		else {
			return true;
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
		
		BinaryTreeElement level22 = new BinaryTreeElement(4);
		level1left.setLeftObject(level22);
		
		System.out.print("Is it Balanced?: " + isBalanced(root));
	}

}
