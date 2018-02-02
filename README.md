# Robot shop

Prototype of a Robot store application. It is implemented with Angular5 and Java SpringBoot.

The application is deployed on the cloud at [this address](https://bollenma-robot-shop.herokuapp.com/)


## Prerequisites

- NodeJs (v6.9+, with NPM)
- Java (v1.8)
- Maven (v3.3+).

## Build

Execute the command `mvn clean install` at the root of the project (folder `robot-shop`)

## Start the application

- Execute the command `mvn spring-boot:run` in the folder `backend`.
- Browse to [http://localhost:8080/robots](http://localhost:8080/robots)

## Organization of the robot store

### Front-end

The front-end contains 2 main pages: 
- The list of robots to be purchased: `{server_url}/robots`
- The form to create a new robot: `{server_url}/new-robot`

The front-end consumes the back-end REST API to fetch and send the data.
The code is in the module `frontend`, at the path `frontend/src/main/frontend/src/app`
#### List of robots

The code for the list of robots can be found at `components/pages/page-robots-list`.
The component contains the controller, the html template and the styling files.

#### Create a robot

The code for the page of the creation of a robot can be found at `app/components/pages/page-robots-new`.

The form itself is another component found at `components/form-robot`.

### Back-end

The code is in the module `backend`, at the path `backend/src/main/java/src/main/java/arhs/cube/robotshop`

#### REST Controllers

The standard base url to access the REST controllers is `{server_url}/api`

The REST controllers can be found at `restcontrollers`.

The `RobotRestController` class maps the REST URL to the java robot services.
The `RobotModelRestController` class maps the REST URL to the java robot model services.

#### Facades
The facades makes the link between the REST controllers and the Services.
Its main role is to convert the DTO to the Entity, to be used in the Services. 
No DTO object is allowed past the facades.

The facades can be found at `facades`.

#### Services

The facades can be found at `services`.

#### Repositories

The facades can be found at `repositories`.

#### Models

The models can be found at `core`.

It contain the entities `Robot` and `RobotModel` stored into the database.