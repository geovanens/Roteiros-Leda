package sorting.simpleSorting;
import util.Util;

import sorting.AbstractSorting;

/**
 * The bubble sort algorithm iterates over the array multiple times, pushing big
 * elements to the right by swapping adjacent elements, until the array is
 * sorted.
 */
public class BubbleSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (leftIndex >= 0 && leftIndex <= array.length && rightIndex >= 0 && rightIndex <= array.length
				&& leftIndex <= rightIndex) {
			boolean troca = true;
			int i = leftIndex;
			while (i <= rightIndex && troca) {
				troca = false;
				for (int j = i+1; j <= rightIndex; j++) {
					if (array[i].compareTo(array[j]) == 1) {
						Util.swap(array, i, j);
						troca = true;
					}
				}
				i++;
			}
		}
	}
}
