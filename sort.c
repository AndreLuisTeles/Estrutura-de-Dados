#include <stdio.h>
#include <stdlib.h>
#include <time.h>

void selectionSort(int conjunto[], int n) { //Feito em conformidade com os materiais da disciplina
    int i, j, auxiliar, menor;

    for (i = 0; i < n-1; i++) {
        menor = i; // Não é recomenddao declarar "menor" como zero pois podem haver elementos negativos
        for (j = i+1; j < n; j++) {
            if (conjunto[j] < conjunto[menor]) //Se um elemento em questão for menor que o atual menor...
                menor = j; //... o menor receberá o indice de tal elemento
        }
        if (conjunto[i] != conjunto[menor]) { //Código padrão para troca de valores em ordem crescente
            auxiliar = conjunto[i]; //Sem o auxiliar, a posição que receberá o menor valor perderá...
            conjunto[i] = conjunto[menor]; //... o dado já armazenado
            conjunto[menor] = auxiliar;
        }
    }
}

void insertionSort(int conjunto[], int n) { //Feito também de acordo com os materiais da disciplina
    int i, j, pivor; //Pivor é o elemento que será comparado com os anteriores para determinar sua posição
    for (i = 1; i < n; i++) { //Comparação é feita de 1 a n-1
        pivor = conjunto[i];
        j = i - 1;

        while (j >= 0 && conjunto[j] > pivor) { //Compara o pivor com os anteriores e poderia ser feito com...
            conjunto[j + 1] = conjunto[j]; // ...for e ifs
            j = j - 1; //Decrementa o contador até todo o conjunto numérico seja analizado 
        }
        conjunto[j + 1] = pivor;
    }
}

void mostraOrdenamento(int conjunto[], int escopo) { //Função que optei por não usar
    int i;
    for (i = 0; i < escopo; i++)
        printf("%d ", conjunto[i]);
    printf("\n"); 
}

int lerArquivo(const char *filename, int conjunto[], int escopo_maximo) { //Leitura padrão de arquivo
    FILE *file = fopen(filename, "r"); 
    if (!file) {
        perror("Erro ao abrir o arquivo");
        exit(EXIT_FAILURE);
    }

    int i = 0;
    while (i < escopo_maximo && fscanf(file, "%d", &conjunto[i]) == 1) {
        i++;
    }
    fclose(file);
    return i;
}

void ordenaConjunto(const char *filename, int tamanho) { //Esta função serve para evitar o uso excessivo...
    int temporizador1, temporizador2; //... das outras chamadas de função
    int conjunto1[tamanho], conjunto2[tamanho];

    int n = lerArquivo(filename, conjunto1, tamanho); 
    for (int i = 0; i < n; i++) {
        conjunto2[i] = conjunto1[i]; 
    }

    clock_t start, end;
    double cpu_time_used;

    // Selection Sort
    start = clock(); //Processo padrão para obter tempo de programa
    selectionSort(conjunto1, n);
    end = clock();
    cpu_time_used = ((double) (end - start)) / CLOCKS_PER_SEC;
    temporizador1 = cpu_time_used;
    printf("   Selection Sort Time: %f seconds\n", cpu_time_used);

    // Insertion Sort
    start = clock(); //Idem
    insertionSort(conjunto2, n);
    end = clock();
    cpu_time_used = ((double) (end - start)) / CLOCKS_PER_SEC;
    temporizador2 = cpu_time_used;
    printf("   Insertion Sort Time: %f seconds\n", cpu_time_used);

    if (temporizador1 >= temporizador2) {
        printf("Insertion Sort é mais rápido!\n"); //Por base, o Insertion Sort é mais rápido
    } else {
        printf("Selection Sort é mais rápido!\n"); //Não houve um único caso de teste realizado em que...
    }                                               //... o Selection Sort fosse mais rápido
    printf("\n");
}

int main() {
    const char *filename10001 = "num.1000.1.in";
    printf("ARQUIVO num.1000.1.in\n");
    ordenaConjunto(filename10001,1000); 

    const char *filename10002 = "num.1000.2.in";
    printf("ARQUIVO num.1000.2.in\n");
    ordenaConjunto(filename10002,1000); 

    const char *filename10003 = "num.1000.3.in";
    printf("ARQUIVO num.1000.3.in\n");
    ordenaConjunto(filename10003,1000); 

    const char *filename10004 = "num.1000.4.in";
    printf("ARQUIVO num.1000.4.in\n");
    ordenaConjunto(filename10004,1000); 

    printf("--------------------\n");

    const char *filename100001 = "num.10000.1.in";
    printf("ARQUIVO num.10000.1.in\n");
    ordenaConjunto(filename100001,10000); 

    const char *filename100002 = "num.10000.2.in";
    printf("ARQUIVO num.10000.2.in\n");
    ordenaConjunto(filename100002,10000); 

    const char *filename100003 = "num.10000.3.in";
    printf("ARQUIVO num.10000.3.in\n");
    ordenaConjunto(filename100003,10000); 

    const char *filename100004 = "num.10000.4.in";
    printf("ARQUIVO num.10000.4.in\n");
    ordenaConjunto(filename100004,10000); 

    printf("--------------------\n");

    const char *filename1000001 = "num.100000.1.in";
    printf("ARQUIVO num.100000.1.in\n");
    ordenaConjunto(filename1000001,100000); 

    const char *filename1000002 = "num.100000.2.in";
    printf("ARQUIVO num.100000.2.in\n");
    ordenaConjunto(filename1000002,100000); 

    const char *filename1000003 = "num.100000.3.in";
    printf("ARQUIVO num.100000.3.in\n");
    ordenaConjunto(filename1000003,100000); 

    const char *filename1000004 = "num.100000.4.in";
    printf("ARQUIVO num.100000.4.in\n");
    ordenaConjunto(filename1000004,100000); 
 
    return 0;
}
