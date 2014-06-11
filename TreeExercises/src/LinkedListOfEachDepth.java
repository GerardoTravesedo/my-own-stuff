import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// Create a linked list per level with the elements of that level. 4 level = 4 linked lists

public class LinkedListOfEachDepth {
	
	public static int numberOfLevels(BinaryTreeElement root) {
		
		if (root == null) {
			return 0;
		}
		
		int leftLevels = 1 + numberOfLevels(root.getLeftObject());

		int rightLevels = 1 + numberOfLevels(root.getRightObject());
		
		return Math.max(leftLevels, rightLevels);
	}
	
	
	// BFS but with some changes
	public static List<LinkedList<BinaryTreeElement>> createLinkedLists(
		BinaryTreeElement root) {
		
		if (root == null) {
			return null;
		}
		
		int numberOfLevels = numberOfLevels(root);
		
		List<LinkedList<BinaryTreeElement>> list = new ArrayList<LinkedList<BinaryTreeElement>>(numberOfLevels);	
		
		LinkedList<BinaryTreeElement> lkList = new LinkedList<BinaryTreeElement>();
		
		lkList.add(root);
		
		list.add(0, lkList);
		
		for (int level = 1; level < numberOfLevels; level++) {
			
			lkList = new LinkedList<BinaryTreeElement>();
			
			for (int node = 0; node < list.get(level - 1).size(); node++) {
				BinaryTreeElement element = list.get(level - 1).get(node);
				
				
				if (element.getLeftObject() != null) {
					lkList.add(element.getLeftObject());
				}
				
				if(element.getRightObject() != null) {
					lkList.add(element.getRightObject());
				}
			}
			
			list.add(level, lkList);
		}
		
		return list;
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
		
		createLinkedLists(root);
	}

}
