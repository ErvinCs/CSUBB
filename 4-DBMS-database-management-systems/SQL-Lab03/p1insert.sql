USE dbms
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE OR ALTER PROCEDURE dbo.usp_insert_mm (
	--@don_id INT='5',
	@don_name VARCHAR(100)='',	--'sp don5',
	@don_bd DATE='01-NOV-67',
	@don_cnp BIGINT=112123,
	--@dc_id INT=4,
	@dc_name VARCHAR(100)='sp dc4'
)
AS
BEGIN
	DECLARE @err_id INT
	DECLARE @err_trace VARCHAR(100) = ''
	
	BEGIN TRY
		SET @err_id = dbo.check_string(@don_name);
		IF (@err_id = 0)
			SET @err_trace += 'Invalid donor name\n';
		SET @err_id = dbo.check_string(@dc_name);
		IF (@err_id = 0)
			SET @err_trace += 'Invalid donation_center name\n'; 
		IF LEN(@err_trace) > 0
		BEGIN
			RAISERROR(@err_trace,16,1);
		END

		DECLARE @don_id INT
		DECLARE @dc_id INT

		BEGIN TRAN
			INSERT INTO donors([name],[birth_date],[CNP]) VALUES (@don_name,@don_bd,@don_cnp);
			SET @don_id = IDENT_CURRENT( 'dbo.donors' );

			INSERT INTO donation_centers([name]) VALUES (@dc_name);
			SET @dc_id = IDENT_CURRENT( 'dbo.donation_centers' );

			INSERT INTO donors_donation_centers([dcs_donors],[donors_dcs]) VALUES (@dc_id,@don_id);
	    COMMIT TRAN 
		print('Transaction complete!')
	END TRY

	BEGIN CATCH
		IF @@TRANCOUNT > 0
			ROLLBACK TRAN
			exec usp_error_info
		
	END CATCH
END