USE dbms
GO
BEGIN TRAN
	UPDATE dbms.dbo.donors SET Name = 'Gicu Deadlock' WHERE donor_id = 2

	UPDATE dbo.donation_centers SET Name = 'Strada Deadlock' WHERE dc_id = 2
COMMIT TRAN