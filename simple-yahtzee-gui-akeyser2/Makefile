# Really simple wrapper on Maven's command to build, test, & run Yahtzee
# Maven is a package builder and runner tool for Java
#  https://maven.apache.org/

build:
	@echo "Building Yahtzee package - results in target/ directory"
	mvn package -DskipTests

test:
	@echo "Running tests -- not including toString tests"
	mvn test -Dgroups=!ToStringTest

vtest:
	@echo "Running  all tests"
	mvn test

mvn-run:
	@echo "Running Yahtzee main - see pom.xml for arguments passed"
	mvn exec:java

run: build
	@echo "Running Yahtzee main without maven overhead"
	java -jar target/yahtzee-*.jar

fast-run:
	java -jar target/yahtzee-*.jar

spellcheck:
	-codespell src/

setup-dependencies:
	apt update
	apt install -y maven python3-pip checkstyle
	pip3 install codespell

javadoc:
	@echo "Creating javadoc materials"
	@echo "These go into: target/site/apidocs/"
	@echo "Load up index.html to read them"
	-mvn javadoc:javadoc

lint:
	@echo "Running spotless linter to check source files"
	mvn spotless:check

lint-autofix:
	@echo "Autofixing linting errors"
	mvn spotless:apply

build-docker:
	@echo "Building the official CI docker image"
	docker build -t cpsc224yahtzee:latest .

push-docker: build-docker
	@echo "Pushing updated yahtzee docker image to local registry"
	@echo "See: https://www.docker.com/blog/how-to-use-your-own-registry-2/"
	docker tag cpsc224yahtzee:latest localhost:5000/cpsc224yahtzee:latest
	docker push localhost:5000/cpsc224yahtzee:latest
