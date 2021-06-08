# Upcoming QC Questions for Monday June 14th:
> Topics: <br>
> - SQL
> - JDBC
> - Avdanced Java
> - Threads


## Advanced

86. What are functional interfaces?
  + Functional interfaces only have one method, and can be used in conjuntion with lambdas
87. What are lambdas?
  + Like anonymous functions, they allow implementation of functional interfaces directly without creating a class
88. What is try-with-resources? What interface must the resource implement to use this feature?
  + Try-with-resources allows for automatically closing resources in a try/catch block using `try(resource) {...}` syntax. Must implement the `AutoCloseable` interface
89. How to make numbers in your code more readable?
  + Use the `_` for numeric literals - must be placed between numbers

90. Which collections cannot hold null values?
  + `HashTable`, `TreeSet`, `ArrayDeque`, `PriorityQueue`
91. If 2 interfaces have default methods and you implement both, what happens?
  + The code will NOT compile unless you override the method. However, the code WILL compile if one interface is implemented further up in the class hierarchy than the other - in this case, the closest method implementation in the hierarchy will be called
92. If 2 interfaces have same variable names and you implement both, what happens?
  + The code will compile unless you make a reference to the variable (this is an ambiguous reference). You must explicitly define the variable by using the interface name: `int a = INTERFACENAME.a;`
93. Why does `HashTable` not take `null` key?
  + The hash table hashes the keys given as input, and the `null` value cannot be hashed
