# Producer-Consumer Application

This is a sample application that focuses on the basic idea behind producer and consumer.
Kafka is used for facilitating the message system.

There are two user->

  1. **User1**- Produces messages and sends to the topic1.
            Consumes messages from topic2.
  3. **User2**- Produces messages and sends to the topic2.
            Consumes messages from topic1

<br>

GroupId-1 is for receiving messages from user1
GroupId-2 is for receiving messages from user2

For the setup you must first start the zookeeper server followed by the kafka server-
cmd:

  1. bin\windows\zookeeper-server-start.bat config\zookeeper.properties
  2. bin\windows\kafka-server-start.bat config\server.properties

<br>
After starting both the servers, start the spring boot applications.
Run the Appapplication.java file for both the user1 and user2
The Microservices will be started.

For testing the entire process, you can use postman and hit the following endpoints-

_curl_ --location 'http://localhost:8080/user1/send' \
--data '"I am user 1"'

_curl_ --location --request POST 'http://localhost:8080/user1/read'

_curl_ --location 'http://localhost:8081/user2/send' \
--header 'Content-Type: application/json' \
--data '"I am user2 who are you?"'

_curl_ --location --request POST 'http://localhost:8081/user2/read'

