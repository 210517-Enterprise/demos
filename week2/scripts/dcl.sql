-- DCL -- Data Control Language
-- DCL is totally restricted to matters only concerning your database
-- and who can view, access, manipulate your data


-- 1. CREATE USER
CREATE USER sophiag WITH PASSWORD 'dumbledore';

-- 2. GRANT SOME PRIV. IN A SCHEMA
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA sophiaproject0 TO sophiag;
REVOKE ALL PRIVILEGES ON ALL TABLES IN SCHEMA sophiaproject0 TO sophiag;

-- 3. CREATE THE ROLE WITH TYPES OF ABILITIES
DROP ROLE IF EXISTS administrator;
CREATE ROLE administrator WITH
	CREATEDB
	CREATEROLE
	LOGIN
	INHERIT;

-- 4. GRANT THE ROLE TO OUR NEW USER
GRANT administrator TO sophiag;


	
	