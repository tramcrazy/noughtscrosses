.DEFAULT_GOAL := install

install:
	javac -d ./out/ ./Main.java

run:
	java -cp $(PWD)/out Main

clean:
	rm -r ./out
