package adt.linkedList;

public class SingleLinkedListImpl<T> implements LinkedList<T> {

	protected SingleLinkedListNode<T> head;

	public SingleLinkedListImpl() {
		this.head = new SingleLinkedListNode<T>();
	}

	@Override
	public boolean isEmpty() {
		return this.head.isNIL();
	}

	@Override
	public int size() {
		int size = 0;
		SingleLinkedListNode<T> aux = this.head;
		while (aux != null && !aux.isNIL()) {
			size++;
			aux = aux.getNext();
		}
		
		return size;
	}

	@Override
	public T search(T element) {
		T resp;
		if (isEmpty()) {
			resp = null;
		}
		else {
			SingleLinkedListNode<T> aux = this.head;
			while (aux != null && aux.getData() != element) {
				aux = aux.getNext();
			}
			if (aux != null) {
				resp = aux.getData();
			}
			else {
				resp = null;
			}
			
		}
		return resp;
	}

	@Override
	public void insert(T element) {
		SingleLinkedListNode<T> newNode = new SingleLinkedListNode<T>(element, new SingleLinkedListNode<T>());

		if (isEmpty()) {
			setHead(newNode);
		} else {
			SingleLinkedListNode<T> aux = this.head;

			while (!aux.getNext().isNIL()) {
				aux = aux.getNext();
			}

			aux.setNext(newNode);
		}
	}
	
	/**
	 * Verifica se um elemento esta contido na lista
	 * @param element o elemento a ser procurado
	 * @return true se o elemento existe na lista ou falso caso contrario. 
	 */
	protected boolean contains(T element) {
		return search(element) != null;
	}

	@Override
	public void remove(T element) {
		if (contains(element)) {
			if (this.head.getData() == element) {
				this.head = this.head.getNext();
			} else {
				SingleLinkedListNode<T> aux = this.head;
				SingleLinkedListNode<T> prev = null;

				while (aux != null && aux.getData() != element) {
					prev = aux;
					aux = aux.getNext();
				}

				prev.setNext(aux.getNext());
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] toArray() {
		T[] array = (T[]) new Object[this.size()];
		SingleLinkedListNode<T> aux = this.head;
		
		int index = 0;
		while (aux.getNext() != null) {
			array[index] = aux.getData();
			aux = aux.getNext();
			index++;
		}
		
		return array;
	}

	public SingleLinkedListNode<T> getHead() {
		return head;
	}

	public void setHead(SingleLinkedListNode<T> head) {
		this.head = head;
	}
}
