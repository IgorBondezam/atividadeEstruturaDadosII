import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class MainAtividade3 {
    public static void main(String[] args) {
        BinaryTreeImpl treeBalanceada = new BinaryTreeImpl();
        BinaryTreeImpl treeNaoBalanceada = new BinaryTreeImpl();
        System.out.println("Importar arquivo...\n");
        List<String> linhasArquivo = lerArquivo();
        if(Objects.isNull(linhasArquivo)){
            System.out.println("Arquivo Vazio!!\n");
            return;
        }

        System.out.println("Apresentando árvore balanceada: \n\n");
        operacoesApresentacao(treeBalanceada, linhasArquivo);

        System.out.println("Apresentando a mesma árvore sem estar balanceada: \n\n");
        treeNaoBalanceada.setBalancear(false);
        operacoesApresentacao(treeNaoBalanceada, linhasArquivo);
    }

    private static List<String> lerArquivo(){
        try {
            //Para testar alterar o arquivo das palavras
            String file = "C:\\Users\\Igor Bondezam Franca\\Desktop\\Erinaldo\\atividade_1\\atividade_3\\palavras.txt";

            BufferedReader reader = new BufferedReader(new FileReader(file));
            List<String> linhaAtual = reader.lines().collect(Collectors.toList());
            reader.close();
            return linhaAtual;

        }catch (Exception e){
            e.printStackTrace();
            System.out.println("\n\nErro ao ler o arquivo, path pode estar incorreto.");
        }
        return null;
    }

    private static void operacoesApresentacao(BinaryTreeImpl tree, List<String> linhasArquivo){
        System.out.println("Adicionando todas as letras na Árvore.\n");
        linhasArquivo.forEach(tree::insert);
        System.out.println("Procurando todas as palavras:");
        linhasArquivo.forEach(l -> System.out.println(tree.findPalavra(l)));

        System.out.println("\n\n");
        System.out.println("Procurando pela palavra placa");
        System.out.println(tree.findPalavra("placa") + "\n");

        System.out.println("Tamanho: " + tree.getTamanho());
        System.out.println("Print árvore: \n" + tree.printAsTree());
        System.out.println("\n\n\n");
        System.out.print("Print inOrder: ");
        tree.inorder();
        System.out.print("\n\n");
    }
}
