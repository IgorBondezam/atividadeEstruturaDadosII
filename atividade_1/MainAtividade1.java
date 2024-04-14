import java.util.*;

public class MainAtividade1 {
    public static void main(String[] args) {
        System.out.println("\n\n\n\n");
        System.out.println("Adicionando valores na memória Stack - início do escopo da Stack ");
        Stack<Produto> pilhaProd = (Produto.criarPilhaProdutos());
        pilhaProd.pop();

        HashMap<Double, LinkedList<Produto>> hashTable = new HashMap<>();
        System.out.println("Passando valores da Stack para a Heap ");
        pilhaProd.forEach(p -> {

            Double chaveHash = getChave(p.getNome());
            if(Objects.isNull(hashTable.get(chaveHash))){
                hashTable.put(chaveHash, new LinkedList<>());
            }
            hashTable.get(chaveHash).add(p);
        });
        pilhaProd = null;
        System.out.println("Stack perdendo uso - acabou o escopo do seu uso! Valor da Stack: " + pilhaProd);

        procurarPor("Ovo", hashTable);
        procurarPor("Morango", hashTable);
        procurarPor("Cafe", hashTable);
//        procurarPor("Maca", hashTable);

    }

    public static Double getChave(String nome){
        Double chaveHash = 0.0;
        for (byte letra : nome.getBytes()) {
            chaveHash = chaveHash * 31D + letra;
        }
        return chaveHash % 10D;
    }

    private static void procurarPor(String palavra, HashMap<Double, LinkedList<Produto>> hashTable){
        System.out.println("\n\nProcurando Por: " + palavra + "\n\n");
        System.out.println("\nValor da Lista Encadeada na tabela Hash:\n" + hashTable.get(getChave(palavra)));
        System.out.println("\nValor procurado na Lista Encadeada:\n" + hashTable.get(getChave(palavra))
                .stream().filter(p -> p.getNome().equals(palavra)).findFirst().get());
    }

}
