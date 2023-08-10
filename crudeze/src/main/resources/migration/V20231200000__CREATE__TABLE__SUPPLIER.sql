DO
$$
    BEGIN
        IF NOT EXISTS(SELECT 1 FROM pg_tables WHERE tablename = 'tb_supplier') THEN
            CREATE TABLE tb_supplier
            (
              supplier_id           			SERIAL PRIMARY KEY,
              name_supplier 							VARCHAR(45) NULL DEFAULT NULL,
							date_registration 					DATE NULL DEFAULT NULL,
							corporate_name 							VARCHAR(45) NULL DEFAULT NULL,
							fantasy_name 								VARCHAR(45) NULL DEFAULT NULL,
							cnpj 												VARCHAR(14) NULL DEFAULT NULL,
							responsible 								VARCHAR(45) NULL DEFAULT NULL,
							email 											VARCHAR(60) NULL DEFAULT NULL,
							municipal_registration 			VARCHAR(45) NULL DEFAULT NULL,
							state_registration 					VARCHAR(45) NULL DEFAULT NULL,
							phone 											VARCHAR(13) NULL DEFAULT NULL,
							celphone 										VARCHAR(13) NULL DEFAULT NULL,
							date_of_last_purchase 			DATE NULL DEFAULT NULL,
							id_address 									INT NULL,
							tb_address_id_address 			INT NOT NULL

            );

        comment on table tb_bank  is 'This table serves to storge bank records.';

        RAISE INFO 'Table bank was created';
    ELSE
        RAISE INFO 'Table bank already exists';
    END IF;
END
$$;