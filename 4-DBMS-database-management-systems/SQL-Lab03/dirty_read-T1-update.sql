USE dbms
GO

SELECT * FROM dbms.dbo.donors

BEGIN TRAN
	UPDATE dbms.dbo.donors SET [name] = 'Updated-Dirty'
	WHERE donor_id = 1

	WAITFOR DELAY '00:00:05'
ROLLBACK TRANSACTION