all:
	make clean
	gcj -I src -C src/*.java
	gcj -I src -o bin/SuperJumperServer --main=SuperJumperServer src/*.class
	chmod +x bin/SuperJumperServer
	rm -f bin/*.class
	rm -f src/*.class

clean:
	rm -f src/*.class
	rm -f bin/*
