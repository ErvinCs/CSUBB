USE Lab01
GO
BEGIN
	IF OBJECT_ID('dbo.donors_donation_centers', 'U') IS NOT NULL
	DROP TABLE dbo.donors_donation_centers
	IF OBJECT_ID('dbo.blood_samples', 'U') IS NOT NULL
	DROP TABLE dbo.blood_samples
	IF OBJECT_ID('dbo.test_results', 'U') IS NOT NULL
	DROP TABLE dbo.test_results
	IF OBJECT_ID('dbo.donation_centers', 'U') IS NOT NULL
	DROP TABLE dbo.donation_centers
	IF OBJECT_ID('dbo.patients', 'U') IS NOT NULL
	DROP TABLE dbo.patients
	IF OBJECT_ID('dbo.requests', 'U') IS NOT NULL
	DROP TABLE dbo.requests
	IF OBJECT_ID('dbo.donations', 'U') IS NOT NULL
	DROP TABLE dbo.donations
	IF OBJECT_ID('dbo.appointments', 'U') IS NOT NULL
	DROP TABLE dbo.appointments
	IF OBJECT_ID('dbo.doctors', 'U') IS NOT NULL
	DROP TABLE dbo.doctors
	IF OBJECT_ID('dbo.donors', 'U') IS NOT NULL
	DROP TABLE dbo.donors
	IF OBJECT_ID('dbo.addresses', 'U') IS NOT NULL
	DROP TABLE dbo.addresses
	IF OBJECT_ID('dbo.tableVersions', 'U') IS NOT NULL
	DROP TABLE dbo.tableVersions
END

CREATE TABLE addresses(
	addr_id INT NOT NULL PRIMARY KEY,
	country VARCHAR(30) NOT NULL,
	town VARCHAR(30) NOT NULL,
	addr VARCHAR(100) NOT NULL
	--/(1-1) donors
	--/(1-1) donation_centers
	--/(1-1) patients
	--/(1-1) doctors
);


CREATE TABLE doctors (
	doctor_id INT NOT NULL PRIMARY KEY,
	name VARCHAR(50) NOT NULL,
	birth_date DATE,
	CNP BIGINT UNIQUE NOT NULL,
	--/(1-m) patients
	--/(1-m) requests
	--/(1-m) appointments
	--(1-1) addresses
	--CONSTRAINT doctor_addr FOREIGN KEY (doctor_id)
	--	REFERENCES Lab01.dbo.addresses (addr_id)
	--	ON DELETE CASCADE
	--	ON UPDATE CASCADE
	doctor_addr INT REFERENCES addresses(addr_id) NOT NULL
	CONSTRAINT doctor_addr UNIQUE (doctor_addr)
);

CREATE TABLE patients (
	patient_id INT NOT NULL PRIMARY KEY,
	name VARCHAR(50) NOT NULL,
	birth_date DATE,
	CNP	BIGINT UNIQUE NOT NULL,
	--(m-1) doctors
	--CONSTRAINT patients_doctor FOREIGN KEY (patient_id)
	--	REFERENCES Lab01.dbo.doctors (doctor_id)
	--	ON DELETE NO ACTION  --CASCADE
	--	ON UPDATE NO ACTION, --CASCADE,
	patients_doctor INT REFERENCES doctors(doctor_id) NOT NULL,
	--(1-1) addresses
	--CONSTRAINT patient_addr FOREIGN KEY (patient_id)
	--	REFERENCES Lab01.dbo.addresses (addr_id)
	--	ON DELETE CASCADE
	--	ON UPDATE CASCADE
	patient_addr INT REFERENCES addresses(addr_id) NOT NULL
	CONSTRAINT patient_addr UNIQUE (patient_addr)
);

CREATE TABLE requests(
	request_id INT NOT NULL PRIMARY KEY,
	quantity INT NOT NULL,
	rh CHAR(3),
	blood_group CHAR(3),
	--(m-1) doctors
	--CONSTRAINT requests_doctor FOREIGN KEY (request_id)
	--	REFERENCES Lab01.dbo.doctors (doctor_id)
	--	ON DELETE CASCADE
	--	ON UPDATE CASCADE
	requests_doctor INT REFERENCES doctors(doctor_id) NOT NULL
);

CREATE TABLE donors(
	donor_id INT NOT NULL PRIMARY KEY,
	name VARCHAR(50) NOT NULL,
	birth_date DATE,
	CNP	BIGINT UNIQUE NOT NULL,
	--/(1-m) donations
	--/(1-1) appointments
	--(1-1) addresseses
	--CONSTRAINT donor_addr FOREIGN KEY (donor_id)
	--	REFERENCES Lab01.dbo.addresses (addr_id)
	--	ON DELETE CASCADE
	--	ON UPDATE CASCADE
	donor_addr INT REFERENCES addresses(addr_id) NOT NULL
	CONSTRAINT donor_addr UNIQUE (donor_addr)
	--(m-m) donation_centers
);

CREATE TABLE donation_centers(
	dc_id INT NOT NULL PRIMARY KEY,
	name VARCHAR(100) NOT NULL,
	--/(1-m) appointments
	--(1-1) addresses
	--CONSTRAINT dc_addr FOREIGN KEY (dc_id)
	--	REFERENCES Lab01.dbo.addresses (addr_id)
	--	ON DELETE CASCADE
	--	ON UPDATE CASCADE
	dc_addr INT REFERENCES addresses(addr_id) NOT NULL
	CONSTRAINT dc_addr UNIQUE (dc_addr)
	--(m-m) donors 
);

CREATE TABLE donors_donation_centers(
	donor_dc_id INT NOT NULL PRIMARY KEY,
	--(1-m) donation_centers
	--CONSTRAINT dc_donors FOREIGN KEY (donor_dc_id)
	--	REFERENCES Lab01.dbo.donation_centers (dc_id)
	--	ON DELETE NO ACTION   
	--	ON UPDATE NO ACTION, 
	dcs_donors INT REFERENCES donation_centers(dc_id) NOT NULL,
	--(1-m) donors
	--CONSTRAINT donors_dcs FOREIGN KEY (donor_dc_id)
	--	REFERENCES Lab01.dbo.donors (donor_id)
	--	ON DELETE NO ACTION  
	--	ON UPDATE NO ACTION  
	donors_dcs INT REFERENCES donors (donor_id) ON DELETE CASCADE NOT NULL 
);

CREATE TABLE donations(
	donation_id INT NOT NULL PRIMARY KEY,
	quantity INT NOT NULL,
	donation_date DATE NOT NULL,
	--(m-1) donors
	--CONSTRAINT donations_donor FOREIGN KEY (donation_id)
	--	REFERENCES Lab01.dbo.donors (donor_id)
	--	ON DELETE CASCADE
	--	ON UPDATE CASCADE
	donations_donor INT REFERENCES donors (donor_id) NOT NULL
	--/(1-1) blood_samples
);


CREATE TABLE appointments(
	appointment_id INT NOT NULL PRIMARY KEY,
	date_time DATETIME NOT NULL,
	--(m-1) donation_centers
	--CONSTRAINT appointments_dc FOREIGN KEY (appointment_id)
	--	REFERENCES Lab01.dbo.donation_centers (dc_id)
	--	ON DELETE CASCADE
	--	ON UPDATE CASCADE,
	appointments_dc INT REFERENCES donation_centers (dc_id) NOT NULL,
	--(1-1) donors
	--CONSTRAINT appointment_donor FOREIGN KEY (appointment_id)
	--	REFERENCES Lab01.dbo.donors (donor_id)
	--	ON DELETE NO ACTION  
	--	ON UPDATE NO ACTION  
	appointment_donor INT REFERENCES donors(donor_id) NOT NULL
	CONSTRAINT appointment_donor UNIQUE (appointment_donor)
);


CREATE TABLE test_results(
	result_id INT NOT NULL PRIMARY KEY,
	rh CHAR(4),
	blood_group CHAR(4),
	hiv BIT,
	hepatitis_C BIT
	--/(1-1) blood_samples
);

CREATE TABLE blood_samples(
	sample_id INT NOT NULL PRIMARY KEY,
	--(1-1) test_results
	--CONSTRAINT sample_test FOREIGN KEY (sample_id)
	--	REFERENCES Lab01.dbo.test_results (result_id)
	--	ON DELETE CASCADE
	--	ON UPDATE CASCADE,
	sample_test INT REFERENCES test_results(result_id) NOT NULL
	CONSTRAINT sample_test UNIQUE (sample_test),
	--(1-1) donations
	--CONSTRAINT sample_donation FOREIGN KEY (sample_id)
	--	REFERENCES Lab01.dbo.donations (donation_id)
	--	ON DELETE CASCADE
	--	ON UPDATE CASCADE
	sample_donation INT REFERENCES donations(donation_id) NOT NULL
	CONSTRAINT sample_donation UNIQUE (sample_donation)
);

CREATE TABLE tableVersions (
	version_id INT NOT NULL PRIMARY KEY
);

GO