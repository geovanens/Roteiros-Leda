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
public class RepositorioProdutoNaoPerecivelArray {
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
		int indice = -1;
		boolean encontrou = false;
		for (int i = 0; i < produtos.length; i++) {
			if (produtos[i] != null && produtos[i].getCodigo() == codigo && !encontrou) {
				indice = i;
				encontrou = true;
			}
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
		boolean existe = false;
		boolean encontrou = false;
		for (ProdutoNaoPerecivel produtoNaoPerecivel : produtos) {
			if (produtoNaoPerecivel != null && produtoNaoPerecivel.getCodigo() == codigo && !encontrou) {
				existe = true;
				encontrou = true;
			}
		}
		
		return existe;
	}

	/**
	 * Insere um novo produto (sem se preocupar com duplicatas)
	 */
	public void inserir(ProdutoNaoPerecivel produto) {
		boolean inseriu = false;
		for (int i = 0; i< produtos.length; i++) {
			if (produtos[i] == null && !inseriu) {
				produtos[i] = produto;
				inseriu = true;
			}
		}
		
		int size = produtos.length;
		if (!inseriu) {
			extende();
			inserir(produto);
		}
	}
	
	private void extende() {
		int size = produtos.length;
		if (size == 0) {
			ProdutoNaoPerecivel[] newProdutos = new ProdutoNaoPerecivel[1];
			this.produtos = newProdutos;
		}
		else {
			ProdutoNaoPerecivel[] newProdutos = new ProdutoNaoPerecivel[2*size];
			for (int i = 0; i < size; i++) {
				newProdutos[i] = produtos[i];
			}
			this.produtos = newProdutos;
		}
		
		
	}

	/**
	 * Atualiza um produto armazenado ou retorna um erro caso o produto nao
	 * esteja no array. Note que, para localizacao, o código do produto será
	 * utilizado.
	 */
	public void atualizar(ProdutoNaoPerecivel produto) {
		boolean atualizou = false;
		for (int i = 0; i < produtos.length; i++) {
			if (produtos[i] != null && produtos[i].getCodigo() == produto.getCodigo() && !atualizou) {
				produtos[i] = produto;
				atualizou = true;
			}
		}
		if (!atualizou) {
			throw new RuntimeException();
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
		boolean removeu = false;
		for (int i = 0; i < produtos.length; i++) {
			if (produtos[i] != null && produtos[i].getCodigo() == codigo && !removeu) {
				removeu = true;
				int j = i;
				while (j < produtos.length && produtos[j] != null) {
					produtos[j] = produtos[j+1];
					j++;
				}
				produtos[j-1] = null;
			}
		}
		if (!removeu) {
			throw new RuntimeException();
		}
	}

	/**
	 * Retorna um produto com determinado codigo ou entao um erro, caso o
	 * produto nao esteja armazenado
	 * 
	 * @param codigo
	 * @return
	 */
	public ProdutoNaoPerecivel procurar(int codigo) {
		boolean encontrou = false;
		ProdutoNaoPerecivel pnp = null;
		for (ProdutoNaoPerecivel produtoNaoPerecivel : produtos) {
			if (!encontrou && produtoNaoPerecivel != null && produtoNaoPerecivel.getCodigo() == codigo) {
				pnp = produtoNaoPerecivel;
			}
		}
		if (pnp != null) {
			return pnp;
		}
		throw new RuntimeException();
	}

}