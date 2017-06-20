import java.util.ArrayList;
import java.util.List;

public class Domino {

    /* Representação de uma peça de dominó*/
    static class Peca {
        private int a;
        private int b;
        private boolean usada;
        private boolean primeiro;
        private Peca proximo;
        private boolean ultimo;

        Peca(int a, int b) {
            this.a = a;
            this.b = b;
            this.usada = false;
            this.primeiro = false;
            this.proximo = null;
            this.ultimo = false;
        }

        int getA() {
            return a;
        }

        int getB() {
            return b;
        }

        boolean isUsada() {
            return usada;
        }

        void setUsada(boolean usada) {
            this.usada = usada;
        }

        Peca getProximo() {
            return proximo;
        }

        void setProximo(Peca proximo) {
            this.proximo = proximo;
        }

        void trocar() {
            int auxiliar = a;
            a = b;
            b = auxiliar;
        }

        boolean getPrimeiro(){
            return primeiro;
        }

        void setPrimeiro(boolean primeiro){
            this.primeiro = primeiro;
        }

        boolean getUltimo(){
            return ultimo;
        }

        void setUltimo(boolean ultimo){
            this.ultimo = ultimo;
        }

    }

    /*Inicializa a recrusão*/
    private List<Peca> combinacao(Peca peca){
        List<Peca> tripa = new ArrayList<>();
        Peca proximaPeca = peca.getProximo();
        return combinacao(peca, proximaPeca,tripa);
    }

    /*Realiza a recursão*/
    private List<Peca> combinacao(Peca peca1, Peca peca2, List<Peca> tripa){
        if(peca1.getUltimo() && peca1.getProximo() == null)
            return tripa;
        peca1.setUsada(true);
        tripa.add(peca1);
        if((peca1.getB() == peca2.getA()) && !peca2.isUsada()){
            peca1 = peca2;
            peca2 = peca2.getProximo();
            return combinacao(peca1, peca2, tripa);
        }
        if((peca1.getB() == peca2.getB()) && !(peca2.isUsada())){
            peca2.trocar();
            peca1 = peca2;
            peca2 = peca2.getProximo();
            return combinacao(peca1, peca2, tripa);
        }
        if(peca1.getUltimo() && peca1.getProximo().getPrimeiro()){
            peca1.setProximo(null);
            return combinacao(peca1, peca2, tripa);
        }
        peca2 = peca2.getProximo();
        peca1.setUsada(false);
        tripa.remove(peca1);
        return combinacao(peca1, peca2, tripa);
    }

    public static void main(String[] args) {
        Domino domino = new Domino();

        /*Teste simples*/
        Peca peca1 = new Peca(1, 2);
        Peca peca2 = new Peca(3, 4);
        Peca peca3 = new Peca(2, 3);

        peca1.setProximo(peca2);
        peca2.setProximo(peca3);
        peca3.setProximo(peca1);

        /*Auxiliares para a recursão*/
        peca1.setPrimeiro(true);
        peca3.setUltimo(true);

        /*Imprime a combinação dos dominos*/
        List<Peca> tripa = domino.combinacao(peca1);
        if (tripa.isEmpty())
            System.out.println("SEM COMBINAÇÃO");
        else{
            System.out.println("COMBINAÇÃO: ");
            for (Peca aux: tripa){
                String peca = "[" + aux.getA() + "|" + aux.getB() + "] ";
                System.out.print(peca);
            }
        }
    }
}

