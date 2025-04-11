mainclass := Main


SRCS := $(wildcard src/**/*.java src/*.java)
CLASSES := $(patsubst src/%.java, build/%.class, $(SRCS))


all: $(CLASSES)
	java -cp build ${mainclass}


build/%.class: src/%.java
	@mkdir -p $(dir $@)
	javac -source 8 -target 8 -d build $< -cp src



release: $(CLASSES) 
	jar cvfe build/rubiks.jar ${mainclass} -C build .

clean: 
	rm build/* -rf
