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
			this.usada = false;
		}

		public int getA() {
			return a;
		}

		public void setA(int a) {
			this.a = a;
		}

		public int getB() {
			return b;
		}

		public void setB(int b) {
			this.b = b;
		}

		public boolean isUsada() {
			return usada;
		}

		public void setUsada(boolean usada) {
			this.usada = usada;
		}

		public void trocar() {
			int auxiliar = a;
			a = b;
			b = auxiliar;
		}
	}

	private ArrayList<Peca> entrada = new ArrayList<>();

	/* adicionar peca na lista de pecas */
	public void adicionarPeca(int a, int b) {
		Peca peca = new Peca(a, b);
		entrada.add(peca);
	}

	/* Verifica se existe uma solução para o conjunto de entrada */
	public ArrayList<Peca> verificarSolucao(ArrayList<Peca> usadas, ArrayList<Peca> entrada) {
		/* enquanto existir peças sem marcar como usada */
		for (Peca peca : entrada) {
			if (!peca.isUsada()) {
				if (usadas.isEmpty()) {
					usadas.add(peca);
				} else {
					if (usadas.get(usadas.size() - 1).getB() == peca.getA()) {
						usadas.add(peca);
						peca.setUsada(true);
					} else if (usadas.get(usadas.size() - 1).getB() == peca.getB()) {
						peca.trocar();
						usadas.add(peca);
						peca.setUsada(true);
					}
				}
			}
		}

		return usadas;
	}

	/* imprime a solução encontrada */
	public String mostrarSolucao() {
		return new String();
	}

	/* retorna a lista de peças de entrada */
	public ArrayList<Peca> getEntrada() {
		return entrada;
	}

	/* ler o arquivo com as peças */
	public void lerArquivo() {

	}

	public static void main(String[] args) {
		Principal principal = new Principal();

		/* simulando a leitura de um arquivo */
		// principal.adicionarPeca(1, 4);
		// principal.adicionarPeca(2, 4);
		// principal.adicionarPeca(3, 5);

		principal.adicionarPeca(8, 1);
		principal.adicionarPeca(1, 2);
		principal.adicionarPeca(2, 7);
		principal.adicionarPeca(7, 4);
		principal.adicionarPeca(4, 3);

		/* verificando se existe solução */
		ArrayList<Peca> solucao = principal.verificarSolucao(new ArrayList<>(), principal.getEntrada());
		for (Peca peca : solucao) {
			System.out.println(peca.getA() + " " + peca.getB());
		}
	}
}
