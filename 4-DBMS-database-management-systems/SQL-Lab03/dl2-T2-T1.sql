USE dbms
GO
BEGIN TRAN
	UPDATE dbo.donation_centers SET Name = 'Strada Deadlock' WHERE dc_id = 2

	UPDATE dbms.dbo.donors SET name = 'GicuDeadlock' WHERE donor_id = 2	
COMMIT TRAN