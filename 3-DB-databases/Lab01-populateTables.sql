USE Lab01
GO
INSERT INTO Lab01.dbo.addresses
   ([addr_id],[country],[town],[addr])
VALUES
   ( 1, N'Romania', N'Cluj-Napoca', N'Strada1'),
   ( 2, N'Romania', N'Bucuresti', N'Str2'),
   ( 3, N'Romania', N'Oradea', N'Strada3'),
   ( 4, N'Romania', N'Cluj-Napoca', N'Str4'),
   ( 5, N'Romania', N'Deva', N'Strada5'),
   ( 6, N'Romania', N'Hunedoara', N'Str6'),
   ( 7, N'Romania', N'Simeria', N'Strada7'),
   ( 8, N'Romania', N'Bucuresti', N'Str8'),
   ( 9, N'Romania', N'Simeria', N'Strada9'),
   ( 10, N'Romania', N'Lugoj', N'Str10'),
   ( 11, N'Romania', N'Cluj-Napoca', N'Strada Saracilor'),
   ( 12, N'Romania', N'Cluj-Napoca', N'Strada mai putin Saraci'),
   ( 13, N'Romania', N'Cluj-Napoca', N'Doctor Street'),
   ( 14, N'Romania', N'Cluj-Napoca', N'Donor Street'),
   ( 15, N'Romania', N'Calarasi', N'Another Doctor Street'),
   ( 16, N'Romania', N'Except', N'Java lang exception'),
   ( 17, N'Romania', N'Not In', N'ABCD'),
   ( 18, N'Romania', N'Not In', N'Addr18'),
   ( 19, N'Romania', N'Not In', N'Addr19'),
   ( 20, N'Romania', N'Not In', N'Addr20'),
   ( 21, N'Romania', N'Not In', N'Addr21'),
   ( 22, N'Romania', N'Not In', N'Addr22'),
   ( 23, N'Romania', N'Not In', N'Addr23'),
   ( 24, N'Romania', N'Not In', N'Addr24'),
   ( 25, N'Romania', N'Not In', N'Addr25')

INSERT INTO Lab01.dbo.doctors
   ([doctor_id],[name],[birth_date],[CNP],[doctor_addr])
VALUES
   ( 1, N'Ionel', '29-JUN-80', 168123, 1),
   ( 2, N'Gicu', '02-NOV-67', 123456, 2),
   ( 3, N'Geanina', '29-AUG-80', 268123, 3),
   ( 4, N'G', '10-FEB-08', 268134, 4)
   --( 4, N'Uniq addr Constraint Violated', '02-NOV-67', 123456, 10)

INSERT INTO Lab01.dbo.donors
	([donor_id],[name],[birth_date],[CNP],[donor_addr])
VALUES
   ( 1, N'Donor#1', '10-FEB-08', 172224, 5),
   ( 2, N'Donor#2', '11-DEC-07', 282225, 6),
   ( 3, N'Donor#3', '13-FEB-06', 292223, 7),
   ( 4, N'Donor#4', '10-FEB-08', 292226, 8),
   ( 5, N'Ionel', '20-FEB-08', 193226, 9)

INSERT INTO Lab01.dbo.donation_centers
	([dc_id],[name],[dc_addr])
VALUES
	( 1, N'Donatioan Center de Saraci', 10),
	( 2, N'Donation Center de mai putin Saraci', 11)

INSERT INTO Lab01.dbo.patients
   ([patient_id],[name],[birth_date],[CNP],[patients_doctor],[patient_addr])
VALUES
   ( 1, N'Pacient#1', '9-FEB-08', 161123, 1, 12),
   ( 2, N'Georgel', '12-NOV-97', 123356, 1, 13),
   ( 3, N'Pacient#2', '10-FEB-08', 271124, 2, 14),
   ( 4, N'Pacient#3', '11-DEC-07', 281125, 2, 15),
   ( 5, N'Pacient#4', '13-FEB-06', 291126, 3, 16)
	
INSERT INTO Lab01.dbo.requests
	([request_id],[blood_group],[rh],[quantity],[requests_doctor])
VALUES
	(1, N'A2', N'rh-', 300, 1),
	(2, N'A2', N'rh+', 400, 1),
	(3, N'B3', N'rh+', 500, 1),
	(4, N'AB4', N'rh-', 600, 3),
	(6, NULL, NULL, 2000, 2),
	(7, N'O1', NULL, 1, 3),
	(8, NULL, N'rh-', 500, 2)
	--(9, N'VIO', N'LAT', 100, 10)

INSERT INTO Lab01.dbo.donors_donation_centers
	([donor_dc_id],[dcs_donors],[donors_dcs])
VALUES
	(1, 1, 1),
	(2, 1, 2),
	(3, 2, 1),
	(4, 2, 3)

INSERT INTO Lab01.dbo.appointments
	([appointment_id],[appointment_donor],[appointments_dc],[date_time])
VALUES
	(1, 3, 1, '20-DEC-18'),
	(2, 4, 2, '21-DEC-18'),
	(3, 5, 1, '01-FEB-19')

INSERT INTO Lab01.dbo.donations
	([donation_id], [quantity], [donation_date], [donations_donor])
VALUES
	(1, 1000, '20-FEB-10', 2),
	(2, 500, '21-FEB-10', 4),
	(3, 500, '8-DEC-09', 1),
	(4, 2000, '10-DEC-09', 3)

INSERT INTO Lab01.dbo.test_results
	([result_id], [rh], [blood_group], [hiv], [hepatitis_C])
VALUES	
	(1, N'rh-', N'A2', 0, 0),
	(2, N'rh-', N'A2', 1, 1),
	(3, N'rh+', N'B3', 0, 1),
	(4, N'rh+', N'AB4', 1, 0)

INSERT INTO Lab01.dbo.blood_samples
	([sample_id], [sample_test], [sample_donation])
VALUES
	(1, 1, 1),
	(2, 2, 2),
	(3, 3, 3),
	(4, 4, 4)

--INSERT INTO Lab01.dbo.tableVersions
--	([version_id])
--VALUES
--	(1),
--	(2),
--	(3),
--	(4),
--	(5),
--	(6),
--	(7)

SELECT * FROM Lab01.dbo.donation_centers;
SELECT * FROM Lab01.dbo.donors;
SELECT * FROM Lab01.dbo.donors_donation_centers;
SELECT * FROM Lab01.dbo.patients;
SELECT * FROM Lab01.dbo.doctors;
SELECT * FROM Lab01.dbo.requests
SELECT * FROM Lab01.dbo.appointments
SELECT * FROM Lab01.dbo.donations
SELECT * FROM Lab01.dbo.blood_samples
SELECT * FROM Lab01.dbo.test_results
SELECT * FROM Lab01.dbo.tableVersions

GO