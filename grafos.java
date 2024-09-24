public class No {
    private int vertice; //Para a lista de adjacência, a estrutura será semelhante a de listas encadeadas
    private No prox;

    public No (int vertice) {
        this.vertice = vertice;
        this.prox = null;
    }

    public int getVertice() {
        return vertice;
    }

    public void setProx(No next) {
        this.prox = prox;
    }

    public No getProx() {
        return prox; //O molde é o mesmo usado no exercício sobre listas
    }
}
////////////////////////////
import java.util.*;
import java.io.*; //Bibliotecas usadas para trabalhar com leitura de arquivos em java

public class Grafo {
    private int V; //guarda o número de vértices
    private int[][] MatrizDeAdj;  //Matriz bidimencional
    private No[] ListaDeAdj;  //É o vetor usado para guardar a lista de adjacências

    public Grafo(int V) { //Instancia (essencialente) o grafo
        this.V = V;
        MatrizDeAdj = new int[V][V];
        ListaDeAdj = new No[V];  
    }

  
    private No adicionaNo(No cabeca, int vertex) { //Exatamente como lista
        No novo_No = new No(vertex);
        if (cabeca == null) {
            return novo_No;
        } else {
            No temp = cabeca;
            while (temp.getProx() != null) {
                temp = temp.getProx();
            }
            temp.setProx(novo_No);
        }
        return cabeca;
    }

    public void adicionaAresta(int v, int w) {
        MatrizDeAdj[v][w] = 1; //Do jeito que foi implementado, não há alocação de
        MatrizDeAdj[w][v] = 1; //vértices "avulsos", ou seja, que não possuam ligação
                               //com nenhum outro vértice
        ListaDeAdj[v] = adicionaNo(ListaDeAdj[v], w);
        ListaDeAdj[w] = adicionaNo(ListaDeAdj[w], v);
    } //A alocação de vértices avulsos apresenta o problema de que, a depender do método, eles ficam "invisíveis" para o algoritmo de print
 

    public void printMatrizDeAdj() {
        System.out.println("Matriz de Adjacência:");
        for (int i = 0; i < V; i++) { //Laço de repetição aninhado para impressão dos 
            for (int j = 0; j < V; j++) {//elementos das colunas para cada linha
                System.out.print(MatrizDeAdj[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void printListaDeAdj() {
        System.out.println("Lista de Adjacência:");
        for (int i = 0; i < V; i++) {//Visita os elementos do vetor para exibir
            System.out.print(i + ": ");//os nós em cada lista
            No temp = ListaDeAdj[i];
            while (temp != null) {
                System.out.print(temp.getVertice() + " ");
                temp = temp.getProx();
            }
            System.out.println();
        }
    }

    public void BFS(int s, int t) {
        boolean[] vert_visitado = new boolean[V]; //Impede visitação repetida
        int[] parent = new int[V];
        Arrays.fill(parent, -1);

        Queue<Integer> queue = new LinkedList<>();
        vert_visitado[s] = true;
        queue.add(s);

        while (!queue.isEmpty()) {
            int u = queue.poll();
            No temp = ListaDeAdj[u];

            while (temp != null) { //Enquanto o vetor não for nulo...
                int v = temp.getVertice();
                if (!vert_visitado[v]) { //Garante visitação única
                    vert_visitado[v] = true;
                    parent[v] = u; 
                    queue.add(v);
                    if (v == t) break;  
                }
                temp = temp.getProx();
            }
        }

        if (!vert_visitado[t]) { //Indica grafo "quebrado"
            System.out.println("Não há caminho entre os vértices " + s + " e " + t);
            return; //Nesse caso, encerra a função depois de notificar o usuário sobre o erro
        }

        List<Integer> path = new ArrayList<>();
        for (int v = t; v != -1; v = parent[v]) {
            path.add(v);
        }
        Collections.reverse(path);
        System.out.println("Caminho do BFS entre " + s + " e " + t + ": " + path);
    }

    public void DFSpilha(int s) {
        boolean[] vert_visitado = new boolean[V];
        Stack<Integer> pilha = new Stack<>();

        pilha.push(s);
        while (!pilha.isEmpty()) { //Seguindo as instruções do exercício...
            int u = pilha.pop();

            if (!vert_visitado[u]) {
                System.out.print(u + " ");
                vert_visitado[u] = true;
            }

            No temp = ListaDeAdj[u];
            while (temp != null) { //Para não usar recursão, a pilha é acrescida em um elemento
                int v = temp.getVertice();//toda vez que o vértice a ser visitado o está 
                if (!vert_visitado[v]) {//pela primeira vez
                    pilha.push(v);
                }
                temp = temp.getProx();
            }
        }
        System.out.println();
    }

    public static Grafo loadGrafoFromFile(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        int V = Integer.parseInt(br.readLine().trim());
        Grafo Grafo = new Grafo(V);

        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(" ");
            int v = Integer.parseInt(parts[0]);
            int w = Integer.parseInt(parts[1]);
            Grafo.adicionaAresta(v, w);
        }

        br.close();
        return Grafo;
    }
}
//////////////////////////////
import java.io.*; //Tratamento de arquivo

public class Main {
    public static void main(String[] args) {
        try {
            Grafo grafo = Grafo.loadGrafoFromFile("graph.txt");
    
            grafo.printMatrizDeAdj();
            grafo.printListaDeAdj();
    
    
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }
}
