

public class ArrayFromBST {
	
	// Get an ordered array from a binary search tree
	
	
	public static void buildArray(BinaryTreeElement root, Integer[] result, int start, int end) {
		
		if (start <= end) {
			if (root == null) {
				return;
			}
			
			int middle = (start + end) / 2;
			
			result[middle] = root.getValue();
			
			buildArray(root.getLeftObject(), result, start, middle - 1);
			
			buildArray(root.getRightObject(), result, middle + 1, end);
		}
	}
	
	public static void main(String[] args) {
		
		BinaryTreeElement root = new BinaryTreeElement(5);
		BinaryTreeElement level21 = new BinaryTreeElement(3);
		BinaryTreeElement level22 = new BinaryTreeElement(7);
		BinaryTreeElement level31 = new BinaryTreeElement(2);
		BinaryTreeElement level32 = new BinaryTreeElement(4);
		BinaryTreeElement level33 = new BinaryTreeElement(6);
		BinaryTreeElement level34 = new BinaryTreeElement(8);
		
		root.setLeftObject(level21);
		root.setRightObject(level22);
		
		level21.setLeftObject(level31);
		level21.setRightObject(level32);
		
		level22.setLeftObject(level33);
		level22.setRightObject(level34);
		
		Integer[] elements = new Integer[7];
		
		buildArray(root, elements, 0, elements.length - 1);
		
		for (int i = 0; i < elements.length; i++) {
			System.out.print(elements[i] + " ");
		}
		
	}

}
