import java.util.Objects;

public class Node {
    private String palavra;
    private Node esquerda;
    private Node direita;
    private Integer tamanhoLocal;
    private Integer chave;
    private Integer peso = 1;

    public Node(String palavra, Integer key) {
        this.palavra = palavra;
        this.chave = key;
        this.esquerda = null;
        this.direita = null;
    }

    public Node() {
    }

    public String getPalavra() {
        return palavra;
    }

    public Node getEsquerda() {
        return esquerda;
    }

    public Node getDireita() {
        return direita;
    }

    public Integer getChave() {
        return chave;
    }

    public void setChave(Integer chave) {
        this.chave = chave;
    }

    public void setPalavra(String palavra) {
        this.palavra = palavra;
    }

    public void setEsquerda(Node esquerda) {
        this.esquerda = esquerda;
    }

    public void setDireita(Node direita) {
        this.direita = direita;
    }

    public Integer getTamanhoLocal() {
        return tamanhoLocal;
    }

    public void setTamanhoLocal(Integer tamanhoLocal) {
        this.tamanhoLocal = tamanhoLocal;
    }

    public Integer getPeso() {
        return peso;
    }

    public void setPeso(Integer peso) {
        this.peso = peso;
    }

    @Override
    public String toString() {
        return "Node{" +
                "palavra='" + palavra + '\'' +
                ", esquerda=" + (Objects.isNull(esquerda) ? " null" : esquerda.palavra) +
                ", direita=" + (Objects.isNull(direita) ? " null" : direita.palavra) +
                ", tamanhoLocal=" + tamanhoLocal +
                ", chave=" + chave +
                '}';
    }
}
