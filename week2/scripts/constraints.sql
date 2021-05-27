-- we must create table first because of referential integrity
-- another way of creatingconstraints...(the long way )

CREATE TABLE depts (
	id SERIAL,
	dept_name VARCHAR,

	CONSTRAINT depts_PK PRIMARY KEY (id)
);

CREATE TABLE sample_employees (
	id SERIAL, -- THIS WILL BE MY pk, BUT I'll DECLARE it below
	first_name VARCHAR NOT NULL,
	last_name VARCHAR NOT NULL,
	dept_id INT, -- THIS IS THE FK....but it's declared below too

	-- another way of declaring contraints
	CONSTRAINT sample_employees_PK PRIMARY KEY (id),
	CONSTRAINT employees_depts_FK FOREIGN KEY (dept_id) REFERENCES depts 
	-- by default Foreign keys automatically point to the primary key 
	-- of the table that I reference, hence why I don't need to specify the column of depts
);

SELECT * FROM depts;