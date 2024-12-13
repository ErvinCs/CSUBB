--Up to Version 6 - add a table
USE Lab01
GO

--Return 0 rows even if there are null values in a column
SET ANSI_NULLS ON
GO
--Identifiers can be delimited by double quotation marks and literals must be delimited by single quotation marks
SET QUOTED_IDENTIFIER ON
GO

--CREATE
ALTER PROCEDURE [dbo].[up_to_v6]
AS
BEGIN
	--Prevent extra result sets from interfering with SELECT statements.
	SET NOCOUNT ON;

	CREATE TABLE donation_center_members(
	dc_member_id INT NOT NULL PRIMARY KEY,
	name VARCHAR(100) NOT NULL
	)
	print('up_to_v6 - create table - donation_center_members(dc_member_id, name)')
END