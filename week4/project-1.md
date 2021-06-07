# Project 1 - Custom Object Relational Mapping Framework
>***:clock2: Due Monday June 28th, 2021***

## Description

Your first project will be to create a custom object relational mapping (ORM) framework. This framework will allow for a simplified and SQL-free interaction with the relational data source. The requires of the project are purposefully vague, the intention is to allow for you to be creative in your implementation of this framework. There are many ways that this task can be approached, and you are encouraged to explore existing Java ORM implementations in order to get some inspiration. Some suggested features that your ORM can provide are:

1. A public API that can be added as a dependency in other projects

2. File-based or programmatic configuration of entities

3. Programmatic persistence of entities (basic CRUD support)

4. Basic transaction management (begin, commit, savepoint, rollback)

5. Connection pooling

6. Lightweight session creation

7. Session-based caching to minimize calls to the database

## Tech Stack
- [ ] Java 8
- [ ] JUnit
- [ ] Apache Maven
- [ ] PostGreSQL deployed on AWS RDS
- [ ] Git SCM (on GitHub)

## Init Instructions
- Create a new repository within this organization (naming convention: `orm_name_p1`; with `orm_name` being replaced by the name of your custom library)

## Presentation
- [ ] finalized version of library must be pushed to personal repository within this organization by the presentation date (February 19th, 2021)
- [ ] 10-15 minute live demonstration of the implemented features using a demo application to showcase the ORM's functionality
