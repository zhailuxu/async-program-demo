Akka Remote Example in Java
=========================

## Introduction
This project was made while testing the Akka Remoting features.
It contains three modules; a Client, a Server and a LoggerEnvironment. The two types of Akka Remoting are tested; Creation and Lookup.

##### Client
The client uses remote lookup to get an `ActorSelection` of a Remote Actor (running on the Server.) and sends a message to it. 

##### Server
The server creates a `CalculatorActor` and handles incoming messages.  
The server also creates a `LoggingActor` which is (remotely) deployed on the `LoggerEnvironment`.

##### LoggerEnvironment
Essentially nothing more than a container. The server will create an actor on this environment.

## How to run
You can run the program like every ordinary Java main program. Make sure you have `mvn clean install`ed the project before to get the Akka dependency.
It's important to run the projects in the following order:

1. LoggerEnvironment
2. Server
3. Client
