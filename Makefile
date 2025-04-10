all: build/Main.class 
	java -cp build Main


build/Main.class: src/Main.java
	javac -d build -sourcepath src src/Main.java 



release: build
	jar cvfe build/rubiks.jar Main -C build .

clean: 
	rm build/* -rf
