# Ensimag 2A POO - TP 2019/2020
# ============================
#
all: TestSimulator

GrilleSimulator:
	javac -d bin -classpath bin/gui.jar -sourcepath src src/GrilleSimulator.java

TestSimulator:
	javac -d bin -classpath bin/gui.jar -sourcepath src src/TestGui.java

# Execution:
# on peut taper directement la ligne de commande :
#   > java -classpath bin TestGUI
# ou bien lancer l'execution en passant par ce Makefile:
#   > make exeIHM
exeTestSimulator:
	java -classpath bin:bin/gui.jar TestGui

clean:
	rm -rf bin/*.class
