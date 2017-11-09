package sorting.divideAndConquer.threeWayQuicksort;

import sorting.AbstractSorting;

public class ThreeWayQuickSort<T extends Comparable<T>> extends
		AbstractSorting<T> {

	/**
	 * No algoritmo de quicksort, selecionamos um elemento como pivot,
	 * particionamos o array colocando os menores a esquerda do pivot 
	 * e os maiores a direita do pivot, e depois aplicamos a mesma estrategia 
	 * recursivamente na particao a esquerda do pivot e na particao a direita do pivot. 
	 * 
	 * Considerando um array com muitoe elementos repetidos, a estrategia do quicksort 
	 * pode ser otimizada para lidar de forma mais eficiente com isso. Essa melhoria 
	 * eh conhecida como quicksort tree way e consiste da seguinte ideia:
	 * - selecione o pivot e particione o array de forma que
	 *   * arr[l..i] contem elementos menores que o pivot
	 *   * arr[i+1..j-1] contem elementos iguais ao pivot.
	 *   * arr[j..r] contem elementos maiores do que o pivot. 
	 *   
	 * Obviamente, ao final do particionamento, existe necessidade apenas de ordenar
	 * as particoes contendo elementos menores e maiores do que o pivot. Isso eh feito
	 * recursivamente. 
	 **/
	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (array != null && leftIndex < rightIndex && leftIndex >= 0 && rightIndex < array.length && array.length != 0) {
			int iPivot = partition(array, leftIndex, rightIndex);
			int occu = occurrences(array, leftIndex, rightIndex);
			sort(array, leftIndex, iPivot-occu);
			sort(array, iPivot+1, rightIndex);
		}
	}
	
	private int partition(T[] array, int leftIndex, int rightIndex) {
		T pivot = array[leftIndex];
        int i = leftIndex;
        
        for (int j = leftIndex+1; j <= rightIndex; j++) {
          if (array[j].compareTo(pivot) < 0) {
            i += 1;
            T aux = array[i];
            array[i] = array[j];
            array[j] = aux;
          }
          if (array[j].compareTo(pivot) == 0) {
              T aux = array[i+1];
              array[i+1] = array[j];
              array[j] = aux;
            }
        }
        T aux = array[i];
        array[i] = array[leftIndex];
        array[leftIndex] = aux;
		return i;
	}
	
	private int occurrences(T[] array, int leftIndex, int rightIndex) {
		T pivot = array[leftIndex];
		int ocorrencias = 1;
		for (int i = leftIndex+1; i <= rightIndex; i++) {
			if (array[i].compareTo(pivot) == 0) {
				ocorrencias++;
			}
		}
		return ocorrencias;
		
	}

}
