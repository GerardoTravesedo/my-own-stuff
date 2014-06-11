import java.util.LinkedList;
import java.util.Queue;



public class LevelWithMaximunNumberOfNodes {
	
	// Return level with max number of nodes
	
	
	public static int maxNodesLevel(BinaryTreeElement root) {
		
		Queue<BinaryTreeElement> queue = new LinkedList<BinaryTreeElement>();
		
		Queue<BinaryTreeElement> nextQueue = new LinkedList<BinaryTreeElement>();
		
		int level = 0;
		
		int maxNodeLevel = 0;
		
		queue.add(root);
		
		int elementsInLevel = 0;
		
		int maxElements = 0;
		
		while(!queue.isEmpty()) {
			BinaryTreeElement current = queue.poll();
			
			elementsInLevel++;
			
			if (current.getLeftObject() != null) {
				nextQueue.add(current.getLeftObject());
			}
			
			if (current.getRightObject() != null) {
				nextQueue.add(current.getRightObject());
			}
			
			if (queue.isEmpty()) {
				if (maxElements < elementsInLevel) {
					maxNodeLevel = level;
					
					maxElements = elementsInLevel;
				}
				
				level++;
				
				elementsInLevel = 0;
				
				while(!nextQueue.isEmpty()) {
					BinaryTreeElement movingNode = nextQueue.poll();
					
					queue.add(movingNode);
				}
				
				nextQueue = new LinkedList<BinaryTreeElement>();
			}	
		}
		
		return maxNodeLevel;
	}
	
	public static void main(String[] args) {
	
		BinaryTreeElement root = new BinaryTreeElement(5);
		BinaryTreeElement level21 = new BinaryTreeElement(1);
		BinaryTreeElement level22 = new BinaryTreeElement(1);
		BinaryTreeElement level31 = new BinaryTreeElement(3);
		BinaryTreeElement level32 = new BinaryTreeElement(8);
		BinaryTreeElement level33 = new BinaryTreeElement(8);
		BinaryTreeElement level34 = new BinaryTreeElement(3);
		BinaryTreeElement level41 = new BinaryTreeElement(7);
		BinaryTreeElement level42 = new BinaryTreeElement(7);
		BinaryTreeElement level43 = new BinaryTreeElement(7);
		BinaryTreeElement level44 = new BinaryTreeElement(7);
		BinaryTreeElement level45 = new BinaryTreeElement(7);
		
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
		level33.setLeftObject(level45);
		
		int level = maxNodesLevel(root);
		
		System.out.print("RESULT: " + level);
	}

}
