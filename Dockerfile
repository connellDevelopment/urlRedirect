FROM ubuntu:latest
RUN \
# Update
apt-get update -y && \
# Install Java
apt-get install default-jre -y
ADD ./target/urlshortener-0.0.1.jar urlshortener-0.0.1.jar
EXPOSE 8080
CMD java -jar urlshortener-0.0.1.jar