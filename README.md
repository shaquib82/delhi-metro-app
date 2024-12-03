Delhi Metro App

The Delhi Metro App is a web-based application that allows users to explore the metro map, find routes between stations, and calculate fares. It is designed to make metro travel convenient and efficient for users.

Features

	•	View the entire Delhi Metro map.
	•	Plan a route by selecting the start and destination stations.
	•	Calculate the shortest route based on distance using Dijkstra’s Algorithm.
	•	View route details including distance, time, interchanges, and cost.
	•	Calculate metro fare dynamically based on the number of stations traveled.

Tech Stack

Frontend

	•	HTML
	•	CSS
	•	Thymeleaf: Used for dynamic web page rendering.

Backend

	•	Spring Boot: Framework for building the backend application.
	•	Java: Programming language used for backend development.
	•	Spring Data JPA: For managing database entities.
	•	Spring MVC: For building web controllers and handling requests.

Database

	•	MySQL: Used to store metro station and route data.

Tools

	•	Maven: For project dependency management.
	•	Git: For version control.
	•	IntelliJ IDEA: IDE used for development.
	•	Postman: For testing backend APIs.

Installation and Setup

	1.	Clone the repository: git clone https://github.com/shaquib82/delhi-metro-app.git
 	2.	Open the project in your IDE (e.g., IntelliJ IDEA).
	3.	Set up the database:
	•	Create a MySQL database named metro_db.
	•	Update the application.properties file with your database credentials.
	4.	Run the application: mvn spring-boot:run
 	5.	Access the application at http://localhost:8080.

Project Structure

	•	src/main/java/com: Contains the main application logic.
	•	controllers: Handles user requests and maps them to services.
	•	services: Contains business logic like route calculation.
	•	repositories: Interfaces for database interactions.
	•	models/entities: Java classes representing database tables.
	•	src/main/resources/templates: Contains Thymeleaf templates for the frontend.
	•	src/main/resources/static: Contains CSS files for styling.

How It Works

	1.	Plan a Route: Users select the start and destination stations, and the application calculates the shortest route using Dijkstra’s Algorithm.
	2.	Check Fare: Based on the route, the app calculates the fare dynamically:
	•	₹20 for the first 3 stations.
	•	₹5 for every 2 additional stations.

Future Enhancements

	•	Add real-time train schedules.
	•	Integrate metro fare policies dynamically based on future updates.
	•	Implement user authentication for personalized features.
 

 
