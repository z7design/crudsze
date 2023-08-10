DO
$$
    BEGIN
        IF NOT EXISTS(SELECT 1 FROM pg_tables WHERE tablename = 'tb_account_type_bank') THEN
            CREATE TABLE tb_account_type_bank
            (
              account_type_bank_id          		SERIAL PRIMARY KEY,
							name_account_type 								VARCHAR(45) NULL,
  						description 											VARCHAR(45) NULL
            );

        comment on table tb_account_type_bank  is 'This table serves to storge tb_account_type_bank records.';

        RAISE INFO 'Table tb_account_type_bank was created';
    ELSE
        RAISE INFO 'Table tb_account_type_bank already exists';
    END IF;
END
$$;