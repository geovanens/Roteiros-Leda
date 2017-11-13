package sorting.variationsOfBubblesort;

import sorting.AbstractSorting;
import util.Util;

/**
 * The implementation of the algorithm must be in-place!
 */
public class GnomeSort<T extends Comparable<T>> extends AbstractSorting<T> {
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (array != null && leftIndex < rightIndex && leftIndex >= 0 && rightIndex < array.length && array.length > 1) {
			int i = leftIndex;
			while (i < rightIndex) {
				if (array[i].compareTo(array[i+1]) > 0) {
					Util.swap(array, i, i+1);
					while (i > leftIndex && array[i].compareTo(array[i-1]) < 0) {
						Util.swap(array, i, i-1);
						i--;
					}
					
				}
				i++;
				
			}
		}
	}
}
