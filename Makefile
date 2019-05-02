all: clean build publish deploy
build:
	./gradlew build
deploy: build
	echo
publish: build
	./gradlew publishToMavenLocal
clean:
	./gradlew clean
