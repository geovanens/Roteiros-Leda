package produto;

/**
 * Classe que representa um repositório de produtos usando arrays como estrutura
 * sobrejacente. Alguns métodos (atualizar, remover e procurar) ou executam com
 * sucesso ou retornam um erro. Para o caso desde exercício, o erro será
 * representado por uma RuntimeException que não precisa ser declarada na
 * clausula "throws" do mos metodos.
 * 
 * Obs: Note que você deve utilizar a estrutura de dados array e não
 * implementações de array prontas da API Collections de Java (como ArrayList,
 * por exemplo).
 * 
 * @author Adalberto
 *
 */
public class RepositorioProdutoNaoPerecivelArray implements RepositorioProdutos {
	/**
	 * A estrutura (array) onde os produtos sao mantidos.
	 */
	private ProdutoNaoPerecivel[] produtos;

	/**
	 * A posicao do ultimo elemento inserido no array de produtos. o valor
	 * inicial é -1 para indicar que nenhum produto foi ainda guardado no array.
	 */
	private int index = -1;

	public RepositorioProdutoNaoPerecivelArray(int size) {
		super();
		this.produtos = new ProdutoNaoPerecivel[size];
	}

	/**
	 * Recebe o codigo do produto e devolve o indice desse produto no array ou
	 * -1 caso ele nao se encontre no array. Esse método é util apenas na
	 * implementacao com arrays por questoes de localizacao. Outras classes que
	 * utilizam outras estruturas internas podem nao precisar desse método.
	 * 
	 * @param codigo
	 * @return
	 */
	private int procurarIndice(int codigo) {
		int i = 0;
		int indice = -1;
		boolean encontrou = false;
		while ((i <= index) && !encontrou) {
			if (produtos[i].getCodigo() == codigo) {
				indice = i;
				encontrou = true;
			}
			i++;
		}
			
		
		return indice;
	}

	/**
	 * Recebe o codigo e diz se tem produto com esse codigo armazenado
	 * 
	 * @param codigo
	 * @return
	 */
	public boolean existe(int codigo) {
		int indice = procurarIndice(codigo);
		boolean existe = (indice != -1);
		return existe;
	}
	

	/**
	 * Insere um novo produto (sem se preocupar com duplicatas)
	 */
	public void inserir(Produto produto) {
		if (this.index < produtos.length-2) {
			produtos[++index] = (ProdutoNaoPerecivel) produto;
		}
		else {
			extende();
			produtos[++index] = (ProdutoNaoPerecivel) produto;
		}
	}
	
	/**
	 * Duplica o tamanho do array
	 */
	private void extende() {
		int size = produtos.length;
		
		ProdutoNaoPerecivel[] newProdutos = new ProdutoNaoPerecivel[2*size];
		for (int i = 0; i < size; i++) {
			newProdutos[i] = produtos[i];
		}
		this.produtos = newProdutos;
		
	}

	/**
	 * Atualiza um produto armazenado ou retorna um erro caso o produto nao
	 * esteja no array. Note que, para localizacao, o código do produto será
	 * utilizado.
	 */
	public void atualizar(Produto produto) {
		int indice = this.procurarIndice(produto.getCodigo());
		
		if (indice != -1) {
			produtos[indice] = (ProdutoNaoPerecivel) produto;
		}
		else {
			throw new RuntimeException("Produtos nao encontrado");
		}
	}

	/**
	 * Remove produto com determinado codigo, se existir, ou entao retorna um
	 * erro, caso contrário. Note que a remoção NÃO pode deixar "buracos" no
	 * array.
	 * 
	 * @param codigo
	 */
	public void remover(int codigo) {
		int indice = this.procurarIndice(codigo);
		
		if (indice != -1) {
			while (indice < produtos.length && produtos[indice] != null) {
				produtos[indice] = produtos[indice+1];
				indice++;
			}
			produtos[indice] = null;
			index--;
		}
		else {
			throw new RuntimeException("Produtos nao encontrado");
		}
	}

	/**
	 * Retorna um produto com determinado codigo ou entao um erro, caso o
	 * produto nao esteja armazenado
	 * 
	 * @param codigo
	 * @return
	 */
	public Produto procurar(int codigo) {
		int indice = this.procurarIndice(codigo);
		
		if (indice != -1) {
			return produtos[indice];
		}
		else {
			throw new RuntimeException("Produtos nao encontrado");
		}
	}

}
