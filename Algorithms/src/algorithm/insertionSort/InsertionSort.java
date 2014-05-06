package algorithm.insertionSort;


public class InsertionSort {
	
	
	public static Integer[] insertionSort(Integer[] elements) {
	
		for (int element = 1; element < elements.length; element++) {
			int tmpPosition = element;
			
			// Con un bucle nos recorremos todos los elementos en el orden normal
			// Con el bucle interior nos recorremos los elementos ya ordenados
			// es decir, los elementos a la izquierda del mismo.
			// Cuando un elemento de la izquierda es mas pequenio que el actual, 
			// ya tenemos al actual ordenado, ya que todos a la izquierda se 
			// encuentran ordenados. Si un elemento de la izquierda es mas grande,
			// tenemos que interbamciarlos y seguir comparando hasta poner al actual
			// en su sitio.
			
			for(int sorted = element - 1; sorted >= 0; sorted--) {
				if (elements[tmpPosition] < elements[sorted]) {
					Integer temp = elements[sorted];
					
					elements[sorted] = elements[tmpPosition];
					
					elements[tmpPosition] = temp;
					
					tmpPosition--;
					
				}
				else {
					break;
				}
			}
		}
		
		return elements;
		
	}
	
	public static void main(String[] args) {
		
		Integer[] elements = new Integer[]{5,1,8,3,7,2,4,6};
		
		elements = insertionSort(elements);
		
		for (int i = 0; i < elements.length; i++) {
			System.out.print(elements[i] + ", ");
		}	
	}

}
