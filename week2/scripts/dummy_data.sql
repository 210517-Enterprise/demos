
-- Insert some users into the users table
insert into sophiaproject0.users (username, pwd, "user_role") values ('alaurant1', 'Hyqk6J', 'Admin');
insert into sophiaproject0.users (username, pwd, "user_role") values ('gmcgahy2', '9lJYTFa', 'Employee');
insert into sophiaproject0.users (username, pwd, "user_role") values ('sdemarco3', 'QlW3emct', 'Customer');
insert into sophiaproject0.users (username, pwd, "user_role") values ('sfechnie4', 'zbOTOYc5', 'Customer');
insert into sophiaproject0.users (username, pwd, "user_role") values ('dcorry5', 'nlSGvCcV6IzH', 'Customer');
insert into sophiaproject0.users (username, pwd, "user_role") values ('njewes6', '4InhWQ', 'Customer');

SELECT * FROM sophiaproject0.users;

-- insert some accounts, each belonging to a different user.
INSERT INTO sophiaproject0.accounts (balance, acc_owner, active) VALUES (10000, 3, TRUE);
INSERT INTO sophiaproject0.accounts (balance, acc_owner, active) VALUES (2.05, 5, FALSE);
INSERT INTO sophiaproject0.accounts (balance, acc_owner, active) VALUES (300000, 1, TRUE);
INSERT INTO sophiaproject0.accounts (balance, acc_owner, active) VALUES (4053.32, 6, TRUE);
INSERT INTO sophiaproject0.accounts (balance, acc_owner, active) VALUES (90.01, 2, TRUE);
INSERT INTO sophiaproject0.accounts (balance, acc_owner, active) VALUES (80000000, 4, TRUE);
INSERT INTO sophiaproject0.accounts (balance, acc_owner, active) VALUES (500.76, 5, TRUE);


SELECT * FROM sophiaproject0.accounts;

-- Insert the values into the join table to make querying easier when looking up users accounts
INSERT INTO sophiaproject0.users_accounts_jt (acc_owner, account) VALUES (3, 1);
INSERT INTO sophiaproject0.users_accounts_jt (acc_owner, account) VALUES (5, 2);
INSERT INTO sophiaproject0.users_accounts_jt (acc_owner, account) VALUES (1, 3);
INSERT INTO sophiaproject0.users_accounts_jt (acc_owner, account) VALUES (6, 4);
INSERT INTO sophiaproject0.users_accounts_jt (acc_owner, account) VALUES (2, 5);
INSERT INTO sophiaproject0.users_accounts_jt (acc_owner, account) VALUES (4, 6);
INSERT INTO sophiaproject0.users_accounts_jt (acc_owner, account) VALUES (5, 7);

SELECT * FROM sophiaproject0.users_accounts_jt;


-- This complex query will join the data from the users table, accounts table, and accounts_jt table showing you 
-- each record of an account + balance with its corresponding user, user id, and user role
SELECT sophiaproject0.users.id, sophiaproject0.users.username, sophiaproject0.users.pwd, sophiaproject0.users.user_role, sophiaproject0.accounts.id 

	AS account_id, sophiaproject0.accounts.balance FROM sophiaproject0.users 

		LEFT JOIN sophiaproject0.users_accounts_jt ON sophiaproject0.users.id = sophiaproject0.users_accounts_jt.acc_owner 
		LEFT JOIN sophiaproject0.accounts ON sophiaproject0.accounts.id = sophiaproject0.users_accounts_jt.account;







