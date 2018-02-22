package adt.avltree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.Util;

/**
 * 
 * Performs consistency validations within a AVL Tree instance
 * 
 * @author Claudio Campelo
 *
 * @param <T>
 */
public class AVLTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements
		AVLTree<T> {

	// TODO Do not forget: you must override the methods insert and remove
		// conveniently.

		@Override
		public void insert(T element) {
			if (isEmpty()) {
				this.root.setData(element);
				this.root.setRight(new BSTNode<>());
				this.root.setLeft(new BSTNode<>());
			} else {
				insert(this.root, null, element);
			}
		}

		private void insert(BSTNode<T> node, BSTNode<T> parent, T element) {

			if (node.isEmpty()) {
				setNode(node, parent, element);
				rebalanceUp(node);
			} else {
				if (element.compareTo(node.getData()) > 0) {
					insert((BSTNode<T>) node.getRight(), node, element);
				} else {
					insert((BSTNode<T>) node.getLeft(), node, element);
				}
			}

		}

		private void setNode(BSTNode<T> node, BSTNode<T> parent, T element) {
			node.setData(element);
			node.setRight(new BSTNode<>());
			node.setLeft(new BSTNode<>());
			node.setParent(parent);
		}

		protected void spinsRight(BSTNode<T> node) {
			
			BSTNode<T> pivot = Util.rightRotation(node);
			
			if (isRoot(node)) {
				setRoot(pivot);
			}
			node.setParent(pivot);
			node.getLeft().setParent(node);
			
			setChildParent(pivot);
		}

		private void setChildParent(BSTNode<T> node) {
			if (node.getParent() != null) { 
				if (node.getData().compareTo(node.getParent().getData()) > 0) {
					node.getParent().setRight(node);
				} else {
					node.getParent().setLeft(node);
				}
			}

		}

		protected void spinsLeft(BSTNode<T> node) {
			BSTNode<T> pivot = Util.leftRotation(node);
			
			if (isRoot(node)) {
				setRoot(pivot);
			}
			node.setParent(pivot);
			node.getRight().setParent(node);
			
			setChildParent(pivot);
		}

		@Override
		public void remove(T element) {
			BSTNode<T> found = search(element);
			remove(found);
		}

		private void remove(BSTNode<T> node) {
			if (!node.isEmpty()) {
				if (node.isLeaf()) {
					node.setData(null);
					rebalanceUp(node);
				} else if (degree(node) == 1) {
					if (!node.equals(this.root)) {
						if (isLeftChild(node)) {
							if (!node.getLeft().isEmpty()) {
								node.getParent().setLeft(node.getLeft());
								node.getLeft().setParent(node.getParent());
							} else {
								node.getParent().setLeft(node.getRight());
								node.getRight().setParent(node.getParent());
							}
						} else {
							if (!node.getLeft().isEmpty()) {
								node.getParent().setRight(node.getLeft());
								node.getLeft().setParent(node.getParent());
							} else {
								node.getParent().setRight(node.getRight());
								node.getRight().setParent(node.getParent());
							}
						}
					} else {
						T newData = null;
						if (!node.getLeft().isEmpty()) {
							newData = treeMaximum((BSTNode<T>) node.getLeft()).getData();
						} else {
							newData = treeMinimum((BSTNode<T>) node.getRight()).getData();
						}
						remove(newData);
						this.root.setData(newData);
					}
					rebalanceUp(node);

				} else {
					BSTNode<T> sucessor = sucessor(node.getData());
					T newData = sucessor.getData();
					remove(sucessor.getData());
					node.setData(newData);
				}

			}
		}

		private void setRoot(BSTNode<T> node) {
			this.root = node;
		}

		private boolean isRoot(BSTNode<T> node) {
			return node.getParent() == null;
		}

		// AUXILIARY
		protected int calculateBalance(BSTNode<T> node) {
			return heightSubTree((BSTNode<T>) node.getLeft()) - heightSubTree((BSTNode<T>) node.getRight());
		}

		private int heightSubTree(BSTNode<T> aNode) {
			if (aNode.isEmpty()) {
				return -1;
			}

			int lefth = heightSubTree((BSTNode<T>) aNode.getLeft());
			int righth = heightSubTree((BSTNode<T>) aNode.getRight());

			if (lefth > righth) {
				return lefth + 1;
			} else {
				return righth + 1;
			}
		}

		// AUXILIARY
		protected void rebalance(BSTNode<T> node) {
			int balance = calculateBalance(node);
			int caso = detectCase(node, balance);
			if (caso != 0) {
				rotate(node, caso);
			}

		}
		
		protected int detectCase(BSTNode<T> node, int balance) {
			//caso 0 a arvore esta balanceada
			int caso = 0;
			if (balance == 2) {
				int newBalance = calculateBalance((BSTNode<T>) node.getLeft());
				if (newBalance == 1) {
					//caso 1 a arvore precisa de uma rotacao LL
					caso = 1;
				}
				else if (newBalance == -1) {
					//caso 2 a arvore precisa de uma rotacao LR
					caso = 2;
				}
			}
			else if (balance == -2) {
				int newBalance = calculateBalance((BSTNode<T>) node.getRight());
				if (newBalance == -1) {
					//caso 3 a arvore precisa de uma rotacao RR
					caso = 3;
				} else if (newBalance == 1) {
					//caso 4 a arvore precisa de uma rotacao RL
					caso = 4;
				}
			}
			return caso;
		}

		protected void rotate(BSTNode<T> node, int caso) {
			if (caso == 1) {
				spinsLeft(node);
			}
			else if (caso == 2) {
				spinsLeft((BSTNode<T>) node.getLeft());
				spinsRight(node);
			}

			else if (caso == 3) {
				spinsRight(node);
			}
			else {
				spinsRight(node);
				spinsLeft(node);
			}
				
		}

		// AUXILIARY
		protected void rebalanceUp(BSTNode<T> node) {
			BSTNode<T> parent = (BSTNode<T>) node.getParent();
			while (parent != null) {
				rebalance(parent);
				parent = (BSTNode<T>) parent.getParent();
			}
		}
}
