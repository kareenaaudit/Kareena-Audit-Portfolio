PROJECT TITLE: 
Infection Testing Simulation 

PROJECT OVERVIEW:  
This project simulates a simple infection testing system. The focus is on managing and tracking individual test results through a class that represents each test and provides methods for setting and querying its status. This system could be applicable in contexts such as medical diagnostics or epidemiological monitoring.

DATE:
22/Nov/2021

AUTHORS:
Kareena Audit 

COMPONENTS: 

InfectionTest Class

The InfectionTest class is designed to encapsulate the details of an infection test. Each instance of this class represents a single test with the following attributes and methods:

Attributes:

ID: A unique identifier for the test.
Status: A variable indicating if the test status is known and, if so, whether it is positive or negative.

Methods:

getID(): Returns the ID of the test.
isKnown(): Returns true if the status of the test is known, otherwise false.
isPositive(): If the status is known, it returns true for a positive result or false for a negative result. Throws an IllegalStateException if the status is unknown.
setStatus(boolean status): Sets the status of the test to positive (true) or negative (false). Marks the test status as known.
getDetails(): Provides a string detailing the test status:
If the status is unknown, it returns "<ID> status unknown".
If the status is known and negative, it returns "<ID> is negative".
If the status is known and positive, it returns "<ID> is positive".

InfectionTestTester Class

The InfectionTestTester class contains a suite of unit tests designed to validate the functionality of the InfectionTest class. It uses the JUnit framework to ensure the class behaves as expected in various scenarios.

Setup:

A unique ID is generated for each test instance using a random number generator in the setUp() method.
An InfectionTest instance is initialized before each test case.

Tests:

Initial State Tests:
Verify that a new test has the correct ID.
Ensure that a new test has an unknown status.

Status Setting Tests:
Confirm that setting the status to false updates the test to known and indicates a negative result.
Confirm that setting the status to true updates the test to known and indicates a positive result.

Exception Handling:
Validate that querying the positivity status throws an exception if the status is not known.

Details Retrieval:
Verify that the getDetails method provides the correct description based on the test's current status (unknown, positive, or negative).

USAGE:

InfectionTest Class:
Instantiate the class with a unique ID.
Use setStatus to define the test's result.
Use isKnown, isPositive, and getDetails to query the test's status and details.

InfectionTestTester Class:
Run the JUnit tests to ensure the InfectionTest class operates as expected.
