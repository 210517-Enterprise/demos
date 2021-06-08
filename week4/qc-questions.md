# Upcoming QC Questions for Monday June 14th:
> Topics: <br>
> - SQL
> - JDBC
> - Avdanced Java
> - Reflection
> - Threads

## JDBC
1. What is JDBC?
    + A Java API used to execute queries on various databases. Uses JDBC drivers to connect with the database

2. What are the core interfaces / classes in JDBC?
    + `DriverManager`, `Connection`, `Statement`, `PreparedStatement`, `CallableStatement`, `ResultsSet`

3. What is a stored procedure and how would you call it in Java?
    + A stored procedure is an executable block of code that is written in PL/SQL and stored in the Oracle database. A stored procedure is called from a Java class using a CallableStatement object. When the procedure is called, its name and any relevant parameters are sent over the JDBC connection to the DBMS, which executes the procedure and returns the results (if applicable) via the connection.

4. What is the difference between Statement and PreparedStatement?
    + PreparedStatements are pre-compiled by the JVM. The database doesn't have to compile the SQL each and every time it is executed. PreparedStatement can be parameterized, which can make the SQL more readable. Furthermore, PreparedStatement will properly escape reserved characters to prevent SQL injection attacks.

5. Steps to executing an SQL query using JDBC?
    + 1) Register the driver using `.forName()` (or let `DriverManager` detect and load automatically from classpath)
    + 2) Create the connection (`DriverManager.getConnection(url,username,password)`)
    + 3) Create a statement for executing the SQL query (`Statement st = conn.createStatement()`);
    + 4) Execute the SQL query (`ResultSet rs = st.executeQuery(String sql)`)
    + 5) Use `ResultSet` to get values returned (`rs.getInt(1)`, etc)
    + 6) Close the connection (`conn.close()`) 

6. How to execute stored procedures using JDBC?
    + Use the `Callable` statement interface

7. Which interface is responsible for transaction management?
    + The `Connection` interface - can `commit`, `rollback`, etc

<br>

## Reflections API

1. What is Reflection API?
     + The first component of the Reflection API is the mechanism used to fetch information about a class. This mechanism is built into the class named Class. The special class Class is the universal type for the meta information that describes objects within the Java system. Class loaders in the Java system return objects of type Class. Up until now the three most interesting methods in this class were:
    + `.forName()`, which would load a class of a given name, using the current class loader
    + `.getName()`, which would return the name of the class as a String object,which was useful for identifying object references by their class name
    + `.newInstance()`, which would invoke the null constructor on the class (if it exists) and return you an object instance of that class of object
    + To these three useful methods the Reflection API adds some additional methods to class Class. These are as follows:
    + `getConstructor`, `getConstructors`, `getDeclaredConstructor`
    + `getMethod`, `getMethods`, `getDeclaredMethods`
    + `getField`, `getFields`, `getDeclaredFields`
    + `getSuperclass`
    + `getInterfaces`
    + `getDeclaredClasses`
 
 2. What is a Dynamic Proxy?
 
 <br>
 
## Design patterns
1. What are Singleton / Factory design patterns?
     + Singleton - allows for creation of only 1 object. Method for retrieving object returns reference to the same object in memory. Implement via private constructor

    + Factory - abstracts away instantiation logic, usually used in conjunction with singleton pattern

<br>

## Advanced Java
1. What are functional interfaces?
    + Functional interfaces only have one method, and can be used in conjuntion with lambdas

2. What are lambdas?
  + Like anonymous functions, they allow implementation of functional interfaces directly without creating a class

3. What is try-with-resources? What interface must the resource implement to use this feature?
    + Try-with-resources allows for automatically closing resources in a try/catch block using `try(resource) {...}` syntax. Must implement the `AutoCloseable` interface

4. How to make numbers in your code more readable?
    + Use the `_` for numeric literals - must be placed between numbers

5. Which collections cannot hold null values?
    + `HashTable`, `TreeSet`, `ArrayDeque`, `PriorityQueue`

6. If 2 interfaces have default methods and you implement both, what happens?
    + The code will NOT compile unless you override the method. However, the code WILL compile if one interface is implemented further up in the class hierarchy than the other - in this case, the closest method implementation in the hierarchy will be called

7. If 2 interfaces have same variable names and you implement both, what happens?
   + The code will compile unless you make a reference to the variable (this is an ambiguous reference). You must explicitly define the variable by using the interface name: `int a = INTERFACENAME.a;`

8. Why does `HashTable` not take `null` key?
   + The hash table hashes the keys given as input, and the `null` value cannot be hashed
