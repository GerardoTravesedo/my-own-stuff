

public class TreeIsSubtreeOfAnother {
	
	// Checking if a tree is a subtree of another tree
	
	
	public static boolean isSubtree(BinaryTreeElement mainRoot, BinaryTreeElement subtreeRoot) {
		
		if(mainRoot == null) {
			return false;
		}
		
		if (subtreeRoot == null) {
			return true;
		}
		
		// If they are not equal we dont have to compare the trees
		if (mainRoot.getValue() == subtreeRoot.getValue()) {
			if (equalTrees(mainRoot, subtreeRoot)) {
				return true;
			}
		}
		
		return isSubtree(mainRoot.getLeftObject(), subtreeRoot) || isSubtree(mainRoot.getRightObject(), subtreeRoot);
	}
	
	private static boolean equalTrees(BinaryTreeElement root1, BinaryTreeElement root2) {
		
		if (root1 == null && root2 == null) {
			return true;
		}
	
		if (root1 == null || root2 == null) {
			return false;
		}
		
		if (root1.getValue() != root2.getValue()) {
			return false;
		}
		
		return equalTrees(root1.getLeftObject(), root2.getLeftObject()) &&
						equalTrees(root1.getRightObject(), root2.getRightObject());
	}
	
	public static void main(String[] args) {
	
		BinaryTreeElement root = new BinaryTreeElement(8);
		BinaryTreeElement level1right = new BinaryTreeElement(6);
		BinaryTreeElement level1left = new BinaryTreeElement(10);
		root.setLeftObject(level1left);
		root.setRightObject(level1right);
		BinaryTreeElement level21 = new BinaryTreeElement(3);
		level1left.setLeftObject(level21);
		BinaryTreeElement level23 = new BinaryTreeElement(1);
		level1right.setLeftObject(level23);
		BinaryTreeElement level33 = new BinaryTreeElement(1);
		level23.setLeftObject(level33);
		
		BinaryTreeElement secondRoot = new BinaryTreeElement(1);
		BinaryTreeElement secondLevel33 = new BinaryTreeElement(1);
		secondRoot.setLeftObject(secondLevel33);
		
		boolean result = isSubtree(root, secondRoot);
		
		System.out.print("RESULT: " + result);
	}

}
