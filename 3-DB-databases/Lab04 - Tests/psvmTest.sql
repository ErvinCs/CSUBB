Use Lab04Test
GO
--Needs at least DB Version 5 to work!
--Affected DBs:
	--addresses - 1 PL (addr_id)
	--donors - 1 PK (donor_id) & 1 FK (donor_addr)
	--doctors - multicolumn PK

--CREATE
ALTER PROCEDURE [dbo].[psvmTest]
	@nb_of_rows varchar(30)
AS
BEGIN
	SET NOCOUNT ON;

	if ISNUMERIC(@nb_of_rows) != 1
	BEGIN
		print('Invalid input; Required - number')
		return 1 
	END

	declare @all_start datetime
	set @all_start = GETDATE();

	declare @addresses_insert_start datetime
	set @addresses_insert_start = GETDATE()
	execute Lab04Test.dbo.insert_rows @nb_of_rows, 'addresses'
	declare @addresses_insert_end datetime
	set @addresses_insert_end = GETDATE()

	declare @donors_insert_start datetime
	set @donors_insert_start = GETDATE()
	execute Lab04Test.dbo.insert_rows @nb_of_rows, 'donors'
	declare @donors_insert_end datetime
	set @donors_insert_end = GETDATE()

	declare @doctors_insert_start datetime
	set @doctors_insert_start = GETDATE()
	execute Lab04Test.dbo.insert_rows @nb_of_rows, 'doctors'
	declare @doctors_insert_end datetime
	set @doctors_insert_end = GETDATE()

	declare @doctors_delete_start datetime
	set @doctors_delete_start = GETDATE()
	execute Lab04Test.dbo.delete_rows @nb_of_rows, 'doctors'
	declare @doctors_delete_end datetime
	set @doctors_delete_end = GETDATE()

	declare @donors_delete_start datetime
	set @donors_delete_start = GETDATE()
	execute Lab04Test.dbo.delete_rows @nb_of_rows, 'donors'
	declare @donors_delete_end datetime
	set @donors_delete_end = GETDATE()

	declare @addresses_delete_start datetime
	set @addresses_delete_start = GETDATE()
	execute Lab04Test.dbo.delete_rows @nb_of_rows, 'addresses'
	declare @addresses_delete_end datetime
	set @addresses_delete_end = GETDATE()

	declare @view_1_start datetime
	set @view_1_start = GETDATE()
	execute Lab04Test.dbo.select_view 'View_1_doctor_addresses'
	declare @view_1_end datetime
	set @view_1_end = GETDATE()

	declare @view_2_start datetime
	set @view_2_start = GETDATE()
	execute Lab04Test.dbo.select_view 'View_2_donors'
	declare @view_2_end datetime
	set @view_2_end = GETDATE()

	declare @view_3_start datetime
	set @view_3_start = GETDATE()
	execute Lab04Test.dbo.select_view 'View_3_addresses_doctors_donors'
	declare @view_3_end datetime
	set @view_3_end = GETDATE()

	declare @all_stop datetime 
	set @all_stop = getdate() 

	declare @description varchar(500)
	set @description = 'TestRun: ' + convert(varchar(10), (select max(TestRunID) from TestRuns)) + 'delete, insert' + @nb_of_rows + 'rows, select all views'

	--Can't insert explicit value for ID
	insert into Lab04Test.dbo.TestRuns(Description, StartAt, EndAt)
	values(@description, @all_start, @all_stop);

	declare @lastTestRunID int; 
	set @lastTestRunID = (select max(TestRunID) from TestRuns);

	insert into Lab04Test.dbo.TestRunTables
	values(@lastTestRunID, 1, @addresses_insert_start, @addresses_insert_end)

	insert into Lab04Test.dbo.TestRunTables
	values(@lastTestRunID, 2, @donors_insert_start, @donors_insert_end)

	insert into Lab04Test.dbo.TestRunTables
	values(@lastTestRunID, 3, @doctors_insert_start, @doctors_insert_end)

	insert into Lab04Test.dbo.TestRunTables
	values(@lastTestRunID, 4, @addresses_delete_start, @addresses_delete_end)

	insert into Lab04Test.dbo.TestRunTables
	values(@lastTestRunID, 5, @donors_delete_start, @donors_delete_end)

	insert into Lab04Test.dbo.TestRunTables
	values(@lastTestRunID, 6, @doctors_delete_start, @doctors_delete_end)

	insert into Lab04Test.dbo.TestRunViews
	values(@lastTestRunID, 1, @view_1_start, @view_1_end)
	
	insert into Lab04Test.dbo.TestRunViews
	values(@lastTestRunID, 2, @view_2_start, @view_2_end)

	insert into Lab04Test.dbo.TestRunViews
	values(@lastTestRunID,3, @view_3_start, @view_3_end)

END
