mvn clean compile
docker build --tag=sumaaes .
docker run -p 9090:9090 -t -i sumaaes