package adt.heap;

import util.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * O comportamento de qualquer heap é definido pelo heapify. Neste caso o
 * heapify dessa heap deve comparar os elementos e colocar o maior sempre no
 * topo. Ou seja, admitindo um comparador normal (responde corretamente 3 > 2),
 * essa heap deixa os elementos maiores no topo. Essa comparação não é feita
 * diretamente com os elementos armazenados, mas sim usando um comparator. Dessa
 * forma, dependendo do comparator, a heap pode funcionar como uma max-heap ou
 * min-heap.
 */
public class HeapImpl<T extends Comparable<T>> implements Heap<T> {

	protected T[] heap;
	protected int index = -1;
	/**
	 * O comparador é utilizado para fazer as comparações da heap. O ideal é mudar
	 * apenas o comparator e mandar reordenar a heap usando esse comparator. Assim
	 * os metodos da heap não precisam saber se vai funcionar como max-heap ou
	 * min-heap.
	 */
	protected Comparator<T> comparator;

	private static final int INITIAL_SIZE = 20;
	private static final int INCREASING_FACTOR = 10;

	/**
	 * Construtor da classe. Note que de inicio a heap funciona como uma min-heap.
	 */
	@SuppressWarnings("unchecked")
	public HeapImpl(Comparator<T> comparator) {
		this.heap = (T[]) (new Comparable[INITIAL_SIZE]);
		this.comparator = comparator;
	}

	// /////////////////// METODOS IMPLEMENTADOS
	private int parent(int i) {
		return (i - 1) / 2;
	}

	/**
	 * Deve retornar o indice que representa o filho a esquerda do elemento indexado
	 * pela posicao i no vetor
	 */
	private int left(int i) {
		return (i * 2 + 1);
	}

	/**
	 * Deve retornar o indice que representa o filho a direita do elemento indexado
	 * pela posicao i no vetor
	 */
	private int right(int i) {
		return (i * 2 + 1) + 1;
	}

	@Override
	public boolean isEmpty() {
		return (index == -1);
	}

	@Override
	public T[] toArray() {
		ArrayList<T> resp = new ArrayList<T>();
		for (int i = 0; i <= this.index; i++) {
			resp.add(this.heap[i]);
		}
		return (T[]) resp.toArray(new Comparable[0]);
	}

	// ///////////// METODOS A IMPLEMENTAR
	/**
	 * Valida o invariante de uma heap a partir de determinada posicao, que pode ser
	 * a raiz da heap ou de uma sub-heap. O heapify deve colocar os maiores
	 * (comparados usando o comparator) elementos na parte de cima da heap.
	 */
	private void heapify(int position) {
		int max = indexMax(position, left(position), right(position));

		if (max != position) {
			Util.swap(this.heap, position, max);
			heapify(max);
		}
	}

	private int indexMax(int i, int l, int r) {
		T current = this.heap[i];

		T left = null;
		if (l > 0 && l <= this.index) {
			left = this.heap[l];
		}

		T right = null;
		if (r > 0 && r <= this.index) {
			right = this.heap[r];
		}

		T max = current;
		if (left != null && comparator.compare(max, left) > 0) {
			max = left;
		}

		if (right != null && comparator.compare(max, right) > 0) {
			max = right;
		}

		int index = i;
		if (max == left) {
			index = l;
		} else if (max == right) {
			index = r;
		}

		return index;
	}

	@Override
	public void insert(T element) {
		// ESSE CODIGO E PARA A HEAP CRESCER SE FOR PRECISO. NAO MODIFIQUE
		if (index == heap.length - 1) {
			heap = Arrays.copyOf(heap, heap.length + INCREASING_FACTOR);
		}
		// /////////////////////////////////////////////////////////////////
		// TODO Implemente a insercao na heap aqui.

		this.index++;
		this.heap[this.index] = element;

		int aux = this.index;

		while (aux > 0 && comparator.compare(this.heap[parent(aux)], element) > 0) {
			Util.swap(this.heap, parent(aux), aux);
			aux = parent(aux);
		}
	}

	@Override
	public void buildHeap(T[] array) {
		this.heap = array;
		this.index = array.length - 1;
		for (int i = parent(this.index); i >= 0; i--) {
			heapify(i);
		}
	}

	@Override
	public T extractRootElement() {
		T exit = rootElement();
		if (!isEmpty()) {
			Util.swap(this.heap, 0, this.index);
			this.index--;
			heapify(0);
		}
		return exit;
		
	}

	@Override
	public T rootElement() {
		T exit = null;
		if (!isEmpty()) {
			exit = this.heap[0];
		}
		return exit;
	}

	@Override
	public T[] heapsort(T[] array) {
		HeapImpl<T> ht = new HeapImpl<>((o1, o2) -> o1.compareTo(o2));
		ht.buildHeap(array);
		T[] arr = (T[]) new Comparable[array.length];
		
		int i = 0;
		while (!ht.isEmpty()) {
			arr[i] = ht.extractRootElement();
			i++;
		}
		return arr;
	}

	@Override
	public int size() {
		return this.index + 1;
	}

	public Comparator<T> getComparator() {
		return comparator;
	}

	public void setComparator(Comparator<T> comparator) {
		this.comparator = comparator;
	}

	public T[] getHeap() {
		return heap;
	}
}
