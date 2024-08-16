# Variáveis
JAVAC = javac
JAVA = java
SRC = FilaComVetor.java
CLASS = FilaComVetor

# Regras
all: compile run

compile:
	$(JAVAC) $(SRC)

run:
	$(JAVA) $(CLASS)

clean:
	rm -f *.class
