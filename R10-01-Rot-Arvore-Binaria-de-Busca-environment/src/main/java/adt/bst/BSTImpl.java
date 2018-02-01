package adt.bst;

import java.util.ArrayList;

import adt.bt.BTNode;

public class BSTImpl<T extends Comparable<T>> implements BST<T> {

	protected BSTNode<T> root;

	public BSTImpl() {
		root = new BSTNode<T>();
	}

	public BSTNode<T> getRoot() {
		return this.root;
	}

	@Override
	public boolean isEmpty() {
		return root.isEmpty();
	}

	@Override
	public int height() {
		return findHeight(this.root);
	}
	
	private int findHeight(BSTNode<T> aNode) {
	    if (aNode.isEmpty()) {
	        return -1;
	    }

	    int lefth = findHeight((BSTNode<T>) aNode.getLeft());
	    int righth = findHeight((BSTNode<T>) aNode.getRight());

	    if (lefth > righth) {
	        return lefth + 1;
	    } else {
	        return righth + 1;
	    }
	}

	@Override
	public BSTNode<T> search(T element) {
		return search(element, this.root);
	}
	
	private BSTNode<T> search(T element, BSTNode<T> node) {
		if (node.isEmpty() || node.getData().equals(element)) {
			return node;
		}
		if (element.compareTo(node.getData()) > 0) {
			return search(element, (BSTNode<T>)node.getRight());
		}
		else {
			return search(element, (BSTNode<T>)node.getLeft());
		}
		
	} 

	@Override
	public void insert(T element) {
		if(isEmpty()) {
			this.root.setData(element);
			this.root.setRight(new BSTNode<>());
			this.root.setLeft(new BSTNode<>());
		}
		else {
			insert(this.root, this.root, element);
		}
	}
	
	private void insert(BSTNode<T> node, BSTNode<T> parent, T element) {
		
		if(node.isEmpty()) {	
			node.setData(element);
			node.setRight(new BSTNode<>());
			node.setLeft(new BSTNode<>());
			node.setParent(parent);
		}
		else {
			if (element.compareTo(node.getData()) > 0) {
				insert((BSTNode<T>)node.getRight(), node, element);
			}
			else {
				insert((BSTNode<T>)node.getLeft(), node, element);
			}
		}
		
	}
 
	@Override
	public BSTNode<T> maximum() {
		BSTNode<T> aux = this.root;
		if (isEmpty()) {
			aux = null;
		}
		else {
			while (!aux.getRight().isEmpty()) {
				aux = (BSTNode<T>) aux.getRight();
			}
		}
		
		return aux;
	}

	private BSTNode<T> treeMaximum(BSTNode<T> node) {
		while (!node.getLeft().isEmpty()) {
			node = (BSTNode<T>) node.getLeft();
		}
		return node;
	} 
	
	@Override
	public BSTNode<T> minimum() {
		BSTNode<T> aux = this.root;
		if (isEmpty()) {
			aux = null;
		}
		else {
			while (!aux.getLeft().isEmpty()) {
				aux = (BSTNode<T>) aux.getLeft();
			}
		}
		
		return aux;
	}
	
	private BSTNode<T> treeMinimum(BSTNode<T> node) {
		BSTNode<T> right = (BSTNode<T>) node.getRight();
		while (!right.getLeft().isEmpty()) {
			right = (BSTNode<T>) right.getLeft();
		}
		return right;
	} 

	@Override
	public BSTNode<T> sucessor(T element) {
		BSTNode<T> node = search(element);
		BSTNode<T> aux = null;
		if (!node.isEmpty()) {
			if (!node.getRight().isEmpty()) {
				return treeMinimum(node);
			}
			aux = (BSTNode<T>) node.getParent();
			while (aux != null && !aux.isEmpty() && node.equals(aux.getRight())) {
				node = aux;
				aux = (BSTNode<T>) aux.getParent();
			}
		}
		
		return aux;
	}

	@Override
	public BSTNode<T> predecessor(T element) {
		BSTNode<T> node = search(element);
		BSTNode<T> aux = null;
		if (!node.isEmpty()) {
			if (!node.getLeft().isEmpty()) {
				return treeMaximum(node);
			}
			aux = (BSTNode<T>) node.getParent();
			while (aux != null && !aux.isEmpty() && node.equals(aux.getLeft())) {
				node = aux;
				aux = (BSTNode<T>) aux.getParent();
			}
		}
		return aux;
	}

	@Override
	public void remove(T element) {
		BSTNode<T> node = search(element);
		if (!node.isEmpty()) {
			if (node.isLeaf()) {
				node.setData(null);
			}
			else if (degree(node) == 1) {
				if (!node.equals(this.root)) {
					if (isLeftChild(node)) {
						if (!node.getLeft().isEmpty()) {
							node.getParent().setLeft(node.getLeft());
						}
						else {
							node.getParent().setLeft(node.getRight());
						}
					}
					else {
						if (!node.getLeft().isEmpty()) {
							node.getParent().setRight(node.getLeft());
						}
						else {
							node.getParent().setRight(node.getRight());
						}
					}
				}
				else {
					this.root = getChildRoot();
				}
			}
			else {
				BSTNode<T> sucessor = sucessor(element);
				T data = sucessor.getData();
				remove(sucessor.getData());
				node.setData(data);
			}
		}
		
	}

	private boolean isLeftChild(BSTNode<T> node) {
		if (node.equals(this.root) ) {
			return false;
		}
		else {
			BSTNode<T> parent = (BSTNode<T>) node.getParent();
			return parent.getLeft().equals(node);
		}
	}

	private BSTNode<T> getChildRoot() {
		BTNode<T> saida;
		if (!this.root.getLeft().isEmpty()) {
			saida = this.root.getLeft();
		}
		else {
			saida = this.root.getRight();
		}
		
		return (BSTNode<T>) saida;
	}

	private int degree(BSTNode<T> node) {
		int degree = 0;
		if (!node.getLeft().isEmpty()) {
			degree++;
		}
		if (!node.getRight().isEmpty()) {
			degree++;
		}
		
		return degree;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] preOrder() {
		ArrayList<T> array = preOrder(new ArrayList<>(), this.root);
		return array.toArray((T[]) new Comparable[size()]);
	}
	
	private ArrayList<T> preOrder(ArrayList<T> array, BSTNode<T> node) {
		if(!node.isEmpty()){
			visit(array, node);
			preOrder(array, (BSTNode<T>) node.getLeft());
			preOrder(array, (BSTNode<T>) node.getRight());
		}
		return array;
	}

	private void visit(ArrayList<T> array, BSTNode<T> node) {
		array.add(node.getData());
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] order() {
		ArrayList<T> array = order(new ArrayList<>(), this.root);
		return array.toArray((T[]) new Comparable[size()]);
	}

	private ArrayList<T> order(ArrayList<T> array, BSTNode<T> node) {
		if(!node.isEmpty()){
			order(array, (BSTNode<T>) node.getLeft());
			visit(array, node);
			order(array, (BSTNode<T>) node.getRight());
		}
		return array;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] postOrder() {
		ArrayList<T> array = postOrder(new ArrayList<>(), this.root);
		return array.toArray((T[]) new Comparable[size()]);
	}

	private ArrayList<T> postOrder(ArrayList<T> array, BSTNode<T> node) {
		if(!node.isEmpty()){
			postOrder(array, (BSTNode<T>) node.getLeft());
			postOrder(array, (BSTNode<T>) node.getRight());
			visit(array, node);
		}
		return array;
	}

	/**
	 * This method is already implemented using recursion. You must understand
	 * how it work and use similar idea with the other methods.
	 */
	@Override
	public int size() {
		return size(root);
	}

	private int size(BSTNode<T> node) {
		int result = 0;
		// base case means doing nothing (return 0)
		if (!node.isEmpty()) { // indusctive case
			result = 1 + size((BSTNode<T>) node.getLeft())
					+ size((BSTNode<T>) node.getRight());
		}
		return result;
	}
}
