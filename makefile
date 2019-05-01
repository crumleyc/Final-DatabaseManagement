compile:
	javac -cp lib/* -d bin src/*.java

run:
	java -cp lib/*:bin MainDriver

clean:
	rm ./bin/*.class
