
all: TestGUI

BallsSimulator:
	javac -d bin -classpath bin/gui.jar -sourcepath src src/BallsSimulator.java

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
