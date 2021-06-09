# Aestivate

## Project Description
Aestivate is a basic Java ORM that works through inheritance. It Currently works with
just postgresql, but this may expand in the future.

Currently Aestivate takes in an aestivate.xml to configure database connection.

## Technologies Used
* JDBC
* Java
* Git
* Github
* Reflection

---------------------
## Features
Objects that extend the model class can:  
* Create a new table based on the child class  
* Drop then create a table of a child class  
* Save an instance of an object as an entry  
* Update an entry in the class database  
* Delete an instance from the database  
* Find entries in the class database that satisify conditions on given columns with given values  
* Check if an entry exists of an object with then same primary key  

-------------------

## SETUP

For Aestivate to work, it requires an aestivate.xml file in the resources directory.
This file informs Aestivate of the database type it is connecting to (eg. postgresql),
the url, login, and password. In addition it takes arguments for the minimum number of
connections to idle, the max number of connections to idle, and the max open prepared
statements there can be. An Example of the xml file is provided here:

-----------------------------------------------
~~~ xml
<?xml version="1.0" encoding="UTF-8" ?>
<Configuration>

    <!-- Database connection info -->
    <Database name = "postgresql">
        <Url url="url"/>
        <Login login="login name"/>
        <Password password="password/>
        <MinIdle minIdle="5"/>
        <MaxIdle maxIdle="10"/>
        <maxOpenPreparedStatements maxOpen="100"/>
    </Database>

</Configuration>
~~~
--------------------------------------------------------
--------------------------------------------------------
## Usage
The way to access the database is by extending the BaseModel. For Example, public class example extends BaseModel<example>{}

This gives two abstract methods to fill out: setColumns and setTableName. setTableName is so that the class extending the model  
can be used with existing databases. Return a String that is the same as the table name you want to associate the class with. If  
you don't care about the name of the table, return null and the class will handle it for you.  

Example:
    @Override
    protected String setTableName() {
        return "app_users";
    }
OR
    @Override
    protected String setTableName() {
        return null;
    }

The other, setColumns, is a little more involved. This is the method that is used to get information about the columns in  
the table, and the corresponding fields in the class. The return type is a ColumnField[]. A ColumnField holds a string  
column name, a string type, and an SQLConstraint, which is an enum type.  

The array of columns should have at one Primary_Key constraint. There is not extensive testing, to it is unknown what  
happens if there are none or more than 1 given.  
Another limitation is that each column can only be given one constraint. For the most part this doesn't matter if the object  
is connecting to an already existing database, but it could have unforseen side effects if the column constraints don't line  
up with the table's constraints.  

An example of the setColumns():
~~~ java
    @Override
    protected ColumnField[] setColumns() {  
        ColumnField[] columns = new ColumnField[5];  
        columns[0] = new ColumnField("id", "serial", SQLConstraints.PRIMARY_KEY);  
        columns[1] = new ColumnField("username", "varchar", SQLConstraints.UNIQUE);  
        columns[2] = new ColumnField("password", "varchar", SQLConstraints.NOT_NULL);  
        columns[3] = new ColumnField("firstName", "varchar", SQLConstraints.NOT_NULL);  
        columns[4] = new ColumnField("lastName", "varchar", SQLConstraints.NOT_NULL);  
        return columns;
    }
~~~
-----------------------------------------------------------
Reminder, does not work if the Database name is different than "postgresql".

Future plans are to make the variable types of the allowed columns to be more robust, logging, and unit testing.

Stretch goals are to implement foreign keys to connect classes, and even further down the line is
the ability to connect to multiple databases at once.
