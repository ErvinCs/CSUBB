USE Lab01
GO

--A) 1.UNION ALL & 1.UNION OR
SELECT dc.name
FROM donation_centers dc
UNION
SELECT d.name
FROM donors d
WHERE donor_addr <= 5 OR donor_addr >=16

SELECT birth_date
FROM doctors
UNION ALL
SELECT birth_date
FROM donors
ORDER BY birth_date

--B) 1.INTERSECT & 1.IN
SELECT doctor_id
FROM doctors
INTERSECT
SELECT patients_doctor
FROM patients;

SELECT doctor_id
FROM doctors
INTERSECT
SELECT patients_doctor
FROM patients
WHERE name IN ('Pacient#1', 'Pacient#2', 'Pacient#3') 

--C) 1.EXCEPT & 1.NOT IN
SELECT name 
FROM donors
EXCEPT
SELECT name 
FROM doctors

SELECT name 
FROM donors
WHERE name NOT IN (SELECT name FROM doctors)


GO