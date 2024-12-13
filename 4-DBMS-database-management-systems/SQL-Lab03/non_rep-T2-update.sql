USE dbms
GO

UPDATE dbms.dbo.donors SET [name] = 'Updated-Non-Repeatable'
WHERE donor_id = 1