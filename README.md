# Overview 

This Java_apps package contains three apps. Twitter App, Grep App and JDBC. 

# Twitter CLI App

## Introduction

Twitter CLI App can create, read and delete tweets on Twitter from command line. The app is designed based on existing protocols 8of Twitter REST API and performed three main features: post, delete and find tweet from Twitter website. 

## Usage

#### First Feature: Create a tweet on timeline 

`Usage: TwitterCLI post "tweet_text" "latitude:longitude"`

##### Description: 

Create a tweet with a geotag and output the created tweet object in JSON format.

##### Arguments: 

`tweet_text` - Tweet_text cannot exceed 140 characters.

`latitude:longitude` - Geo location.

#### Second Feature: Show a tweet by ID

`Usage: TwitterCLI show tweet_id [field1, fields2]`

##### Description:

Lookup a tweet by ID and print tweet object in JSON format. Show all fields in JSON document if `[field1, fields2]` empty. Otherwise, only show user specified fields in the JSON document. 

##### Arguments: 

`tweet_id` - Tweeter id (for example, 1097607853932564480). Same as id_str in the tweet object.

#### Third Feature: Delete a tweet by tweet ID

`Usage: TwitterCLI delete tweet_ids`

##### Description:

Delete a list of tweets by id. Output deleted tweet id and print deleted tweet object. 

##### Arguments:

`tweet_ids` - A comma- separated list of tweets.

## Design and Implementation

#### Overview of App Architecture

Following diagram shows different layers of the twitter app development cycle.

![Twitterpost-1](https://user-images.githubusercontent.com/51927068/61220086-917a2900-a6e3-11e9-9e0f-4cd0a1269183.jpg)

#### Tweet Object Description

The official tweet object is a huge document. In this application, we created a simplified `tweet object`with specific attributes.

```
{
"created_at":"Mon Feb 18 21:24:39 +0000 2019"
"id":1097607853932564480,
"id_str":"1097607853932564480",
"text":"test with loc223",
"entities":{
"hashtags":[ ], 
"user_mentions":[ ] 
},
"coordinates":null,
"retweet_count":0,
"favorite_count":0,
"favorited":false,
"retweeted":false
}
```

#### Component Design

Description of high level components for the twitter app implementation.

- `HttpHelper` : Make HTTP requests (POST, GET, DEL) and handle http protocols.
- `TwitterRestDAO` : Data Access Object which handles tweet object and http response in JSON format. it depends on `HttpHelper`.
- `TwitterService` : Business logic is handled in service layer. It depends on DAO and manipulate twitter object according to application requirements (select certain fields when showing tweet object).
- `TwitterCLIRunner` : Parse user CLI inputs and calls the corresponding service methods.
- `TwitterCLI` : Create above components and start the application.

#### Pseudocode

# Java Grep App

## Introduction

The app searches for a text pattern recursively in a given directory and output matched lines to a file. 

## Usage

##### `Usage: regex rootPath outFile`

### Description

* `regex` - a special text string for describing a search pattern
* `rootPath` - root directory path
* `outFile` - ouput file name

### Example

The app takes three arguments. For example, we want to find all lines that contain the text "data" from all the files in a given directory named bootcamp and write down the matched lines in a file named grep.

`data ~/rootpath/bootcamp /tmp/grep.out`

## Design and Implementation

### Pseudocode

```
matchedLines = [ ]

for file in listFiles(rootDir)

   for line in readLined(File)

​         if containsPattern(line)

​            matchedLines.add(line)

writetoFile(matchedLines)
```



### Grep Libraries 

`import java.io.File`

`import java.io.IOException`

`import java.util.List`

**File** - an abstract representation of file and directory pathnames.

**IOException** - generate an exceptions for failure or interruption of I/O operations.

**List** - an ordered collection of elements. 



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

* `Connection` - method that returns a connection with database
* `Statement` - statement from the connection 
* `ResultSet` - create an instance to pass the query

### Example 

Query the database : Suppose we have a customer table with four columns and 100 rows and we want to count the number of customer from Java platform. We create a connection and create a statement and execute the query. 

`resultset = connection.statement.executeQuery("SELECT COUNT(*) from customer")`

## Design and Implementation

Following figure shows an  work flow overview of JDBC in the Java persistence layer. 

### Work Flow Diagram

![JDBCflow diagram-2](C:\Users\User\Documents\JDBCflow diagram-2.jpg)

### Pseudocode

```
        // Create a database connection

1. Connection connection = DriverManager.getConnection(url, databasename, username, password)

   // Create a statement

2. Statement statement = connection.createStatement()

   // Execute the statement query

3. ResultSet resultset = statement.executeQuery("Select * from table_name")

```

### JDBC Libraries

In our Java program, we can include the JDBC libraries : 

* `import java.sql.DriverManager`
* `import java.sql.Connection`

* `import java.sql.SQLException`

* `import java.sql.ResultSet`

* `import java.sql.Statement`

**DriverManager** - a class that interacts with the driver for creating connections.

**Connection** - represents the connection to the database.

**SQLException** - handles SQL errors between the java application and the database.

**ResultSet** - the response from the database in a logical "tabular" form.

**Statement** - the representation SQL to be executed against the database.

