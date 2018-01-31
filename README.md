# Robot shop

[add the heroku build status]

Prototype of a Robot store application. It is implemented with Angular5 and Java SpringBoot.

[add the heroku link]


## Prerequisites

- NodeJs (v6.9+, with NPM)
- Java (v1.8)
- Maven (v3.3+).

## Installation

### Front-end

Execute those commands in the folder `client` to build the front-end:
 1. `npm install`

### Back-end

Execute the command `mvn install` in the folder `src/main/java` to download all dependencies for the back-end. 

## Build the application

Execute those commands to build the application:

1. `node_modules/.bin/ng` (or `node_modules/.bin/ng.cmd` for windows) in the `client` folder;
2. `mvn install` in the `src/main/java` folder.

Then, a jar is produce in `src/man/java/target` folder.

## Run the application

Once the jar is generated, `java -jar petstore.jar`, with `petstore.jar` the path to the generated jar, start the server.

It is also possible to run `mvn spring-boot:run` in `src/man/java` folder to start it.

It can be accessed with the URL [http://localhost:4200/](http://localhost:4200/).
Localhost can be replaced with any address referring to the computer launching the jar.

## Organization of the robot store

### Front-end

The front-end contains 2 main pages: 
- The list of robots to be purchased: `http://localhost:4200/robots`
- The form to create a new robot: `http://localhost:4200/new-robot`

The front-end consumes the back-end REST API to fetch and send the data.

#### List of robots

The code for the list of robots can be found at `client/src/app/components/pages/page-robots-list`.
The component contains the controller, the html template and the styling files.

#### Create a robot

The code for the page of the creation of a robot can be found at `client/src/app/components/pages/page-robots-new`.

The form itself is another component found at `client/src/app/components/form-robot`.

### Back-end

#### REST Controllers

The standard base url to access the REST controllers is `http://localhost/8080/api`

The REST controllers can be found at `src/main/java/src/main/java/arhs/cube/robotshop/restcontrollers`.

The `RobotRestController` class maps the REST URL to the java robot services.
The `RobotModelRestController` class maps the REST URL to the java robot model services.

#### Facades
The facades makes the link between the REST controllers and the Services.
Its main role is to convert the DTO to the Entity, to be used in the Services. 
No DTO object is allowed past the facades.

The facades can be found at `src/main/java/src/main/java/arhs/cube/robotshop/facades`.

#### Services

The facades can be found at `src/main/java/src/main/java/arhs/cube/robotshop/services`.

#### Repositories

The facades can be found at `src/main/java/src/main/java/arhs/cube/robotshop/repositories`.

#### Models

The models can be found at `src/main/java/src/main/java/arhs/cube/robotshop/core`.

It contain the entities `Robot` and `RobotModel` stored into the database.