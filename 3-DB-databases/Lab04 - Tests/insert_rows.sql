Use Lab04Test
GO
--Needs at least DB Version 5 to work!
--Affected DBs:
	--addresses - 1 PL (addr_id)
	--donors - 1 PK (donor_id) & 1 FK (donor_addr)
	--doctors - multicolumn PK

--CREATE
ALTER PROCEDURE [dbo].[insert_rows] 
	@no_rows varchar(10),
	@table_name varchar(30)
	AS
BEGIN
SET NOCOUNT ON;

	declare @table varchar(100)
		set @table = ('[' + @table_name + ']')

	if ISNUMERIC(@no_rows) != 1
	BEGIN
		print('Invalid input; Required - number')
		return 1 
	END

	SET @no_rows = cast(@no_rows as INT)

	declare @counter int
	set @counter = 1

	DECLARE @addr_id INT
	DECLARE @addr_country VARCHAR(30)
	DECLARE @addr_town VARCHAR(30)
	DECLARE @addr_addr VARCHAR(100)

	DECLARE @donor_id INT
	DECLARE @donor_name VARCHAR(50)
	--DECLARE @donor_bd DATE
	DECLARE @donor_cnp BIGINT
	DECLARE @donor_addr VARCHAR(100)

	DECLARE @doctor_id INT
	DECLARE @doctor_name VARCHAR(50)
	--DECLARE @doctor_bd DATE
	DECLARE @doctor_cnp BIGINT
	DECLARE @doctor_addr VARCHAR(100)

	WHILE @counter <= @no_rows
	BEGIN
		IF @table_name = 'addresses'
		BEGIN
			SET @addr_id = @counter + 1000
			SET @addr_country = 'Romania'
			SET @addr_town = 'Cluj'
			SET @addr_addr = 'Str. W/e, Bl. W/e, Ap. W/e'
			INSERT INTO addresses 
				([addr_id],[country],[town],[addr])
			VALUES (@addr_id, @addr_country, @addr_town, @addr_addr)
		END

		IF @table_name = 'donors'
		BEGIN
			SET @donor_id = @counter + 1000
			SET @donor_name = 'Some Name'
			--SET @donor_bd = GETDATE()
			SET @donor_cnp = 1971234567890 + @counter
			--SET @donor_addr = @counter + 1000
			INSERT INTO donors 
				([donor_id],[name],[birth_date],[CNP]) --,[donor_addr]
			VALUES (@donor_id, @donor_name, GETDATE(), @donor_cnp) --, @donor_addr
		END

		IF @table_name = 'doctors'
		BEGIN
			SET @doctor_id = @counter + 1000
			SET @doctor_name = 'Some Other Name'
			--SET @doctor_bd = GETDATE()
			SET @doctor_cnp = 2971234567890 + @counter
			--SET @doctor_addr = @counter + 1000
			INSERT INTO doctors 
				([doctor_id],[name],[birth_date],[CNP])	--, [doctor_addr]
			VALUES (@doctor_id, @doctor_name, GETDATE(), @doctor_cnp) --, @doctor_addr
		END

		SET @counter = @counter + 1
	END
END

