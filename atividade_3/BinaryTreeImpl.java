import java.util.Arrays;
import java.util.Objects;

public class BinaryTreeImpl {

    Node root;

    Integer tamanho = 0;

    boolean balancear = true;

    public BinaryTreeImpl() {
        root = null;
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public Integer getTamanho() {
        return this.tamanho;
    }

    public boolean isBalancear() {
        return balancear;
    }

    public void setBalancear(boolean balancear) {
        this.balancear = balancear;
    }

    public void insert(String palavra) {
        Integer iteracoes = 0;
        root = insertRec(root, palavra, iteracoes);
        setTamanhosItensArvore();
    }

    private Node insertRec(Node root, String palavra, Integer iteracoes) {
        Integer somaBytePalavra = palavra.chars().sum();
        iteracoes += 1;
        if (root == null) {
            root = new Node(palavra, somaBytePalavra);
        }
        Integer somaByteRoot = root.getPalavra().chars().sum();

        if (somaBytePalavra > somaByteRoot) {
            root.setDireita(insertRec(root.getDireita(), palavra, iteracoes));
        } else if (somaBytePalavra < somaByteRoot) {
            root.setEsquerda(insertRec(root.getEsquerda(), palavra, iteracoes));
        }
        if(balancear) {
            root = balancearArvore(root);
        }
        return root;
    }

    private void setTamanhosItensArvore(){
        setTamanhosItensArvore(this.root, 0);
    }
    private void setTamanhosItensArvore(Node node, Integer posicaoLocal){
        posicaoLocal += 1;
        if(Objects.isNull(node)){
            return;
        }
        node.setTamanhoLocal(posicaoLocal);
        if(node.getTamanhoLocal() > this.tamanho){
            this.tamanho = node.getTamanhoLocal();
        }
        setTamanhosItensArvore(node.getEsquerda(), posicaoLocal);
        setTamanhosItensArvore(node.getDireita(), posicaoLocal);
    }
    public void inorder() {
        inorderRec(root);
    }

    private void inorderRec(Node root) {
        if (root != null) {
            inorderRec(root.getEsquerda());
            System.out.print(root.getChave() + " ");
            inorderRec(root.getDireita());;
        }
    }

    public Node findPalavra(String palavra) {
        if (Objects.isNull(this.root)) {
            return null;
        }
        return findPalavra(palavra, this.root);
    }

    private Node findPalavra(String palavra, Node node) {
        Integer chavePalavra = palavra.chars().sum();
        if (Objects.isNull(node) || Objects.equals(node.getChave(), chavePalavra)) {
            return node;
        } else if (chavePalavra > node.getChave()) {
            return findPalavra(palavra, node.getDireita());
        }
        return findPalavra(palavra, node.getEsquerda());
    }

    public String printAsTree() {
        if (Objects.isNull(this.root)) {
            return null;
        }
        String[] arvoreArray = printAsTree(this.root, new String[this.tamanho+1], 1);
        String arvoreString = "";
        for (int i = 0; i < arvoreArray.length; i++) {
            arvoreString += getEspacosEntreLetras(i);
            for (char letra: arvoreArray[i].toCharArray()) {
                arvoreString += letra + getEspacosEntreLetras(i-1);
            }
            arvoreString += "\n";
        }
        return arvoreString;
    }

    private String[] printAsTree(Node node, String[] arr, Integer posicaoAtual) {
        if (Objects.isNull(node)) {
            arr[posicaoAtual-1] = Objects.isNull(arr[posicaoAtual-1]) ? " " : arr[posicaoAtual-1] + " ";
            return arr;
        }
        arr[node.getTamanhoLocal()-1] = Objects.isNull(arr[node.getTamanhoLocal()-1]) ? node.getPalavra().substring(0,1) :
                arr[node.getTamanhoLocal()-1] + node.getPalavra().charAt(0);
        printAsTree(node.getEsquerda(), arr, node.getTamanhoLocal() + 1);
        printAsTree(node.getDireita(), arr, node.getTamanhoLocal() + 1);
        return arr;
    }

    private String getEspacosEntreLetras(Integer posicaoAtual){
        String espacos = "";
        for (int j = 0; j <= Math.pow(2,this.tamanho-posicaoAtual); j++) {
            espacos += " ";
        }
        return espacos;
    }












    public Node balancearArvore(Node node){
        node.setPeso(1 + Math.max(validateGetPeso(node.getEsquerda()), validateGetPeso(node.getDireita())));

        int balance = getBalance(node);
        if(balance >= -1 && balance <= 1){
            return node;
        }

        if (isRotacaoDireita(balance, node)) {
            return rotacaoDireita(node);
        }

        if (isRotacaoEsquerda(balance, node)) {
            return rotacaoEsquerda(node);
        }

        if (isRotacaoEsquerdaDireita(balance, node)) {
            return rotacaoDireita(node);
        }

        if (isRotacaoDireitaEsquerda(balance, node)) {
            return rotacaoEsquerda(node);
        }
        return node;
    }

    private boolean isRotacaoDireitaEsquerda(int balance, Node node) {
        if (balance < -1 && node.getChave() < node.getDireita().getChave()) {
            node.setDireita(rotacaoDireita(node));
            return true;
        }
        return false;
    }

    private boolean isRotacaoEsquerdaDireita(int balance, Node node) {
        if (balance > 1 && node.getChave() > node.getDireita().getChave()) {
            node.setEsquerda(rotacaoEsquerda(node));
            return true;
        }
        return false;
    }

    private boolean isRotacaoDireita(int balance, Node node) {
        return balance < -1 && node.getChave() > node.getEsquerda().getChave();
    }

    private boolean isRotacaoEsquerda(int balance, Node node) {
        return balance > 1 && node.getChave() < node.getDireita().getChave();
    }

    private int getBalance(Node node) {
        if (node == null) {
            return 0;
        }
        return validateGetPeso(node.getDireita()) - validateGetPeso(node.getEsquerda());
    }

    private Node rotacaoDireita(Node nodePrincipal) {
        Node nodeSecundario = nodePrincipal.getEsquerda();
        Node nodeTemporaria = nodeSecundario.getDireita();
        nodePrincipal.setEsquerda(nodeTemporaria);
        nodeSecundario.setDireita(nodePrincipal);

        if(nodePrincipal == this.root){
            this.root = nodeSecundario;
        }

        return nodeSecundario;
    }

    private Node rotacaoEsquerda(Node nodePrincipal) {
        Node nodeSecundario = nodePrincipal.getDireita();
        Node nodeTemporaria = nodeSecundario.getEsquerda();
        nodePrincipal.setDireita(nodeTemporaria);
        nodeSecundario.setEsquerda(nodePrincipal);

        if(nodePrincipal == root){
            root = nodeSecundario;
        }
        return nodeSecundario;
    }

    private int validateGetPeso(Node node) {
        if (node == null) {
            return 0;
        }
        return node.getPeso();
    }
}
