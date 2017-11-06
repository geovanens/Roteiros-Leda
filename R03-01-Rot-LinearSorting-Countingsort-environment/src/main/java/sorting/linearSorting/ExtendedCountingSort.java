package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa do Counting Sort vista em sala. Desta vez este
 * algoritmo deve satisfazer os seguitnes requisitos: - Alocar o tamanho minimo
 * possivel para o array de contadores (C) - Ser capaz de ordenar arrays
 * contendo numeros negativos
 */
public class ExtendedCountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
		if (array != null && leftIndex < rightIndex && leftIndex >= 0 && rightIndex < array.length
				&& array.length != 0) {
			Integer max = array[leftIndex];
			Integer min = array[leftIndex];

			for (int i = leftIndex; i <= rightIndex; i++) {
				if (array[i] > max) {
					max = array[i];
				}
				if (array[i] < min) {
					min = array[i];
				}
			}

			Integer[] aux = new Integer[max - min + 1];
			for (int i = 0; i < aux.length; i++) {
				aux[i] = 0;
			}

			for (int i = leftIndex; i <= rightIndex; i++) {
				aux[array[i] - min]++;
			}

			int index = leftIndex;
			int cont = leftIndex;
			while (index < aux.length) {
				while (aux[index] != 0) {
					array[cont] = index + min;
					cont++;
					aux[index]--;
				}
				index++;
			}
		}
	}
}
