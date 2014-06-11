

public class PrintCornersAlternateOrder {
	
	// Print nodes of extreme corners of each level but in alternate order.

	public static void printRightSublevels(BinaryTreeElement root) {
		
		if (root == null) {
			return;
		}
		
		System.out.print(root.getValue());
		
		if (root.getRightObject() != null) {
			printRightSublevels(root.getRightObject().getRightObject());
		}	
	}
	
	public static void printLeftSublevels(BinaryTreeElement root) {
		
		if (root == null) {
			return;
		}
		
		System.out.print(root.getValue());
		
		if (root.getLeftObject() != null) {
			printLeftSublevels(root.getLeftObject().getLeftObject());
		}	
	}
	
	public static void printCorners(BinaryTreeElement root) {
		
		if (root == null) {
			return;
		}
		
		System.out.print(root.getValue());
		
		printRightSublevels(root.getRightObject());
		
		if (root.getLeftObject() != null) {
			printLeftSublevels(root.getLeftObject().getLeftObject());
		}
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
		
		printCorners(root);
		
	}
	
}
