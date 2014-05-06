package algorithm.mergeSort;

// El orden de merge sort es O(nlogn)
// Vamos dividiendo la lista por dos, por lo que tenemos log(base2)n niveles
// Por cada nivel realizamos n operaciones (una por cada elemento)
// T(n) = 2T(n/2) + cn

public class MergeSort {
	
	/**
	 * Este metodo se encarga de mezclar y ordenar los elementos de dos subconjuntos 
	 * Por ejemplo: si tenemos cuantro elementos, hay dos subconjuntos de dos elementos
	 * @param elements: La lista global con todos los elementos
	 * @param first: El primer elemento del conjunto a ordenar
	 * @param middle: El elemento central que separa los dos subconjuntos dentro del conjunto a ordenar
	 * @param last: Ultimo elemento del conjunto a ordenar
	 * Nota1: Se basa en el principio de las pilas de cartas: los dos subconjuntos que llegan estan ya ordenados
	 * por lo que el primer elemento en cada uno de ellos sera el mas pequeno de cada subconjunto. Ya solo
	 * tenemos que comparar los primeros elementos de cada subconjunto (apuntados por leftPosition y rightPosition)
	 * y extraer el mas pequeno (moviendo el puntero de ese subconjunto a la siguiente posicion).
	 * Nota2: El nivel mas bajo se compone de un conjunto de dos elementos con dos subconjuntos de un 
	 * elemento (obviamente ya ordenados, ya que solo hay un elemento)
	 */
	public static void merge(Integer[] elements, int first, int middle, int last) {
		
		// Array axiliar con el que trabajar: donde iremos metiendo los elementos 
		// ordenador temporalmente. No podemos trabajar directamente con el array
		// verdadero ya que podriamos perder elementos y dejar duplicados (imaginemos
		// un caso en el que el menor elemento es el primero del subconjunto de la derecha,
		// y lo ponemos al principio del conjunto, perdiendo el primer elemento del
		// subconjunto de la izquierda y generando un duplicado.
		Integer[] aux = new Integer[last - first + 1];
		
		// Puntero a la posicion actual del subconjunto de la izquierda
		int leftPosition = first;
		// Puntero a la posicion actual del subconjunto de la derecha
		int rightPosition = middle + 1;
		
		for (int all = 0; all < aux.length; all++) {
			
			// Si se acabo la lista izquierda, metemos elemento de la derecha
			// Solo quedan elementos en la lista de la derecha
			if (leftPosition > middle) {
				aux[all] = elements[rightPosition];
				
				rightPosition++;
			}
			// Si se acabo la lista derecha, metemos elemento de la izquierda
			// Solo quedan elementos en la lista de la izquierda
			else if (rightPosition > last) {
				aux[all] = elements[leftPosition];
				
				leftPosition++;
			}
			// Si elemento de la lista izquierda es mas pequenio que el de la derecha, 
			// es el menor de ambos subconjuntos
			else if (elements[leftPosition] < elements[rightPosition]) {
				aux[all] = elements[leftPosition];
				
				leftPosition++;
			}
			// Si elemento de la lista derecha es mas pequenio que el de la izquierda, 
			// es el menor de ambos subconjuntos
			else {
				aux[all] = elements[rightPosition];
				
				rightPosition++;
			}
		}
		
		int globalPosition = first;
		
		// Hemos tenido que trabajar con un array auxiliar, por lo que ahora movemos el resultado
		// final de ese array auxiliar al array verdadero, dejando este conjunto de elementos ordenado
		for (int i = 0; i < aux.length; i++) {
			elements[globalPosition] = aux[i];
			
			globalPosition++;
		}
		
	}
	
	
	/**
	 * Este metodo se encarga de ordenar un array a traves de divide and conquer
	 * @param elements: Array con todos los elementos a ordenar
	 * @param first: primero elemento de la subiteracion a ordenar
	 * @param last: ultimo elemento de la subiteracion a ordenar
	 */
	public static void mergeSort(Integer[] elements, int first, int last) {
		
		//  Cuando tenemos arrays de un solo elemento no hacemos nada (first == last)
		if (first < last) {
			// Sacamos posicion central del array a ordenar
			int middle = (last + first) / 2;
			
			// Una lista tendra la mitad izquierda de los elementos
			mergeSort(elements, first, middle);
			
			// La otra lista la mitad derecha de los elementos
			mergeSort(elements, middle + 1, last);
			
			// Ordenamos en un mismo conjunto los dos sunconjuntos de los 
			// pasos anteriores (ya vienen ordenador gracias a las llamadas 
			// recursivas)
			merge(elements, first, middle, last);
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
