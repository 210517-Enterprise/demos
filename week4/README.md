# Week 4: Multithreading & HTTP

## `Tuesday`
1. What is multi-threading?
    + Handling multiple threads / paths of execution in your program.

2. In what ways can you create a thread?
   + By extending the Thread Class or by implementing the `Runnable` Interface. You must call Thread's `.start()` method to start it as a new thread of execution.

3. Lifecycle of a thread
    + When created, in NEW state.
    + When `.start()` is called, it goes to RUNNABLE state.
    + When `.run()` is called, goes to RUNNING state.
    + If `.sleep()` or `.wait()` is called, will go to WAITING.
    + If dependent on another thread to release a lock, it will go to BLOCKED state.
    + When finished executing, will be in DEAD state and cannot be restarted.

4. What is deadlock?
    + When two or more threads are waiting on locks held by the others, such that no thread can execute

5. What is synchronized keyword?
    + Only allowing one thread access to the method or variable at a time - enforces thread-safety

- Here is a [list of possible QC questions that will be asked on Monday]().
    + Topics: SQL, Threads, Advanced Java, Functional Interfaces, HTTP
  


## `Monday`
- [Project 1 Assignment](https://github.com/210517-Enterprise/demos/blob/main/week4/project-1.md): 🕑: *Due Monday June 28th, 2021*
  - Take some time to practice [Git SCM Workflow](https://github.com/210517-Enterprise/demos/tree/main/week4/git-workshop)  
- QC Audit at 2pm EST
- Begin Multithreading Tomorrow, Tuesday

