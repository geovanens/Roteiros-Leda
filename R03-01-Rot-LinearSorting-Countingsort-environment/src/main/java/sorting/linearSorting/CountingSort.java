package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa a estratégia de Counting Sort vista em sala. Procure
 * evitar desperdicio de memoria alocando o array de contadores com o tamanho
 * sendo o máximo inteiro presente no array a ser ordenado.
 * 
 */
public class CountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
		if (array != null && leftIndex < rightIndex && leftIndex >= 0 && rightIndex < array.length && array.length != 0) {
			Integer max = array[0];
			
			for (int i = leftIndex; i <= rightIndex; i++) {
				if (array[i] > max) {
					max = array[i];
				}
			}
			
			Integer[] aux = new Integer[max+1];
			for (int i = 0; i <= max; i++) {
				aux[i] = 0;
			}
			
			for (int i = leftIndex; i <= rightIndex; i++) {
				aux[array[i]]++;
			}
			
			int index = leftIndex;
			int cont = leftIndex;
			while (index <= max) {
				while (aux[index] != 0) {
					array[cont] = index;
					cont++;
					aux[index]--;
				}
				index++;
			}
		}
	}

}
