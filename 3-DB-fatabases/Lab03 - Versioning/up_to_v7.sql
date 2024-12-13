--Up to Version 7 - add FK constraint
USE Lab01
GO

--Return 0 rows even if there are null values in a column
SET ANSI_NULLS ON
GO
--Identifiers can be delimited by double quotation marks and literals must be delimited by single quotation marks
SET QUOTED_IDENTIFIER ON
GO

--CREATE
ALTER PROCEDURE [dbo].[up_to_v7]
AS
BEGIN
	--Prevent extra result sets from interfering with SELECT statements.
	SET NOCOUNT ON;

	ALTER TABLE dbo.donation_centers
	ADD  FOREIGN KEY(working_dc) REFERENCES donation_center_members(dc_member_id)
	--patients_doctor INT REFERENCES doctors(doctor_id) NOT NULL,
	print('up_to_v7 - add FK constraint - wroking_dc: 1 dc - M dc_members')
END