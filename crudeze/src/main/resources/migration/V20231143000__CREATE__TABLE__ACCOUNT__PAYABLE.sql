DO
$$
    BEGIN
        IF NOT EXISTS(SELECT 1 FROM pg_tables WHERE tablename = 'tb_accounts_payable') THEN
            CREATE TABLE tb_accounts_payable
            (
              accounts_payable_id     SERIAL PRIMARY KEY,
              bank_name 							VARCHAR(60) NULL DEFAULT NULL,
							agency 									VARCHAR(45) NULL DEFAULT NULL,
							checking_account 				VARCHAR(45) NULL DEFAULT NULL,
							current_account_holder 	VARCHAR(100) NULL DEFAULT NULL,
							date_registration 			DATE NULL DEFAULT NULL,
							phone 									VARCHAR(45) NULL DEFAULT NULL,
							cpf_cnpj 								VARCHAR(45) NULL DEFAULT NULL,
							municipal_registration 	VARCHAR(45) NULL DEFAULT NULL,
							state_registration 			VARCHAR(45) NULL DEFAULT NULL
            );

        comment on table tb_accounts_payable  is 'This table serves to storge bank records.';

        RAISE INFO 'Table account payable was created';
    ELSE
        RAISE INFO 'Table account payable already exists';
    END IF;
END
$$;