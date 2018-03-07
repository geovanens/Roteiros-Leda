package adt.skipList.fromBST;

import java.util.HashMap;

import adt.bst.BST;
import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.skipList.SkipListImpl;

public class SkipListFromBSTImpl extends SkipListImpl<Integer> implements
		SkipListFromBST<Integer> {

	public SkipListFromBSTImpl(int maxHeight) {
		super(maxHeight);
	}

	public void importFromBST(BST<Integer> bst) {
		if (!this.isEmpty()) {
			resetSkip();
		}
		
		HashMap<Integer, Integer> elements = mapPreOrder((BSTImpl<Integer>) bst);
		for (Integer integer : elements.keySet()) {
			Integer value = integer;
			Integer height = elements.get(value);
			this.insert(value, value, this.maxHeight - height);
		}
	}
	
	public boolean isEmpty() {
		return this.size() == 0;
	}
	
	private void connectRootToNil() {
		for (int i = 0; i < maxHeight; i++) {
			this.root.getForward()[i] = NIL;
		}
	}
	
	public void resetSkip() {
		connectRootToNil();
	}
	
	/**
	 * Visita todos os nós da bst e retorna um mapa<Integer, Integer> contendo 
	 * o valor do nó e o nível que ele se encontra na bst.
	 * @param bst a bst da qual sera retornada valores e niveis dos nós. 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public HashMap<Integer, Integer> mapPreOrder(BSTImpl<Integer> bst) {
		return preOrder(new HashMap<Integer, Integer>(), bst.getRoot(), 0);
	}
	
	private HashMap<Integer, Integer> preOrder(HashMap<Integer, Integer> array, BSTNode<Integer> node, Integer level) {
		if(!node.isEmpty()){
			visit(array, node, level);
			preOrder(array, (BSTNode<Integer>) node.getLeft(), level+1);
			preOrder(array, (BSTNode<Integer>) node.getRight(), level+1);
		}
		return array;
	}

	private void visit(HashMap<Integer, Integer> array, BSTNode<Integer> node, Integer level) {
		array.put(node.getData(), level);
		
	}
}
