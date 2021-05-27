/*
	PL/pgSQL = Procedural Language extension of PostgreSQL
	
		- NOT A SUBLANGUAGE OF SQL!
		- Allows devs to create:
			- stored functions (returns a result)
			- stored procedures (since postgres 11)
			- triggers
			- custom types
			
		- Adds procedural features to the normally declarative SQL scripts
			- loops
			- exception handling
			- if statements
			
*/

/*
	Stored Functions
	
		create [or replace] function [name] (params)
		returns [type]
		as '
			begin
				[logic]
			end
		'
		language plpgsql;
*/

CREATE OR REPLACE FUNCTION multiply(x NUMERIC, y NUMERIC)
RETURNS NUMERIC
AS '
		begin
			return x * y;
		end
   '
LANGUAGE plpgsql;

SELECT multiply(5, '4'); -- postgres has TYPE coersion!


CREATE OR REPLACE FUNCTION multiply(x NUMERIC, y NUMERIC, z NUMERIC)
RETURNS NUMERIC
AS $$
		begin
			return $1 * $2 * $3; -- here IS another way OF accessing 1st, 2nd, 3rd param
		end
   $$ -- you can ALSO use $$ AS a delimeter
LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION get_last_track_info()
RETURNs TEXT
AS $$
	
	DECLARE
	largest int;
	track_name TEXT;
	end_result TEXT;
	
	
	BEGIN
		-- aggregate function!
		SELECT max("TrackId")
			INTO largest
			FROM "Track";
		
		SELECT "Name"
			INTO track_name
			FROM "Track" WHERE "TrackId" = largest;
		
		-- variable := expression
		end_result := largest || '-' || track_name;
		
		RETURN end_result;
	END

$$
LANGUAGE plpgsql;

SELECT  get_last_track_info();


CREATE TABLE colors (
	id SERIAL CONSTRAINT "colors_pk" PRIMARY KEY, -- yet another way OF declaring a CONSTRAINT
	color VARCHAR
);

CREATE TABLE sizes (
	id serial CONSTRAINT "sizes_pk" PRIMARY KEY,
	c_size VARCHAR
);

insert into sizes (c_size)
values
	('S'), ('M'), ('L'), ('XL'), ('XXL');

insert into colors (color) 
values
	('red'), ('orange'), ('yellow'), ('green'), ('blue'), ('indigo'), ('violet');

SELECT * FROM colors;
SELECT * FROM sizes;

-- CROSS JOIN is used to generate a paired combo of each row of the first table with each row of the second table
SELECT s.c_size, c.color -- I'm selecting these SPECIFIC COLUMNS FROM those TABLES
	FROM sizes s 
	CROSS JOIN colors c;

-- https://www.essentialsql.com/cross-join-introduction/

-- TRIGGER FUNCTION: Defining a function to occur when a particular event occurs
CREATE OR REPLACE FUNCTION no_more_blues()
RETURNS TRIGGER -- USING TRIGGER RETURN clause so it plays nice WITH the TRIGGER that we've SET up.  Otherwise the FUNCTION
				-- doesn't actually return anything (and doesn't have params)
AS $$
		
		BEGIN
			
			If(NEW.color = 'blue') THEN
				RETURN NULL;
			END IF;
			
		
			RETURN NEW; -- NEW IS a keyword that represents a NEW value becing inserted AS a record IN the DATABASE
		END

   $$
   LANGUAGE plpgsql;

  -- we must use a trigger to define when the function occurs, because otherwise it's never invoked
  
  CREATE TRIGGER no_blue
  	BEFORE INSERT OR UPDATE ON colors
  	FOR EACH ROW
  	EXECUTE FUNCTION no_more_blues();
  
SELECT * FROM colors;
INSERT INTO colors (color) values ('magenta');

INSERT INTO colors (color) VALUES ('blue');

CREATE VIEW VIEW_colors  AS SELECT * FROM colors;
 
INSERT INTO view_colors (color) VALUES ('blue');

SELECT * FROM colors;

UPDATE colors SET  color = 'blue' WHERE id = 1;
 

EXPLAIN SELECT * FROM chinook.colors WHERE color = 'blue';
-- An INDEX is also a table -- it has a data structure
-- INDEXES are pointers that represent the physical address of data
-- INDEXES are only crated on columns of table 
--  https://www.postgresqltutorial.com/postgresql-indexes/postgresql-create-index/
CREATE INDEX fast_colors
	ON chinook.colors(color);







