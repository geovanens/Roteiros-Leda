package adt.linkedList.batch;

import adt.linkedList.DoubleLinkedListImpl;
import adt.linkedList.DoubleLinkedListNode;
import util.GenericException;

/**
 * Manipula elementos da LinkedList em bloco (batch).
 * 
 * @author campelo
 * @author adalberto
 *
 * @param <T>
 */
public class BatchLinkedListImpl<T> extends DoubleLinkedListImpl<T> implements BatchLinkedList<T> {

	/* 
	 * Nao modifique nem remova este metodo.
	 */
	public BatchLinkedListImpl() {
		head = new DoubleLinkedListNode<T>();
		last = (DoubleLinkedListNode<T>)head;
	}
	
	@Override
	public boolean isEmpty() {
		return this.head.isNIL();
	}

	@Override
	public int size() {
		DoubleLinkedListNode<T> aux = (DoubleLinkedListNode<T>) this.head;
		int size = 0;
		while (!aux.isNIL()) {
			aux = (DoubleLinkedListNode<T>) aux.getNext();
			size++;
		}
		return size;
	}

	@Override
	public void inserirEmBatch(int posicao, T[] elementos) throws GenericException {
		if (posicao < 0 || posicao > this.size() || elementos == null) {
			throw new GenericException();
		}
		if (elementos.length > 0) {
			DoubleLinkedListNode<T> firstNode = new DoubleLinkedListNode<>();
			firstNode.setData(elementos[0]);
			firstNode.setNext(new DoubleLinkedListNode<>());
			firstNode.setPrevious(new DoubleLinkedListNode<>());
			
			DoubleLinkedListNode<T> noAtual = firstNode;
			for (int i = 1; i < elementos.length; i++) {
				DoubleLinkedListNode<T> newNode = new DoubleLinkedListNode<>();
				newNode.setData(elementos[i]);
				newNode.setPrevious(noAtual);
				newNode.setNext(new DoubleLinkedListNode<>());
				noAtual.setNext(newNode);
				
				noAtual = newNode;
			}
			
			if (isEmpty()) {
				this.setHead(firstNode);
				this.setLast(noAtual);
			}
			else {
				DoubleLinkedListNode<T> inicio = getNodeFromIndex(posicao);
				DoubleLinkedListNode<T> next = (DoubleLinkedListNode<T>) inicio.getNext();
				
				inicio.setNext(firstNode);
				firstNode.setPrevious(inicio);
				
				next.setPrevious(noAtual);
				noAtual.setNext(next);				
			}
		}
		
	}

	@Override
	public void removerEmBatch(int posicao, int quantidade) throws GenericException {
		if (posicao < 0 || posicao > this.size() || posicao+quantidade > this.size()) {
			throw new GenericException();
		}
		
		if (quantidade != 0) {
			int size = this.size();
			
			DoubleLinkedListNode<T> inicioRemove = getNodeFromIndex(posicao);
			DoubleLinkedListNode<T> fimRemove = getNodeFromDistanceNode(inicioRemove, quantidade);
			
			DoubleLinkedListNode<T> prevInicioRemove = inicioRemove.getPrevious();
			DoubleLinkedListNode<T> nextFimRemove = (DoubleLinkedListNode<T>) fimRemove.getNext();
			
			prevInicioRemove.setNext(nextFimRemove);
			nextFimRemove.setPrevious(prevInicioRemove);
			
			if (posicao == 0) {
				this.setHead(nextFimRemove);
			}
			if (posicao+quantidade == size) {
				this.setLast(prevInicioRemove);
			}
		}
		
		
	}
	
	/**
	 * Pega o ultimo elemento a ser removido. 
	 */
	private DoubleLinkedListNode<T> getNodeFromDistanceNode(DoubleLinkedListNode<T> node, int distance) {
		DoubleLinkedListNode<T> aux = node;
		int cont = 1;
		while (cont < distance) {
			aux = (DoubleLinkedListNode<T>) aux.getNext();
			cont++;
		}
		return aux;
	}

	/**
	 * Pega o primeiro elemento a partir do qual será feita a remoção.
	 */
	private DoubleLinkedListNode<T> getNodeFromIndex(int index) {
		DoubleLinkedListNode<T> aux = (DoubleLinkedListNode<T>) this.head;
		int cont = 0;
		while (cont < index) {
			aux = (DoubleLinkedListNode<T>) aux.getNext();
			cont++;
		}
		return aux;
	}
	
	
	/* 
	 * NAO MODIFIQUE NEM REMOVA ESTE METODO!!!
	 * 
	 * Use este metodo para fazer seus testes
	 * 
	 * Este metodo monta uma String contendo os elementos do primeiro ao ultimo, 
	 * comecando a navegacao pelo Head
	 */
	public String toStringFromHead() {
		
		String result = "";
		DoubleLinkedListNode<T> aNode = (DoubleLinkedListNode<T>)getHead();
		
		while(!aNode.isNIL()) {
			
			if (!result.isEmpty()) {
				result += " ";
			}
				
			result += aNode.getData();
			aNode = (DoubleLinkedListNode<T>) aNode.getNext();
			
		}
		
		return result;
	}
	
	/* 
	 * NAO MODIFIQUE NEM REMOVA ESTE METODO!!!
	 * 
	 * Use este metodo para fazer seus testes
	 * 
	 * Este metodo monta uma String contendo os elementos do primeiro ao ultimo, 
	 * porem comecando a navegacao pelo Last
	 * 
	 * Este metodo produz o MESMO RESULTADO de toStringFromHead() 
	 * 
	 */
	public String toStringFromLast() {
		
		String result = "";
		DoubleLinkedListNode<T> aNode = getLast();
		
		while(!aNode.isNIL()) {
			
			if (!result.isEmpty()) {
				result = " " + result;
			}
				
			result = aNode.getData() + result;
			aNode = (DoubleLinkedListNode<T>) aNode.getPrevious();
			
		}
		
		return result;
	}
	
	@Override
	public String toString() {
		return toStringFromHead();
	}
}
