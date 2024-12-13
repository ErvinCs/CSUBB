--Down to Version 3 - drop pk constraint
USE Lab01
GO

--Return 0 rows even if there are null values in a column
SET ANSI_NULLS ON
GO
--Identifiers can be delimited by double quotation marks and literals must be delimited by single quotation marks
SET QUOTED_IDENTIFIER ON
GO

--CREATE
ALTER PROCEDURE [dbo].[down_to_v3]
AS
BEGIN
	--Prevent extra result sets from interfering with SELECT statements.
	SET NOCOUNT ON;

	--ALTER TABLE dbo.doctors
	--DROP CONSTRAINT UQ_ID
	print('down_to_v3 - drop PK constraint - unique id')
END