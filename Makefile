.DEFAULT_GOAL := build-run

setup:
	./gradlew wrapper --gradle-version 8.5

clean:
	./gradlew clean

build:
	./gradlew clean build

install:
	./gradlew clean install

run-dist:
	./build/install/app/bin/app -h

run:
	./gradlew run

test:
	./gradlew test


check-deps:
	./gradlew dependencyUpdates -Drevision=release


build-run: build run

.PHONY: build