USE Lab04Test
GO
	--addresses - 1 PK (addr_id)
	--donors - 1 PK (donor_id) & 1 FK (donor_addr)
	--doctors - multicolumn PK

BEGIN
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
	addr_id INT NOT NULL PRIMARY KEY NONCLUSTERED,
	country VARCHAR(30) NOT NULL,
	town VARCHAR(30) NOT NULL,
	addr VARCHAR(100) NOT NULL
);

CREATE TABLE doctors (
	doctor_id INT NOT NULL PRIMARY KEY,
	name VARCHAR(50) NOT NULL,
	birth_date DATE,
	CNP BIGINT UNIQUE NOT NULL,

	CONSTRAINT FK_doc_addr FOREIGN KEY (doctor_id)
	REFERENCES Lab04Test.dbo.addresses (addr_id)
	ON DELETE CASCADE    
    ON UPDATE CASCADE   
);

CREATE TABLE donors(
	donor_id INT NOT NULL,
	name VARCHAR(50) NOT NULL,
	birth_date DATE,
	CNP	BIGINT UNIQUE NOT NULL,
	CONSTRAINT PK_donor PRIMARY KEY (donor_id, name, cnp)
);

CREATE TABLE tableVersions (
	version_id INT NOT NULL PRIMARY KEY
);