/**
 * This class helps us to calculate the sum of the elements in the odd levels and subtract the even ones
 * 
 * @author Gerardo Travesedo
 */

public class SumOddSubEven {
	
	
	public static int calculate (BinaryTreeElement root) {
		
		if (root == null) {
			return 0;
		}
		
		return root.value - calculate(root.getRightObject()) - calculate(root.getLeftObject());
	}
	
	
	public static void main(String[] args) {
		
		BinaryTreeElement root = new BinaryTreeElement(8);
		BinaryTreeElement element11 = new BinaryTreeElement(8);
		BinaryTreeElement element12 = new BinaryTreeElement(9);
		BinaryTreeElement element121 = new BinaryTreeElement(10);
		BinaryTreeElement element122 = new BinaryTreeElement(12);
		BinaryTreeElement element1222 = new BinaryTreeElement(14);
		
		root.setRightObject(element11);
		root.setLeftObject(element12);
		
		element12.setRightObject(element121);
		element12.setLeftObject(element122);
		
		element122.setLeftObject(element1222);
		
		System.out.print("THE TOTAL SUM IS: " + calculate(root));
		
	}

}
