package algorithm.quicksort;


public class Quicksort {

	// Calculate the pivot element as the median of three elements: first, middle and last
	private static int medianOfThree(Integer[] list, int first, int last) {
		
		int middle = (first + last) / 2;
		
		if (list[first] > list[middle]) {
			if (list[middle] > list[last]) {
				return middle;
			}
			else if (list[first] > list[last]) {
				return last;
			}
			else {
				return first;
			}
		}
		else {
			if (list[first] > list[last]) {
				return first;
			}
			else if (list[middle] > list[last]) {
				return last;
			}
			else {
				return middle;
			}
		}
	}
	
	// Method used to swap two element inside an array
	private static void swap(Integer[] list, int pos1, int pos2) {
		
		int aux = list[pos1];
		
		list[pos1] = list[pos2];
		
		list[pos2] = aux;
	}
	
	public static int partition(Integer[] list, int start, int end) {
		
		// Getting pivot
		int pivot = medianOfThree(list, start, end);
		
		int pivotFinalLocation = start + 1;
		
		// Switching pivot with first element
		swap(list, start, pivot);
		
		for(int i = start + 1; i <= end; i++) {
			if (list[i] < list[start]) {
				swap(list, pivotFinalLocation, i);
				
				pivotFinalLocation++;
			}
		}
		
		swap(list, pivotFinalLocation - 1, start);
		
		return pivotFinalLocation - 1;
	}
	
	public static void quicksort(Integer[] list, int start, int end) {
		
		if (start < end) {
			int pivotNewIndex = partition(list, start, end);
			
			quicksort(list, start, pivotNewIndex - 1);
			
			quicksort(list, pivotNewIndex + 1, end);
		}
	}
	
	public static void main(String[] args) {
		
		// Array to sort
		Integer[] elements = {7,3,4,8,1,6,22,2};
						
		quicksort(elements, 0, elements.length - 1);
						
		for (int i = 0; i < elements.length; i++) {
			System.out.print(elements[i] + " ");
		}
		
	}
}
