import java.util.ArrayList;

public class Principal {

	/* representação de uma peça */
	private class Peca {
		private int a;
		private int b;
		private boolean usada;

		public Peca(int a, int b) {
			this.a = a;
			this.b = b;
		}
	}

	private ArrayList<Peca> lista = new ArrayList<>();

	/* adicionar peca na lista de pecas */
	public void adicionarPeca(int a, int b) {
		Peca peca = new Peca(a, b);
		lista.add(peca);
	}

	/* verifica se existe uma solução para o conjunto de entrada */
	public ArrayList<Peca> verificarSolucao() {
		return new ArrayList<>();
	}

	/* imprime a solução encontrada */
	public String mostrarSolucao() {
		return new String();
	}

	/* ler o arquivo com as peças */
	public void lerArquivo() {

	}

	public static void main(String[] args) {
		Principal principal = new Principal();

		/* simulando a leitura de um arquivo */
		principal.adicionarPeca(1, 4);
		principal.adicionarPeca(2, 4);
		principal.adicionarPeca(3, 5);

		/* verificando se existe solução */
		principal.verificarSolucao();
		principal.mostrarSolucao();
	}
}
