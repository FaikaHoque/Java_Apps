# Java Grep App

## Introduction

The app searches for a text pattern recursively in a given directory and output matched lines to a file. 

## Usage

### USAGE: regex rootPath outFile

### Description

* regex - a special text string for describing a search pattern
* rootPath - root directory path
* outFile - ouput file name

### Example

The app takes three arguments. For example, we want to find all lines that contain the text "data" from all the files in a given directory named bootcamp and write down the matched lines in a file named grep.

*data* ~/rootpath/bootcamp /tmp/grep.out

## Design and Implementation

### Pseudocode

matchedLines = [ ]

for file in listFiles(rootDir)

   for line in readLined(File)

​         if containsPattern(line)

​            matchedLines.add(line)

writetoFile(matchedLines)

### Grep Libraries 

*import java.io.File*

*import java.io.IOException*

*import java.util.List*

File - an abstract representation of file and directory pathnames.

IOException - generate an exceptions for failure or interruption of I/O operations.

List - an ordered collection of elements. 



# Java JDBC App

## Introduction

Java Database Connectivity (JDBC) is the java API that manages connecting to a database, executing queries and commands and handling result sets obtained from the database. 

## Usage

### CRUD    

When we are building JDBC API, we want our models to perform basic types of functionalities. The model must be able to Create, Read, Update and Delete resources from the database. These functions are referred by the acronym CRUD. 

* Creating data - INSERT statement
* Read data - SELECT statement
* Update data - UPDATE statement
* Delete data - DELETE statement 

### USAGE : Connection **Statement** **ResultSet**

### Description

* Connection - method that returns a connection with database
* Statement - statement from the connection 
* ResultSet - create an instance to pass the query

### Example 

Query the database : Suppose we have a customer table with four columns and 100 rows and we want to count the number of customer from Java platform. We create a connection and create a statement and execute the query. 

resultset = connection.statement.executeQuery("SELECT COUNT(*) from customer")

## Design and Implementation

Following figure shows an  work flow overview of JDBC in the Java persistence layer. 

### Work Flow Diagram

![JDBCflow diagram-2](C:\Users\User\Documents\JDBCflow diagram-2.jpg)

### Pseudocode

​        // Create a database connection

1. Connection connection = DriverManager.getConnection(url, databasename, username, password)

   // Create a statement

2. Statement statement = connection.createStatement()

   // Execute the statement query

3. ResultSet resultset = statement.executeQuery("Select * from table_name")

### JDBC Libraries

In our Java program, we can include the JDBC libraries : 

* *import java.sql.DriverManager*
* *import java.sql.Connection*

* *import java.sql.SQLException*

* *import java.sql.ResultSet*

* *import java.sql.Statement*

DriverManager - a class that interacts with the driver for creating connections.

Connection - represents the connection to the database.

SQLException - handles SQL errors between the java application and the database.

ResultSet - the response from the database in a logical "tabular" form.

Statement - the representation SQL to be executed against the database.

# Twitter CLI App

## Introduction

Twitter CLI App can post, read and delete tweets.

## Usage

### USAGE: post "tweet_text" "latitude:longitude"

### Description: 

Create a tweet with a geotag and output the created tweet object in JSON format.

### Arguments: 

tweet_text - tweet_text cannot exceed 140 characters

latitude:longitude - Geo location

## Design and Implementation

### Overview of App Architecture

Following diagram shows different layers of the app cycle

![Twitterpost-1](C:\Users\User\Documents\Twitterpost-1.jpg)

### Component Design

Description of high level component design diagram

* HttpHelper : Make HTTP requests (POST, GET, DEL) and handle http protocols
* DAO : Data Access Object which handles tweet object and http response in JSON format. it depends on HttpHelper.
* Service : Business logic is handled in service layer. It depends on DAO and manipulate twitter object according to application requirements (select certain fields when showing tweet object)
* Runner : Parse user CLI inputs and calls the corresponding service methods.

### Pseudocode

// Create components

HttpHelper httpHelper = new ApacheHttpHelper()

CrdDao dao = new TwitterRestDao (httpHelper)

TwitterService service = new TwitterServiceImp(dao)

// Runner

TwitterCLIRunner runner = new TwitterCLIRunner(service)

//Run the application

runner.run(args)



