DROP TYPE IF EXISTS sophiaproject0.user_role CASCADE;
CREATE TYPE sophiaproject0.user_role AS ENUM ('Admin', 'Employee', 'Customer'); 

-- This is just a demo and a guide! Your project does NOT need to be exactly like this....
-- in fact, I encourage you to be more creative
DROP TABLE IF EXISTS sophiaproject0.users CASCADE;
CREATE TABLE sophiaproject0.users (
	id SERIAL PRIMARY KEY,
	username VARCHAR(50) UNIQUE NOT NULL,
	pwd VARCHAR(50) NOT NULL,
	user_role sophiaproject0.user_role NOT NULL
);

DROP TABLE IF EXISTS sophiaproject0.accounts CASCADE;
CREATE TABLE sophiaproject0.accounts (
	id SERIAL PRIMARY KEY,
	balance NUMERIC (50, 2) NOT NULL CHECK (balance >= 0) DEFAULT 0,
	acc_owner INTEGER NOT NULL REFERENCES sophiaproject0.users (id),
	active BOOLEAN DEFAULT FALSE -- this IS determining whethere the account IS active
	-- true means that it's active, false means that it's not/closed
);

DROP TABLE IF EXISTS sophiaproject0.users_accounts_jt CASCADE; 
CREATE TABLE sophiaproject0.users_accounts_jt (
	acc_owner INTEGER NOT NULL REFERENCES sophiaproject0.users(id),
	account INTEGER NOT NULL REFERENCES sophiaproject0.accounts(id)
);

-- this table is about the applications that belong to different users
-- diff. users who are applying for diff accounts
DROP TABLE IF EXISTS sophiaproject0.applications CASCADE; 
CREATE TABLE sophiaproject0.applications (
	id SERIAL PRIMARY KEY,
	app_owner INTEGER NOT NULL REFERENCES sophiaproject0.users(id)
);

















