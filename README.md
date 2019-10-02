# Project instructions
mvn clean install

# Run on local machine
java -jar ./target/urlshortener-0.0.1.jar

# Docker
Create image:
> docker build . -t url-rewrite-image

Confirm image creation:
> docker images

Run image
> docker run -t -p 8080:8080 --name url-shorten-container url-rewrite-image

Check image status
> docker ps -a

Stop image
> docker stop url-shorten-container