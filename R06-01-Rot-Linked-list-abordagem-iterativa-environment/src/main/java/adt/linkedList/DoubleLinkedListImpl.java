package adt.linkedList;

public class DoubleLinkedListImpl<T> extends SingleLinkedListImpl<T> implements
		DoubleLinkedList<T> {

	protected DoubleLinkedListNode<T> last;
	
	@Override
	public void insert(T element) {
		DoubleLinkedListNode<T> newNode = new DoubleLinkedListNode<T>(element, new DoubleLinkedListNode<T>(), new DoubleLinkedListNode<>());

		if (isEmpty()) {
			setHead(newNode);
			setLast(newNode);
		} else {
			DoubleLinkedListNode<T> oldLast = getLast();
			newNode.setPrevious(oldLast);
			oldLast.setNext(newNode);
			setLast(newNode);
		}
		
	}

	@Override
	public void insertFirst(T element) {
		
		if (isEmpty()) {
			insert(element);
		} else {
			DoubleLinkedListNode<T> newHead = new DoubleLinkedListNode<T>(element, new DoubleLinkedListNode<T>(), new DoubleLinkedListNode<T>());
			newHead.setNext(getHead());
			DoubleLinkedListNode<T> oldHead = (DoubleLinkedListNode<T>) getHead();
			oldHead.setPrevious(newHead);
			setHead(newHead);
			
		}
	}

	@Override
	public void removeFirst() {
		DoubleLinkedListNode<T> nodeNil = new DoubleLinkedListNode<>();
		if (!isEmpty()) {
			if (getHead().getNext().isNIL()) {
				setHead(nodeNil);
				setLast(nodeNil);
			}
			else {
				setHead(getHead().getNext());	
				((DoubleLinkedListNode<T>) getHead()).setPrevious(nodeNil);
			}
		}
		
	}

	@Override
	public void removeLast() {
		DoubleLinkedListNode<T> nodeNil = new DoubleLinkedListNode<>();
		if (!isEmpty()) {
			if (getLast().getPrevious().isNIL()) {
				setHead(nodeNil);
				setLast(nodeNil);
			}
			else {
				setLast(getLast().getPrevious());	
				getLast().setNext(nodeNil);
			}
		}
	}
	
	protected DoubleLinkedListNode<T> searchNode(T element) {
		DoubleLinkedListNode<T> resp;
		if (isEmpty()) {
			resp = null;
		}
		else {
			DoubleLinkedListNode<T> aux = (DoubleLinkedListNode<T>) this.head;
			while (aux != null && aux.getData() != element) {
				aux = (DoubleLinkedListNode<T>) aux.getNext();
			}
			if (aux != null) {
				resp = aux;
			}
			else {
				resp = null;
			}
			
		}
		return resp;
	}

	
	@Override
	public void remove(T element) {
		if (!isEmpty() && contains(element)) {
			if (element == this.getHead().getData()) {
				removeFirst();
			}
			else if (element == this.getLast().getData()) {
				removeLast();
			}
			else {
				DoubleLinkedListNode<T> remove = searchNode(element);
				DoubleLinkedListNode<T> last = remove.getPrevious();
				DoubleLinkedListNode<T> next = (DoubleLinkedListNode<T>) remove.getNext();
				last.setNext(next);
				next.setPrevious(last);
				
			}
		}
	}
	
	public DoubleLinkedListNode<T> getLast() {
		return last;
	}

	public void setLast(DoubleLinkedListNode<T> last) {
		this.last = last;
	}
}
