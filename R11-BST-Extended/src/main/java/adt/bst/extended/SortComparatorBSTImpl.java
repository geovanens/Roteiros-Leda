package adt.bst.extended;

import java.util.Comparator;

import adt.bst.BSTImpl;

public class SortComparatorBSTImpl<T extends Comparable<T>> extends BSTImpl<T> {
	
	private Comparator<T> comparator;

}
