
public class Teste {

	public static void main(String[] args) {
		Integer[] v = {1,100, 125, 126, 130, 1000};
		//System.out.println(floor(v, 0));
		System.out.println(binarySearch(v, 1000, 0, v.length-1));

	}

	public static Integer binarySearch(Integer[] array, Integer key, int ini, int fim) {
		int meio = (ini + fim) / 2;
		
		if (ini > fim) {
			return -1;
		}
		else if (array[meio] == key) {
			return key;
		}
		else if (array[meio] < key) {
			return binarySearch(array, key, meio+1, fim);
		}
		else {
			return binarySearch(array, key, ini, meio-1);
		}
		

	}
	
	public static Integer floor(Integer[] array, Integer x) {
		int search = x - 1;
		int menor = array[0];
//		int f = binarySearch(array, search);
//		
//		while (f != search && search >= menor) {
//			f = binarySearch(array, --search);
//		}
	
		
		return search;
	}

}
