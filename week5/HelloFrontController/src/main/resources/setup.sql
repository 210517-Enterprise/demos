DROP TABLE IF EXISTS employee;

CREATE TABLE employee (
	id SERIAL PRIMARY KEY,
	first_name VARCHAR(50),
	last_name VARCHAR(50),
	username VARCHAR(50),
	pass_word VARCHAR(50)
);

INSERT INTO employee (first_name, last_name, username, pass_word)
	VALUES ('Tony', 'Stark', 'ironman', 'secret'),
			('Steve', 'Rogers', 'cap', 'shield');
	

SELECT * FROM employee;
