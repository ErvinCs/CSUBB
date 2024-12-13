USE dbms
GO

BEGIN TRANSACTION
	SELECT [name] FROM dbms.dbo.donors WHERE donor_id = 1

	WAITFOR DELAY '00:00:05'

	SELECT [name] FROM dbms.dbo.donors WHERE donor_id = 1
COMMIT TRANSACTION