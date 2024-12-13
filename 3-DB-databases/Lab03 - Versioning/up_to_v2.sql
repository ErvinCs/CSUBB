--Up to version 2 - Add Field hospitalization_date: DATE
USE Lab01
GO

--Return 0 rows even if there are null values in a column
SET ANSI_NULLS ON
GO
--Identifiers can be delimited by double quotation marks and literals must be delimited by single quotation marks
SET QUOTED_IDENTIFIER ON
GO

--CREATE
ALTER PROCEDURE [dbo].[up_to_v2]
AS
BEGIN
	--Prevent extra result sets from interfering with SELECT statements.
	SET NOCOUNT ON;

	ALTER TABLE dbo.patients
	ADD hospitalization_date DATE
	print('up_to_v2 - add column to patients - hospitalization_date: DATE')
END