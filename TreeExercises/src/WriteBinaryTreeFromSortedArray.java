

public class WriteBinaryTreeFromSortedArray {
	
	
	// Getting a BINARY SEARCH TREE from a sorted array
	
	public static BinaryTreeElement getBinaryTree(Integer[] sortedArray, int start, int end) {
		
		if (start > end) {
			return null;
		}
		
		int middle = (start + end) / 2;
		
		BinaryTreeElement node = new BinaryTreeElement();
		
		node.setLeftObject(getBinaryTree(sortedArray, start, middle - 1));
		
		node.setRightObject(getBinaryTree(sortedArray, middle + 1, end));
		
		node.setValue(sortedArray[middle]);
		
		return node;	
	}
	
	public static void main(String[] args) {
	
		Integer[] values = new Integer[]{1,2,3,4,5,6,7,8};
		
		BinaryTreeElement root = getBinaryTree(values, 0, 7);
		
		System.out.print("TREE: " + root);
	}

}
