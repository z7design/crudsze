DO
$$
    BEGIN
        IF NOT EXISTS(SELECT 1 FROM pg_tables WHERE tablename = 'tb_cash_flow') THEN
            CREATE TABLE tb_cash_flow
            (
              cash_flow_id          		UUID PRIMARY KEY DEFAULT gen_random_uuid(),
              tb_client_id_client			 	INT NULL DEFAULT NULL,
							account_type 							VARCHAR(45) NULL DEFAULT NULL,
							quantify 									VARCHAR(45) NULL DEFAULT NULL,
							description 							VARCHAR(45) NULL DEFAULT NULL,
							value_of_cash 						DECIMAL(18,6) NULL DEFAULT NULL,
							date_registration 				DATE NULL DEFAULT NULL,
							total 										DECIMAL(18,6) NULL DEFAULT NULL
            );

        comment on table tb_cash_flow  is 'This table serves to storge tb_cash_flow records.';

        RAISE INFO 'Table tb_cash_flow was created';
    ELSE
        RAISE INFO 'Table tb_cash_flow already exists';
    END IF;
END
$$;