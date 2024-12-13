Use Lab04Test
GO
--Needs at least DB Version 5 to work!
--Affected DBs:
	--addresses - 1 PK (addr_id)
	--donors - 1 PK (donor_id) & 1 FK (donor_addr)
	--doctors - multicolumn PK

--CREATE
ALTER PROCEDURE [dbo].[select_view] 
	@view_name varchar(50)
	AS
BEGIN
	SET NOCOUNT ON;

	IF @view_name = 'View_1_doctors_addresses'
	BEGIN
		SELECT * 
		FROM Lab04Test.dbo.View_1_doctors_addresses
	END

	IF @view_name = 'View_2_donors'
	BEGIN
		SELECT * 
		FROM Lab04Test.dbo.View_2_donors
	END

	IF @view_name = 'View_3_addresses_doctors_donors'
	BEGIN
		SELECT * 
		FROM Lab04Test.dbo.View_3_addresses_doctors_donors
	END
END