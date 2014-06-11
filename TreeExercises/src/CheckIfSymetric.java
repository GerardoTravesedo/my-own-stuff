

public class CheckIfSymetric {
	
	// Checking if a tree is simetris. Right and left branches should be equal
	
	public static boolean isSymetric(BinaryTreeElement root) {
		
		return compareSubtrees(root.getLeftObject(), root.getRightObject());
	}
	
	public static boolean compareSubtrees(BinaryTreeElement root1, BinaryTreeElement root2) {
		
		if (root1 == null && root2 == null) {
			return true;
		}
		
		if (root1 == null || root2 == null) {
			return false;
		}
		
		if (root1.getValue() != root2.getValue()) {
			return false;
		}
		
		return compareSubtrees(root1.getLeftObject(), root2.getRightObject()) &&
						compareSubtrees(root1.getRightObject(), root2.getLeftObject());
		
	}
	
	public static void main(String[] args) {
		
		BinaryTreeElement root = new BinaryTreeElement(5);
		BinaryTreeElement level21 = new BinaryTreeElement(1);
		BinaryTreeElement level22 = new BinaryTreeElement(1);
		BinaryTreeElement level31 = new BinaryTreeElement(3);
		BinaryTreeElement level32 = new BinaryTreeElement(8);
		BinaryTreeElement level33 = new BinaryTreeElement(8);
		BinaryTreeElement level34 = new BinaryTreeElement(3);
		
		root.setLeftObject(level21);
		root.setRightObject(level22);
		
		level21.setLeftObject(level31);
		level21.setRightObject(level32);
		
		level22.setLeftObject(level33);
		level22.setRightObject(level34);
		
		boolean result = isSymetric(root);
		
		System.out.print("RESULT: " + result);
		
		
	}

}
