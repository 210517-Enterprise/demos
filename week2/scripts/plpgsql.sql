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

















