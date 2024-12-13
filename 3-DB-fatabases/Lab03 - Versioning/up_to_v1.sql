--Up to version 1 - Convert CNP from BIGINT to VARCHAR
USE Lab01
GO

--Return 0 rows even if there are null values in a column
SET ANSI_NULLS ON
GO
--Identifiers can be delimited by double quotation marks and literals must be delimited by single quotation marks
SET QUOTED_IDENTIFIER ON
GO

--CREATE
ALTER PROCEDURE [dbo].[up_to_v1]
AS
BEGIN
	--Prevent extra result sets from interfering with SELECT statements.
	SET NOCOUNT ON;

	ALTER TABLE dbo.patients
	ALTER COLUMN CNP VARCHAR(15)
	print('v1 - CNP - from bigint to varchar')
END