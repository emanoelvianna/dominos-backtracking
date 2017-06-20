package br.com.principal;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Principal {

	/* representação de uma peça */
	class Peca {
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
	private ArrayList<Peca> resultado;

	public ArrayList<Peca> backtrack(ArrayList<Peca> usadas, ArrayList<Peca> entrada, int tentativa) {
		if (entrada.isEmpty() || (tentativa > entrada.size() - 1)) {
			return usadas;
		} else {
			for (Peca peca : entrada) {
				if (!entrada.isEmpty() && usadas.get(usadas.size() - 1).getB() == peca.getA()) {
					usadas.add(peca);
					usadas.get(usadas.size() - 1).setUsada(true);
					entrada.remove(0);
					backtrack(usadas, entrada, 0);
				}
				if (!entrada.isEmpty() && usadas.get(usadas.size() - 1).getB() == peca.getB()) {
					usadas.add(peca);
					usadas.get(usadas.size() - 1).setUsada(true);
					usadas.get(usadas.size() - 1).trocar();
					entrada.remove(0);
					backtrack(usadas, entrada, 0);
				}
				/* peca não encaixa */
				if (!entrada.isEmpty()) {
					Peca auxiliar = peca;
					entrada.remove(0);
					entrada.add(auxiliar);
					tentativa++;
					backtrack(usadas, entrada, tentativa);
				}
				if (entrada.isEmpty())
					break;
			}
		}
		return usadas;
	}

	/* adicionar peca na lista de pecas */
	public void adicionarPeca(int a, int b) {
		Peca peca = new Peca(a, b);
		entrada.add(peca);
	}

	/* imprime a solução encontrada */
	public String mostrarSolucao() {
		return new String();
	}

	/* retorna a lista de peças de entrada */
	public ArrayList<Peca> getEntrada() {
		return entrada;
	}

	/* retorna a lista de peças usadas para a solucao */
	public ArrayList<Peca> getResultado() {
		return resultado;
	}

	/* ler o arquivo com as peças */
	public void lerArquivo() {
		try {
			BufferedReader info = new BufferedReader(new FileReader("case11.txt"));
			String linha = info.readLine();
			String[] tamanhoMatriz = linha.split(" ");

			linha = info.readLine();
			while (linha != null) {
				String[] aux = linha.split(" ");

				adicionarPeca(Integer.parseInt(aux[0]), Integer.parseInt(aux[1]));

				linha = info.readLine();
			}

		} catch (IOException e) {
			System.out.println("Erro ao abrir arquivo de movimentacoes");
		}
	}

	public static void main(String[] args) {
		Principal principal = new Principal();

		/* simulando a leitura de um arquivo */
		principal.lerArquivo();
		for (int i = 0; i < principal.getEntrada().size() - 1; i++) {
			ArrayList<Peca> usadas = new ArrayList<>();
			usadas.add(principal.getEntrada().get(i));

			/* removendo o elemento como possivel solução da lista de entrada */
			ArrayList<Peca> entrada = new ArrayList<>(principal.getEntrada());
			entrada.remove(i);

			ArrayList<Peca> resultado = principal.backtrack(usadas, entrada, 0);
			for (Peca peca : resultado) {
				System.out.print("[" + peca.getA() + " " + peca.getB() + "]" + " ");
			}
			System.out.println("\n");
		}

	}
}
