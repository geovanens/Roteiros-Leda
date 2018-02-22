package adt.avltree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import adt.bst.BSTNode;

public class AVLCountAndFillImpl<T extends Comparable<T>> extends
		AVLTreeImpl<T> implements AVLCountAndFill<T> {

	private int LLcounter;
	private int LRcounter;
	private int RRcounter;
	private int RLcounter;

	public AVLCountAndFillImpl() {
		
	}

	@Override
	public int LLcount() {
		return LLcounter;
	}

	@Override
	public int LRcount() {
		return LRcounter;
	}

	@Override
	public int RRcount() {
		return RRcounter;
	}

	@Override
	public int RLcount() {
		return RLcounter;
	}
	
	@Override
	protected void rotate(BSTNode<T> node, int caso) {
		if (caso == 1) {
			spinsRight(node);
			this.LLcounter++;
		}
		else if (caso == 2) {
			spinsLeft((BSTNode<T>) node.getLeft());
			spinsRight(node);
			this.LRcounter++;
		}

		else if (caso == 3) {
			spinsLeft(node);
			this.RRcounter++;
		}
		else {
			spinsRight((BSTNode<T>) node.getRight());
			spinsLeft(node);
			this.RLcounter++;
		}
	}

	@Override
	public void fillWithoutRebalance(T[] array) {
		if (array.length < 3) {
			for (T value: array) {
				this.insert(value);
			}
		}
		else {
			ArrayList<T> newArray = new ArrayList<>(Arrays.asList(array));
			Collections.sort(newArray);
			ArrayList<Integer> arr = median_index(newArray);
			for (Integer index: arr) {
				this.insert(newArray.get(index));
			}
		}
		
	}
	
	
	@SuppressWarnings("unchecked")
	public ArrayList<Integer> median_index(ArrayList<T> array) {
		int size = array.size();
		ArrayList<Integer> arr = new ArrayList<>();
		if(size%2 == 1) {
			int median = size/2;
			arr.add(median);
			int fator = (median/2)+1;
			int nos = 2;
			int cont = 0;
			boolean primeiro = true;
			
			int index = 0;
			while (index < arr.size()) {
				int i = arr.get(index);
				
				if (i-fator >= 0 && !arr.contains(i-fator)) {
			        arr.add(i-fator);
				}
				if (i+fator < size && !arr.contains(i+fator)) {
			        arr.add(i+fator);
				}
				
				if (primeiro) {
					fator /= 2;
					cont--;
					primeiro = false;
				}
				
				cont++;
				if (cont == nos) {
					fator /= 2;
					cont = 0;
					nos *= nos;
				}
				
				index++;
			     
			}
		}
		else {
			T value = array.get(size-1);
			array.remove(size-1);
			arr = median_index(array);
			array.add(value);
			arr.add(size-1);
		}
		
		return arr;
	}
}
