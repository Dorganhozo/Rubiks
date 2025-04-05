all: Main.class 
	java -cp build Main


Main.class: Main.java
	javac -d build/ *.java
