# Ensimag 2A POO - TP 2019
# ============================

all: TestBoidsSimulator

Vecteur:
	javac -d bin -classpath bin/gui.jar -sourcepath src src/Vecteur.java

Boids:
	javac -d bin -classpath bin/gui.jar -sourcepath src src/Boids.java


BoidsSimulator:
	javac -d bin -classpath bin/gui.jar -sourcepath src src/BoidsSimulator.java

TestBoidsSimulator:
	javac -d bin -classpath bin/gui.jar -sourcepath src src/TestBoidsSimulator.java


# Execution:
# on peut taper directement la ligne de commande :
#   > java -classpath bin TestGUI
# ou bien lancer l'execution en passant par ce Makefile:
#   > make exeIHM
exeGUI:
	java -classpath bin:bin/gui.jar TestBoidsSimulator

clean:
	rm -rf bin/*.class
