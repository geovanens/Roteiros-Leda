package sorting.variationsOfBubblesort;

import sorting.AbstractSorting;
import util.Util;

/**
 * The combsort algoritm.
 */
public class CombSort<T extends Comparable<T>> extends AbstractSorting<T> {
	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (array != null && leftIndex < rightIndex && leftIndex >= 0 && rightIndex < array.length && array.length != 0) {
			int tamanho = rightIndex - leftIndex;
			
			int gap = (int) (tamanho / 1.25);
			 
			while (gap >= 1) {
				int i = leftIndex;
				while (i+gap <= tamanho) {
					if (array[i+gap].compareTo(array[i]) < 0) {
						Util.swap(array, i, gap+i);
					}
					i++;
				}
				gap = (int) (gap / 1.25);
				
					
			}
		}
		
	}
}
