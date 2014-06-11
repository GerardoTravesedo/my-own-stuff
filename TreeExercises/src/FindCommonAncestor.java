

public class FindCommonAncestor {
	
	// Class used to find the common antecesor of two particular nodes
	
	
	// If both nodes are the the same branch, we continue going down
	// If each node is in a different branch, this is the common antecesor (there is no other going down)
	public static BinaryTreeElement findCommonAncestor(
		BinaryTreeElement node1, BinaryTreeElement node2, BinaryTreeElement root) {
			
		if (isInSubtree(root.getLeftObject(), node1) && isInSubtree(root.getLeftObject(), node2)) {
			return findCommonAncestor(node1, node2, root.getLeftObject());
		}
		if (isInSubtree(root.getRightObject(), node1) && isInSubtree(root.getRightObject(), node2)) {
			return findCommonAncestor(node1, node2, root.getRightObject());
		}
		
		return root;
	}
	
	
	// Method to check if a node is in a particular subtree
	private static boolean isInSubtree(BinaryTreeElement root, BinaryTreeElement node) {
		
		if (root == null) {
			return false;
		}
		
		if (node == root) {
			return true;
		}
		else if (isInSubtree(root.getLeftObject(), node) || isInSubtree(root.getRightObject(), node)) {
			return true;
		}
		else {
			return false;
		}
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
		
		BinaryTreeElement result = findCommonAncestor(level1left, level33, root);
		
		System.out.print("RESULT: " + result.getValue());
	}

}
