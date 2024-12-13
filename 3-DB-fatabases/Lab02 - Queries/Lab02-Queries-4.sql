USE Lab01
GO


--E) 1.WHERE [IN] & 1.WHERE[WHERE[EXISTS]]
SELECT *
FROM donors
WHERE name IN (SELECT name FROM doctors)

SELECT *
FROM donors
WHERE name NOT IN (SELECT name
				   FROM doctors
				   WHERE doctor_addr < 10)

--F) 2.WHERE [subquery - EXISTS]
SELECT name, patients_doctor
FROM patients
WHERE EXISTS (SELECT name FROM doctors WHERE doctor_addr > 3)

SELECT quantity, rh, requests_doctor
FROM requests
WHERE rh = N'rh-' AND
	EXISTS(SELECT name FROM doctors WHERE doctor_id = 1)

--G) 2.FROM [subquery]
--DIS
SELECT COUNT(*) 
FROM (SELECT 1
	 FROM donors
	 WHERE donor_addr < 7)

SELECT name
FROM ()

GO