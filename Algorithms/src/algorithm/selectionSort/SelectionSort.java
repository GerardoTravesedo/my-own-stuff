package algorithm.selectionSort;


public class SelectionSort {
	
	
	public static Integer[] selectionSort(Integer[] elements) {
		
		for (int element = 0; element < elements.length; element++) {
			int minPos = element;
			
			int minValue = elements[element];
			
			// Buscamos el elemento mas pequeno de la lista entre los
			// elementos que aun no han sido ordenados. Una vez que lo 
			// encontramos lo movemos hasta la primera posicion del subarray
			// desordenado que pasa a ser parte del ordenado ahora.
			
			for (int next = element; next < elements.length; next++) {
				if (minValue > elements[next]) {
					minValue = elements[next];
					
					minPos = next;
				}
			}
			
			int aux = elements[element];
			
			elements[element] = elements[minPos];
			
			elements[minPos] = aux;
		}
		
		return elements;
	}
	
	public static void main(String[] args) {
		
		Integer[] elements = new Integer[]{5,1,8,3,7,2,4,6};
		
		elements = selectionSort(elements);
		
		for (int i = 0; i < elements.length; i++) {
			System.out.print(elements[i] + ", ");
		}	
	}

}
