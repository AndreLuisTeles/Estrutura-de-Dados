public class No {
    private int dado;
    private No prox;

    public No() {
        prox = null;
    }

    public No getProx() {
        return prox;
    }

    public void setProx(No prox) {
        this.prox = prox;
    }

    public int getDado() {
        return dado;
    }

    public void setDado(int dado) {
        this.dado = dado;
    }
}
////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////
public class Lista{
    private No head;
    private int card_atual;    
    
    public Lista(){
        head = null;
        card_atual = 0;
    }
    
    public boolean listaVazia() {
        return (card_atual == 0);
    }

    public int tamanhoLista() {
        return card_atual;
    }
    
    public int tamanhoListaAlternativo() {
        if (listaVazia()) {
            System.out.print("Lista vazia");
            card_atual = 0;
            return card_atual;
        }
        
        No atual = head;
        int cont;
        for (cont = 1; atual != null; cont++) {
            atual = atual.getProx();  
        }
        
        card_atual = cont;
        return card_atual;
    }
    
    public int obtemDado(int pos) {
        if (listaVazia()) {
            System.out.print("Lista vazia");
            return -1;
        }
        
        if ((pos < 1) || (pos > card_atual)) {
            System.out.print("Posição inválida");
            return -2;
        } 
        
        No atual = head;
        int cont;
        for (cont = 1; cont != pos; cont++) {
            atual = atual.getProx();
        }
        
        return atual.getDado();
    }
    
    public boolean insereLista(int pos, int dado) {
        if ((pos < 1) || (pos > card_atual+1)) {
            System.out.print("Posição inválida");
            return false;
        }
        
        if (listaVazia() && (pos != 1)) {
            System.out.print("Lista vazia e posição inválida");
            return false;
        }
        
        No elemento = new No();
        elemento.setDado(dado);
        
        if (pos == 1) {
            elemento.setProx(head);
            head = elemento;
        }
        
        if ((pos > 1) && (pos < card_atual+1)) {
            No atual = head;
            No ant = null;
            int cont = 0;

            while (atual != null && cont < pos-1) {
                ant = atual;
                atual = atual.getProx();
                cont++;
            }
            
            if (atual == null) {
                System.out.print("Manipulação incorreta de lista");
                return false;
            }

            elemento.setProx(atual);
            if (ant != null) {
                ant.setProx(elemento);
            } else {
                head = elemento; 
            }
        }
        
        if ((pos > 1) && (pos == card_atual+1)) {
            No atual = head;
            
            while(atual.getProx() != null){
                atual = atual.getProx();
            }
            elemento.setProx(null);
            atual.setProx(elemento);
        }
        
        card_atual++;
        return true;
    }
    
    public int removeLista(int pos) {
        if ((pos < 1) || (pos > card_atual)) {
            System.out.print("Posição inválida");
            return -1;
        }

        if (listaVazia()) {
            System.out.print("Lista vazia");
            return -2;
        }

        No atual = head;
        int dado;

        if (pos == 1) {
            atual = head;
            dado = atual.getDado();
            head = head.getProx();
            atual = null;
        } else {
            No ant = null;
            int cont = 1;

            while (atual != null && cont < pos) {
                ant = atual;
                atual = atual.getProx();
                cont++;
            }

            if (atual == null) {
                System.out.print("Posição inválida");
                return -1;
            }

            dado = atual.getDado();
            ant.setProx(atual.getProx());
            atual = null;
        }

        card_atual--;
        return dado;
    }

    
    public void imprimeLista() {
        No atual = head;
        while (atual != null) {
            System.out.print(atual.getDado() + " ");
            atual = atual.getProx();
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        Lista lista = new Lista();
        
        lista.insereLista(1,10);
        lista.insereLista(1,20);
        lista.insereLista(1,30);
        
        lista.imprimeLista();
        lista.insereLista(2,25);
        lista.imprimeLista();
        
        lista.insereLista(5,5);
        lista.imprimeLista();
        
        lista.removeLista(1);
        lista.imprimeLista();
        
	} 
}
