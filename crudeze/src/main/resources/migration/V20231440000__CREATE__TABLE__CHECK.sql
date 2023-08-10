DO
$$
    BEGIN
        IF NOT EXISTS(SELECT 1 FROM pg_tables WHERE tablename = 'tb_check') THEN
            CREATE TABLE tb_check
            (
              check_id          						SERIAL PRIMARY KEY,
							number_check 									INT NULL,
							status_check 									CHAR(1) NULL,
							date_status 									DATE NULL,
							id_checkbook 									INT NULL,
							tb_checkbook_id_checkbook 		INT NOT NULL,
							tb_check_issued_id_checkbook 	INT NOT NULL,
							tb_bank_id_bank 							INT NOT NULL
            );

        comment on table tb_check  is 'This table serves to storge tb_check records.';

        RAISE INFO 'Table tb_check was created';
    ELSE
        RAISE INFO 'Table tb_check already exists';
    END IF;
END
$$;