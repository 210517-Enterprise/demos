/*
 * Join Types
 * 		- INNER 
 * 			+ FULL
 * 			+ RIGHT
 * 			+ LEFT
 * 
 * 		- OUTER 
 * 			+ FULL
 * 			+ RIGHT
 * 			+ LEFT
 * 		
 * 		- SELF
 * 
 * 		- CROSS
 * 
 * -----------------------------
 * 
 * 		- Theta Joins (when your ON clause uses an arbitrary comparison <, >, >=, <= etc
 *  		- Equi Joins (theta joins that uses the equality operator (=)
 * 				- Natural join (the join occurs on a column whose name is shared between both tables - lets us do USING)
 */

select * 
from "Artist";

select *
from "Album";

select *
from "Employee";

-- INNER JOIN with an ON clause 
-- The INNER JOIN selects all rows from both participating tables as long as there is a match between the columns
select alb."AlbumId", alb."Title", art."Name" as artist
	from "Album" alb
	join "Artist" art
	on alb."ArtistId" = art."ArtistId";
	
-- Natural inner join
-- the two tables being joined share a column with the exact same name
select alb."AlbumId", alb."Title", art."Name" as artist
from "Album" alb
join "Artist" art
using ("ArtistId");

-- Self join (joins on another record within the same table)
select e1."FirstName", e1."LastName", e1."Title", e2."FirstName", e2."LastName", e2."Title"
from "Employee" e1
join "Employee" e2
on e1."ReportsTo" = e2."EmployeeId";

-- Create a view to simplify 
CREATE VIEW emp_rel AS
SELECT e1."FirstName" , e1."LastName" , e1."Title", e2."FirstName" AS boss_fn , e2."LastName" AS boss_ln , e2."Title" AS boss_title
	FROM "Employee" e1
	JOIN "Employee" e2
	ON e1."ReportsTo" = e2."EmployeeId";

SELECT * FROM emp_rel;


-- Multi-table joins
select 
	t."Name" as track_name, 
	alb."Title" as album_title, 
	art."Name" as artist_name
from "Track" t
join "Album" alb
using ("AlbumId")
join "Artist" art
using ("ArtistId")
order by artist_name;

-- Subqueries
-- Also called "nested queries"
-- Can be used in: column selector list, from clause, and where clause

-- subquery in a from clause
SELECT * FROM "Genre";

SELECT r_genres.*
	FROM (
	SELECT * FROM "Genre" 
	WHERE "Name" LIKE 'R%' -- find ALL DATA that begins WITH capital R
	) AS r_genres -- somename IS the queryresult () 
WHERE r_genres."GenreId" > 6;


/*
 * Views
 * 
 * 		When we execute a query, we generate a result set.
 * 		Sometimes we may frequently use that result set as 
 * 		a base for executing other queries.
 * 
 * 		Views are basically just saved result sets that have
 * 		a name that can be references and they act as a sort 
 * 		of "virtual table" - not a real table.
 */
CREATE VIEW view_R_genres AS select some_name.*
from (
	select *
	from "Genre"
	where "Name" like 'R%' -- LIKE is case-sensitve!
	) as some_name
where some_name."GenreId" > 6;

SELECT * FROM view_R_genres;

-- The LEFT JOIN keyword returns all the rows of the table 
-- on the left side of the join and matching rows for the 
-- table on the right side of the join. If there are left 
-- side rows with no matching rows on the right side, a null value will fill the space for the right side.
SELECT a."TrackId", a."Name" , a."Composer" , b."InvoiceLineId" , b."InvoiceId" 
FROM "Track" AS a
LEFT JOIN "InvoiceLine" AS b
ON a."TrackId" = b."TrackId";

SELECT * FROM "Track";
SELECT * FROM "InvoiceLine";

/*
 * In the above example, the query sets the tracks table to the variable name a and the invoice_items table 
 * to the variable name b. 
 * 
 * It links them through the common key, trackid, and returns the trackid, name, 
 * and composer from the tracks table with the invoice line ID and invoice ID from the invoice_items table.
 */
-- https://towardsdatascience.com/sql-join-8212e3eb9fde
SELECT a."TrackId", a."Name" , a."Composer" , b."InvoiceLineId" , b."InvoiceId" 
FROM "Track" AS a
LEFT JOIN "InvoiceLine" AS b
ON a."TrackId" = b."TrackId"
WHERE b."InvoiceId" IS NULL;

/*
 * The IS NULL clause is useful for finding any null values in a column. 
 * Adding this line to the original example, we find that many rows did not 
 * have a value in the original invoice_items table and are replaced with 
 * null values instead.
 */
SELECT a."TrackId", a."Name" , a."Composer" , b."InvoiceLineId" , b."InvoiceId" 
FROM "Track" AS a
RIGHT JOIN "InvoiceLine" AS b
ON a."TrackId" = b."TrackId";

--INNER JOIN: Selects all rows from both tables that have a matching common key.
--LEFT JOIN: Uses all rows of the table on the left side and finds matching rows from the table on the right side.
--RIGHT JOIN: Uses all rows of the table on the right side and finds matching rows from the table on the left side.
--FULL JOIN: Combines all rows from both tables.

-- https://www.tutorialspoint.com/sql/sql-full-joins.htm
SELECT a."TrackId", a."Name" , a."Composer" , b."InvoiceLineId" , b."InvoiceId"  
FROM "Track" AS a
FULL JOIN "InvoiceLine" AS b
ON  a."TrackId" = b."TrackId";
