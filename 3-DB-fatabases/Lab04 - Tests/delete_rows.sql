Use Lab04Test
GO
--Needs at least DB Version 5 to work!
--Affected DBs:
	--addresses - 1 PL (addr_id)
	--donors - 1 PK (donor_id) & 1 FK (donor_addr)
	--doctors - multicolumn PK

--CREATE
ALTER PROCEDURE [dbo].[delete_rows] 
	@no_rows varchar(10),
	@table_name varchar(30)
	AS
BEGIN
	SET NOCOUNT ON;

	IF ISNUMERIC(@no_rows) != 1
	BEGIN
		PRINT('Invalid input; Required - number')
		RETURN 1 
	END
	
	SET @no_rows = cast(@no_rows as INT)

	DECLARE @last_row INT
	IF @table_name = 'addresses'
		BEGIN
			SET @last_row = (select MAX(addr_id) FROM dbo.addresses) - @no_rows

			DELETE from dbo.addresses
			WHERE addr_id > @last_row
		END
		
		IF @table_name = 'donors'
		BEGIN
			SET @last_row =(select MAX(donor_id) FROM dbo.donors) - @no_rows

			DELETE FROM dbo.donors 
			WHERE donor_id > @last_row
		END

		IF @table_name = 'doctors'
		BEGIN
			DELETE FROM dbo.doctors
		END

END