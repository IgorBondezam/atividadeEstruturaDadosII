import java.util.Stack;

public class Produto {
    private String nome;
    private Double preco;
    private Integer qtnEstoque;

    public Produto() {
    }

    public Produto(String nome, Double preco, Integer qtnEstoque) {
        this.nome = nome;
        this.preco = preco;
        this.qtnEstoque = qtnEstoque;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Integer getQtnEstoque() {
        return qtnEstoque;
    }

    public void setQtnEstoque(Integer qtnEstoque) {
        this.qtnEstoque = qtnEstoque;
    }

    public static Stack<Produto> criarPilhaProdutos(){
        Stack<Produto> pilha = new Stack<>();

        pilha.push(new Produto("Leite", 5.40, 3));
        pilha.push(new Produto("Leite Condensado", 7.24, 10));
        pilha.push(new Produto("Cafe", 12.56, 2));
        pilha.push(new Produto("Uva", 12.56, 2));
        pilha.push(new Produto("Ovo", 12.56, 2));
        pilha.push(new Produto("Pessego", 12.56, 2));
        pilha.push(new Produto("Morango", 12.56, 2));
        pilha.push(new Produto("Maca", 12.56, 2));

        return pilha;
    }

    @Override
    public String toString() {
        return "\nProduto{" +
                "nome='" + nome + '\'' +
                ", preco=" + preco +
                ", qtnEstoque=" + qtnEstoque +
                "}\n";
    }
}
