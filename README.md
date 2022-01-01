# jmscommunications
Message producer. For consumer see jmscommunicationsb

These two projects (jmscommunications and jmscommunicationsb) set the JMS PRODUCER CONSUMER example under Spring Boot.
jmscommunications ---> The Producer.
jmscommunicationsb ---> The Consumer.

To be used with the DOCKER IMAGE found at: https://github.com/vromero/activemq-artemis-docker

docker run -it --rm -p 8161:8161  -p 61616:61616 vromero/activemq-artemis
