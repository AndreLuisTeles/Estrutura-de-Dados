public class ListaComVetor {
    private int dados[];
    private int card_max; //Card vem de cardinalidade, ou seja, número de elementos em um conjunto
    private int card_atual;
    
    public ListaComVetor(){
        dados = new int[card_max];
        card_max = 255;
        card_atual = 0; //Zero indica que a lista está vazia
    }
    
    public boolean listaVazia() {
        return (card_atual == 0);
    }
    
    public boolean listaCheia(){
        return (card_atual == card_max); 
    }
    
    public int cardinalidade() {
        return card_atual; 
    } 
    
    public int cardinalidade_alternativo() {
        int tam_lista = 0;
        
        for (int i = 0; i < card_max; i++) {
            if (dados[i] != 0) {
                tam_lista++;
            } else {
                break;
            }
        }
        
        card_atual = tam_lista; 
        return card_atual; 
    }
    
    public int obtemElemento(int posic) {
        int elemento;
        
        if ((posic <= 0) || (posic > card_atual)) {
            System.out.print("Posição inválida!");
            return -1;
        }
        
        elemento = dados[posic-1];
        return elemento; 
    }
    

	public static void main(String[] args) {
        System.out.println("Hello World");
	}
}

