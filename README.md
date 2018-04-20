MarsRoverApp
============

App designed to model movements of Mars rovers moving around a 
rectangular plateau on Mars.

Usage Instructions
------------------

Requirement: Java Runtime Environment 1.8

Run the jar file with
```java -jar <path to jar file>```
Follow the on screen instructions for inputting data for the rover and plateau.

The first line you input will be two integers, separated by a space representing 
the north-east corner of the plateau, where 0,0 is the south west corner. E.g. `5 5`

Next you will enter information for the rovers on the plateau. For each rover enter two lines:

First a line of two integers and a letter representing a compass direction, e.g. `3 4 N`

Then on the next line enter a string of the symbols either 'M', 'L', or 'R', representing instructions 
for the rover. L and R will rotate the rover left and right 90 degrees respectively. M will move the 
rover forward in it's current direction.

Once you have entered all rover instructions, press enter again to print the final position and heading 
of each rover.

Example Input:
```
5 5
1 2 N
LMLMLMLMM
3 3 E
MMRMMRMRRM
```

Example Output:
```
1 3 N
5 1 E
```

Build Instructions
------------------

Requirements: JDK 1.8, Maven 3

Run `mvn clean package` to run tests and build jar file.

Run `mvn test` to run tests

Testing Libraries Used
----------------------
- JUnit
- AssertJ fluent assertions
- Mockito mocking library