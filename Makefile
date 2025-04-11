mainclass := Main


SRCS := $(wildcard src/**/*.java src/*.java)
CLASSES := $(patsubst src/%.java, build/%.class, $(SRCS))


all: $(CLASSES)
	java -cp build ${mainclass}


build/%.class: src/%.java
	@mkdir -p $(dir $@)
	javac -d build $< -cp src



release: $(CLASSES) 
	jar cvfe build/rubiks.jar ${mainclass} -C build .

clean: 
	rm build/* -rf
