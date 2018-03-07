package adt.skipList;

public class SkipListImpl<T> implements SkipList<T> {

	protected SkipListNode<T> root;
	protected SkipListNode<T> NIL;

	protected int maxHeight;

	protected double PROBABILITY = 0.5;

	public SkipListImpl(int maxHeight) {
		this.maxHeight = maxHeight;
		root = new SkipListNode(Integer.MIN_VALUE, maxHeight, null);
		NIL = new SkipListNode(Integer.MAX_VALUE, maxHeight, null);
		connectRootToNil();
	}

	/**
	 * Faz a ligacao inicial entre os apontadores forward do ROOT e o NIL Caso
	 * esteja-se usando o level do ROOT igual ao maxLevel esse metodo deve
	 * conectar todos os forward. Senao o ROOT eh inicializado com level=1 e o
	 * metodo deve conectar apenas o forward[0].
	 */
	private void connectRootToNil() {
		for (int i = 0; i < maxHeight; i++) {
			root.forward[i] = NIL;
		}
	}

	
	@Override
	public void insert(int key, T newValue, int height) {
		@SuppressWarnings("unchecked")
		SkipListNode<T>[] update = new SkipListNode[this.maxHeight];
		
		SkipListNode<T> aux = this.root;
		for (int level = this.maxHeight-1; level >= 0; level--) {
			while (aux.getForward(level).getKey() < key) {
				aux = aux.getForward(level);
			}
			update[level] = aux;
		}
		aux = aux.getForward(0);
		if (aux.getKey() == key) {
			aux.setValue(newValue);
		}
		else {
			SkipListNode<T> newNode = new SkipListNode<T>(key, height, newValue);
			for (int lev = 0; lev < height; lev++) {
				newNode.getForward()[lev] = update[lev].getForward()[lev];
				update[lev].getForward()[lev] = newNode;
			}
		}
	}

	@Override
	public void remove(int key) {
		@SuppressWarnings("unchecked")
		SkipListNode<T>[] update = new SkipListNode[this.maxHeight];
		
		SkipListNode<T> aux = this.root;
		for (int level = this.maxHeight-1; level >= 0; level--) {
			while (aux.getForward(level).getKey() < key) {
				aux = aux.getForward(level);
			}
			update[level] = aux;
		}
		aux = aux.getForward(0);
		if (aux.getKey() == key) {
			for (int lev = 0; lev < this.height(); lev++) {
				if (update[lev].getForward(lev) != aux) {
					break;
				}
				update[lev].getForward()[lev] = aux.getForward(lev);
			}
		}
	}

	@Override
	public int height() {
		SkipListNode<T> aux = this.root;
		int height = 0;
		while (aux.getForward(0).getKey() != Integer.MAX_VALUE) {
			aux = aux.getForward(0);
			
			if (aux.height() > height) {
				height = aux.height();
			}
		}
		return height;
	}

	@Override
	public SkipListNode<T> search(int key) {
		SkipListNode<T> aux = this.root;
		SkipListNode<T> found = null;
		for (int level = this.maxHeight-1; level >= 0; level--) {
			while (aux.getForward(level).getKey() < key) {
				aux = aux.getForward(level);
			}
		}
		aux = aux.getForward(0);
		
		if (aux.getKey() == key) {
			found = aux;
		}
		
		return found;
	}

	@Override
	public int size() {
		SkipListNode<T> aux = this.root;
		int size = 0;
		while (aux.getForward(0).getKey() != Integer.MAX_VALUE) {
			aux = aux.getForward(0);
			size++;
		}
		return size;
	}

	@Override
	public SkipListNode<T>[] toArray() {
		@SuppressWarnings("unchecked")
		SkipListNode<T>[] allNodes = new SkipListNode[this.size()+2];
		SkipListNode<T> aux = this.root;
		int index = 0;
		while (aux != null && aux.key <= Integer.MAX_VALUE) {
			allNodes[index] = aux;
			aux = aux.getForward(0);
			index++;
		}
		return allNodes;
	}
}