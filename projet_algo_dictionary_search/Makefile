CC=gcc -g -std=c99 -Wall -Wextra  #compiler
TEST1=test/test1
TEST2=test/test2
TEST3=test/test3
TEST4=test/test4
TEST5=test/test5
TEST6=test/test6

all: src/fonction_base.o src/iterateur.o test/test1.o test/test5.o test/test6.o
	$(CC) src/fonction_base.c src/fct_iteratifs.c src/fct_recursive.c  src/iterateur.c  test/test1.c -o $(TEST1)
	$(CC) src/fonction_base.c src/fct_iteratifs.c src/fct_recursive.c  src/iterateur.c  test/test2.c -o $(TEST2)
	$(CC) src/fonction_base.c src/fct_iteratifs.c src/fct_recursive.c  src/iterateur.c  test/test3.c -o $(TEST3)
	$(CC) src/fonction_base.c src/fct_iteratifs.c src/fct_recursive.c  src/iterateur.c  test/test4.c -o $(TEST4)
	$(CC) src/fonction_base.c src/fct_iteratifs.c src/fct_recursive.c  src/iterateur.c  test/test5.c -o $(TEST5)
	$(CC) src/fonction_base.c src/fct_iteratifs.c src/fct_recursive.c  src/iterateur.c  test/test6.c -o $(TEST6)

test1:
	./$(TEST1)

test2:
	./$(TEST2)

test3:
	./$(TEST3)

test4:
	./$(TEST4)

test5:
	./$(TEST5)
test6:
	./$(TEST6)


destroy1:
	valgrind ./$(TEST1)

destroy2:
	valgrind ./$(TEST2)

destroy3:
	valgrind ./$(TEST5)

destroy4:
	valgrind ./$(TEST5)

destroy5:
	valgrind ./$(TEST5)

destroy6:
	valgrind ./$(TEST6)


clean:
	rm test/*.o $(TEST1) $(TEST2) $(TEST3) $(TEST4) $(TEST5) $(TEST6)
	# rm src/*.o $(TARGET1) $(TARGET2)
