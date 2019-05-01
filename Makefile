all: clean build
build:
	./gradlew build
deploy: build
	echo skipping
clean:
	./gradlew clean
