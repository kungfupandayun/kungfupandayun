# Ensimag 2A POO - TP 2019


all: TestGUI

Vecteur:
	javac -d bin -classpath bin/gui.jar -sourcepath src src/Vecteur.java


Boids:
	javac -d bin -classpath bin/gui.jar -sourcepath src src/Boids.java

Lions:
	javac -d bin -classpath bin/gui.jar -sourcepath src src/Lions.java

Loups:
	javac -d bin -classpath bin/gui.jar -sourcepath src src/Loups.java

Zebre:
	javac -d bin -classpath bin/gui.jar -sourcepath src src/Zebre.java

TestGUI:
	javac -d bin -classpath bin/gui.jar -sourcepath src src/TestGUI.java


# Execution:
# on peut taper directement la ligne de commande :
#   > java -classpath bin TestGUI
# ou bien lancer l'execution en passant par ce Makefile:
#   > make exeIHM
exeGUI:
	java -classpath bin:bin/gui.jar TestGUI

clean:
	rm -rf bin/*.class
