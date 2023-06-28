DO
$$
    BEGIN
        IF NOT EXISTS(SELECT 1 FROM pg_tables WHERE tablename = 'tb_accounts_receive') THEN
            CREATE TABLE tb_accounts_receive
            (
              accounts_receive_id     	UUID PRIMARY KEY DEFAULT gen_random_uuid(),
             	description 							VARCHAR(60) NOT NULL,
							due_date 									DATE NOT NULL,
							date_document 						DATE NOT NULL,
							value_acconts_receivable 	DECIMAL(18,6) NOT NULL,
							pay_date 									DATE NOT NULL,
							situation 								VARCHAR(45) NOT NULL,
							id_accont 								VARCHAR(45) NOT NULL,
							account_type 							VARCHAR(45) NOT NULL,
							difference 								DECIMAL(18,6) NOT NULL,
							type_of_charge 						VARCHAR(45) NOT NULL,
							tb_category_id_category 	INT NOT NULL,
							tb_client_id_client 			INT NOT NULL
            );

        comment on table tb_accounts_receive  is 'This table serves to storge tb_accounts_receive records.';

        RAISE INFO 'Table tb_accounts_receive was created';
    ELSE
        RAISE INFO 'Table tb_accounts_receive already exists';
    END IF;
END
$$;