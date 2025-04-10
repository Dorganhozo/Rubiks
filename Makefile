mainclass=Main


all: build/${mainclass}.class 
	java -cp build ${mainclass}


build/${mainclass}.class: src/${mainclass}.java
	javac -d build -sourcepath src src/${mainclass}.java 



release: build/${mainclass}.class
	jar cvfe build/rubiks.jar ${mainclass} -C build .

clean: 
	rm build/* -rf
