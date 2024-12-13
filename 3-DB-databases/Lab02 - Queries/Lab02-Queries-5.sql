USE Lab01
GO

--H) 4.GROUP BY from which 3.HAVING clause from which 2.HAVING [subquery] + COUNT, SUM, AVG, MIN, MAX
SELECT patients_doctor
FROM patients
GROUP BY patients_doctor
HAVING COUNT(*) >= 2

SELECT R.rh, R.quantity
FROM requests R
GROUP BY rh, quantity
HAVING AVG(quantity) > 1000

SELECT D.doctor_id, D.name, D.CNP 
FROM doctors D
GROUP BY CNP, name, doctor_id
HAVING D.CNP LIKE '2%'

SELECT name, birth_date
FROM donors
GROUP BY birth_date, name

--I) 4.[subquery - ANY & ALL]


SELECT *
FROM donors
WHERE name = ANY (SELECT name
				   FROM doctors
				   WHERE doctor_addr IN (1, 2, 3, 4, 5, 6, 7, 8, 9))


