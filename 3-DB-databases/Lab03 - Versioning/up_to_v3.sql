--Up to version 3 - Add DEFAULT Constraint - hospitalization_date = Current Date
USE Lab01
GO

--Return 0 rows even if there are null values in a column
SET ANSI_NULLS ON
GO
--Identifiers can be delimited by double quotation marks and literals must be delimited by single quotation marks
SET QUOTED_IDENTIFIER ON
GO

--CREATE
ALTER PROCEDURE [dbo].[up_to_v3]
AS
BEGIN
	--Prevent extra result sets from interfering with SELECT statements.
	SET NOCOUNT ON;

	ALTER TABLE dbo.patients
	ADD CONSTRAINT hospitalization_date_current DEFAULT GETDATE() FOR hospitalization_date
	print('up_to_v3 - add default constraint to - hospitalization_date: DATE = current date')
END