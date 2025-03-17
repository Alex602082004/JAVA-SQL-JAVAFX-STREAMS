# JAVA-SQL-JAVAFX-STREAMS

## Non-functional Requirements
- The requirements defined in Assignment A2 remain unchanged.

## Functional Requirements
- Implement a new Repository that allows storing domain entities in an SQL database.

- The decision on which repository type is used will be made based on the settings.properties file, implemented in Assignment A3.

- The SQL-based repository implementation must generate and save 100 pseudo-random entities in the database. You can use lists of names/surnames, functions to generate dates and random numbers, as well as tools like Java Faker to generate this data.

- Implement a graphical user interface (GUI) using JavaFX for the problem initially selected in Assignment A2.

- Keep the command-line interface (CLI) source code.

- The application must be able to run in both CLI and GUI mode with only minor source code modifications (e.g., commenting/uncommenting a class instantiation).

- Implement the following reports using Java 8 Streams at the service layer of the application, for each problem:
### Problem 2: Cake Order Management
  - Number of cakes ordered per day

  - Display dates for which orders were placed, along with the total number of cakes ordered on each date.
  - Sort in descending order by the number of cakes ordered.
  - Number of cakes ordered per month

  - Display each month of the year and the total number of cakes ordered in that month.
  - Sort in descending order by the number of cakes ordered.
  - Most frequently ordered cakes

  - Display all details of each cake, along with the total number of orders for it.
  - Sort in descending order by the number of orders per cake.
      
  Note: A single order may include multiple cakes of different types.

## Bonus (0.1p) – Application Launch Mode Setting
- Add a new setting in the settings.properties file to specify whether the application starts in CLI or GUI mode.
- Modifying this setting in the file should change the application's startup mode without requiring code modifications.
