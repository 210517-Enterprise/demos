# Week 2: SQL & JDBC Study Questions
You should research and be able to answer the following questions at the ned of each day:

> - Use Google and [class notes](https://github.com/210517-Enterprise/demos/blob/main/week2/notes/sql.md)<br>
> - The [PostgreSQL Documentation](https://www.postgresqltutorial.com/) is a great resource (and easy to follow).<br>
> - :star: **[W3 Schools SQL Tutorial](https://www.w3schools.com/sql/)**

<br>

## `Rest of the Week`
Go over these questions to prepare for :


1.  Explain what SQL is. What are some SQL RDBMS Vendors? *What is an RDBMS*?
    
2.  Draw a simple ERD for modeling Students and Classes
    
3.  What are the 5 sublanguages of SQL? Which commands correspond to them?
    
4.  What is the difference between DELETE, DROP, and TRUNCATE commands?
    
5.  What are some SQL clauses you can use with SELECT statements?
    
6.  What is the difference between WHERE and HAVING? *`WHERE` is used to filter rows before grouping and `HAVING` is used to exclude records after grouping. Read more [here]( https://www.java67.com/2019/06/difference-between-where-and-having-in-sql.html#ixzz6kwoJQmXd)*.
    
7.  Explain what the ORDER BY and GROUP BY clauses do.
  - Practice [here](https://www.w3schools.com/sql/sql_orderby.asp).
    
8.  Explain the concept of relational integrity.
    
9.  List the integrity constraints.
    
10.  Define the word “schema”.
    
11.  What is a candidate key? What about a surrogate key?
    
12.  What conditions lead to orphan records? (*Think about what happens when we delete from a table that a child table is dependent on because it feautres its Primary keys as foreign keys within the table*)
    
13.  What are some SQL data types?
    
14.  What is normalization? What are the levels? (0 - 3NF)
    
15.  What are the properties a transaction must follow? (*A.C.I.D*)
    
16.  Explain the different isolation levels. What read phenomena do each prevent?
    
17.  What is the difference between joins and set operators?
    
18.  What are the types of joins? Explain the differences.
    
19.  What is a cascade delete?
    
20.  How would you setup a primary key that automatically increments with every INSERT statement?
    
21.  What is the difference between scalar and aggregate functions? Give examples of each

23.  
    
## JDBC Questions
1. What is JDBC?

2. What are the core interfaces / classes in JDBC?

3. What is a stored procedure and how would you call it in Java?

4. What is the difference between Statement and PreparedStatement?

5. Steps to executing an SQL query using JDBC?

6. How to execute stored procedures using JDBC?

7. Which interface is responsible for transaction management? `Connection` Interface.  See this resource here about [JDBC and Transaction management](https://www.javatpoint.com/transaction-management-in-jdbc#:~:text=In%20JDBC%2C%20Connection%20interface%20provides%20methods%20to%20manage%20transaction)


## `Tuesday`
:star: For extra practice try the [Chinook Query Challenge](https://github.com/210517-Enterprise/demos/tree/main/week2/chinook-challenge)
- Explain what SQL is. 
  -  What are some SQL RDBMS Vendors?  
 
- What is an RDBMS?
  - Are all databases relational?  What is the defining chracteristic of a relational database? 

- What are the 5 sublanguages of SQL? Which commands correspond to them?

- What is the difference between `DELETE`, `DROP`, and `TRUNCATE` commands?

- What are some SQL data types?

- Define the word “schema”.

-  What is a cascade delete?
    
- How would you setup a primary key that automatically increments with every `INSERT` statement?

- What is a **constraint**? Give some examples.

- What is referential integrity?
  - Primary Key? Foreign Key?

- What kind of services does AWS offer?

- What is an AWS RDS?

- What does an AWS Security Group do?

- What does `JDBC` stand for?
