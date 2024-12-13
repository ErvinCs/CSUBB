USE dbms
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE OR ALTER PROCEDURE dbo.usp_insert_mm_roll (
	--@don_id INT='6',
	@don_name VARCHAR(100)='',	--'sp2 don6',
	@don_bd DATE='02-NOV-67',
	@don_cnp BIGINT=112124,
	--@dc_id INT=5,
	@dc_name VARCHAR(100)='sp2 dc5'
)
AS
BEGIN
	DECLARE @err_id INT
	DECLARE @err_trace VARCHAR(100) = ''
	DECLARE @err_code INT = 0
	
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

        BEGIN TRAN;
			BEGIN TRY
				INSERT INTO donors([name],[birth_date],[CNP]) VALUES (@don_name,@don_bd,@don_cnp);
				SET @don_id = IDENT_CURRENT( 'dbo.donors' );
			END TRY
			BEGIN CATCH
				SET @err_code = 1
				RAISERROR('Insert error: donors',16,1);
			END CATCH
			
			SAVE TRANSACTION insert_donors_tran

			BEGIN TRY
				INSERT INTO donation_centers([name]) VALUES (@dc_name);
				SET @dc_id = IDENT_CURRENT( 'dbo.donation_centers' );
			END TRY
			BEGIN CATCH
				SET @err_code = 2
				RAISERROR('Insert error: donation_centers',16,1);
			END CATCH

			SAVE TRANSACTION insert_dc_save

			BEGIN TRY
				INSERT INTO donors_donation_centers([dcs_donors],[donors_dcs]) VALUES (@dc_id,@don_id);
			END TRY
			BEGIN CATCH
				SET @err_code = 3
				RAISERROR('Insert error: donors_donation_centers',16,1);
			END CATCH

			SAVE TRANSACTION insert_d_dc_save
        COMMIT TRANSACTION
		print('Transaction complete!')
	END TRY

	BEGIN CATCH
			IF @@TRANCOUNT > 0
			BEGIN
				--Rollback failed transactions & keep the changes of the succesful transactions
				IF (@err_code = 1)
				BEGIN
					ROLLBACK TRANSACTION insert_donors_tran
					EXEC usp_error_info
					COMMIT TRANSACTION
				END
				ELSE IF (@err_code = 2)
				BEGIN
					ROLLBACK TRANSACTION insert_dc_save
					EXEC usp_error_info 
					COMMIT TRANSACTION
				END
				ELSE IF (@err_code = 3)
				BEGIN
					ROLLBACK TRANSACTION insert_d_dc_save
					EXEC usp_error_info 
					COMMIT TRANSACTION
				END
				ELSE 
					BEGIN
					EXEC usp_error_info
					ROLLBACK TRANSACTION 
					COMMIT TRANSACTION
					END
			END
			ELSE
			--Print all other errors
				BEGIN
					EXEC usp_error_info
				END
	END CATCH
END