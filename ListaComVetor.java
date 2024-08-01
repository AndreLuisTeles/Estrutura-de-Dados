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
    
    /*public int cardinalidadeAlternativo() { //Caso a variável card_atual não seja incrementada 
        int tam_lista = 0;                  //ou decrementada nas funções apropriadas
        
        for (int i = 0; i < card_max; i++) {
            if (dados[i] != 0) {
                tam_lista++;
            } else {
                break;
            }
        }
        
        card_atual = tam_lista; 
        return card_atual; 
    } */ 
    
    public int obtemElemento(int posic) {
        int elemento;
        
        if ((posic <= 0) || (posic > card_atual)) {
            System.out.print("Posição inválida!");
            return -1;
        }
        
        elemento = dados[posic-1];
        return elemento; 
    }
    
    public boolean insereElemento(int posic, int elemento) { //Como recomendado, a função é do tipo
        if (listaCheia()) {                     //...booleano e retorna true quando a inserção funcionar 
            System.out.print("Lista já está cheia! Não é possível inserir elemento.");
            return false;
        }
        
        if (posic <= 0) {
            System.out.print("Posição abaixo do mínimo! Não é possível inserir elemento.");
            return false;
        }
        
        if (posic > card_atual+1) {
            System.out.print("Posição acima do máximo! Não é possível inserir elemento.");
            return false;
        }
        
        int cont = card_atual;
        while (cont > posic) {
            dados[cont] = dados[cont-1]; //Move os elementos para abrir espaço para o novo elemento
            cont--;
        }
        
        dados[posic] = elemento;
        card_atual++;
        
        if (card_atual == card_max) {
            System.out.print("Lista acabou de encher! Não é possível adicionar mais elementos.");
        }
        
        return true;
    }
    
    public int removeElemento(int posic) { //Como recomendado, a função retorna o elemento removido
        if (listaVazia()) {                      
            System.out.print("Lista já está vazia! Não é possível remover elemento.");
            return -2;
        }
        
        if (posic <= 0) {
            System.out.print("Posição abaixo do mínimo! Não é possível remover elemento.");
            return -3;
        }
        
        if (posic > card_atual+1) {
            System.out.print("Posição acima do máximo! Não é possível remover elemento.");
            return -4;
        }
        
        int cont = posic; 
        int elemento_removido = dados[posic-1]; //Posição começa em 1, enquanto a numeração do vetor 
        //... começa em zero, então para remover o primeiro elemento, por exemplo, dado[0] será removido
        while(cont < card_atual-1) { 
            dados[cont] = dados[cont+1];
            cont++;
        }
        card_atual--; 
        
        if (card_atual < 1) {
            System.out.print("Lista acabou de esvaziar! Não é possível remover elementos.");
        }
        
        return elemento_removido;
    }
}
