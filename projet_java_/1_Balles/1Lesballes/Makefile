#Équipe 58 en Teide
all: TestBalls

Point:
	javac -d bin -classpath bin/gui.jar -sourcepath src src/Point.java

Balls:
	javac -d bin -classpath bin/gui.jar -sourcepath src src/Balls.java

TestBalls:
	javac -d bin -classpath bin/gui.jar -sourcepath src src/TestBalls.java

# Execution:
# on peut taper directement la ligne de commande :
#   > java -classpath bin TestGUI
# ou bien lancer l'execution en passant par ce Makefile:
#   > make exeIHM
exeGUI:
	java -classpath bin:bin/gui.jar TestBalls

clean:
	rm -rf bin/*.class
