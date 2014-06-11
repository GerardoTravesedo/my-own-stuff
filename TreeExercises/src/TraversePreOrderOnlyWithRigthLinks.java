
// We want to traverse a tree in pre-order using only right links. Modify the tree so this can be achieved.
// Pre-order traversal: root - left - right

public class TraversePreOrderOnlyWithRigthLinks {

	
	public static boolean hasRightChild(BinaryTreeElement root) {
		
		if (root.getRightObject() == null) {
			return false;
		}
		else {
			return true;
		}
	}
	
	public static boolean hasLeftChild(BinaryTreeElement root) {
		
		if (root.getLeftObject() == null) {
			return false;
		}
		else {
			return true;
		}
	}
		
	// Traversing the tree in reverse pre-order and connect each right link to the previous element
	public static BinaryTreeElement modifyTree(BinaryTreeElement root, BinaryTreeElement previous) {
				
		if (hasRightChild(root)) {
			previous = modifyTree(root.getRightObject(), previous);
		}
		
		if (hasLeftChild(root)) {
			previous = modifyTree(root.getLeftObject(), previous);
		}
		
		root.setRightObject(previous);
		
		return root;
	}
	
	public static void main(String[] args) {
		
		BinaryTreeElement root = new BinaryTreeElement(8);
		BinaryTreeElement level21 = new BinaryTreeElement(4);
		BinaryTreeElement level22 = new BinaryTreeElement(13);
		BinaryTreeElement level31 = new BinaryTreeElement(2);
		BinaryTreeElement level32 = new BinaryTreeElement(6);
		BinaryTreeElement level33 = new BinaryTreeElement(10);
		BinaryTreeElement level34 = new BinaryTreeElement(15);
		BinaryTreeElement level41 = new BinaryTreeElement(1);
		BinaryTreeElement level42 = new BinaryTreeElement(3);
		BinaryTreeElement level43 = new BinaryTreeElement(5);
		BinaryTreeElement level44 = new BinaryTreeElement(7);
		
		root.setLeftObject(level21);
		root.setRightObject(level22);
		
		level21.setLeftObject(level31);
		level21.setRightObject(level32);
		
		level22.setLeftObject(level33);
		level22.setRightObject(level34);
		
		level31.setLeftObject(level41);
		level31.setRightObject(level42);
		level32.setLeftObject(level43);
		level32.setRightObject(level44);
		
		BinaryTreeElement currentElement = modifyTree(root, null);
		
		while (!(currentElement == null)) {
			System.out.print(currentElement.getValue() + "\n");
			
			currentElement = currentElement.getRightObject();
		}
	}
}
