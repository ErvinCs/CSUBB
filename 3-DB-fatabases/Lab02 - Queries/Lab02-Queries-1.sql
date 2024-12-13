USE Lab01
GO
--Violate referential constraint in one Insert statement OK
--Update data for 3 tables
UPDATE patients
SET name = N'NEW NAME'
WHERE patient_id < 4 AND birth_date BETWEEN '2008-02-09' AND '2008-02-10';

UPDATE requests
SET quantity = 1000, rh = N'rh+'
WHERE rh IS NULL AND blood_group IS NOT NULL;

UPDATE requests
SET requests_doctor = 1
WHERE quantity >= 1000;	--request

--Delete data for 2 tables
DELETE FROM donors
WHERE CNP % 10 > 4 OR CNP LIKE '1%'

DELETE FROM patients
WHERE patient_addr IN (2, 3, 4)

SELECT * FROM Lab01.dbo.requests
SELECT * FROM Lab01.dbo.donors
SELECT * FROM Lab01.dbo.patients;

GO
