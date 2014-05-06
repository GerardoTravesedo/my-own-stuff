package algorithm.mergeSort;


public class Test {
	
	
	public static void sort(Integer[] list, int start, int middle, int end) {
		
		Integer[] aux = new Integer[end - start + 1];
		
		int leftPos = start;
		
		int rightPos = middle + 1;
		
		for (int i = 0; i < aux.length; i++) {
			if (leftPos > middle) {
				aux[i] = list[rightPos];
				
				rightPos++;
			}
			else if (rightPos > end) {
				aux[i] = list[leftPos];
				
				leftPos++;
			}
			else if (list[leftPos] < list[rightPos]) {
				aux[i] = list[leftPos];
				
				leftPos++;
			}
			else {
				aux[i] = list[rightPos];
				
				rightPos++;
			}
		}
		
		int globalPos = start;
		
		for(int i = 0; i < aux.length; i++) {
			list[globalPos] = aux[i];
			
			globalPos++;
		}
	}
	
	public static void mergeSort(Integer[] list, int start, int end) {
		
		if(start < end) {
			int middle = (start + end) / 2;
			
			mergeSort(list, start, middle);
			
			mergeSort(list, middle + 1, end);
			
			sort(list, start, middle, end);
		}
	}
	
	public static void main(String[] args) {
		// Array a ordenar
		Integer[] elements = {7,3,4,8,1,6,22,2};
				
		mergeSort(elements, 0, elements.length - 1);
				
		for (int i = 0; i < elements.length; i++) {
			System.out.print(elements[i] + " ");
		}	
	}

}
