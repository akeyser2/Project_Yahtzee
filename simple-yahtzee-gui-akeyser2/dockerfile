FROM openjdk:11
WORKDIR /app
COPY . .
RUN apt update 
RUN apt install -y make 
RUN make setup-dependencies 
RUN make build && make test && make lint && make spellcheck
RUN ls -A1 | xargs rm -rf
