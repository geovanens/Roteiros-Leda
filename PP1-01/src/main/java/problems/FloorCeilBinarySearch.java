package problems;

/**
 * Calcula o floor e ceil de um numero em um array usando a estrategia de busca
 * binaria.
 * 
 * Restricoes: - Algoritmo in-place (nao pode usar memoria extra a nao ser
 * variaveis locais) - O tempo de seu algoritmo deve ser O(log n).
 * 
 * @author Adalberto
 *
 */
public class FloorCeilBinarySearch implements FloorCeil {

	public static Integer binarySearch(Integer[] array, Integer key) {
		int ini = 0;
		int fim = array.length;

		while (ini < fim) {
			int meio = (ini + fim) / 2;

			if (array[meio] == key) {
				return array[meio];
			}
			if (array[meio] < key) {
				ini = meio + 1;
			}
			if (array[meio] > key) {
				fim = meio;
			}
		}

		return -1;
	}

	@Override
	public Integer floor(Integer[] array, Integer x) {
		if (array.length != 0) {
			int search = x - 1;
			int f = binarySearch(array, search);

			f = f == search ? f : x;

			return f;

		}

		return -1;
	}

	@Override
	public Integer ceil(Integer[] array, Integer x) {
		if (array.length != 0) {
			int search = x + 1;
			int f = binarySearch(array, search);

			f = f == search ? f : x;

			return f;
		}
		return -1;

	}

}
