--Up to Version 4 - Add a PK
USE Lab01
GO

--Return 0 rows even if there are null values in a column
SET ANSI_NULLS ON
GO
--Identifiers can be delimited by double quotation marks and literals must be delimited by single quotation marks
SET QUOTED_IDENTIFIER ON
GO

--CREATE
ALTER PROCEDURE [dbo].[up_to_v4]
AS
BEGIN
	--Prevent extra result sets from interfering with SELECT statements.
	SET NOCOUNT ON;
	
	--ALTER TABLE dbo.doctors
	--ADD CONSTRAINT UQ_ID PRIMARY KEY (doctor_id)
	print('up_to_v4 - add UNIQUE PK CONSTRAINT - id')
END