

public class BinaryTreeElement {
	
	public BinaryTreeElement() {
	}
	
	public BinaryTreeElement(int value) {
		this.value = value;
	}

	public BinaryTreeElement getLeftObject() {
	
		return leftObject;
	}
	
	public void setLeftObject(BinaryTreeElement leftObject) {
	
		this.leftObject = leftObject;
	}
	
	public BinaryTreeElement getRightObject() {
	
		return rightObject;
	}
	
	public void setRightObject(BinaryTreeElement rightObject) {
	
		this.rightObject = rightObject;
	}
	
	public int getValue() {
	
		return value;
	}
	
	public void setValue(int value) {
	
		this.value = value;
	}
	
	private BinaryTreeElement leftObject;
	private BinaryTreeElement rightObject;
	int value;

}
