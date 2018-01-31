# Robot shop

[add the heroku build status]

Prototype of a Robot store application. It is implemented with Angular5 and Java SpringBoot.

[add the heroku link]


## Prerequisites

- NodeJs (v6.9+, with NPM)
- Java (v1.8)
- Maven (v3.3+).

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