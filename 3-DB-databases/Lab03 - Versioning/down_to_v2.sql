--Down to verion 2
USE Lab01
GO

--Return 0 rows even if there are null values in a column
SET ANSI_NULLS ON
GO
--Identifiers can be delimited by double quotation marks and literals must be delimited by single quotation marks
SET QUOTED_IDENTIFIER ON
GO

--CREATE
ALTER PROCEDURE [dbo].[down_to_v2]
AS
BEGIN
	--Prevent extra result sets from interfering with SELECT statements.
	SET NOCOUNT ON;

	--Not a constraint wut?
	ALTER TABLE dbo.patients
	DROP CONSTRAINT hospitalization_date_current
	print('down_to_v2 - remove constraint from patients - hospitalization_date: DATE')
END