USE Lab01
GO
--ALSO 1.JOIN 3 TABLES & 1.JOIN 2xM-M
--D)1.INNER JOIN
SELECT addresses.addr_id, addresses.country, addresses.town, donors.name, donors.CNP
FROM addresses
INNER JOIN donors
ON donors.donor_addr = addresses.addr_id

--1.LEFT JOIN 
SELECT addresses.addr_id, addresses.country, addresses.town, donors.name, donors.CNP
FROM addresses
LEFT JOIN donors
ON donors.donor_addr = addresses.addr_id

--1.RIGHT JOIN
--Addresses, Donors, DCS
SELECT addresses.addr_id, addresses.country, addresses.town, donors.name, donors.CNP, donation_centers.name, donation_centers.dc_addr
FROM donors
RIGHT JOIN addresses 
ON donors.donor_addr = addresses.addr_id
FULL JOIN donation_centers
ON donors.donor_addr = donation_centers.dc_id

--1.FULL JOIN
SELECT patients.patient_id, patients.name, doctors.doctor_id, doctors.name
FROM patients
FULL JOIN doctors
ON patients.patients_doctor = doctors.doctor_id

--M-M

GO