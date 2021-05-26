DROP SCHEMA IF EXISTS sophiag CASCADE;

CREATE SCHEMA sophiag; /* your name! */

-- this is a single line comment
/*
This is 
a multi-line 
comment
*/
-- ANYBODY WHO IS USING AMAZON, PLESE USE YOUR FIRSTNAME-LAST-INITIAL SCHEMA
DROP TABLE IF EXISTS public.users CASCADE;
-- Completely remove the users table, regardless of any rlationships
-- Without the CASCADE keyword, this DROP statement would fail
-- if there were any relationships that needed to be upheld
-- Such as Foreign Keys 

-- This is DDL -- DEFINING TABLE STRCUTURE
CREATE TABLE public.users (
	id SERIAL PRIMARY KEY, -- SERIAL IS a special 
	first_name VARCHAR(30) NOT NULL CHECK(LENGTH(first_name) > 1),
	last_name VARCHAR(30) NOT NULL,
	email VARCHAR(30) UNIQUE,
	user_age INTEGER NOT NULL DEFAULT 0 CHECK (user_age >= 0),
	supervisor INTEGER
);

SELECT * FROM public.users;

ALTER TABLE public.users
	ADD CONSTRAINT users_supervisors_fk
	FOREIGN KEY (supervisor) REFERENCES users(id);

-- ctrl + enter
INSERT INTO public.users (first_name, last_name)
	VALUES ('Matthew', 'Murdock'); -- we use '' WITH VARCHAR

-- selectively qeureying data
SELECT first_name, last_name FROM public.users;

-- You can construct custom columns from the columns within the tables
-- The || is performing string concatenation
-- Can use the AS keyword as an alias
SELECT first_name || ' ' || last_name AS "Full Name" FROM public.users;

-- Views are reusable query results that you can refer to later 

INSERT INTO public.users (first_name, last_name, email, user_age) VALUES
	('Hulk', 'Hogan', 'hulk@gmail.com', 60),
	('Peter', 'Parker', 'spiderman@gmail', 17);

SELECT * FROM public.users;

INSERT INTO public.users(first_name, last_name, email, user_age, supervisor) VALUES
-- Hulk Hogan's PK is 2. Tony Stark's supervisor is Hulk Hogan	
	('Tony', 'Stark', 'tstark@gmail.com', 43, 2);

DROP TABLE IF EXISTS public.phonenumbers CASCADE; -- CASCADE means that you want TO DELETE ALL DATA even IF 
												  -- other tables depend on it
CREATE TABLE public.phonenumbers (
	id SERIAL PRIMARY KEY,
	user_id INTEGER NOT NULL REFERENCES public.users(id), -- our foreign KEY!!!
	home VARCHAR(20),
	work_num VARCHAR(20),
	mobile VARCHAR(20)
);


INSERT INTO public.phonenumbers (user_id, home, work_num) 
	VALUES (4, '2345678', '9999999999'),
			(3, '88888888', '777777777'),
		(4, '1111111', '3333333333');
		
SELECT * FROM public.phonenumbers;


-- Only return Tony Stark's phone numbers using WHERE clause;
SELECT work_num FROM public.phonenumbers WHERE user_id = 4;


DROP TABLE IF EXISTS public.accounts CASCADE;
CREATE TABLE public.accounts (
	id SERIAL PRIMARY KEY,
	owner_id INTEGER NOT NULL REFERENCES public.users(id),
	-- Integer allows WHOLE values,
	-- NUMERIC allows decimal values
	balance NUMERIC (40, 2) NOT NULL DEFAULT 0
);S

-- DML! 
DELETE FROM public.users WHERE first_name='Matthew';

SELECT * FROM public.users;

TRUNCATE TABLE public.users CASCADE;

DROP TABLE IF EXISTS public.one CASCADE;
DROP TABLE IF EXISTS public.two CASCADE;

CREATE TABLE public.one (
	c_one INTEGER PRIMARY KEY,
	c_two INTEGER
);

CREATE TABLE public.two (
	c_one INTEGER PRIMARY KEY,
	c_two INTEGER
);

INSERT INTO public.one VALUES (1, 1), (2, 2);
INSERT INTO public.two VALUES (1, 1), (2, 1);

-- SET operations only operate on results that have the
-- same number and type of columns

-- The UNION operator will combine all results together
-- However, it will not include duplicates
SELECT * FROM public.one UNION SELECT * FROM public.two;

-- UNION ALL does include duplicates
SELECT * FROM public.one UNION ALL SELECT * FROM public.two;

-- INTERSECT will only include matching results
SELECT * FROM public.one INTERSECT SELECT * FROM public.two;

-- EXCEPT will keep results from the left view, and remove
-- any matching results that came from the right;
/*
 * The SQL EXCEPT clause/operator is used to combine two 
 * SELECT statements and returns rows from the first SELECT 
 * statement that are not returned by the second SELECT statement
 */
SELECT * FROM public.one EXCEPT SELECT * FROM public.two;

-- SQL supports aggregate and scalar functions that can be used along with SELECT statements

-- SCALAR FUNCTIONS
-- are FUNCTIONS that operate ON ONLY a SINGLE INPUT and produce 1 output of EACH input
-- UPPER, LOWER...

-- AGGREGATE FUNCTIONS
-- operate on an entire column as input and produce 1 output
-- SUM, AVG, etc...

SELECT SUM(c_one) FROM public.one;


SELECT AVG(LENGTH(first_name)) FROM public.users;

-- scalar function -- perform somthing to every value...and return it!
SELECT UPPER(first_name) FROM public.users; 

SELECT * FROM public.users;

CREATE SCHEMA sophiaproject0;






