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
public class AVLTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements AVLTree<T> {

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
			if (parent.getParent() == null) {
				setNode(node, parent, element);
			} else if (isBalanced(parent)) {
				setNode(node, parent, element);
			} else {
				if (isRightPending((BSTNode<T>) parent.getParent())) {
					// pendendo pra direita e adicionado a direita
					if (element.compareTo(parent.getData()) > 0) {
						setNode(node, parent, element);
						spinsLeft((BSTNode<T>) parent.getParent());
					}
					// pendendo pra direita e adicionado a esquerda (zigzag)
					else {
						setNode(node, parent, element);
						BSTNode<T> newParent = (BSTNode<T>) parent.getParent();
						spinsRight(parent);
						spinsLeft(newParent);
					}
				} else if (isLeftPending((BSTNode<T>) parent.getParent())) {
					// pendendo pra esquerda e adicionado a esquerda
					if (element.compareTo(parent.getData()) < 0) {
						setNode(node, parent, element);
						spinsRight((BSTNode<T>) parent.getParent());
					}
					// pendendo pra esquerda e adicionado a direita (zigzag)
					else {
						setNode(node, parent, element);
						BSTNode<T> newParent = (BSTNode<T>) parent.getParent();
						spinsLeft(parent);
						spinsRight(newParent);
					}
				}
			}
		} else {
			if (element.compareTo(node.getData()) > 0) {
				insert((BSTNode<T>) node.getRight(), node, element);
			} else {
				insert((BSTNode<T>) node.getLeft(), node, element);
			}
		}

	}

	private boolean isBalanced(BSTNode<T> node) {
		if (node.isEmpty() || Math.abs(calculateBalance(node)) < 2) {
			return true;
		}
		return false;
	}

	private boolean isRightPending(BSTNode<T> node) {
		return calculateBalance(node) == -1;
	}

	private boolean isLeftPending(BSTNode<T> node) {
		return calculateBalance(node) == 1;
	}

	private void setNode(BSTNode<T> node, BSTNode<T> parent, T element) {
		node.setData(element);
		node.setRight(new BSTNode<>());
		node.setLeft(new BSTNode<>());
		node.setParent(parent);
	}

	public void spinsRight(BSTNode<T> node) {
		
		BSTNode<T> pivot = Util.rightRotation(node);
		
		if (isRoot(node)) {
			setRoot(pivot);
		}
		
		pivot.setParent(node.getParent());
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

	public void spinsLeft(BSTNode<T> node) {
		BSTNode<T> pivot = Util.leftRotation(node);
		
		if (isRoot(node)) {
			setRoot(pivot);
		}
		
		pivot.setParent(node.getParent());
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
		if (Math.abs(balance) > 1)
			if (balance == 2) {
				int newBalance = calculateBalance((BSTNode<T>) node.getLeft());
				if (newBalance == 1) {
					spinsRight(node);
				} else if (newBalance == -1) {
					spinsLeft((BSTNode<T>) node.getLeft());
					spinsRight(node);
				}

			} else if (balance == -2) {
				int newBalance = calculateBalance((BSTNode<T>) node.getRight());
				if (newBalance == -1) {
					spinsLeft(node);
				} else if (newBalance == 1) {
					spinsRight((BSTNode<T>) node.getRight());
					spinsLeft(node);
				}
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

	public static void main(String[] args) {
		AVLTreeImpl<Integer> tree = new AVLTreeImpl<>();
		tree.insert(70);
		tree.insert(60);
		tree.insert(85);
		tree.insert(80);
		tree.insert(90);

		tree.remove(90);
		tree.remove(60);
		System.out.println(tree.getRoot());

	}
}
