--Down to Version 5 - dop table
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

	DROP TABLE dbo.donation_center_members;
	print('down_to_v5 - drop table - donation_center_members')
END