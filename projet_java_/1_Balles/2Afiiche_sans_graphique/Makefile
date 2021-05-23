all: TestBallsSimulator

Point:
	javac -d bin -classpath bin/gui.jar -sourcepath src src/Point.java

Balls:
	javac -d bin -classpath bin/gui.jar -sourcepath src src/Balls.java

BallsSimulator:
	javac -d bin -classpath bin/gui.jar -sourcepath src src/BallsSimulator.java

TestBallsSimulator:
	javac -d bin -classpath bin/gui.jar -sourcepath src src/TestBallsSimulator.java
# Execution:
# on peut taper directement la ligne de commande :
#   > java -classpath bin TestGUI
# ou bien lancer l'execution en passant par ce Makefile:
#   > make exeIHM
exeGUI:
	java -classpath bin:bin/gui.jar TestBallsSimulator

clean:
	rm -rf bin/*.class
