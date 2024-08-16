public class FilaComVetor {
    private int dados[];
    private int card_max; //Card vem de cardinalidade, ou seja, número de elementos em um conjunto
    private int card_atual;
    private int inicio;
    private int fim;
    
    public FilaComVetor(){
        card_max = 255;
        dados = new int[card_max];
        card_atual = 0; //Zero indica que a lista está vazia
        inicio = 0;
        fim = -1;
    }
    
    public boolean insereElemento(int elemento) { //Como recomendado, a função é do tipo
        if (filaCheia()) {                     //...booleano e retorna true quando a inserção funcionar 
            System.out.print("Fila já está cheia! Não é possível inserir elemento.");
            return false;
        }
        
        fim = (fim + 1) % card_max;
        dados[fim] = elemento;
        card_atual++;
        
        if (card_atual == card_max) {
            System.out.print("Fila acabou de encher! Não é possível adicionar mais elementos.");
        }
        
        return true;
    }
    
    public int removeElemento() { //Como recomendado, a função retorna o elemento removido
        if (filaVazia()) {                      
            System.out.print("Fila já está vazia! Não é possível remover elemento.");
            return -2;
        }
        
        int elemento_removido = dados[inicio];
        inicio = (inicio + 1) % card_max;
        card_atual--; 
        
        if (card_atual < 1) {
            System.out.print("Fila acabou de esvaziar! Não é possível remover elementos.");
        }
        
        return elemento_removido;
    }
	
	public int consultaInicio () {
        if (filaVazia()) {
            System.out.print("Fila Vazia");
            return -1;
        }
        
        return dados[inicio]; 
    }
    
    public boolean filaVazia() {
        return (card_atual == 0);
    }
    
    public boolean filaCheia(){
        return (card_atual == card_max); 
    }
    
    public static void main(String[] args) {
        FilaComVetor fila = new FilaComVetor();

        fila.insereElemento(10);
        fila.insereElemento(20);
        fila.insereElemento(30);
        fila.insereElemento(40);
        fila.insereElemento(50);

        System.out.println("Elemento no início da fila:  [" + fila.consultaInicio() + "]");

        System.out.println("Elemento removido da fila:   [" + fila.removeElemento() + "]");
        System.out.println("Elemento removido da fila:   [" + fila.removeElemento() + "]");

        System.out.println("Elemento no início da fila:  [" + fila.consultaInicio() + "]");
    }
}
