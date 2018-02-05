package adt.bst.extended;

import adt.bst.BST;

public interface SortComparatorBST<T extends Comparable<T>> extends BST<T> {
	
	public T[] sort(T[] array);
	
	/**
	 * DIR, RAIZ, ESQ
	 * @return
	 */
	public T[] reverseOrde();

}
