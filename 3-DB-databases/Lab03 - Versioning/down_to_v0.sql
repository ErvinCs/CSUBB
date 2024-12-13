--Down to version 0
USE Lab01
GO

--Return 0 rows even if there ar enull values in a column
SET ANSI_NULLS ON
GO
--Identifiers can be delimited by double quotation marks and literals must be delimited by single quotation marks
SET QUOTED_IDENTIFIER ON
GO

--CREATE
ALTER PROCEDURE [dbo].[down_to_v0]
AS
BEGIN
	--Prevent extra result sets from interfering with SELECT statements.
	SET NOCOUNT ON;

	ALTER TABLE dbo.patients
	ALTER COLUMN CNP BIGINT NOT NULL
	print('v0 - CNP - from varchar to bigint')
END