# Week 1 Study Questions & Assignments
You should research and be able to answer the following questions:

## `Tuesday`
- :star: Try the Array sort challenge.

- What are access modifiers? Explain all 4.

- Tell me about Arrays:
   - What is an Array?
   - Can I change the size of an Array?

- What is Abstraction?

- What's the difference between an Abstract class and an Interface?
   - Here's a [great thread on Stack Overflow about this topic](https://stackoverflow.com/questions/479142/when-to-use-an-interface-instead-of-an-abstract-class-and-vice-versa).

- How many classes can a Class extend? In other words, does Java support **multi-inheritance**?

- How many interfaces can a class implement?

- How is memory managed in Java
   - [Here]() is a page about `Gabage Collection`.

- Why are Strings immutable in Java?

- What is the difference between String, StringBuilder, and StringBuffer 

- How many objects are created? What is the output?
```java
String s1 = "hello";
String s2 = "hello";
String s3 = new String("hello");

System.out.println(s1 == s2);       // a
System.out.println(s1.equals(s2));  // b
System.out.println(s1 == s3);       // c
System.out.println(s1.equals(s3));  // d

s1.concat("goodbye");
System.out.println(s1 == s2);       // e
System.out.println(s1.equals(s2));  // f
```

> Here is the answer to the above 2 questions for your reference: <br>
> *Answer*: 2 objects are created.

> *Explanation*: Identical String literals are collected in the "String pool" in an effort to conserve memory. Reference variables will then point to the same String object instance. **Changing the object's state in the String pool will make changes to all references to that String object**. Instead, when a change to a String is made, the JVM makes a new String object, and the reference variable points to the new String in the String pool.

<br>

<hr>

<br>

## `Monday`

- :star: Try this challenge [here](https://github.com/210517-Enterprise/demos/blob/main/week1/FirstJavaProject/src/com/revature/C/scanner/GradeCalcChallenge.java). 

- What is Java and why is it so great?

- What is JRE / JDK / JVM?
   
- How is Java code compiled and executed? In otherwords, explain src code --> byte code --> Target machine's JVM

   - [Here](https://www.dummies.com/programming/java/what-is-a-java-virtual-machine/#:~:text=Generally%2C%20computers%20don't%20execute,in%20a%20slightly%20different%20way.) is a great resource and example.

- What are some useful features in Java 8 (*It's ok if you list them with a brief conceptual overview)*.

- What are the 8 primitive datatypes in Java?

- What is an object?  What is a class?

- What is a wrapper class?

- What is autoboxing / unboxing?

- Tell me the full syntax of the `main` method. What is its purpose?

<br>

### Resources: :mag:
The first place you should be looking for the answers is in Google and your notes from class. Google will most likely lead you to the following websites which I reccomend you frequently refer to for accurate explanations of the concepts above:

- [Stack OverFlow](https://stackoverflow.com/)
- [GeeksForGeeks](https://www.geeksforgeeks.org/)
- [JavaTPoint](https://www.javatpoint.com/)
- YouTube! There are tons of great videos like [this](https://www.youtube.com/watch?v=tppI4lJDnY4)
- [Oracle Documentation](https://docs.oracle.com/javase/8/docs/)
