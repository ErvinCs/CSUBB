USE Lab01
GO

--Return 0 rows even if there ar enull values in a column
SET ANSI_NULLS ON
GO
--Identifiers can be delimited by double quotation marks and literals must be delimited by single quotation marks
SET QUOTED_IDENTIFIER ON
GO

--CREATE
ALTER PROCEDURE [dbo].[psvm]
	@newVer varchar(30)
AS
BEGIN
	--Prevent messages that show the number of rows affected by SQL statements
	SET NOCOUNT ON;

	DECLARE @next varchar(30)
	DECLARE @current INT
	SET @current = (SELECT version_id FROM dbo.tableVersions)

	IF ISNUMERIC(@newVer) != 1
	BEGIN
		PRINT('Invalid Version No.')
		RETURN 1
	END

	SET @newVer = cast (@newVer AS INT)
	IF @newVer < 0 or @newVer > 7
	BEGIN
		PRINT('Invalid Version No.')
		RETURN 2
	END

	WHILE @current < @newVer
	BEGIN
		SET @current = @current + 1
		SET @next = 'up_to_v' + convert(varchar(5), @current)
		EXECUTE @next
	END

	WHILE @current > @newVer
	begin
		SET @current = @current - 1
		SET @next = 'down_to_v' + convert(varchar(5), @current)
		EXECUTE @next
	end

	TRUNCATE TABLE Lab01.dbo.tableVersions
	INSERT INTO Lab01.dbo.tableVersions values(@newVer)
END