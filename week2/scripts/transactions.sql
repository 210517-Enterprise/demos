DROP TABLE IF EXISTS bank_accounts CASCADE;
CREATE TABLE bank_accounts (
	c_name VARCHAR(50),
	balance NUMERIC(50, 2)
);

INSERT INTO bank_accounts (c_name, balance)
	VALUES ('Alice', 100),
			('Bob', 50),
			('Sam', 0);
			
SELECT * FROM bank_accounts;

-- 1. Subtract $50 from Alice's account
UPDATE bank_accounts SET balance = balance - 50
	WHERE c_name = 'Alice';
	
-- 2. Add $50 to Bob's account
UPDATE bank_accounts SET balance = balance + 50
	WHERE c_name = 'Bob';

SELECT * FROM bank_accounts;

-- 2 separate operations.

-- A Transaction is a unit of work to be performed against a DB
-- Transactions follow ACID principles
-- TCL Transaction Control Language (DDL, DML, DCL, TCL *DQL*)
BEGIN;
	-- 1. Subtract $50 from Alice's account
	UPDATE bank_accounts SET balance = balance - 50
		WHERE c_name = 'Alice';
	
		SAVEPOINT my_savepoint;
	-- Savepoints allow you to selectively discard parts of the
	-- the transaction while committing the rest.
	
	UPDATE bank_accounts SET balance = balance + 50
		WHERE c_name = 'Sam'; 
	-- Whoops! didn't mean to send money to Sam -> Send it to Bob instead
-- ROLLBACK is used typially if the transaction faces any errors during its execution
	-- we can rollback to a save point

	ROLLBACK TO my_savepoint;
	-- You wounldn't typically provide a manual Rollback -- typically used for testing purposes 	


	-- 2. Add $50 to Bob's account
	UPDATE bank_accounts SET balance = balance + 50
		WHERE c_name = 'Bob';
COMMIT;

-- Transactions always have to do with CRUD operations

-- A.C.I.D Properties
-- a proper transaction must have the following properties

-- A = Atomicity = it either happens or not.  It's succesful or it's not.
-- C = Consistency = Referential Integrity is maintained and constraints are changed properly upon successful TRANSACTION 
-- I = Isolation = Transactions occur independently of eachother
-- D = Durability = Ensures that the result of a commited transaction persists in the case of system failure.

-------------------------------------------------------------------------------------------------------------------

-- Transaction Problems: sometimes issues occur with transactions interfering with eachother
-- and getting ROLLED BACK due to the concurrent nature of a DB.

-- Dirty Read: A tx reads data from another transaction that hasn't been commited.
-- Non-Repeatable Read: 1 tx reads the same data TWICE while another tx updates the data in between the 1st and 2nd
-- Phantom Read: tx runs a query twice and gets diff data each time. Llike a diff # of ROWS 

-----------------------------------------------------------------------------------

-- Transaction Isolation Level: The higher the isolation level (the highest is called Serializable)
-- the more careful the system is about avoiding conflicts.

-- https://www.geeksforgeeks.org/transaction-isolation-levels-dbms/







































