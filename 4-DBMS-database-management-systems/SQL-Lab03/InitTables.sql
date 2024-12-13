USE dbms
GO

/*
INSERT INTO dbms.dbo.addresses
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

INSERT INTO dbms.dbo.doctors
   ([name],[birth_date],[CNP],[doctor_addr])
VALUES
   (N'Ionel', '29-JUN-80', 168123, 1),
   (N'Gicu', '02-NOV-67', 123456, 2),
   (N'Geanina', '29-AUG-80', 268123, 3),
   (N'Mercedesa', '10-FEB-08', 268134, 4)

INSERT INTO dbms.dbo.requests
	([blood_group],[rh],[quantity],[requests_doctor])
VALUES
	(N'A2', N'rh-', 300, 1),
	(N'A2', N'rh+', 400, 2),
	(N'B3', N'rh+', 500, 2),
    (N'AB4', N'rh-', 600, 3)

INSERT INTO dbms.dbo.patients
	([name], [birth_date], [CNP], [patients_doctor])
VALUES
	(N'Bolnav', '25-JUN-80', 168123, 1),
	(N'Sarac', '10-JUN-90', 168723, 1),
	(N'Om Simplu', '1-JAN-90', 212315, 2)

SELECT * FROM dbo.doctors
SELECT * FROM dbo.requests
SELECT * FROM dbo.patients
*/

INSERT INTO dbms.dbo.donors
	([name], [birth_date], [CNP])
VALUES
	(N'Dorel', '25-JUN-80', 168123),
	(N'Bicu', '10-JUN-85', 168124),
	(N'Smaranda', '1-JAN-90', 212315),
	(N'Calina', '2-JAN-90', 212310)

INSERT INTO dbms.dbo.donation_centers
	([name])
VALUES
	(N'C Motilor'),
	(N'C Manastur'),
	(N'C Zorilor')

INSERT INTO dbms.dbo.donors_donation_centers
	([dcs_donors], [donors_dcs])
VALUES
	(1, 1),
	(1, 2),
	(1, 3),
	(2, 1),
	(2, 2),
	(3, 3)

SELECT * FROM dbms.dbo.donation_centers
SELECT * FROM dbms.dbo.donors
SELECT * FROM dbms.dbo.donors_donation_centers