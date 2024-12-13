--Down to Version 4
USE Lab01
GO

--Return 0 rows even if there are null values in a column
SET ANSI_NULLS ON
GO
--Identifiers can be delimited by double quotation marks and literals must be delimited by single quotation marks
SET QUOTED_IDENTIFIER ON
GO

--CREATE
ALTER PROCEDURE [dbo].[down_to_v4]
AS
BEGIN
	--Prevent extra result sets from interfering with SELECT statements.
	SET NOCOUNT ON;

	ALTER TABLE dbo.doctors
	DROP CONSTRAINT UQ_CNP_NAME
	print('down_to_v4 - remove candidate key constraint - UQ_CNP_NAME UNIQUE(CNP, name)')
END