

public class LinkedListElement {
	
	public LinkedListElement getNextNode() {
		return _nextNode;
	}
	
	public void setNextNode(LinkedListElement nextNode) {
		this._nextNode = nextNode;
	}
	
	public int getValue() {
		return _value;
	}
	
	public void setValue(int value) {
		this._value = value;
	}
	
	private LinkedListElement _nextNode;
	private int _value;

}
